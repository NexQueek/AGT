package application.dispo;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.controlsfx.control.textfield.TextFields;

import application.sql.ConnectMe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class hinzuController {
	
    @FXML private Button close;
	@FXML private Button send;
	@FXML TextField plz;
	@FXML TextField ort;
	@FXML TextField name;
	@FXML TextField mail;
	@FXML TextField telefon;
	@FXML TextField mobil;
	@FXML TextField chef;
	@FXML TextField groesse;
	@FXML TextField farbe;
	@FXML TextField typ;
	@FXML TextField besonderheiten;
	String sPlz; 
	String sOrt ;
	String sName ;
	String sMail ;
	String sTelefon;
	String sMobil ;
	String sChef ;
	String sGroesse ;
	String sFarbe ;
	String sTyp;
	String sBesonderheiten;
	
	@FXML
	Label bestatigung;
	
	@FXML Button agree;
	@FXML TextField uidBearbeitung;
	@FXML Button suchen;
	@FXML Label titleHinzu;

    @FXML
    void closewindow(ActionEvent event) {
    	final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    @FXML
    void initialize() {
    	try {
    		datenBefuellen();
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    	
    	plzRunterladen();
    	
    }
    void plzRunterladen(){
    	String sql = "Select plz From plz.koordinaten";
    	ConnectMe c = new ConnectMe();
    	Statement stmt = c.getStatement();
    	ArrayList<String> postalCode = new ArrayList<String>(); 
    	try {
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				postalCode.add(rs.getString(1));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	TextFields.bindAutoCompletion(plz, postalCode);
    	
    }
	


	    @FXML
	    void d0cbd0(ActionEvent event) {    	
	    	
	}
		@FXML
		public void senden(ActionEvent event) throws IOException {
			sPlz = plz.getText();
			sOrt = ort.getText();
			sName = name.getText();
			sMail = mail.getText();
			sTelefon = telefon.getText();
			sMobil = mobil.getText();
			sChef = chef.getText();
			sGroesse = groesse.getText();
			sFarbe = farbe.getText();
			sTyp = typ.getText();
			sBesonderheiten = besonderheiten.getText();
			try{  
				
				
				ConnectMe c = new ConnectMe();
				Statement stmt=	c.getStatement();
				stmt.executeUpdate("Insert into plz.unternehmen( plz,ort, name, mail,telefon,mobil,leiter,fahrzeuggroesse,fahrzeugfarbe,fahrzeugtyp,besonderheiten) "
						+ "Values('"+ sPlz + "','" + sOrt + "','" + sName +"','"+ sMail + "','" + sTelefon + "','" + sMobil + "','" + sChef + "','" + sGroesse + "','" 
						+ sFarbe +  "','" + sTyp+"','" + sBesonderheiten +"');" , Statement.RETURN_GENERATED_KEYS ); 
				

				stmt.close();
				c.closeConnection();  


			}catch(Exception e){ System.out.println(e);
			}  
			final Node source = (Node) event.getSource();
	        final Stage stage = (Stage) source.getScene().getWindow();
	        stage.close();
	        
	       
	        
	    	try {
				
	    		Stage primaryStage = new Stage();
			 	Parent root = FXMLLoader.load(getClass().getResource("bestaetigung.fxml"));  	 
			 	root.getStylesheets().add(
					    getClass().getResource("application.css").toExternalForm());
		        primaryStage.setTitle("AGT");         
		        primaryStage.setScene(new Scene(root));
		        // primaryStage.setFullScreen(true);
		        primaryStage.show(); 
		        
		} catch(Exception e) {
			e.printStackTrace();
		}
			
		}
		/**
		 * Here the data of the unternehmen should show if uid is entered
		 * @param event
		 */
		public void datenBefuellen(){
			Unternehmen unter = UidObject.unternehmen;
			
			
			plz.setText( unter.getPLZ());
			ort.setText(unter.getOrt());
			name.setText(unter.getName());
			mail.setText(unter.getMail());
			telefon.setText(unter.getTelefon());
		    mobil.setText(unter.getMobil());
			chef.setText(unter.getChef());
			groesse.setText(unter.getGroesse());
			farbe.setText(unter.getFarbe());
			typ.setText(unter.getTyp());
			besonderheiten.setText(unter.getBesonderheiten());
			
		}
		
		@FXML public void bearbeitungSenden(ActionEvent event) {
			sPlz = plz.getText();
			sOrt = ort.getText();
			sName = name.getText();
			sMail = mail.getText();
			sTelefon = telefon.getText();
			sMobil = mobil.getText();
			sChef = chef.getText();
			sGroesse = groesse.getText();
			sFarbe = farbe.getText();
			sTyp = typ.getText();
			sBesonderheiten = besonderheiten.getText();
			
				try{  
					ConnectMe c = new ConnectMe();
					Statement stmt=c.getStatement();  		
					stmt.executeUpdate("UPDATE plz.unternehmen SET plz = '"+sPlz+ "', ort='"+sOrt+"',name ='"+sName+
							"',mail='"+sMail+"',telefon='"+sTelefon+"',mobil='"+sMobil+"',leiter='"+sChef+"',fahrzeuggroesse='"+sGroesse+
							"',fahrzeugfarbe='"+sFarbe+"',fahrzeugtyp='"+sTyp+"',besonderheiten='"+sBesonderheiten+
							"'  WHERE U_ID = '"+ UidObject.unternehmen.getUid() +"';"); 
					stmt.close();
					c.closeConnection(); 
					final Node source = (Node) event.getSource();
			        final Stage stage = (Stage) source.getScene().getWindow();
			        stage.close();
			} catch(Exception e) { 
				System.out.println(e);
			}  
			try {
				
				Stage primaryStage = new Stage();
			 	Parent root = FXMLLoader.load(getClass().getResource("bestaetigung.fxml")); 
			    primaryStage.setScene(new Scene(root));
			    primaryStage.show(); 
			    
			} catch(Exception e) {
			e.printStackTrace();
			}
						
			
		}

	 

}
