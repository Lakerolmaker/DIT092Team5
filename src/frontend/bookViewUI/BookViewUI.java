package frontend.bookViewUI;

import frontend.*;
import frontend.aboutUI.AboutUI;
import frontend.bookEditUI.BookEditUI;
import frontend.booksUI.*;
import frontend.homeUI.HomeUI;
import frontend.registerUserUI.RegisterUserUI;
import frontend.userListUI.UserListUI;
import frontend.userprofileUI.UserProfileUI;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import frontend.statsUI.*;
import frontend.MainWindow;
import frontend.aboutUI.AboutUI;
import frontend.booksUI.BooksUI;
import frontend.delayedBooksUI.DelayedBook;
import frontend.homeUI.HomeUI;
import frontend.newBookUI.NewBookUI;
import frontend.preferencesUI.PreferencesUI;
import frontend.registerUserUI.RegisterUserUI;
import frontend.userListUI.UserListUI;
import java.util.Map.Entry;
import java.util.Optional;
import frontend.delayedBooksUI.*;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import program.Book;
import program.Functions;
import program.Library;
import program.LoanInstance;
import program.User;

/**
 * Empty template on how to initialize a
 *
 */

public class BookViewUI implements Initializable {
	private static VBox root;
	private static Scene bookView;
	// Left Panel
	@FXML
	private ImageView logoImage;
	@FXML
	private Label menuHome, menuBooks, menuUsers, menuDelayed;
	// SidePanel
	@FXML private Text nameText,streetText,cityText,balanceText,amountText,basketText,booksLoaningText,booksLoaningAmount,enterIdText, currencyText;
	@FXML private Text onlyNumText,noUserFoundText,switchUserText,returnDateText,dateErrorText, basketTitleText, basketQtyText;
	@FXML private Button goBtn,loanBtn,loanActionBtn,cancelBtn, editBookBtn, removeBtn;
	@FXML private TextField userIdField;
	@FXML private DatePicker datePicker;
	@FXML private ListView<HBox> basketList;
	private ContextMenu cm2; // BasketList context menu

	private static Book selectedBook;
	private String title, author, imageName, isbn;
	private int year, availableQty;
	private String category, shelfText, quantity, description, publisher;
	@FXML private Text titleText, quantityText, availableText, descriptionText, publisherText, authorText, isbnText, yearText, categoryText;
	@FXML private Button loanBtn2, backBtn;
	@FXML public Label bookTitle;
	@FXML private ImageView bookImageView;
	@FXML private Image bookImage;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		nameText.setOnMouseClicked(e -> {
			UserProfileUI.display(MainWindow.user);
		});
		booksLoaningText.setOnMouseClicked(e -> {
			UserProfileUI.display(MainWindow.user);
		});
		description = selectedBook.getDescription();
		if (description != null && description.length() > 0) {
			descriptionText.setText(description);
		} else {
			descriptionText.setText("This book has no description, and may be lacking other information."
					+ " Books should have a summary to inform others of the content, author, source, and date if possible. "
					+ "If you know or have access to such information, please add it to the book page. ");
		}
		title = selectedBook.getTitle();
		author = selectedBook.getAuthor();
		isbn = selectedBook.getIsbn();
		year = selectedBook.getYear();
		category = selectedBook.getCategory();
		publisher = selectedBook.getPublisher();
		imageName = selectedBook.getImage();
		quantity = Integer.toString(selectedBook.getQuantity());
		availableQty = selectedBook.getAvailableQuantity();
		bookTitle.setId("bookTitle");
		bookTitle.setStyle("-fx-alignment: TOP_LEFT;");
		titleText.setText(title);
		authorText.setText(author);
		isbnText.setText(isbn);
		yearText.setText(Integer.toString(year));
		categoryText.setText(category);
		publisherText.setText(publisher);
		quantityText.setText(quantity);
		availableText.setText(Integer.toString(availableQty));
		if (availableQty < 1 || BooksUI.bookInBasket(selectedBook) >= availableQty) {
			loanBtn2.setDisable(true);
		} else {
			loanBtn2.setDisable(false);
			;
		}

		try {
			BufferedImage bookImageBuffered = ImageIO.read(new File("bookimages/" + imageName));
			bookImage = SwingFXUtils.toFXImage(bookImageBuffered, null);
			bookImageView.setImage(bookImage);
			bookImageView.autosize();
			bookImageView.resize(170, 270);
		} catch (Exception e) {
			e.printStackTrace();
		}

		switchUserText.setId("switchUserText");
		Image logo = new Image("resources/logo.png");
		logoImage.setImage(logo);

		try {
			bookTitle.setText(title);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		showSidePanel();

		if (MainWindow.user == null) {
			newBasket();
		} else {
			try {
				ArrayList<LoanInstance> loanList = MainWindow.user.getBookList();

			} catch (Exception e) {
				// User is not currently loaning any books
			}

		}
	}

	public static void display(Book book) {
		Class context = BookViewUI.class;
		selectedBook = book;
		try {
			// This is the scene that is going to be shown inside the window 
			VBox bookViewContainer = (VBox) FXMLLoader.load(context.getResource("BookView.fxml"));
			bookView = new Scene(bookViewContainer, 1192, 650);
			bookView.getStylesheets().add(MainWindow.css);

			// Set the main window to show this scene
			MainWindow.window.setScene(bookView);
			MainWindow.window.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loanBtn2Click() {
		addToBasket(selectedBook);
		if (availableQty < 1 || BooksUI.bookInBasket(selectedBook) >= availableQty) {
			loanBtn2.setDisable(true);
		}
	}

	public void editBookBtnClick(){
		BookEditUI.display(selectedBook);
	}
	

	
	/************************* SIDE PANEL ***************************/

	/** Loan button **/
	public void loanBtnClicked() {
		LocalDate returnDate = datePicker.getValue(); // TODO
		if (returnDate == null) {
			dateErrorText.setText("Please fill in return date");
			return;
		}
		if (!returnDate.isAfter(LocalDate.now())) {
			dateErrorText.setText("Return date has to be after today");
			dateErrorText.setVisible(true);
			return;
		}
		if (!returnDate.isBefore(LocalDate.now().plusDays(Library.LOAN_ALLOWANCE + 1))) {
			dateErrorText.setText("Maximum days to loan is " + Library.LOAN_ALLOWANCE);
			return;
		}
		dateErrorText.setText("");
		for (Entry<Book, Integer> book : BooksUI.booksInBasket.entrySet()) {
			for (int i = 0; i < book.getValue(); i++) {
				try {
					MainWindow.lib.loanBook(MainWindow.user, book.getKey(), returnDate);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			showSidePanel(); // Update side panel
			basketList.getItems().clear(); // Clear basket
			newBasket();
			BookViewUI.display(selectedBook);
		}
	}

	public void newBasket() {
		BooksUI.booksInBasket = new HashMap<>();
		if (MainWindow.user != null) {
			basketText.setVisible(true);
			cancelBtn.setDisable(true);
		}
	}

	public void goBtnClicked() {
		String idStr = userIdField.getText();
		if (Functions.isInt(idStr)) {
			int id = Integer.parseInt(idStr);
			MainWindow.user = MainWindow.lib.getUser(id);
			if (MainWindow.user != null) {
				showSidePanel();
			} else {
				onlyNumText.setVisible(false);
				noUserFoundText.setVisible(true);
			}

		} else {
			noUserFoundText.setVisible(false);
			onlyNumText.setVisible(true);
		}
	}

	public void basketListClick(MouseEvent event) {

		if (cm2 != null) {
			if (cm2.isShowing()) {
				cm2.hide(); // Don't allow duplicate context menus open
			}
		}
		if (event.getButton() == MouseButton.SECONDARY) {
			if (basketList.getSelectionModel().getSelectedItem() != null) {
				HBox hBox = (HBox) basketList.getSelectionModel().getSelectedItem();
				ObservableList list = hBox.getChildren();
				Text txt1 = (Text) list.get(0);
				String tmpQty = txt1.getText().substring(0, 2);
				int tmpQty2 = 0;
				if (Functions.isInt(tmpQty)) {
					tmpQty2 = Integer.parseInt(tmpQty);
				} else {
					tmpQty2 = Integer.parseInt(tmpQty.substring(0, 1));
				}
				final int qty = tmpQty2;
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
						for (Book book : BooksUI.booksInBasket.keySet()) {
							if (book.getIsbn().equals(isbn)) {
								removeFromBasket(book);
							}
						}
					} catch (Exception e2) {
					}
				});
				if (qty > 1) {
					MenuItem mi2 = new MenuItem("Remove all");
					cm2.getItems().add(mi2);
					mi2.setOnAction(e -> {
						try {
							for (Book book : BooksUI.booksInBasket.keySet()) {
								if (book.getIsbn().equals(isbn)) {
									for (int i = 0; i < qty; i++) {
										removeFromBasket(book);
									}
								}
							}
						} catch (Exception e3) {
						}

					});
				}

				cm2.show(basketList, event.getScreenX(), event.getScreenY());

			}

		}
	}

	public void removeFromBasket(Book book) {
		if (book != null && MainWindow.user != null) {
			if (BooksUI.booksInBasket.containsKey(book)) {
				if (BooksUI.booksInBasket.get(book) > 1) {
					BooksUI.booksInBasket.put(book, BooksUI.booksInBasket.get(book) - 1);
				} else {
					BooksUI.booksInBasket.remove(book);
				}
				updateBasket();
			}
		}
	}

	// Add to basket
	public void addToBasket(Book book) {
		if (book != null && MainWindow.user != null) {
			if (BooksUI.booksInBasket.containsKey(book)) {
				BooksUI.booksInBasket.put(book, BooksUI.booksInBasket.get(book) + 1);
			} else {
				BooksUI.booksInBasket.put(book, 1);
			}

			updateBasket();
		}
	}

	public void updateBasket() {
		basketList.getItems().clear();
		for (Entry<Book, Integer> book : BooksUI.booksInBasket.entrySet()) {
			Label isbn = new Label(book.getKey().getIsbn());
			isbn.setId("bookIsbnBasket");

			String title = book.getKey().getTitle();
			if (title.length() > 20) {
				title = title.substring(0, 20) + "...";
			}
			Label titleLabel = new Label(title);

			titleLabel.setId("bookTitleBasket");
			HBox hBox = new HBox(new Text(book.getValue().toString() + "pcs"), titleLabel, isbn);
			hBox.setSpacing(15);
			basketList.getItems().add(hBox);
		}

		if (BooksUI.booksInBasket.isEmpty()) {
			basketText.setVisible(true);
			cancelBtn.setDisable(true);
		} else {
			basketText.setVisible(false);
			cancelBtn.setDisable(false);
		}
		if (availableQty < 1 || BooksUI.bookInBasket(selectedBook) >= availableQty) {
			loanBtn2.setDisable(true);
		} else {
			loanBtn2.setDisable(false);
		}
	}

	public void showSidePanel() {
		User user = MainWindow.user;
		if (user == null) {
			currencyText.setVisible(false);
			dateErrorText.setText("");
			dateErrorText.setVisible(false);
			returnDateText.setVisible(false);
			datePicker.setVisible(false);
			switchUserText.setVisible(false);
			cancelBtn.setVisible(false);
			noUserFoundText.setVisible(false);
			onlyNumText.setVisible(false);
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
			try {
				bookCount = Integer.toString(user.getBookList().size());
			} catch (Exception e) {
				bookCount = "0";
			}
			currencyText.setVisible(true);
			dateErrorText.setVisible(true);
			returnDateText.setVisible(true);
			datePicker.setVisible(true);
			setDatePicker();
			switchUserText.setVisible(true);
			cancelBtn.setVisible(true);
			noUserFoundText.setVisible(false);
			onlyNumText.setVisible(false);
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
			int dept = (int) user.getDebt();
			String deptStr = "";
			if (dept > 0) {
				deptStr += "- ";
				amountText.setId("amountTextNegative");
			} else {
				amountText.setId("amountText");
			}
			deptStr += Integer.toString(dept);
			amountText.setText(deptStr);
			basketText.setVisible(true);
			basketList.setVisible(true);
			loanBtn.setVisible(true);
			updateBasket();

		}
	}

	public void setDatePicker() {
		int maxDays = Library.LOAN_ALLOWANCE;
		if (maxDays < 14) {
			datePicker.setValue(LocalDate.now().plusDays(Library.LOAN_ALLOWANCE));
		} else {
			datePicker.setValue(LocalDate.now().plusDays(14));
		}
	}

	public void switchUserClick() {
		MainWindow.user = null;
		display(selectedBook);
	}

	public void cancelBtnClicked() {
		BooksUI.booksInBasket.clear();
		updateBasket();
	}

	public void onEnterLogIn() {
		goBtnClicked();
	}

	public void removeBookBtnClick() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure that you want to remove this book?");
		Optional <ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK ) {
			frontend.MainWindow.lib.removeBook(selectedBook, selectedBook.getQuantity());
			new Alert(Alert.AlertType.INFORMATION, "Book: " + selectedBook.getTitle() + " has been removed!").showAndWait();
			BooksUI.display();
		}	
	}
	
	
	/******** File MENU ********/
	public void newBook(){
		NewBookUI.display();
	}
	public void save() {
		MainWindow.lib.save();

		Alert alert = new Alert(AlertType.INFORMATION, "Library Saved", ButtonType.OK);
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		alert.show();
		
	}
	public void quitMenuClick() {
		MainWindow.closeProgram();
	}
	public void prefMenuBtnClick(){
		PreferencesUI.display();
	}
	public void aboutMenuBtnClick() {
		AboutUI.display();
	}
	/******** Main menu ********/
	public void homeMenuAction() {
		HomeUI.display();
	}

	public void booksMenuAction() {
		BooksUI.display();
	}

	public void usersMenuAction() {
		UserListUI.display();
	}

	public void goToBookView(Book book) {
		BookViewUI.display(book);
	}

	public void openDelayedBooks() {
		DelayedBook.display();
	}

	public void openRegister() {
		RegisterUserUI.display();
	}

	public void openStats() {
		StatsUI.display();
	}


}
