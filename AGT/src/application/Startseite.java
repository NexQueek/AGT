package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class Startseite extends AnchorPane{
public Startseite(){
	 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Main.fxml"));

     fxmlLoader.setRoot(this);
     fxmlLoader.setController(this);

     try {
         fxmlLoader.load();
     } catch (IOException e) {
         e.printStackTrace();
     }
}
}
