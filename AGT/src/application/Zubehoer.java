package application;

import java.io.IOException;
import java.text.DecimalFormat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;


public class Zubehoer extends AnchorPane{
	

	@FXML
	private TextField stundenBeginStd;

	@FXML
	private TextField stundenBeginMin;

	@FXML
	private TextField stundenEndeStd;
	
	@FXML
	private TextField stundenEndeMin;

	@FXML
	private Label stundenAusgabe;
	
    @FXML
    void stundenBerechnen(ActionEvent event) {
    	
    int BeginStd = Integer.parseInt(stundenBeginStd.getText());
    int BeginMin = Integer.parseInt(stundenBeginMin.getText());
    int EndeStd = Integer.parseInt(stundenEndeStd.getText());
    int EndeMin = Integer.parseInt(stundenEndeMin.getText());
    
    int StundenDifferenz = EndeStd - BeginStd;
    int MinutenDifferenz = EndeMin - BeginMin;
    
    String Stunden = Integer.toString(StundenDifferenz);
    String Minuten = Integer.toString(MinutenDifferenz);
    
    String Ausgabe = "Die Dauer des Einsatzes beträgt " + Stunden + " Stunden und " + Minuten + " Minuten.";
    stundenAusgabe.setText(Ausgabe);
    	
    }

    
    
	@FXML
	private TextField fahrerBeginStd;
	
	@FXML
	private TextField fahrerBeginMin;
	
	@FXML
	private TextField fahrerEndeStd;

	@FXML
	private TextField fahrerEndeMin;
	
	@FXML
	private TextField fahrerLaenge;
	
	@FXML
	private Label fahrerAusgabe;
	
	 
    @FXML
    void fahrerBerechnen(ActionEvent event) {
    	
	int BeginStd = Integer.parseInt(fahrerBeginStd.getText());
	//int BeginMin = Integer.parseInt(fahrerBeginMin.getText());
	int EndeStd = Integer.parseInt(fahrerEndeStd.getText());
	//int EndeMin = Integer.parseInt(fahrerEndeMin.getText());
	int StreckeInKm = Integer.parseInt(fahrerLaenge.getText());
	
	int StreckeInStd = StreckeInKm / 70;
	
    //int StundenDifferenz = EndeStd - BeginStd;
    //int MinutenDifferenz = EndeMin - BeginMin;
    
    int Einsatzzeit = (EndeStd + StreckeInStd) - BeginStd;
    int ZeitVorOrt = EndeStd - (BeginStd + StreckeInStd);
    
    if (StreckeInKm < 700 && Einsatzzeit < 13) {
    	String Ausgabe = "Es kommt ein Fahrer zum Einsatz";
    	fahrerAusgabe.setText(Ausgabe);
    } 
    if (StreckeInKm < 700 && Einsatzzeit > 13 && ZeitVorOrt > 9){
    	String Ausgabe = "Es kommt ein Fahrer zum Einsatz";
    	fahrerAusgabe.setText(Ausgabe);
    }
    if (StreckeInKm < 700 && Einsatzzeit > 13){
    	String Ausgabe = "Es müssen zwei Fahrer zum Einsatz kommen";
    	fahrerAusgabe.setText(Ausgabe);
    }
    if (StreckeInKm > 700 && Einsatzzeit > 13){
    	String Ausgabe = "Es müssen zwei Fahrer zum Einsatz kommen";
    	fahrerAusgabe.setText(Ausgabe);
    }
    if (StreckeInKm < 700 && Einsatzzeit > 17){
    	String Ausgabe = "Es müssen zwei Fahrer zum Einsatz kommen";
    	fahrerAusgabe.setText(Ausgabe);
    }
    if (StreckeInKm > 1400){
    	String Ausgabe = "Es müssen drei Fahrer zum Einsatz kommen";
    	fahrerAusgabe.setText(Ausgabe);
    }
    
    }
	

	
	@FXML
    private TextField fahrerBegin;
    //Streckenberechnung
    @FXML
    private TextField streckenLaenge;
    //Streckenberechnung
	@FXML
	private Label streckenDauer;
    
    @FXML
    private WebView webv;
    //Änderung s
    //Streckenberechnung
    @FXML
    void streckenBerechnen(ActionEvent event) {
    	double laenge;
    	int dauerh;
    	double dauermin;
    	String dauerText;
    	String dauerTextMin;
    	DecimalFormat dc = new DecimalFormat("#,###,##0");
    	DecimalFormat dc1 = new DecimalFormat("#,###,##0");
    	//streckenLaenge
    	//streckenDauer
    	laenge =Double.parseDouble(streckenLaenge.getText());
    	dauerh = (int) ((laenge / 70));//2
    	dauermin = ((laenge / 70)*60)%60;
    	//100=60
    	dauerText =dc.format(dauerh);
    	dauerTextMin = dc1.format(dauermin);
    	dauerText = dauerh +"h " + dauerTextMin +"min";
    	streckenDauer.setText(dauerText);
    }

    
    @FXML
    void gmAufrufen(ActionEvent event) {
    	
    }

public Zubehoer(){
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Zubehoer.fxml"));

    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);

    try {
        fxmlLoader.load();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}

