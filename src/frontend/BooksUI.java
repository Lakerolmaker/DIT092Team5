package frontend;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import newbook.gui.javafx.NewBookUI;
//import newbook.gui.javafx.NewBookUI;
import DelayedBooks.DelayedBook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Effect;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import program.*;


public class BooksUI implements Initializable {
	
	@FXML public TableView<Book> tableBook;
	@FXML public TableColumn<Book, String> titleColumn;
	@FXML public TableColumn<Book, String> authorColumn;
	@FXML public TableColumn<Book, String> yearColumn;
	@FXML public TableColumn<Book, String> isbnColumn;
	@FXML public TextField searchField;
	@FXML public Label menuHome;
	@FXML public Label menuBooks;
	@FXML public Label menuUsers;
	@FXML public Label menuDelayed;
	@FXML public Text nameText;
	@FXML public Text streetText;
	@FXML public Text cityText;
	@FXML public Text balanceText;
	@FXML public Text amountText;
	@FXML public Text basketText;
	@FXML public Text booksLoaningText;
	@FXML public Text booksLoaningAmount;
	@FXML public ListView basketList;
	@FXML public Button loanBtn;
	@FXML public Text enterIdText;
	@FXML public TextField userIdField;
	@FXML public Button goBtn; 
	
	public static ArrayList<Book> booksInBasket;
	private ContextMenu cm;
	private static VBox root;
	private static Scene bookScene;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		menuHome.setId("menuHome");
		menuBooks.setId("menuBooks");
		menuUsers.setId("menuBooks");
		menuDelayed.setId("menuDelayed");
		
		
		
		showSidePanel();
		
		
		if(MainWindow.user == null) {
			newBasket();
		}
		
 		try {
			initTable();
		}catch (NullPointerException e) {}
	}
	
	
	public void newBasket(){
		booksInBasket = new ArrayList<>();
	}

	public void goBtnClicked(){
		String idStr = userIdField.getText();
		if(Functions.isInt(idStr)) {
			int id = Integer.parseInt(idStr);
			MainWindow.user = MainWindow.lib.getUser(id);
			showSidePanel();
		}
	}
	
	public static void display() {
		
		try {
			URL url = new File("src/frontend/Book.fxml").toURI().toURL();
			VBox bookView = (VBox)FXMLLoader.load(url);
			bookScene = new Scene(bookView,1192,650);
			bookScene.getStylesheets().add(MainWindow.css);
			
			MainWindow.window.setScene(bookScene);
			MainWindow.window.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/** Loan button **/
	public void loanBtnClicked(){
		for (Book book : booksInBasket) {
			try {
				MainWindow.lib.loanBook(MainWindow.user, book);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			showSidePanel(); // Update side panel
			basketList.getItems().clear(); // Clear basket
			newBasket();
		}
	}
	
	/** Search Button **/
	public void searchFunc(ActionEvent event){
		String searchString = searchField.getText().toString();
		tableBook.setItems(getMatchingBooks(searchString));
	}
	
	
	

	/******** BOOK VIEW FUNCTIONS ********/

	// Initialize table
	public void initTable(){
		// Title column
		titleColumn = new TableColumn<>("Title");
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		// Author column
		authorColumn = new TableColumn<>("Author");
		authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
		// Year column
		yearColumn = new TableColumn<>("Year");
		yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
		// ISBN column
		isbnColumn = new TableColumn<>("ISBN");
		isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));


		
		tableBook.setItems(getBooks());
		tableBook.getColumns().addAll(titleColumn, authorColumn, yearColumn, isbnColumn);
	}

	/** Book List Table click functions **/
	@FXML
	public void clickItem(MouseEvent event) {
		
    	if (cm != null) {
	    	if (cm.isShowing()) {
	    		cm.hide(); // Don't allow duplicate context menus open
	    	}
    	}
	    if (event.getClickCount() == 2) {
	    	Book selectedBook = tableBook.getSelectionModel().getSelectedItem(); // Retrieve selected cell
	    	if (selectedBook != null) {
	    		goToBookView(selectedBook);
	    	}
	    }
	    else if(event.getButton() == MouseButton.SECONDARY) {

	        if (tableBook.getSelectionModel().getSelectedItem() != null) { // Check if selected cell contains a book
		        Book selectedBook = tableBook.getSelectionModel().getSelectedItem();
		        cm = new ContextMenu();
		        MenuItem mi1 = new MenuItem("Loan");
		        cm.getItems().add(mi1);
		        MenuItem mi2 = new MenuItem("Delete");
		        cm.getItems().add(mi2);
		        mi1.setOnAction(e -> addToBasket(selectedBook));
		        mi2.setOnAction(e -> System.out.println("Delete"));
		        cm.setAutoHide(true);
	        	cm.show(tableBook , event.getScreenX() , event.getScreenY()); // Context menu is shown
	        }
	    }
	}
	
	public void addToBasket(Book book){
		if (book != null && MainWindow.user != null) {
			booksInBasket.add(book);
			updateBasket();
		}
	}
	
	public void updateBasket(){
		basketList.getItems().clear();
		for (Book books : booksInBasket) {
			basketList.getItems().add(books.getTitle());
		}
		
	
		
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

	
	public void showSidePanel(){
		User user = MainWindow.user;
		if (user == null) {
			booksLoaningAmount.setText("");
			nameText.setText("");
			streetText.setText("");
			cityText.setText("");
			booksLoaningText.setVisible(false);
			balanceText.setVisible(false);
			amountText.setText("");
			basketText.setVisible(false);
			basketList.setVisible(false);
			loanBtn.setVisible(false);
			enterIdText.setVisible(true);
			userIdField.setVisible(true);
			goBtn.setVisible(true);
			
		} else {
			String bookCount = "";
			if(user.getBookList() == null) {
				bookCount = "0";
			}else {
				bookCount = Integer.toString(user.getBookList().size());
			}
			enterIdText.setVisible(false);
			userIdField.setVisible(false);
			goBtn.setVisible(false);
			nameText.setText(user.getName());
			streetText.setText(user.getStreet());
			cityText.setText(user.getCity());
			booksLoaningText.setVisible(true);
			booksLoaningAmount.setText(bookCount);
			balanceText.setVisible(true);
			amountText.setText(Double.toString(user.getDebt()));
			basketText.setVisible(true);
			basketList.setVisible(true);
			loanBtn.setVisible(true);
			updateBasket();
			
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
		EmptyTemplateUI.display();
	}
	public void booksMenuAction(){
		BooksUI.display();
	}
	public void usersMenuAction() {
		UserListUI.display(this.getClass());
	}
	public void goToBookView(Book book){
		BookViewUI.display(this.getClass(), book);
	}
	public void openDelayedBooks() {
		DelayedBook.display();
	}	
	public void openRegister() {
		RegisterUserUI.display();
	}
	
	
	// for DEBUGGING
	public void returnAllBooks(){
		User user = MainWindow.user;
		for (int i = 0 ; i < user.getBookList().size(); i++) {
			MainWindow.lib.returnBook(user, MainWindow.user.getBookList().get(i));
		}
		System.out.println("All books returned for + " + user.getName());
		showSidePanel();
	}

}
