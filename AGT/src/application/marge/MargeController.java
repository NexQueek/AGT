package application.marge;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import application.sql.ConnectMe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyEvent;

public class MargeController {

    @FXML
    private TextField Verkaufspreis;

    @FXML
    private TextField Einkaufspreis;

    @FXML
    private TextField Unternehmen;

	@FXML TextField team;

	@FXML TextField rnummer;
    @FXML
    private Pane unternehmer;

    @FXML
    private TableColumn<MargeKalender, String> nr;



    @FXML
    private TableColumn<MargeKalender, String> margeEur;

    @FXML
    private TableColumn<MargeKalender, String> ekpreis;

    @FXML
    private TableColumn<MargeKalender, String> vkpreis;

    @FXML
    private TableColumn<MargeKalender, String> margePro;


	@FXML TableColumn<MargeKalender, String> Team1;

	@FXML TableColumn<MargeKalender, String> Team;

	@FXML TableColumn<MargeKalender, String> unternehmen;

	@FXML TableColumn<MargeKalender, String> rNummer;

	@FXML TableColumn<MargeKalender, String> teamTable;
    @FXML
    private TextField kalenderWoche;

    @FXML
    private TextField jahr;
    @FXML
    private TableView<MargeKalender> table;

	@FXML ComboBox<String> autoUnternehmen;
	
	void wertZuweisung(){
		autoUnternehmen.setOnKeyTyped(event -> {
			System.out.print(event.getCharacter());
		});
	}

    
    @FXML
    void initialize(){
    	nr.setCellValueFactory(new PropertyValueFactory<>("Mkid"));
    	ekpreis.setCellValueFactory(new PropertyValueFactory<>("Einkaufspreis"));
        vkpreis.setCellValueFactory(new PropertyValueFactory<>("Verkaufspreis"));
	    margeEur.setCellValueFactory(new PropertyValueFactory<>("MargeAnteil"));
	    margePro.setCellValueFactory(new PropertyValueFactory<>("MargeProzent"));
	    unternehmen.setCellValueFactory(new PropertyValueFactory<>("Uid"));
	    teamTable.setCellValueFactory(new PropertyValueFactory<>("Team"));
	    rNummer.setCellValueFactory(new PropertyValueFactory<>("Rechnungsnummer"));
        
        
    	wocheEintragen();
    	
    }
    /**
     * Methode zur Ermittlung der KalenderWoche
     */
    void wocheEintragen(){
    	
    	Calendar cal = Calendar.getInstance();
    	kalenderWoche.setText(""+cal.get(Calendar.WEEK_OF_YEAR));
    	jahr.setText(""+cal.get(Calendar.YEAR));
    }
	
    @FXML
    void eintragen(ActionEvent event) {
    	ObservableList<MargeKalender> list;
    	list= FXCollections.observableArrayList();
    	ConnectMe c = new ConnectMe();
    	Statement stmt = c.getStatement();
    	Calendar cal = Calendar.getInstance();
    	int woche = cal.get(Calendar.WEEK_OF_YEAR);
    	String sql = "Select * From margekalender where kalenderwoche = '"+woche+"' ";
    	
    	//Ersetzen des Komma durch Punkt da in Java beides vertauscht wird 
    	//Beispiel Nutzer gibt 3,5 ein und java kann mit dem komma nichts anfangen
    	String einkaufsPreisMitKomma = Einkaufspreis.getText().replace(',', '.');
    	String verkaufsPreisMitKomma = Verkaufspreis.getText().replace(',', '.');
    	
    	try {
			stmt.executeUpdate("Insert into plz.margekalender( kalenderwoche,einkaufspreis, verkaufspreis, U_ID,Team,rechnungsnummer,jahr)"
					+ "Values('"+ kalenderWoche.getText() +"', '"+ einkaufsPreisMitKomma +"', '"+verkaufsPreisMitKomma+"','"
							+ Unternehmen.getText() +  "', '"+team.getText()+"','"+rnummer.getText()+"', '"+ jahr.getText() +"')"
					);
			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				MargeKalender mk = new MargeKalender(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
				list.add(mk);
				System.out.println("hallo");
				}
			table.setItems(list);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//Reset der Eingaben
    	Einkaufspreis.setText("");
    	Verkaufspreis.setText("");
    	Unternehmen.setText("");
    	team.setText("");
    	rnummer.setText("");
		
		c.closeConnection();
    	
    	
    	
    }

    @FXML
    void abschicken(ActionEvent event) {

    }


	@FXML public void complete(KeyEvent event) {
		System.out.print(event.getText());
	}







}
