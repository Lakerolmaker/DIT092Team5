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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import newbook.gui.javafx.NewBookUI;
import program.Book;

public class DelayedBooksController {


	@FXML public TableColumn<DelayedPerson, Double> debtColumn;
	
	public void keypress(ActionEvent e) {
		System.out.println("hello");
	}
	
	
	public void initialize() {
		/*
		// Title column
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		
		// Title column
		NameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
	
		// Title column
		debtColumn.setCellValueFactory(new PropertyValueFactory<>("debt"));
		
		// Title column
		userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));

		delayedBook.setItems(getBooks());
		boolean addAll = delayedBook.getColumns().addAll( NameColumn,titleColumn);
		*/
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
