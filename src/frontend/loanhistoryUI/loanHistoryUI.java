package frontend.loanhistoryUI;

import frontend.MainWindow;
import frontend.bookViewUI.BookViewUI;
import frontend.booksUI.BooksUI;
import frontend.delayedBooksUI.DelayedBook;
import frontend.homeUI.HomeUI;
import frontend.newBookUI.NewBookUI;
import frontend.registerUserUI.RegisterUserUI;
import frontend.statsUI.StatsUI;
import frontend.userListUI.UserListUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import program.Book;
import program.LoanInstance;
import program.User;
import program.UserBookList;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class loanHistoryUI implements Initializable {
    private static VBox root;
    private static Scene historyScene;
    static Stage window;
    private static User u;

    @FXML private TableView<UserBookList> bookHistoryView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        // load the list of borrowed books

        try {
            ObservableList<UserBookList> b = FXCollections.observableArrayList();
            for(LoanInstance book : u.getBookHistoryList()) {
                b.add(new UserBookList(book));
            }

            bookHistoryView.setItems(b);
        } catch (Exception e) {
            e.printStackTrace();
        }


        // table of borrowed books
        TableColumn titleCol = new TableColumn("Title");
        titleCol.setMinWidth(200);
        titleCol.setCellValueFactory( new PropertyValueFactory<UserBookList, String>("title"));

        TableColumn authorCol = new TableColumn("Author");
        authorCol.setMinWidth(120);
        authorCol.setCellValueFactory(new PropertyValueFactory<UserBookList, String>("author"));

        TableColumn dateCol = new TableColumn("Date");
        dateCol.setMinWidth(50);
        dateCol.setCellValueFactory(new PropertyValueFactory<UserBookList, LocalDate>("date"));

        bookHistoryView.getColumns().addAll(titleCol, authorCol, dateCol);
    }

    public static void display(User user) {
        u = user;
        Class context = frontend.loanhistoryUI.loanHistoryUI.class;
        window = new Stage();
        try {
            // This is the scene that is going to be shown inside the window ( Main window in this case )
            VBox homeView = (VBox) FXMLLoader.load(context.getResource("loanHistoryUI.fxml"));
            historyScene = new Scene(homeView);
            historyScene.getStylesheets().add(MainWindow.css);

            // Set the main window to show this scene
            window.setScene(historyScene);
            window.show();
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
    public void openStats() {
        StatsUI.display();
    }

}
