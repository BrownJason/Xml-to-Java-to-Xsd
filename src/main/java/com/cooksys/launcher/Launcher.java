package com.cooksys.launcher;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.wiztools.xsdgen.ParseException;
import org.wiztools.xsdgen.XsdGen;

import com.cooksys.books.Author;
import com.cooksys.books.Book;
import com.cooksys.books.Librarian;
import com.cooksys.books.Library;
import com.cooksys.books.Book.Authors;
import com.cooksys.books.Library.Books;
import com.cooksys.books.Library.Librarians;

public class Launcher {
	
	public static void main(String[] args) throws JAXBException, DatatypeConfigurationException, IOException, ParseException {
		JAXBContext context = JAXBContext.newInstance(Author.class, Book.class, Librarian.class, Library.class);
	
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(generateLibrary(), new FileOutputStream(new File("library.xml")));
		
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Library library = (Library) unmarshaller.unmarshal(new File("library.xml"));
		marshaller.marshal(library, System.out);
		
		XsdGen gen = new XsdGen();
		gen.parse(new File("library.xml"));
		File out = new File("library.xsd");
		gen.write(new FileOutputStream(out));
		
	}
	
	private static Library generateLibrary() throws DatatypeConfigurationException{
		
		Book book = new Book();
		
		book.setTitle("Eight Mile");
		book.setGenre("Biography");
		
		book.setDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(2006, 9, 5)));
		
		Author a = new Author();
		a.setFirstName("Marshall");
		a.setLastName("Mathers");
		
		Book goodBook = new Book();
		goodBook.setTitle("SuperBetter");
		goodBook.setGenre("Self-Help/Psychology");
		goodBook.setDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(2015, 4, 25)));
		
		Author b = new Author();
		b.setFirstName("Jane");
		b.setLastName("McGonigal");
		
		Authors author = new Authors();
		author.getAuthor().add(a);
		
		book.setAuthors(author);
		
		Authors goodBookAuthor = new Authors();
		goodBookAuthor.getAuthor().add(b);
		
		goodBook.setAuthors(goodBookAuthor);
		
		Books books = new Books();
		books.getBook().add(book);
		books.getBook().add(goodBook);
		
		Library library = new Library();
		
		library.setLocation("Memphis, TN");
		library.setName("Nameless Library");
		library.setAddress("34535 N Quail Hollow");
		
		Librarian librarian = new Librarian();
		librarian.setFirstName("Jason");
		librarian.setLastName("Brown");
		librarian.setPosition("Head Librarian");
		
		Librarians librarians = new Librarians();
		librarians.getLibrarian().add(librarian);
		
		library.setLibrarians(librarians);
		library.setBooks(books);
		
		return library;
	}
}

