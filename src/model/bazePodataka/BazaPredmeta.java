package model.bazePodataka;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import model.entiteti.Predmet;
import model.entiteti.Profesor;
import model.entiteti.Student;

public class BazaPredmeta {

	private static BazaPredmeta instance = null;
	private ArrayList<String> columns;
	private ArrayList<Predmet> subjects;
	private ArrayList<Predmet> backup;
	
	public static BazaPredmeta getBazaPredmeta() {
		if (instance == null)
			instance = new BazaPredmeta();
		return instance;
	}
	
	private BazaPredmeta() {
		columns = new ArrayList<String>();
		subjects = new ArrayList<Predmet>();
		backup = new ArrayList<Predmet>();
		initCols();
		initBackup();
		pullProfessors();
		pullStudents();
		for(Predmet p : backup)
			subjects.add(p);
	}

	//vuce predmete iz fajla da inicijalnizuje backup listu
	private void initBackup() {
		BufferedReader reader = null;
		String[] data;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream("resources/listings/subjectListing.txt"), "utf-8"));
			String line = null;
			while((line = reader.readLine()) != null) {
				
				data = line.split("\\|");
				String sifra = data[0].trim();
				String naziv = data[1].trim();
				String semestar = data[2].trim();
				String godina = data[3].trim();
				Predmet p = new Predmet(sifra, naziv, semestar, godina);
				backup.add(p);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
	/***
	 * Pulls professors from the file 'ProfSubjListing'.
	 * Rows in the file have the following form:
	 * 	string1|string2|string3|...|stringN
	 * 	where
	 * 	string1 represents a subject ID
	 * 	string2...N represent professors' IDs
	 */
	public void pullProfessors() {
		BufferedReader in = null;
		String[] data;
		try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream("resources/listings/ProfSubjListing.txt")));
			
			String line = null;
			while((line = in.readLine()) != null) {
				data = line.split("\\|");
				String subID = data[0];
				Predmet subj = getSubject(subID);  //nemoguce da vrati null, jer to je obezbedjeno kod doavanja profe na predmet
				for(int i = 1; i < data.length; i++) {
					Profesor prof = BazaProfesora.getBazaProfesora().getProfesor(data[i]);
					subj.addProfessor(prof);
					prof.getPredmeti().add(subj);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void initCols() {
		this.columns.add("\u25B3\u25BD Šifra");
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

	public ArrayList<Predmet> getBackup() {
		return backup;
	}

	public void setBackup(ArrayList<Predmet> backup) {
		this.backup = backup;
	}

	public void setSubjects(ArrayList<Predmet> subjects) {
		this.subjects = subjects;
	}
	//vraca vrednost iz promenljive liste, nikad se skoro ne koristi
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
			return subj.getGodina();
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
	
	public int getColumnCount() {
		return 5; //PAZI!
	}

	public boolean addSubject(Predmet subj) {
		if (getSubject(subj.getSifra()) != null)
			return false;
		return backup.add(subj);
	}
	//vrati false ako postoji neki predmet koji ima istu sifru, a nije predmet koji se trenutno menja
	public boolean editSubject(String ID, Predmet subj) {
		Predmet s = getSubject(subj.getSifra()); //nova verzija predmeta
		Predmet p = getSubject(ID); //stara verzija predmeta
		if(s != null && !p.getSifra().equalsIgnoreCase(subj.getSifra()))
			return false;

		//Ako se promeni godina predmeta treba izbrisati sve studente
		if(!p.getGodina().equalsIgnoreCase(subj.getGodina()))
			p.getStudenti().clear();

		p.setSifra(subj.getSifra());
		p.setNaziv(subj.getNaziv());
		p.setSemestar(subj.getSemestar());
		p.setGodina(subj.getGodina());
		return true;
	}
	//ako nije naveden neki podatak popuni ta polja sa tekucim podacima, da bi kritetijum bio zadovoljen
	public void findSubject(String sifra, String naziv, String semestar, String godina) {
		ArrayList<Predmet> search = new ArrayList<Predmet>();
		for (int i = 0; i < backup.size(); i++) {
			Predmet p = backup.get(i);
			String sif = sifra;
			String sem = semestar;
			String naz = naziv;
			String god = godina;
			if (sif.equals(""))
				sif = p.getSifra().toLowerCase();
			if (naz.equals(""))
				naz = p.getNaziv().toLowerCase();
			if (sem.equals(""))
				sem = p.getSemestar().toLowerCase();
			if (god.equals(""))
				god = p.getGodina();
			
			if(p.getSifra().toLowerCase().contains(sif) && p.getNaziv().toLowerCase().contains(naz)
				&& p.getSemestar().toLowerCase().contains(sem) && p.getGodina().contains(god)) 
				search.add(p);
		}
		subjects = search;
	}
	
	public void swapTables() {
		subjects = backup;
	}
	
	public void removeSubject(Predmet subj) {
		subjects.remove(subj);
		backup.remove(subj);
	}
	
	public Predmet getSubject(String ID) {
		for(int i = 0; i < backup.size(); i++) {
			if(backup.get(i).getSifra().equalsIgnoreCase(ID)) {
				return backup.get(i);
			}
		}
		return null;
	}

	//format : E214|ra-102-2017|
	private void pullStudents() {
		BufferedReader reader = null;
		String [] data;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream("resources/listings/StudentSubjListing.txt"), "utf-8"));
			String line = null;
			while((line = reader.readLine()) != null) {
				data = line.split("\\|");

				String IDSubj = data[0];
				Predmet p = getSubject(IDSubj);

				for (int i = 1; i<data.length; i++) {
					String StudID = data[i];
					if (StudID=="")
						continue;

					Student s = BazaStudenata.getBazaStudenata().getStudentPrekoIndeksa(StudID);
					p.addStudent(s);
				}


			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
}
