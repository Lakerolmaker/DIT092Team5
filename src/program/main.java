package program;
import com.google.gson.Gson;

import UILibrary.UI;
import database.FileClass;

public class main {

	public static Library lib = new Library();
	
	public static void main(String[] args) { 
		
		lib.load();
		
		for (Book book : lib.getBookList()) {
			System.out.println(book.toString());
		}
		
	}
	

	public static void print(Object o) {
	    System.out.println(o); 
	}
}
