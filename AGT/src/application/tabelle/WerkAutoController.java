package application.tabelle;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

public class WerkAutoController {

    @FXML
    private TextField linieAnzahl;

    @FXML
    private TextField schichtenFruehHin;

    @FXML
    private ComboBox<?> busseLinie;

    @FXML
    private TextField linieName;

    @FXML
    private ComboBox<String> linieTage;

    @FXML
    private TextField busseUnternehmer;

    @FXML
    private TextField busseArt;

    @FXML
    private TextField linieUeber;

    @FXML
    private TextField schichtenSpaetZu;

    @FXML
    private ComboBox<?> busseBus;

    @FXML
    private TextField werkAnzahl;

    @FXML
    private ComboBox<?> schichtenLinie;

    @FXML
    private TextField linieNach;

    @FXML
    private ComboBox<?> schichtenBus;

    @FXML
    private TextField linieVon;

    @FXML
    private TextField schichtenFruehTz;

    @FXML
    private ColorPicker linieFarbe;

    @FXML
    private TextField schichtenSpaetHin;

    @FXML
    private TextField schichtenSpaetTz;

    @FXML
    private ComboBox<String> linieLinie;

    @FXML
    private TextField busseFahrer;

    @FXML
    private TextField bussePreis;

    @FXML
    private TextField schichtenNachtTz;

    @FXML
    private TextField schichtenFruehZu;

    @FXML
    private Label linieBeispiel;

    @FXML
    private TextField busseGroesse;

    @FXML
    private TextField schichtenNachtHin;

    @FXML
    private TextField schichtenNachtZu;

    @FXML
    private TextField busseKennzeichen;

	@FXML Tab linie;

	@FXML Tab busse;

	@FXML Tab schichten;
	 @FXML
	    void initialize() {
		 
	 }

    @FXML
    void linieRefresh(ActionEvent event) {
    	int auswahlNummer = -1;
    	auswahlNummer = getAuswahlNummer();
    	if(auswahlNummer==-1){
    		
    	}else{
    		if(Werk.werk.getLinieListe().get(auswahlNummer) == null){
        		
        	}else{
        		if(Werk.werk.getLinieListe().get(auswahlNummer).getN_ID()==null||Werk.werk.getLinieListe().get(auswahlNummer).getN_ID().equals("")){
        			fensterNull();
        		}else{
        			linieAnzahl.setText(Werk.werk.getLinieListe().get(auswahlNummer).getAnzahlDerBusse());
            		linieFarbe.setValue(Werk.werk.getLinieListe().get(auswahlNummer).getColor());
            		
            		linieVon.setText(Werk.werk.getLinieListe().get(auswahlNummer).getVon());
            		linieUeber.setText(Werk.werk.getLinieListe().get(auswahlNummer).getUeber());
            		linieNach.setText(Werk.werk.getLinieListe().get(auswahlNummer).getNach());
            		linieTage.setValue(Werk.werk.getLinieListe().get(auswahlNummer).getWannBisWann());
            		linieBeispiel.setStyle("-fx-background-color: "+ Werk.werk.getLinieListe().get(auswahlNummer).getColor().toString().substring(2));
        		}
        		
        	}
        	
    	}
    	
    	
    }
    //Enkapselt da meherer Funktionen diese Methode nutzen 
    //Methode gibt die nummer der Linie 
    int getAuswahlNummer(){
    	int lange = Werk.werk.getLinieListe().size();
    	int auswahlNummer = -1;
    	for (int i = 0; i < lange; i++) {
    		
    		//Wenn name gleich ist
    		if( linieLinie.getSelectionModel().getSelectedItem().equals(Werk.werk.getLinieListe().get(i).getName())){
    			auswahlNummer = i;
    		}
		}
    	return auswahlNummer;
    }
    @FXML
    void linieSpeichern(ActionEvent event) {
    	//Auf welcher Linie
    	int auswahlNummer = getAuswahlNummer();
    	if(auswahlNummer == -1){
    		Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Fehler");
			//alert.setHeaderText(e.getMessage());
			alert.setContentText("Fehler");
			alert.showAndWait();
    	}else{
    		
    		Werk.werk.getLinieListe().get(auswahlNummer).setAnzahlDerBusse(linieAnzahl.getText());
    		Werk.werk.getLinieListe().get(auswahlNummer).setFarbe(linieFarbe.getValue().toString().substring(2));
    		Werk.werk.getLinieListe().get(auswahlNummer).setColor(linieFarbe.getValue());
    		Werk.werk.getLinieListe().get(auswahlNummer).setN_ID(Werk.werk.getnID());;
    		Werk.werk.getLinieListe().get(auswahlNummer).setVon(linieVon.getText());
    		Werk.werk.getLinieListe().get(auswahlNummer).setUeber(linieUeber.getText());
    		Werk.werk.getLinieListe().get(auswahlNummer).setNach(linieNach.getText());
    		Werk.werk.getLinieListe().get(auswahlNummer).setWannBisWann(linieTage.getSelectionModel().getSelectedItem());  		
    		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Erfolg ");
			alert.setHeaderText("Daten für Linie" + (auswahlNummer+1) + " gepseichert");
			/* alert.setContentText("You didn't select a file!"); */
			alert.showAndWait();
			fensterNull();
			linieLinie.setValue("");
    	}
    	//Zum testen
		//System.out.println(Werk.werk.getLinieListe().get(auswahlNummer).getUeber());
		//System.out.println(Werk.werk.getLinieListe().get(auswahlNummer).getN_ID());
		
    	
    }
    void fensterNull(){
    	linieAnzahl.setText("");
		linieFarbe.setValue(null);
		linieBeispiel.setStyle(null);                     
		linieVon.setText("");
		linieUeber.setText("");
		linieNach.setText("");
		linieTage.setValue("");
		
		
    }

    @FXML
    void busseLinieRefresh(ActionEvent event) {

    }

    @FXML
    void busseSpeichern(ActionEvent event) {

    }

    @FXML
    void busseRefresh(ActionEvent event) {

    }

    @FXML
    void schichtenLinieRefresh(ActionEvent event) {

    }

    @FXML
    void schichtenBusRefresh(ActionEvent event) {

    }

	@FXML public void werkAbsenden(ActionEvent event) {
		if(werkAnzahl.getText().equals("")){
			
		}else{
			try{
				Integer.parseInt(werkAnzahl.getText());
			}catch(NumberFormatException e){
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Fehler");
				alert.setHeaderText(e.getMessage());
				/* alert.setContentText("You didn't select a file!"); */
				alert.showAndWait();
			}
			werkAnzahl.getText();
			if(Werk.werk.getLinieListe()!=null){
				Werk.werk.getLinieListe().clear();
			}
			
			for (int i = 0; i < Integer.parseInt(werkAnzahl.getText()); i++) {
				//Speicherung der Linien in dem Werk.werk
				Linie line = new Linie();
				line.setName("Linie "+(i+1));
				Werk.werk.getLinieListe().add(line);
			}
			System.out.println(Werk.werk.getLinieListe().size());
			linie.setDisable(false);
		}
		initLinien();
	}

	private void initLinien() {
		//Liste für die Dropdown
		ObservableList<String> listeMoglichkeit = FXCollections.observableArrayList();
		int laenge=0;
		//!!!Moglicher Fehler wenn keine Linie eingegeben worden sind 
		try {
			laenge = Werk.werk.getLinieListe().size();
		} catch (Exception e) {
			e.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Fehler");
			alert.setHeaderText(e.getMessage());
			alert.setContentText("Bitte eine Zahl eingeben");
			alert.showAndWait();
		}
		
		
		for (int i = 0; i < laenge; i++) {
			listeMoglichkeit.add(Werk.werk.getLinieListe().get(i).getName());
		}
		linieLinie.setItems(listeMoglichkeit);
		
	}

	@FXML public void linieFarbeAuswahl(ActionEvent event) {
		System.out.println(linieFarbe.getValue());
		linieBeispiel.setStyle("-fx-background-color: "+ linieFarbe.getValue().toString().substring(2));
		
	}

 
}
