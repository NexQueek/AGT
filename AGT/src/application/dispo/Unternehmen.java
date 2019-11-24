package application.dispo;

public class Unternehmen {
	private String uid;
	private String plz;
	private String ort;
	private String name;
	private String mail;
	private String telefon;
	private String mobil;
	private String chef;
	private String groesse;
	private String farbe; 
	private String besonderheiten;
	private String typ;
	private String bewertungen;
	private String infos;
	private String detail;
	private String distanz;
	
	public String getDistanz() {
		return distanz;
	}

	public void setDistanz(String distanz) {
		this.distanz = distanz;
	}

	public Unternehmen(String uid, String plz, String ort, String name, String mail, String  telefon, String mobil,String chef,String groesse,String farbe,String typ,String besonderheiten, String infos ){
		this.uid =uid;
		this.plz = plz;
		this.ort = ort;
		this.name = name;
		this.mail =mail;
		this.telefon = telefon;
		this.mobil = mobil;
		this.chef = chef;
		this.groesse = groesse;
		this.farbe = farbe;
		this.typ = typ;
		this.besonderheiten = besonderheiten;
		this.infos = infos;
	
	}
	
	public String getInfos(){
		return infos;
	}
	
	public String getUid() {
		return uid;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String s){
		this.detail = s;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}


	public String getPLZ() {
		return plz;
	}


	public void setPLZ(String plz) {
		this.plz = plz;
	}
	public String getBewertungen(){
		return bewertungen;
	}


	public String getOrt() {
		return ort;
	}
	public void setOrt(String ort) {
		this.ort = ort;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getMobil() {
		return mobil;
	}
	public void setMobil(String mobil) {
		this.mobil = mobil;
	}
	public String getChef() {
		return chef;
	}
	public void setChef(String chef) {
		this.chef = chef;
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
	public String getBesonderheiten() {
		return besonderheiten;
	}
	public void setBesonderheiten(String besonderheiten) {
		this.besonderheiten = besonderheiten;
	}
	public String getTyp() {
		return typ;
	}
	public void setTyp(String typ) {
		this.typ = typ;
	}
	

}