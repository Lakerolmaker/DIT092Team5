package program;
import java.util.ArrayList;

import com.google.gson.Gson;


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
			if (book.getIsbn() == isbn) {
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


	public void save() {
		
		file.createFolder("Database", file.CurrentDir);
		file.createFolder("USERS", file.CurrentDir + "/Database");
		file.createFolder("BOOKS", file.CurrentDir + "/Database");
		
		for(int i = 0 ; i < userDirectory.size(); i++ ) {
			
			String json = gson.toJson(userDirectory.get(i));
			String fileName = "user" + i;
			String directory = file.CurrentDir + "/Database/USERS";
			
			file.createTextFile(fileName , directory);
			
			file.writeToTextFile(fileName  ,json , directory);
			
		}
		
		for(int i = 0 ; i < bookDirectory.size(); i++ ) {
			
			String json = gson.toJson(bookDirectory.get(i));
			String fileName = "book" + i;
			String directory = file.CurrentDir + "/Database/BOOKS";
			
			file.createTextFile(fileName , directory);
			
			file.writeToTextFile(fileName  ,json , directory);
			main.print(i);
		}
	
	}

	public void load() {
		
		boolean scanUser = true;
		int indexUSer = 0;
		
		while(scanUser) {

			String fileName = "user" + indexUSer ;
			String directory = file.CurrentDir + "/Database/USERS";
			
			String json = file.readFromTextFile( fileName , directory );
			
			if(json != null) {
				User user = gson.fromJson(json , User.class);
				userDirectory.add(user);	
				indexUSer++;
			}else {
				scanUser = false;
			}
			
		}
		
		boolean scanBook = true;
		int indexBook = 0;
		
		while(scanBook) {

			String fileName = "book" + indexBook ;
			String directory = file.CurrentDir + "/Database/BOOKS";
			
			String json = file.readFromTextFile( fileName , directory );
			
			if(json != null) {
				Book book = gson.fromJson(json , Book.class);
				bookDirectory.add(book);	
				indexBook++;
			}else {
				scanBook = false;
			}
			
		}
	
		main.print("Books : " + indexBook);
		main.print("Users : " + indexUSer);
		
	}
	
}
