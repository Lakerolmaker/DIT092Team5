package frontend;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import program.Book;
import program.Functions;

public class BooksUI {

	
	private static VBox root;
	private static Scene bookScene;
	
	public static void display(Class context) {
		
		try {
			VBox bookView = (VBox)FXMLLoader.load(context.getResource("Book.fxml"));
			bookScene = new Scene(bookView,1192,650);
			bookScene.getStylesheets().add(context.getResource("application.css").toExternalForm());
			
			
			MainWindow.window.setScene(bookScene);
			MainWindow.window.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	

	
	
	

	


}
