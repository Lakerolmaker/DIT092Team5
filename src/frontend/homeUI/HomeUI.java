package frontend.homeUI;


import frontend.booksUI.*;
import frontend.*;
import frontend.bookViewUI.BookViewUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import frontend.registerUserUI.*;
import frontend.userListUI.UserListUI;
import frontend.delayedBooksUI.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import newbook.gui.javafx.NewBookUI;
import program.Book;


public class HomeUI implements Initializable{
	private static VBox root;
	private static Scene homeScene;
	
	@FXML private Label topMenu1; // Link to the fx:id in scenebuilder
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO: Code goes here :)
		
		
		
	}
	
	public static void display() {
		Class context = HomeUI.class;
		try {
			// This is the scene that is going to be shown inside the window ( Main window in this case )
			VBox homeView = (VBox)FXMLLoader.load(context.getResource("HomeUI.fxml")); 
			homeScene = new Scene(homeView,1192,650);
			homeScene.getStylesheets().add(MainWindow.css);

			// Set the main window to show this scene
			MainWindow.window.setScene(homeScene);
			MainWindow.window.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	public void goToBookView(Book book){
		BookViewUI.display(book);
	}
	public void openDelayedBooks() {
		DelayedBook.display();
	}	
	public void openRegister() {
		RegisterUserUI.display();
	}

}
