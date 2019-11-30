package model;

import java.util.ArrayList;

public class Profesor extends Osoba {
	
	private String kanc;
	private int licna;
	private String titula;
	private String zvanje;
	private ArrayList<Predmet> predmeti;
	
	public Profesor(String i, String p, String d, String a, int t, String e, String k, int l, String tit, String z, ArrayList<Predmet> pr) {
		super(i, p, d, a, t, e);
		this.kanc = k;
		this.licna = l;
	    this.titula = tit;
	    this.zvanje = z;
	    this.predmeti = pr;
	}

	public String getKanc() {
		return kanc;
	}

	public void setKanc(String kanc) {
		this.kanc = kanc;
	}

	public int getLicna() {
		return licna;
	}

	public void setLicna(int licna) {
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
