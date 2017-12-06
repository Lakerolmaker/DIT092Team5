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

class BorrowedBooks{
	Book book;
	LocalDate date;
	
	BorrowedBooks(Book book, LocalDate date){
		this.book = book;
		this.date = date;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}
	
	public Book getBook() {
		return this.book;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public LocalDate getDate() {
		return this.date;
	}
}

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
	private ArrayList<BorrowedBooks> bB = new ArrayList<BorrowedBooks>();
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

		for(BorrowedBooks b : bB) {
			if(b.getBook().getId() == bookID) {
				System.out.println(b.getDate());
				return b.getDate();
			}
			System.out.println("Book ID: " + b.getBook().getId());
		}
		return LocalDate.now();

	}
	
	public int getDaysLeft(Book book) {
		LocalDate today = LocalDate.now();
		LocalDate returnDate = getBorrowedBookReturnDate(book.getId());
		int days = (int) (returnDate.toEpochDay() - today.toEpochDay());
		
		return days;
	}
	
	public ArrayList<BorrowedBooks> getBookList() {
		return bB;
	}
	
	public void borrowBook(Book book) {
		LocalDate today = LocalDate.now();
		this.bB.add(new BorrowedBooks(book, today));
		
		for (BorrowedBooks b: bB) {
			System.out.println(b.getBook().getId());
		}
		
		System.out.println("This book id has been borrowed: " + book.getId());
		
		book.loan();
	}
	
	public ArrayList<Book> getDelayedBooks(){
		ArrayList<Book> temp = new ArrayList<Book>();
		
		/*for(int i = 0; i < getBookList().size(); i++) {
			
			Book selectedBook = books.get(i);
			
			if(getDaysLeft(selectedBook) < 0) {
				temp.add(selectedBook);
			}	
		}*/
		
		for (BorrowedBooks b : bB) {
			if (b.getDate().isAfter(LocalDate.now())) {
				temp.add(b.getBook());
			}
		}
		
		return temp;
	}
	
	/**
	public void setLendDate(Book book, LocalDate date) {
		
	}
	**/

	public void removeBorrowedBook(Book book) {
		for (BorrowedBooks b : bB) {
			if (b.getBook().getId() == book.getId()) {
				bB.remove(b);
			}
		}
	}

}
