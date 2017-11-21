import com.google.gson.Gson;

public class main {
	
	FileClass file = new FileClass();
	Gson gson = new Gson();

	public static Library library = new Library();
	
	public static void main(String[] args) {
		//library.userDiretory.add(new User("hello", 0, 0.0, 1, 99,"ee"));
		library.save();
		
	}

	public static void load() {
		
		String json = library.file.readFromTextFile( "Data base" , library.file.CurrentDir);
		//System.out.println(json);
		Library test = library.gson.fromJson(json , Library.class);
		
	}

	
	
}
