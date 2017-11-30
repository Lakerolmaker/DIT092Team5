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
import program.Book;


/**
 * Empty template on how to initialize a 
 *
 */

public class BookViewUI implements Initializable{
	private static Book selectedBook;
	private String title;
	private String author;
	private int year;
	private int availableQty;
	private static VBox root;
	private static Scene bookView;
	@FXML public Label bookTitle;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		title = selectedBook.getTitle();
		author = selectedBook.getAuthor();
		year = selectedBook.getYear();
		availableQty = selectedBook.getAvailableQuantity();
		
		
		try {
			bookTitle.setText(title);
		}catch (Exception e ) {
			System.out.println(e.getMessage());
		}

	}
	
	

	
	public static void display(Class context, Book book)  {
		selectedBook = book;
		try {
			// This is the scene that is going to be shown inside the window ( Main window in this case )
			VBox bookViewContainer = (VBox)FXMLLoader.load(context.getResource("BookView.fxml")); 
			bookView = new Scene(bookViewContainer,1192,650);
			bookView.getStylesheets().add(context.getResource("application.css").toExternalForm());

			// Set the main window to show this scene
			MainWindow.window.setScene(bookView);
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
	public void openDelayedBooks(ActionEvent event) {
		DelayedBook.display();
	}


	

}
