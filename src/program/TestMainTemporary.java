package program;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestMainTemporary {
	public static Library lib = new Library("database1");

	public static void main(String[] args) {
		lib.load();
		
		/*
		try {
			lib.addBook("9780563528821", "Lord of the Rings", "Hobbit Writer", 1990, "Sci Fiction", 1, 2);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		lib.save();
		*/
		/*
		
		try {
			lib.addUser("James", "Bond", "201712059999", "123456789","5th Aveneue" , "99930", "Los Angeles");
		}catch (Exception e) {
			e.getMessage();
		}
		lib.save();
		
		 */

		Book tmpBook = lib.findBookByIsbn("9789544464912");
		System.out.println(tmpBook.getTitle() + ": Available quantity " + tmpBook.getAvailableQuantity());

		User user1 = lib.findUser("201712059999");
		try {
			System.out.println(user1.getName() + "  currently loaning " + user1.getBookList().size() + " books.");
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		//lib.save();
		/*
		try {
			lib.loanBook(user1, tmpBook);
			System.out.println(user1.getName() + " loaned " + tmpBook.getTitle());
		} catch (Exception e) {
		}
		
		System.out.println(user1.getName() + "  currently loaning " + user1.getBookList().size() + " books.");
		System.out.println("Days until return: " + user1.getDaysLeft(tmpBook) + ", Return date: " + user1.getBorrowedBookReturnDate(tmpBook.getId()));
		
		User user2 = lib.findUser("9301019998");
		
		try {
			lib.loanBook(user2, tmpBook);
			System.out.println(user2.getName() + " loaned " + tmpBook.getTitle());
		} catch (Exception e) {
			System.out.println(user2.getName() + " couldnt loan " + tmpBook.getTitle() + " since there's no copies left.");
		}
		
		/*
		// James Bond book list
		System.out.println("James Bond's book list:");
		HashMap<Book, LocalDate> bookMap = user1.getBookMap();
		for (Map.Entry<Book, LocalDate> entry : bookMap.entrySet()) {
		    Book book = entry.getKey();
		    LocalDate date = entry.getValue();
		    System.out.println(book.getTitle() +": "  + user1.getDaysLeft(book) + " days left. (Return date: " + date + ")");
		}
		*/
	}

}
