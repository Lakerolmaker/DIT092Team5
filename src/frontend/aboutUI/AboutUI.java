package frontend.aboutUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import frontend.MainWindow;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AboutUI implements Initializable{
	private static Stage window;
	private static Scene aboutUI;
	private static AnchorPane rootView;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public static void display() {
		window = new Stage();
		try {
			Class<AboutUI> context = AboutUI.class;
			rootView = (AnchorPane)FXMLLoader.load(context.getResource("AboutUI.fxml"));
			aboutUI = new Scene(rootView);
			aboutUI.getStylesheets().add(MainWindow.css);

			// Set the main window to show this scene
			window.initModality(Modality.APPLICATION_MODAL); // Block other windows as long this one is open part1
			window.setScene(aboutUI);
			window.setResizable(false);
			window.showAndWait(); // Block other windows as long this one is open part2
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
