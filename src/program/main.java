package program;

public class main {

	public static Library lib = new Library("database1");
	
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
