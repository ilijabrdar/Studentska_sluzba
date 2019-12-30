package model.bazePodataka;

import model.entiteti.Profesor;
import view.components.MainFrame;

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
			reader = new BufferedReader(new InputStreamReader(new FileInputStream("resources/listings/profesorListing.txt"), "utf-8"));
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

		for (int row = 0; row< MainFrame.getInstance().getProfesor_table().getRowCount(); row++) {
			String ime = (String) MainFrame.getInstance().getProfesor_table().getValueAt(row,0);
			String prz = (String) MainFrame.getInstance().getProfesor_table().getValueAt(row,1);
			String licna = (String) MainFrame.getInstance().getProfesor_table().getValueAt(row,2);
			String titula = (String) MainFrame.getInstance().getProfesor_table().getValueAt(row,3);
			String zvanje = (String) MainFrame.getInstance().getProfesor_table().getValueAt(row,4);
			String kanc = (String) MainFrame.getInstance().getProfesor_table().getValueAt(row,5);
			String email = (String) MainFrame.getInstance().getProfesor_table().getValueAt(row,6);
			String datum_r = (String) MainFrame.getInstance().getProfesor_table().getValueAt(row,7);
			String tel = (String) MainFrame.getInstance().getProfesor_table().getValueAt(row,8);
			String adresa = (String) MainFrame.getInstance().getProfesor_table().getValueAt(row,9);


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
	
	public boolean addProfesor(Profesor p) {
		for (Profesor temp : database) {
			if (temp.getLicna().equalsIgnoreCase(p.getLicna()))
				return false;
		}

		//TODO: ne smem dodati u oba, zasto?
		//profesori.add(p);
		database.add(p);
		return true;
	}
	
	public boolean editProfesor(int index_tabele, Profesor p) {
		String licna_starog = getValueAt(index_tabele,2);
		Profesor stari = getProfesor(licna_starog);

		if (licna_starog.equalsIgnoreCase(p.getLicna())) {
			stari.setIme(p.getIme());
			stari.setPrezime(p.getPrezime());
			stari.setTitula(p.getTitula());
			stari.setTelefon(p.getTelefon());
			stari.setAdresa(p.getAdresa());
			stari.setKanc(p.getKanc());
			stari.setDatum(p.getDatum());
			stari.setZvanje(p.getZvanje());
			stari.setEmail(p.getEmail());

			return true;
		}
		else {
			for (Profesor temp : profesori) {
				if (temp.getLicna().equalsIgnoreCase(p.getLicna()))
					return false;
			}

			stari.setIme(p.getIme());
			stari.setPrezime(p.getPrezime());
			stari.setTitula(p.getTitula());
			stari.setTelefon(p.getTelefon());
			stari.setAdresa(p.getAdresa());
			stari.setKanc(p.getKanc());
			stari.setDatum(p.getDatum());
			stari.setZvanje(p.getZvanje());
			stari.setEmail(p.getEmail());
			stari.setLicna(p.getLicna());

			return true;
		}

	}
	
	public void removeProfesor(Profesor p) {
		profesori.remove(p);
		database.remove(p);
	}
	
	public Profesor getProfesor(String ID) {
		for(Profesor p : profesori)
			if(p.getLicna().equalsIgnoreCase(ID))
				return p;
		return null;
	}

	public void findProfessor(String imep, String przp, String licnap, String titulap, String zvanjep, String datump) {
		ArrayList<Profesor> search = new ArrayList<>();
		for(Profesor p : database) {
			String ime = imep;
			String prz = przp;
			String licna = licnap;
			String titula = titulap;
			String datum = datump;

			if(ime.equals("")) ime = p.getIme().toLowerCase();
			else if(prz.equals("")) prz = p.getPrezime().toLowerCase();
			else if(licna.equals("")) licna = p.getLicna().toLowerCase();
			else if(titula.equals("")) titula = p.getTitula().toLowerCase();
			else if(datum.equals("")) datum = p.getDatum().toLowerCase();

			if(p.getIme().toLowerCase().contains(ime) && p.getPrezime().toLowerCase().contains(przp) &&
				p.getLicna().toLowerCase().contains(licna) && p.getTitula().toLowerCase().contains(titula) &&
				p.getDatum().toLowerCase().contains(datum))
					search.add(p);
		}
		profesori = search;
	}

	public void swapTables() { profesori = database; }

	public Profesor findProfByID(String ID) {
		Profesor prof = null;
		for(Profesor p : profesori) {
			if (p.getLicna().equalsIgnoreCase(ID))
				prof = p;
		}
		return prof;
	}

}
