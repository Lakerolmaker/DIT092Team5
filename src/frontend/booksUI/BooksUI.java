package frontend.booksUI;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import frontend.newBookUI.*;
import frontend.delayedBooksUI.*;
import frontend.homeUI.HomeUI;
import frontend.MainWindow;
import frontend.registerUserUI.*;
import frontend.userListUI.*;
import frontend.bookViewUI.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import program.*;


public class BooksUI implements Initializable {
	
	@FXML public TableView<Book> tableBook;
	@FXML public TableColumn<Book, String> titleColumn;
	@FXML public TableColumn<Book, String> authorColumn;
	@FXML public TableColumn<Book, String> yearColumn;
	@FXML public TableColumn<Book, String> isbnColumn;
	@FXML public TableColumn<Book, String> qtyAvColumn;
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
	@FXML public ListView<HBox> basketList;
	@FXML public Button loanBtn;
	@FXML public Text enterIdText;
	@FXML public TextField userIdField;
	@FXML public Button goBtn; 
	@FXML public Text basketQtyText;
	@FXML public Text basketTitleText;
	@FXML public CheckBox showOnlyAv;
	
	private static boolean showOnlyAvailable;
	private static String searchFieldString = "";
	
	public static HashMap<Book, Integer> booksInBasket;
	//public static ArrayList<Book> booksInBasket;
	private ContextMenu cm;
	private ContextMenu cm2;
	private static Scene bookScene;
	

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		showOnlyAv.setSelected(showOnlyAvailable);
		showOnlyAv.setOnAction(e -> {
			showOnlyAvailable = showOnlyAv.isSelected();
			display();
		});
		
		searchField.setText(searchFieldString);
		
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
		booksInBasket = new HashMap<>();
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
			Class<BooksUI> context = BooksUI.class;
			VBox bookView = (VBox)FXMLLoader.load(context.getResource("Book.fxml"));
			bookScene = new Scene(bookView,1192,650);
			bookScene.getStylesheets().add(MainWindow.css);
			
			MainWindow.window.setScene(bookScene);
			MainWindow.window.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** Loan button **/
	public void loanBtnClicked(){
		//for (Book book : booksInBasket) {
		for (Entry<Book, Integer> book : booksInBasket.entrySet()) {
			for (int i = 0; i < book.getValue(); i++) {
				try {
					MainWindow.lib.loanBook(MainWindow.user, book.getKey());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			showSidePanel(); // Update side panel
			basketList.getItems().clear(); // Clear basket
			newBasket();
		}
		tableBook.refresh();
	}
	
	/** Search Button **/
	public void searchFunc(ActionEvent event){
		String searchString = searchField.getText().toString();
		tableBook.setItems(getMatchingBooks(searchString));
		searchFieldString = searchString;
	}
	
	

	/******** BOOK VIEW FUNCTIONS ********/

	// Initialize table
	public void initTable(){
		// Title column
		titleColumn = new TableColumn<>("Title");
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		titleColumn.setMaxWidth(8000);
		// Author column
		authorColumn = new TableColumn<>("Author");
		authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
		// Year column
		yearColumn = new TableColumn<>("Year");
		yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
		yearColumn.setMaxWidth(2500);
		// ISBN column
		isbnColumn = new TableColumn<>("ISBN");
		isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
		isbnColumn.setMaxWidth(4000);
		// Available quantity
		qtyAvColumn = new TableColumn<>("Available");
		qtyAvColumn.setCellValueFactory(c-> new SimpleStringProperty(Integer.toString(c.getValue().getAvailableQuantity())));
		qtyAvColumn.setMaxWidth(3000);
		qtyAvColumn.setId("qtyAvColumn");
		qtyAvColumn.setStyle("-fx-alignment: CENTER;");


		
		tableBook.setItems(getBooks());
		tableBook.getColumns().addAll(titleColumn, authorColumn, yearColumn, isbnColumn, qtyAvColumn);
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
	
	
	
	public void basketListClick(MouseEvent event) {
		
	   	if (cm2 != null) {
	    	if (cm2.isShowing()) {
	    		cm2.hide(); // Don't allow duplicate context menus open
	    	}
    	}
		if (event.getButton() == MouseButton.SECONDARY) {
			if(basketList.getSelectionModel().getSelectedItem() != null) {
				HBox hBox = (HBox) basketList.getSelectionModel().getSelectedItem();
				ObservableList list =  hBox.getChildren();
				Text txt1 = (Text) list.get(0);
				int qty = Integer.parseInt((txt1.getText()));
				Label lbl1 = (Label) list.get(1);
				String title = lbl1.getText();
				Label lbl2 = (Label) list.get(2);
				String isbn = lbl2.getText();
				// Context menu
				cm2 = new ContextMenu();
				MenuItem mi1 = new MenuItem("Remove");
				cm2.getItems().add(mi1);
				mi1.setOnAction(e -> {
					try {
						for (Book book : booksInBasket.keySet()) {
							if (book.getIsbn().equals(isbn)) {
								removeFromBasket(book);
							}
						}
					}catch (Exception e2) {}
				});
				if (qty > 1) {
					MenuItem mi2 = new MenuItem("Remove all");
					cm2.getItems().add(mi2);
					mi2.setOnAction(e -> {
						try {
							for (Book book : booksInBasket.keySet()) {
								if (book.getIsbn().equals(isbn)) {
									for (int i = 0; i < qty ; i++) {
										removeFromBasket(book);			
									}
								}
							}
						}catch (Exception e3) {}
						
					});
				}
				
				cm2.show(basketList, event.getScreenX() , event.getScreenY());
				
			}
			
			
		}
	}
	
	public void removeFromBasket(Book book) {
		if (book != null && MainWindow.user != null) {
			if (booksInBasket.containsKey(book)) {
				if (booksInBasket.get(book) > 1) {
					booksInBasket.put(book, booksInBasket.get(book) - 1);
				}else {
					booksInBasket.remove(book);
				}
				updateBasket();
			}
		}
	}
	
	// Add to basket
	public void addToBasket(Book book){
		if (book != null && MainWindow.user != null) {
			if (booksInBasket.containsKey(book)) {
				booksInBasket.put(book, booksInBasket.get(book) +1 );
			}else {
				booksInBasket.put(book, 1);
			}
			
			updateBasket();
		}
	}
	
	public void updateBasket(){
		basketList.getItems().clear();
		for (Entry<Book, Integer> book : booksInBasket.entrySet()) {
			Label isbn = new Label(book.getKey().getIsbn());
			isbn.setId("bookIsbnBasket");
			Label title = new Label(book.getKey().getTitle());
			title.setId("bookTitleBasket");
			HBox hBox = new HBox(new Text(book.getValue().toString()), title, isbn);
			hBox.setSpacing(15);
			basketList.getItems().add(hBox);
		}
		
		if (booksInBasket.isEmpty()) {
			basketText.setVisible(true);
		}else {
			basketText.setVisible(false);
		}
	
		
	}
	
	// Return list of books
	public ObservableList<Book> getBooks() {
		ObservableList<Book> books = FXCollections.observableArrayList();
		for (Book book : MainWindow.lib.getBookList()) {
			books.add(book);
		}
		
		if(showOnlyAv.isSelected()) {
			books = showOnlyAvailable(books);
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
		if(showOnlyAv.isSelected()) {
			books = showOnlyAvailable(books);
		}
		return books; // Return the new composed list
	}
	
	
	public ObservableList<Book> showOnlyAvailable(ObservableList<Book> bookList) {
		ObservableList<Book> newBookList = FXCollections.observableArrayList();
		for (Book book : bookList) {
			if (book.getAvailableQuantity() > 0) {
				newBookList.add(book);
			}
		}
		return newBookList;
	}

	
	public void showSidePanel(){
		User user = MainWindow.user;
		if (user == null) {
			basketQtyText.setVisible(false);
			basketTitleText.setVisible(false);
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
			basketQtyText.setVisible(true);
			basketTitleText.setVisible(true);
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
	
	
	// for DEBUGGING
	public void returnAllBooks(){
		User user = MainWindow.user;
		for (int i = user.getBookList().size() - 1 ; i >= 0 ; i--) {
			MainWindow.lib.returnBook(user, user.getBookList().get(i));
		}
		
		System.out.println("All books returned for : " + user.getName());
		display();
	}

}
