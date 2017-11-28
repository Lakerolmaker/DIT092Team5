package database;

import java.io.File;
import java.io.IOException;
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
	private int LOAN_ALLOWANCE = 14;
	
	public DatabaseHelper() {
		userDirectory = new ArrayList<User>();
		bookDirectory = new ArrayList<Book>();
	}
	
	public void loadLibrary(String LibraryID) {
	
		String libDir = file.CurrentDir + "/" + LibraryID;
		
		boolean scanUser = true;
		int indexUSer = 0;
		
		while(scanUser) {
			String fileName = "user" + indexUSer ;
			String directory = libDir + "/USERS";
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
			String directory = libDir + "/BOOKS";
			String json = file.readFromTextFile( fileName , directory );
			
			if(json != null) {
				Book book = gson.fromJson(json , Book.class);
				this.bookDirectory.add(book);	
				indexBook++;
			}else {
				scanBook = false;
			}
		}
		
		String fileName = "LOAN_ALLOWANCE";
		String directory = libDir;
		String loanValue = file.readFromTextFile( fileName , directory );
		LOAN_ALLOWANCE = Integer.parseInt(loanValue);
		
		main.print("Books : " + indexBook);
		main.print("Users : " + indexUSer);
		
	}

	public void saveLibrary(Library lib) {
		
		
		String libDir = file.CurrentDir + "/" + lib.getName();
		
		file.createFolder(lib.getName(), file.CurrentDir);
		file.createFolder("USERS", libDir);
		file.createFolder("BOOKS", libDir);
		
		for(int i = 0 ; i < lib.getUserList().size(); i++ ) {
			
			String json = gson.toJson(lib.getUserList().get(i));
			String fileName = "user" + i;
			String directory = libDir + "/USERS";
			
			file.createTextFile(fileName , directory);
			file.writeToTextFile(fileName  ,json , directory);
		}
		
		for(int i = 0 ; i < lib.getBookList().size(); i++ ) {
			
			String json = gson.toJson(lib.getBookList().get(i));
			String fileName = "book" + i;
			String directory = libDir +  "/BOOKS";
			
			file.createTextFile(fileName , directory);
			file.writeToTextFile(fileName  ,json , directory);
			
		}
		
		String value = String.valueOf(lib.getLoanAllowance());
		String fileName = "LOAN_ALLOWANCE";
		String directory = libDir;
		file.createTextFile(fileName , directory);
		file.writeToTextFile(fileName  ,value , directory);
	}
	
	public boolean deleteDatabase(String LibraryID) {
		String path = file.CurrentDir + "/" + LibraryID;
		
		File dir = new File(path);
	
		return file.deleteDirectory(dir);
	}
	
	public ArrayList<User> getUserList(){
		return this.userDirectory;
	}
	
	public ArrayList<Book> getBookList(){
		return this.bookDirectory;
	}
	
	public int getLOAN_ALLOWANCE () {
		return LOAN_ALLOWANCE;
	}

}
