package com.cooksys.books;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="library", propOrder= {
		"name",
		"location",
		"address",
		"librarians",
		"books"
})
public class Library {
	
	private String name;
	private String location;
	private String address;
	private Library.Books books;
	private Library.Librarians librarians;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Library.Books getBooks() {
		return books;
	}
	public void setBooks(Library.Books books) {
		this.books = books;
	}
	public Library.Librarians getLibrarians() {
		return librarians;
	}
	public void setLibrarians(Library.Librarians librarians) {
		this.librarians = librarians;
	}
	
	public static class Books {
		public List<Book> book;
		
		public List<Book> getBook(){
			if (book == null) {
				book = new ArrayList<Book>();
            }
            return this.book;
		}	
	}
	
	public static class Librarians {
		public List<Librarian> librarian;
		
		public List<Librarian> getLibrarian(){
			if(librarian == null) {
				librarian = new ArrayList<Librarian>();
			}
			return this.librarian;
		}
	}
}
