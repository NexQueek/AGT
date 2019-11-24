package application.bewertung;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Bewertung {
	private String bid;
	private String uid;
	private String bewertetVon;
	private String bewertetAm;
	private String rechnungsnummer;
	private String thematik;
	private int erreichbar;
	private int zuverlassig;
	private int service;
	private int sauber;
	private String beschreibung;
	private String loesung;
	private String bild;
	SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
	public Bewertung(String bid, String uid, String bewertetVon, Date bewertetAm, String rechnungsnummer,
			String thematik, int erreichbar, int zuverlassig, int service, int sauber, String beschreibung,
			String loesung, String bild) {
		super();
		this.bid = bid;
		this.uid = uid;
		this.bewertetVon = bewertetVon;
		this.bewertetAm = sdf.format(bewertetAm);
		this.rechnungsnummer = rechnungsnummer;
		this.thematik = thematik;
		this.erreichbar = erreichbar;
		this.zuverlassig = zuverlassig;
		this.service = service;
		this.sauber = sauber;
		this.beschreibung = beschreibung;
		this.loesung = loesung;
		this.bild = bild;
		
	}
	public String getBid() {
		return bid;
	}
	public String getBild(){
		return bild;
	}
	public String getUid() {
		return uid;
	}
	public String getBewertetVon() {
		return bewertetVon;
	}
	public String getBewertetAm() {
		return bewertetAm;
	}
	public String getRechnungsnummer() {
		return rechnungsnummer;
	}
	public String getThematik() {
		return thematik;
	}
	public int getErreichbar() {
		return erreichbar;
	}
	public int getZuverlassig() {
		return zuverlassig;
	}
	public int getService() {
		return service;
	}
	public int getSauber() {
		return sauber;
	}
	public String getBeschreibung() {
		return beschreibung;
	}
	public String getLoesung() {
		return loesung;
	}
}
