package application.bewertung;


import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import application.Benutzer;
import application.dispo.UidObject;
import application.sql.ConnectMe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;

public class BewertungController {

    @FXML
    private Label unternehmen;

    @FXML
    private ComboBox<Integer> erreichbarkeitEintrag;

    @FXML
    private ComboBox<String> thematik;
    

    @FXML
    private TextField rechnungsnummer;

    @FXML
    private ComboBox<Integer> punktlichkeitEintrag;

    @FXML
    private ComboBox<Integer> zuverlässigkeitEintrag;

    @FXML
    private ComboBox<Integer> serviceEintrag;
    ObservableList<String> options = 
		    FXCollections.observableArrayList(
		        "Service",
		        "Sauberkeit",
		        "Zuverlässlichkeit",
		        "Erreichbarkeit",
		        "Sonstiges"
		    );
    ObservableList<Integer> noten = 
		    FXCollections.observableArrayList(
		        1,
		        2,
		        3,
		        4,
		        5,
		        6
		    );

	@FXML Button farb;

	@FXML TextArea loesung;



	@FXML TextArea beschrei;
    @FXML
    void initialize() {
    	unternehmen.setText(UidObject.unternehmen.getName());
    	thematik.setItems(options);
    	erreichbarkeitEintrag.setItems(noten);
    	punktlichkeitEintrag.setItems(noten);
    	zuverlässigkeitEintrag.setItems(noten);
    	serviceEintrag.setItems(noten);
    	farb.setVisible(false);
    }

    @FXML
    void hochladen(ActionEvent event) {

    }

    @FXML
    void bewertenLassen(ActionEvent event) {
    	 int service = 0;
    	 int punkt = 0;
    	 int verlass = 0;
    	 int erreichbar = 0;
    	try{
    		service = serviceEintrag.getSelectionModel().getSelectedItem();
    	
    	    
    	   
    	    
    	}catch(Exception e){
    		System.out.println(e);
    	}
    	try {
    		punkt = punktlichkeitEintrag.getSelectionModel().getSelectedItem();
		} catch (Exception e) {
			// TODO: handle exception
		}
    	try {
    		 verlass = zuverlässigkeitEintrag.getSelectionModel().getSelectedItem();
		} catch (Exception e) {
			// TODO: handle exception
		}
    	try {
    		 erreichbar = erreichbarkeitEintrag.getSelectionModel().getSelectedItem();
		} catch (Exception e) {
			// TODO: handle exception
		}
    	if(service==0||punkt==0||verlass==0||erreichbar==0){
    		if(service == 0){
    			serviceEintrag.setStyle("-fx-background-color: red");
    		}
    		if(punkt == 0){
    			punktlichkeitEintrag.setStyle("-fx-background-color: red");
    		}
    		if (verlass == 0){
    			zuverlässigkeitEintrag.setStyle("-fx-background-color: red");
    		}
    		if(erreichbar == 0){
    			erreichbarkeitEintrag.setStyle("-fx-background-color: red");
    		}
    		farb.setVisible(true);
    	}else{
    		hochlad(erreichbar, service, verlass, punkt);
        	System.out.println(service);
        	final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
    	}
    	
	    
    }

	@FXML public void reset(ActionEvent event) {
		serviceEintrag.setStyle("-fx-background-color: lightgray");
		punktlichkeitEintrag.setStyle("-fx-background-color: lightgray");
		zuverlässigkeitEintrag.setStyle("-fx-background-color: lightgray");
		erreichbarkeitEintrag.setStyle("-fx-background-color: lightgray");
		farb.setVisible(false);
	}
	void hochlad(int erreichbar, int service, int verlass, int sauber){
		Date date = new Date();
		String thema = thematik.getSelectionModel().getSelectedItem();
		String beschreibung = beschrei.getText();
		String losung = loesung.getText(); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String datumHeute = sdf.format(date);
		String sqlBefehl = "Insert into plz.unverify( U_ID,bewertetVon, bewertetAm, rechnungsnummer,thematik"
				+ ",erreichbarkeit , zuverlassigkeit, service, sauberkeit,beschreibung, loesung, bild) "
						+ "Values('"+UidObject.unternehmen.getUid()+"', '"+Benutzer.benutz.get(0).getName()+"', '"+datumHeute+"', '"+rechnungsnummer.getText()+"','"
								+thema+"', "+erreichbar+", "+verlass+", "+service+", "+sauber+", '"+beschreibung+"', '"+losung+"','placeholder' )";
		ConnectMe c = new ConnectMe();
		Statement stmt = c.getStatement();
		try {
			stmt.executeUpdate(sqlBefehl);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
