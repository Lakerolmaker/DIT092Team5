package frontend;


import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;


/**
 * Empty template on how to initialize a 
 *
 */

public class EmptyTemplateUI {
	private static VBox root;
	private static Scene emptyTemplate;
	
	public static void display(Class context) {
		try {
			// This is the scene that is going to be shown inside the window ( Main window in this case )
			VBox homeView = (VBox)FXMLLoader.load(context.getResource("EmptyTemplate.fxml")); 
			emptyTemplate = new Scene(homeView,1192,650);
			emptyTemplate.getStylesheets().add(context.getResource("application.css").toExternalForm());
			

			// Set the main window to show this scene
			MainWindow.window.setScene(emptyTemplate);
			MainWindow.window.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
