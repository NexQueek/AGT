package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import application.admin.Admin;
import application.auskunft.Auskunft;
import application.bewertung.Bewertungen;
import application.dispo.Disposition;
import application.freimeldung.Freimeldung;
import application.marge.Marge;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Menu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Controller {

	@FXML
	private Button bewertungButton;

	@FXML
	private Button zubehoerButton;

	@FXML
	private Button adminButton;

	@FXML
	private BorderPane mainPane;

	@FXML
	private MenuItem hallo;
	@FXML
	private Button auskunftButton;

	@FXML
	private Button margeButton;

	@FXML
	private Button dispoButton;

	@FXML
	public void onBtnAClick() {
		Disposition contentA = new Disposition();
		mainPane.setCenter(contentA);
	}

	@FXML
	public void onBtnCClick() {
		Zubehoer z = new Zubehoer();
		mainPane.setCenter(z);
	}

	@FXML
	public void AuskunftOeffnen(ActionEvent event) {
		Auskunft auskunft = new Auskunft();

		mainPane.setCenter(auskunft);
	}

	@FXML
	private TextField auskunftDatumVon;

	@FXML
	private TextField auskunftPLZ;

	@FXML
	private TextField auskunftDatumBis;

	@FXML
	Label eingeloggt;

	@FXML
	Button freimeldung;

	@FXML
	Label username;

	@FXML
	Menu start;

	@FXML
	ImageView setLogo;

	@FXML
	ImageView agtLogo;

	@FXML Menu helo;

	@FXML Pane fadeMe;

	@FXML
	void auskunft_suchen(ActionEvent event) {

	}

	@FXML
	void auskunft_eintragen(ActionEvent event) {

	}

	@FXML
	void initialize() {
		
		try {

			Image logoEins = new Image(getClass().getResourceAsStream("logoSet.png"));
			Image logoZwei = new Image(getClass().getResourceAsStream("LogoAgt.png"));
			setLogo.setPreserveRatio(true);
			setLogo.setImage(logoEins);
			agtLogo.setPreserveRatio(true);
			agtLogo.setImage(logoZwei);

		} catch (Exception e) {
			e.printStackTrace();
		}
		start.setOnShown(event ->{
			System.out.println(1);
		});
		helo.setOnAction(event ->{
			System.out.println(2);
		});
		Benutzer e = Benutzer.benutz.get(0);
		eingeloggt.setText("eingeloggt als: " + e.getName());
		bewertungButton.setDisable(true);
		adminButton.setDisable(true);
		auskunftButton.setDisable(true);
		dispoButton.setDisable(true);
		margeButton.setDisable(true);
		zubehoerButton.setDisable(true);
		if (e.isAdmin()) {
			adminButton.setDisable(false);
		}
		if (e.isAuskunft()) {
			auskunftButton.setDisable(false);
		}
		if (e.isBewertung()) {
			bewertungButton.setDisable(false);
		}
		if (e.isDispo()) {
			dispoButton.setDisable(false);
		}
		if (e.isMarge()) {
			margeButton.setDisable(false);

		}
		if (e.isZubehor()) {
			zubehoerButton.setDisable(false);
		}
		username.setText(e.getName());
		
	}

	@FXML
	void margeOpen(ActionEvent event) {
		Marge marge = new Marge();
		mainPane.setCenter(marge);
	}

	@FXML
	void halo(ActionEvent event) {
		System.out.println("Hallo");
	}

	@FXML
	public void onBtnBClick() {
		Bewertungen b = new Bewertungen();
		mainPane.setCenter(b);
	}

	@FXML
	void adminOeffnen(ActionEvent event) {
		Admin a = new Admin();
		mainPane.setCenter(a);
	}

	@FXML
	void zurStartseit(ActionEvent event) {
		mainPane.setCenter(username.getParent());
	}

	@FXML
	public void freimelden(ActionEvent event) {
		Freimeldung fr = new Freimeldung();
		mainPane.setCenter(fr);
	}

}