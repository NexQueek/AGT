package application.marge;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Calendar;

import application.sql.ConnectMe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Marge extends AnchorPane{
	
	public Marge(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Margex.fxml"));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
    @FXML
    private TableColumn<?, ?> umsatz;

    @FXML
    private TableColumn<?, ?> marge;

    @FXML
    private Label jahresUms;

    @FXML
    private Label jahresMar;

    @FXML
    private TableColumn<?, ?> margep;

    @FXML
    private TableColumn<?, ?> ekpreise;

    @FXML
    private Label JahresMarProz;

    @FXML
    private TableColumn<?, ?> vorgang;

    @FXML
    private TableColumn<?, ?> kalenderwochen;

    @FXML
    private TableView<KalenderWocheMarge> table;

	    @FXML
	    void initialize(){
	    	
	    	ekpreise.setCellValueFactory(new PropertyValueFactory<>("ekPreis"));
	        umsatz.setCellValueFactory(new PropertyValueFactory<>("umsatz"));
		    marge.setCellValueFactory(new PropertyValueFactory<>("marge"));
		    margep.setCellValueFactory(new PropertyValueFactory<>("MargeProzent"));
		    vorgang.setCellValueFactory(new PropertyValueFactory<>("vorgang"));
		    kalenderwochen.setCellValueFactory(new PropertyValueFactory<>("kalenderwoche"));
		    tabelleBefuellen();
	        
	    	
	    	
	    }
	    void tabelleBefuellen(){
	    	ObservableList<KalenderWocheMarge> list;
	    	list= FXCollections.observableArrayList();
	    	ConnectMe c = new ConnectMe();
	    	Statement stmt = c.getStatement();
	    	Calendar cal = Calendar.getInstance();
	    	
	    	String jahr = ""+cal.get(Calendar.YEAR);
	    	DecimalFormat dcf = new DecimalFormat("#0.00");
	    	double gesamtUmsatz=0;
	    	double gesamtMarge=0;
	    	double gesamtProzent=0;
	    	double einkaufsPreis=0;
	    	for (int i = 0; i <55 ; i++) {
	    		String sql = "SELECT Count(*) as Vorgänge, sum(einkaufspreis), sum(verkaufspreis), kalenderwoche  FROM plz.margekalender where kalenderwoche = "+i+" and jahr='"+jahr+"' ; ";

		    	try {
					ResultSet rs = stmt.executeQuery(sql);
					
					
						while(rs.next()) {
							System.out.println(i);
							String vorgang = rs.getString(1);
							String ekPreis = rs.getString(2);
							String umsatz = rs.getString(3);
							String kalender = rs.getString(4);
							if(!(vorgang == null||ekPreis == null||umsatz == null||kalender == null)){
								KalenderWocheMarge kWm = new KalenderWocheMarge(rs.getString(4), rs.getString(1), rs.getString(3), rs.getString(2));
								list.add(kWm);
							}	
						
						}
						
		    	
		    	}catch (Exception e) {
							e.printStackTrace();
						}
						
					}
					table.setItems(list);
					for (KalenderWocheMarge kMarge : list) {
						gesamtMarge +=Double.parseDouble(kMarge.getMarge().replace(',', '.'));
						einkaufsPreis += Double.parseDouble(kMarge.getEkPreis().replace(',', '.'));
						gesamtUmsatz +=Double.parseDouble(kMarge.getUmsatz());
					}
					gesamtProzent = (gesamtMarge/einkaufsPreis)*100;
					jahresUms.setText(""+gesamtUmsatz);
					jahresMar.setText(""+gesamtMarge);
					JahresMarProz.setText(dcf.format(gesamtProzent));
					
					c.closeConnection();
			}
	    	
	  
	    
	    @FXML
	    void eintragen(ActionEvent event) {
	    	try {
				
				Stage primaryStage = new Stage();
			 	Parent root = FXMLLoader.load(getClass().getResource("Marge eintragen.fxml")); 
			 	root.getStylesheets().add(
					    getClass().getResource("application.css").toExternalForm());
			    primaryStage.setScene(new Scene(root));
			    primaryStage.show(); 
			    
			} catch(Exception e) {
			e.printStackTrace();
			}
	    }
	    

	    @FXML
	    void abschicken(ActionEvent event) {

	    }
}
