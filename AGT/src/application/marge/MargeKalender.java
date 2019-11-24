package application.marge;

import java.text.DecimalFormat;

public class MargeKalender {
	private String mkid;
	private String kalenderwoche;
	private String einkaufspreis;
	private String verkaufspreis;
	private String uid;
	private String team;
	private String rechnungsnummer;
	private String jahr;
	private double margeAnteil;
	private double margeProzent;
	private String margeProzentGerundet;
	DecimalFormat dcf = new DecimalFormat("#0.00");
	
	
	public MargeKalender(String mkid, String kalenderwoche, String einkaufspreis, String verkaufspreis, String uid,
			String team, String rechnungsnummer, String jahr) {
		super();
		this.mkid = mkid;
		this.kalenderwoche = kalenderwoche;
		this.einkaufspreis = einkaufspreis;
		this.verkaufspreis = verkaufspreis;
		this.uid = uid;
		this.team = team;
		this.rechnungsnummer = rechnungsnummer;
		this.jahr = jahr;
	}
	public String getMkid() {
		return mkid;
	}
	public void setMkid(String mkid) {
		this.mkid = mkid;
	}
	public String getKalenderwoche() {
		return kalenderwoche;
	}
	public void setKalenderwoche(String kalenderwoche) {
		this.kalenderwoche = kalenderwoche;
	}
	public String getEinkaufspreis() {
		return einkaufspreis;
	}
	public void setEinkaufspreis(String einkaufspreis) {
		this.einkaufspreis = einkaufspreis;
	}
	public String getVerkaufspreis() {
		return verkaufspreis;
	}
	public void setVerkaufspreis(String verkaufspreis) {
		this.verkaufspreis = verkaufspreis;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getRechnungsnummer() {
		return rechnungsnummer;
	}
	public void setRechnungsnummer(String rechnungsnummer) {
		this.rechnungsnummer = rechnungsnummer;
	}
	public String getJahr() {
		return jahr;
	}
	public void setJahr(String jahr) {
		this.jahr = jahr;
	}
	
	
	public String getMargeAnteil() {
		margeAnteil = Double.parseDouble(verkaufspreis) - Double.parseDouble(einkaufspreis); 
		String a = dcf.format(margeAnteil);
		//margeAnteil = Double.parseDouble(a);
		setMargeProzent(a);
		return a;
	}
	public void setMargeProzent(String margeAnteil ){
		margeProzent = (Double.parseDouble(margeAnteil.replace(',', '.'))/Double.parseDouble(einkaufspreis))*100;
		margeProzentGerundet = dcf.format(margeProzent);
	}
	public String getMargeProzent() {
		 
		return margeProzentGerundet;
	}
	
	
	
}
