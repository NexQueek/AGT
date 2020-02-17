package application.tabelle;

import java.io.Serializable;
import java.util.ArrayList;

public class Werk implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8526896170910603703L;
	private String nID;
	private String werkBezeichnung;
	private String stadt;
	private String anzahlLinie;
	private ArrayList<Linie> linieListe;
	
	static Werk werk;
	static ArrayList<Werk> werkeList = new ArrayList<Werk>();
	
	public Werk() {
		this.linieListe = new ArrayList<Linie>();;
	}
	
	
	
	public ArrayList<Linie> getLinieListe() {
		if(linieListe == null){
			
		}
		return linieListe;
	}



	public String getnID() {
		return nID;
	}

	public void setnID(String nID) {
		this.nID = nID;
	}

	public String getWerkBezeichnung() {
		return werkBezeichnung;
	}

	public void setWerkBezeichnung(String werkBezeichnung) {
		this.werkBezeichnung = werkBezeichnung;
	}

	public String getStadt() {
		return stadt;
	}

	public void setStadt(String stadt) {
		this.stadt = stadt;
	}

	public String getAnzahlLinie() {
		return anzahlLinie;
	}

	public void setAnzahlLinie(String anzahlLinie) {
		this.anzahlLinie = anzahlLinie;
	}




	
}
