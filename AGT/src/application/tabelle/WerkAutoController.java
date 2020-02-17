package application.tabelle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

/**
 * Der Nutzer kann ein Werk automatisieren dafür mussen 4 Schritte getätigt
 * werden 1. Eingabe Anzahl der Linien 2. Erstellung der Linien 3. Erstellung
 * der Busse und zuordnung der Linie 4. Erstellung und Zuordnung der Schichten
 * je Linie
 * 
 * Danach soll das Programm für den bestimmten Zeitraum ( der bei den Linien
 * angegeben wird) eine Tabelle erstellen von den Fahrten
 * 
 * @author Nex
 *
 */
public class WerkAutoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 30391605206074104L;

	@FXML
	private TextField linieAnzahl;

	@FXML
	private TextField schichtenFruehHin;

	@FXML
	private ComboBox<String> busseLinie;

	@FXML
	private TextField linieName;

	@FXML
	private ComboBox<String> linieTage;

	@FXML
	private TextField busseUnternehmer;

	@FXML
	private TextField busseArt;

	@FXML
	private TextField linieUeber;

	@FXML
	private TextField schichtenSpaetZu;

	@FXML
	private ComboBox<String> busseBus;

	@FXML
	private TextField werkAnzahl;

	@FXML
	private ComboBox<String> schichtenLinie;

	@FXML
	private TextField linieNach;

	@FXML
	private ComboBox<String> schichtenBus;

	@FXML
	private TextField linieVon;

	@FXML
	private TextField schichtenFruehTz;

	@FXML
	private ComboBox<String> linieFarbe;

	@FXML
	private TextField schichtenSpaetHin;

	@FXML
	private TextField schichtenSpaetTz;

	@FXML
	private ComboBox<String> linieLinie;

	@FXML
	private TextField busseFahrer;

	@FXML
	private TextField bussePreis;

	@FXML
	private TextField schichtenNachtTz;

	@FXML
	private TextField schichtenFruehZu;

	@FXML
	private Label linieBeispiel;

	@FXML
	private TextField busseGroesse;

	@FXML
	private TextField schichtenNachtHin;

	@FXML
	private TextField schichtenNachtZu;

	@FXML
	private TextField busseKennzeichen;

	@FXML
	Tab linie;

	@FXML
	Tab busse;

	@FXML
	Tab schichten;
	ObservableList<String> listeMoglichkeit = FXCollections.observableArrayList();

	@FXML
	Button werkAbsendenButton;

	@FXML
	CheckBox schichtenMo;

	@FXML
	CheckBox schichtenDi;

	@FXML
	CheckBox schichtenMi;

	@FXML
	CheckBox schichtenDo;

	@FXML
	CheckBox schichtenFr;

	@FXML
	CheckBox schichtenSa;

	@FXML
	CheckBox schichtenSo;

	@FXML
	DatePicker schichtenWoche;
	ObservableList<String> farbenLinie = FXCollections.observableArrayList("Cyan","Hellblau", "Rosa", "Orange", "Gelb");

	@FXML
	void initialize() {
		linieFarbe.setItems(farbenLinie);
		
		String name = Werk.werk.getWerkBezeichnung();
		try {
			File f = new File(new URI("file:///M:/0.Share/SET/Werke/" + name + ".ser"));
			FileInputStream fis = new FileInputStream(f);
			//
			ObjectInputStream ois = new ObjectInputStream(fis);
			Werk.werk = (Werk) ois.readObject();
			ois.close();
			schichten.setDisable(false);
			;
			busse.setDisable(false);
			linie.setDisable(false);
			initLinien();
			busInit();

		} catch (FileNotFoundException e) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Infos");
			alert.setHeaderText(
					"Zu dem Werk: " + Werk.werk.getWerkBezeichnung() + " wurde keine Automatisierung gefunden");
			/* alert.setContentText("You didn't select a file!"); */
			alert.showAndWait();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		busseLinie.setOnMouseClicked(ev -> {
			busLinieZuordnen();
		});
		schichtenLinie.setOnMouseClicked(ev -> {
			schichtenLinieRefresh(null);
		});

	}

	@FXML
	public void werkAbsenden(ActionEvent event) {
		if (werkAnzahl.getText().equals("")) {

		} else {
			try {
				Integer.parseInt(werkAnzahl.getText());
			} catch (NumberFormatException e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Fehler");
				alert.setHeaderText(e.getMessage());
				/* alert.setContentText("You didn't select a file!"); */
				alert.showAndWait();
			}
			werkAnzahl.getText();
			if (Werk.werk == null) {
				Werk.werk = new Werk();
			} else if (Werk.werk.getLinieListe() != null) {
				Werk.werk.getLinieListe().clear();
			}

			for (int i = 0; i < Integer.parseInt(werkAnzahl.getText()); i++) {
				// Speicherung der Linien in dem Werk.werk
				Linie line = new Linie();
				line.setName("Linie " + (i + 1));
				Werk.werk.getLinieListe().add(line);
			}
			System.out.println(Werk.werk.getLinieListe().size());
			linie.setDisable(false);
		}
		initLinien();
		// Nachdem eine Linie eingegeben wurde soll der Button zu nochmaligen
		// Absenden deaktiviert werden
		werkAbsendenButton.setDisable(true);

	}

	/**
	 * <b>Zweck</b> <br>
	 * Setzt die Textfelder bei dem Lininen Tab auf null<br>
	 * <br>
	 * Warum:<br>
	 * Nachdem eine Linie gespeichert wird sollen die Eingaben gespeichert
	 * werden und der Nutzer kann dann auch eine neue Linie bearbeiten und sieht
	 * direkt ob dort Daten vorhanden sind
	 */
	void fensterNull() {
		linieAnzahl.setText("");
		linieFarbe.setValue(null);
		linieBeispiel.setStyle(null);
		linieVon.setText("");
		linieUeber.setText("");
		linieNach.setText("");
		


	}

	// Enkapselt da meherer Funktionen diese Methode nutzen
	// Methode gibt die nummer der Linie
	int getAuswahlNummer() {
		int lange = Werk.werk.getLinieListe().size();
		int auswahlNummer = -1;
		for (int i = 0; i < lange; i++) {

			// Wenn name gleich ist
			if (linieLinie.getSelectionModel().getSelectedItem().equals(Werk.werk.getLinieListe().get(i).getName())) {
				auswahlNummer = i;
			}
		}
		return auswahlNummer;
	}

	private void initLinien() {
		// Liste für die Dropdown

		int laenge = 0;
		// !!!Moglicher Fehler wenn keine Linie eingegeben worden sind
		try {
			laenge = Werk.werk.getLinieListe().size();
		} catch (Exception e) {
			e.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Fehler");
			alert.setHeaderText(e.getMessage());
			alert.setContentText("Bitte eine Zahl eingeben");
			alert.showAndWait();
		}

		for (int i = 0; i < laenge; i++) {
			listeMoglichkeit.add(Werk.werk.getLinieListe().get(i).getName());
		}
		linieLinie.setItems(listeMoglichkeit);
		schichtenLinie.setItems(listeMoglichkeit);

	}

	@FXML
	void linieRefresh(ActionEvent event) {
		int auswahlNummer = -1;
		auswahlNummer = getAuswahlNummer();
		if (auswahlNummer == -1) {

		} else {
			if (Werk.werk.getLinieListe().get(auswahlNummer) == null) {

			} else {
				if (Werk.werk.getLinieListe().get(auswahlNummer).getN_ID() == null
						|| Werk.werk.getLinieListe().get(auswahlNummer).getN_ID().equals("")) {
					fensterNull();
				} else {
					linieAnzahl.setText(Werk.werk.getLinieListe().get(auswahlNummer).getAnzahlDerBusse());
					// linieFarbe.setValue(Werk.werk.getLinieListe().get(auswahlNummer).getColor());

					linieVon.setText(Werk.werk.getLinieListe().get(auswahlNummer).getVon());
					linieUeber.setText(Werk.werk.getLinieListe().get(auswahlNummer).getUeber());
					linieNach.setText(Werk.werk.getLinieListe().get(auswahlNummer).getNach());
					linieFarbe.setValue(Werk.werk.getLinieListe().get(auswahlNummer).getFarbe());
					
					try {
						linieBeispiel.setStyle(
								"-fx-background-color: " + Werk.werk.getLinieListe().get(auswahlNummer).getFarbeAlsHex());
					} catch (Exception e) {
						// TODO: handle exception
					}

				}

			}

		}

	}

	@FXML
	public void linieFarbeAuswahl(ActionEvent event) {
		String farbeAlsHex = "lightgray;";
		if(linieFarbe.getSelectionModel().getSelectedItem()!=null){
			switch (linieFarbe.getSelectionModel().getSelectedItem()) {
			case "Cyan":
				farbeAlsHex = "ccffffff";
				break;
			case "Hellblau":
				farbeAlsHex = "cce6ffff";
				break;
			case "Rosa":
				farbeAlsHex = "e6ccffff";
				break;
			case "Orange":
				farbeAlsHex = "ffccb3ff";
				break;
			case "Gelb":
				farbeAlsHex = "ffffb3ff";
				break;

			default:
				farbeAlsHex = "";
			}
		}
		

		linieBeispiel.setStyle("-fx-background-color: " + farbeAlsHex + ";");

	}

	@FXML
	void linieSpeichern(ActionEvent event) {
		// Auf welcher Linie
		int auswahlNummer = getAuswahlNummer();
		if (auswahlNummer == -1) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Fehler");
			// alert.setHeaderText(e.getMessage());
			alert.setContentText("Fehler");
			alert.showAndWait();
		} else {

			Werk.werk.getLinieListe().get(auswahlNummer).setAnzahlDerBusse(linieAnzahl.getText());
			if (linieFarbe.getValue().toString().substring(2) == null) {

			} else {
				Werk.werk.getLinieListe().get(auswahlNummer).setFarbe(linieFarbe.getValue().toString().substring(2));
			}

			Werk.werk.getLinieListe().get(auswahlNummer).setFarbe(linieFarbe.getSelectionModel().getSelectedItem());
			Werk.werk.getLinieListe().get(auswahlNummer).setN_ID(Werk.werk.getnID());
			Werk.werk.getLinieListe().get(auswahlNummer).setVon(linieVon.getText());
			Werk.werk.getLinieListe().get(auswahlNummer).setUeber(linieUeber.getText());
			Werk.werk.getLinieListe().get(auswahlNummer).setNach(linieNach.getText());
			Werk.werk.getLinieListe().get(auswahlNummer).getBusListe().clear();

			busseLinieHinzufugen(auswahlNummer);

			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Erfolg ");
			alert.setHeaderText("Daten für Linie" + (auswahlNummer + 1) + " gepseichert");
			/* alert.setContentText("You didn't select a file!"); */
			alert.showAndWait();
			// Fenster wird auf null gesetzt bzw alle Werte
			fensterNull();
			linieLinie.setValue("");
			// Freischalten des BusseTabs
			busse.setDisable(false);
			// Bus tab init
			busInit();
		}
		// Zum testen
		// System.out.println(Werk.werk.getLinieListe().get(auswahlNummer).getUeber());
		// System.out.println(Werk.werk.getLinieListe().get(auswahlNummer).getN_ID());

	}

	private void busseLinieHinzufugen(int linienNummer) {
		int anzahlDerBusse;
		anzahlDerBusse = Integer.parseInt(Werk.werk.getLinieListe().get(linienNummer).getAnzahlDerBusse());
		System.out.println("anzahl der busse : " + anzahlDerBusse);
		for (int i = 0; i < anzahlDerBusse; i++) {
			Busse b = new Busse();
			b.setName("Bus " + (i + 1));
			Werk.werk.getLinieListe().get(linienNummer).getBusListe().add(b);

		}

	}

	/**
	 * Zweck: <br>
	 * Soll das Pane/Tab Bus alle informationen übergeben <br>
	 * <br>
	 * Warum: <br>
	 * Der Tab hat somit seine eigene Methode in dem alle Objekte gesettet/init
	 * werden Soll zur besseren Übersicht dienen <br>
	 * Methode die vorher aufgherufen werden muss ist die linieSpeichern()
	 * Methode
	 *
	 */
	private void busInit() {
		busseLinie.setItems(listeMoglichkeit);

	}

	/**
	 * Zweck: <br>
	 * Soll die Busse für die bestimmte Linie in der Combobox anzeigen <br>
	 * <br>
	 * Warum: <br>
	 * Die Combobox wo die Busse angezeigt werden muss sich immer nach der
	 * aktuellen Linen anpassen
	 */
	private void busLinieZuordnen() {
		ObservableList<String> busseBusListe = FXCollections.observableArrayList();
		String bussLinie = busseLinie.getSelectionModel().getSelectedItem();
		bussLinie = bussLinie.replaceAll("[a-zA-Z ]{1,}", "");
		// Soll die Zahl der Linie enthalten
		int linieInt = -1;

		try {
			linieInt = Integer.parseInt(bussLinie);
		} catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Fehler");
			alert.setHeaderText(e.getMessage());
			/* alert.setContentText("You didn't select a file!"); */
			alert.showAndWait();
		}
		System.out.println(linieInt);
		if ((Werk.werk.getLinieListe().get(linieInt - 1).getAnzahlDerBusse()) == null) {
			busseBus.setItems(null);
		}
		int anzahlBusse = Integer.parseInt(Werk.werk.getLinieListe().get(linieInt - 1).getAnzahlDerBusse());
		System.out.println(anzahlBusse);
		System.out.println(Werk.werk.getLinieListe().get(linieInt - 1).getBusListe().size());
		if (linieInt == -1) {

		} else {
			for (int i = 0; i < anzahlBusse; i++) {
				Busse b = Werk.werk.getLinieListe().get(linieInt - 1).getBusListe().get(i);
				busseBusListe.add(b.getName());
			}
		}
		busseBus.setItems(null);
		busseBus.setItems(busseBusListe);

	}

	/**
	 * FXML Event für den Button ruft eine vorhandene Methode auf
	 * 
	 * @param event
	 */
	@FXML
	void busseLinieRefresh(ActionEvent event) {
		busLinieZuordnen();
	}

	@FXML
	void busseRefresh(ActionEvent event) {
		int busNummer = getBusseBusNummer();
		int linieNummer = getBusseLinieNummer();
		Busse b = Werk.werk.getLinieListe().get(linieNummer).getBusListe().get(busNummer);
		busseArt.setText(b.getArt());
		busseFahrer.setText(b.getFahrer());
		busseGroesse.setText(b.getGroesse());
		busseKennzeichen.setText(b.getKennzeichen());
		bussePreis.setText(b.getPreisProTag());
		busseUnternehmer.setText(b.getUnternehmer());
	}

	@FXML
	void busseSpeichern(ActionEvent event) {

		Busse aktuellerBus = Werk.werk.getLinieListe().get(getBusseLinieNummer()).getBusListe()
				.get(getBusseBusNummer());
		aktuellerBus.setArt(busseArt.getText());
		aktuellerBus.setFahrer(busseFahrer.getText());
		aktuellerBus.setGroesse(busseGroesse.getText());
		aktuellerBus.setKennzeichen(busseKennzeichen.getText());
		aktuellerBus.setPreisProTag(bussePreis.getText());
		aktuellerBus.setUnternehmer(busseUnternehmer.getText());
		fensterBusNull();
		schichten.setDisable(false);
	}

	private void fensterBusNull() {
		busseArt.setText("");
		busseFahrer.setText("");
		busseGroesse.setText("");
		busseKennzeichen.setText("");
		bussePreis.setText("");
		busseUnternehmer.setText("");
	}

	int getBusseBusNummer() {
		String busNummerAlsString = "5";
		busNummerAlsString = busseBus.getSelectionModel().getSelectedItem();
		if (busNummerAlsString == null) {

		} else {
			busNummerAlsString = busNummerAlsString.replaceAll("[a-zA-Z ]{1,}", "");
		}
		int busNummer = 0;
		try {
			busNummer = Integer.parseInt(busNummerAlsString);
		} catch (Exception e) {
			// TODO: handle exception
		}

		busNummer--;
		return busNummer;
	}

	int getBusseLinieNummer() {
		String linienNummerAlsString = busseLinie.getSelectionModel().getSelectedItem();
		linienNummerAlsString = linienNummerAlsString.replaceAll("[a-zA-Z ]{1,}", "");
		int linienNummer = Integer.parseInt(linienNummerAlsString);
		linienNummer--;
		return linienNummer;
	}

	int getSchichtenBusNummer() {
		String busNummerAlsString = "5";
		busNummerAlsString = schichtenBus.getSelectionModel().getSelectedItem();
		busNummerAlsString = busNummerAlsString.replaceAll("[a-zA-Z ]{1,}", "");
		int busNummer = Integer.parseInt(busNummerAlsString);
		busNummer--;
		return busNummer;
	}

	int getSchichtenLinieNummer() {
		String linienNummerAlsString = schichtenLinie.getSelectionModel().getSelectedItem();
		linienNummerAlsString = linienNummerAlsString.replaceAll("[a-zA-Z ]{1,}", "");
		int linienNummer = Integer.parseInt(linienNummerAlsString);
		linienNummer--;
		return linienNummer;
	}

	@FXML
	void schichtenLinieRefresh(ActionEvent event) {
		ObservableList<String> schichtenBusListe = FXCollections.observableArrayList();
		int anzahlBusse = Werk.werk.getLinieListe().get(getSchichtenLinieNummer()).getBusListe().size();
		for (int i = 0; i < anzahlBusse; i++) {
			schichtenBusListe
					.add(Werk.werk.getLinieListe().get(getSchichtenLinieNummer()).getBusListe().get(i).getName());
		}

		schichtenBus.setItems(schichtenBusListe);
	}

	@FXML
	void schichtenBusRefresh(ActionEvent event) {
		Busse b = Werk.werk.getLinieListe().get(getSchichtenLinieNummer()).getBusListe().get(getSchichtenBusNummer());
		if (b == null || b.getSchicht() == null) {

		} else {
			schichtenBusSetzen(b);
		}
	}

	private void schichtenBusSetzen(Busse b) {
		Schichten schicht = b.getSchicht();

		schichtenMo.setSelected(schicht.isMontag());
		schichtenDi.setSelected(schicht.isDienstag());
		schichtenMi.setSelected(schicht.isMittwoch());
		schichtenDo.setSelected(schicht.isDonnerstag());
		schichtenFr.setSelected(schicht.isFreitag());
		schichtenSa.setSelected(schicht.isSamstag());
		schichtenSo.setSelected(schicht.isSonntag());

		schichtenFruehHin.setText(schicht.getFruhHin());
		schichtenFruehTz.setText(schicht.getFruhTeil());
		schichtenFruehZu.setText(schicht.getFruhZu());
		schichtenSpaetHin.setText(schicht.getSpatHin());
		schichtenSpaetTz.setText(schicht.getSpatTeil());
		schichtenSpaetZu.setText(schicht.getSpatZu());
		schichtenNachtHin.setText(schicht.getNachtHin());
		schichtenNachtZu.setText(schicht.getNachtZu());
		schichtenNachtTz.setText(schicht.getNachtTeil());
		schichtenWoche.setValue(schicht.getAnfangsdatum());

	}

	@FXML
	public void schichtenSpeichern(ActionEvent event) {
		int linie = getSchichtenLinieNummer();
		int bus = getSchichtenBusNummer();
		Schichten schicht = new Schichten();
		schicht.setFruhHin(schichtenFruehHin.getText());
		schicht.setFruhTeil(schichtenFruehTz.getText());
		schicht.setFruhZu(schichtenFruehZu.getText());
		schicht.setNachtHin(schichtenNachtHin.getText());
		schicht.setNachtTeil(schichtenNachtTz.getText());
		schicht.setNachtZu(schichtenNachtZu.getText());
		schicht.setSpatHin(schichtenSpaetHin.getText());
		schicht.setSpatTeil(schichtenSpaetTz.getText());
		schicht.setSpatZu(schichtenSpaetZu.getText());
		setDays(linie, bus, schicht);
		Werk.werk.getLinieListe().get(linie).getBusListe().get(bus).setSchicht(schicht);

		String werkBez = Werk.werk.getWerkBezeichnung();
		File f = null;
		try {
			f = new File(new URI("file:///M:/0.Share/SET/Werke/" + werkBez + ".ser"));
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block

		}
		// File f = new File(werkBez+".ser");
		FileOutputStream fos;
		ObjectOutputStream oos;
		try {
			fos = new FileOutputStream(f);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(Werk.werk);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void setDays(int linie, int bus, Schichten schicht) {
		schicht.setMontag(schichtenMo.isSelected());
		schicht.setDienstag(schichtenDi.isSelected());
		schicht.setMittwoch(schichtenMi.isSelected());
		schicht.setDonnerstag(schichtenDo.isSelected());
		schicht.setFreitag(schichtenFr.isSelected());
		schicht.setSamstag(schichtenSa.isSelected());
		schicht.setSonntag(schichtenSo.isSelected());
		if (schichtenWoche.getValue() != null) {
			schicht.setAnfangsdatum(schichtenWoche.getValue());
		}

	}

}
