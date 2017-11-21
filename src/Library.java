import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Collections;

public class Library {
	
	FileClass file = new FileClass();
	Gson gson = new Gson();
	
	ArrayList<Book> books;

	public Library() {

		books = new ArrayList<Book>();

	}
	
	public void sortBooksAuthorAZ() {

		Collections.sort(books, Book.authorComparatorAZ);

		for (Book str : books) {
			System.out.println(str);
		}

	}

	public void sortBooksAuthorZA() {

		Collections.sort(books, Book.authorComparatorZA);

		for (Book str : books) {
			System.out.println(str);
		}

	}

	public void sortBooksNameAZ() {

		Collections.sort(books, Book.nameComparatorAZ);

		for (Book str : books) {
			System.out.println(str);
		}

	}

	public void sortBooksNameZA() {

		Collections.sort(books, Book.nameComparatorZA);

		for (Book str : books) {
			System.out.println(str);
		}

	}
	

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
