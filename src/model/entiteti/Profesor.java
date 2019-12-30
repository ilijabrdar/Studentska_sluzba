package model.entiteti;

import java.util.ArrayList;

public class Profesor extends Osoba {
	public enum Zvanje {Dipl,Mast,Spec,Dr,Struk,SpecStruk};
	private String kanc;
	private String licna;
	private String titula;
	private Zvanje zvanje;
	private ArrayList<Predmet> predmeti = new ArrayList<Predmet>();
	
	public Profesor(String i, String p, String d, String a, String t, String e, String k, String l, String tit, Zvanje z) {
		super(i, p, d, a, t, e);
		this.kanc = k;
		this.licna = l;
	    this.titula = tit;
	    this.zvanje = z;
	}

	public String getKanc() {
		return kanc;
	}

	public void setKanc(String kanc) {
		this.kanc = kanc;
	}

	public String getLicna() {
		return licna;
	}

	public void setLicna(String licna) {
		this.licna = licna;
	}

	public String getTitula() {
		return titula;
	}

	public void setTitula(String titula) {
		this.titula = titula;
	}

	public String getZvanje() {
		return zvanje.toString();
	}

	public void setZvanje(String zvanje) {
		this.zvanje = Zvanje.valueOf(zvanje);
	}

	public ArrayList<Predmet> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(ArrayList<Predmet> predmeti) {
		this.predmeti = predmeti;
	}	
}
