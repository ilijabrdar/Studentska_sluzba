package model.entiteti;

public class Osoba {
	protected String ime;
	protected String prezime;
	protected String datum; //TODO: Tip Date?
	protected String adresa;
	protected String telefon;
	protected String email;
	
	public Osoba(String ime, String prezime, String datum, String adresa, String telefon, String email) {
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

	public boolean search_ime(String ime) {
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

		return this.datum.equalsIgnoreCase(datum);
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
