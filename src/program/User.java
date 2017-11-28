package program;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class User {

	private String firstName;
	private String lastName;
	private String ssn; 
	private int userId;
	private int libraryCardNum;
	private double debt;
	private String phoneNr; // Changed to string
	private Address address;

	private HashMap<Book, LocalDate> booksBorrowed = new HashMap<Book, LocalDate>();
	static AtomicInteger nextId = new AtomicInteger();

	public User(String firstName, String lastName, String ssn, String phoneNr, String street, String zipCode, String city) {
		this.userId = nextId.incrementAndGet();
		this.firstName = firstName;
		this.lastName = lastName;
		this.libraryCardNum = this.userId; // 2 variables storing the same value ?
		this.debt = 0; 
		this.ssn = ssn;
		this.phoneNr = phoneNr;
		this.address = new Address(street, zipCode, city);
	}

	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.firstName;
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

	public String getPhoneNr() {
		return this.phoneNr;
	}

	public Address getAdress() {
		return this.address;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setDebt(double debt) {
		this.debt = debt;
	}

	public void setPhoneNr(String phoneNr) {
		this.phoneNr = phoneNr;
	}

	public void setAddress(Address adress) {
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
				returnDate = entry.getValue().plus(Library.LOAN_ALLOWANCE, ChronoUnit.DAYS);

		return returnDate;

	}

	public void borrowBook(Book book) {
		LocalDate today = LocalDate.now();
		booksBorrowed.put(book, today);
		book.loan();

	}

	public void removeBorrowedBook(Book book) {
		booksBorrowed.remove(book);
		book.returnBook();
	}

}
