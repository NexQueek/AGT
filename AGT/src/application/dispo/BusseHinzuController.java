package application.dispo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

import application.sql.ConnectMe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class BusseHinzuController {
	static String imagePath = "";
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ComboBox<String> farbe;

	@FXML
	private Label unternehmen;

	@FXML
	private TextField platz;

	@FXML
	private ComboBox<String> typ;

	@FXML
	private TextField reiseleite;

	ObservableList<String> optionsBusse = FXCollections.observableArrayList("Reisebus", "Midibus", "Kleinbus",
			"Doppeldecker", "Linienbus", "Ueberlandbus/Kombibus", "MiniVan", "VIP Kleinbus", "VIP Reisebus",
			"VIP Doppeldecker", "VIP MiniVan");
	ObservableList<String> marken = FXCollections.observableArrayList("MAN","Mercedes","Neoplan","Setra",
			"Bova"," van Hool","Volvo", "Temsa Bus & Coach","Irisbus (Iveco-Gruppe)", "Scania");

	ObservableList<String> optionsFarbe = FXCollections.observableArrayList("", "Weiß", "Schwarz", "Blau", "Gelb",
			"Grün");
	ObservableList<String> jaNe = FXCollections.observableArrayList("Ja", "Nein");

	@FXML
	ImageView ViewMe;
	@FXML
	ComboBox<String> jaNein;
	@FXML
	ComboBox<String> marke;

	/**
	 * Zweck:<br>
	 * Hier werden die Daten für den Bus hochgeladen<br>
	 * <br>
	 * Warum:<br>
	 * Das PreparedStatment wird genutzt um das Bildhinzuzufügen und die Nummer
	 * 1-6 stehen für die Fragezeichen.<br>
	 * Die Fragezeichen sind einfach nur Platzhalter.<br>
	 * Sollte ein Fehler auftreten kommt über einen Alert ein Fenster mit der
	 * Fehlernachricht
	 * 
	 * @param event
	 */
	@FXML
	void einenHinzu(ActionEvent event) {
		ConnectMe c = new ConnectMe();
		Connection con = c.getC();
		System.out.println(imagePath);
		try {
			PreparedStatement pstmt = con.prepareStatement(
					("INSERT INTO busse (U_ID, typ, groesse, reiseleiter, farbe,  bild, branding, marke)  VALUES(?,?,?,?,?,?,?,?);"));
			if (imagePath.equals("")) {
				pstmt.setString(6, null);
			} else {
				InputStream in = new FileInputStream(imagePath);
				pstmt.setBlob(6, in);
			}
			// Nullsetzen des Strings
			imagePath = "";
			pstmt.setString(1, UidObject.unternehmen.getUid());
			pstmt.setString(2, typ.getSelectionModel().getSelectedItem());
			pstmt.setString(3, platz.getText());
			pstmt.setString(4, reiseleite.getText());
			pstmt.setString(5, farbe.getSelectionModel().getSelectedItem());
			pstmt.setString(7, jaNein.getSelectionModel().getSelectedItem());
			pstmt.setString(8, marke.getSelectionModel().getSelectedItem());

			pstmt.execute();
			final Node source = (Node) event.getSource();
			final Stage stage = (Stage) source.getScene().getWindow();
			stage.close();

		} catch (SQLException | FileNotFoundException e) {

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Fehler");
			alert.setContentText(e.getMessage() + "");
			alert.showAndWait();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			c.closeConnection();
		}

	}

	@FXML
	void mehrHinzu(ActionEvent event) {

	}

	@FXML
	void initialize() {
		unternehmen.setText(UidObject.unternehmen.getName());
		typ.setItems(optionsBusse);
		farbe.setItems(optionsFarbe);
		jaNein.setItems(jaNe);
		marke.setItems(marken);
		Busse b = Busse.getB();
		if (b != null) {
			typ.setValue(b.getTyp());
			platz.setText(b.getGroesse());
			reiseleite.setText(b.getEigenschaft1());
			farbe.setValue(b.getFarbe());
			jaNein.setValue(b.getBranding());
			if (b.getBild()==null) {
				
			}else{
				ViewMe.setImage(b.getBild().getImage());
			}
		
			marke.setValue(b.getMarke());
		}

	}

	/**
	 * Zweck:<br>
	 * Button zum hochladen der beim event ein Dateibrowser öffnet und dort aus
	 * der File ein Image macht<br>
	 * <br>
	 * Warum:<br>
	 * Wir brauchen die Bild für das ImageView, damit der Nutzer sieht wie das
	 * Bild aussieht<br>
	 * 
	 * @param event
	 */
	@FXML
	public void bildHoch(ActionEvent event) {
		final FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters()
				.addAll(new FileChooser.ExtensionFilter("Bilder", "*.bmp", "*.png", "*.jpg", "*.gif"));
		File file = fileChooser.showOpenDialog(new Stage());
		if (file != null) {
			String imagepath = "file:" + file.getPath();
			imagePath = file.getPath();
			System.out.println("file:" + imagepath);
			Image image = new Image(imagepath);
			ViewMe.setImage(image);

		} else {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Bitte ein Bild auswählen");
			/* alert.setContentText("You didn't select a file!"); */
			alert.showAndWait();
		}

	}

	/**
	 * Zweck:<br>
	 * Hier soll ein Bus bearbeitet werden -> Problem war das Bild
	 * 
	 * <br>
	 * Warum<br>
	 * Das Bild fall ein neues hochgeladen werden soll musste erst null gesetzt
	 * werden doch dies konte Probleme verursachen und dadurch wird er nun jetzt
	 * er null gesetzt und dann noch mal komplett bearbeitet.
	 * 
	 * @param event
	 */
	@FXML
	public void bearbeiten(ActionEvent event) {
		Busse bus = Busse.getB();
		String s;
		ConnectMe c = new ConnectMe();
		Connection con = c.getC();
		// Falls kein neues Bild hochgeladen wird soll das alte Bild uebernommen
		// werden
		s = "UPDATE `plz`.`busse` SET `U_ID` = ?, " + "`typ` = ?, `groesse` = ?, `reiseleiter` = ?,"
				+ " `farbe` = ?," + " `branding` = ?,`marke` = ?  WHERE (`BUS_ID` = ?);";
		

		try {

			PreparedStatement pstmt = con.prepareStatement((s));

			

			pstmt.setString(1, UidObject.unternehmen.getUid());
			pstmt.setString(2, typ.getSelectionModel().getSelectedItem());
			pstmt.setString(3, platz.getText());
			pstmt.setString(4, reiseleite.getText());
			pstmt.setString(5, farbe.getSelectionModel().getSelectedItem());
			pstmt.setString(6, jaNein.getSelectionModel().getSelectedItem());
			pstmt.setString(7, marke.getSelectionModel().getSelectedItem());
			pstmt.setString(8, bus.getBid());

			pstmt.execute();
			if (!imagePath.equals("")) {
				s = "UPDATE `plz`.`busse` SET  `bild` = ? WHERE (`BUS_ID` = ?);";
				PreparedStatement pstmt1 = con.prepareStatement((s));
				InputStream in = new FileInputStream(imagePath);
				System.out.println(imagePath + "23");
				pstmt1.setString(1, null);
				pstmt1.setString(2, bus.getBid());
				pstmt1.execute();
				pstmt1.setBlob(1, in);
				pstmt1.setString(2, bus.getBid());
				pstmt1.execute();
			}
			final Node source = (Node) event.getSource();
			final Stage stage = (Stage) source.getScene().getWindow();
			stage.close();
			// Nullsetzen des Strings
			imagePath = "";
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
