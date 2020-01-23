package application.tabelle;

import java.sql.Date;

public class Liste {
	private String busNr;
	private String bemerkungen;
	private String ansprechpartner;
	private String kurzel;
	private String KW;
	private Date datum;
	private String abfahrtort;
	private String uhrzeit;
	private String schicht;
	private String tag;	
	private String fahrer;
	private String handynr;
	private String sollBus;
	private String istBus;
	private String preisNetto;
	private String brutto;
	private String ek;
	private String marge;
	private String gesamt;
	private int fahrtId;
	private int nId; 
	public int getFahrtId() {
		return fahrtId;
	}
	public void setFahrtId(int fahrtId) {
		this.fahrtId = fahrtId;
	}
	public int getnId() {
		return nId;
	}
	public void setnId(int nId) {
		this.nId = nId;
	}
	public String getBusNr() {
		return busNr;
	}
	public void setBusNr(String busNr) {
		this.busNr = busNr;
	}
	public String getBemerkungen() {
		return bemerkungen;
	}
	public void setBemerkungen(String bemerkungen) {
		this.bemerkungen = bemerkungen;
	}
	public String getAnsprechpartner() {
		return ansprechpartner;
	}
	public void setAnsprechpartner(String ansprechpartner) {
		this.ansprechpartner = ansprechpartner;
	}
	public String getKurzel() {
		return kurzel;
	}
	public void setKurzel(String kurzel) {
		this.kurzel = kurzel;
	}
	public String getKW() {
		return KW;
	}
	public void setKW(String kW) {
		KW = kW;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	public String getAbfahrtort() {
		return abfahrtort;
	}
	public void setAbfahrtort(String abfahrtort) {
		this.abfahrtort = abfahrtort;
	}
	public String getUhrzeit() {
		return uhrzeit;
	}
	public void setUhrzeit(String uhrzeit) {
		this.uhrzeit = uhrzeit;
	}
	public String getSchicht() {
		return schicht;
	}
	public void setSchicht(String schicht) {
		this.schicht = schicht;
		
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getFahrer() {
		return fahrer;
	}
	public void setFahrer(String fahrer) {
		this.fahrer = fahrer;
	}
	public String getHandynr() {
		return handynr;
	}
	public void setHandynr(String handynr) {
		this.handynr = handynr;
	}
	public String getSollBus() {
		return sollBus;
	}
	public void setSollBus(String sollBus) {
		this.sollBus = sollBus;
	}
	public String getIstBus() {
		return istBus;
	}
	public void setIstBus(String istBus) {
		this.istBus = istBus;
	}
	public String getPreisNetto() {
		return preisNetto;
	}
	public void setPreisNetto(String preisNetto) {
		this.preisNetto = preisNetto;
	}
	public String getBrutto() {
		return brutto;
	}
	public void setBrutto(String brutto) {
		this.brutto = brutto;
	}
	public String getEk() {
		return ek;
	}
	public void setEk(String ek) {
		this.ek = ek;
	}
	public String getMarge() {
		return marge;
	}
	public void setMarge(String marge) {
		this.marge = marge;
	}
	public String getGesamt() {
		return gesamt;
	}
	public void setGesamt(String gesamt) {
		this.gesamt = gesamt;
	}
}
	
	
	
	
	
	
	