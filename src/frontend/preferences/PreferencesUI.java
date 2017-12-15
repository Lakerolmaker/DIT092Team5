package frontend.preferences;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import frontend.MainWindow;
import frontend.emptyTemplateUI.EmptyTemplateUI;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class PreferencesUI implements Initializable {
	private static AnchorPane root;
	private static Scene preferencesUI;
	private static Stage window;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	
	public static void display() {
		window = new Stage();
		try {
			Class context = PreferencesUI.class;
			AnchorPane rootView = (AnchorPane)FXMLLoader.load(context.getResource("PreferencesUI.fxml"));
			preferencesUI = new Scene(rootView);
			preferencesUI.getStylesheets().add(MainWindow.css);

			// Set the main window to show this scene
			window.setScene(preferencesUI);
			window.setResizable(false);
			window.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
