package application.tabelle;

import java.sql.Statement;

import application.sql.ConnectMe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class WerkController {

    @FXML
    private TextField name;

    @FXML
    private TextField stadt;

    @FXML
    void abschicken(ActionEvent event) {
    	ConnectMe c = new ConnectMe();
    	String werk ="";
    	String stadtText ="";
    	werk = name.getText();
    	stadtText = stadt.getText();
    	try {
    		Statement stmt = c.getStatement();
    		stmt.executeUpdate("INSERT INTO `plz`.`werke` ( `werkBezeichnung`, `stadt`) VALUES ( '"+werk+"', '"+stadtText+"')");
    		
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Daten wurden erfolgreich hochgeladen");
			/* alert.setContentText("You didn't select a file!"); */
			alert.showAndWait();
    		
    		final Node source = (Node) event.getSource();
	        final Stage stage = (Stage) source.getScene().getWindow();
	        stage.close();
		} catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Fehler");
			alert.setHeaderText(e.getMessage());
			/* alert.setContentText("You didn't select a file!"); */
			alert.showAndWait();
		}
    }

}
