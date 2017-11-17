import com.google.gson.Gson;

public class Library {
	
	FileClass file = new FileClass();
	Gson gson = new Gson();
	

	public void save() {
		
		String json = gson.toJson(main.library);
		
		file.createTextFile("Data base", file.CurrentDir);
		
		file.writeToExistingFile("Data base" ,json , file.CurrentDir);
		
	}
	
	public void load() {
		
		String json = file.readFromTextFile( "Data base" , file.CurrentDir);
		
		main.library = gson.fromJson(json , Library.class);
		
	}
	
	
}
