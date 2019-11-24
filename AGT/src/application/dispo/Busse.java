package application.dispo;

public class Busse {
	
	private String bid;
	private String uid;
	private String typ;
	private String groesse;
	private String farbe;
	private String eigenschaft1;
	private String eigenschaft2;
	
	
	
	
	public Busse(String bid, String uid, String typ, String groesse, String farbe, String eigenschaft1,
			String eigenschaft2) {
		super();
		this.bid = bid;
		this.uid = uid;
		this.typ = typ;
		this.groesse = groesse;
		this.farbe = farbe;
		this.eigenschaft1 = eigenschaft1;
		this.eigenschaft2 = eigenschaft2;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getTyp() {
		return typ;
	}
	public void setTyp(String typ) {
		this.typ = typ;
	}
	public String getGroesse() {
		return groesse;
	}
	public void setGroesse(String groesse) {
		this.groesse = groesse;
	}
	public String getFarbe() {
		return farbe;
	}
	public void setFarbe(String farbe) {
		this.farbe = farbe;
	}
	public String getEigenschaft1() {
		return eigenschaft1;
	}
	public void setEigenschaft1(String eigenschaft1) {
		this.eigenschaft1 = eigenschaft1;
	}
	public String getEigenschaft2() {
		return eigenschaft2;
	}
	public void setEigenschaft2(String eigenschaft2) {
		this.eigenschaft2 = eigenschaft2;
	}
	
	
	

}
