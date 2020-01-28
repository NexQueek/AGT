package application.tabelle;

import javafx.scene.paint.Color;

public class Linie {
	private String linieID;
	private String N_ID; //Werk/niederlassungsid
	private String name ;
	private String anzahlDerBusse;
	private String von;
	private String ueber;
	private String nach;
	private String wannBisWann;
	private String farbe;
	private Color color;
	//private Schichten schicht;
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
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
	
}
