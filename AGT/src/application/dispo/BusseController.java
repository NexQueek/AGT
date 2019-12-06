package application.dispo;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;

import application.sql.ConnectMe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class BusseController {

	@FXML
	private TextField typEingabe;

	@FXML
	private TableColumn<?, ?> farbe;

	@FXML
	private TableColumn<?, ?> groesse;

	@FXML
	private TextField groesseEingabe;

	@FXML
	private Label unternehmenName;

	@FXML
	private Label anzahl;

	@FXML
	private TableView<Busse> tabelle;

	@FXML
	private TableColumn<?, ?> typ;

	@FXML
	private TableColumn<?, ?> e1;

	@FXML
	private TableColumn<?, ?> e2;

	@FXML
	private TableColumn<?, ?> BUS_ID;

	private ObservableList<Busse> liste = FXCollections.observableArrayList();

	@FXML
	void initialize() {
		liste = befuellen();
		typ.setCellValueFactory(new PropertyValueFactory<>("Typ"));
		e1.setCellValueFactory(new PropertyValueFactory<>("eigenschaft1"));
		e2.setCellValueFactory(new PropertyValueFactory<>("Bild"));
		BUS_ID.setCellValueFactory(new PropertyValueFactory<>("bid"));

		groesse.setCellValueFactory(new PropertyValueFactory<>("groesse"));
		farbe.setCellValueFactory(new PropertyValueFactory<>("farbe"));
		tabelle.setItems(liste);
		unternehmenName.setText(UidObject.unternehmen.getName());
		anzahl.setText(liste.size() + "");
	}

	@FXML
	void busHinzufuegen(ActionEvent event) {
		Parent root;
		Stage primaryStage = new Stage();
		try {
			root = FXMLLoader.load(getClass().getResource("Bushinzufuegen.fxml"));
			root.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());

			primaryStage.setTitle("Bus hinzufügen");
			primaryStage.setResizable(false);
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	ObservableList<Busse> befuellen() {
		ObservableList<Busse> li;
		li = FXCollections.observableArrayList();
		String sql = "Select * From busse where U_ID =" + UidObject.unternehmen.getUid() + ";";
		ConnectMe c = new ConnectMe();
		Statement stmt = c.getStatement();
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Busse b = new Busse(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(6),
						rs.getString(5), rs.getString(7));
				java.sql.Blob blob = rs.getBlob(8);
				System.out.println(blob);
				if (blob == null) {
					System.out.println("Null");
				} else {
					System.out.println("Yo");
					InputStream in = blob.getBinaryStream();
					BufferedImage image;
					image = ImageIO.read(in);
					Image im = SwingFXUtils.toFXImage(image, null);
					ImageView i = new ImageView();
					i.setImage(im);
					b.setBild(i);

				}
				
				li.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return li;
	}

	@FXML
	void filtern(ActionEvent event) {

	}

}
