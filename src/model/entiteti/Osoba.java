package model.entiteti;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Osoba {
	protected String ime;
	protected String prezime;
	protected LocalDate datum;
	protected String adresa;
	protected String telefon;
	protected String email;

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");

	public Osoba() {
	}

	public Osoba(String ime, String prezime, String datum, String adresa, String telefon, String email) {
		this.ime = ime;
		this.prezime = prezime;
		this.datum = LocalDate.parse(datum,formatter);
		this.adresa = adresa;
		this.telefon = telefon;
		this.email = email;
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
		return datum.format(formatter);
	}

	public void setDatum(String datum) {
		this.datum = LocalDate.parse(datum,formatter);
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean search_email(String email) {
		if (email.equals(""))
			return true;

		return this.email.equalsIgnoreCase(email);
	}

	public boolean search_ime(String ime) { //ako je ime defaultna vrednost postavljena u metodi findStudent studentControllera, onda znaci da se ne pretrazuje po imenu i nebitno je koje je ime studenta
		if (ime.equals(""))
			return true;

		return this.ime.equalsIgnoreCase(ime);
	}

	public boolean search_prezime(String prezime) {
		if (prezime.equals(""))
			return true;

		return this.prezime.equalsIgnoreCase(prezime);
	}

	public boolean search_datum_rodjenja(String datum) {
		if (datum.equals(""))
			return true;

		return this.datum.format(formatter).equalsIgnoreCase(datum);
	}

	public boolean search_adresa(String adresa) {
		if (adresa.equals(""))
			return true;

		return this.adresa.equalsIgnoreCase(adresa);
	}

	public boolean search_telefon(String telefon) {
		if (telefon.equals(""))
			return true;

		return this.telefon.equalsIgnoreCase(telefon);
	}


	
	@Override
	public String toString() {
		return  ime + " " + prezime;
	}
}
