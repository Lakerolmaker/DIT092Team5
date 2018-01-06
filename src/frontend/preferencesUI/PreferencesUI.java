package frontend.preferencesUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import frontend.MainWindow;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import program.Functions;
import program.Library;

public class PreferencesUI implements Initializable {
	private static AnchorPane rootView;
	private static Scene preferencesUI;
	private static Stage window;
	@FXML private TextField maxDays;
	@FXML private TextField maxBooks;
	@FXML private TextField maxDept;
	@FXML private Button saveBtn;
	@FXML private Button cancelBtn;
	@FXML private Text error1, error2, error3;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		reset();
	}
	
	public static void display() {
		window = new Stage();
		try {
			Class<PreferencesUI> context = PreferencesUI.class;
			rootView = (AnchorPane)FXMLLoader.load(context.getResource("PreferencesUI.fxml"));
			preferencesUI = new Scene(rootView);
			preferencesUI.getStylesheets().add(MainWindow.css);

			// Set the main window to show this scene
			window.initModality(Modality.APPLICATION_MODAL); // Block other windows as long this one is open part1
			window.setScene(preferencesUI);
			window.setResizable(false);
			window.showAndWait(); // Block other windows as long this one is open part2
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void reset() {
		maxBooks.setText("");
		maxDept.setText("");
		maxDays.setText(Integer.toString(Library.LOAN_ALLOWANCE));
		error1.setVisible(false);
		error2.setVisible(false);
		error3.setVisible(false);
	}
	
	public void saveClick() {
		try {
			int setDays = Integer.parseInt(maxDays.getText());
			MainWindow.lib.setLoanAllowance(setDays);
			error1.setVisible(false);
			window.close();
		}catch (Exception e){error1.setVisible(true);}
		/* TODO: When implemented
		try {
			int setMaxDept = Integer.parseInt(maxDept.getText());
			// TODO: Set maximum dept allowed
			error2.setVisible(false);
		}catch (Exception e){error2.setVisible(true);}
			
		try {
			int setMaxBooks = Integer.parseInt(maxBooks.getText());
			// TODO: Set max books allowed to loan
			error3.setVisible(false);
		}catch (Exception e){error3.setVisible(true);}
		*/
	}
	
	public void cancelClick() {
		window.close();
	}
}
