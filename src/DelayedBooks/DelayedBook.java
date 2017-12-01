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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DelayedBook{
	
	public static Scene scene;
	
		public static void display() {
			
			try {
				URL url = new File("src/DelayedBooks/DelayedBook.fxml").toURI().toURL();
				VBox bookView = (VBox)FXMLLoader.load(url);
				scene = new Scene(bookView,1192,650);
				scene.getStylesheets().add(MainWindow.css);
				
				MainWindow.window.setScene(scene);
				MainWindow.window.show();
			
				 //DelayedBooksController control =  new DelayedBooksController();
				// control.delayedBook = (TableView<DelayedPerson>) scene.lookup("#delayedBook");
				// control.initialize();
				 
				 
			} catch(Exception e) {
				//System.out.println(e.getMessage());
			}
	
		}

		
	}