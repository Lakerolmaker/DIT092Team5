package frontend.delayedBooksUI;

import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import frontend.emptyTemplateUI.*;
import frontend.homeUI.HomeUI;
import frontend.userListUI.UserListUI;
import frontend.MainWindow;
import frontend.booksUI.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Light.Point;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import frontend.newBookUI.*;
import frontend.registerUserUI.RegisterUserUI;
import program.Book;
import program.User;
import sun.util.resources.LocaleData;

public class DelayedBooksController {

	@FXML public TableView<DelayedPerson> delayedBook;

	@FXML public TableColumn<DelayedPerson, String> titleColumn;
	@FXML public TableColumn<DelayedPerson, String> NameColumn;
	@FXML public TableColumn<DelayedPerson, Double > debtColumn;
	@FXML public TableColumn<DelayedPerson, Integer>  userIdColumn; 
	@FXML public TableColumn<DelayedPerson, String>  dateLoanedColumn; 
	
	private ContextMenu cm = new ContextMenu();
	DelayedPerson selectedBook;
	 
	public void initialize() {
		
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		NameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
		debtColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
		userIdColumn.setCellValueFactory(new PropertyValueFactory<>("debt"));
		dateLoanedColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

		

			MenuItem mi1 = new MenuItem("Go to User");
	        	MenuItem mi2 = new MenuItem("Go to Book");
	        cm.getItems().add(mi1);
	        cm.getItems().add(mi2);
	      
	        mi1.setOnAction(e -> System.out.println("loan"));
	        mi2.setOnAction(e -> System.out.println("Delete"));
	        cm.setAutoHide(true);
	        cm.setHideOnEscape(true);
	        
	    	delayedBook.setItems(getBooks());
	    	
		System.out.println("hello");
	}
	/**
	 * User class has been rewritten. It don't uses the book's id any more instead it uses the index the book has in the users bookList.
	 * Commented out to keep the program from errors when calling the view
	 */
	// Return list of books TODO:
	public ObservableList<DelayedPerson> getBooks() {
		ObservableList<DelayedPerson> persons = FXCollections.observableArrayList();
		/*
		for (User user : MainWindow.lib.getUserList()) {
				
			ArrayList<Book> delayedbooks =  user.getDelayedBooks();
			for (Book book : delayedbooks ) {
				
				LocalDate date = user.getBorrowedBookReturnDate(book.getId());
				System.out.println(date);
				
				DelayedPerson newdelay = new DelayedPerson(
						book.getTitle(),
						user.getFirstName() + user.getLastName() ,
						user.getUserId(), 
						user.getDebt(),
						date.toString());
				
				persons.add(newdelay);
			}
		}
		*/
			return persons;
			
	}
	
	
	public void gridLeftCLick() {
		 if (delayedBook.getSelectionModel().getSelectedItem() != null) { // Check if selected cell contains a book
			 	
			 	selectedBook = delayedBook.getSelectionModel().getSelectedItem();
	
		        PointerInfo a = MouseInfo.getPointerInfo();
		        java.awt.Point b = a.getLocation();
		        int x = (int) b.getX();
		        int y = (int) b.getY();
		     
		        cm.show(delayedBook , x , y); // Context menu is shown
		        
	        }
		
	}
	
	public void gridClick() {
		if (cm.isShowing()) {
    			cm.hide(); 
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
		public void openRegister() {
			RegisterUserUI.display();
		}
}
