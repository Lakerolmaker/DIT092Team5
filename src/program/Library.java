package program;
import java.util.ArrayList;

import com.google.gson.Gson;

import database.DatabaseHelper;
import database.FileClass;


public class Library {
	
	FileClass file = new FileClass();
	Gson gson = new Gson();
	
	public static int LOAN_ALLOWANCE = 14; // Just changed to capital letters
	private ArrayList<User> userDirectory;
	private ArrayList<Book> bookDirectory;
	

	public Library() {
		userDirectory = new ArrayList<User>();
		bookDirectory = new ArrayList<Book>();
	}

	/** Add book to library **/
	public void addBook(String isbn, String title, String author, int year, String category, int shelf, int qty) throws Exception {
		for (Book book : bookDirectory) {
			if (book.getIsbn().equals(isbn)) {
				int currentQty = book.getQuantity();
				book.setQuantity(currentQty + qty);
				return;
			}
		}
		Book newbook = new Book(isbn, title, author, year, category, shelf, qty);
		bookDirectory.add(newbook);
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
	public void loanBook(User user, Book book) throws Exception {
		if (book.getAvailableQuantity() > 0) {
			user.borrowBook(book);
		}else {
			throw new Exception("Error: The book is not avalaible.");
		}
	}
	
	/** Return book **/
	public void returnBook(User user, Book book) {
		user.removeBorrowedBook(book);
	}
	
	public ArrayList<Book> getBookList(){
		return this.bookDirectory;
	}
	
	public ArrayList<User> getUserList(){
		return this.userDirectory;
	}
	
	/** Save library **/
	public void save() {
		DatabaseHelper.saveLibrary(this);
	}
	/** Load library **/
	public void load() {
		DatabaseHelper db = DatabaseHelper.loadLibrary();
		userDirectory = db.getUserList();
		bookDirectory = db.getBookList();
	}
}
