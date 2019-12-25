package model;

import view.MainFrame;

import java.io.*;
import java.util.ArrayList;

public class BazaProfesora {
	
	private static BazaProfesora instance = new BazaProfesora();
	private ArrayList<String> columns;
	private ArrayList<Profesor> profesori;
	private ArrayList<Profesor> database;

	public static BazaProfesora getBazaProfesora() {
		return instance;
	}
	
	private BazaProfesora() {
		columns = new ArrayList<String>();
		profesori = new ArrayList<Profesor>();
		database = new ArrayList<Profesor>();
		initColumns();
		initDatabase();
		for (Profesor p : database)
			profesori.add(p);
	}
	

	private void initDatabase() {
		BufferedReader reader = null;
		String[] data;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream("profesorListing.txt"), "utf-8"));
			String line = null;
			while((line = reader.readLine()) != null) {
				data = line.split("\\|");

				String ime = data[0].trim();
				String prezime = data[1].trim();
				String broj_licne = data[2].trim();
				String titula = data[3].trim();
				String zvanje = data[4].trim();
				String kancelarija = data[5].trim();
				String email = data[6].trim();
				String datum_rodjenja = data[7].trim();
				String telefon = data[8].trim();
				String adresa = data[9].trim();


				Profesor p = new Profesor(ime,prezime,datum_rodjenja,adresa,telefon,email,kancelarija,broj_licne,titula,zvanje);
				database.add(p);
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

	private void initColumns() {
		this.columns.add("Ime");
		this.columns.add("Prezime");
		this.columns.add("Broj licne karte");
		this.columns.add("Titula");
		this.columns.add("Zvanje");
		this.columns.add("Kancelarija");
		this.columns.add("Email");
		this.columns.add("Datum rodjenja");
		this.columns.add("Telefon");
		this.columns.add("Adresa");
	}

	public ArrayList<String> getColumns() {
		return columns;
	}

	public void setColumns(ArrayList<String> columns) {
		this.columns = columns;
	}

	public ArrayList<Profesor> getDatabase() {
		return database;
	}

	public void setDatabase(ArrayList<Profesor> database) {
		this.database = database;
	}

	public void updateArrayList() {
		ArrayList<Profesor> pomocna = new ArrayList<Profesor>();

		for (int row = 0; row< MainFrame.getInsance().getProfesor_table().getRowCount(); row++) {
			String ime = (String) MainFrame.getInsance().getProfesor_table().getValueAt(row,0);
			String prz = (String) MainFrame.getInsance().getProfesor_table().getValueAt(row,1);
			String licna = (String) MainFrame.getInsance().getProfesor_table().getValueAt(row,2);
			String titula = (String) MainFrame.getInsance().getProfesor_table().getValueAt(row,3);
			String zvanje = (String) MainFrame.getInsance().getProfesor_table().getValueAt(row,4);
			String kanc = (String) MainFrame.getInsance().getProfesor_table().getValueAt(row,5);
			String email = (String) MainFrame.getInsance().getProfesor_table().getValueAt(row,6);
			String datum_r = (String) MainFrame.getInsance().getProfesor_table().getValueAt(row,7);
			String tel = (String) MainFrame.getInsance().getProfesor_table().getValueAt(row,8);
			String adresa = (String) MainFrame.getInsance().getProfesor_table().getValueAt(row,9);


			Profesor p = new Profesor(ime,prz,datum_r,adresa,tel,email,kanc,licna,titula,zvanje);
			pomocna.add(p);
		}

		BazaProfesora.getBazaProfesora().setProfesori(pomocna);

	}

	public ArrayList<Profesor> getProfesori() {
		return profesori;
	}

	public void setProfesori(ArrayList<Profesor> profesori) {
		this.profesori = profesori;
	}
	
	public String getValueAt(int row, int column) {
		Profesor p = profesori.get(row);
		switch(column) {
		case 0:
			return p.getIme();
		case 1:
			return p.getPrezime();
		case 2:
			return p.getLicna();
		case 3:
			return p.getTitula();
		case 4:
			return p.getZvanje();
		case 5:
			return p.getKanc();
		case 6:
			return p.getEmail();
		case 7:
			return p.getDatum();
		case 8:
			return p.getTelefon();
		case 9:
			return p.getAdresa();
		default:
				return null;
		}
	}
	
	public Profesor getRow(int row) {
		return this.profesori.get(row);
	}
	
	public String getColumnName(int column) {
		return this.columns.get(column);
	}
	
	public void addProfesor(Profesor p) {
		for (Profesor temp : profesori) {
			if (temp.getLicna().equalsIgnoreCase(p.getLicna()))
				return;
		}

		profesori.add(p);
	}
	
	public void editProfesor(int index, Profesor p) {
		profesori.remove(index);
		profesori.add(index, p);
	}
	
	public void removeProfesor(Profesor p) {
		profesori.remove(p);
	}
	
	public Profesor getProfesor(String ID) {
		for(Profesor p : profesori)
			if(p.getLicna().equalsIgnoreCase(ID))
				return p;
		return null;
	}

	public void findProfessor(String... args) {
		ArrayList<Profesor> serach = new ArrayList<>();

	}
}
