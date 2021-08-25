package application.tabelle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import application.sql.ConnectMe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class KalenderController {

	@FXML ComboBox<String> werk;
	@FXML TextField kalender;
	ObservableList<String> werke = FXCollections.observableArrayList();
	
    @FXML 
    void initialize(){
    	for (int i = 0; i < Daten.listeDerWerk.size(); i++) {
			werke.add(Daten.listeDerWerk.get(i).getWerkBezeichnung());
		}
    	werk.setItems(werke);
    }
	
	@FXML public void abbruch(ActionEvent event) {
		final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
	}

	@FXML public void submit(ActionEvent event) {
		String werkAuswahl = werk.getSelectionModel().getSelectedItem();
		String kw = kalender.getText();
		if(werkAuswahl==null||kw == null){
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Fehler");
			alert.setHeaderText("Nicht alle Werte wurden eingeben");
			alert.showAndWait();
		}else{
			ConnectMe c = new ConnectMe();
			Statement stmt = c.getStatement();
			String sqlNid = "Select N_ID from plz.werke where werkBezeichnung='"+werkAuswahl+"'";
			String nid = null;
			try {
				ResultSet rs = stmt.executeQuery(sqlNid);
				while (rs.next()) {
					nid = rs.getString(1);
					
				}
				String sql = "Select * From plz.fahrten where N_ID ='"+nid+"' and KW ='"+kw+"'";
				ResultSet rs1 = stmt.executeQuery(sql);
				if(rs1.next()){
					Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
					alert.setTitle("Daten vorhanden");
					alert.setHeaderText("Daten für diese Woche sind schon vorhanden trotzdem abschicken");
					Optional<ButtonType> result = alert.showAndWait();
					if(result.get() == ButtonType.OK){
						String deleteSql = "Delete From plz.fahrten where N_ID ='"+nid+"' and KW ='"+kw+"'";
						stmt.executeUpdate(deleteSql);
						datenSchreiben(stmt, kw, werkAuswahl);
						
						
					}
					
					
				}else{
					System.out.println("datenschreiben");
					datenSchreiben(stmt, kw, werkAuswahl);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			final Node source = (Node) event.getSource();
	        final Stage stage = (Stage) source.getScene().getWindow();
	        stage.close();
			
		}
	}
	void datenSchreiben(Statement stmt,String kw, String werk) throws SQLException{
		Liste liste = null;
		for (int i = 0; i < Daten.listeListe.size(); i++) {
			liste = Daten.listeListe.get(i);

			String sqlUpdate = "INSERT INTO `plz`.`fahrten` "
					+ "(`N_ID`, `busnr`, `bemerkung`, `ansprechpartner`, `kurzel`, `KW`, `datum`,"
					+ " `abfahrtort`, `uhrzeit`, `schicht`, `tag`, `fahrer`, `handynr`, `sollBus`,"
					+ " `istBus`, `preisNetto`, `brutto`, `ek`, `marge`, `gesamt`, `storno`,"
					+ " `linienName`, `werkBezeichnung`, `farben`, `unternehmen`)" + " VALUES ('" + liste.getnId() + "', '"
					+ liste.getBusNr() + "', '" + liste.getBemerkungen() + "',"
					+ " '"+liste.getAnsprechpartner()+"', '"+liste.getKurzel()+"',"
					+ " '"+kw+"', '"+liste.getDatum()+"', '"+liste.getAbfahrtort()+"',"
					+ " '"+liste.getUhrzeit()+"', '"+liste.getSchicht()+"', '"+liste.getTag()+"',"
					+ " '"+liste.getFahrer()+"', '"+liste.getHandynr()+"', '"+liste.getSollBus()+"',"
					+ " '"+liste.getIstBus()+"', '"+liste.getPreisNetto()+"', '"+liste.getBrutto()+"',"
					+ " '"+liste.getEk()+"', '"+liste.getMarge()+"', '"+liste.getGesamt()+"',"
					+ " '"+liste.getStorno()+"', '"+liste.getLinienName()+"', '"+liste.getWerkBez()+"',"
							+ " '"+liste.getFarben()+"', '"+liste.getUnternehmen()+"');";
			System.out.println(sqlUpdate);
			stmt.executeUpdate(sqlUpdate);
			
		}
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Erfolg");
		alert.setHeaderText("Alle Daten wurden für das Werk" +werk+ "und die Kalenderwoche: "+kw+"  übernommen" );
		alert.showAndWait(); 
		
	}
	
}
