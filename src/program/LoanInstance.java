package program;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class LoanInstance {
	
	private Book book;
	private LocalDate date;
	
	
	public LoanInstance(Book book) {
		this.book = book;
		this.date = LocalDate.now().plus(2, ChronoUnit.WEEKS);
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