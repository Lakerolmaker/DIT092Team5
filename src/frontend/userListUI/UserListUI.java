package frontend.userListUI;


        import java.io.IOException;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.Map.Entry;
        import java.util.ResourceBundle;

        import frontend.booksUI.BooksUI;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Scene;
        import javafx.scene.layout.HBox;
        import javafx.scene.layout.VBox;
        import javafx.scene.text.*;
        import frontend.newBookUI.*;
        import frontend.delayedBooksUI.*;
        import frontend.homeUI.HomeUI;
        import frontend.MainWindow;
        import frontend.registerUserUI.*;
        import frontend.userListUI.*;
        import frontend.bookViewUI.*;
        import javafx.beans.property.SimpleStringProperty;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
        import javafx.scene.control.Button;
        import javafx.scene.control.CheckBox;
        import javafx.scene.control.ContextMenu;
        import javafx.scene.control.Label;
        import javafx.scene.control.ListView;
        import javafx.scene.control.MenuItem;
        import javafx.scene.control.TableColumn;
        import javafx.scene.control.TableView;
        import javafx.scene.control.TextField;
        import javafx.scene.control.cell.PropertyValueFactory;
        import javafx.scene.input.MouseButton;
        import javafx.scene.input.MouseEvent;
        import program.*;



public class UserListUI implements Initializable {
    //private static VBox userListView;
    private static Scene userListScene;


    @FXML public TableView<User> tableUser;
    @FXML public TableColumn<User, String> nameColumn;
    @FXML public TableColumn<User, String> surnameColumn;
    @FXML public Label menuHome;
    @FXML public Label menuBooks;
    @FXML public Label menuUsers;
    @FXML public Label menuDelayed;
    @FXML public Label menuRegisterUser;
    @FXML public TextField searchFieldUser;
    @FXML public Button BtnSearch;
    @FXML public Label label1;
    @FXML public Label label2;
    @FXML public Button BtnShowAllUsers;
    @FXML public Button BtnShowUsersWithDebts;
    @FXML public Button BtnShowUsersWithBooks;

    private static String searchFieldString = "";




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        searchFieldUser.setText(searchFieldString);

        menuHome.setId("menuHome");
        menuBooks.setId("menuBooks");
        menuUsers.setId("menuBooks");
        menuDelayed.setId("menuDelayed");




    }

    public static void display() {

        try {
            Class<UserListUI> context = UserListUI.class;
            VBox userListView = (VBox)FXMLLoader.load(context.getResource("UserListFxml.fxml"));
            userListScene = new Scene(userListView,1192,650);
            userListScene.getStylesheets().add(MainWindow.css);

            MainWindow.window.setScene(userListScene);
            MainWindow.window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    /******** File MENU ********/
    public void newBook(){
        NewBookUI.display();
    }
    public void quitMenuClick() {
        MainWindow.closeProgram();
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
    public void goToBookView(Book book){
        BookViewUI.display(book);
    }
    public void openDelayedBooks() {
        DelayedBook.display();
    }
    public void openRegister() {
        RegisterUserUI.display();
    }

}
