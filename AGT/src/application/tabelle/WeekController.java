package application.tabelle;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class WeekController {
	@FXML TableColumn<Object, String> ExAnsprechpartner;
	@FXML TableColumn<Object, String> ExPosition;
	@FXML TableColumn<Object, String> ExHandynr;
	@FXML TableColumn<Object, String> ExArbeitszeiten;
	@FXML TableColumn<Object, String> ExMail;
	@FXML TableColumn<Object, String> BuAnsprechpartner;
	@FXML TableColumn<Object, String> BuName;
	@FXML TableColumn<Object, String> BuHandynr;
	@FXML TableColumn<Object, String> BuFestnetz;
	@FXML TableColumn<Object, String> BuMail;
	@FXML TableColumn<Object, String> InAGTBuero;
	@FXML TableColumn<Object, String> InPosition;
	@FXML TableColumn<Object, String> InHandynummer;
	@FXML TableColumn<Object, String> InFestnetz;
	@FXML TableColumn<Object, String> InMail;
	@FXML Label NameDesWerkes;
	@FXML Label GestartetAm;
	@FXML Label NettoUmsatz;
	@FXML Label BruttoUmsatz;
	@FXML Label BruttoMarge;
	@FXML Label NettoMarge;

	@FXML
	public void initialize(){
		
	}

	@FXML public void speichern(ActionEvent event) {}
}
