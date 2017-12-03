package frontend;

import java.io.File;

/** 
 * Description: 
 * Register User GUI with text fields for the first name, last name, ssn, user ID, library card number,
 * phone number, zip code, street and city.
 * @author Tihana Causevic
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import DelayedBooks.DelayedBook;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import newbook.gui.javafx.NewBookUI;
import program.User;


/**
 * Empty template on how to initialize a 
 *
 */

public class RegisterUserUI implements Initializable{
	private static VBox root;
	private static Scene userScene;
	private static User user;
	private static boolean isRegUser = true;
	
	@FXML private Label topMenu1; // Link to the fx:id in scenebuilder
	@FXML private TextField fName;
	@FXML private TextField lName;
	@FXML private TextField SSN;
	@FXML private TextField uID;
	@FXML private TextField lCN;
	@FXML private TextField phoneNr;
	@FXML private TextField zCode;
	@FXML private TextField street;
	@FXML private TextField city;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
	
	public static void display() {
		try {
			// This is the scene that is going to be shown inside the window ( Main window in this case )
			URL url = new File("src/frontend/RegisterUserUI.fxml").toURI().toURL();
			VBox userView = (VBox)FXMLLoader.load(url); 
			userScene = new Scene(userView,1192,650);
			userScene.getStylesheets().add(MainWindow.css);

			// Set the main window to show this scene
			MainWindow.window.setScene(userScene);
			MainWindow.window.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	


	@FXML
	public void submitButtonAction(Event event)
	{
		if(!fName.getText().equals("") || !lName.getText().equals("")) {  // if the first name field and the last name field are not empty then create a new user 
			user = new User(fName.getText(), lName.getText(), SSN.getText(), phoneNr.getText(), street.getText(), zCode.getText(), city.getText());
		}else {
			JOptionPane.showMessageDialog(null, "Please enter a first name and a last name to continue"); // if the first name and the last name field are empty then display an error box
		}
	}
	
	@FXML
	public void cancelButtonAction(Event event)
	{
		EmptyTemplateUI.display(); // displays the empty template (instead of the home screen, for now)
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
		RegisterUserUI.display();
	}
	public void openRegister() {
		RegisterUserUI.display();
	}
	public void booksMenuAction(){
		BooksUI.display();
	}
	public void usersMenuAction() {
		// User view call
		System.out.println("Example: User button clicked");
	}
	public void openDelayedBooks() {
		DelayedBook.display();
	}
	
	

}
