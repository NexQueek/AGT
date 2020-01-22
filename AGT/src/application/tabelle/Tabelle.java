package application.tabelle;


import java.sql.Date;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableView;


public class Tabelle {
	
	@FXML TableColumn<?, ?> bemerkung;
	@FXML TextField kalenderwoche;
	@FXML ChoiceBox <?>dropdownWerk;
	@FXML TableColumn<?, ?> busNr;
	@FXML TableColumn<?, ?> bemerkungen;
	@FXML TableColumn<?, ?> ansprechpartner;
	@FXML TableColumn<?, ?> kurzel;
	@FXML TableColumn<?, ?> KW;
	@FXML TableColumn<?, ?> datum;
	@FXML TableColumn<?, ?> abfahrtort;
	@FXML TableColumn<?, ?> uhrzeit;
	@FXML TableColumn<Liste, TextField> schichtplaner;
	@FXML TableColumn<?, ?> tag;
	@FXML TableColumn<?, ?> fahrer;
	@FXML TableColumn<?, ?> handynr;
	@FXML TableColumn<?, ?> sollBus;
	@FXML TableColumn<?, ?> istBus;
	@FXML TableColumn<?, ?> preisNetto;
	@FXML TableColumn<?, ?> brutto;
	@FXML TableColumn<?, ?> ek;
	@FXML TableColumn<?, ?> marge;
	@FXML TableColumn<?, ?> gesamt;
	@FXML TableView<Liste> tabelleEx;
	ObservableList<Liste> listListe = FXCollections.observableArrayList();
	
	

	@FXML
	    void initialize() {
			//fid.setCellValueFactory(new PropertyValueFactory<>("fid"));
		    //plz.setCellValueFactory(new PropertyValueFactory<>("plz"));
	        //ort.setCellValueFactory(new PropertyValueFactory<>("Ort"));
		    //r_nummer.setCellValueFactory(new PropertyValueFactory<>("rechnungsnummer"));
		    //unternehmen.setCellValueFactory(new PropertyValueFactory<>("unternehmen"));
		    //datumvon.setCellValueFactory(new PropertyValueFactory<>("datumVon"));
		    //datumbis.setCellValueFactory(new PropertyValueFactory<>("datumBis"));
		    //busmodel.setCellValueFactory(new PropertyValueFactory<>("busModel"));
	        //busplätze.setCellValueFactory(new PropertyValueFactory<>("busPlatze"));
		    //eingetragenvon.setCellValueFactory(new PropertyValueFactory<>("eingetragen"));
		    //methodenImplementieren();
		 
		    
		busNr.setCellValueFactory(new PropertyValueFactory<>("busNr"));           
		bemerkungen.setCellValueFactory(new PropertyValueFactory<>("bemerkungen"));     
		ansprechpartner.setCellValueFactory(new PropertyValueFactory<>("ansprechpartner")); 
		kurzel.setCellValueFactory(new PropertyValueFactory<>("kurzel"));          
		KW.setCellValueFactory(new PropertyValueFactory<>("KW"));              
		datum.setCellValueFactory(new PropertyValueFactory<>("datum"));           
		abfahrtort.setCellValueFactory(new PropertyValueFactory<>("abfahrtort"));      
		uhrzeit.setCellValueFactory(new PropertyValueFactory<>("uhrzeit"));         
		schichtplaner.setCellValueFactory(new PropertyValueFactory<>("schichtText"));;   
		tag.setCellValueFactory(new PropertyValueFactory<>("tag"));             
		fahrer.setCellValueFactory(new PropertyValueFactory<>("fahrer"));          
		handynr.setCellValueFactory(new PropertyValueFactory<>("handynr"));     
		sollBus.setCellValueFactory(new PropertyValueFactory<>("sollBus"));         
		istBus.setCellValueFactory(new PropertyValueFactory<>("istBus"));          
		preisNetto.setCellValueFactory(new PropertyValueFactory<>("preisNetto"));      
		brutto.setCellValueFactory(new PropertyValueFactory<>("brutto"));          
		ek.setCellValueFactory(new PropertyValueFactory<>("ek"));              
		marge.setCellValueFactory(new PropertyValueFactory<>("marge"));           
		gesamt.setCellValueFactory(new PropertyValueFactory<>("gesamt"));    
	
		Liste e = new Liste();
		e.setBusNr("busNr");
		e.setBemerkungen("bemerkungen");
		e.setAnsprechpartner("ansprechpartner");
		e.setKurzel("kurzel");
		e.setKW("KW");
		e.setDatum(Date.valueOf(LocalDate.now()));
		e.setAbfahrtort("Abfahrtort");
		e.setUhrzeit("uhrzeit");
		e.setSchicht("schichtplaner");
		e.setTag("tag");
		e.setFahrer("Fahrer");
		e.setHandynr("handynr");
		e.setSollBus("sollBus");
		e.setIstBus("istBus");
		e.setPreisNetto("preisNetto");
		e.setBrutto("Brutto");
		e.setEk("ek");
		e.setMarge("marge");
		e.setGesamt("gesamt");
		listListe.add(e);
		tabelleEx.setItems(listListe);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	    }

	@FXML public void suchen(ActionEvent event) {
		Liste e =  listListe.get(0);
		
		
		
		
		if (tabelleEx.getItems()==null){
			tabelleEx.setItems(listListe);
		}else{
			
			System.out.println();
			listListe.get(0).setSchicht(schichtplaner.getCellData(0).getText());
			tabelleEx.setItems(null);
		}
		
	}

}
