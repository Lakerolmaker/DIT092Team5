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
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

public class Book {

	private int id;
	private String isbn;
	private String title;
	private String author;
	private int year;
	private String category;
	private int shelf;

	// private String publisher;
	// exists in library class for sorting.

	static private AtomicInteger idGen = new AtomicInteger();

	public Book(String isbn, String title, String author, int year, String category, int shelf) {
		this.id = idGen.incrementAndGet();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.year = year;
		this.category = category;
		this.shelf = shelf;
	}

	public Book(int id, String isbn, String title, String author, int year, String category, int shelf) {
		this.id = id;
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.year = year;
		this.category = category;
		this.shelf = shelf;

		idGen.set(id);
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

	public String toString() {
		return "Title: " + this.title + "Author: " + this.author + "Year: " + this.year + "Category: " + this.category
				+ "Shelf: " + this.shelf + "Isbn: " + this.isbn;

	}

	public int compareTo(Object e) {
		if (e instanceof Book) {
			Book b = (Book) e;
			return this.author.compareTo(b.getAuthor());
		}
		return 0;
	}

	// There is no need to add Comparator for author since I already created
	// method compare to that do the same

	// ascending order AZ
	public static Comparator<Book> authorComparatorAZ = new Comparator<Book>() {

		public int compare(Book b1, Book b2) {
			String authorName1 = b1.getAuthor().toUpperCase();
			String authorName2 = b2.getAuthor().toUpperCase();

			return authorName1.compareTo(authorName2);

		}
	};

	// descending order ZA
	/*
	 * public static Comparator<Book> authorComparatorZA = new
	 * Comparator<Book>() {
	 * 
	 * public int compare(Book b1, Book b2) { String authorName1 =
	 * b1.getAuthor().toUpperCase(); String authorName2 =
	 * b2.getAuthor().toUpperCase();
	 * 
	 * return authorName2.compareTo(authorName1);
	 * 
	 * } };
	 */

	public static Comparator<Book> nameComparatorAZ = new Comparator<Book>() {

		public int compare(Book b1, Book b2) {
			String bookName1 = b1.getTitle().toUpperCase();
			String bookName2 = b2.getTitle().toUpperCase();

			return bookName1.compareTo(bookName2);

		}
	};

	/*
	 * public static Comparator<Book> nameComparatorZA = new Comparator<Book>()
	 * {
	 * 
	 * public int compare(Book b1, Book b2) { String bookName1 =
	 * b1.getName().toUpperCase(); String bookName2 =
	 * b2.getName().toUpperCase();
	 * 
	 * return bookName2.compareTo(bookName1);
	 * 
	 * } };
	 */

	public static Comparator<Book> shelfComparatorASC = new Comparator<Book>() {

		public int compare(Book b1, Book b2) {
			int shelf1 = b1.getShelf();
			int shelf2 = b2.getShelf();

			return Integer.compare(shelf1, shelf2);

		}
	};

	public static Comparator<Book> genreComparatorAZ = new Comparator<Book>() {

		public int compare(Book b1, Book b2) {
			String genre1 = b1.getCategory().toUpperCase();
			String genre2 = b2.getCategory().toUpperCase();

			return genre1.compareTo(genre2);

		}
	};

	

}
