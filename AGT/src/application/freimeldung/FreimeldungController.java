package application.freimeldung;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;


import application.Benutzer;
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
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class FreimeldungController {

    @FXML
    private TextField ort;

    @FXML
    private DatePicker datum_von;

    @FXML
    private ComboBox<String> busmodel;

    @FXML
    private TextField unternehmen;

    @FXML
    private TextField busplaetze;

    @FXML
    private TextField r_nummer;

    @FXML
    private TextField plz;

    @FXML
    private DatePicker datum_bis;
    
    ObservableList<String> options = 
		    FXCollections.observableArrayList(
		       "Reisebus",
		       "Midibus",
		       "Kleinbus",
		       "MiniVan",
		       "Doppeldecker"
		    );
    @FXML
    void eintragen(ActionEvent event) {
    	ConnectMe c = new ConnectMe();
    	Statement stmt = c.getStatement();
    	Benutzer.benutz.get(0).getName();
    	
    	Date dateVon = Date.valueOf(datum_von.getValue());
    	Date dateBis = Date.valueOf(datum_bis.getValue());
    	java.util.Date datumAktuell = dateVon;
    	
    	
    	SimpleDateFormat sdfSQL = new SimpleDateFormat("yyyy-MM-dd");
    	
    	String sql = "INSERT INTO freimeldung(eingetragenVon, rechnungsnummer, plz, Ort, unternehmen, datumVon, datumBis, busmodel, busplatz) VALUES"
        			+ "('"+Benutzer.benutz.get(0).getName() + "', '"+r_nummer.getText()+"', '"+plz.getText()+"','"+ort.getText()+"',"
        					+ " '"+unternehmen.getText()+"','"+sdfSQL.format(datumAktuell)+"', '"+sdfSQL.format(dateBis)+"', '"+busmodel.getSelectionModel().getSelectedItem()+"',"
        							+ " '"+busplaetze.getText()+"');";
       
    	try {
       		
       		 stmt.executeUpdate(sql);
       		 
       		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Aktion Erfolgreich!");
			alert.setContentText("Freimeldung wurde angelegt");
			alert.showAndWait();
			final Node source = (Node) event.getSource();
	        final Stage stage = (Stage) source.getScene().getWindow();
	        stage.close();
    	
       	} catch (SQLException e) {
    	// TODO Auto-generated catch block
       		e.printStackTrace();
    	}
        
            //Notaustieg bei 9 Tagen
            
    	
    	//Fügt neue Freimeldung hinzu
    	
    	
    	
    }
    @FXML
    void initialize(){
    	busmodel.setItems(options);
    }

}
