package frontend.registerUserUI;

import java.io.File;

/** 
 * Description: 
 * Register User GUI with text fields for the first name, last name, ssn, user ID, library card number,
 * phone number, zip code, street and city.
 * @author Tihana Causevic
 */
import frontend.*;
import frontend.bookViewUI.BookViewUI;
import frontend.emptyTemplateUI.*;
import frontend.homeUI.HomeUI;
import frontend.userListUI.UserListUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import frontend.delayedBooksUI.*;
import frontend.booksUI.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import frontend.newBookUI.*;
import program.*;

public class RegisterUserUI implements Initializable{
	private static Library lib = MainWindow.lib;
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
		Class context = RegisterUserUI.class;
		try {
			// This is the scene that is going to be shown inside the window ( Main window in this case )
			VBox userView = (VBox)FXMLLoader.load(context.getResource("RegisterUserUI.fxml")); 
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
		if(!fName.getText().equals("") || !lName.getText().equals("") || !SSN.getText().equals("")) {  // if the first name field and the last name field are not empty then create a new user 
			try {
				lib.addUser(fName.getText(), lName.getText(), SSN.getText(), phoneNr.getText(), street.getText(), zCode.getText(), city.getText());
				lib.save();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	
	

}
