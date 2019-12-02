package model;

import java.util.ArrayList;

public class BazaProfesora {
	
	private static BazaProfesora instance = new BazaProfesora();
	private ArrayList<String> columns;
	private ArrayList<Profesor> profesori;
	
	public static BazaProfesora getBazaProfesora() {
		return instance;
	}
	
	private BazaProfesora() {
		columns = new ArrayList<String>();
		profesori = new ArrayList<Profesor>();
		initColumns();
		initProfesori();
	}
	
	
	private void initProfesori() {
		Profesor p = new Profesor("Darko", "Darkovic","", "adresa",063,"email","kancelarija", 105,"Doktor","Prof.",null );
		profesori.add(p);
	}
	
	private void initColumns() {
		this.columns.add("Ime");
		this.columns.add("Prezime");
		this.columns.add("Titula");
		this.columns.add("Zvanje");
		this.columns.add("Kancelarija");
		this.columns.add("Email");
	}

	public ArrayList<String> getColumns() {
		return columns;
	}

	public void setColumns(ArrayList<String> columns) {
		this.columns = columns;
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
			return p.getTitula();
		case 3:
			return p.getZvanje();
		case 4:
			return p.getKanc();
		case 5:
			return p.getEmail();
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
		profesori.add(p);
	}
	
	public void editProfesor(int index, Profesor p) {
		profesori.remove(index);
		profesori.add(index, p);
	}
	
	public void removeProfesor(Profesor p) {
		profesori.remove(p);
	}
}
