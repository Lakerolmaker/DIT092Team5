package database;

import java.util.ArrayList;

import com.google.gson.Gson;

import program.Book;
import program.Library;
import program.User;
import program.main;

public class DatabaseHelper {
	
	private static FileClass file = new FileClass();
	private static Gson gson = new Gson();
	private ArrayList<User> userDirectory;
	private ArrayList<Book> bookDirectory;
	
	public DatabaseHelper() {
		userDirectory = new ArrayList<User>();
		bookDirectory = new ArrayList<Book>();
		loadLibrary();
	}
	
	public void loadLibrary() {
	
		boolean scanUser = true;
		int indexUSer = 0;
		
		while(scanUser) {
			String fileName = "user" + indexUSer ;
			String directory = file.CurrentDir + "/Database/USERS";
			String json = file.readFromTextFile( fileName , directory );
			
			if(json != null) {
				User user = gson.fromJson(json , User.class);
				this.userDirectory.add(user);	
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
				this.bookDirectory.add(book);	
				indexBook++;
			}else {
				scanBook = false;
			}
		}
		main.print("Books : " + indexBook);
		main.print("Users : " + indexUSer);
		
	}

	
	public static void saveLibrary(Library lib) {
		
		file.createFolder("Database", file.CurrentDir);
		file.createFolder("USERS", file.CurrentDir + "/Database");
		file.createFolder("BOOKS", file.CurrentDir + "/Database");
		
		for(int i = 0 ; i < lib.getUserList().size(); i++ ) {
			
			String json = gson.toJson(lib.getUserList().get(i));
			String fileName = "user" + i;
			String directory = file.CurrentDir + "/Database/USERS";
			
			file.createTextFile(fileName , directory);
			file.writeToTextFile(fileName  ,json , directory);
		}
		
		for(int i = 0 ; i < lib.getBookList().size(); i++ ) {
			
			String json = gson.toJson(lib.getBookList().get(i));
			String fileName = "book" + i;
			String directory = file.CurrentDir + "/Database/BOOKS";
			
			file.createTextFile(fileName , directory);
			file.writeToTextFile(fileName  ,json , directory);
			main.print(i);
		}
	}
	
	public ArrayList<User> getUserList(){
		return this.userDirectory;
	}
	
	public ArrayList<Book> getBookList(){
		return this.bookDirectory;
	}

}
