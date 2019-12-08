package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BazaPredmeta {

	private static BazaPredmeta instance = null;
	private ArrayList<String> columns;
	private ArrayList<Predmet> subjects;
	private ArrayList<Profesor> profs;
	
	public static BazaPredmeta getBazaPredmeta() {
		if (instance == null)
			instance = new BazaPredmeta();
		return instance;
	}
	
	private BazaPredmeta() {
		columns = new ArrayList<String>();
		subjects = new ArrayList<Predmet>();
		profs = new ArrayList<Profesor>();
		initCols();
		initSubj();
	}
	
	private void initSubj() {
		BufferedReader reader = null;
		String[] data;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream("subjectListing.txt"), "utf-8"));
			String line = null;
			while((line = reader.readLine()) != null) {
				data = line.split("\\|");
				String sifra = data[0].trim();
				String naziv = data[1].trim();
				String semestar = data[2].trim();
				int godina = Integer.parseInt(data[3].trim());
				Predmet p = new Predmet(sifra, naziv, semestar, godina);
				subjects.add(p);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	private void initCols() {
		this.columns.add("Šifra");
		this.columns.add("Naziv");
		this.columns.add("Semestar");
		this.columns.add("Godina");
		this.columns.add("Profesori");
		this.columns.add("Studenti");
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
		for(int i = 0; i < subjects.size(); i++)
			if (subjects.get(i).getSifra().equalsIgnoreCase(subj.getSifra()))
				return false;
		return subjects.add(subj);
	}
	
	public void editSubject(int index, Predmet subj) {
		System.out.println(index);
		subjects.remove(index);
		subjects.add(index, subj);
		
	}
	
	public void removeSubject(Predmet subj) {
		subjects.remove(subj);
	}
}
