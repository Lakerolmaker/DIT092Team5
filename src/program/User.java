package program;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description: User class with attributes (first name, last name, ssn, user ID (library card number), dept, 
 * phone number, street, zip code and city) and getters/setters.
 * @author Tihana Causevic
 */

public class User {

	private String firstName;
	private String lastName;
	private String ssn; 
	private int userId;
	private double debt;
	private String phoneNr; 
	private String street;
	private String zipCode;
	private String city;
	private ArrayList<Book> books = new ArrayList<>();
	private HashMap<String, LocalDate> booksBorrowed = new HashMap<String, LocalDate>();
	static AtomicInteger nextId = new AtomicInteger();

	public User(String firstName, String lastName, String ssn, String phoneNr, String street, String zipCode, String city) {
		this.userId = nextId.incrementAndGet();
		this.firstName = firstName;
		this.lastName = lastName;
		this.debt = 0;
		this.ssn = ssn;
		this.phoneNr = phoneNr;
		this.street = street;
		this.zipCode = zipCode;
		this.city = city;
	}

	public String getName() {
		return this.firstName + " " + this.lastName;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
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


	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

		for (HashMap.Entry<String, LocalDate> entry : booksBorrowed.entrySet()) {
			if (entry.getKey().equals(Integer.toString(bookID)));
				returnDate = entry.getValue().plus(Library.LOAN_ALLOWANCE, ChronoUnit.DAYS);
		}
		return returnDate;

	}
	
	public int getDaysLeft(Book book) {
		LocalDate today = LocalDate.now();
		LocalDate returnDate = getBorrowedBookReturnDate(book.getId());
		int days = (int) (returnDate.toEpochDay() - today.toEpochDay());
		
		return days;
	}
	
	public ArrayList<Book> getBookList() {
		return books;
	}
	
	public HashMap<String, LocalDate> getBookMap() {
		return this.booksBorrowed;
	}
	
	public void borrowBook(Book book) {
		LocalDate today = LocalDate.now();
		books.add(book);
		int lastIndex = books.size() - 1;
		String index = Integer.toString(lastIndex);
		booksBorrowed.put(index, today);
		book.loan();
	}
	
	public ArrayList<Book> getDelayedBooks(){
		ArrayList<Book> temp = new ArrayList<Book>();
		
		for(int i = 0; i < getBookList().size(); i++) {
			
			Book selectedBook = books.get(i);
			
			if(getDaysLeft(selectedBook) < 0) {
				temp.add(selectedBook);
			}	
		}
		
		return temp;
	}
	
	public void setLendDate(Book book, LocalDate date) {
		String key = getHashMapkey(book);
		
		booksBorrowed.put(key, date);
	}
	
	public String getHashMapkey(Book book) {
		
		for (int i = 0; i < books.size(); i++) {
			if(books.get(i).getId() == book.getId()) {
				return Integer.toString(i);
			}
		}
		
		return null;
	}

	public void removeBorrowedBook(Book book) {
		for (int i = 0; i < books.size(); i++) {
			if(books.get(i).getIsbn().equals(book.getIsbn())){
				books.remove(i);
				booksBorrowed.remove(i);
			}
		}
		book.returnBook();

	}

}
