package frontend.delayedBooksUI;

import frontend.MainWindow;
import frontend.statsUI.StatsUI;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;


/*
This is a class created by Jacob Olsson

This class only have function , it's to launch the Delayed book screen.


*/
public class DelayedBook {
	
	public static Scene scene;
	
		//: A function that launches the delayed book view
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