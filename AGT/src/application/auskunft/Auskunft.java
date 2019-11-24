package application.auskunft;

import java.io.IOException;
import java.net.URL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

import application.sql.ConnectMe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Auskunft extends AnchorPane {

    @FXML
    private TableColumn<Eventauskunft, String> datumEintragung;

    @FXML
    private TableColumn<Eventauskunft, String> anzahl;

    @FXML
    private TableColumn<Eventauskunft, String> rechnungsnummer;
    @FXML
    private TextField umkreis;

    @FXML
    private CheckBox umkreisAktiv;

    @FXML
    private DatePicker auskunftDatumVon;

    @FXML
    private TableColumn<Eventauskunft, String> name;

    @FXML
    private TableColumn<Eventauskunft, String> datumVeranstaltung;

    @FXML
    private TableColumn<Eventauskunft, String> eingetragenVon;

    @FXML
    private TableColumn<Eventauskunft, String> aid;
    @FXML
    private TableColumn<Eventauskunft, String> artVeranstaltung;

    @FXML
    private TextField auskunftPLZ;

    @FXML
    private DatePicker auskunftDatumBis;
    @FXML
    private TableColumn<Eventauskunft, String> distanzTable;


    @FXML
    private TableColumn<Eventauskunft, String> plz;
    @FXML
    private TableView<Eventauskunft> tabelle;
	public Auskunft(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Auskunft.fxml"));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
            initialize(null, null);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


	    @FXML
	    void auskunft_suchen(ActionEvent event) {
	    	ObservableList<Eventauskunft> list;
	    	list = FXCollections.observableArrayList();
	    	String plz = auskunftPLZ.getText();
	    	LocalDate von = auskunftDatumVon.getValue();
	    	LocalDate bis = auskunftDatumBis.getValue();
	    	
	    	
	    	
			
			try {
				ConnectMe c = new ConnectMe();
				Statement stmt=c.getStatement();
				double lat=0.0;
				double lon=0.0;
				String lang = umkreis.getText();
				if(umkreisAktiv.isSelected()){
					ResultSet rs1=stmt.executeQuery("Select lat,lon from plz.koordinaten where plz ="+ plz +"; ");
					while(rs1.next()){
						lat= rs1.getDouble(1);
						lon = rs1.getDouble(2);
					}
					System.out.println("Sue");
					ResultSet rs2 = stmt.executeQuery("SELECT Distinct A_ID, datumDerEintragung, eingetragenVon, auskunft.plz, datumVeranstaltung, nameVeranstaltung, anzahl, rechnungsnummer, artVeranstaltung, ROUND((6371 * acos(cos(radians(  "+lat+")) * cos(radians( lat)) * cos(radians( lon) - radians( "+lon+" )) + sin(radians( "+ lat +" )) * sin(radians( lat)))),0) AS distance FROM koordinaten inner join auskunft on auskunft.plz = koordinaten.plz having distance <="+lang+" and auskunft.datumVeranstaltung between '"+von+ "' and '"+bis+"';");
					while(rs2.next()){
						Eventauskunft en = new Eventauskunft(rs2.getInt(1),rs2.getDate(2),rs2.getString(3),rs2.getString(4),rs2.getDate(5), rs2.getString(6),rs2.getInt(7),rs2.getString(8),rs2.getString(9));
						en.setDistanz(rs2.getInt(10));
						list.add(en);
					}
				}else{
					ResultSet rs = stmt.executeQuery("SELECT * FROM plz.auskunft where plz ='"+plz+"' and datumVeranstaltung between '"+von+ "' and '"+bis+"'; ");
					
					
					while (rs.next()) {
						Eventauskunft en = new Eventauskunft(rs.getInt(1),rs.getDate(2),rs.getString(3),rs.getString(4),rs.getDate(5), rs.getString(6),rs.getInt(7),rs.getString(8),rs.getString(9));
						list.add(en);
						
				}
				
				}
				
				tabelle.setItems(list);
				c.closeConnection();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}  			
			
			
	    }

	    @FXML
	    void auskunftEintragen(ActionEvent event) {
			Stage primaryStage = new Stage();
		 	Parent root;
			try {
				root = FXMLLoader.load(getClass().getResource("AuskunftEintragen.fxml"));
				root.getStylesheets().add(
					    getClass().getResource("application.css").toExternalForm());
				primaryStage.setTitle("Auskunft eintragen");         
		        primaryStage.setScene(new Scene(root));
		        primaryStage.show(); 
			} catch (IOException e) {
				
				e.printStackTrace();
			}  	 

		
	    }
	    @FXML
	    void auskunftAendern(ActionEvent event) {
	    	Stage primaryStage = new Stage();
		 	Parent root;
			try {
				root = FXMLLoader.load(getClass().getResource("AuskunftBearbeiten.fxml"));
				root.getStylesheets().add(
					    getClass().getResource("application.css").toExternalForm());
				primaryStage.setTitle("Auskunft eintragen");         
		        primaryStage.setScene(new Scene(root));
		        primaryStage.show(); 
			} catch (IOException e) {
				
				e.printStackTrace();
			}  	 
	    }
	    

	
	public void initialize(URL location, ResourceBundle resources) {
		

		
		aid.setCellValueFactory(new PropertyValueFactory<>("aid"));
	    plz.setCellValueFactory(new PropertyValueFactory<>("Plz"));
        eingetragenVon.setCellValueFactory(new PropertyValueFactory<>("EingetragenVon"));
	    name.setCellValueFactory(new PropertyValueFactory<>("NameVeranstaltung"));
	    datumVeranstaltung.setCellValueFactory(new PropertyValueFactory<>("DatumVeranstaltung"));
	    anzahl.setCellValueFactory(new PropertyValueFactory<>("AnzahlBusse"));
	    rechnungsnummer.setCellValueFactory(new PropertyValueFactory<>("RechnungsNummer"));
	    datumEintragung.setCellValueFactory(new PropertyValueFactory<>("Eintragung"));
	    artVeranstaltung.setCellValueFactory(new PropertyValueFactory<>("Art"));
	    distanzTable.setCellValueFactory(new PropertyValueFactory<>("Distanz"));
	}
}
