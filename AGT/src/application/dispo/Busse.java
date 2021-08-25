package application.dispo;

import javafx.scene.image.ImageView;

public class Busse {

	private String bid;
	private String uid;
	private String typ;
	private String groesse;
	private String farbe;
	private String eigenschaft1;
	private String eigenschaft2;
	private ImageView bild;
	private String bildDatei;
	private String branding;
	private String marke;
	public String getBranding() {
		return branding;
	}

	public void setBranding(String branding) {
		this.branding = branding;
	}

	public String getMarke() {
		return marke;
	}

	public void setMarke(String marke) {
		this.marke = marke;
	}

	private static Busse b = null;
	
	
	public ImageView getBild() {
		
		if(bild == null){
			return null;
		}else{
			bild.setPreserveRatio(true);
			bild.setFitWidth(120);
			bild.setFitHeight(120);
		}
		
		return bild;
	}

	public void setBild(ImageView bild) {
		this.bild = bild;
	}

	public String getBildDatei() {
		return bildDatei;
	}

	public void setBildDatei(String bildDatei) {
		this.bildDatei = bildDatei;
	}

	private String sql;
	private String platzBis;

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

	public void setSql(String sql) {
		this.sql = sql;
	}

	public void setPlatzBis(String platzBis) {
		this.platzBis = platzBis;
	}

	public String getPlatzVonBis() {
		return getGroesse() + " - " + getPlatzBis();
	}
	/**
	 * 
	 * @return sql:String der dazu dient um die Eine Query mit n-Anzahl bussen zu executen
	 */
	public String getSql() {
		sql ="and ";
		return sql;
	}

	public String getPlatzBis() {
		return platzBis;
	}

	public static Busse getB() {
		return b;
	}

	public static void setB(Busse b) {
		Busse.b = b;
	}

}
