package program;
/***********************************
 * Name of the class: Book
 * 
 * Description: Class has getters and setters for all attributes except the setter for id. 
 * toString method
 * compareTo method checks if received object is instance of Book, then compares two books by author.
 * If received object is not instance of Book, 0 will be returned.
 * 
 * @author (Elsada Lagumdzic)
 * 
************************************/

import java.util.concurrent.atomic.AtomicInteger;

public class Book {

	private int id;
	private String isbn;
	private String title;
	private String author;
	private int year;
	private String category;
	private int shelf;
	private int quantity;
	private int loaned;

	// private String publisher;
	// exists in library class for sorting.

	static private AtomicInteger idGen = new AtomicInteger();

	public Book(String isbn, String title, String author, int year, String category, int shelf, int quantity) {
		this.id = idGen.incrementAndGet();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.year = year;
		this.category = category;
		this.shelf = shelf;
		this.quantity = quantity;
		this.loaned = 0;
	}

	public int getId() {
		return this.id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getShelf() {
		return shelf;
	}

	public void setShelf(int shelf) {
		this.shelf = shelf;
	}
	 
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getLoaned() {
		return loaned;
	}

	public void setLoaned(int loaned) {
		this.loaned = loaned;
	}
	 
	public int getAvailableQuantity() {
		return this.quantity - this.loaned;
	}

	public void addQuantity(int qty) {
		this.quantity += qty;
	}
	
	public void loan() {
		this.loaned += 1;
	}
	
	public void returnBook() {
		this.loaned -= 1;
	}
	
	public String toString() {
		return "Title: " + this.title + ", Author: " + this.author + ", Year: " + this.year + ", Category: " + this.category
				+ ", Shelf: " + this.shelf + ", Isbn: " + this.isbn;

	}

}
