package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			root.getStylesheets().add(
				    getClass().getResource("application.css").toExternalForm());
            Stage stage = primaryStage;
            stage.setTitle("Software Engineering Team - SET");
            Image image = new Image(getClass().getResourceAsStream("15.jpg"));
            
            
            
            
            Scene scene = new Scene(root);
            stage.getIcons().add(image);
            stage.setScene(scene);
            
            stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
