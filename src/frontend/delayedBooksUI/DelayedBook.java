package frontend.delayedBooksUI;

import frontend.MainWindow;
import frontend.statsUI.StatsUI;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

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