package application.tabelle;

import java.util.ArrayList;

public class Schichten {
	private String idSchicht ;
	private String idLinie;
	private String fruhHin;
	private String fruhTeil;
	private String fruhZu;
	private String spatHin;
	private String spatTeil;
	private String spatZu;
	private String nachtHin;
	private String nachtTeil;
	private String nachtZu;
	private ArrayList<String> listeAllerSchichten;
	
	
	public ArrayList<String> getListeAllerSchichten() {
		listeAllerSchichten = new ArrayList<>();
		if(getFruhHin()==null){
			
		}else{
			listeAllerSchichten.add(getFruhHin());
		}
		if(getFruhTeil()==null){
			
		}else{
			listeAllerSchichten.add(getFruhTeil());
		}
		if(getFruhZu()==null){
			
		}else{
			listeAllerSchichten.add(getFruhZu());
		}
		if(getSpatHin()==null){
			
		}else{
			listeAllerSchichten.add(getSpatHin());
		}
		if(getSpatTeil()==null){
			
		}else{
			listeAllerSchichten.add(getSpatTeil());
		}
		if(getSpatZu()==null){
			
		}else{
			listeAllerSchichten.add(getSpatZu());
		}
		if(getNachtHin()==null){
			
		}else{
			listeAllerSchichten.add(getNachtHin());
		}
		if(getNachtTeil()==null){
			
		}else{
			listeAllerSchichten.add(getNachtTeil());
		}
		if(getNachtZu()==null){
			
		}else{
			listeAllerSchichten.add(getNachtZu());
		}
		
		
		return listeAllerSchichten;
	}
	public String getIdSchicht() {
		return idSchicht;
	}
	public void setIdSchicht(String idSchicht) {
		this.idSchicht = idSchicht;
	}
	public String getIdLinie() {
		return idLinie;
	}
	public void setIdLinie(String idLinie) {
		this.idLinie = idLinie;
	}
	public String getFruhHin() {
		return fruhHin;
	}
	public void setFruhHin(String fruhHin) {
		this.fruhHin = fruhHin;
	}
	public String getFruhTeil() {
		return fruhTeil;
	}
	public void setFruhTeil(String fruhTeil) {
		this.fruhTeil = fruhTeil;
	}
	public String getFruhZu() {
		return fruhZu;
	}
	public void setFruhZu(String fruhZu) {
		this.fruhZu = fruhZu;
	}
	public String getSpatHin() {
		return spatHin;
	}
	public void setSpatHin(String spatHin) {
		this.spatHin = spatHin;
	}
	public String getSpatTeil() {
		return spatTeil;
	}
	public void setSpatTeil(String spatTeil) {
		this.spatTeil = spatTeil;
	}
	public String getSpatZu() {
		return spatZu;
	}
	public void setSpatZu(String spatZu) {
		this.spatZu = spatZu;
	}
	public String getNachtHin() {
		return nachtHin;
	}
	public void setNachtHin(String nachtHin) {
		this.nachtHin = nachtHin;
	}
	public String getNachtTeil() {
		return nachtTeil;
	}
	public void setNachtTeil(String nachtTeil) {
		this.nachtTeil = nachtTeil;
	}
	public String getNachtZu() {
		return nachtZu;
	}
	public void setNachtZu(String nachtZu) {
		this.nachtZu = nachtZu;
	}

	
}
