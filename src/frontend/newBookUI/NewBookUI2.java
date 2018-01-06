package frontend.newBookUI;



import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import frontend.MainWindow;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import program.Book;



public class NewBookUI2 implements Initializable{
	private static Scene scene;
	private static String isbn,description;
	@FXML private Text titleText;
	@FXML private TextArea descriptionText;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		titleText.setText("You want to add a description?");
		descriptionText.setWrapText(true);
        
	}
	
	public static void display(String isbn) {
		NewBookUI2.isbn = isbn;
		try {
			Class context = NewBookUI2.class;
			AnchorPane homeView = (AnchorPane)FXMLLoader.load(context.getResource("NewBook2.fxml"));
			scene = new Scene(homeView);
			scene.getStylesheets().add(MainWindow.css);
		
			NewBookUI.window.setScene(scene);
			NewBookUI.window.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	
	@FXML
	public void okAction() {
		try {
			Book book = MainWindow.lib.findBookByIsbn(isbn);
			description = descriptionText.getText();
			book.setDescription(description);
			NewBookUI.window.close();
		} catch (Exception e) {
			e.printStackTrace();
			new Alert(Alert.AlertType.WARNING, "Max 450 characters!").showAndWait();
		}
	}
}
