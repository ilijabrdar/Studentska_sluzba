package model;

import java.util.ArrayList;

public class Predmet {
	
	private String sifra;
	private String naziv;
	private String semestar;
	private int godina;
	private Profesor prof;
	//private ArrayList<Student> studenti;
	
	public Predmet(String sifra, String naziv, String semestar, int godina, Profesor prof) {
		this.sifra = sifra;
		this.naziv = naziv;
		this.semestar = semestar;
		this.godina = godina;
		this.prof = prof;
		// LISTA STUDENATA!!!!!
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getSemestar() {
		return semestar;
	}

	public void setSemestar(String semestar) {
		this.semestar = semestar;
	}

	public int getGodina() {
		return godina;
	}
	
	public String getStrGodina() {
		switch (godina) {
		case 1: return "Prva"; 
		case 2: return "Druga"; 
		case 3: return "Treća"; 
		case 4: return "Četvrta";
		default: return "Prva";
		}
	}

	public void setGodina(int godina) {
		this.godina = godina;
	}

	public void setStrGodina(String god) {
		switch (god) {
		case "Prva" : godina = 1; break;
		case "Druga" : godina = 2; break;
		case "Treća" : godina = 3; break;
		case "Četvrta" : godina = 4; break;
		default: godina = 1;
		}
	}
	
	public Profesor getProf() {
		return prof;
	}

	public void setProf(Profesor prof) {
		this.prof = prof;
	}

	@Override
	public String toString() {
		return "Predmet [sifra=" + sifra + ", naziv=" + naziv + ", semestar=" + semestar + ", godina=" + godina
				+ ", prof=" + prof + "]";
	}
	
	
}
