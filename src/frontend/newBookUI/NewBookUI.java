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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;



public class NewBookUI implements Initializable{
	private static Scene scene;
	static Stage window;
	
	@FXML private TextField isbnText;
	@FXML private TextField titleText;
	@FXML private TextField authorText;
	@FXML private TextField yearText;
	@FXML private ComboBox<String>  categoryText;
	@FXML private TextField shelfText;
	@FXML private TextField quantityText;
	@FXML private TextField publisherText; 
	
	@FXML private TextField imageText;
	private BufferedImage bufferedImage = null;
	private String imagePath;
	private String imageName;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// use regular expression to force user to enter valid input
	
		yearText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            	if (!newValue.matches("\\d{0,4}")) { // accepts 0 to 4 digits for year 
            		String text = newValue.replaceAll("[^\\d]", ""); // removes non digit characters
            		if(text.length() > 4) {
            			text = text.substring(0, 4);
            		}
            		yearText.setText(text);
                }
            }
        });
		
		isbnText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            	if (!newValue.matches("\\d{0,13}")) {  // accepts 0 to 13 digits for isbn
            		String text = newValue.replaceAll("[^\\d]", "");
            		if(text.length() > 13) {
            			text = text.substring(0, 13);
            		}
            		isbnText.setText(text);
                }
            }
        });
		
		
		shelfText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            	if (!newValue.matches("\\d{0,5}")) { // accepts 0 to 5 digits for shelf
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
            	if (!newValue.matches("\\d{0,5}")) { // accepts 0 to 5 digits for quantity
            		String text = newValue.replaceAll("[^\\d]", "");
            		if(text.length() > 5) {
            			text = text.substring(0, 5);
            		}
            		quantityText.setText(text);
                }
            }
        });
		
		// set all categories for comboBox 
		categoryText.setItems(FXCollections.observableArrayList(
				"Science fiction",
				"Satire",
				"Novel",
				"Drama",
				"Action and Adventure",
				"Romance",
				"Mystery",
				"Horror",
				"Health",
				"Guide",
				"Travel",
				"Children's",
				"Science",
				"History",
				"Math",
				"Anthology",
				"Poetry",
				"Encyclopedias",
				"Dictionaries",
				"Comics",
				"Art",
				"Cookbooks",
				"Diaries",
				"Journals",
				"Series",
				"Trilogy",
				"Biographies",
				"Autobiographies",
				"Fantasy"
				));
		categoryText.setValue("Comics");
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
		// checking mandatory fields 
		if(isbnText.getText().trim().equals("") ||
				titleText.getText().trim().equals("") ||
				authorText.getText().trim().equals("") ||
				shelfText.getText().trim().equals("") ||
				quantityText.getText().trim().equals("") || 
				publisherText.getText().trim().equals("") ||
				yearText.getText().trim().equals("") || 
				categoryText.getValue().toString().trim().equals(""))
		{
			new Alert(Alert.AlertType.NONE, "Please fill in all fields!", ButtonType.OK).showAndWait();
			return;
		}
		
		//get values from the form
		String isbn = isbnText.getText().trim();
		String title = titleText.getText().trim();
		String author = authorText.getText().trim(); 
		int year = Integer.parseInt(yearText.getText().trim());
		String category = categoryText.getValue().toString().trim();
		int shelf = Integer.parseInt(shelfText.getText().trim());
		int qty = Integer.parseInt(quantityText.getText().trim());
		String publisher = publisherText.getText().trim();
		
		if (isbn.length() != 10 && isbn.length() != 13) {
			new Alert(Alert.AlertType.NONE, "Isbn must be either 10 or 13 digits", ButtonType.OK).showAndWait();
			return;
		}
		
		// handles exceptions from addBook method
		try {
			String bookImage = "genericBookCover.jpg";
			if(bufferedImage != null) {
				try {
		            File outputfile = new File("bookimages/" + imageName);
		            ImageIO.write(bufferedImage, "png", outputfile);
		            System.out.println(outputfile.getAbsolutePath());
		            System.out.println(outputfile.toPath());
		            System.out.println(outputfile.toURL());
		            bookImage = imageName;
				}catch (Exception c) {
					c.printStackTrace();
				}
			}
			// addBook can throw exception
			frontend.MainWindow.lib.addBook(isbn, title, author, year, category, shelf, qty, bookImage, publisher);
			new Alert(Alert.AlertType.NONE, "Book added successfully!", ButtonType.OK).showAndWait();
			window.close();
			NewBookUI2.display(isbn);
		} catch (Exception e) {
			e.printStackTrace();
			new Alert(Alert.AlertType.WARNING, "Failed to add book! \n" + e.getMessage()).showAndWait();
		}
	}

	public void browseBtnClick(){
        FileChooser fileChooser = new FileChooser();
        
        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
         
        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);
                  
        try {
            bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imagePath = file.getAbsolutePath();
            imageName = file.getName();
            imageText.setText(imageName);
        } catch (Exception ex) {
            //Logger.getLogger(NewBookUI.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
}
