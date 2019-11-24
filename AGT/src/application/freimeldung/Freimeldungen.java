package application.freimeldung;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Freimeldungen {
	private String fid;
	private String eingetragen;
	private String rechnungsnummer;
	private String plz;
	private String ort;
	private String unternehmen;
	private String datumVon;
	private String datumBis;
	private String busModel;
	private String busPlatze;
	SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
	
	
	public Freimeldungen(String fid, String eingetragen, String rechnungsnummer, String plz, String ort,
			String unternehmen, Date datumVon, Date datumBis, String busModel, String busPlatze) {
		super();
		this.fid = fid;
		this.eingetragen = eingetragen;
		this.rechnungsnummer = rechnungsnummer;
		this.plz = plz;
		this.ort = ort;
		this.unternehmen = unternehmen;
		this.datumVon = sdf.format(datumVon);
		this.datumBis = sdf.format(datumBis);
		this.busModel = busModel;
		this.busPlatze = busPlatze;
	}
	
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getEingetragen() {
		return eingetragen;
	}
	public void setEingetragen(String eingetragen) {
		this.eingetragen = eingetragen;
	}
	public String getRechnungsnummer() {
		return rechnungsnummer;
	}
	public void setRechnungsnummer(String rechnungsnummer) {
		this.rechnungsnummer = rechnungsnummer;
	}
	public String getPlz() {
		return plz;
	}
	public void setPlz(String plz) {
		this.plz = plz;
	}
	public String getOrt() {
		return ort;
	}
	public void setOrt(String ort) {
		this.ort = ort;
	}
	public String getUnternehmen() {
		return unternehmen;
	}
	public void setUnternehmen(String unternehmen) {
		this.unternehmen = unternehmen;
	}
	public String getDatumVon() {
		return datumVon;
	}
	public void setDatumVon(String datumVon) {
		this.datumVon = datumVon;
	}
	public String getDatumBis() {
		return datumBis;
	}
	public void setDatumBis(String datumBis) {
		this.datumBis = datumBis;
	}
	public String getBusModel() {
		return busModel;
	}
	public void setBusModel(String busModel) {
		this.busModel = busModel;
	}
	public String getBusPlatze() {
		return busPlatze;
	}
	public void setBusPlatze(String busPlatze) {
		this.busPlatze = busPlatze;
	}
	
	
}
