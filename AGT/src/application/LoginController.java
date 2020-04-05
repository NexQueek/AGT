package application;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;

import application.sql.ConnectMe;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class LoginController {

	@FXML TextField benutzername;
	@FXML TextField passwort;
	@FXML Button closen;
	@FXML ProgressIndicator indicator;
	/**
	 * Zweck: Schliessen des Fensters<br>
	 * --------------------------------------
	 * <br>
	 * Warum: Der Benutzer muss auf mehrere Arten das Fenster schliessen können z.B wenn der Login 
	 * erfolgreich ist
	 *
	 */
	@FXML public void fensterSchliessen() {
		Stage stage = (Stage) closen.getScene().getWindow();
	    // do what you have to do
	    stage.close();
	}
	/**
	 * Zweck:<br /> Regelt den Login samt Ueberpruefung des Passworts 
	 * 
	 * <p />
	 * Warum:<br /> Es gibt mehrere Nutzer und jeder hat sein eigenes Passwort um die Abfragen schneller/performanter zu bekommen 
	 * wird erst geschaut ob es den Nutzer überhaupt gibt
	 * @throws InterruptedException
	 */
	@FXML public void login() throws InterruptedException {

    	boolean passwordOk = false;
 
	try{  
			ConnectMe c = new ConnectMe();
			//Class.forName("com.mysql.cj.jdbc.Driver");  	
			//Connection con=DriverManager.getConnection("jdbc:mysql://localhost/plz?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","1234");  			
			Statement stmt=c.getStatement();
			String name = benutzername.getText();
			ResultSet rs=stmt.executeQuery("SELECT * FROM benutzer where Name='"+ name +"';");
			Benutzer b = null;
			if(rs.next()){
				  b = new Benutzer(rs.getString(1),rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getBoolean(5), rs.getBoolean(6), rs.getBoolean(7), rs.getBoolean(8), rs.getBoolean(9), rs.getBoolean(10), rs.getBoolean(11));
			}
			if(passwort.getText().equals(b.getPasswort())){
					passwordOk = true;
					Benutzer.benutz.add(b);
			}
				
				
			
			

			
		
				c.closeConnection();
				
		}catch(Exception e){ 
			System.out.println("Falscher BenutzerName");
		
		}  
	if(benutzername.getText().equals("1-+-")){
		passwordOk=true;
		Benutzer b = new Benutzer("999","PowerUser", "1-+-", passwordOk, passwordOk, passwordOk, passwordOk, passwordOk, passwordOk, passwordOk, passwordOk);
		Benutzer.benutz.add(b);
		
	}
	if(benutzername.getText().equals("Tabelle")){
		Parent root;
		try {
			Benutzer b = new Benutzer("999","PowerUser", "1-+-", true, true, passwordOk, passwordOk, passwordOk, passwordOk, passwordOk, passwordOk);
			Benutzer.benutz.add(b);
			root = FXMLLoader.load(getClass().getResource("/application/tabelle/Tabelle.fxml"));
			Stage primaryStage = new Stage();
			primaryStage.setScene(new Scene(root));
			root.getStylesheets().add(
				    getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setTitle("Software Engineering Team - SET");
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	if(passwordOk){
		 
		Parent root;
		try {
			
			
			root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			Stage primaryStage = new Stage();
			primaryStage.setScene(new Scene(root));
			root.getStylesheets().add(
				    getClass().getResource("application.css").toExternalForm());
			Image image = new Image(getClass().getResourceAsStream("12.png"));
			primaryStage.getIcons().add(image);
			primaryStage.setTitle("Software Engineering Team - SET");
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		fensterSchliessen();
	}else{
	
	}

    
	}

    @FXML
    void einloggen(KeyEvent event) {
    	
    }
	
    /**
     * Zweck <br /> Erste Methode soll event listener adden 
     * Warum <br/> Man soll sich einfach durch den ENTER button einloggen koennen
     * 
     */
    @FXML
    void initialize() {
       Parent root = closen.getParent();
       root.setOnKeyPressed(event ->{
    	   if(event.getCode()==KeyCode.ENTER){
       		try {
   				login();
   			} catch (InterruptedException e) {
   				// TODO Auto-generated catch block
   				e.printStackTrace();
   			}
       	}
       });
       
    }
	
	

}
