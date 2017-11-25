package test.gui.javafx;

import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {
	
	static boolean answer;
	
	public static boolean display(String title, String message) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL); // Block other windows as long this one is open
		window.setTitle(title);
		window.setMinWidth(300);
		window.setMinHeight(250);
		Label label = new Label();
		label.setText(message);
		
		// Create two buttons - yes / now
		Button yesButton = new Button("Yes");
		Button noButton = new Button("No");
		
		yesButton.setOnAction(e -> {
			answer = true;
			window.close();
		});
		
		noButton.setOnAction(e -> {
			answer = false;
			window.close();
		});
		
		
		
		HBox layout = new HBox(10);
		layout.setSpacing(10);
		layout.getChildren().addAll(yesButton, noButton);
		layout.setAlignment(Pos.CENTER);
		VBox vBox = new VBox(10);
		vBox.setAlignment(Pos.CENTER);
		vBox.getChildren().addAll(label, layout);
		
		Scene scene = new Scene(vBox);
		window.setScene(scene);
		window.showAndWait(); // Block other windows as long this one is open
		
		return answer;
	}

}
