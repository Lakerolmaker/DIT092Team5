package program;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.gson.Gson;

import database.DatabaseHelper;
import database.FileClass;
import javafx.scene.image.Image;


public class Library {
	
	FileClass file = new FileClass();
	Gson gson = new Gson();
	
	private String name; //: Name of the library
	public static int LOAN_ALLOWANCE = 14; // Just changed to capital letters
	private ArrayList<User> userDirectory;
	private ArrayList<Book> bookDirectory;
	

	public Library(String libraryName) {
		userDirectory = new ArrayList<User>();
		bookDirectory = new ArrayList<Book>();
		this.name = libraryName;
	}

	/** Add book to library **/
	public boolean addBook(String isbn, String title, String author, int year, String category, int shelf, int qty, String image, String publisher) throws Exception {
		for (Book book : bookDirectory) {
			if (book.getIsbn().equals(isbn)) {
				int currentQty = book.getQuantity();
				book.setQuantity(currentQty + qty);
				return false;
			}
		}
		try {
			Book newbook = new Book(isbn, title, author, year, category, shelf, qty, image, publisher);
			bookDirectory.add(newbook);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/** Remove book from library */
	public void removeBook(Book book, int qty){
		if (bookDirectory.contains(book)) {
			int currentQty = book.getQuantity();
			if (qty >= currentQty) {
				book.setQuantity(0);
				bookDirectory.remove(book);
			}else {
				book.setQuantity(currentQty - qty);
			}
		}
	}
	
	/** Find book by ISBN */
	public Book findBookByIsbn(String isbn){
		for (Book book : bookDirectory) {
			if (book.getIsbn().equals(isbn)) {
				return book;
			}
		}
		return null;
	}
	
	/** Register user **/
	public void addUser(String firstName, String lastName, String ssn, String phoneNr, String street, String zipCode, String city) throws Exception {
		if (findUser(ssn) != null) {	// Search for duplicate using SSN
			throw new Exception("Error: Customer with same SSN already exists in db");

		}else {				// If no duplicate found - a new User are registered
			User newUser = new User(firstName, lastName, ssn, phoneNr, street, zipCode, city);
			userDirectory.add(newUser);
		}
	}
	
	/** Register user **/
	public void addUser(User user) throws Exception {
		if (findUser(user.getSsn()) != null) {	// Search for duplicate using SSN
			throw new Exception("Error: Customer with same SSN already exists in db");

		}else {				// If no duplicate found - a new User are registered
			userDirectory.add(user);
		}
	}
	
	
	/** Remove user **/
	public void removeUser(User user) throws Exception {
		if (userDirectory.contains(user)) {
			userDirectory.remove(user);
		}
		else {
			throw new Exception("User could not be found.");
		}
	}
	
	
	/** Retrieve user from their id **/
	public User getUser(int id) {
		for (User user : userDirectory) {
			if (user.getUserId() == id) {
				return user;
			}
		}
		return null;
	}
	
	/** Find User by SSN **/
	public User findUser(String ssn) {
		for (User user : userDirectory) {
			if (user.getSsn().equals(ssn)) {
				return user;
			}
		}
		return null;
	}
	

	/** Loan book  **/
	public void loanBook(User user, Book book, LocalDate returnDate) throws Exception {
		if (book.getAvailableQuantity() > 0) {
			user.borrowBook(book, returnDate);
			book.loanTotal++;
			user.setLoanedBooksTotal(user.getLoanedBooksTotal() + 1);
		}else {
			throw new Exception("Error: The book is not avalaible.");
		}
	}
	
	/** Return book **/
	public void returnBook(User user, Book book) {
		ArrayList<Integer> list = user.getBookIndex(book);
		
		if(list.size() == 1) {
			user.setDebt( user.getOldDebt() +  user.getDelayfee(list.get(0)) );
			user.removeBorrowedBook(list.get(0));
			
		}else {
			// User have multiple copies - Returning the copy that was borrowed first
			int firstToReturn = 0;
			int daysLeft = user.getDaysLeft(list.get(0));
			for (int i = 1; i < list.size(); i++) {
				if (user.getDaysLeft(list.get(i)) < daysLeft) {
					daysLeft = user.getDaysLeft(list.get(i));
					firstToReturn = list.get(i);
				}
			}
			user.setDebt( user.getOldDebt() + user.getDelayfee(firstToReturn) );
			user.removeBorrowedBook(firstToReturn);
		}
	}
	
	public void setLoanAllowance(int value) {
		LOAN_ALLOWANCE = value;
	}
	
	public int getLoanAllowance() {
		return LOAN_ALLOWANCE;
	}
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList<Book> getBookList(){
		return this.bookDirectory;
	}
	
	public ArrayList<User> getUserList(){
		return this.userDirectory;
	}
	
	
	public void setID(int newID){
		AtomicInteger AtomicInteger = new AtomicInteger();
		AtomicInteger.set(newID);
		this.userDirectory.get(0).setNextId(AtomicInteger);
		
	}

	public int getID(){
		return User.getNextId().intValue();
	}
	
	
	
	/** Save library **/
	public void save() {
		DatabaseHelper db = new DatabaseHelper();
		db.setLOAN_ALLOWANCE(LOAN_ALLOWANCE);
		db.setID(this.getID());
		db.deleteDatabase(this.name);
		db.saveLibrary(this);
		System.out.println("Session saved");
	
	}
	/** Load library **/
	public void load() {
		DatabaseHelper db = new DatabaseHelper();
		db.loadLibrary(this.name);
		userDirectory = db.getUserList();
		bookDirectory = db.getBookList();
		LOAN_ALLOWANCE = db.getLOAN_ALLOWANCE();
		this.setID(db.getID());
	}
	
	public void returnAllBooks(){
		for (User user : userDirectory) {
			ArrayList<LoanInstance> bookList;
			
		try {
			bookList = user.getBookList();
			for (int i = bookList.size() - 1 ; i >= 0 ; i--) {
				this.returnBook(user, user.getBookList().get(i).getBook());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		}
	}

	//: Removes all items in the library
	public void clear() {
		userDirectory.clear();
		bookDirectory.clear();
	}
	
	//: Reset all loaned books 
	public void reset() {
		
		for (Book book : bookDirectory) {
			book.setLoaned(0);
		}
		
		for (User user : userDirectory) {
			user.clearBookList();
		}
		
	}
	
	//: deletes the saved copy of the library
	public void purge() {
		DatabaseHelper db = new DatabaseHelper();
		db.deleteDatabase(this.name);
	}
	
}
