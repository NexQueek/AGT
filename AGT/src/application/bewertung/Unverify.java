package application.bewertung;

import java.sql.Date;
import java.text.SimpleDateFormat;


public class Unverify {
	private String unBid;
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
	private Date datumam;
	private String unterName;
	
	
	public static Unverify u;
	SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
	
	public Unverify(String unBid, String uid, String bewertetVon, Date bewertetAm, String rechnungsnummer,
			String thematik, int erreichbar, int zuverlassig, int service, int sauber, String beschreibung,
			String loesung) {
		super();
		this.unBid = unBid;
		this.uid = uid;
		this.bewertetVon = bewertetVon;
		this.bewertetAm = sdf.format(bewertetAm);
		setDate(bewertetAm);
		this.rechnungsnummer = rechnungsnummer;
		this.thematik = thematik;
		this.erreichbar = erreichbar;
		this.zuverlassig = zuverlassig;
		this.service = service;
		this.sauber = sauber;
		this.beschreibung = beschreibung;
		this.loesung = loesung;
	}
	public String getBild() {
		return bild;
	}
	public Date getDate(){
		return datumam;
	}
	void setDate(Date d){
		this.datumam = d;
	}
	public void setBild(String bild) {
		this.bild = bild;
	}
	public String getBeschreibung() {
		return beschreibung;
	}
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	public String getLoesung() {
		return loesung;
	}
	public void setLoesung(String loesung) {
		this.loesung = loesung;
	}
	public String getUnBid() {
		return unBid;
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
	public String getUnterName() {
		return unterName;
	}
	public void setUnterName(String unterName) {
		this.unterName = unterName;
	}

}
