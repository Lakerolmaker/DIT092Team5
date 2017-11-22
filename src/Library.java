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
	
	}

	public void load() {
		
		boolean scan = true;
		int index = 0;
		
		while(scan) {

			String fileName = "user" + index ;
			String directory = file.CurrentDir + "/Database/USERS";
			
			String json = file.readFromTextFile( fileName , directory );
			
			if(json != null) {
				User test = gson.fromJson(json , User.class);
				userDiretory.add(test);	
				main.print("scan : " + index);
				index++;
			}else {
				scan = false;
			}
			
		}
	
	}


	
	
}
