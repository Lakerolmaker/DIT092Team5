import com.google.gson.Gson;

public class main {
	
	static FileClass file = new FileClass();
	static Gson gson = new Gson();

	public static Library library = new Library();
	
	public static void main(String[] args) { 
		
		library.load();
		//library.userDiretory.add(new User("dd", 0, 0, 0, 0, 0));
		//library.bookDiretory.add(new Book(0, "hello", "me", 0, "hentai", 111011));

		
		//library.save();
		
		print("done");
		
	}
	

	public static void print(Object o) {
	    System.out.println(o); 
	}
	
}
