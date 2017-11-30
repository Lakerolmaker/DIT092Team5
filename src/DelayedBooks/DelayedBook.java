package DelayedBooks;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import frontend.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class DelayedBook{
	
	public static Scene scene;
	
		public static void display(Class context) {
			
			try {
				URL url = new File("src/DelayedBooks/DelayedBook.fxml").toURI().toURL();
				
				FXMLLoader loader = new FXMLLoader(url);
				Parent root = null;

				try {
				    root = loader.load();
				} catch (IOException e) {System.out.println(e.getMessage());}
				
				 scene = new Scene(root, 1192,650);
				 scene.getStylesheets().add("application.css");

				 MainWindow.window.setTitle("Delayed Books");
				 MainWindow.window.setScene(scene);
				// MainWindow.window.show();
			
				 DelayedBooksController control =  new DelayedBooksController();
				 
				 control.delayedBook = (TableView<DelayedPerson>) scene.lookup("#delayedBook");
				 
				 
				 control.initialize();
				 
				 
			} catch(Exception e) {
				//System.out.println(e.getMessage());
			}
	
		}

		
	}