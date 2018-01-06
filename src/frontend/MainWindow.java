package frontend;

import java.net.URL;

import frontend.booksUI.BooksUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import program.Library;
import program.User;


public class MainWindow extends Application {
	
	public static Library lib = new Library("database1");
	public static Stage window;
	public static Scene scene, scene2, scene3;
	public static String css ; 
	public static User user;
	
	public static void main(String[] args) throws Exception {
		lib.load();	
		
		launch(args);
	}
	 
	@Override
	public void start(Stage primaryStage){
		
		//: Adds the css file to a variabel.
		URL cssURL = this.getClass().getResource("application.css");
		css = "file:///" + cssURL.getPath().replace("\\", "/");
		
		// Set up main window
		window = primaryStage;
		try {
			window.setMinWidth(1192);
			window.setMinHeight(650);
			window.setMaxWidth(1192);
			window.setMaxHeight(650);
			window.setResizable(false);
			window.setTitle("That Library");
			window.setOnCloseRequest(e -> {
				e.consume(); // Take care of the close event so it wont close anyways
				closeProgram();
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// Show first page
		BooksUI.display();
		

	}
	
	public Library getLibrary(){
		return lib;
	}
	
	/** Handle's the close program request **/
	public static void closeProgram() {
		lib.save(); // Saves library session
		window.close(); // Exiting program
	}
}
