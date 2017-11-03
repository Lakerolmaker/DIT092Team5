import com.google.gson.Gson;

public class main {

	FileClass file = new FileClass();
	Gson gson = new Gson();
	
	Library library = new Library();
	
	public static void main(String[] args) {

	}

	public void saveLibrary() {
	
		String json = gson.toJson(library);
		
		file.createTextFile("Data base", file.CurrentDir);
		
		file.writeToExistingFile("Data base" ,json , file.CurrentDir);
		
	}
	
	public void loadLibrary() {
		
		String json = file.readFromTextFile( "Data base" , file.CurrentDir);
		
		library = gson.fromJson(json , Library.class);
		
	}
	
	
}
