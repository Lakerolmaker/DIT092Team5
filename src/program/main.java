package program;
import com.google.gson.Gson;

import UILibrary.UI;
import menu.menu;

public class main {
	
	static FileClass file = new FileClass();
	static Gson gson = new Gson();
	
	public static UI frame = new UI("myframe", "menu", 400, 400, false, true, true);

	public static Library library = new Library();
	
	public static void main(String[] args) { 
		
		library.load();
		//library.userDiretory.add(new User("dd", 0, 0, 0, 0, 0));
		//library.bookDiretory.add(new Book(0, "hello", "me", 0, "hentai", 111011));

		
		//library.save();
		
		menu mymenu =  new menu();
		
		mymenu.start();
		
		print("done");
		
	}
	

	public static void print(Object o) {
	    System.out.println(o); 
	}
	
}
