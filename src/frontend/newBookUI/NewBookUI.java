package frontend.newBookUI;



import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import frontend.MainWindow;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



public class NewBookUI implements Initializable{
	private static Scene scene;
	static Stage window;
	
	@FXML private TextField isbnText;
	@FXML private TextField titleText;
	@FXML private TextField authorText;
	@FXML private TextField yearText;
	@FXML private TextField categoryText;
	@FXML private TextField shelfText;
	@FXML private TextField quantityText;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		yearText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            	if (!newValue.matches("\\d{0,4}")) {
            		String text = newValue.replaceAll("[^\\d]", "");
            		if(text.length() > 4) {
            			text = text.substring(0, 4);
            		}
            		yearText.setText(text);
                }
            }
        });
		
		shelfText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            	if (!newValue.matches("\\d{0,5}")) {
            		String text = newValue.replaceAll("[^\\d]", "");
            		if(text.length() > 5) {
            			text = text.substring(0, 5);
            		}
            		shelfText.setText(text);
                }
            }
        });
		
		quantityText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            	if (!newValue.matches("\\d{0,5}")) {
            		String text = newValue.replaceAll("[^\\d]", "");
            		if(text.length() > 5) {
            			text = text.substring(0, 5);
            		}
            		quantityText.setText(text);
                }
            }
        });
	}
	
	public static void display() {
		window = new Stage();
		try {
			Class context = NewBookUI.class;
			AnchorPane homeView = (AnchorPane)FXMLLoader.load(context.getResource("NewBook.fxml"));
			scene = new Scene(homeView);
			scene.getStylesheets().add(MainWindow.css);
		
			window.setScene(scene);
			window.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@FXML
	public void cancelAction() {
		window.close();
	}
	
	@FXML
	public void newBookAction() {	
		if(isbnText.getText().trim().equals("") ||
				titleText.getText().trim().equals("") ||
				authorText.getText().trim().equals("") ||
				shelfText.getText().trim().equals("") ||
				quantityText.getText().trim().equals("") || 
				yearText.getText().trim().equals("") || 
				categoryText.getText().trim().equals("")) {
			new Alert(Alert.AlertType.NONE, "Please fill in all fields!", ButtonType.OK).showAndWait();
			return;
		}
		
		String isbn = isbnText.getText().trim();
		String title = titleText.getText().trim();
		String author = authorText.getText().trim(); 
		int year = Integer.parseInt(yearText.getText().trim());
		String category = categoryText.getText().trim();
		int shelf = Integer.parseInt(shelfText.getText().trim());
		int qty = Integer.parseInt(quantityText.getText().trim());
		
		try {
			frontend.MainWindow.lib.addBook(isbn, title, author, year, category, shelf, qty);
			new Alert(Alert.AlertType.NONE, "Book added successfully!", ButtonType.OK).showAndWait();
			window.close();
		} catch (Exception e) {
			e.printStackTrace();
			new Alert(Alert.AlertType.WARNING, "Failed to add book! \n" + e.getMessage()).showAndWait();
		}
	}

}
