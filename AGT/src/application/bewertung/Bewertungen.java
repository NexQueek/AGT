package application.bewertung;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import application.dispo.UidObject;
import application.dispo.Unternehmen;
import application.sql.ConnectMe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Bewertungen extends AnchorPane {
    @FXML
    private TableColumn<Bewertung, String> nameUnternehmen;

    @FXML
    private TableColumn<Bewertung, String> sauber;

    @FXML
    private TableColumn<Bewertung, String> Thematik;

    @FXML
    private ComboBox<String> suchenNach;

    @FXML
    private TableColumn<Bewertung, String> rechnungsnummer;

    @FXML
    private Label serviceG;

    @FXML
    private Label sauberG;

    @FXML
    private Label anzahlBewertung;

    @FXML
    private TextField uID;

    @FXML
    private Label zuverlassigG;

    @FXML
    private TableColumn<Bewertung, Integer> zuverlassig;

    @FXML
    private Label erreichbarkeitGanz;

    @FXML
    private TableColumn<Bewertung, Integer> service;

    @FXML
    private TableColumn<Bewertung, Date> bewertetAm;

    @FXML
    private TextField suchenNacheintragen;

    @FXML
    private TableColumn<Bewertung, String> bewertetVon;

    @FXML
    private TableColumn<Bewertung, Integer> erreichbar;
    
    @FXML
    private TableColumn<Bewertung, String> bid;
    @FXML
    private ComboBox<String> kriterium;
    @FXML
    private TextField kriteriumEingabe;
    @FXML
    TableView<Bewertung> tabelle;
    @FXML
    private Label beschr;
    ObservableList<String> options2 = 
		    FXCollections.observableArrayList(
		        "Erreichbarkeit",
		        "Sauberkeit",
		        "Verlässlichkeit",
		        "Service"
		    );
    ObservableList<String> options1 = 
		    FXCollections.observableArrayList(
		        "Schlechter als",
		        "Besser als"
		    );
    
		public Bewertungen(){
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Bewertungen.fxml"));

	        fxmlLoader.setRoot(this);
	        fxmlLoader.setController(this);

	        try {
	            fxmlLoader.load();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
		@FXML
	    void initialize() {
			bid.setCellValueFactory(new PropertyValueFactory<>("Bid"));
	        nameUnternehmen.setCellValueFactory(new PropertyValueFactory<>("uid"));
		    bewertetVon.setCellValueFactory(new PropertyValueFactory<>("bewertetVon"));
		    bewertetAm.setCellValueFactory(new PropertyValueFactory<>("bewertetAm"));
		    rechnungsnummer.setCellValueFactory(new PropertyValueFactory<>("rechnungsnummer"));
		    Thematik.setCellValueFactory(new PropertyValueFactory<>("thematik"));
		    erreichbar.setCellValueFactory(new PropertyValueFactory<>("erreichbar"));
	        zuverlassig.setCellValueFactory(new PropertyValueFactory<>("zuverlassig"));
		    service.setCellValueFactory(new PropertyValueFactory<>("service"));
		    sauber.setCellValueFactory(new PropertyValueFactory<>("sauber"));
		    
		    
		    tabelle.setOnMousePressed(new EventHandler<MouseEvent>() {
				   @Override 
				   public void handle(MouseEvent ee) {
				      if (ee.isPrimaryButtonDown() && ee.getClickCount() == 2) {
				          //Gives the uid to popUp so it opens a window with specific information
				        anzeig(tabelle.getSelectionModel().getSelectedItem().getBeschreibung() );
				      }
				      
				   }
				});
			    
	    }

		
		void anzeig(String beschreibung){
			System.out.println(beschreibung);
			beschr.setText(beschreibung);
		
		}
	    @FXML
	    void bewertungeintragen(ActionEvent event) {
	    	Parent root;
	    	String uid = uID.getText();
	    	String sqlAbfrage = "Select * From plz.unternehmen where U_ID ='"+uid+"' ";
	    	if(uID.getText().equals("")){
	    		uID.setStyle("-fx-background-color: red");
	    	}else{
	    	 	try {
	    	 		
	    	 		uID.setStyle("-fx-background-color: white");
	    	 		ConnectMe c = new ConnectMe();
	    	 		Statement stmt = c.getStatement();
	    	 		ResultSet rs= stmt.executeQuery(sqlAbfrage);
	    	 		Unternehmen e = null;
	    	 		while(rs.next()){
	    	 			e = new Unternehmen(rs.getString(1), rs.getString(2),rs.getString(3) , rs.getString(4), rs.getString(5)
		    	 				, rs.getString(6), rs.getString(7),rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13) );
		    	 		
	    	 		}
	    	 	
	    	 		UidObject.unternehmen = e;
	    	 		
					root = FXMLLoader.load(getClass().getResource("BewertungEintragen.fxml"));
					Stage primaryStage = new Stage();
					primaryStage.setScene(new Scene(root));
					root.getStylesheets().add(
						    getClass().getResource("application.css").toExternalForm());
					
					primaryStage.setTitle("Software Engineering Team - SET");
					primaryStage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
		    }
	    	}
	   

	    @FXML
	    void suchen(ActionEvent event) {
	    	String uId = "Halloooooooooo";
	    	uId = uID.getText();
	    	ConnectMe c = new ConnectMe();
	    	Statement stmt = c.getStatement();
	    	ResultSet rs = null;
	    	ObservableList<Bewertung> list = FXCollections.observableArrayList();
	    	
	    	try {
				rs = stmt.executeQuery("Select * From bewertung inner join unternehmen on bewertung.U_ID = unternehmen.U_ID  where bewertung.U_ID ='"+uId+"'");
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
	    	
	    	System.out.println(list.size());
	    	tabelle.setItems(list);
	    	anzahlBewertung.setText(String.valueOf(list.size()));
	    	
	    	
	    	ausrechnen(list);
	    	
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
	    	System.out.println(erreichbarkeitDurchschnitt);
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


		@FXML public void hochladen(ActionEvent event) {}


		@FXML public void bewertenLassen(ActionEvent event) {}

	
		 

	
	

}
