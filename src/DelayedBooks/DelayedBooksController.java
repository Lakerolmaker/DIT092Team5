package DelayedBooks;

import java.net.URL;
import java.util.ResourceBundle;

import frontend.BooksUI;
import frontend.EmptyTemplateUI;
import frontend.MainWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import newbook.gui.javafx.NewBookUI;
import program.Book;

public class DelayedBooksController {

	@FXML public TableView<DelayedPerson> delayedBook;

	@FXML public TableColumn<DelayedPerson, String> titleColumn;
	@FXML public TableColumn<DelayedPerson, String> NameColumn;
	@FXML public TableColumn<DelayedPerson, Double > debtColumn;
	@FXML public TableColumn<DelayedPerson, Integer>  userIdColumn; 
	

	public void initialize() {
		
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		NameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
		debtColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
		userIdColumn.setCellValueFactory(new PropertyValueFactory<>("debt"));

		delayedBook.setItems(getBooks());
		
	}
	
	// Return list of books
	public ObservableList<DelayedPerson> getBooks() {
		ObservableList<DelayedPerson> persons = FXCollections.observableArrayList();
		for (Book book : MainWindow.lib.getBookList()) {
				
		// TODO check delayed
		DelayedPerson newdelay = new DelayedPerson(book.getTitle(), "Jacob Olsson", 1, 0.0);
		persons.add(newdelay);
			
		}
			return persons;
	}
	
		
		/******** File MENU ********/
		public void newBook(){
			NewBookUI.display();	
		}
		/******** Main menu ********/
		public void homeMenuAction(){
			EmptyTemplateUI.display();
		}
		public void booksMenuAction(){
			BooksUI.display();
		}
		public void usersMenuAction() {
			// User view call
			System.out.println("Example: User button clicked");
		}	
}
