package application.admin;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.Benutzer;
import application.dispo.UidObject;
import application.sql.ConnectMe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminController {


    @FXML
    private TextField ort;

    @FXML
    private CheckBox auskunft;

    @FXML
    private CheckBox marge;

    @FXML
    private CheckBox zubehoer;

    @FXML
    private TextField ort1;

    @FXML
    private CheckBox admin;

    @FXML
    private Button send;

    @FXML
    private Button close;

    @FXML
    private CheckBox bewertung;

    @FXML
    private CheckBox dispo;

    @FXML
    void bearbeitungSenden(ActionEvent event) {
    	String id = UidObject.uid;
    	ConnectMe c = new ConnectMe();
    	Statement stmt = c.getStatement();
    	
    	String nameB = ort1.getText();
    	String password = ort.getText();
    	int adminB = 0;
    			if(admin.isSelected()){
    				adminB =1;
    			}
    	int bewertungB = 0;
    			if(bewertung.isSelected()){
    				bewertungB = 1;
    			};
    	int margeB = 0;
    			if(marge.isSelected()){
    				margeB = 1;
    			};
    			
    	int auskunftB = 0;
    		if(auskunft.isSelected()){
    			auskunftB = 1;
    		}
    	
    	int zubehorB = 0;
    		if(zubehoer.isSelected()){
    			zubehorB = 1;
    		}
    		int  dispoB = 0;
    	if(dispo.isSelected()){
    		dispoB  = 1;
    	}
    	
    	

    	try {
    			stmt.executeUpdate("UPDATE plz.benutzer SET name = '"+nameB+ "', passwort='"+password+"',dispo ='"+dispoB+
						"',admin='"+adminB+"',bewertung='"+bewertungB+"',marge='"+margeB+"',auskunft='"+auskunftB+"',zubehor='"+zubehorB+
						"',recht1='0',recht2='0"+
						"'  WHERE User_ID = '"+ id +"';"); 
			
			
				}catch(SQLException e){
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
    void datenBefuellen(ActionEvent event) {

    }
    @FXML
    void initialize() {
    	String id = UidObject.uid;
    	ConnectMe c = new ConnectMe();
    	Statement stmt = c.getStatement();
    	ResultSet rs = null;
    	Benutzer  u= null;
    	


    	try {
    		 rs= stmt.executeQuery("Select * From plz.benutzer where User_ID ='"+id+"'");
			while(rs.next()){
				u = new Benutzer(rs.getString(1),rs.getString(2),rs.getString(3),rs.getBoolean(4),rs.getBoolean(5),rs.getBoolean(6),rs.getBoolean(7), rs.getBoolean(8),
						rs.getBoolean(9), rs.getBoolean(10), rs.getBoolean(11));
				}
			
				}catch(SQLException e){
					e.printStackTrace();
				}
    	
    	System.out.println(u.getName());
    	c.closeConnection();
    	ort1.setText(u.getName());
    	ort.setText(u.getPasswort());
    	
    	if(u.isAdmin()){
    		admin.setSelected(true);
    	}
    	if (u.isAuskunft()){
    		auskunft.setSelected(true);
    	}
    	if (u.isDispo()) {
    		dispo.setSelected(true);
		}
    	if(u.isMarge()){
    		marge.setSelected(true);
    	}
    	if(u.isBewertung()){
    		bewertung.setSelected(true);
    	}
    	
    	
    	if (u.isZubehor()) {
    		zubehoer.setSelected(true);
		}
    	
    	
    	
    }



}
