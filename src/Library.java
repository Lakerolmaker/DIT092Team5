import java.util.ArrayList;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * Functions does not work as the other classes needs to change some in their structure.
 *
 */

public class Library {
	
	FileClass file = new FileClass();
	Gson gson = new Gson();
<<<<<<< HEAD


	public ArrayList<User> userDiretory = new ArrayList<User>();
	public ArrayList<Book> bookDiretory = new ArrayList<Book>();
	
	public void addBook() {
		
	}
	
	public void addUser() {
		
	}
	
	public void loanBook(int userID , int bookID) {
		
	}
=======
	
	private Map<Book, Integer> books; // Map of all books in Library and their quantity.
	private Map<Integer, User> users; // Map of all users in library and their ID mapped as key.

	public Library() {
		this.books = new HashMap<Book, Integer>();
		this.users = new HashMap<Integer, User>();

	}
	

	/** Register user
	 * Checks if a user with the same SSN already exists.
	 * If the SSN is not already registered a new user will be registered. 
	 * @throws Exception 
	 */
	public void registerUser(String firstName, String lastName, String ssn, int phoneNr, String street, int zipCode, String city) throws Exception {
		// Search for duplicate using SSN
		User tmp = findUser(ssn); 
		if (tmp != null) {
			throw new Exception("Error: Customer with same SSN already exists in db with ID: " + tmp.getUserId());
		
		// If no duplicate found - a new User are registered
		}else {
			tmp = new User(firstName, lastName, ssn, phoneNr, street, zipCode, city);
			users.put(tmp.getUserId(), tmp);
		}
	}
	
	
	/** Retrieve user from their id
	 * This function should be called to access user functions. Ex: user.getAdress(), user.getFirstName();
	 *  @return User (returns null if no user is found)
	 */
	public User retrieveUser(int id) {
		return users.get(id);
	}
	
	
	/** Find User by SSN
	 * @param ssn
	 * @return User (or null if not found)
	 */
	public User findUser(String ssn) {
		for (User val : this.users.values() ) {
			if (val.getSsn().equals(ssn)) {
				return val;
			}
		}
		return null;
	}
	
	
	/** Create a new Book
	 * Checks if the book already exists to prevent duplicates are created. 
	 * @param isbn, name, author, year, category
	 * @return Book
	 */
	public Book createBook(String isbn, String name, String author, int year, String category) {
		Book tmp = null;
		
		// Search for duplicate using ISBN
		for (Book key : this.books.keySet()) {
			if (key.getIsbn().equals(isbn)) {
				tmp = key;
			}
		}
		// If no duplicate is found a new Book is created.
		if (tmp == null) {
			tmp = new Book(isbn, name, author, year, category);
		}
		
		// Return the book created. (Or if the book already existed, a reference to that book)
		return tmp;
	}
	
	
	/** Add book to library
	 * @param book
	 */
	public void addBook(Book book) {
		// If the library already contains the book, the quantity is increased by 1.
		if (books.containsKey(book)) {
			books.put(book, books.get(book) + 1);
		
		}else {
			books.put(book, 1); // New book is added to the library.
		}
	}
	
	public void loanBook(int userID , int bookID) {
		// TODO
	}
	
	public void returnBook(int userID , int bookID) {
		// TODO
	}
	
	
	
	/**
	 *  TODO : No need for duplicate sorting functions. Descending order can be achieved
	 *  by just adding one line: 
	 *  Collections.reverse();
	 */
	
	public void sortBooksAuthorAZ() {
		// TODO: To sort our Map instead of an ArrayList this one line has to be added:
		List<Book> books = new ArrayList<Book>(this.books.keySet());
		
		
		Collections.sort(books, Book.authorComparatorAZ); 

		for (Book str : books) {
			System.out.println(str); // TODO : Avoid printing inside classes - this is handled by our main
		}

	}
	
	
	/** TODO
	 * Bunch of code and functions below this line that might be unused or not necessary?
	 * A cleanup might be needed.
	 */
	
	

	public void sortBooksAuthorZA() {
		
		Collections.sort(books, Book.authorComparatorZA);

		for (Book str : books) {
			System.out.println(str); 
		}


	}

	public void sortBooksNameAZ() {

		Collections.sort(books, Book.nameComparatorAZ);

		for (Book str : books) {
			System.out.println(str);
		}

	}

	public void sortBooksNameZA() {

		Collections.sort(books, Book.nameComparatorZA);

		for (Book str : books) {
			System.out.println(str);
		}

	}
	

>>>>>>> ade14c784d950fde2dc324b0e2093a90ee9d3b33
	

	
	//: sort functions
	
	SortInterface sort = new SortInterface() {

		@Override
		public void alphabetically() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void byAthor() {
			// TODO Auto-generated method stub
			
		}
<<<<<<< HEAD

=======
>>>>>>> ade14c784d950fde2dc324b0e2093a90ee9d3b33

		@Override
		public void byShelfNumber() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void byGenre() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void byPublisher() {
			// TODO Auto-generated method stub
			
		}
		
	
		
		
		
	};


	public void save() {
		
		file.createFolder("Database", file.CurrentDir);
		file.createFolder("USERS", file.CurrentDir + "/Database");
		file.createFolder("BOOKS", file.CurrentDir + "/Database");
		
		for(int i = 0 ; i < userDiretory.size(); i++ ) {
			
			String json = gson.toJson(userDiretory.get(i));
			String fileName = "user" + i;
			String directory = file.CurrentDir + "/Database/USERS";
			
			file.createTextFile(fileName , directory);
			
			file.writeToTextFile(fileName  ,json , directory);
			
		}
		
		for(int i = 0 ; i < bookDiretory.size(); i++ ) {
			
			String json = gson.toJson(bookDiretory.get(i));
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
				userDiretory.add(user);	
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
				bookDiretory.add(book);	
				indexBook++;
			}else {
				scanBook = false;
			}
			
		}
	
		main.print("Books : " + indexBook);
		main.print("Users : " + indexUSer);
		
	}


	
	
}
