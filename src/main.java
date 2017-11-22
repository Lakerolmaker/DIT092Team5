import com.google.gson.Gson;

public class main {
	
	static FileClass file = new FileClass();
	static Gson gson = new Gson();

	public static Library library = new Library();
	
	public static void main(String[] args) { 
		library.load();
		library.userDiretory.add(new User("Jacob", "199804057033", 0733443240, "Ola dals vägen 24"));
		
	
		library.save();
		
		print("done");
		
	}
	

	public static void print(Object o) {
	    System.out.println(o); 
	}
	
}
