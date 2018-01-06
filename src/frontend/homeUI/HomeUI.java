package frontend.homeUI;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import frontend.MainWindow;
import frontend.aboutUI.AboutUI;
import frontend.bookViewUI.BookViewUI;
import frontend.booksUI.BooksUI;
import frontend.delayedBooksUI.DelayedBook;
import frontend.newBookUI.NewBookUI;
import frontend.preferencesUI.PreferencesUI;
import frontend.registerUserUI.RegisterUserUI;
import frontend.statsUI.StatsUI;
import frontend.userListUI.UserListUI;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import program.Book;


public class HomeUI implements Initializable{
	private static VBox root;
	private static Scene homeScene;
	
	@FXML private ImageView logoImage;
	@FXML private Label topMenu1; // Link to the fx:id in scenebuilder
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Image logo = new Image("resources/logo.png");
		logoImage.setImage(logo);		
		
	}
	
	public static void display() {
		Class context = HomeUI.class;
		try {
			// This is the scene that is going to be shown inside the window ( Main window in this case )
			VBox homeView = (VBox)FXMLLoader.load(context.getResource("HomeUI.fxml")); 
			homeScene = new Scene(homeView,1192,650);
			homeScene.getStylesheets().add(MainWindow.css);

			// Set the main window to show this scene
			MainWindow.window.setScene(homeScene);
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
	public void openStats() {
		StatsUI.display();
	}
}
