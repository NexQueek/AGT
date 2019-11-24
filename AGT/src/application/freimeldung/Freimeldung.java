package application.freimeldung;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

import application.sql.ConnectMe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Freimeldung extends AnchorPane {
	public Freimeldung(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Freimeldung.fxml"));

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
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Freimeldungen, String> fid;

    @FXML
    private TableColumn<Freimeldungen, String> ort;

    @FXML
    private TableColumn<Freimeldungen, String> busmodel;

    @FXML
    private TableColumn<Freimeldungen, String> unternehmen;

    @FXML
    private TableColumn<Freimeldungen, String> busplätze;

    @FXML
    private TableColumn<Freimeldungen, String> datumbis;

    @FXML
    private TableColumn<Freimeldungen, String> eingetragenvon;

    @FXML
    private TableColumn<Freimeldungen, String> datumvon;

    @FXML
    private TableColumn<Freimeldungen, String> r_nummer;

    @FXML
    private TableColumn<Freimeldungen, String> plz;
    
    @FXML
    private TableView<Freimeldungen> table;
    @FXML
    private DatePicker zielDatum;
    @FXML
    private TextField umkreisPostleitzahl;
    @FXML
    private TextField umkreisEingabe;
    @FXML
    void freimeldungEintragen(ActionEvent event) {

		Stage primaryStage = new Stage();
	 	Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("FreimeldungEintragen.fxml"));
			root.getStylesheets().add(
				    getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Freimledung eintragen");         
	        primaryStage.setScene(new Scene(root));
	        primaryStage.show(); 
		} catch (IOException e) {
			
			e.printStackTrace();
		}  	 
    }

    @FXML
    void initialize() {
		fid.setCellValueFactory(new PropertyValueFactory<>("fid"));
	    plz.setCellValueFactory(new PropertyValueFactory<>("plz"));
        ort.setCellValueFactory(new PropertyValueFactory<>("Ort"));
	    r_nummer.setCellValueFactory(new PropertyValueFactory<>("rechnungsnummer"));
	    unternehmen.setCellValueFactory(new PropertyValueFactory<>("unternehmen"));
	    datumvon.setCellValueFactory(new PropertyValueFactory<>("datumVon"));
	    datumbis.setCellValueFactory(new PropertyValueFactory<>("datumBis"));
	    busmodel.setCellValueFactory(new PropertyValueFactory<>("busModel"));
        busplätze.setCellValueFactory(new PropertyValueFactory<>("busPlatze"));
	    eingetragenvon.setCellValueFactory(new PropertyValueFactory<>("eingetragen"));
	    methodenImplementieren();
	    
    }
    void methodenImplementieren(){
    	 table.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

 	        @Override
 	        public void handle(MouseEvent t) {
 	        	System.out.println("Hi");
 	            if(t.getButton() == MouseButton.SECONDARY) {
 	            	ContextMenu cm = new ContextMenu();
 	        	    MenuItem mi1 = new MenuItem("Freimeldung löschen");
 	        	    cm.getItems().add(mi1);
 	        	    cm.show(table, t.getScreenX(), t.getScreenY());
 	        	    mi1.setOnAction(new EventHandler<ActionEvent>() {
	                    @Override public void handle(ActionEvent e) {
	                    	try{
	                    		if(table.getSelectionModel().getSelectedItem()!=null){
	                    			freiLoeschen(table.getSelectionModel().getSelectedItem());
			 	        	    	cm.hide();
	                    		}else{
	                    			System.out.println("honk");
	                    		}
	                
	                    		
	                    	}catch(NullPointerException nEx){
	                    		cm.hide();
	                    		System.err.println(nEx+"wie dumm");
	                    	}
	                    	
	                    }
	                });
 	        	   
 	        	    
 	            }
 	        }
 	    });
    }
    void freiLoeschen(Freimeldungen fr){
    	 ConnectMe c = new ConnectMe();
    	 String query = "delete from freimeldung where F_ID = "+fr.getFid()+"";
         PreparedStatement preparedStmt;
		try {
			preparedStmt = c.getC().prepareStatement(query);
			preparedStmt.execute();
			//Hat geklappt dann soll ein Fenster kommen 
			Alert alert = new Alert(AlertType.INFORMATION);
			
			alert.setHeaderText("Aktion Erfolgreich!");
			alert.setContentText("Freimeldung mit der ID "+fr.getFid()+" wurde gelöscht");
			alert.showAndWait();
			tabelleBefuellen();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         

    
         
         c.closeConnection();
    }
    void tabelleBefuellen(){
    	ObservableList<Freimeldungen> liste = FXCollections.observableArrayList();
    	LocalDate eingabe = zielDatum.getValue();
    	double lat = 0.0;
    	double lon = 0.0;
    	String postleitzahl =umkreisPostleitzahl.getText();
    	ConnectMe c = new ConnectMe();
    	Statement stmt = c.getStatement();
    	String entfernung = umkreisEingabe.getText();
    	
    	try {
    		ResultSet rs1=stmt.executeQuery("Select lat,lon from plz.koordinaten where plz ='"+ postleitzahl +"'; ");
    		while(rs1.next()){
    			lat= rs1.getDouble(1);
    			lon = rs1.getDouble(2);
    		}
			System.out.println(lat);
			System.out.println(entfernung);
			ResultSet rs = stmt.executeQuery(
					"SELECT Distinct F_ID, eingetragenVon, rechnungsnummer, freimeldung.plz, Ort, unternehmen, datumVon, datumBis, busmodel,busplatz,"
					+ "  ROUND((6371 * acos(cos(radians(  "+lat+")) * cos(radians( lat)) * cos(radians( lon) - radians( "+lon+" )) + sin(radians( "+ lat +" )) * sin(radians( lat)))),0) AS distance"
							+ " FROM koordinaten inner join freimeldung on freimeldung.plz = koordinaten.plz having distance <="+entfernung+" and ('"+eingabe+"' between datumVon and datumBis);");
			while(rs.next()){
				Freimeldungen f = new Freimeldungen(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6), rs.getDate(7), rs.getDate(8), rs.getString(9), rs.getString(10));
				liste.add(f);
			}
		    table.setItems(liste);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	c.closeConnection();
    }

    @FXML
    void suchen(ActionEvent event) {
    	tabelleBefuellen();
    }

}
