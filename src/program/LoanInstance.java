package program;

import java.time.LocalDate;

public class LoanInstance {
	
	private Book book;
	private LocalDate date;
	
	
	public LoanInstance(Book book) {
		this.book = book;
		this.date = LocalDate.now();
	}


	public Book getBook() {
		return book;
	}


	public void setBook(Book book) {
		this.book = book;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}
}