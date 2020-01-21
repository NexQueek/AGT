package application.tabelle;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;


public class Tabelle {
	public Tabelle(){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Tabelle.fxml"));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
            initialize();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
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
		 
		    
	    }

}
