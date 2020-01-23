package application.tabelle;

import java.sql.ResultSet;
import java.sql.Statement;

import application.sql.ConnectMe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FahrtController {

	@FXML
	private DatePicker datum;

	@FXML
	private TextField istbus;

	@FXML
	private TextField fahrer;

	@FXML
	private TextField kurzel;

	@FXML
	private TextField ansprechpartner;

	@FXML
	private TextField uhrzeit;

	@FXML
	private TextField EK;

	@FXML
	private TextField abfahrtort;

	@FXML
	private TextField KW;

	@FXML
	private TextField handy;

	@FXML
	private ComboBox<String> werk;

	@FXML
	private TextField preisBrutto;

	@FXML
	private TextField marge;

	@FXML
	private TextField preisNetto;

	@FXML
	private TextField bemerkung;

	@FXML
	private TextField schicht;

	@FXML
	private TextField tag;

	@FXML
	private TextField gesamt;

	@FXML
	private TextField sollBus;

	@FXML
	private TextField busnr;


	@FXML
    void initialize() {
		ObservableList<String> Werke = FXCollections.observableArrayList();
		ConnectMe c = new ConnectMe();
		Statement stmt = c.getStatement();
		try {
			ResultSet rs = stmt.executeQuery("Select werkBezeichnung from plz.werke");
			while(rs.next()){
				Werke.add(rs.getString(1));
			}
			werk.setItems(Werke);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
		c.closeConnection();	
		}
	}

	@FXML
	void sende(ActionEvent event) {
		ConnectMe c = new ConnectMe();
		String nId= "";
		Statement stmt = c.getStatement();
		
		try {
			ResultSet rs2 = stmt.executeQuery("Select N_ID From werke Where werkBezeichnung= '"+werk.getSelectionModel().getSelectedItem()+"'");
			while (rs2.next()) {
				nId = rs2.getString(1);
				
			}
			String sql = "INSERT INTO `plz`.`fahrten` (`N_ID`, `busnr`, `bemerkung`, `ansprechpartner`,"
					+ " `kurzel`, `KW`, `datum`, `abfahrtort`, `uhrzeit`, `schicht`, `tag`, `fahrer`,"
					+ " `handynr`, `sollBus`, `istBus`, `preisNetto`, `brutto`, `ek`, `marge`, `gesamt`)"
					+ " VALUES ('"+nId+"', '"+busnr.getText()+"',"
					+ " '"+bemerkung.getText()+"', '"+ansprechpartner.getText()+"',"
					+ " '"+kurzel.getText()+"', '"+KW.getText()+"', '"+datum.getValue()+"',"
					+ " '"+abfahrtort.getText()+"', '"+uhrzeit.getText()+"', '"+schicht.getText()+"',"
					+ " '"+tag.getText()+"', '"+fahrer.getText()+"', '"+handy.getText()+"',"
					+ " '"+sollBus.getText()+"', '"+istbus.getText()+"',"
					+ " '"+preisNetto.getText()+"', '"+preisBrutto.getText()+"', '"+EK.getText()+"',"
					+ " '"+marge.getText()+"', '"+gesamt.getText()+"')";

			stmt.executeUpdate(sql);
			final Node source = (Node) event.getSource();
	        final Stage stage = (Stage) source.getScene().getWindow();
	        stage.close();
			
		} catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Fehler");
			alert.setHeaderText(e.getMessage());
			/* alert.setContentText("You didn't select a file!"); */
			alert.showAndWait();
		} finally {
			c.closeConnection();
		}
	}

}
