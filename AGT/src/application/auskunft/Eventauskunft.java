package application.auskunft;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Eventauskunft {
	private int aid;
	private String eintragung;
	private String eingetragenVon;
	private String plz;
	private int distanz;
	public int getDistanz() {
		return distanz;
	}
	public void setDistanz(int i) {
		this.distanz = i;
	}
	private String datumVeranstaltung;
	private String nameVeranstaltung;
	private int anzahlBusse;
	private String rechnungsNummer;
	private String art;
	SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy");
	
	public Eventauskunft(int aid, Date eintragung, String eingetragenVon, String plz, Date datumVeranstaltung,
			String nameVeranstaltung, int anzahlBusse, String rechnungsNummer,String art) {
		super();
		this.aid = aid;
		this.eintragung = sdf.format(eintragung);
		this.eingetragenVon = eingetragenVon;
		this.plz = plz;
		this.datumVeranstaltung = sdf.format(datumVeranstaltung);
		this.nameVeranstaltung = nameVeranstaltung;
		this.anzahlBusse = anzahlBusse;
		this.rechnungsNummer = rechnungsNummer;
		this.art = art;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getEintragung() {
		return eintragung;
	}
	public String getArt() {
		return art;
	}
	public void setArt(String art) {
		this.art = art;
	}
	public void setEintragung(String eintragung) {
		this.eintragung = eintragung;
	}
	public String getEingetragenVon() {
		return eingetragenVon;
	}
	public void setEingetragenVon(String eingetragenVon) {
		this.eingetragenVon = eingetragenVon;
	}
	public String getPlz() {
		return plz;
	}
	public void setPlz(String plz) {
		this.plz = plz;
	}
	public String getDatumVeranstaltung() {
		return datumVeranstaltung;
	}
	public void setDatumVeranstaltung(String datumVeranstaltung) {
		this.datumVeranstaltung = datumVeranstaltung;
	}
	public String getNameVeranstaltung() {
		return nameVeranstaltung;
	}
	public void setNameVeranstaltung(String nameVeranstaltung) {
		this.nameVeranstaltung = nameVeranstaltung;
	}
	public int getAnzahlBusse() {
		return anzahlBusse;
	}
	public void setAnzahlBusse(int anzahlBusse) {
		this.anzahlBusse = anzahlBusse;
	}
	public String getRechnungsNummer() {
		return rechnungsNummer;
	}
	public void setRechnungsNummer(String rechnungsNummer) {
		this.rechnungsNummer = rechnungsNummer;
	}
	@Override
	public String toString() {
		return "Eventauskunft [aid=" + aid + ", eintragung=" + eintragung + ", eingetragenVon=" + eingetragenVon
				+ ", plz=" + plz + ", datumVeranstaltung=" + datumVeranstaltung + ", nameVeranstaltung="
				+ nameVeranstaltung + ", anzahlBusse=" + anzahlBusse + ", rechnungsNummer=" + rechnungsNummer + "]";
	}
	
	

}
