package frontend.delayedBooksUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import frontend.emptyTemplateUI.*;
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
			Class context = DelayedBook.class;
			try {

				VBox bookView = (VBox)FXMLLoader.load(context.getResource("DelayedBook.fxml"));
				scene = new Scene(bookView,1192,650);
				scene.getStylesheets().add(MainWindow.css);
	
				MainWindow.window.setScene(scene);
				MainWindow.window.show();

			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
				
		}
	
	}