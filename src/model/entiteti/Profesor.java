package model.entiteti;

import java.util.ArrayList;

public class Profesor extends Osoba {
	
	private String kanc;
	private String licna;
	private String titula;
	private String zvanje;
	private ArrayList<Predmet> predmeti = new ArrayList<Predmet>();
	
	public Profesor(String i, String p, String d, String a, String t, String e, String k, String l, String tit, String z) {
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
		return zvanje;
	}

	public void setZvanje(String zvanje) {
		this.zvanje = zvanje;
	}

	public ArrayList<Predmet> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(ArrayList<Predmet> predmeti) {
		this.predmeti = predmeti;
	}	
}