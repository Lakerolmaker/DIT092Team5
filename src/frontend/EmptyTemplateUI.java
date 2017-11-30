package frontend;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import DelayedBooks.DelayedBook;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


/**
 * Empty template on how to initialize a 
 *
 */

public class EmptyTemplateUI implements Initializable{
	private static VBox root;
	private static Scene emptyTemplate;
	
	@FXML private Label topMenu1; // Link to the fx:id in scenebuilder
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO: Code goes here :)
		// Example
		topMenu1.setText("Header Here");
		
		
		
	}
	
	public static void display(Class context) {
		try {
			// This is the scene that is going to be shown inside the window ( Main window in this case )
			VBox homeView = (VBox)FXMLLoader.load(context.getResource("EmptyTemplate.fxml")); 
			emptyTemplate = new Scene(homeView,1192,650);
			emptyTemplate.getStylesheets().add(context.getResource("application.css").toExternalForm());
			

			// Set the main window to show this scene
			MainWindow.window.setScene(emptyTemplate);
			MainWindow.window.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	

	
	/******** File MENU ********/
	public void newBook(){
		// Call to display add new book view
		System.out.println("New book called");
	}
	public void quitMenuClick() {
		MainWindow.closeProgram();
	}
	
	/******** Main menu ********/
	public void homeMenuAction(){
		EmptyTemplateUI.display(this.getClass());
	}
	public void booksMenuAction(){
		BooksUI.display(this.getClass());
	}
	public void usersMenuAction() {
		// User view call
		System.out.println("Example: User button clicked");
	}
	public void openDelayedBooks() {
		DelayedBook.display();
	}
	
	

}
