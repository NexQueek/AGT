package application.dispo;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import application.bewertung.Bewertung;
import application.sql.ConnectMe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;

public class InfoController {

    @FXML
    private Label info;
	@FXML Label plz;
	@FXML Label ort;
	@FXML Label email;
	@FXML Label tel;
	@FXML Label mobil;
	@FXML Label dispol;
	@FXML Label gross;
	@FXML Label farbe;
	@FXML Label typ;
	@FXML Label sauberG;
	@FXML Label erreichbarkeitGanz;
	@FXML Label serviceG;
	@FXML Label zuverlassigG;
	@FXML TextArea textArea;
    @FXML
    void initialize() {
    	
        Unternehmen ew = UidObject.unternehmen;
        info.setText("Informationen zu " + ew.getName());
        plz.setText(ew.getPLZ());
        ort.setText(ew.getOrt());
        email.setText(ew.getMail());
        tel.setText(ew.getTelefon());
        mobil.setText(ew.getMobil());
        dispol.setText(ew.getChef());
        gross.setText(ew.getGroesse());
        farbe.setText(ew.getFarbe());
        typ.setText(ew.getTyp());
        getBewertungen(ew.getUid());
        textArea.setText(ew.getInfos());
       
        
    }
    
    void getBewertungen(String uid){
    	ConnectMe c = new ConnectMe();
    	Statement stmt = c.getStatement();
    	ResultSet rs = null;
    	ObservableList<Bewertung> list = FXCollections.observableArrayList();
    	
    	try {
			rs = stmt.executeQuery("Select * From bewertung inner join unternehmen on bewertung.U_ID = unternehmen.U_ID  where bewertung.U_ID ='"+uid+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			while(rs.next()){
				Bewertung b = new Bewertung(rs.getString(1), rs.getString(17), rs.getString(3), rs.getDate(4) , rs.getString(5), rs.getString(6),
						rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getString(11), rs.getString(12), rs.getString(13));
				list.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	ausrechnen(list);
    	c.closeConnection();
    }
    
    void ausrechnen(ObservableList<Bewertung> liste){
    	double erreichbarkeitDurchschnitt = 0;
    	double serviceDurchschnitt = 0;
    	double sauberkeitDurchschnitt = 0;
    	double verlassigkeitDurchschnitt = 0;
    	double anzahl = liste.size();
    	
    	for (Bewertung bewertung : liste) {
			sauberkeitDurchschnitt =sauberkeitDurchschnitt+ bewertung.getSauber();
			erreichbarkeitDurchschnitt = erreichbarkeitDurchschnitt+ bewertung.getErreichbar();
			serviceDurchschnitt =serviceDurchschnitt + bewertung.getService();
			verlassigkeitDurchschnitt =verlassigkeitDurchschnitt+ bewertung.getZuverlassig();
		}
    	
    	 erreichbarkeitDurchschnitt =erreichbarkeitDurchschnitt / anzahl;
    	 serviceDurchschnitt = serviceDurchschnitt / anzahl;
    	 sauberkeitDurchschnitt= sauberkeitDurchschnitt / anzahl;
    	 verlassigkeitDurchschnitt = verlassigkeitDurchschnitt / anzahl;
    	 System.out.println(erreichbarkeitDurchschnitt);
    	 DecimalFormat dcf = new DecimalFormat("#.00");
    	 zuverlassigG.setText(dcf.format(verlassigkeitDurchschnitt));
    	 serviceG.setText(dcf.format(serviceDurchschnitt));
    	 sauberG.setText(dcf.format(sauberkeitDurchschnitt));
    	 erreichbarkeitGanz.setText(dcf.format(erreichbarkeitDurchschnitt));
    }

	@FXML public void absenden(ActionEvent event) {
		ConnectMe c = new ConnectMe();
		Statement stmt = c.getStatement();
		try {
			stmt.executeUpdate("UPDATE plz.unternehmen SET infos ='"+textArea.getText()+""
					+" '  WHERE U_ID = '"+ UidObject.unternehmen.getUid() +"';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}
}


