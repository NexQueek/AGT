package application.tabelle;

import java.sql.ResultSet;
import java.sql.Statement;

import org.omg.CORBA.INITIALIZE;

import application.sql.ConnectMe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ClientController {

	@FXML TextField Kalenderwoche;
	@FXML Label client;
	@FXML TableColumn<Liste, String> tag;
	@FXML TableColumn<Liste, String> schichtplaner;
	@FXML TableColumn<Liste, String> uhrzeit;
	@FXML TableColumn<Liste, String> abfahrtort;
	@FXML TableColumn<Liste, String> datum;
	@FXML TableColumn<Liste, String> KW;
	@FXML TableColumn<Liste, String> kurzel;
	@FXML TableColumn<Liste, String> ansprechpartner;
	@FXML TableColumn<Liste, String> bemerkungen;
	@FXML TableColumn<Liste, String> busNr;
	@FXML TableView<Liste> tableEx;
	
    	
		
		
	@FXML public void initialize(){
		busNr.setCellValueFactory(new PropertyValueFactory<>("busNr"));
	   	bemerkungen.setCellValueFactory(new PropertyValueFactory<>("bemerkungen"));
		ansprechpartner.setCellValueFactory(new PropertyValueFactory<>("ansprechpartner"));
		kurzel.setCellValueFactory(new PropertyValueFactory<>("kurzel"));
		KW.setCellValueFactory(new PropertyValueFactory<>("KW"));
		datum.setCellValueFactory(new PropertyValueFactory<>("datum"));
		abfahrtort.setCellValueFactory(new PropertyValueFactory<>("abfahrtort"));
		uhrzeit.setCellValueFactory(new PropertyValueFactory<>("uhrzeit"));
		schichtplaner.setCellValueFactory(new PropertyValueFactory<>("schicht"));
		tag.setCellValueFactory(new PropertyValueFactory<>("unternehmen"));

	}

	@FXML public void suchen(ActionEvent event) {
		
		String clientName = client.getText();
		String week = Kalenderwoche.getText();
		tableEx.getItems().clear();
		ConnectMe c = new ConnectMe();
		Statement stmt = c.getStatement();
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM fahrten a join werke b on a.N_ID = b.N_ID and b.auftraggeber ='"+clientName+"' and a.KW ='"+week+"'" );
			while(rs.next()){
				Liste e = new Liste();
				e.setFahrtId(rs.getInt(1));
				e.setnId(rs.getInt(2));
				e.setBusNr(rs.getString(3));
				e.setBemerkungen(rs.getString(4));
				e.setAnsprechpartner(rs.getString(5));
				e.setKurzel(rs.getString(6));
				e.setKW(rs.getString(7));
				e.setDatum(rs.getDate(8));
				e.setAbfahrtort(rs.getString(9));
				e.setUhrzeit(rs.getString(10));
				e.setSchicht(rs.getString(11));
				e.setTag(rs.getString(12));
				e.setFahrer(rs.getString(13));
				e.setHandynr(rs.getString(14));
				e.setSollBus(rs.getString(15));
				e.setIstBus(rs.getString(16));
				e.setPreisNetto(rs.getString(17));
				e.setBrutto(rs.getString(18));
				e.setEk(rs.getString(19));
				e.setMarge(rs.getString(20));
				e.setGesamt(rs.getString(21));
				e.setStorno(rs.getString(22));
				e.setLinienName(rs.getString(23));
				e.setWerkBez(rs.getString(24));
				e.setFarben(rs.getString(25));
				e.setUnternehmen(rs.getString(26));
				tableEx.getItems().add(e);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
