package model.entiteti;

import java.util.ArrayList;
import java.util.Date;


public class Student extends Osoba {
	
	public enum Status {B,S}
	private String index;
	private String datum_upisa; //TODO: prebaci u Date
	private int godina_studija;
	private Status status;
	private double prosek;
	ArrayList<Predmet> predmeti = new ArrayList<Predmet>();
	

	public Student(String ime, String prezime, String datum, String adresa, String telefon, String email, String index,
			String datum_upisa, int godina_studija, Status status,double prosek) {
		super(ime, prezime, datum, adresa, telefon, email);
		this.index = index;
		this.datum_upisa = datum_upisa;
		this.godina_studija = godina_studija;
		this.status = status;
		this.prosek = prosek;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getDatum_upisa() {
		return datum_upisa;
	}

	public void setDatum_upisa(String datum_upisa) {
		this.datum_upisa = datum_upisa;
	}

	public int getGodina_studija() {
		return godina_studija;
	}

	public void setGodina_studija(int godina_studija) {
		this.godina_studija = godina_studija;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public double getProsek() {
		return prosek;
	}

	public void setProsek(double prosek) {
		this.prosek = prosek;
	}

	public ArrayList<Predmet> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(ArrayList<Predmet> predmeti) {
		this.predmeti = predmeti;
	}

	@Override
	public String toString() {
		return "Student [index=" + index + ", datum_upisa=" + datum_upisa + ", godina_studija=" + godina_studija
				+ ", status=" + status + ", prosek=" + prosek +  "]"; //TODO: pogledaj ovde sta se desi kada dodas 'predmeti' u ispis, pri prikazu studenata na predmet javi se greska
	}

	public boolean dodajPredmetStudentu(Predmet p) {

		for (Predmet temp : predmeti) {
			if (temp.getSifra().equalsIgnoreCase(p.getSifra()))
				return false;
		}

		predmeti.add(p);
		return true;
	}

	public boolean search_datum_upisa(String datum_upisa) {
		if (datum_upisa.equals(""))
			return true;

		return this.datum_upisa.equalsIgnoreCase(datum_upisa);
	}

	public boolean search_godina_stud(int godina_studija) {
		if (godina_studija==0)
			return true;

		return this.godina_studija == godina_studija;
	}

	public boolean search_status(Status status) {
		if (status==null)
			return true;

		return this.status.equals(status);
	}

	public boolean search_prosek(double prosek) {
		if (prosek==0)
			return true;

		return this.prosek==prosek;
	}

	public boolean search_index(String index) {
		if (index.equals(""))
			return true;

		return this.index.equalsIgnoreCase(index);
	}

	

	
	
	
	
	
	

}
