package application.tabelle;

import java.io.Serializable;

public class Busse implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4474263251945304012L;
	private String name;
	private String groesse;
	private String art;
	private String kennzeichen;
	private String fahrer;
	private String unternehmer;
	private String preisProTag;
    private Schichten schicht;
	
	
	public Schichten getSchicht() {
		return schicht;
	}
	public void setSchicht(Schichten schicht) {
		this.schicht = schicht;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGroesse() {
		return groesse;
	}
	public void setGroesse(String groesse) {
		this.groesse = groesse;
	}
	public String getArt() {
		return art;
	}
	public void setArt(String art) {
		this.art = art;
	}
	public String getKennzeichen() {
		return kennzeichen;
	}
	public void setKennzeichen(String kennzeichen) {
		this.kennzeichen = kennzeichen;
	}
	public String getFahrer() {
		return fahrer;
	}
	public void setFahrer(String fahrer) {
		this.fahrer = fahrer;
	}
	public String getUnternehmer() {
		return unternehmer;
	}
	public void setUnternehmer(String unternehmer) {
		this.unternehmer = unternehmer;
	}
	public String getPreisProTag() {
		return preisProTag;
	}
	public void setPreisProTag(String preisProTag) {
		this.preisProTag = preisProTag;
	}
	
	
	
}
