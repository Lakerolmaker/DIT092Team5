package frontend;

import DelayedBooks.*;
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
	public static User user;
	
	public static void main(String[] args) {
		lib.load();
		launch(args);
		
	}
	
	@Override
	public void start(Stage primaryStage) {
		// Set up main window
		window = primaryStage;
		try {
			window.setMinWidth(1192);
			window.setMinHeight(650);
			window.setMaxWidth(1192);
			window.setMaxHeight(650);
			window.setOnCloseRequest(e -> {
				e.consume(); // Take care of the close event so it wont close anyways
				closeProgram();
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// Show first page
		// TODO: Change to home view when it's created
		EmptyTemplateUI.display(this.getClass());
		//DelayedBook.display(this.getClass());

	}
	
	public Library getLibrary(){
		return lib;
	}
	
	/** Handle's the close program request **/
	private void closeProgram() {
		lib.save(); // Saves library session
		System.out.println("Session saved");
		window.close(); // Exiting program
	}
}
