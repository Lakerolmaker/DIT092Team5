package program;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class LoanInstance {
	
	private Book book;
	private LocalDate date;
	private LocalDate returnDate;
	private LocalDate returnedDate;
	
	public LoanInstance(Book book) {
		this.book = book;
		this.date = LocalDate.now();
		this.returnDate = LocalDate.now().plusDays(Library.LOAN_ALLOWANCE);
	}
	
	public LoanInstance(Book book, LocalDate returnDate) throws Exception {
		this.book = book;
		this.date = LocalDate.now();
		setReturnDate(returnDate);
	}

	public LoanInstance(Book book, LocalDate returnDate, LocalDate returnedDate) throws Exception {
		this.book = book;
		this.date = LocalDate.now();
		setReturnDate(returnDate);
		this.returnedDate = returnedDate;
	}
	
	public LocalDate getReturnDate() {
		return returnDate;
	}

	public LocalDate getReturnedDate() {
		return returnedDate;
	}

	public void setReturnedDate() {
		this.returnedDate = LocalDate.now();
	}
	
	public void setReturnDate(LocalDate returnDate) throws Exception {
		LocalDate now = LocalDate.now();
		if(returnDate.isAfter(now)) {
			if (returnDate.isBefore(now.plusDays(Library.LOAN_ALLOWANCE +1))) {
				this.returnDate = returnDate;
			}
			else {
				throw new Exception("Maximum loan allowance is " + (Library.LOAN_ALLOWANCE) + " days");
			}
		}
		else {
			throw new Exception("Return date has to be after today.");
		}
		
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