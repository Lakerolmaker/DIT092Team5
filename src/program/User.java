package program;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import frontend.MainWindow;

/**
 * Description: User class with attributes (first name, last name, ssn, user ID (library card number), debt, 
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
	private int loanedBooksTotal;
	private ArrayList<LoanInstance> bookList = new ArrayList<>();
	static AtomicInteger nextId = new AtomicInteger();

	public User(String firstName, String lastName, String ssn, String phoneNr, String street, String zipCode, String city) {
		this.userId = nextId.incrementAndGet();   // autoincrements user id
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

	//: Gets the total debt , from both the debt from previously borrowed books and currently borrowed books.
	public double getDebt() {
		this.debt += calculateDebt();
		return this.debt;
	}
	
	public void setDebt(double debt) {
		this.debt = debt;
	}
	
	//: Get's the total debt from currently borrowed books.
	public double calculateDebt() {
		
		double tempDebt =  0;
		
		for(int i = 0 ;  i < bookList.size(); i++) {
			tempDebt += this.getDelayfee(i);
		}
	
		return tempDebt;
		
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
	

	public double getDelayfee(int bookListIndex) {  // debt for 1 book only; iterate through borrowed books to get full debt

		LocalDate today = LocalDate.now();
		LocalDate returnDate = getBorrowedBookReturnDate(bookListIndex);

		double days = today.toEpochDay() - returnDate.toEpochDay();

		if (days > 0)
			return days * 2;

		return 0;


	}

	public LocalDate getBorrowedBookReturnDate(int bookListIndex) {  // gets the date when the book should be returned
		LocalDate returnDate = bookList.get(bookListIndex).getReturnDate();
		return returnDate;
	}
	
	public int getDaysLeft(int bookListIndex) { // leftover days for returning a book
		LocalDate today = LocalDate.now();
		LocalDate returnDate = getBorrowedBookReturnDate(bookListIndex);
		int days = (int) (returnDate.toEpochDay() - today.toEpochDay());
		return days;
	}
	
	public ArrayList<LoanInstance> getBookList() throws Exception {   // returns book list
		if (bookList == null) {
			throw new Exception("Users BookList is empty");
		}else {
			return bookList;
		}
	}

	public void borrowBook(Book book, LocalDate returnDate) throws Exception {  // adds the book to the list of borrowed books
		LoanInstance tmp = new LoanInstance(book, returnDate);
		bookList.add(tmp);
		book.loan();
	}
	
	public ArrayList<LoanInstance> getDelayedBooks(){    // gets the list of delayed books
		ArrayList<LoanInstance> temp = new ArrayList<>();
		for (int i = 0; i < bookList.size(); i++) {
			if(getDaysLeft(i) < 0) {
				temp.add(bookList.get(i));
			}
		}
		return temp;
	}
	
	public void setLendDate(int bookListIndex, LocalDate date) {  // sets the date when the book should be returned
		bookList.get(bookListIndex).setDate(date);
	}
	
	
	public void removeBorrowedBook(int bookListIndex) {  // removes the book from list of borrowed books
		Book book = bookList.get(bookListIndex).getBook();
		ArrayList<Book> mainBookList = MainWindow.lib.getBookList();
		for (Book tmpBook : mainBookList) {
			if (tmpBook.getIsbn().equals(book.getIsbn())) { 
				tmpBook.returnBook();
			}
		}
		bookList.remove(bookListIndex);
	}
	
	public ArrayList<Integer> getBookIndex(Book book) {  // returns the book index of a given book
		ArrayList<Integer> tmp = new ArrayList<>();
		for (int i = 0; i < bookList.size(); i++) {
			if(bookList.get(i).getBook().getIsbn().equals(book.getIsbn())) {
				tmp.add(i);
			}
		}
		return tmp;
	}

	public static AtomicInteger getNextId() {
		return nextId;
	}

	public static void setNextId(AtomicInteger nextId) {
		User.nextId = nextId;
	}

	public int getLoanedBooksTotal() {
		return loanedBooksTotal;
	}

	public void setLoanedBooksTotal(int loanedBooksTotal) {
		this.loanedBooksTotal = loanedBooksTotal;
	}
	
	public void clearBookList() {
		this.bookList.clear();
	}
	
}