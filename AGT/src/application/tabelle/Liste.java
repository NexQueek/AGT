package application.tabelle;

public class Liste {
	private String busNr;
	private String bemerkungen;
	private String KW;
	private String tag;
	private String abfahrtort;
	private String uhrzeit;
	private String schicht;
	private String fahrer;
	private String handynr;
	
	
	
	
	
	public Liste(String kW, String tag, String abfahrtort, String uhrzeit, String schicht, String fahrer,
			String handynr) {
		super();
		KW = kW;
		this.tag = tag;
		this.abfahrtort = abfahrtort;
		this.uhrzeit = uhrzeit;
		this.schicht = schicht;
		this.fahrer = fahrer;
		this.handynr = handynr;
	}
	public String getKW() {
		return KW;
	}
	public void setKW(String kW) {
		KW = kW;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
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
	
	
	
	
}
