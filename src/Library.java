import java.util.ArrayList;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Collections;

public class Library {
	
	FileClass file = new FileClass();
	Gson gson = new Gson();
	
<<<<<<< HEAD
	ArrayList<Book> books;

	public Library() {

		books = new ArrayList<Book>();

	}
	
	public void sortBooksAuthorAZ() {

		Collections.sort(books, Book.authorComparatorAZ);

		for (Book str : books) {
			System.out.println(str);
		}

	}

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
	
=======
	public ArrayList<User> userDiretory = new ArrayList<User>();
	public ArrayList<Book> bookDiretory = new ArrayList<Book>();
	
	public int hello = 0;
	
	public void addBook() {
		
	}
	
	public void addUser() {
		
	}
	
	public void loanBook(int userID , int bookID) {
		
	}
	
	public void returnBook(int userID , int bookID) {
		
	}
	
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
>>>>>>> cb4c937b263f09a1d0cc0a28a1e737691774294a

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
		
		String json = gson.toJson(main.library);
		
		file.createTextFile("Data base", file.CurrentDir);
		
		file.writeToExistingFile("Data base" ,json , file.CurrentDir);
		
	}
	
	public void load() {
		
		String json = file.readFromTextFile( "Data base" , file.CurrentDir);
		
		main.library = gson.fromJson(json , Library.class);
		
	}
	
	
}
