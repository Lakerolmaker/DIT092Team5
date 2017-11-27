package program;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class User {

	private String name;
	private String ssn; // What is this ?
	private int userId;
	private int libraryCardNum;
	private double debt;
	private int phoneNr;
	private Object address;

	private HashMap<Book, LocalDate> booksBorrowed = new HashMap<Book, LocalDate>();

	static AtomicInteger nextId = new AtomicInteger();

	User(String name, int userId, double debt, String ssn, int phoneNr, Object adress) {
		this.name = name;
		this.userId = userId;
		this.libraryCardNum = this.userId;
		this.debt = debt;
		this.ssn = ssn;
		this.phoneNr = phoneNr;
		this.address = adress;
	}

	public String getName() {
		return this.name;
	}

	public int getUserId() {
		return this.userId;
	}

	public double getDebt() {
		return this.debt;
	}

	public String getSsn() {
		return this.ssn;
	}

	public int getPhoneNr() {
		return this.phoneNr;
	}

	public Object getAdress() {
		return this.address;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDebt(double debt) {
		this.debt = debt;
	}

	public void setUserId() {
		this.userId = nextId.incrementAndGet();
	}

	public void setPhoneNr(int phoneNr) {
		this.phoneNr = phoneNr;
	}

	public void setAddress(Object adress) {
		this.address = address;
	}

	public double getDelayfee(int bookID) {

		LocalDate today = LocalDate.now();
		LocalDate returnDate = getBorrowedBookReturnDate(bookID);

		double days = today.toEpochDay() - returnDate.toEpochDay();

		if (days > 0)

			this.debt = days * 2;

		return this.debt;

		// debt for 1 book only; iterate through borrowed books to get full debt

	}

	public LocalDate getBorrowedBookReturnDate(int bookID) {

		LocalDate returnDate = null;

		for (HashMap.Entry<Book, LocalDate> entry : booksBorrowed.entrySet())
			if (entry.getKey().getId() == bookID)
				returnDate = entry.getValue().plus(Library.loanAllowance, ChronoUnit.DAYS);

		return returnDate;

	}

	public void borrowBook(Book book) {
		LocalDate today = LocalDate.now();
		booksBorrowed.put(book, today);

	}

	public void removeBorrowedBook(Book book) {
		booksBorrowed.remove(book);

	}

}
