package application.tabelle;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Schichten implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2588383469709627883L;
	private String idSchicht;
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
	private boolean montag;
	private boolean dienstag;
	private boolean mittwoch;
	private boolean donnerstag;
	private boolean freitag;
	private boolean samstag;
	private boolean sonntag;
	private LocalDate anfangsdatum;

	private ArrayList<String> listeAllerSchichten;
	private ArrayList<String> listeSchichtenName;
	private ArrayList<Boolean> listeAllerTage;

	public ArrayList<String> getListeAllerSchichten() {
		listeAllerSchichten = new ArrayList<>();
		listeSchichtenName = new ArrayList<>();
		if (getFruhHin().equals("")) {

		} else {
			listeAllerSchichten.add(getFruhHin());
			listeSchichtenName.add("FrühHin");
		}
		if (getFruhTeil().equals("")) {

		} else {
			listeAllerSchichten.add(getFruhTeil());
			listeSchichtenName.add("FrühTZ zurück");
		}
		if (getFruhZu().equals("")) {

		} else {
			listeAllerSchichten.add(getFruhZu());
			listeSchichtenName.add("FrühZurück");
		}
		if (getSpatHin().equals("")) {

		} else {
			listeAllerSchichten.add(getSpatHin());
			listeSchichtenName.add("SpaetHin");
		}
		if (getSpatTeil().equals("")) {

		} else {
			listeAllerSchichten.add(getSpatTeil());
			listeSchichtenName.add("SpaetTZ");
		}
		if (getSpatZu().equals("")) {

		} else {
			listeAllerSchichten.add(getSpatZu());
			listeSchichtenName.add("SpaetZu");
		}
		if (getNachtHin().equals("")) {

		} else {
			listeAllerSchichten.add(getNachtHin());
			listeSchichtenName.add("NachtHin");
		}
		if (getNachtTeil().equals("")) {

		} else {
			listeAllerSchichten.add(getNachtTeil());
			listeSchichtenName.add("NachtTZ");
		}
		if (getNachtZu().equals("")) {

		} else {
			listeAllerSchichten.add(getNachtZu());
			listeSchichtenName.add("NachtZurück");
		}

		return listeAllerSchichten;
	}

	public ArrayList<String> getListeSchichtenName() {
		return listeSchichtenName;
	}

	public LocalDate getAnfangsdatum() {
		return anfangsdatum;
	}

	public void setAnfangsdatum(LocalDate anfangsdatum) {
		this.anfangsdatum = anfangsdatum;
	}

	// wegen der size
	public ArrayList<Boolean> getListeAllerTage() {
		
		listeAllerTage = new ArrayList<>();

		listeAllerTage.add(isMontag());

		listeAllerTage.add(isDienstag());

		listeAllerTage.add(isMittwoch());

		listeAllerTage.add(isDonnerstag());

		listeAllerTage.add(isFreitag());

		listeAllerTage.add(isSamstag());

		listeAllerTage.add(isSonntag());

		return listeAllerTage;
	}

	public boolean isMontag() {
		return montag;
	}

	public void setMontag(boolean montag) {
		this.montag = montag;
	}

	public boolean isDienstag() {
		return dienstag;
	}

	public void setDienstag(boolean dienstag) {
		this.dienstag = dienstag;
	}

	public boolean isMittwoch() {
		return mittwoch;
	}

	public void setMittwoch(boolean mittwoch) {
		this.mittwoch = mittwoch;
	}

	public boolean isDonnerstag() {
		return donnerstag;
	}

	public void setDonnerstag(boolean donnerstag) {
		this.donnerstag = donnerstag;
	}

	public boolean isFreitag() {
		return freitag;
	}

	public void setFreitag(boolean freitag) {
		this.freitag = freitag;
	}

	public boolean isSamstag() {
		return samstag;
	}

	public void setSamstag(boolean samstag) {
		this.samstag = samstag;
	}

	public boolean isSonntag() {
		return sonntag;
	}

	public void setSonntag(boolean sonntag) {
		this.sonntag = sonntag;
	}

	public void setListeAllerSchichten(ArrayList<String> listeAllerSchichten) {
		this.listeAllerSchichten = listeAllerSchichten;
	}

	public void setListeSchichtenName(ArrayList<String> listeSchichtenName) {
		this.listeSchichtenName = listeSchichtenName;
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
