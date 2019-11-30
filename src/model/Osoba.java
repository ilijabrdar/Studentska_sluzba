package model;

public class Osoba {
	protected String ime;
	protected String prezime;
	protected String datum;
	protected String adresa;
	protected int telefon;
	protected String email;
	
	public Osoba(String ime, String prezime, String datum, String adresa, int telefon, String email) {
		this.ime = ime;
		this.prezime = prezime;
		this.datum = datum;
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
	
	@Override
	public String toString() {
		return  ime + " " + prezime;
	}
}
