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

    // Initialize table
    public void initTable() {

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


       tableUser.setItems(getUsers());
        tableUser.getColumns().addAll(nameColumn, surnameColumn, idColumn, amountBooksColumn, amountDebtColumn);

    }

    // Return list of users
    public ObservableList<User> getUsers() {
        ObservableList<User> users = FXCollections.observableArrayList();
        for (User user: MainWindow.lib.getUserList()) {
            users.add(user);
        }

        return users;
    }

//TODO!!!
    public ObservableList<User> getUsersWithBooks(){
        ObservableList<User> usersWithBooks = FXCollections.observableArrayList();
        for (User users: MainWindow.lib.getUserList()){
            if(users.getBookList.size >0 ){

            }
        }

    }

    //throw away
    // Return list of users
    /*public void getUsers() {
        ArrayList<User> listUsers = new ArrayList();
        for (User user : MainWindow.lib.getUserList ()){

            listUsers.add(user);
        }
    }
*/
    // table click functions
    @FXML
    public void clickItem(MouseEvent event) {

        if (event.getClickCount() == 2) {
            User selectedUser = tableUser.getSelectionModel().getSelectedItem(); // Retrieve selected cell
            if (selectedUser != null) {
                goToUserView(selectedUser);
            }
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
    public void goToUserView(User user){
        UserProfileUI.display(user);
    }

}
