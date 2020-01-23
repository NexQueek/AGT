package application.admin;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.mysql.cj.jdbc.DatabaseMetaData;

import application.Benutzer;
import application.bewertung.Unverify;
import application.dispo.UidObject;
import application.sql.ConnectMe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Admin extends AnchorPane {
	@FXML
	private TableColumn<Benutzer, String> auskunft;

	@FXML
	private Button but;

	@FXML
	private TableColumn<Unverify, String> nameUnternehmen;

	@FXML
	private TableColumn<Unverify, String> sauber;

	@FXML
	private TableColumn<Unverify, String> thematik;

	@FXML
	private TableColumn<Unverify, String> unBid;

	@FXML
	private TableColumn<Unverify, String> rechnungsnummer;

	@FXML
	private TableView<Unverify> unBewertung;

	@FXML
	private TableColumn<Unverify, String> zuverlassig;

	@FXML
	private TableColumn<Unverify, String> service;

	@FXML
	private TableColumn<Unverify, String> bewertetAm;

	@FXML
	private TableColumn<Unverify, String> bewertetVon;

	@FXML
	private TableColumn<Unverify, String> erreichbar;

	@FXML
	private TableColumn<Benutzer, String> marge;

	@FXML
	private TableColumn<Benutzer, String> passwort;

	@FXML
	private TableColumn<Benutzer, String> anzahl;

	@FXML
	private TableColumn<Benutzer, String> name;

	@FXML
	private TableColumn<Benutzer, String> admin;

	@FXML
	private TableColumn<Benutzer, String> zu;

	@FXML
	TableView<Benutzer> userid;
	@FXML
	private TableColumn<Benutzer, String> uid;

	@FXML
	private TableColumn<Benutzer, String> bewertung;

	@FXML
	private TableColumn<Benutzer, String> dispo;

	public Admin() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Admin.fxml"));

		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
			initialize(null, null);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initialize(URL location, ResourceBundle resources) {

		// BewertungTable
		unBid.setCellValueFactory(new PropertyValueFactory<>("UnBid"));
		nameUnternehmen.setCellValueFactory(new PropertyValueFactory<>("unterName"));
		bewertetVon.setCellValueFactory(new PropertyValueFactory<>("bewertetVon"));
		bewertetAm.setCellValueFactory(new PropertyValueFactory<>("bewertetAm"));
		rechnungsnummer.setCellValueFactory(new PropertyValueFactory<>("rechnungsnummer"));
		thematik.setCellValueFactory(new PropertyValueFactory<>("thematik"));
		erreichbar.setCellValueFactory(new PropertyValueFactory<>("erreichbar"));
		zuverlassig.setCellValueFactory(new PropertyValueFactory<>("zuverlassig"));
		service.setCellValueFactory(new PropertyValueFactory<>("service"));
		sauber.setCellValueFactory(new PropertyValueFactory<>("sauber"));

		// UserTable
		uid.setCellValueFactory(new PropertyValueFactory<>("userId"));
		name.setCellValueFactory(new PropertyValueFactory<>("Name"));
		passwort.setCellValueFactory(new PropertyValueFactory<>("Passwort"));
		anzahl.setCellValueFactory(new PropertyValueFactory<>(""));
		dispo.setCellValueFactory(new PropertyValueFactory<>("DispoS"));
		dispo.setCellFactory(column -> {
			return new TableCell<Benutzer, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					boolean items = Boolean.parseBoolean(item);
					if (items == true || empty) {
						setText("✔");

					} else {
						setText("");
					}
				}
			};
		});
		auskunft.setCellValueFactory(new PropertyValueFactory<>("AuskunftS"));
		auskunft.setCellFactory(column -> {
			return new TableCell<Benutzer, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					boolean items = Boolean.parseBoolean(item);
					if (items == true || empty) {
						setText("✔");

					} else {
						setText("");
					}
				}
			};
		});
		marge.setCellValueFactory(new PropertyValueFactory<>("MargeS"));
		marge.setCellFactory(column -> {
			return new TableCell<Benutzer, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					boolean items = Boolean.parseBoolean(item);
					if (items == true || empty) {
						setText("✔");

					} else {
						setText("");
					}
				}
			};
		});
		zu.setCellValueFactory(new PropertyValueFactory<>("ZubehoerS"));
		zu.setCellFactory(column -> {
			return new TableCell<Benutzer, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					boolean items = Boolean.parseBoolean(item);
					if (items == true || empty) {
						setText("✔");

					} else {
						setText("");
					}
				}
			};
		});
		admin.setCellValueFactory(new PropertyValueFactory<>("AdminS"));
		admin.setCellFactory(column -> {
			return new TableCell<Benutzer, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					boolean items = Boolean.parseBoolean(item);
					if (items == true || empty) {
						setText("✔");

					} else {
						setText("");
					}
				}
			};
		});
		bewertung.setCellValueFactory(new PropertyValueFactory<>("BewertungS"));
		bewertung.setCellFactory(column -> {
			return new TableCell<Benutzer, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					boolean items = Boolean.parseBoolean(item);
					if (items == true || empty) {
						setText("✔");

					} else {
						setText("");
					}
				}
			};
		});
		// Fills the C Table with data
		abrufen(null);
		// When u doubleclick on the table on bewertung
		unBewertung.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent ee) {
				if (ee.isPrimaryButtonDown() && ee.getClickCount() == 2) {
					// Gives the uid to popUp so it opens a window with specific
					// information
					verify(unBewertung.getSelectionModel().getSelectedItem().getUnBid());
				}

			}
		});

		userid.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent ee) {
				if (ee.isPrimaryButtonDown() && ee.getClickCount() == 2) {
					// Gives the uid to popUp so it opens a window with specific
					// information
					userEdit(userid.getSelectionModel().getSelectedItem().getUserId());
				}

			}
		});
	}

	@FXML
	void userGet(ActionEvent event) {
		ObservableList<Benutzer> list = FXCollections.observableArrayList();
		try {

			ConnectMe c = new ConnectMe();
			Statement stmt = c.getStatement();

			ResultSet rs = stmt.executeQuery("select  * from plz.benutzer");
			while (rs.next()) {
				Benutzer e = new Benutzer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getBoolean(4),
						rs.getBoolean(5), rs.getBoolean(6), rs.getBoolean(7), rs.getBoolean(8), rs.getBoolean(9),
						rs.getBoolean(4), rs.getBoolean(4));
				list.add(e);
			}
			c.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		userid.setItems(list);

	}

	void verify(String id) {
		UidObject.uid = id;

		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("Verify.fxml"));
			Stage primaryStage = new Stage();
			primaryStage.setScene(new Scene(root));
			root.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			System.out.println("Hallo");

			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void userEdit(String id) {

		UidObject.uid = id;
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("NutzerBearbeiten.fxml"));
			Stage primaryStage = new Stage();
			primaryStage.setScene(new Scene(root));
			root.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			System.out.println("Hallo");

			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * method for calling the compliance Table
	 * 
	 * @param event
	 */
	@FXML
	void abrufen(ActionEvent event) {
		ConnectMe c = new ConnectMe();
		Statement stmt = c.getStatement();
		ObservableList<Unverify> liste = FXCollections.observableArrayList();
		try {
			ResultSet rs = stmt.executeQuery(
					"Select * From plz.unverify inner join plz.unternehmen on unverify.U_ID = unternehmen.U_ID ");
			while (rs.next()) {
				Unverify u = new Unverify(rs.getString(1), rs.getString(14), rs.getString(3), rs.getDate(4),
						rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10),
						rs.getString(11), rs.getString(12));
				u.setUnterName(rs.getString(17));
				liste.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		unBewertung.setItems(liste);
		c.closeConnection();
	}
	// Datenbank einrichten

	@FXML
	void amazon(ActionEvent event) {
		// Tabelle erstellen
		String sql = "	CREATE TABLE `plz`.`werke` (			" + " `N_ID` INT NOT NULL AUTO_INCREMENT,     "
				+ " `werkBezeichnung` VARCHAR(150) NULL,    " + " `stadt` VARCHAR(100) NULL,              "
				+ " PRIMARY KEY (`N_ID`));";
		String sqlFahrt = "CREATE TABLE `plz`.`Fahrten` (" + "`Fahrt_ID` INT NOT NULL AUTO_INCREMENT,"
				+ "`N_ID` INT NULL,                         " + "`busnr` VARCHAR(45) NULL,                "
				+ "`bemerkung` VARCHAR(45) NULL,            " + "`ansprechpartner` VARCHAR(45) NULL,      "
				+ "`kurzel` VARCHAR(45) NULL,               " + "`KW` VARCHAR(45) NULL,                   "
				+ "`datum` DATE NULL,                       " + "`abfahrtort` VARCHAR(45) NULL,           "
				+ "`uhrzeit` VARCHAR(45) NULL,              " + "`schicht` VARCHAR(45) NULL,              "
				+ "`tag` VARCHAR(45) NULL,                  " + "`fahrer` VARCHAR(45) NULL,               "
				+ "`handynr` VARCHAR(45) NULL,              " + "`sollBus` VARCHAR(45) NULL,              "
				+ "`istBus` VARCHAR(45) NULL,               " + "`preisNetto` VARCHAR(45) NULL,           "
				+ "`brutto` VARCHAR(45) NULL,               " + "`ek` VARCHAR(45) NULL,                   "
				+ "`marge` VARCHAR(45) NULL,                " + "`gesamt` VARCHAR(45) NULL,               "
				+ "PRIMARY KEY (`Fahrt_ID`));               ";
		ConnectMe c = new ConnectMe();

		Statement stmt = c.getStatement();

		DatabaseMetaData dbm = null;
		try {
			dbm = (DatabaseMetaData) c.getC().getMetaData();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// check if "employee" table is there
		ResultSet tables = null;
		try {
			tables = dbm.getTables(null, null, "werke", null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (tables.next()) {
				System.out.println("vorhanden");
			}
			// Falls nicht vorhanden
			else {
				stmt.executeUpdate(sql);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			tables = dbm.getTables(null, null, "Fahrten", null);
			if (tables.next()) {
				System.out.println("vorhanden");
			}
			// Falls nicht vorhanden
			else {
				stmt.executeUpdate(sqlFahrt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			c.closeConnection();
		}

	}

}
