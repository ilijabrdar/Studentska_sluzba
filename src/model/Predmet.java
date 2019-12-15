package model;

import java.util.ArrayList;

public class Predmet {
	
	private String sifra;
	private String naziv;
	private String semestar;
	private String godina;
	private ArrayList<Profesor> profs;
	private ArrayList<Student> studenti;
	
	public Predmet(String sifra, String naziv, String semestar, String godina) {
		this.sifra = sifra;
		this.naziv = naziv;
		this.semestar = semestar;
		this.godina = godina;
		profs = new ArrayList<Profesor>();
		studenti = new ArrayList<Student>();
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

	public String getGodina() {
		return godina;
	}
	
	public String getStrGodina() {
		switch (godina) {
		case "1": return "Prva"; 
		case "2": return "Druga"; 
		case "3": return "Treća"; 
		case "4": return "Četvrta";
		default: return "Prva";
		}
	}
	
	public void setGodina(String godina) {
		this.godina = godina;
	}
	
	public void setStrGodina(String god) {
		switch (god) {
		case "Prva" : godina = "1"; break;
		case "Druga" : godina = "2"; break;
		case "Treća" : godina = "3"; break;
		case "Četvrta" : godina = "4"; break;
		default: godina = "1";
		}
	}
	
	public ArrayList<Profesor> getProfs() {
		return profs;
	}

	public void setProfs(ArrayList<Profesor> profs) {
		this.profs = profs;
	}

	public ArrayList<Student> getStudenti() {
		return studenti;
	}

	public void setStudenti(ArrayList<Student> studenti) {
		this.studenti = studenti;
	}

	public void addProfessor(Profesor prof) {
		for (Profesor p : profs)
			if(p.getLicna() == prof.getLicna())
				return;
		
		profs.add(prof);
	}
	
	@Override
	public String toString() {
		return "Predmet [sifra=" + sifra + ", naziv=" + naziv + ", semestar=" + semestar + ", godina=" + godina + "]";
	}
	
	
}
