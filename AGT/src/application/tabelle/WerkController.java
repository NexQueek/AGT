package application.tabelle;

import java.sql.Statement;

import application.sql.ConnectMe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class WerkController {

    @FXML
    private TextField name;

    @FXML
    private TextField stadt;

	@FXML ComboBox<String> dropdownWerk;

	@FXML TextField name1;

	@FXML TextField stadt1;
	ObservableList<String> listeWerke = FXCollections.observableArrayList();

	@FXML ComboBox<String> auftraggeber;
    @FXML
    void initialize() {

    	auftraggeber.getItems().add("Adeco");
    	auftraggeber.getItems().add("GI");
    	auftraggeber.getItems().add("Amazon");
    	
    }

    @FXML
    void abschicken(ActionEvent event) {
    	ConnectMe c = new ConnectMe();
    	String werk ="";
    	String stadtText ="";
    	String auftragGeber ="";
    	werk = name.getText();
    	stadtText = stadt.getText();
    	auftragGeber = auftraggeber.getSelectionModel().getSelectedItem();
    	try {
    		Statement stmt = c.getStatement();
    		stmt.executeUpdate("INSERT INTO `plz`.`werke` ( `werkBezeichnung`, `stadt`, `auftraggeber`) VALUES ( '"+werk+"', '"+stadtText+"', '"+auftragGeber+"')");
    		
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
