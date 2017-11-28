package program;
import com.google.gson.Gson;

import UILibrary.UI;
import menu.menu;

public class main {
	
	static FileClass file = new FileClass();
	static Gson gson = new Gson();
	
	public static UI frame = new UI("myframe", "menu", 400, 400, false, true, true);

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
