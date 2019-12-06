package application.dispo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.sql.ConnectMe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class BusseHinzuController {
	static String imagePath ="";
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

	ObservableList<String> optionsFarbe = FXCollections.observableArrayList("", "Weiß", "Schwarz", "Blau", "Gelb",
			"Grün");

	@FXML
	ImageView ViewMe;

	@FXML
	void einenHinzu(ActionEvent event) {
		Image i = ViewMe.getImage();
		ConnectMe c = new ConnectMe();
		Connection con = c.getC();
		System.out.println(imagePath);
		try {
			PreparedStatement pstmt =con.prepareStatement(("INSERT INTO busse (U_ID, typ, groesse, reiseleiter, farbe,  bild)  VALUES(?,?,?,?,?,?);"));
			InputStream in = new FileInputStream(imagePath);
			pstmt.setString(1, UidObject.unternehmen.getUid());
			pstmt.setString(2, typ.getSelectionModel().getSelectedItem());
			pstmt.setString(3, platz.getText());
			pstmt.setString(4, reiseleite.getText());
			pstmt.setString(5, farbe.getSelectionModel().getSelectedItem());		
			pstmt.setBlob(6, in);
			pstmt.execute();
		} catch (SQLException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	}

	@FXML
	public void bildHoch(ActionEvent event) {
		final FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files",
                        "*.bmp", "*.png", "*.jpg", "*.gif"));
		File file = fileChooser.showOpenDialog(new Stage());
		if(file != null) {
	        String imagepath = "file:" + file.getPath();
	        imagePath = file.getPath();
	        System.out.println("file:"+imagepath);
	        Image image = new Image(imagepath);
	        ViewMe.setImage(image);
	    }
	    else
	    {
	        Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle("Information Dialog");
	        alert.setHeaderText("Bitte ein Bild auswählen");
	        /*alert.setContentText("You didn't select a file!");*/
	        alert.showAndWait();
	    }
		
		
	}

}
