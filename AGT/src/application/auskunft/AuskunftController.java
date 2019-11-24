
package application.auskunft;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import application.Benutzer;
import application.sql.ConnectMe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AuskunftController {

    @FXML
    private Button auskunft_abbrechen;

    @FXML
    private Button auskunftEintragen;

	@FXML Button auskunft_eintragen;

	@FXML TextField rehnungsnr;

	@FXML TextField anzahl;

	@FXML TextField PLZ;

	@FXML TextField datum;
	
	@FXML TextField name;

	@FXML ComboBox<String> artderVeranstalltung;

	@FXML TextField A_IDeingabe;
    @FXML
    private Button eintragenBearbeiten;

	@FXML Button sucher;
	
	ObservableList<String> optionen = FXCollections.observableArrayList(
			"Messen",
			"AGT Events",
			"Festival",
			"Schulausflüge",
			"Sonstiges"
			
			);

	@FXML DatePicker datumBearbeiten;

	@FXML DatePicker datumEintrag;

	@FXML TextField rechnungEintrag;

	@FXML TextField anzahlEintrag;

	@FXML TextField plzEintrag;

	@FXML TextField nameEintrag;
	
	    @FXML
	    void fensterSchliessenBearbeiten(ActionEvent event) {
	    	Stage stage = (Stage) A_IDeingabe.getScene().getWindow();
		    // do what you have to do
		    stage.close();
	    }
	    /**
	     * Updaten der AUskunft 
	     * Wichtig ohne Benutzer nicht moeglich
	     * @param event
	     */
	    @FXML
	    void bearbeitenAbschliessen(ActionEvent event) {
	    	String aid = A_IDeingabe.getText();
	    	String artVeranstaltung = artderVeranstalltung.getSelectionModel().getSelectedItem();
	    	String nam = name.getText();
	    	//Date datumVeranstaltung = java.sql.Date.valueOf(datumBearbeiten.getValue());
	    	LocalDate datumVeranstaltung = datumBearbeiten.getValue();
	    	LocalDate eintragung = LocalDate.now();
	    	String plzText = PLZ.getText();
	    	String rechnung = rehnungsnr.getText();
	    	int anzahlBusse = Integer.parseInt(anzahl.getText());
		    	try{  
					
					Class.forName("com.mysql.cj.jdbc.Driver");  	
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost/plz?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","1234");  			
					Statement stmt=con.createStatement();  		
					stmt.executeUpdate("UPDATE auskunft SET datumDerEintragung ='"+eintragung+"',  eingetragenVon ='"+Benutzer.benutz.get(0).getName()
							+"' , plz ='"+ plzText+"', datumVeranstaltung ='"+datumVeranstaltung+"', nameVeranstaltung ='"+nam+"', "
									+ "anzahl="+anzahlBusse+", rechnungsnummer='"+rechnung+"',"
											+ "artVeranstaltung='"+ artVeranstaltung+"'   WHERE A_ID = '"+ aid +"';"); 
					stmt.close();
					con.close(); 
					closeWindow(event);
				}catch(Exception e) { 
					System.out.println(e);
				}
	    }
	    	

	    @FXML
	    void schliessen(ActionEvent event) {
	    	Stage stage = (Stage) eintragenBearbeiten.getScene().getWindow();
		    // do what you have to do
		    stage.close();
	    }
	    
	    /**
	     * Findet die passende Auskunft  zur A_ID in der Datenbank
	     * @param event ist der Klick auf den Button
	     */
		@FXML void bearbeitenSuchen(ActionEvent event) {
			//Verbindung zur Datenbank aufnehmen
			try {
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost/plz?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","1234");
				Statement stmt=con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM plz.auskunft where A_ID='"+ A_IDeingabe.getText() +"';");
			
				
				
				while (rs.next()) {

					name.setText(rs.getString(6));
					PLZ.setText(rs.getString(4));
					Date date = rs.getDate(5);
					LocalDate ds = date.toLocalDate();
					anzahl.setText(rs.getString(7));
					rehnungsnr.setText(rs.getString(8));
					artderVeranstalltung.setValue(rs.getString(9));
					datumBearbeiten.setValue(ds);

				}

			} catch (Exception e) {
				
			}
			
			
		}
		 @FXML
		    void initialize() {
		        artderVeranstalltung.setItems(optionen);

		    }
		 
		 
		 
		 void closeWindow(ActionEvent event){
			 final Node source = (Node) event.getSource();
		        final Stage stage = (Stage) source.getScene().getWindow();
		        stage.close();
		 }
		@FXML public void closeWindow2(ActionEvent event) {
			closeWindow(event);
		}
		@FXML public void neuEintrag(ActionEvent event) {
			String art  = artderVeranstalltung.getSelectionModel().getSelectedItem();
			String name = nameEintrag.getText();
			LocalDate datum = datumEintrag.getValue();
			String plz = plzEintrag.getText();
			String rechnung = rechnungEintrag.getText();
			int anzahl = Integer.parseInt(anzahlEintrag.getText());
			String nutzer = Benutzer.benutz.get(0).getName();
			LocalDate dateToday = LocalDate.now();
			System.out.println(dateToday);
			ConnectMe c = new ConnectMe();
			Statement stmt = c.getStatement();
			try {
				stmt.executeUpdate("INSERT INTO plz.auskunft (datumDerEintragung, eingetragenVon, plz, datumVeranstaltung, nameVeranstaltung, "
						+ "anzahl, rechnungsnummer, artVeranstaltung) VALUES ('"+dateToday+"','"+nutzer+"','"+plz+"', '"+datum+"', '"+name+"',"+anzahl+",'"+rechnung+"', '"+art+"'   );" );
				closeWindow(event);
				c.closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		

}

