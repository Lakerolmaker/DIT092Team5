package program;

import java.time.LocalDate;

public class UserBookList {
	private String title;
	private String author;
	private LocalDate date;
	private Book book;
	private LocalDate returnDate;
	private LocalDate returnedDate;
	
	public UserBookList(LoanInstance lI) {
		this.title = lI.getBook().getTitle();
		this.author = lI.getBook().getAuthor();
		this.date = lI.getDate();
		this.book = lI.getBook();
		this.returnDate = lI.getReturnDate();
		this.returnedDate = lI.getReturnedDate();
	}
	
	public Book getBook() {
		return this.book;
	}
	
	public LocalDate getReturnDate() {
		return this.returnDate;
	}
	
	public LocalDate getReturnedDate() {
		return this.returnedDate;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public LocalDate getDate() {
		return this.date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
}
