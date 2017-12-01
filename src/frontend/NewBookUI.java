package frontend;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
	
	public static void display(Class context) {
		window = new Stage();
		try {
			AnchorPane homeView = (AnchorPane)FXMLLoader.load(context.getResource("NewBook.fxml")); 
			scene = new Scene(homeView);
			scene.getStylesheets().add(context.getResource("application.css").toExternalForm());
			


			window.setScene(scene);
			window.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@FXML
	public void cancelAction(Event event) {
		window.close();
	}
	
	@FXML
	public void newBookAction(Event event)
	{}

}
