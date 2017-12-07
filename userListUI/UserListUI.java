package frontend.userListUI;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import frontend.*;
import frontend.delayedBooksUI.*;
import frontend.bookViewUI.BookViewUI;
import frontend.booksUI.*;
import frontend.emptyTemplateUI.EmptyTemplateUI;
import frontend.homeUI.HomeUI;
import frontend.registerUserUI.RegisterUserUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import frontend.newBookUI.*;
import program.Book;


/**
 * Empty template on how to initialize a 
 *
 */

public class UserListUI implements Initializable{
	private static VBox root;
	private static Scene emptyTemplate;
	
	@FXML private Label topMenu1; // Link to the fx:id in scenebuilder
	@FXML private Button showNewBook;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO: Code goes here :)
		// Example
		topMenu1.setText("Header Here");
		
		
		
	}
	
	public static void display() {
		Class context = UserListUI.class;
		try {
			// This is the scene that is going to be shown inside the window ( Main window in this case )
			VBox homeView = (VBox)FXMLLoader.load(context.getResource("UserListUI.fxml")); 
			emptyTemplate = new Scene(homeView,1192,650);
			emptyTemplate.getStylesheets().add(MainWindow.css);
			

			// Set the main window to show this scene
			MainWindow.window.setScene(emptyTemplate);
			MainWindow.window.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void NewBookButtonClicked(){
		NewBookUI.display();
	}


	

	
	
	/******** File MENU ********/
	public void newBook(){
		NewBookUI.display();
	}
	public void quitMenuClick() {
		MainWindow.closeProgram();
	}
	
	/******** Main menu ********/
	public void homeMenuAction(){
		HomeUI.display();
	}
	public void booksMenuAction(){
		BooksUI.display();
	}
	public void usersMenuAction() {
		UserListUI.display();
	}
	public void openDelayedBooks() {
		DelayedBook.display();
	}	
	public void openRegister() {
		RegisterUserUI.display();
	}

	/******** User List Buttons ******/
	public void seachForUserAction(){
		UserSeacrchUI.display();
	}
	
	

}
