package model;

import java.util.ArrayList;

public class BazaPredmeta {

	private static BazaPredmeta instance = null;
	private ArrayList<String> columns;
	private ArrayList<Predmet> subjects;
	
	public static BazaPredmeta getBazaPredmeta() {
		if (instance == null)
			instance = new BazaPredmeta();
		return instance;
	}
	
	private BazaPredmeta() {
		columns = new ArrayList<String>();
		subjects = new ArrayList<Predmet>();
		initCols();
		initSubj();
	}
	
	private void initSubj() {
		//SVAKI PUT KADA SE POKRENE POVUCI CE IZ FAJLA PODATKE
		Profesor p = new Profesor("X", "Y", "", "", 1, "", "", 1, "", "", null);
		subjects.add(new Predmet("as12", "nesto", "letnji", 1, p));
	}
	
	private void initCols() {
		this.columns.add("Šifra");
		this.columns.add("Naziv");
		this.columns.add("Semestar");
		this.columns.add("Godina");
		this.columns.add("Profesor");
	}

	public ArrayList<String> getColumns() {
		return columns;
	}

	public void setColumns(ArrayList<String> columns) {
		this.columns = columns;
	}

	public ArrayList<Predmet> getSubjects() {
		return subjects;
	}

	public void setSubjects(ArrayList<Predmet> subjects) {
		this.subjects = subjects;
	}
	
	public String getValueAt(int row, int col) {
		Predmet subj = subjects.get(row);
		switch(col) {
		case 0:
			return subj.getSifra();
		case 1:
			return subj.getNaziv();
		case 2:
			return subj.getSemestar();
		case 3:
			return Integer.toString(subj.getGodina());
		case 4:
			//return subj.getProf().toString();
			//POPRAVITI KAD DOBIJES KLASU BAZA PROFESORA
			return "";
		default:
			return null;
		}
	}
	
	public Predmet getRow(int row) {
		return subjects.get(row);
	}
	
	public String getColumnName(int col) {
		return this.columns.get(col);
	}
	
	public boolean addSubject(Predmet subj) {
		if(subjects.contains(subj))
			return false;
		else
			return subjects.add(subj);
	}
	
	public boolean removeSubject(Predmet subj) {
		return subjects.remove(subj);
	}
}
