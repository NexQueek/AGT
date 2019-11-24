package application.admin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import application.Benutzer;
import application.bewertung.Unverify;
import application.dispo.UidObject;
import application.sql.ConnectMe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

	public class VerifyController {

	    @FXML
	    private TextField sauber;

	    @FXML
	    private Label unternehmen;

	    @FXML
	    private TextField zuverlassig;

	    @FXML
	    private TextField thematik;

	    @FXML
	    private Button suchen1;

	    @FXML
	    private TextField service;

	    @FXML
	    private TextField rechnungsnummer;

	    @FXML
	    private TextArea lösung;

	    @FXML
	    private TextField erreichbar;

	    @FXML
	    private Button send;

	    @FXML
	    private Button close;

	    @FXML
	    private TextArea beschreibung;

	    @FXML
	    void bearbeitungSenden(ActionEvent event) {
	    	
	    	ConnectMe c = new ConnectMe();
	    	Statement stmt = c.getStatement();
	    	
	    	Unverify u = Unverify.u;
	    	String uid = u.getUid();
	    	
	    	// Strings für die SQL Database um erst zu uppen und dann aus unverify zu loeschen 
	    	String hochladen = "Insert Into plz.bewertung(U_ID, bewertetVon, bewertetAm, rechnungsnummer, thematik, erreichbar, zuverlassig, service"
	    			+ ", sauber, beschreibung, loesung, bild) VALUES("
	    			+ uid + ", '"+Benutzer.benutz.get(0).getName()+"','"+u.getDate()+"', '"+rechnungsnummer.getText()+"', '"+ thematik.getText() +"' "
	    					+ ","+erreichbar.getText()+", "+zuverlassig.getText()+", "+service.getText()+", "+sauber.getText()+""
	    							+ ", '"+beschreibung.getText()+"', '"+lösung.getText()+"', '"+u.getBild()+"' )";
	    	System.out.println(hochladen);
	    	String loeschen = "DELETE FROM plz.unverify where unB_ID ='"+u.getUnBid()+"'";
	    	PreparedStatement preparedStmt;
	 		
	    	try {
				stmt.executeUpdate(hochladen);
				preparedStmt = c.getC().prepareStatement(loeschen);
	 			preparedStmt.execute();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	    	c.closeConnection();
	    	closewindow(event);
	    }

	    @FXML
	    void closewindow(ActionEvent event) {
	    	final Node source = (Node) event.getSource();
	        final Stage stage = (Stage) source.getScene().getWindow();
	        stage.close();
	       
	    }

	    @FXML
	    void bildAnzeigen(ActionEvent event) {

	    }
	    /**
	     * improve me please
	     */
	    @FXML
	    void initialize() {
	    	String id = UidObject.uid;
	    	ConnectMe c = new ConnectMe();
	    	Statement stmt = c.getStatement();
	    	ResultSet rs = null;
	    	Unverify  u= null;
	    	

	
	    	try {
	    		 rs= stmt.executeQuery("Select * From plz.unverify inner join plz.unternehmen on unverify.U_ID = unternehmen.U_ID where unB_ID ='"+id+"'");
				while(rs.next()){
					u = new Unverify(rs.getString(1),rs.getString(14),rs.getString(3),rs.getDate(4),rs.getString(5),rs.getString(6),rs.getInt(7),
							rs.getInt(8),rs.getInt(9),rs.getInt(10),rs.getString(11), rs.getString(12));
					u.setBild(rs.getString(13));
					u.setUnterName(rs.getString(17));
					Unverify.u = u;
					}
				//Setze alle Texte
				unternehmen.setText(u.getUnterName());
				rechnungsnummer.setText(u.getRechnungsnummer());
				thematik.setText(u.getThematik());
				beschreibung.setText(u.getBeschreibung());
				lösung.setText(u.getLoesung());
				erreichbar.setText(String.valueOf(u.getErreichbar()));
				sauber.setText(String.valueOf(u.getSauber()));
				service.setText(String.valueOf(u.getService()));
				zuverlassig.setText(String.valueOf(u.getZuverlassig()));
				
				
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
	    	c.closeConnection();
	    }

	}

