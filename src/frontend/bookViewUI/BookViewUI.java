package frontend.bookViewUI;

import frontend.*;
import frontend.booksUI.*;
import frontend.emptyTemplateUI.*;
import frontend.homeUI.HomeUI;
import frontend.registerUserUI.RegisterUserUI;
import frontend.userListUI.UserListUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import DelayedBooks.DelayedBook;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import newbook.gui.javafx.NewBookUI;
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
	
	public static void display(Book book)  {
		Class context = BookViewUI.class;
		selectedBook = book;
		try {
			// This is the scene that is going to be shown inside the window ( Main window in this case )
			VBox bookViewContainer = (VBox)FXMLLoader.load(context.getResource("BookView.fxml")); 
			bookView = new Scene(bookViewContainer,1192,650);
			bookView.getStylesheets().add(MainWindow.css);

			// Set the main window to show this scene
			MainWindow.window.setScene(bookView);
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
