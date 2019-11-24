package application.marge;

import java.text.DecimalFormat;

public class KalenderWocheMarge {
	
	private String kalenderwoche;
	private String  vorgang;
	private String umsatz;
	private String marge;
	private String margeProzent;
	private String ekPreis;
	DecimalFormat dcf = new DecimalFormat("#0.00");
	
	
	public KalenderWocheMarge(String kalenderwoche, String vorgang, String umsatz, String ekPreis) {
		super();
		this.kalenderwoche = kalenderwoche;
		this.vorgang = vorgang;
		this.umsatz = umsatz;
		this.ekPreis = ekPreis;
	}
	public String getKalenderwoche() {
		return kalenderwoche;
	}
	public void setKalenderwoche(String kalenderwoche) {
		this.kalenderwoche = kalenderwoche;
	}
	public String getVorgang() {
		return vorgang;
	}
	public void setVorgang(String vorgang) {
		this.vorgang = vorgang;
	}
	public String getUmsatz() {
		return umsatz;
	}
	public void setUmsatz(String umsatz) {
		this.umsatz = umsatz;
	}
	public String getMarge() {
		
		double margeAnteil = Double.parseDouble(umsatz) - Double.parseDouble(ekPreis); 
		marge = dcf.format(margeAnteil);
		//margeAnteil = Double.parseDouble(a);
		setMargeProzent(marge);
		return marge;
		
	}
	public void setMarge(String marge) {
		this.marge = marge;
	}
	public String getMargeProzent() {
		return margeProzent;
	}
	public void setMargeProzent(String margeAnteil) {
		double margeProzent1 = (Double.parseDouble(margeAnteil.replace(',', '.'))/Double.parseDouble(ekPreis))*100;
		margeProzent = dcf.format(margeProzent1);
		
	}
	public String getEkPreis() {
		return ekPreis;
	}
	public void setEkPreis(String ekPreis) {
		this.ekPreis = ekPreis;
	}
	
	

}
