package newbook.gui.javafx;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NewBookUI extends Application {

	public static Stage window;
	
	@Override
	public void start(Stage primaryStage) {
		window = primaryStage;
		try {
			Parent root = FXMLLoader.load(getClass().getResource("NewBook.fxml"));
			
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	@FXML
	public void cancelAction(Event event) {
		window.close();
	}
	
	@FXML
	public void newBookAction(Event event)
	{}
}
