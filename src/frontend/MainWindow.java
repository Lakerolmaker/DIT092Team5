package frontend;

import java.net.URL;
import java.time.LocalDate;

import frontend.userListUI.*;
import frontend.userprofileUI.UserProfileUI;
import frontend.emptyTemplateUI.*;
import frontend.homeUI.HomeUI;
import frontend.registerUserUI.RegisterUserUI;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import program.*;
import javafx.scene.Scene;
import javafx.scene.control.Label;


public class MainWindow extends Application {
	
	public static Library lib = new Library("database1");
	public static Stage window;
	public static Scene scene, scene2, scene3;
	public static String css ; 
	public static User user;
	
	public static void main(String[] args) throws Exception {
		lib.load();	
		
		try {
			lib.addUser("Tihana", "Causevic", "19940306XXX", "0733224433", "almstätts gatan ", "233 33", "göteborg");
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
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
			window.setOnCloseRequest(e -> {
				e.consume(); // Take care of the close event so it wont close anyways
				closeProgram();
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// Show first page
		HomeUI.display();
		/*
		User u = lib.getUser(1);  //testing
		try {
			Book b = new Book("00000000", "title2", "author", 7, "category", 5, 8);
			u.borrowBook(b);
			//u.setLendDate(b.getId(), LocalDate.of(2018, 1, 1));
			//u.borrowBook(new Book ("John", "Doe", "",1, "", 2, 7));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	    UserProfileUI.SetUser(u);
		UserProfileUI.display(); 
		
		//RegisterUserUI.display();
		*/
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
