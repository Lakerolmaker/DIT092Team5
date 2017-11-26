package test.gui.javafx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.*;

/**
 * Just a quick test using javaFX instead
 *
 */

public class TestWithJavaFXmain extends Application{
	Stage window;
	Scene scene;
	Scene scene2;
	Button buttonOk;
	Button searchBtn;
	TableView<Book> table;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("TableView Example");
		
	    Text scenetitle = new Text("Library");
	    scenetitle.setId("logo-text");
		
		// Name column
		TableColumn<Book, String> nameColumn = new TableColumn<>("Title");
		nameColumn.setMinWidth(238);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		// Author column
		TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
		authorColumn.setMinWidth(200);
		authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
		// Year column
		TableColumn<Book, String> yearColumn = new TableColumn<>("Year");
		yearColumn.setMinWidth(120);
		yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
		// Category column
		TableColumn<Book, String> categoryColumn = new TableColumn<>("Category");
		categoryColumn.setMinWidth(120);
		categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
		// Shelf column
		TableColumn<Book, String> shelfColumn = new TableColumn<>("Shelf");
		shelfColumn.setMinWidth(80);
		shelfColumn.setCellValueFactory(new PropertyValueFactory<>("shelf"));
		
		// Table initialation
		table = new TableView<>();
		table.setId("booktable");
		table.setItems(getBooks());
		table.getColumns().addAll(nameColumn, authorColumn, yearColumn, categoryColumn, shelfColumn);
		
		// SearchField
		TextField searchField = new TextField();
		searchField.setPrefSize(550, 35);
		// SearchButton
		searchBtn = new Button("Search");
		searchBtn.setPrefSize(100,37);
		searchBtn.setOnAction(e -> searchClicked());
		
		
		// Bottom Right button
		buttonOk = new Button("Ok");
		buttonOk.setPrefSize(100, 37);
		buttonOk.setOnAction(e -> window.setScene(scene2));
		
		/***************** LAYOUT ***************************/
		
		// Top Layout
		HBox topBox = new HBox();
		topBox.setSpacing(20);
		topBox.setPadding(new Insets(50, 0, 0, -50));
		topBox.getChildren().addAll(searchField,searchBtn);

		VBox topLayout = new VBox();
		topLayout.setPadding(new Insets(50, 0, 0, 100));
		topLayout.getChildren().addAll(scenetitle, topBox);
		
		// Center Layout
		VBox centerBox = new VBox();
		centerBox.setPadding(new Insets(20, 50, 50, 50));
		centerBox.getChildren().addAll(table);
		centerBox.setPrefSize(400, 200);
		
		// Bottom layout
		BorderPane bottomBorder = new BorderPane();
		bottomBorder.setPadding(new Insets(20, 0, 20, 20));
		
		
		HBox botButtons = new HBox();
		botButtons.setSpacing(10);
		botButtons.setPadding(new Insets(0, 50, 50, 0));
		botButtons.getChildren().add(buttonOk);
		botButtons.setAlignment(Pos.CENTER_RIGHT);
		

		// Window layout
		BorderPane borderPane = new BorderPane();
		// TOP
		borderPane.setTop(topLayout);
		// CENTER
		borderPane.setCenter(centerBox);
		borderPane.setMaxSize(400, 300);
		borderPane.setCenter(centerBox);
		// BOTTOM
		borderPane.setBottom(botButtons);
		
		
		// Second view
		BorderPane borderPane2 = new BorderPane();
		scene2 = new Scene(borderPane2, 1000, 800);
	    scene2.getStylesheets().add(TestWithJavaFXmain.class.getResource("Library.css").toExternalForm()); // Link to css file
		
		scene = new Scene(borderPane, 1000, 800);
	    scene.getStylesheets().add(TestWithJavaFXmain.class.getResource("Library.css").toExternalForm()); // Link to css file
		window.setScene(scene);
		window.show();
		
		window.setOnCloseRequest(e -> {
			e.consume(); // Take care of the close event so it wont close anyways
			closeProgram();
		});
		
	}
	
	public ObservableList<Book> getBooks() {
		ObservableList<Book> products = FXCollections.observableArrayList();
		products.add(new Book(999999991, "Book1", "Author1", 2008, "Horror", 2));
		products.add(new Book(999999992, "Book2", "Author2", 1948, "Drama", 1));
		products.add(new Book(999999993, "Book3", "Author3", 2028, "Comedy", 4));
		products.add(new Book(999999994, "Book4", "Author4", 2017, "Documentary", 3));
		products.add(new Book(999999995, "Book5", "Author5", 2018, "Fiction", 5));
		return products;
	}
	
	private void searchClicked(){
		System.out.println("Search button clicked");
		
	}
	
	public void closeProgram(){
		boolean answer = ConfirmBox.display("Exit Program", "Sure you want to exit?");
		if (answer == true) {
			System.out.println("Saved all data \nExiting");
			window.close();
		}
	}
	
}
