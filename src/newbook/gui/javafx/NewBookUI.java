package newbook.gui.javafx;



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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



public class NewBookUI implements Initializable{
	private static Scene scene;
	static Stage window;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
	
	public static void display() {
		window = new Stage();
		try {
			URL url = new File("src/newbook/gui/javafx/NewBook.fxml").toURI().toURL();
			AnchorPane homeView = (AnchorPane)FXMLLoader.load(url); 
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
	public void newBookAction()
	{}

}
