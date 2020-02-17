package application.tabelle;

import java.io.Serializable;
import java.util.ArrayList;

public class Linie implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1653525545523482748L;
	private String linieID;
	private String N_ID; // Werk/niederlassungsid
	private String name;
	private String anzahlDerBusse;
	private String von;
	private String ueber;
	private String nach;
	private String wannBisWann;
	private String farbe;
	private Busse bus;
	private ArrayList<Busse> busListe = new ArrayList<Busse>();
	private Schichten schicht;
	

	public String getFarbeAlsHex() {
		switch (getFarbe()) {
		case "Cyan":
			return "ccffffff";
		case "Hellblau":
			return "cce6ffff";
		case "Rosa":
			return "e6ccffff";
		case "Orange":
			return "ffccb3ff";
		case "Gelb":
			return "ffffb3ff";
				
		default:
			return "";
		}
		
	}



	



	public void setBusListe(ArrayList<Busse> busListe) {
		this.busListe = busListe;
	}



	public ArrayList<Busse> getBusListe() {
		return busListe;
	}

	

	public Busse getBus() {
		return bus;
	}

	public void setBus(Busse bus) {
		this.bus = bus;
	}

	

	public Linie() {
		// TODO Auto-generated constructor stub
	}

	public String getLinieID() {
		return linieID;
	}

	public void setLinieID(String linieID) {
		this.linieID = linieID;
	}

	public String getN_ID() {
		return N_ID;
	}

	public void setN_ID(String n_ID) {
		N_ID = n_ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAnzahlDerBusse() {
		return anzahlDerBusse;
	}

	public void setAnzahlDerBusse(String anzahlDerBusse) {
		this.anzahlDerBusse = anzahlDerBusse;
	}

	public String getVon() {
		return von;
	}

	public void setVon(String von) {
		this.von = von;
	}

	public String getUeber() {
		return ueber;
	}

	public void setUeber(String ueber) {
		this.ueber = ueber;
	}

	public String getNach() {
		return nach;
	}

	public void setNach(String nach) {
		this.nach = nach;
	}

	public String getWannBisWann() {
		return wannBisWann;
	}

	public void setWannBisWann(String wannBisWann) {
		this.wannBisWann = wannBisWann;
	}

	public String getFarbe() {
		return farbe;
	}

	public void setFarbe(String farbe) {
		this.farbe = farbe;
	}

	public Schichten getSchicht() {
		return schicht;
	}

	public void setSchicht(Schichten schicht) {
		this.schicht = schicht;
	}

}
