package application.tabelle;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import application.sql.ConnectMe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
	ObservableList<String> listeWerke = FXCollections.observableArrayList();;
    @FXML
    void initialize() {
    	//Bestehende Werke herunterladen
    	ConnectMe c = new ConnectMe();
    	String sql ="Select * From plz.werke ";
    	Werk.werkeList.clear();
    	try {
			Statement stmt = c.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Werk a = new Werk();
				a.setnID(rs.getString(1));
				a.setWerkBezeichnung(rs.getString(2));
				a.setStadt(rs.getString(3));
				Werk.werkeList.add(a);
				listeWerke.add(a.getWerkBezeichnung());
			}
			dropdownWerk.setItems(listeWerke);
			
		} catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Fehler");
			alert.setHeaderText(e.getMessage());
			e.printStackTrace();
			/* alert.setContentText("You didn't select a file!"); */
			alert.showAndWait();
		}finally {
			c.closeConnection();
		}

    }

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

	@FXML public void werkAutomatisieren(ActionEvent event) {
		Stage primaryStage = new Stage();
		Parent root;
		String werkBez;
		werkBez = dropdownWerk.getSelectionModel().getSelectedItem();
		ArrayList<Werk> werke = Werk.werkeList;
		Werk.werk = null;
		//Foreach to get the chosen werk
		for (Werk werk : werke) {
			if(werk.getWerkBezeichnung().equals(werkBez)){
				Werk.werk = werk;
			}
		}
		
		try {
			root = FXMLLoader.load(getClass().getResource("WerkAuto.fxml"));
			root.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());

			primaryStage.setTitle("Werk automatisieren ");
			primaryStage.setResizable(false);
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
