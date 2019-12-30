package model.entiteti;

import java.util.ArrayList;
import java.util.Arrays;

public class Profesor extends Osoba {
	public enum Zvanje {Dipl(1),Mast(2),Spec(3),Dr(4),Struk(5),SpecStruk(6);

		int zvanje;
		Zvanje(int i) {
			this.zvanje = i;
		}

		private String [] opis = {"Dipl.", "Mast.","Spec.", "Dr", "Struk.","Spec. Struk."};

		@Override
		public String toString() {
			return opis[this.ordinal()];
		}
	};

	public enum Titula {Asistent(1),Saradnik(2),Docent(3),Redovniprofesor(4),Vanredniprofesor(5),Dekan(6);
		int titula;
		Titula(int i) {
			this.titula= i;
		}
		private String [] opis = {"Asistent", "Saradnik","Docent", "Redovni profesor", "Vanredni profesor","Dekan"};

		@Override
		public String toString() {
			return opis[this.ordinal()];
		}
	}

	private String kanc;
	private String licna;
	private Titula titula;
	private Zvanje zvanje;
	private ArrayList<Predmet> predmeti = new ArrayList<Predmet>();
	
	public Profesor(String i, String p, String d, String a, String t, String e, String k, String l, Titula tit, Zvanje z) {
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
		return titula.toString();
	}

	public void setTitula(String titula) {
		titula = titula.replaceAll("\\s+","");
		this.titula = Titula.valueOf(titula);
	}

	public String getZvanje() {
		return zvanje.toString();
	}

	public void setZvanje(String zvanje) {
		zvanje = zvanje.replaceAll("\\s+","");
		zvanje = zvanje.replace(".","");
		this.zvanje = Zvanje.valueOf(zvanje);
	}

	public ArrayList<Predmet> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(ArrayList<Predmet> predmeti) {
		this.predmeti = predmeti;
	}	
}
