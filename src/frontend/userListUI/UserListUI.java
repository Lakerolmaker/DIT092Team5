/*
@Author: Nadja Kosir
UI for Directory of users.

1) shows first a table with all users.

options to:
-search for a user,
-to see users with debts,
-to see users with borrowed books.

2) when you click on one user, it opens the UserProfile UI.

*/
package frontend.userListUI;



        import java.io.IOException;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.Map.Entry;
        import java.util.ResourceBundle;

        import frontend.booksUI.BooksUI;
        import frontend.userprofileUI.UserProfileUI;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Scene;
        import javafx.scene.image.Image;
        import javafx.scene.image.ImageView;
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


    @FXML public TableView<User> tableUser;

    @FXML public TableColumn<User, String> nameColumn;
    @FXML public TableColumn<User, String> surnameColumn;
    @FXML public TableColumn<User, String> idColumn;
    @FXML public TableColumn<User, String> amountBooksColumn;
    @FXML public TableColumn<User, String> amountDebtColumn;

    @FXML public Label menuHome;
    @FXML public Label menuBooks;
    @FXML public Label menuUsers;
    @FXML public Label menuDelayed;
    @FXML public Label menuRegisterUser;

    @FXML public TextField searchFieldUser;
    @FXML public Button btnSearch;
    @FXML public Label directoryOfUsers;
    @FXML public Label label2;
    
    @FXML public Button btnShowAllUsers;
    @FXML public Button btnShowUsersWithDebts;
    @FXML public Button btnShowUsersWithBooks;

    @FXML public ImageView logoImage;

    private static String searchFieldString = "";
    private static Scene userListScene;





    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Image logo = new Image("resources/logo.png");
        logoImage.setImage(logo);
        searchFieldUser.setText(searchFieldString);

        menuHome.setId("menuHome");
        menuBooks.setId("menuBooks");
        menuUsers.setId("menuBooks");
        menuDelayed.setId("menuDelayed");

        try {
            initTable();
        }catch (NullPointerException e) {}

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
//_____________________________________________________________
    public void initTable() {                                                       //table - all users
        createTable();
        tableUser.setItems(getUsers());
        tableUser.getColumns().addAll(nameColumn, surnameColumn, idColumn, amountBooksColumn, amountDebtColumn);
    }

    //_____________________________________________________________                     list - all users

    public ObservableList<User> getUsers() {
        ObservableList<User> users = FXCollections.observableArrayList();
            for (User user: MainWindow.lib.getUserList()) {
             users.add(user); }
        return users;
}

//________________________________________________________________________________  list -  users with books

    public ObservableList<User> getUsersWithBooks(){
        ObservableList<User> usersWithBooks = FXCollections.observableArrayList();
        try{
            for (User users: MainWindow.lib.getUserList()){
                if(users.getBookList().size() > 0 ){
                    usersWithBooks.add(users);
                }
            }
        } catch (Exception exception){ }
        return usersWithBooks;
    }
    /*
    public void initTableWithBooks() {

       createTable();
       tableUserWithBooks.setItems(getUsersWithBooks());
       tableUserWithBooks.getColumns().addAll(nameColumn, surnameColumn, idColumn, amountBooksColumn, amountDebtColumn);
     }*/
    //______________________________________________________________________________ list -  users with debts

    public ObservableList<User> getUsersWithDebts(){
        ObservableList<User> usersWithDebts = FXCollections.observableArrayList();
        try{
            for (User users: MainWindow.lib.getUserList()){
                if(users.getDebt() > 0 ){
                    usersWithDebts.add(users);
                }
            }
        } catch (Exception exception){ }
        return usersWithDebts;
    }

//_____________________________________________________________________________    button click functions

    public void BtnShowUsersWithBooksClick(MouseEvent event) {                       // btn: users with books
        // table - with books
        if (event.getButton() == MouseButton.SECONDARY) {
            tableUser.setItems(getUsersWithBooks());
            //tableUser.getColumns().addAll(nameColumn, surnameColumn, idColumn, amountBooksColumn, amountDebtColumn);
        }
    }
    
    public void NewBookButtonClicked(MouseEvent event) {
    	//TODO
    }

    public void btnSearchUserAction() {
    	//TODO
    }


    public void BtnShowUsersWithDebtsClick(MouseEvent event) {                    // btn: users with debts
        // table - with debts
        if (event.getButton() == MouseButton.SECONDARY) {
            tableUser.setItems(getUsersWithDebts());
            //tableUser.getColumns().addAll(nameColumn, surnameColumn, idColumn, amountBooksColumn, amountDebtColumn);
        }
    }


     public void BtnShowAllUsersClick(MouseEvent event){                            // btn: all users
        if (event.getButton() == MouseButton.SECONDARY) {                           // table - all users
            tableUser.setItems(getUsers());
            //tableUser.getColumns().addAll(nameColumn, surnameColumn, idColumn, amountBooksColumn, amountDebtColumn);
        }
        }

    // _____________________________________________________________________    table click functions
    @FXML
    public void clickItem(MouseEvent event) {

        if (event.getClickCount() == 2) {
            User selectedUser = tableUser.getSelectionModel().getSelectedItem(); // Retrieve selected cell
            if (selectedUser != null) {
                goToUserView(selectedUser);
            }
        }
    }

//__________________________________________________________________________     create a table
    public void createTable() {

        // Name column
        nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setMaxWidth(8000);

        // Surname column
        surnameColumn = new TableColumn<>("Surname");
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        surnameColumn.setMaxWidth(9000);

        // ID column

        idColumn = new TableColumn<>("Id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setMaxWidth(2500);
        idColumn.setStyle("-fx-alignment: CENTER;");

        //amount of borrowed books column
        amountBooksColumn = new TableColumn<>("Books borrowed");
        amountBooksColumn.setCellValueFactory(new PropertyValueFactory<>("books borrowed"));
        amountBooksColumn.setStyle("-fx-alignment: CENTER;");

        // amount debt column
        amountDebtColumn = new TableColumn<>("Debt");
        amountDebtColumn.setCellValueFactory(new PropertyValueFactory<>("debt"));
        amountDebtColumn.setStyle("-fx-alignment: CENTER;");
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
    public void goToUserView(User user){
        UserProfileUI.display(user);
    }


}
