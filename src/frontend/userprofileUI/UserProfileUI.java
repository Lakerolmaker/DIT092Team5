
/**
 * Description: Allows a librarian to view or edit information about the user.Contains the list of borrowed books and allows returning of books.
 *
 * @author Tihana Causevic
 */

package frontend.userprofileUI;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import frontend.MainWindow;
import frontend.aboutUI.AboutUI;
import frontend.booksUI.BooksUI;
import frontend.delayedBooksUI.DelayedBook;
import frontend.emptyTemplateUI.EmptyTemplateUI;
import frontend.homeUI.HomeUI;
import frontend.loanhistoryUI.loanHistoryUI;
import frontend.newBookUI.NewBookUI;
import frontend.preferencesUI.PreferencesUI;
import frontend.registerUserUI.RegisterUserUI;
import frontend.statsUI.StatsUI;
import frontend.userListUI.UserListUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import program.LoanInstance;
import program.User;
import program.UserBookList;

public class UserProfileUI implements Initializable{

		private static VBox root;
		private static Scene userScene;
		private static User tmpuser;
		
		@FXML private ImageView logoImage;
		@FXML private Label topMenu1; // Link to the fx:id in scenebuilder
		@FXML private TextField fName; 
		@FXML private TextField lName;
		@FXML private TextField SSN;
		@FXML private TextField phoneNr;
		@FXML private TextField zCode;
		@FXML private TextField street;
		@FXML private TextField city;
		@FXML private TextField debt;
		@FXML private TableView<UserBookList> borrowedBooks;
		@FXML private Label booksBorrowed;
		@FXML private Button edit;
		@FXML private Button deleteUserbtn;
		@FXML private Button btnHistory;
		
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			
			Image logo = new Image("resources/logo.png");
			logoImage.setImage(logo);

            // loads the user information into the text fields
			fName.setText(tmpuser.getFirstName());
			lName.setText(tmpuser.getLastName());
			SSN.setText(tmpuser.getSsn());
			phoneNr.setText(tmpuser.getPhoneNr());
			zCode.setText(tmpuser.getZipCode());
			street.setText(tmpuser.getStreet());
			city.setText(tmpuser.getCity());
			debt.setText(tmpuser.getDebt() + "");
			
			try {
                // load the list of borrowed books
				ObservableList<UserBookList> b = FXCollections.observableArrayList();
				for(LoanInstance book : tmpuser.getBookList()) {
					b.add(new UserBookList(book));
				}
				
				borrowedBooks.setItems(b);

                // table of borrowed books
				TableColumn titleCol = new TableColumn("Title");
				titleCol.setMinWidth(200);
		        titleCol.setCellValueFactory( new PropertyValueFactory<UserBookList, String>("title"));
		        
		        TableColumn authorCol = new TableColumn("Author");
				authorCol.setMinWidth(120);
		        authorCol.setCellValueFactory(new PropertyValueFactory<UserBookList, String>("author"));
		        
		        TableColumn dateCol = new TableColumn("Date");
				dateCol.setMinWidth(50);
		        dateCol.setCellValueFactory(new PropertyValueFactory<UserBookList, LocalDate>("returnDate"));

				TableColumn returnCol = new TableColumn("");
				returnCol.setCellValueFactory(new PropertyValueFactory<>("extraBtn"));

				Callback<TableColumn<UserBookList, String>, TableCell<UserBookList, String>> cellFactory
						=
						new Callback<TableColumn<UserBookList, String>, TableCell<UserBookList, String>>() {
							@Override
							public TableCell call(final TableColumn<UserBookList, String> param) {
								final TableCell<UserBookList, String> cell = new TableCell<UserBookList, String>() {

									final Button returnBtn = new Button("Return"); //creating the return button for the table

									@Override
									public void updateItem(String item, boolean empty) {
										super.updateItem(item, empty);
										if (empty) {
											setGraphic(null);
											setText(null);
										} else {
											returnBtn.setOnAction(event -> {
                                                // return the book when the return button is clicked

                                                UserBookList book = getTableView().getItems().get(getIndex());
                                                
                                                if (LocalDate.now().isAfter(book.getReturnDate())) {
                                                	JOptionPane.showMessageDialog(null, "This book has been delayed");
                                                }
                                                
                                                MainWindow.lib.returnBook(tmpuser, book.getBook());
												
												try {
													ObservableList<UserBookList> b = FXCollections.observableArrayList();
													for(LoanInstance book2 : tmpuser.getBookList()) {
														b.add(new UserBookList(book2));
													}
													borrowedBooks.setItems(b);
												} catch (Exception e) {
													e.printStackTrace();
												}
											});
											returnBtn.setId("returnActionBtn");

											setGraphic(returnBtn); // adds the return button to the table
											setText(null);
										}
									}
								};
								return cell;
							}
						};
	 					
						returnCol.setCellFactory(cellFactory);
				        returnCol.setStyle("-fx-alignment: CENTER;");
				        returnCol.setMaxWidth(2000);
		         
		        borrowedBooks.getColumns().addAll(titleCol, authorCol, dateCol, returnCol);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public static void display(User user) {
			tmpuser = user;
			Class context = UserProfileUI.class;
			try {
				// This is the scene that is going to be shown inside the window ( Main window in this case )
				VBox userView = (VBox)FXMLLoader.load(context.getResource("UserProfileUI.fxml")); 
				userScene = new Scene(userView,1192,650);
				userScene.getStylesheets().add(MainWindow.css);

				// Set the main window to show this scene
				MainWindow.window.setScene(userScene);
				MainWindow.window.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@FXML
		public void submitButtonAction(Event event)
		{
			if(!fName.getText().equals("") || !lName.getText().equals("") || !SSN.getText().equals("")) {  // if the first name field and the last name field are not empty then create a new user 
				tmpuser.setFirstName(fName.getText());
				tmpuser.setLastName(lName.getText());
				tmpuser.setStreet(street.getText());
				tmpuser.setZipCode(zCode.getText());
				tmpuser.setCity(city.getText());
				tmpuser.setPhoneNr(phoneNr.getText());
				
				User indexUser = MainWindow.lib.findUser(tmpuser.getSsn());
				
				try {
					MainWindow.lib.removeUser(indexUser);
                    MainWindow.lib.addUser(tmpuser);
                    
    				JOptionPane.showMessageDialog(null, "User is updated");
				} catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "An error occured while updating the user, please try again.");
                }

			}else {
				JOptionPane.showMessageDialog(null, "Please enter a first name and a last name to continue"); // if the first name and the last name field are empty then display an error box
			}
		}

        @FXML
        public void historyBtnClick(Event event)
        {
            loanHistoryUI.display(tmpuser);
        }
		
		@FXML
		public void cancelButtonAction(Event event)
		{
			UserListUI.display(); // displays the user list
		}
		
		@FXML
		public void deleteUserbtnAction(Event event)
		{
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure that you want to remove this user?");
			Optional <ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK ) {
				
				try {
					frontend.MainWindow.lib.removeUser(tmpuser);
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
				new Alert(Alert.AlertType.INFORMATION, "User: " + tmpuser.getName() + " has been removed!").showAndWait();
				UserListUI.display();
			}
			
			
			
			
		}

		@FXML
		public void editButtonClick(Event event) {
			fName.setDisable(false);
			lName.setDisable(false);
			SSN.setDisable(false);
			phoneNr.setDisable(false);
			street.setDisable(false);
			city.setDisable(false);
			zCode.setDisable(false);
		}
		
		
		/******** File MENU ********/
		public void newBook(){
			NewBookUI.display();
		}
		public void save() {
			MainWindow.lib.save();

			Alert alert = new Alert(AlertType.INFORMATION, "Library Saved", ButtonType.OK);
			alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			alert.show();
			
		}
		public void quitMenuClick() {
			MainWindow.closeProgram();
		}
		public void prefMenuBtnClick(){
			PreferencesUI.display();
		}
		public void aboutMenuBtnClick() {
			AboutUI.display();
		}
		
		/******** Main menu ********/
		public void homeMenuAction(){
			HomeUI.display();
		}
		public void booksMenuAction(){
			BooksUI.display();
		}
		public void usersMenuAction() {
			UserListUI.display();
		}
		public void openDelayedBooks() {
			DelayedBook.display();
		}	
		public void openRegister() {
			RegisterUserUI.display();
		}
		public void openStats() {
			StatsUI.display();
		}
	}