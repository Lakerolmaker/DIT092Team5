
/***********************************
 * Name of the class: Book
 * 
 * Description: Class has getters and setters for all attributes except the setter for id. 
 * toString method
 * compareTo method checks if received object is instance of Book, then compare two books by author.
 * If received object is not instance of book, 0 will be returned.
 * 
 * @author (Elsada Lagumdzic)
 * 
************************************/

public class Book {

	private int id;
	private String name;
	private String author;
	private int year;
	private String category;
	private int shelf;
	 
	public Book(int id, String name, String author, int year, String category, int shelf) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.year = year;
		this.category = category;
		this.shelf = shelf;
	}

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
		return "Name: " + this.name + 
			    "Author: " + this.author +
				"Year: " + this.year +
			    "Category: " + this.category +
			    "Shelf: " + this.shelf;
				
	}
	
	public int compareTo(Object e) {
		if (e instanceof Book) {
			Book b = (Book)e;
			return this.author.compareTo(b.getAuthor());
		}
		return 0;
	}
	 
}
