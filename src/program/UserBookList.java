package program;

import java.time.LocalDate;

public class UserBookList {
	private String title;
	private String author;
	private LocalDate date;
	
	public UserBookList(LoanInstance lI) {
		this.title = lI.getBook().getTitle();
		this.author = lI.getBook().getAuthor();
		this.date = lI.getDate();
	}
	
	public UserBookList(String title, String author, LocalDate date) {
		this.title = title;
		this.author = author;
		this.date = date;
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
