package application.dispo;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.Benutzer;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Disposition extends AnchorPane{

    public Disposition(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Disposition.fxml"));
        
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
    private TableColumn<Unternehmen, String> besonderheiten;
    
    @FXML
    private TableColumn<Unternehmen, String> typ;
    
    @FXML
    private TableColumn<Unternehmen, String> farbe;
    
    @FXML
    private TableColumn<Unternehmen, String> groesse;
    
    @FXML
    private TableColumn<Unternehmen, String> chef;
    
    @FXML
    private TableColumn<Unternehmen, String> uid; 
    @FXML
    private TableColumn<Unternehmen, String> name;

    @FXML
    private TableColumn<Unternehmen, String> mail;

    @FXML
    private TableColumn<Unternehmen, String> plz;

    @FXML
    private TableColumn<Unternehmen, String> telefon;
    
    @FXML
    private TableColumn<Unternehmen, String> ort;

    @FXML
    private TableColumn<Unternehmen, String> mobil;
    @FXML
    private TableColumn<Unternehmen, String> bewertung;

    @FXML
    private ComboBox<String> suchwertDrei;
    
    @FXML
    private TextField ersteBedingung;
    @FXML
    private TextField dritteBedingung;
    
    @FXML
    private TextField entfernungUmkreis;
    
    @FXML
    private TextField plzUmkreis;
   
    
    @FXML
    private ComboBox<String> suchwertZwei;
    
    @FXML
    private TextField zweiteBedingung;
    
    @FXML
    private ComboBox<String> suchwert;
    
    @FXML
     TableView<Unternehmen> tabelle;
    public void initialize(URL location, ResourceBundle resources) {
		Benutzer user = Benutzer.benutz.get(0);
		suchwert.setItems(options);
		suchwertZwei.setItems(options2);
		suchwertDrei.setItems(options2);
		//for copy of the value of email
		
		
		mail.setCellFactory(TextFieldTableCell.forTableColumn());
		
		uid.setCellValueFactory(new PropertyValueFactory<>("uid"));
	    plz.setCellValueFactory(new PropertyValueFactory<>("pLZ"));
        ort.setCellValueFactory(new PropertyValueFactory<>("Ort"));
	    name.setCellValueFactory(new PropertyValueFactory<>("Name"));
	    mail.setCellValueFactory(new PropertyValueFactory<>("Mail"));
	    telefon.setCellValueFactory(new PropertyValueFactory<>("Telefon"));
	    mobil.setCellValueFactory(new PropertyValueFactory<>("mobil"));
	    chef.setCellValueFactory(new PropertyValueFactory<>("chef"));
        groesse.setCellValueFactory(new PropertyValueFactory<>("groesse"));
	    farbe.setCellValueFactory(new PropertyValueFactory<>("farbe"));
	    typ.setCellValueFactory(new PropertyValueFactory<>("typ"));
	    besonderheiten.setCellValueFactory(new PropertyValueFactory<>("besonderheiten"));
	    bewertung.setCellValueFactory(new PropertyValueFactory<>("distanz"));
	    TableView<Unternehmen> tableview = getTabelle();
	    tableview.getSelectionModel().setCellSelectionEnabled(true);
        tableview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	    //Methode für den doppelklick
	    tableview.setOnMousePressed(new EventHandler<MouseEvent>() {
			   @Override 
			   public void handle(MouseEvent ee) {
			      if (ee.isPrimaryButtonDown() && ee.getClickCount() == 2) {
			          //Gives the uid to popUp so it opens a window with specific information
			         popUp(tableview.getSelectionModel().getSelectedItem());
			      }
			      
			   }
			});
	    //Rechtsklick für Methode
	    ContextMenu cm = new ContextMenu();
	    MenuItem mi1 = new MenuItem("Unternehmen bearbeiten");
	    // Unternehmen nur für Admin bearbeitbar
	    if(user.isAdmin()){
	    	cm.getItems().add(mi1);
	    }
	    MenuItem mi2 = new MenuItem("Unternehmen anzeigen");
	    cm.getItems().add(mi2);
	    MenuItem mi3 = new MenuItem("E-Mail senden");
	    cm.getItems().add(mi3);
	    MenuItem mi4 = new  MenuItem("Busse anzeigen");
	    cm.getItems().add(mi4);
	    
	    tableview.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

	        @Override
	        public void handle(MouseEvent t) {
	            if(t.getButton() == MouseButton.SECONDARY) {
	                cm.show(tableview, t.getScreenX(), t.getScreenY());
	                mi1.setOnAction(new EventHandler<ActionEvent>() {
	                    @Override public void handle(ActionEvent e) {
	                    	UidObject.unternehmen = tableview.getSelectionModel().getSelectedItem();
	                        unternehmenBearbeiten(e);
	                    }
	                });
	                //tableview.getSelectionModel().getSelectedItem()
	                mi2.setOnAction(new EventHandler<ActionEvent>() {
	                    @Override public void handle(ActionEvent e) {
	   			         popUp(tableview.getSelectionModel().getSelectedItem());
	                    }
	                });
	                
	                mi3.setOnAction(new EventHandler<ActionEvent>() {
	                    @Override public void handle(ActionEvent e) {
	                    	String trenner =";";
	                    	String email ="";
	                    	ObservableList<Unternehmen> unternehmen = tableview.getSelectionModel().getSelectedItems();
	                    	
	                    	
	                    	for (Unternehmen unter : unternehmen) {
								email = email + unter.getMail() + trenner;
							}
	   			         	try {
								Desktop.getDesktop().mail(new URI("mailto:?bcc=" + email));
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								Alert alert = new Alert(AlertType.ERROR);
								
								alert.setHeaderText("Fehler!");
								alert.setContentText("Fehlermeldung" + e1.getMessage());
								alert.showAndWait();
								
							} catch (URISyntaxException e1) {
								// TODO Auto-generated catch block
								Alert alert = new Alert(AlertType.ERROR);
								
								alert.setHeaderText("Fehler!");
								alert.setContentText("Fehlermeldung" + e1.getMessage());
								alert.showAndWait();
								
								e1.printStackTrace();
							}
	                    }
	                });
	                
	                mi4.setOnAction(event->{
	                	busseAnzeigen(tableview.getSelectionModel().getSelectedItem());
	                });
	            }
	        }
	    });
	}
    
    void busseAnzeigen(Unternehmen e){
    	Stage primaryStage = new Stage();
    	Parent root=null;
    	UidObject.unternehmen=e;
		try {
			root = FXMLLoader.load(getClass().getResource("Busse.fxml"));
			root.getStylesheets().add(
				    getClass().getResource("application.css").toExternalForm());
	    	
			primaryStage.setTitle("Businformationen");    
			primaryStage.setResizable(false);
	        primaryStage.setScene(new Scene(root));      
	        primaryStage.show();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  	
    	
    	
    }
    
    @FXML
    void suchen(ActionEvent event) throws SQLException {
 //String suchbedingung = suchwert.getSelectionModel().getSelectedItem().toString();

		
	    ObservableList<Unternehmen> listeUnternehmen = null;
		//Geht nur rein wenn in der UmkreisPLZ nichts drin steht -> also normale suche
		if(plzUmkreis.getText().equals("")){
			
			String suchWert = ersteBedingung.getText(); //Eingabe des ersten Wert in Textfeld
		    String suchBedingung = suchwert.getSelectionModel().getSelectedItem(); //
		    String suchWertZwei = zweiteBedingung.getText(); //Eingabe des zweiten Werts in Textfeld
		    String suchBedingungZwei =  suchwertZwei.getSelectionModel().getSelectedItem(); //
		    if (suchBedingung.equals("PLZ")) {
				suchBedingung = "plz";
			}
		    if (suchBedingung.equals("Name des Unternehmens")) {
				suchBedingung = "name";
			}
		    if (suchBedingung.equals("Ort")) {
				suchBedingung = "ort";
			}if (suchBedingung.equals("E-Mail")) {
				suchBedingung = "mail";
			}
			 listeUnternehmen =  daten( suchBedingung, suchWert,
		    		suchBedingungZwei, suchWertZwei);
			 
		}
		else if(!plzUmkreis.getText().equals("")){
			listeUnternehmen = umkreisDaten(plzUmkreis.getText(), entfernungUmkreis.getText() );
		}
		
		
		tabelle.setItems(listeUnternehmen);
	    

    }
    /**
	 * Methode zum füllen der Unternehmen die für die bestimmten Bedingung zu treffen
	 * 
	 * @param bedingung ->besteht aus der ersten Suchbedingung { plz, Name, etc} 
	 * @param wertEins -> folgender Wert für die erste Suchbedingung
	 * @param bedingungZwei -> besteht aus zweiter Suchbedingung, kann optinal seinn 
	 * @param wertZwei -> Wert für die zweite Suchbedingung
	 * @return gibt die Liste mit den Unternhmen für die Bedingungen wieder
	 */
    public ObservableList<Unternehmen>  daten(String bedingung, String wertEins,String bedingungZwei, String wertZwei){
		ObservableList<Unternehmen> list = FXCollections.observableArrayList();
		String bedingungDrei = suchwertDrei.getSelectionModel().getSelectedItem();
		
		try{  
			
			ConnectMe c = new ConnectMe();
			Statement stmt = c.getStatement();
			
			
			if (bedingungZwei == null || wertZwei.equals("") ) {
				ResultSet rs=stmt.executeQuery("select  * from plz.unternehmen where "+ bedingung +" like '" + wertEins +"%' ");
				while(rs.next())  {
					
					Unternehmen e = new Unternehmen(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
							rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13));
					list.add(e);	
				}
				
			}else if(dritteBedingung.getText().equals("")||bedingungDrei.equals("")){
				System.out.println("gibt gas");
				ResultSet rs=stmt.executeQuery("select  * from plz.unternehmen where "+ bedingung +" like '" + wertEins +"%' and "+ bedingungZwei +" like '%" + wertZwei +"%' ");
				while(rs.next())  {
					Unternehmen e = new Unternehmen(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
							rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13));
					list.add(e);
					System.out.println("2teBedingung");
					}
			}else{
				System.out.println("3teBedingung");
				ResultSet rs=stmt.executeQuery("select  * from plz.unternehmen where "+ bedingung +" like '" + wertEins +"%' and "+ bedingungZwei +" like '%" + wertZwei +"%' "
						+" and "+bedingungDrei+" like '%"+ dritteBedingung.getText() +"%' ");
				while(rs.next())  {
					Unternehmen e = new Unternehmen(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
							rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13));
					list.add(e);
					
					}
			}
			System.out.println("42teBedingung");
				c.closeConnection();  
				
		}catch(Exception e){
			
			e.printStackTrace();
		
		}  		
		return list;
	}
    /**
	 * Mehtoder für die Umkreissuche im ersten schritt wird die lat und lon einer gesuchten PLZ gegeben und dann anhand 
	 * dieser Werte ein Inner join bei den beiden Tabellen gemacht um die Unternehmensdaten zu bekommen 
	 * @param plz -> für den ersten schritt benötigt 
	 * @param umkreis -> für den 2 Schritt zur berechnung der lat und lon 
	 * @return gibt eine Liste mit Unternehmen die im Umkreis sind
	 * @throws SQLException unbehandelt
	 */
	public ObservableList<Unternehmen>  umkreisDaten(String plz, String umkreis) throws SQLException{
		double lat = 0.0;
		double lon = 0.0;
		ObservableList<Unternehmen> list = FXCollections.observableArrayList();
		ConnectMe c = new ConnectMe();
		Statement stmt = c.getStatement();
		//1. Schritt lon und lat
		ResultSet rs1=stmt.executeQuery("Select lat,lon from plz.koordinaten where plz ="+ plz +"; ");
		
		while(rs1.next()){
			lat= rs1.getDouble(1);
			lon = rs1.getDouble(2);
		}
		
		ResultSet rs = stmt.executeQuery("Select Distinct unternehmen.*, Distanz From  Unternehmen Left Outer Join"
				+ " (SELECT plz,ROUND((6371 * acos(cos(radians(  "+lat+")) * cos(radians( lat)) *"
						+ " cos(radians( lon) - radians( "+lon+" )) + sin(radians( "+lat+" )) *"
								+ " sin(radians( lat)))),0) as Distanz from plz.koordinaten having Distanz <="+umkreis+")"
										+ " As SQ on SQ.plz=unternehmen.plz where Distanz <="+umkreis+";");
		
		while(rs.next())  {
			
			Unternehmen e = new Unternehmen(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13));
			e.setDistanz(rs.getString(14));
			list.add(e);	
		}
		
		return list;
	}
    
    

    @FXML
    void unternehmenBearbeiten(ActionEvent event) {


		Stage primaryStage = new Stage();
	 	Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("bearbeiten.fxml"));
			root.getStylesheets().add(
				    getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Bearbeiten");         
	        primaryStage.setScene(new Scene(root));
	        primaryStage.setResizable(false);
	        primaryStage.show(); 
		} catch (IOException e) {
			
			e.printStackTrace();
		}  	 

	
    }

    @FXML
    void unternehmenHinzufuegen(ActionEvent event) throws IOException {
    	Stage primaryStage = new Stage();
    	Parent root = FXMLLoader.load(getClass().getResource("hinzufuegen.fxml"));  	
    	
    	root.getStylesheets().add(
			    getClass().getResource("application.css").toExternalForm());
    	UidObject.unternehmen=null;
		primaryStage.setTitle("Unternehmen hinzufuegen"); 
		primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));      
        primaryStage.show(); 
    }
    ObservableList<String> options = 
		    FXCollections.observableArrayList(
		        "PLZ",
		        "Name des Unternehmens",
		        "Ort",
		        "E-Mail",
		        "U_ID"
		    );
	ObservableList<String> options2 = 
		    FXCollections.observableArrayList(
		        "Fahrzeuggroesse",
		        "Fahrzeugtyp",
		        "Fahrzeugfarbe"
		    );
	public TableView<Unternehmen> getTabelle() {
		return tabelle;
	}
	
	
	
	

	void popUp(Unternehmen uid){
		Parent root;
		Stage primaryStage;
		UidObject.unternehmen = uid;
		try {
			primaryStage = new Stage();
			root = FXMLLoader.load(getClass().getResource("Popup.fxml"));  	   
	    	root.getStylesheets().add(
				    getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Informationen");         
			primaryStage.setResizable(false);
	        primaryStage.setScene(new Scene(root));      
	        primaryStage.show(); 
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}