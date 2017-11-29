package DelayedBooks;

import java.net.URL;
import java.util.ResourceBundle;

import frontend.MainWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import program.Book;

public class DelayedBooksController {

	@FXML public  TableView<DelayedPerson> delayedBook;
	
	@FXML public TableColumn<DelayedPerson, String> titleColumn;
	@FXML public TableColumn<DelayedPerson, String> NameColumn;
	@FXML public TableColumn<DelayedPerson, Integer> userIdColumn;
	@FXML public TableColumn<DelayedPerson, Double> debtColumn;
	
	public void keypress(ActionEvent e) {
		System.out.println("hello");
	}
	
	
	public void initialize() {
		 System.out.println("hello");
		 
		// Title column
		titleColumn = new TableColumn<>("Title");
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		
		// Title column
		NameColumn = new TableColumn<>("Name of Loaner");
		NameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
	
		// Title column
		debtColumn = new TableColumn<>("Debt");
		debtColumn.setCellValueFactory(new PropertyValueFactory<>("debt"));
		
		// Title column
		userIdColumn = new TableColumn<>("User ID");
		userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
		
		
	
		delayedBook.setItems(getBooks());
		delayedBook.getColumns().addAll(titleColumn , NameColumn , userIdColumn , debtColumn);
		
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
	
	
}
