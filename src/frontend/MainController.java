package frontend;

import java.net.URL;
import java.util.ResourceBundle;

import program.Functions.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import program.*;

public class MainController implements Initializable {
	@FXML public TableView<Book> tableBook;
	@FXML public TableColumn<Book, String> titleColumn;
	@FXML public TableColumn<Book, String> authorColumn;
	@FXML public TableColumn<Book, String> yearColumn;
	@FXML public TableColumn<Book, String> isbnColumn;

	@FXML public TextField searchField;
	@FXML public Label menuHome;
	@FXML public Label menuBooks;
	@FXML public Label menuUsers;

	public void searchFunc(ActionEvent event){
		String searchString = searchField.getText().toString();
		tableBook.setItems(getMatchingBooks(searchString));
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		menuHome.setId("menuHome");
		menuBooks.setId("menuBooks");
		menuUsers.setId("menuBooks");

		try {
			initTable();
		}catch (NullPointerException e) {}

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

	/******** BOOK VIEW FUNCTIONS ********/

	// Initialize table
	public void initTable(){
		// Title column
		titleColumn = new TableColumn<>("Title");
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		rightClickCell(titleColumn);
		// Author column
		authorColumn = new TableColumn<>("Author");
		authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
		rightClickCell(authorColumn);
		// Year column
		yearColumn = new TableColumn<>("Year");
		yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
		//rightClickCell(yearColumn);
		// ISBN column
		isbnColumn = new TableColumn<>("ISBN");
		isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
		rightClickCell(isbnColumn);

		
		tableBook.setItems(getBooks());
		tableBook.getColumns().addAll(titleColumn, authorColumn, yearColumn, isbnColumn);
	}

	/** Right Click on book table **/
	public void rightClickCell(TableColumn<Book, String> selectedColumn){
		selectedColumn.setCellFactory(new Callback<TableColumn<Book, String>, TableCell<Book, String>>() {
		    @Override
		    public TableCell<Book, String> call(TableColumn<Book, String> col) {
		        final TableCell<Book, String> cell = new TableCell<>();
		        cell.textProperty().bind(cell.itemProperty()); // in general might need to subclass TableCell and override updateItem(...) here
		        cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
		            @Override
		            public void handle(MouseEvent event) {
		                if (event.getButton() == MouseButton.SECONDARY) {
		                	handleRightClickTable(event);
		                }
		            }
		          });
		        return cell ;
		    }
		});
	}
	
	public void handleRightClickTable(MouseEvent event) {		                	
        ContextMenu cm = new ContextMenu();
        MenuItem mi1 = new MenuItem("Loan");
        cm.getItems().add(mi1);
        MenuItem mi2 = new MenuItem("Delete");
        cm.getItems().add(mi2);
        
        cm.show(tableBook , event.getScreenX() , event.getScreenY());
	}
	// Return list of books
	public ObservableList<Book> getBooks() {
		ObservableList<Book> books = FXCollections.observableArrayList();
		for (Book book : MainWindow.lib.getBookList()) {
			books.add(book);
		}
		return books;
	}
	// Return matching list of books
	public ObservableList<Book> getMatchingBooks(String search) {
		if (search.length() < 1) {
			return getBooks(); // If search is empty - returns a list of all books
		}
		ObservableList<Book> books = FXCollections.observableArrayList(); // Create new list
		for (Book book : MainWindow.lib.getBookList()) {
			if (Functions.compareStrings(book.getTitle(), search) || Functions.compareStrings(book.getAuthor(), search) || Functions.compareStrings(book.getIsbn(), search) || Functions.compareStrings(Integer.toString(book.getYear()), search)) {
				books.add(book); // If match add the book to list
			}
		}
		return books; // Return the new composed list
	}

	
	
}
