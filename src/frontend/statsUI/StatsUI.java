package frontend.statsUI;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

import frontend.MainWindow;
import frontend.aboutUI.AboutUI;
import frontend.bookViewUI.BookViewUI;
import frontend.booksUI.BooksUI;
import frontend.delayedBooksUI.DelayedBook;
import frontend.homeUI.HomeUI;
import frontend.newBookUI.NewBookUI;
import frontend.preferencesUI.PreferencesUI;
import frontend.registerUserUI.RegisterUserUI;
import frontend.userListUI.UserListUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import program.Book;
import program.Library;
import program.User;

public class StatsUI implements Initializable {

	private static VBox root;
	private static Scene stats;

	private static Library lib = MainWindow.lib;
	
	@FXML
	private ImageView logoImage;
	@FXML
	private Label statsLabel; // Link to the fx:id in scenebuilder
	@FXML
	private BarChart<?, ?> LoanChart;
	@FXML
	private CategoryAxis xBookName;
	@FXML
	private NumberAxis yLoaned;
	@FXML
	private TableView<User> tableUser;
	@FXML
	private TableColumn<User, String> nameColumn, surnameColumn;
	@FXML
	private TableColumn<User, Integer> idColumn, loanColumn;
	@FXML
	private TableColumn<User, Double> debtColumn;
	@FXML
	private Label userDebt, totalBooks, totalUsers;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Image logo = new Image("resources/logo.png");
		logoImage.setImage(logo);

		ArrayList<Book> books = new ArrayList<Book>();
		double sum = 0;
		int sum1 = 0;
		int booksCount = 0;

		ObservableList<User> users = FXCollections.observableArrayList(lib.getUserList());

		books = lib.getBookList();

		Collections.sort(books, comp);

		XYChart.Series set1 = new XYChart.Series<>();

		for (int i = 0; i < 5; i++) {

			set1.getData().add(new XYChart.Data((books.get(i).getTitle()), (books.get(i).loanTotal)));

		}

		LoanChart.getData().addAll(set1);

		nameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
		surnameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
		loanColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("loanedBooksTotal"));
		idColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("userId"));
		debtColumn.setCellValueFactory(new PropertyValueFactory<User, Double>("debt"));

		tableUser.setItems(users);

		for (User user : lib.getUserList()) {

			sum += user.getDebt();

		}
		
		for (Book book : lib.getBookList()) {

			sum1 += book.getQuantity();

		}

		userDebt.setText(userDebt.getText() + "   " + sum + "  SEK");
		totalBooks.setText(totalBooks.getText() + "   " + sum1);
		totalUsers.setText(totalUsers.getText() + "   " + lib.getUserList().size());

	}

	public static void display() {
		Class context = StatsUI.class;
		try {
			// This is the scene that is going to be shown inside the window (
			// Main window in this case )
			VBox homeView = (VBox) FXMLLoader.load(context.getResource("StatsUI.fxml"));
			stats = new Scene(homeView, 1192, 650);
			stats.getStylesheets().add(MainWindow.css);

			// Set the main window to show this scene
			MainWindow.window.setScene(stats);
			MainWindow.window.show();
		} catch (IOException e) {
			e.printStackTrace();
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

	// comparator to compare books by loan total
	public Comparator<Book> comp = new Comparator<Book>() {

		public int compare(Book b1, Book b2) {

			int num1 = b1.loanTotal;
			int num2 = b2.loanTotal;

			return num2 - num1;

		}
	};

}
