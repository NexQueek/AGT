package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class StageMe {
	public Stage getStage(String fxml, String title){
		Parent root;
		try {
			
			
			root = FXMLLoader.load(getClass().getResource(fxml));
			Stage primaryStage = new Stage();
			primaryStage.setScene(new Scene(root));
			root.getStylesheets().add(
				    getClass().getResource("application.css").toExternalForm());
			Image image = new Image(getClass().getResourceAsStream("12.png"));
			primaryStage.getIcons().add(image);
			primaryStage.setTitle(title);
			return primaryStage;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
