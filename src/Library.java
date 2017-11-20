import java.util.ArrayList;

import com.google.gson.Gson;

public class Library {
	
	FileClass file = new FileClass();
	Gson gson = new Gson();
	
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
