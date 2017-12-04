package frontend.newBookUI;



import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import frontend.MainWindow;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



public class NewBookUI implements Initializable{
	private static Scene scene;
	static Stage window;
	
	@FXML private TextField isbnText;
	@FXML private TextField titleText;
	@FXML private TextField authorText;
	@FXML private TextField yearText;
	@FXML private TextField categoryText;
	@FXML private TextField shelfText;
	@FXML private TextField quantityText;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
	
	public static void display() {
		Class context = NewBookUI.class;
		window = new Stage();
		try {
			//URL url = new File("src/newbook/gui/javafx/NewBook.fxml").toURI().toURL();
			AnchorPane homeView = (AnchorPane)FXMLLoader.load(context.getResource("NewBook.fxml")); 
			scene = new Scene(homeView);
			scene.getStylesheets().add(MainWindow.css);
		
			window.setScene(scene);
			window.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@FXML
	public void cancelAction() {
		window.close();
	}
	
	@FXML
	public void newBookAction() {
		String isbn = isbnText.getText().trim();
		String title = titleText.getText().trim();
		String author = authorText.getText().trim(); 
		int year = Integer.parseInt(yearText.getText().trim());
		String category = categoryText.getText().trim();
		int shelf = Integer.parseInt(shelfText.getText().trim());
		int qty = Integer.parseInt(quantityText.getText().trim());
		
		try {
			frontend.MainWindow.lib.addBook(isbn, title, author, year, category, shelf, qty);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
