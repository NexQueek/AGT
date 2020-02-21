package application.tabelle;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.IsoFields;

import application.Benutzer;
import application.sql.ConnectMe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

public class Tabelle {
	@FXML
	private Button hinzu;
	@FXML
	TableColumn<Liste, String> bemerkung;
	@FXML
	TextField kalenderwoche;
	@FXML
	ComboBox<String> dropdownWerk;
	@FXML
	TableColumn<Liste, String> busNr;
	@FXML
	TableColumn<Liste, String> bemerkungen;
	@FXML
	TableColumn<Liste, String> ansprechpartner;
	@FXML
	TableColumn<Liste, String> kurzel;
	@FXML
	TableColumn<Liste, String> KW;
	@FXML
	TableColumn<Liste, String> datum;
	@FXML
	TableColumn<Liste, String> abfahrtort;
	@FXML
	TableColumn<Liste, String> uhrzeit;
	@FXML
	TableColumn<Liste, String> schichtplaner;
	@FXML
	TableColumn<Liste, String> tag;
	@FXML
	TableColumn<Liste, String> fahrer;
	@FXML
	TableColumn<Liste, String> handynr;
	@FXML
	TableColumn<Liste, String> sollBus;
	@FXML
	TableColumn<Liste, String> istBus;
	@FXML
	TableColumn<Liste, String> preisNetto;
	@FXML
	TableColumn<Liste, String> brutto;
	@FXML
	TableColumn<Liste, String> ek;
	@FXML
	TableColumn<Liste, String> marge;
	@FXML
	TableColumn<Liste, String> gesamt;
	@FXML
	TableView<Liste> tabelleEx;
	ObservableList<Liste> listListe = FXCollections.observableArrayList();
	@FXML
	ComboBox<String> dropdownAuftraggeber;
	@FXML TableColumn<Liste, String> linie;

	@FXML
	void initialize() {
		// fid.setCellValueFactory(new PropertyValueFactory<>("fid"));
		// plz.setCellValueFactory(new PropertyValueFactory<>("plz"));
		// ort.setCellValueFactory(new PropertyValueFactory<>("Ort"));
		// r_nummer.setCellValueFactory(new
		// PropertyValueFactory<>("rechnungsnummer"));
		// unternehmen.setCellValueFactory(new
		// PropertyValueFactory<>("unternehmen"));
		// datumvon.setCellValueFactory(new PropertyValueFactory<>("datumVon"));
		// datumbis.setCellValueFactory(new PropertyValueFactory<>("datumBis"));
		// busmodel.setCellValueFactory(new PropertyValueFactory<>("busModel"));
		// busplätze.setCellValueFactory(new
		// PropertyValueFactory<>("busPlatze"));
		// eingetragenvon.setCellValueFactory(new
		// PropertyValueFactory<>("eingetragen"));
		// methodenImplementieren();

		hinzu.setDisable(true);
		if (Benutzer.benutz.get(0).isAdmin()) {
			hinzu.setDisable(false);
		}
		busNr.setCellValueFactory(new PropertyValueFactory<>("busNr"));
		linie.setCellValueFactory(new PropertyValueFactory<>("LinienName"));
		bemerkungen.setCellValueFactory(new PropertyValueFactory<>("bemerkungen"));
		ansprechpartner.setCellValueFactory(new PropertyValueFactory<>("ansprechpartner"));
		kurzel.setCellValueFactory(new PropertyValueFactory<>("kurzel"));
		KW.setCellValueFactory(new PropertyValueFactory<>("KW"));
		datum.setCellValueFactory(new PropertyValueFactory<>("datum"));
		abfahrtort.setCellValueFactory(new PropertyValueFactory<>("abfahrtort"));
		uhrzeit.setCellValueFactory(new PropertyValueFactory<>("uhrzeit"));
		schichtplaner.setCellValueFactory(new PropertyValueFactory<>("schicht"));
		
		;
		tag.setCellValueFactory(new PropertyValueFactory<>("unternehmen"));
		fahrer.setCellValueFactory(new PropertyValueFactory<>("fahrer"));
		handynr.setCellValueFactory(new PropertyValueFactory<>("handynr"));
		sollBus.setCellValueFactory(new PropertyValueFactory<>("sollBus"));
		istBus.setCellValueFactory(new PropertyValueFactory<>("istBus"));
		preisNetto.setCellValueFactory(new PropertyValueFactory<>("preisNetto"));
		brutto.setCellValueFactory(new PropertyValueFactory<>("brutto"));
		ek.setCellValueFactory(new PropertyValueFactory<>("ek"));
		marge.setCellValueFactory(new PropertyValueFactory<>("marge"));
		gesamt.setCellValueFactory(new PropertyValueFactory<>("gesamt"));

		gesamt.setEditable(true);
		dropdownBefuellen();
		Liste e = new Liste();
		e.setBusNr("busNr");
		e.setBemerkungen("bemerkungen");
		e.setAnsprechpartner("ansprechpartner");
		e.setKurzel("kurzel");
		e.setKW("KW");
		e.setDatum(Date.valueOf(LocalDate.now()));
		e.setAbfahrtort("Abfahrtort");
		e.setUhrzeit("uhrzeit");
		e.setSchicht("schichtplaner");
		e.setTag("tag");
		e.setFahrer("Fahrer");
		e.setHandynr("handynr");
		e.setSollBus("sollBus");
		e.setIstBus("istBus");
		e.setPreisNetto("preisNetto");
		e.setBrutto("Brutto");
		e.setEk("ek");
		e.setMarge("marge");
		e.setGesamt("gesamt");
		methodeFuersSetzen();
		listListe.add(e);
		tabelleEx.setEditable(true);
		// tabelleEx.getSelectionModel().cellSelectionEnabledProperty().set(true);
		tabelleEx.setRowFactory(tv -> new TableRow<Liste>() {

			@Override
			public void updateItem(Liste item, boolean empty) {
				super.updateItem(item, empty);
				if (item == null) {

				} else if (item.getFarbe() != null) {
					setStyle(item.getFarbe());
				} else {
					setStyle("");
				}
				if (item == null) {

				} else if (item.getBemerkungen() != null && item.getBemerkungen().equals("Storno")) {
					setStyle("-fx-background-color: red;");
				} else {

				}
				if(item==null){
				
				}else if (item.getFarben()!=null){
					setStyle(item.getFarben());
					System.out.println(item.getFarbe());
					System.out.println(item.getFarben());
				}

			}
		});
		tabelleEx.setItems(listListe);

	}

	private void dropdownBefuellen() {
		ObservableList<String> Werke = FXCollections.observableArrayList();
		ConnectMe c = new ConnectMe();
		Statement stmt = c.getStatement();
		try {
			ResultSet rs = stmt.executeQuery("Select werkBezeichnung from plz.werke");
			while (rs.next()) {
				Werke.add(rs.getString(1));
			}
			dropdownWerk.setItems(Werke);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Zweck:<br>
	 * Soll alle Felder bearbeitbar machen
	 */
	private void methodeFuersSetzen() {
		busNr.setCellFactory(TextFieldTableCell.forTableColumn());
		busNr.setOnEditCommit((TableColumn.CellEditEvent<Liste, String> t) -> (t.getTableView().getItems()
				.get(t.getTablePosition().getRow())).setBusNr(t.getNewValue())

		);
		bemerkungen.setCellFactory(TextFieldTableCell.forTableColumn());
		bemerkungen.setOnEditCommit((TableColumn.CellEditEvent<Liste, String> t) -> (t.getTableView().getItems()
				.get(t.getTablePosition().getRow())).setBemerkungen(t.getNewValue())

		);
		ansprechpartner.setCellFactory(TextFieldTableCell.forTableColumn());
		ansprechpartner.setOnEditCommit((TableColumn.CellEditEvent<Liste, String> t) -> (t.getTableView().getItems()
				.get(t.getTablePosition().getRow())).setAnsprechpartner(t.getNewValue())

		);
		kurzel.setCellFactory(TextFieldTableCell.forTableColumn());
		kurzel.setOnEditCommit((TableColumn.CellEditEvent<Liste, String> t) -> (t.getTableView().getItems()
				.get(t.getTablePosition().getRow())).setKurzel(t.getNewValue())

		);
		KW.setCellFactory(TextFieldTableCell.forTableColumn());
		KW.setOnEditCommit((TableColumn.CellEditEvent<Liste, String> t) -> (t.getTableView().getItems()
				.get(t.getTablePosition().getRow())).setKW(t.getNewValue())

		);

		abfahrtort.setCellFactory(TextFieldTableCell.forTableColumn());
		abfahrtort.setOnEditCommit((TableColumn.CellEditEvent<Liste, String> t) -> (t.getTableView().getItems()
				.get(t.getTablePosition().getRow())).setAbfahrtort(t.getNewValue())

		);
		uhrzeit.setCellFactory(TextFieldTableCell.forTableColumn());
		uhrzeit.setOnEditCommit((TableColumn.CellEditEvent<Liste, String> t) -> (t.getTableView().getItems()
				.get(t.getTablePosition().getRow())).setUhrzeit(t.getNewValue())

		);
		schichtplaner.setCellFactory(TextFieldTableCell.forTableColumn());
		schichtplaner.setOnEditCommit((TableColumn.CellEditEvent<Liste, String> t) -> (t.getTableView().getItems()
				.get(t.getTablePosition().getRow())).setSchicht(t.getNewValue())

		);
		tag.setCellFactory(TextFieldTableCell.forTableColumn());
		tag.setOnEditCommit((TableColumn.CellEditEvent<Liste, String> t) -> (t.getTableView().getItems()
				.get(t.getTablePosition().getRow())).setUnternehmen(t.getNewValue())

		);
		fahrer.setCellFactory(TextFieldTableCell.forTableColumn());
		fahrer.setOnEditCommit((TableColumn.CellEditEvent<Liste, String> t) -> (t.getTableView().getItems()
				.get(t.getTablePosition().getRow())).setFahrer(t.getNewValue())

		);
		handynr.setCellFactory(TextFieldTableCell.forTableColumn());
		handynr.setOnEditCommit((TableColumn.CellEditEvent<Liste, String> t) -> (t.getTableView().getItems()
				.get(t.getTablePosition().getRow())).setHandynr(t.getNewValue())

		);
		sollBus.setCellFactory(TextFieldTableCell.forTableColumn());
		sollBus.setOnEditCommit((TableColumn.CellEditEvent<Liste, String> t) -> (t.getTableView().getItems()
				.get(t.getTablePosition().getRow())).setSollBus(t.getNewValue())

		);
		istBus.setCellFactory(TextFieldTableCell.forTableColumn());
		istBus.setOnEditCommit((TableColumn.CellEditEvent<Liste, String> t) -> (t.getTableView().getItems()
				.get(t.getTablePosition().getRow())).setIstBus(t.getNewValue())

		);
		preisNetto.setCellFactory(TextFieldTableCell.forTableColumn());
		preisNetto.setOnEditCommit((TableColumn.CellEditEvent<Liste, String> t) -> (t.getTableView().getItems()
				.get(t.getTablePosition().getRow())).setPreisNetto(t.getNewValue())

		);
		brutto.setCellFactory(TextFieldTableCell.forTableColumn());
		brutto.setOnEditCommit((TableColumn.CellEditEvent<Liste, String> t) -> (t.getTableView().getItems()
				.get(t.getTablePosition().getRow())).setBrutto(t.getNewValue())

		);

		ek.setCellFactory(TextFieldTableCell.forTableColumn());
		ek.setOnEditCommit((TableColumn.CellEditEvent<Liste, String> t) -> (t.getTableView().getItems()
				.get(t.getTablePosition().getRow())).setEk(t.getNewValue())

		);

		marge.setCellFactory(TextFieldTableCell.forTableColumn());
		marge.setOnEditCommit((TableColumn.CellEditEvent<Liste, String> t) -> (t.getTableView().getItems()
				.get(t.getTablePosition().getRow())).setMarge(t.getNewValue())

		);
		gesamt.setCellFactory(TextFieldTableCell.forTableColumn());
		gesamt.setOnEditCommit((TableColumn.CellEditEvent<Liste, String> t) -> (t.getTableView().getItems()
				.get(t.getTablePosition().getRow())).setGesamt(t.getNewValue())

		);

	}

	/**
	 * Zweck:<br>
	 * Man soll nach einzeln Kalenderwochen suchen können
	 * 
	 * @param event
	 */
	@FXML
	public void suchen(ActionEvent event) {
		String kw = kalenderwoche.getText();
		ConnectMe c = new ConnectMe();
		Statement stmt = c.getStatement();
		// Wichtig
		listListe.clear();
		String nID = dropdownWerk.getSelectionModel().getSelectedItem();
		try {
			ResultSet rs2 = stmt.executeQuery("Select N_ID From werke Where werkBezeichnung= '" + nID + "'");
			while (rs2.next()) {
				nID = rs2.getString(1);
				System.out.println(nID);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			ResultSet rs = stmt
					.executeQuery("Select * From fahrten Where fahrten.KW = '" + kw + "' and N_ID = '" + nID + "'");
			while (rs.next()) {
				Liste e = new Liste();
				e.setFahrtId(rs.getInt(1));
				e.setnId(rs.getInt(2));
				e.setBusNr(rs.getString(3));
				e.setBemerkungen(rs.getString(4));
				e.setAnsprechpartner(rs.getString(5));
				e.setKurzel(rs.getString(6));
				e.setKW(rs.getString(7));
				e.setDatum(rs.getDate(8));
				e.setAbfahrtort(rs.getString(9));
				e.setUhrzeit(rs.getString(10));
				e.setSchicht(rs.getString(11));
				e.setTag(rs.getString(12));
				e.setFahrer(rs.getString(13));
				e.setHandynr(rs.getString(14));
				e.setSollBus(rs.getString(15));
				e.setIstBus(rs.getString(16));
				e.setPreisNetto(rs.getString(17));
				e.setBrutto(rs.getString(18));
				e.setEk(rs.getString(19));
				e.setMarge(rs.getString(20));
				e.setGesamt(rs.getString(21));
				e.setStorno(rs.getString(22));
				e.setLinienName(rs.getString(23));
				e.setWerkBez(rs.getString(24));
				e.setFarben(rs.getString(25));
				e.setUnternehmen(rs.getString(26));
				

				listListe.add(e);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tabelleEx.setItems(listListe);
	}

	@FXML
	public void neuesWerk(ActionEvent event) {
		Stage primaryStage = new Stage();
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("Werk.fxml"));
			root.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());

			primaryStage.setTitle("Werk hinzufuegen");
			primaryStage.setResizable(false);
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	public void speichern(ActionEvent event) {
		int j = tabelleEx.getItems().size();
		ConnectMe c = new ConnectMe();
		Statement stmt = c.getStatement();
		Liste liste;
		try {

			for (int i = 0; i < j; i++) {
				liste = tabelleEx.getItems().get(i);

				String sqlupdate = "UPDATE `plz`.`fahrten` SET `N_ID` = '" + liste.getnId() + "', " + "`busnr` = '"
						+ liste.getBusNr() + "', `bemerkung` = '" + liste.getBemerkungen() + "',"
						+ " `ansprechpartner` = '" + liste.getAnsprechpartner() + "', `kurzel` = '" + liste.getKurzel()
						+ "'," + " `KW` = '" + liste.getKW() + "', `datum` = '" + liste.getDatum() + "',"
						+ " `abfahrtort` = '" + liste.getAbfahrtort() + "', `uhrzeit` = '" + liste.getUhrzeit() + "',"
						+ " `schicht` = '" + liste.getSchicht() + "', `tag` = '" + liste.getTag() + "',"
						+ " `fahrer` = '" + liste.getFahrer() + "', `handynr` = '" + liste.getHandynr() + "',"
						+ " `sollBus` = '" + liste.getSollBus() + "', `istBus` = '" + liste.getIstBus() + "',"
						+ " `preisNetto` = '" + liste.getPreisNetto() + "', `brutto` = '" + liste.getBrutto() + "',"
						+ " `ek` = '" + liste.getEk() + "', `marge` = '" + liste.getMarge() + "'," + " `gesamt` = '"
						+ liste.getGesamt() + "',`storno` = '"+liste.getStorno()+"',"
						+ " `linienName` = '"+liste.getLinienName()+"', `werkBezeichnung` = '"+liste.getWerkBez()+"',"
						+ " `farben` = '"+liste.getFarben()+"', `unternehmen` = '"+liste.getUnternehmen()+"'   WHERE (`Fahrt_ID` = '" + liste.getFahrtId() + "')";

				stmt.executeUpdate(sqlupdate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			c.closeConnection();
		}

	}

	@FXML
	void neueFahrt(ActionEvent event) {
		Stage primaryStage = new Stage();
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("Fahrt.fxml"));
			root.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());

			primaryStage.setTitle("Fahrt hinzufuegen");
			primaryStage.setResizable(false);
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Fehler");
			alert.setHeaderText(e.getMessage());
			/* alert.setContentText("You didn't select a file!"); */
			alert.showAndWait();
		}
	}

	/**
	 * Richtig unschön war schnell
	 * 
	 * @param event
	 */
	@FXML
	void masterMe(ActionEvent event) {
		String kw = kalenderwoche.getText();
		ConnectMe c = new ConnectMe();
		Statement stmt = c.getStatement();
		listListe.clear();

		try {
			ResultSet rs = stmt.executeQuery("Select * From fahrten Where fahrten.KW = '" + kw + "'");
			while (rs.next()) {
				Liste e = new Liste();
				e.setFahrtId(rs.getInt(1));
				e.setnId(rs.getInt(2));
				e.setBusNr(rs.getString(3));
				e.setBemerkungen(rs.getString(4));
				e.setAnsprechpartner(rs.getString(5));
				e.setKurzel(rs.getString(6));
				e.setKW(rs.getString(7));
				e.setDatum(rs.getDate(8));
				e.setAbfahrtort(rs.getString(9));
				e.setUhrzeit(rs.getString(10));
				e.setSchicht(rs.getString(11));
				e.setTag(rs.getString(12));
				e.setFahrer(rs.getString(13));
				e.setHandynr(rs.getString(14));
				e.setSollBus(rs.getString(15));
				e.setIstBus(rs.getString(16));
				e.setPreisNetto(rs.getString(17));
				e.setBrutto(rs.getString(18));
				e.setEk(rs.getString(19));
				e.setMarge(rs.getString(20));
				e.setGesamt(rs.getString(21));
				e.setStorno(rs.getString(22));
				e.setLinienName(rs.getString(23));
				e.setWerkBez(rs.getString(24));
				e.setFarben(rs.getString(25));
				e.setUnternehmen(rs.getString(26));
				

				listListe.add(e);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tabelleEx.setItems(listListe);

	}

	@FXML
	void autoWerk(ActionEvent event) {
		listListe.clear();

		// Dynamische Tage

		// Läuft alle Linien durch
		for (int linie = 0; linie < Werk.werk.getLinieListe().size(); linie++) {
			Linie line = Werk.werk.getLinieListe().get(linie);

			// Läuft für jede Linie jeweils alle Busse durch
			for (int bus = 0; bus < line.getBusListe().size(); bus++) {
				Busse busse = line.getBusListe().get(bus);
				busse.getSchicht();

				for (int tage = 0; tage < 7; tage++) {
					System.out.println(busse.getSchicht().getListeAllerTage().size());
					LocalDate monday = busse.getSchicht().getAnfangsdatum();

					if (busse.getSchicht().getListeAllerTage().get(tage)) {
						// Für jeden Bus an bestimmten Tagen alle Schichten
						for (int schichten = 0; schichten < busse.getSchicht().getListeAllerSchichten()
								.size(); schichten++) {

							Liste list = new Liste();
							boolean bedingungEins = false;
							boolean bedingungZwei = false;
							
							bedingungEins = (busse.getSchicht().getListeSchichtenName().get(schichten).equals("NachtHin") && busse.getSchicht().isNachtschichtHinVortag());
							bedingungZwei = (busse.getSchicht().getListeSchichtenName().get(schichten).equals("Nacht TZ Hin") && busse.getSchicht().isNachtschichtTZVortag());
							if(bedingungEins||bedingungZwei){
								list.setDatum(Date.valueOf(monday.plusDays(tage-1)));
							}else{
								list.setDatum(Date.valueOf(monday.plusDays(tage)));
							}
							
							list.setAbfahrtort(line.getVon() + " über " + line.getUeber() + " nach " + line.getNach());
							list.setUhrzeit(busse.getSchicht().getListeAllerSchichten().get(schichten));
							list.setSchicht(busse.getSchicht().getListeSchichtenName().get(schichten));
							list.setFahrer(busse.getFahrer());
							list.setLinienName(line.getName());
							list.setBusNr(busse.getName());
							list.setFarbe(line.getFarbeAlsHex());
							list.setnId(Integer.parseInt(Werk.werk.getnID()));
							list.setUnternehmen(busse.getUnternehmer());
							list.setSollBus(busse.getGroesse());
							

							list.setKW(monday.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR) + "");

							listListe.add(list);
						}
					}
					;

				}
			}
		}

	}

	@FXML
	public void datenUbernehmen(ActionEvent event) {
		String nid = Werk.werk.getnID();
		String kw = listListe.get(0).getKW();
		ConnectMe c = new ConnectMe();
		try {
			Statement stmt = c.getStatement();
			String sql = "Select * from plz.Fahrten where KW = " + kw + " and N_ID = " + nid + ";";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setTitle("Daten vorhanden");
				alert.setHeaderText("Daten sind vorhanden, wollen sie diese Daten wirklich überschreiben");

				/* alert.setContentText("You didn't select a file!"); */
				alert.showAndWait();
				// Daten vorhanden
				String sqlDelete = "DELETE FROM `plz`.`fahrten` WHERE KW = " + kw + " and N_ID = " + nid + ";";
				stmt.executeUpdate(sqlDelete);
				datenSchreiben(stmt, nid, kw);
			} else {
				System.out.println("Hat keine ");
				datenSchreiben(stmt, nid, kw);
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			c.closeConnection();
		}

	}

	private void datenSchreiben(Statement stmt, String nid, String kw) throws SQLException {
		Liste liste;
		for (int i = 0; i < tabelleEx.getItems().size(); i++) {
			liste = tabelleEx.getItems().get(i);

			String sqlUpdate = "INSERT INTO `plz`.`fahrten` "
					+ "(`N_ID`, `busnr`, `bemerkung`, `ansprechpartner`, `kurzel`, `KW`, `datum`,"
					+ " `abfahrtort`, `uhrzeit`, `schicht`, `tag`, `fahrer`, `handynr`, `sollBus`,"
					+ " `istBus`, `preisNetto`, `brutto`, `ek`, `marge`, `gesamt`, `storno`,"
					+ " `linienName`, `werkBezeichnung`, `farben`, `unternehmen`)" + " VALUES ('" + liste.getnId() + "', '"
					+ liste.getBusNr() + "', '" + liste.getBemerkungen() + "',"
					+ " '"+liste.getAnsprechpartner()+"', '"+liste.getKurzel()+"',"
					+ " '"+liste.getKW()+"', '"+liste.getDatum()+"', '"+liste.getAbfahrtort()+"',"
					+ " '"+liste.getUhrzeit()+"', '"+liste.getSchicht()+"', '"+liste.getTag()+"',"
					+ " '"+liste.getFahrer()+"', '"+liste.getHandynr()+"', '"+liste.getSollBus()+"',"
					+ " '"+liste.getIstBus()+"', '"+liste.getPreisNetto()+"', '"+liste.getBrutto()+"',"
					+ " '"+liste.getEk()+"', '"+liste.getMarge()+"', '"+liste.getGesamt()+"',"
					+ " '"+liste.getStorno()+"', '"+liste.getLinienName()+"', '"+liste.getWerkBez()+"',"
							+ " '"+liste.getFarbe()+"', '"+liste.getUnternehmen()+"');";

			stmt.executeUpdate(sqlUpdate);
		}

	}

}
