package application;

import java.util.ArrayList;

public class Benutzer {
	private String name;
	private String passwort;
	private boolean dispo;
	private boolean admin;
	private boolean bewertung;
	private boolean marge;
	private boolean auskunft;
	private boolean zubehor;
	private boolean recht1;
	private boolean recht2;
	private String userId;
	
	public static ArrayList<Benutzer> benutz = new ArrayList<>();
	
	public Benutzer(String userId,String name, String passwort, boolean dispo, boolean admin, boolean bewertung, boolean marge,
			boolean auskunft, boolean zubehor, boolean recht1, boolean recht2) {
		super();
		this.userId = userId;
		this.name = name;
		this.passwort = passwort;
		this.dispo = dispo;
		this.admin = admin;
		this.bewertung = bewertung;
		this.marge = marge;
		this.auskunft = auskunft;
		this.zubehor = zubehor;
		this.recht1 = recht1;
		this.recht2 = recht2;
	}
	public String getName() {
		return name;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswort() {
		return passwort;
	}
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}
	public boolean isDispo() {
		return dispo;
	}
	public void setDispo(boolean dispo) {
		this.dispo = dispo;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public boolean isBewertung() {
		return bewertung;
	}
	public void setBewertung(boolean bewertung) {
		this.bewertung = bewertung;
	}
	public boolean isMarge() {
		return marge;
	}
	public void setMarge(boolean marge) {
		this.marge = marge;
	}
	public boolean isAuskunft() {
		return auskunft;
	}
	public void setAuskunft(boolean auskunft) {
		this.auskunft = auskunft;
	}
	public boolean isZubehor() {
		return zubehor;
	}
	public void setZubehor(boolean zubehor) {
		this.zubehor = zubehor;
	}
	public boolean isRecht1() {
		return recht1;
	}
	public void setRecht1(boolean recht1) {
		this.recht1 = recht1;
	}
	public boolean isRecht2() {
		return recht2;
	}
	public void setRecht2(boolean recht2) {
		this.recht2 = recht2;
	}
	
	public void addBenutzer(Benutzer e){
		benutz.add(e);
	}
	
	public ArrayList<Benutzer> getListe(){
		return benutz;
	}
	//Wichtig für die CHECKED Tabelle
	public String getDispoS(){
		String b ="";
		if(dispo){
			b ="true";
		}else{
			b="false";
		}
		return b;
	}
	public String getMargeS(){
		String b ="";
		if(marge){
			b ="true";
		}else{
			b="false";
		}
		return b;
	}
	
	public String getBewertungS(){
		String b ="";
		if(bewertung){
			b ="true";
		}else{
			b="false";
		}
		return b;
	}
	public String getAdminS(){
		String b ="";
		if(admin){
			b ="true";
		}else{
			b="false";
		}
		return b;
	}
	public String getAuskunftS(){
		String b ="";
		if(auskunft){
			b ="true";
		}else{
			b="false";
		}
		return b;
	}
	public String getZubehoerS(){
		String b ="";
		if(zubehor){
			b ="true";
		}else{
			b="false";
		}
		return b;
	}
	
	@Override
	public String toString() {
		return "Benutzer [name=" + name + ", passwort=" + passwort + ", dispo=" + dispo + ", admin=" + admin
				+ ", bewertung=" + bewertung + ", marge=" + marge + ", auskunft=" + auskunft + ", zubehor=" + zubehor
				+ ", recht1=" + recht1 + ", recht2=" + recht2 + "]";
	}
	

}
