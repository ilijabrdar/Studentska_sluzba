package model;

import java.util.ArrayList;

public class Profesor {
	
	private String ime;
	private String prezime;
	private String datum;
	private String adresa;
	private int telefon;
	private String email;
	private String kanc;
	private int licna;
	private String titula;
	private String zvanje;
	private ArrayList<Predmet> predmeti;
	
	public Profesor(String i, String p, String d, String a, int t, String e, String k, int l, String tit, String z, ArrayList<Predmet> pr) {
		this.ime = i;
		this.prezime = p;
		this.datum = d;
		this.adresa = a;
		this.telefon = t;
		this.email = e;
		this.kanc = k;
		this.licna = l;
	    this.titula = tit;
	    this.zvanje = z;
	    this.predmeti = pr;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public int getTelefon() {
		return telefon;
	}

	public void setTelefon(int telefon) {
		this.telefon = telefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	@Override
	public String toString() {
		return  ime + " " + prezime;
	}
	
	
}
