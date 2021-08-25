package application.dispo;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Disposition extends AnchorPane {

	public Disposition() {
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
	private TextField entfernungUmkreis;

	@FXML
	private TableColumn<?, ?> mail;

	@FXML
	private TableColumn<?, ?> chef;

	@FXML
	private TextField plzUmkreis;

	@FXML
	private Label ergebnisse;

	@FXML
	private ComboBox<String> dropdown;

	@FXML
	private TableColumn<?, ?> ort;

	@FXML
	private TableColumn<?, ?> mobil;

	@FXML
	private TableColumn<?, ?> uid;

	@FXML
	private TableColumn<?, ?> telefon;

	@FXML
	private TableColumn<?, ?> name;

	@FXML
	private TableColumn<?, ?> besonderheiten;

	@FXML
	private TableColumn<?, ?> bewertung;

	@FXML
	private TableColumn<?, ?> plz;

	@FXML
	private TextField dropdownBedingung;

	@FXML
	private TextField bisPlatz;

	@FXML
	private TableColumn<?, ?> busPlatz;

	@FXML
	private TableColumn<?, ?> busTyp;
	@FXML
	private TableColumn<?, ?> busFarbeTabelle;

	@FXML
	private ComboBox<String> busFarbe;

	@FXML
	private TableView<Busse> busTabelle;

	@FXML
	private Button busReset;

	@FXML
	private Button busHinzu;

	@FXML
	private TextField vonPlatz;
	 ArrayList<String> nameListe = new ArrayList<>();

	@FXML
	private ComboBox<String> dropdownBus;
	ObservableList<Busse> listBusse = FXCollections.observableArrayList();
	@FXML
	TableView<Unternehmen> tabelle;
	ObservableList<String> options = FXCollections.observableArrayList("Name", "Ort", "E-Mail");
	ObservableList<String> optionsBusse = FXCollections.observableArrayList("Reisebus", "Midibus", "Kleinbus",
			"Doppeldecker", "Linienbus", "Ueberlandbus/Kombibus", "MiniVan", "VIP Kleinbus", "VIP Reisebus",
			"VIP Doppeldecker", "VIP MiniVan");
	ObservableList<String> optionsFarbe = FXCollections.observableArrayList("", "Wei�", "Schwarz", "Blau", "Gelb",
			"Gr�n", "Rot", "Silber", "Grau");

	public void initialize(URL location, ResourceBundle resources) {
		
		//plzRunterladen();
		datenSetzen();
		TableView<Unternehmen> tableview = getTabelle();
		tableview.getSelectionModel().setCellSelectionEnabled(true);
		tableview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		// Methode f�r den doppelklick
		tableview.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent ee) {
				if (ee.isPrimaryButtonDown() && ee.getClickCount() == 2) {
					// Gives the uid to popUp so it opens a window with specific
					// information
					popUp(tableview.getSelectionModel().getSelectedItem());
				}

			}
		});
		enterSuchen();
		// Rechtsklick f�r Methode
		ContextMenu cm = new ContextMenu();
		MenuItem mi1 = new MenuItem("Unternehmen bearbeiten");
		
		cm.getItems().add(mi1);

		MenuItem mi2 = new MenuItem("Unternehmen anzeigen");
		cm.getItems().add(mi2);
		MenuItem mi3 = new MenuItem("E-Mail senden");
		cm.getItems().add(mi3);
		MenuItem mi4 = new MenuItem("Busse anzeigen");
		cm.getItems().add(mi4);
		MenuItem mi5 = new MenuItem("E-Mail kopieren");
		cm.getItems().add(mi5);
		//cm.setImpl_showRelativeToWindow(true);
		tableview.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				if (t.getButton() == MouseButton.SECONDARY) {
					cm.show(tableview, t.getScreenX(), t.getScreenY());
					mi1.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent e) {
							UidObject.unternehmen = tableview.getSelectionModel().getSelectedItem();
							unternehmenBearbeiten(e);
						}
					});
					// tableview.getSelectionModel().getSelectedItem()
					mi2.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent e) {
							popUp(tableview.getSelectionModel().getSelectedItem());
						}
					});

					mi3.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent e) {
							String trenner = ";";
							String email = "";
							ObservableList<Unternehmen> unternehmen = tableview.getSelectionModel().getSelectedItems();

							for (Unternehmen unter : unternehmen) {
								email = email + unter.getMail().replaceAll("\\s+", "") + trenner;
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

					mi4.setOnAction(event -> {
						busseAnzeigen(tableview.getSelectionModel().getSelectedItem());
					});
					mi5.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent e) {
							emailKopieren();
						}
					});
				}
			}
		});
	}


	void enterSuchen() {
		Parent root = ergebnisse.getParent();
		root.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {

				try {
					suchen(null);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
	}

	void emailKopieren() {
		TableView<Unternehmen> tableview = getTabelle();
		String trenner = ";";
		String email = "";
		ObservableList<Unternehmen> unternehmen = tableview.getSelectionModel().getSelectedItems();

		for (Unternehmen unter : unternehmen) {
			email = email + unter.getMail().replaceAll("\\s+", "") + trenner;
		}
		try {
			final ClipboardContent content = new ClipboardContent();
			content.putString(email.toString());
			Clipboard.getSystemClipboard().setContent(content);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(AlertType.ERROR);

			alert.setHeaderText("Fehler!");
			alert.setContentText("Fehlermeldung" + e1.getMessage());
			alert.showAndWait();

		}

	}

	/**
	 * Zweck:</br>
	 * Entlastung der init Methode
	 * 
	 * <p>
	 * Mappt die Daten f�r die Tabellen
	 * </p>
	 */
	void datenSetzen() {
		busFarbeTabelle.setCellValueFactory(new PropertyValueFactory<>("farbe"));
		busPlatz.setCellValueFactory(new PropertyValueFactory<>("PlatzVonBis"));
		busTyp.setCellValueFactory(new PropertyValueFactory<>("typ"));
		// Otionen werden gesetzt
		dropdownBus.setItems(optionsBusse);
		dropdown.setItems(options);
		busFarbe.setItems(optionsFarbe);
		// Mapping der Tabelle
		uid.setCellValueFactory(new PropertyValueFactory<>("uid"));
		plz.setCellValueFactory(new PropertyValueFactory<>("pLZ"));
		ort.setCellValueFactory(new PropertyValueFactory<>("Ort"));
		name.setCellValueFactory(new PropertyValueFactory<>("Name"));
		mail.setCellValueFactory(new PropertyValueFactory<>("Mail"));
		telefon.setCellValueFactory(new PropertyValueFactory<>("Telefon"));
		mobil.setCellValueFactory(new PropertyValueFactory<>("mobil"));
		chef.setCellValueFactory(new PropertyValueFactory<>("chef"));

		besonderheiten.setCellValueFactory(new PropertyValueFactory<>("besonderheiten"));
		bewertung.setCellValueFactory(new PropertyValueFactory<>("distanz"));
	}

	void busseAnzeigen(Unternehmen e) {
		Stage primaryStage = new Stage();
		Parent root = null;
		UidObject.unternehmen = e;
		try {
			root = FXMLLoader.load(getClass().getResource("Busse.fxml"));
			root.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setTitle("Businformationen");
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	@FXML
	void suchen(ActionEvent event) throws SQLException {

		ObservableList<Unternehmen> listeUnternehmen = null;
		if (dropdownBedingung.getText().equals("") || dropdownBedingung.getText() == null) {
			// Umkreis ohne Busse
			switch (listBusse.size()) {
			case 0:
				listeUnternehmen = umkreisDaten(plzUmkreis.getText(), entfernungUmkreis.getText());
				break;
			case 1:
				listeUnternehmen = umkreisMitEinemBus(plzUmkreis.getText(), entfernungUmkreis.getText());
				System.out.println("1");
				break;

			case 2:
				listeUnternehmen = umkreisMitZweiBus(plzUmkreis.getText(), entfernungUmkreis.getText());
				System.out.println("2");

				break;
			case 3:
				listeUnternehmen = umkreisMitDreiBus(plzUmkreis.getText(), entfernungUmkreis.getText());
				System.out.println("3");
				break;

			default:
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText("Bitte nur maximal drei Busse ausw�hlen");
				/* alert.setContentText("You didn't select a file!"); */
				alert.showAndWait();
			}

		} else {
			
			listeUnternehmen = daten(dropdown.getSelectionModel().getSelectedItem(), dropdownBedingung.getText());
		}

		ergebnisse.setText(listeUnternehmen.size() + "");
		tabelle.setItems(listeUnternehmen);

	}

	/**
	 * Methode zum f�llen der Unternehmen die f�r die bestimmten Bedingung zu
	 * treffen
	 * 
	 * @param bedingung
	 *            ->besteht aus der ersten Suchbedingung { plz, Name, etc}
	 * @param wertEins
	 *            -> folgender Wert f�r die erste Suchbedingung
	 * @param bedingungZwei
	 *            -> besteht aus zweiter Suchbedingung, kann optinal seinn
	 * @param wertZwei
	 *            -> Wert f�r die zweite Suchbedingung
	 * @return gibt die Liste mit den Unternhmen f�r die Bedingungen wieder
	 */
	public ObservableList<Unternehmen> daten(String bedingung, String wertEins) {
		ObservableList<Unternehmen> list = FXCollections.observableArrayList();

		try {

			ConnectMe c = new ConnectMe();
			Statement stmt = c.getStatement();
			String sql;
			//Bei Name soll Stickpunktsuche gemacht werden 
			if (bedingung.equals("Name")) {
				sql = "select * from plz.unternehmen where " + bedingung + " like '%" + wertEins + "%' ";
			}else{
				 sql = "select * from plz.unternehmen where " + bedingung + " like '" + wertEins + "%' ";
			}
			
			ResultSet rs = stmt
					.executeQuery(sql);
			while (rs.next()) {

				Unternehmen e = new Unternehmen(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
						rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13));
				list.add(e);

			}

			c.closeConnection();

		} catch (Exception e) {

			e.printStackTrace();

		}
		return list;
	}

	/**
	 * Mehtoder f�r die Umkreissuche im ersten schritt wird die lat und lon
	 * einer gesuchten PLZ gegeben und dann anhand dieser Werte ein Inner join
	 * bei den beiden Tabellen gemacht um die Unternehmensdaten zu bekommen
	 * 
	 * @param plz
	 *            -> f�r den ersten schritt ben�tigt
	 * @param umkreis
	 *            -> f�r den 2 Schritt zur berechnung der lat und lon
	 * @return gibt eine Liste mit Unternehmen die im Umkreis sind
	 * @throws SQLException
	 *             unbehandelt
	 */
	public ObservableList<Unternehmen> umkreisDaten(String plz, String umkreis) throws SQLException {
		double lat = 0.0;
		double lon = 0.0;
		ObservableList<Unternehmen> list = FXCollections.observableArrayList();
		ConnectMe c = new ConnectMe();
		Statement stmt = c.getStatement();
		// 1. Schritt lon und lat
		ResultSet rs1 = stmt.executeQuery("Select lat,lon from plz.koordinaten where plz =" + plz + "; ");

		while (rs1.next()) {
			lat = rs1.getDouble(1);
			lon = rs1.getDouble(2);
		}

		ResultSet rs = stmt.executeQuery("Select Distinct unternehmen.*, Distanz From  Unternehmen Left Outer Join"
				+ " (SELECT plz,ROUND((6371 * acos(cos(radians(  " + lat + ")) * cos(radians( lat)) *"
				+ " cos(radians( lon) - radians( " + lon + " )) + sin(radians( " + lat + " )) *"
				+ " sin(radians( lat)))),0) as Distanz from plz.koordinaten having Distanz <=" + umkreis + ")"
				+ " As SQ on SQ.plz=unternehmen.plz where Distanz <=" + umkreis + " group by unternehmen.U_ID;");
		System.out.println(("Select Distinct unternehmen.*, Distanz From  Unternehmen Left Outer Join"
				+ " (SELECT plz,ROUND((6371 * acos(cos(radians(  " + lat + ")) * cos(radians( lat)) *"
				+ " cos(radians( lon) - radians( " + lon + " )) + sin(radians( " + lat + " )) *"
				+ " sin(radians( lat)))),0) as Distanz from plz.koordinaten having Distanz <=" + umkreis + ")"
				+ " As SQ on SQ.plz=unternehmen.plz where Distanz <=" + umkreis + " group by unternehmen.U_ID;"));
		while (rs.next()) {

			Unternehmen e = new Unternehmen(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
					rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13));
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
			root.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
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
		UidObject.unternehmen = new Unternehmen("", "", "", "", "", "", "", "", "", "", "", "", "");
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("hinzufuegen.fxml"));

		root.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());

		primaryStage.setTitle("Unternehmen hinzufuegen");
		primaryStage.setResizable(false);
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	public TableView<Unternehmen> getTabelle() {
		return tabelle;
	}

	void popUp(Unternehmen uid) {
		Parent root;
		Stage primaryStage;
		UidObject.unternehmen = uid;
		try {
			primaryStage = new Stage();
			root = FXMLLoader.load(getClass().getResource("Popup.fxml"));
			root.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setTitle("Informationen");
			primaryStage.setResizable(false);
			primaryStage.setScene(new Scene(root));
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void busHinzu(ActionEvent event) {
		String typ = dropdownBus.getSelectionModel().getSelectedItem();

		String platz = vonPlatz.getText();
		String farbe = busFarbe.getSelectionModel().getSelectedItem();
		if (farbe == null) {
			farbe = "";
		}
		String regexZahl = "^([0-9]{1,2})$";
		if (platz.matches(regexZahl)) {

		}
		Busse b = new Busse(null, null, typ, platz, farbe, null, null);
		b.setPlatzBis(bisPlatz.getText());
		listBusse.add(b);
		busTabelle.setItems(listBusse);

		dropdownBus.getSelectionModel().clearSelection();
		vonPlatz.setText("");
		bisPlatz.setText("");
		busFarbe.getSelectionModel().clearSelection();
	}

	@FXML
	void busReset(ActionEvent event) {
		listBusse.clear();
		busTabelle.setItems(listBusse);
	}

	/**
	 * Zweck:<br>
	 * Wenn ein bus gew�hlt wird soll ein Join gemacht werden.<br>
	 * <br>
	 * Warum:<br>
	 * Mir ist bis jetzt keine bessere SQL Suche eingefallen die performant und
	 * zordnung schafft und gleichzeitig autmatisiert werden kann.
	 * 
	 * @param plz
	 * @param umkreis
	 * @return
	 * @throws SQLException
	 */
	public ObservableList<Unternehmen> umkreisMitEinemBus(String plz, String umkreis) throws SQLException {
		double lat = 0.0;
		double lon = 0.0;
		ObservableList<Unternehmen> list = FXCollections.observableArrayList();
		ConnectMe c = new ConnectMe();
		Statement stmt = c.getStatement();
		// 1. Schritt lon und lat
		ResultSet rs1 = stmt.executeQuery("Select lat,lon from plz.koordinaten where plz =" + plz + "; ");

		while (rs1.next()) {
			lat = rs1.getDouble(1);
			lon = rs1.getDouble(2);
		}
		Busse b = listBusse.get(0);
		b.getTyp();

		ResultSet rs = stmt.executeQuery("SELECT  Da.* FROM ((SELECT DISTINCT unternehmen.*, "
				+ "Distanz FROM Unternehmen LEFT OUTER JOIN (SELECT plz,ROUND((6371 * ACOS(COS(RADIANS(" + lat + ")) *"
				+ " COS(RADIANS(lat)) * COS(RADIANS(lon) - RADIANS(" + lon + ")) + SIN(RADIANS(" + lat + ")) * "
				+ "SIN(RADIANS(lat)))), 0) AS Distanz FROM plz.koordinaten HAVING Distanz <= " + umkreis
				+ ") AS SQ ON SQ.plz" + " = unternehmen.plz WHERE Distanz <= " + umkreis
				+ ") AS Da inner join (Select * From busse" + " where typ ='" + b.getTyp() + "' and groesse between "
				+ b.getGroesse() + " and " + b.getPlatzBis() + " and Farbe like '%" + b.getFarbe()
				+ "') as T1 on Da.U_ID = T1.U_ID )group by Da.U_ID;");
		System.out.println();
		while (rs.next()) {

			Unternehmen e = new Unternehmen(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
					rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13));
			e.setDistanz(rs.getString(14));
			list.add(e);
		}

		return list;
	}

	/**
	 * Zweck:<br>
	 * Wenn 2 busse gew�hlt wird soll ein Join gemacht werden.<br>
	 * <br>
	 * Warum:<br>
	 * Mir ist bis jetzt keine bessere SQL Suche eingefallen die performant und
	 * zordnung schafft und gleichzeitig autmatisiert werden kann.
	 * 
	 * @param plz
	 * @param umkreis
	 * @return
	 * @throws SQLException
	 */
	public ObservableList<Unternehmen> umkreisMitZweiBus(String plz, String umkreis) throws SQLException {
		double lat = 0.0;
		double lon = 0.0;
		ObservableList<Unternehmen> list = FXCollections.observableArrayList();
		ConnectMe c = new ConnectMe();
		Statement stmt = c.getStatement();
		// 1. Schritt lon und lat
		ResultSet rs1 = stmt.executeQuery("Select lat,lon from plz.koordinaten where plz =" + plz + "; ");

		while (rs1.next()) {
			lat = rs1.getDouble(1);
			lon = rs1.getDouble(2);
		}
		Busse bs1 = listBusse.get(0);
		Busse bs2 = listBusse.get(1);

		ResultSet rs = stmt.executeQuery("SELECT Da.* FROM ((SELECT DISTINCT unternehmen.*, Distanz FROM Unternehmen"
				+ " LEFT OUTER JOIN (SELECT plz,ROUND((6371 * ACOS(COS(RADIANS(" + lat + ")) * COS(RADIANS(lat))"
				+ " * COS(RADIANS(lon) - RADIANS(" + lon + ")) + SIN(RADIANS(" + lat + ")) * SIN(RADIANS(lat)))), 0)"
				+ " AS Distanz FROM plz.koordinaten HAVING Distanz <= " + umkreis
				+ ") AS SQ ON SQ.plz = unternehmen.plz WHERE Distanz <= " + umkreis + ")"
				+ " AS Da inner join (Select T1.U_ID From(Select * From busse where typ ='" + bs1.getTyp() + "' "
				+ "and groesse between " + bs1.getPlatzVonBis() + " and " + bs1.getPlatzBis() + " and Farbe like '%"
				+ bs1.getFarbe() + "') as T1 inner join (Select * From busse " + "where typ ='" + bs2.getTyp()
				+ "' and groesse between " + bs2.getPlatzVonBis() + " and " + bs2.getPlatzBis() + " and Farbe like '%"
				+ bs2.getFarbe() + "') as T2 on T1.U_ID = T2.U_ID )"
				+ " as D1 on D1.U_ID = Da.U_ID) group by Da.U_ID;");
		
		
		
		
		
		
		
		System.out.println("SELECT Da.* FROM ((SELECT DISTINCT unternehmen.*, Distanz FROM Unternehmen"
				+ " LEFT OUTER JOIN (SELECT plz,ROUND((6371 * ACOS(COS(RADIANS(" + lat + ")) * COS(RADIANS(lat))"
				+ " * COS(RADIANS(lon) - RADIANS(" + lon + ")) + SIN(RADIANS(" + lat + ")) * SIN(RADIANS(lat)))), 0)"
				+ " AS Distanz FROM plz.koordinaten HAVING Distanz <= " + umkreis
				+ ") AS SQ ON SQ.plz = unternehmen.plz WHERE Distanz <= " + umkreis + ")"
				+ " AS Da inner join (Select T1.U_ID From(Select * From busse where typ ='" + bs1.getTyp() + "' "
				+ "and groesse between " + bs1.getGroesse() + " and " + bs1.getPlatzBis() + " and Farbe like '%"
				+ bs1.getFarbe() + "') as T1 inner join (Select * From busse " + "where typ ='" + bs2.getTyp()
				+ "' and groesse between " + bs2.getGroesse() + " and " + bs2.getPlatzBis() + " and Farbe like '%"
				+ bs2.getFarbe() + "') as T2 on T1.U_ID = T2.U_ID )"
				+ " as D1 on D1.U_ID = Da.U_ID) group by Da.U_ID;");
		while (rs.next()) {

			Unternehmen e = new Unternehmen(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
					rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13));
			e.setDistanz(rs.getString(14));
			list.add(e);
		}

		return list;
	}

	/**
	 * Zweck:<br>
	 * Wenn ein bus gew�hlt wird soll ein Join gemacht werden.<br>
	 * <br>
	 * Warum:<br>
	 * Mir ist bis jetzt keine bessere SQL Suche eingefallen die performant und
	 * zordnung schafft und gleichzeitig autmatisiert werden kann.
	 * 
	 * @param plz
	 * @param umkreis
	 * @return
	 * @throws SQLException
	 */
	public ObservableList<Unternehmen> umkreisMitDreiBus(String plz, String umkreis) throws SQLException {
		double lat = 0.0;
		double lon = 0.0;
		ObservableList<Unternehmen> list = FXCollections.observableArrayList();
		ConnectMe c = new ConnectMe();
		Statement stmt = c.getStatement();
		// 1. Schritt lon und lat
		ResultSet rs1 = stmt.executeQuery("Select lat,lon from plz.koordinaten where plz =" + plz + "; ");

		while (rs1.next()) {
			lat = rs1.getDouble(1);
			lon = rs1.getDouble(2);
		}
		Busse b1 = listBusse.get(0);
		Busse b2 = listBusse.get(1);
		Busse b3 = listBusse.get(2);
		ResultSet rs = stmt.executeQuery("SELECT Da.* FROM((SELECT DISTINCT unternehmen.*, Distanz FROM"
				+ " Unternehmen LEFT OUTER JOIN (SELECT plz,ROUND((6371 * ACOS(COS(RADIANS(" + lat + "))"
				+ " * COS(RADIANS(lat)) * COS(RADIANS(lon) - RADIANS(" + lon + ")) + SIN(RADIANS(" + lat + "))"
				+ " * SIN(RADIANS(lat)))), 0) AS Distanz FROM plz.koordinaten HAVING Distanz <= " + umkreis + ")"
				+ " AS SQ ON SQ.plz = unternehmen.plz WHERE Distanz <= " + umkreis + ") AS Da inner join "
				+ "(Select T1.U_ID From(Select * From busse where typ ='" + b1.getTyp() + "' and groesse between " + ""
				+ b1.getGroesse() + " and " + b1.getPlatzBis() + " and Farbe like '%" + b1.getFarbe() + "' )"
				+ " as T1 inner join(Select * From busse where typ ='" + b2.getTyp() + "' and groesse between "
				+ b2.getGroesse() + "" + " and " + b2.getPlatzBis() + " and Farbe like '%" + b2.getFarbe() + "')"
				+ " as T2 on T1.U_ID = T2.U_ID inner join(Select * From busse where typ ='" + b3.getTyp() + "' and "
				+ "groesse between " + b3.getGroesse() + " and " + b3.getPlatzBis() + " and farbe like '%"
				+ b3.getFarbe() + "')" + " as T3 on T2.U_ID = T3.U_ID) as E1 ) group by E1.U_ID;");

		while (rs.next()) {

			Unternehmen e = new Unternehmen(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
					rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13));
			e.setDistanz(rs.getString(14));
			list.add(e);
		}

		return list;
	}
	void plzRunterladen(){
    	String sql = "Select plz From plz.koordinaten";
    	ConnectMe c = new ConnectMe();
    	Statement stmt = c.getStatement();
    	ArrayList<String> postalCode = new ArrayList<String>(); 
    	try {
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				postalCode.add(rs.getString(1));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	c.closeConnection();
    	TextFields.bindAutoCompletion(plzUmkreis, postalCode);
    	
    }

	
	

}