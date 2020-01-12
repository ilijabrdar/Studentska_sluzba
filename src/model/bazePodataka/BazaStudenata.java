package model.bazePodataka;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import model.entiteti.Predmet;
import model.entiteti.Student;
import model.entiteti.Student.Status;
import view.components.MainFrame;

public class BazaStudenata {
	
	private static BazaStudenata instance = new BazaStudenata();
	private ArrayList<String> columns;
	private ArrayList<Student> students;
	private ArrayList<Student> database;

	
	public static BazaStudenata getBazaStudenata() {
		return instance;
	}
	
	private BazaStudenata() {
		columns = new ArrayList<String>();
		students = new ArrayList<Student>();
		database = new ArrayList<Student>();

		initColumns();
		initDatabase();
		for (Student s : database)
			students.add(s);

	}
	

	private void initDatabase() {
		BufferedReader reader = null;
		String[] data;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream("resources/listings/studentListing.txt"), "utf-8"));
			String line = null;
			while((line = reader.readLine()) != null) {
				data = line.split("\\|");
				String indeks = data[0].trim();
				String ime = data[1].trim();
				String prezime = data[2].trim();
				int trenutna_godina = Integer.parseInt(data[3].trim());
				Student.Status status = Status.valueOf(data[4].trim());
				double prosek = Double.parseDouble(data[5].trim());
				String datum_rodjenja = data[6].trim();
				String adresa = data[7].trim();
				String telefon = data[8].trim();
				String email = data[9].trim();
				String datum_upisa = data[10].trim();


				Student s = new Student(ime,prezime,datum_rodjenja,adresa,telefon,email,indeks,datum_upisa,trenutna_godina,
						status,prosek);
				database.add(s);
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
		this.columns.add("\u25B3\u25BD Broj indeksa");
		this.columns.add("Ime");
		this.columns.add("Prezime");
		this.columns.add("Godina studija");
		this.columns.add("Status");
		this.columns.add("Prosek");
		this.columns.add("Datum rođenja");
		this.columns.add("Adresa stanovanja");
		this.columns.add("Kontakt telefon");
		this.columns.add("E-mail adresa");
		this.columns.add("Datum upisa");
		this.columns.add("Predmeti");

	}

	public ArrayList<String> getColumns() {
		return columns;
	}

	//ovo je zbog buttonColumn
	public int getColumnCount() {
		return 10; //PAZI!
	}

	public void setColumns(ArrayList<String> columns) {
		this.columns = columns;
	}
	

	public ArrayList<Student> getDatabase() {
		return database;
	}

	public void setDatabase(ArrayList<Student> database) {
		this.database = database;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

	public void updateArrayList() {
		ArrayList<Student> pomocna = new ArrayList<Student>();

		for (int row = 0; row< MainFrame.getInstance().getStudent_table().getRowCount(); row++) {
			String indeks = (String) MainFrame.getInstance().getStudent_table().getValueAt(row,0);
			String ime = (String) MainFrame.getInstance().getStudent_table().getValueAt(row,1);
			String prz = (String) MainFrame.getInstance().getStudent_table().getValueAt(row,2);
			String godina_stud = (String) MainFrame.getInstance().getStudent_table().getValueAt(row,3);
			String status = (String) MainFrame.getInstance().getStudent_table().getValueAt(row,4);
			String prosek = (String) MainFrame.getInstance().getStudent_table().getValueAt(row,5);
			String datum_r = (String) MainFrame.getInstance().getStudent_table().getValueAt(row,6);
			String adresa = (String) MainFrame.getInstance().getStudent_table().getValueAt(row,7);
			String tel = (String) MainFrame.getInstance().getStudent_table().getValueAt(row,8);
			String email = (String) MainFrame.getInstance().getStudent_table().getValueAt(row,9);
			String datum_up = (String) MainFrame.getInstance().getStudent_table().getValueAt(row,10);

			int god = Integer.parseInt(godina_stud);
			Status st = Status.valueOf(status);

			double prosek_d;
			if (prosek.equalsIgnoreCase("/"))
				prosek_d = 0;
			else
				prosek_d = Double.parseDouble(prosek);

			Student s = new Student(ime,prz,datum_r,adresa,tel,email,indeks,datum_up,god, st,prosek_d);
			pomocna.add(s);
		}

		BazaStudenata.getBazaStudenata().setStudents(pomocna);

	}
	
	public String getValueAt(int row, int column) {
		Student student = students.get(row);
		switch(column) {
		case 0:
			return student.getIndex();
		case 1:
			return student.getIme();
		case 2:
			return student.getPrezime();
		case 3:
			return Integer.toString(student.getGodina_studija());
		case 4:
			return student.getStatus().toString();
		case 5:
			if (student.getProsek()==0)
				return "/";

			return Double.toString(student.getProsek());
		case 6:
			return student.getDatum().toString();
		case 7:
			return student.getAdresa();
		case 8:
			return student.getTelefon();
		case 9:
			return student.getEmail();
		case 10:
			return student.getDatum_upisa();
		default:
				return null;
		}
	}

	public Student getStudentPrekoIndeksa(String index) {
		for (Student s : database) {
			if (s.getIndex().equalsIgnoreCase(index))
				return s;
		}

		return null;

	}
	
	public Student getRow(int row) {
		return this.students.get(row);
	}
	
	public String getColumnName(int column) {
		return this.columns.get(column);
	}
	
	public boolean addStudent(Student s) {
		for (Student temp : database)  {
			if (temp.getIndex().equalsIgnoreCase(s.getIndex()))
				return false;
		}
		
		//students.add(s); TODO: ne smem da dodam u oba - zasto?
		database.add(s);
		return true;
	}
	
	public boolean editStudent(int index_tabele, Student s) {
		String indeks_starog = getValueAt(index_tabele,0);
		Student stari = getStudentPrekoIndeksa(indeks_starog);

		if (indeks_starog.equalsIgnoreCase(s.getIndex())) {//ostao im je nepromenjen index
			stari.setIme(s.getIme());
			stari.setPrezime(s.getPrezime());
			stari.setDatum(s.getDatum());
			stari.setDatum_upisa(s.getDatum_upisa());

			if (stari.getGodina_studija()!=s.getGodina_studija()) {
				for (Predmet p : BazaPredmeta.getBazaPredmeta().getSubjects()) {
					for (Student temp : p.getStudenti()) {
						if (temp.getIndex().equalsIgnoreCase(s.getIndex())) {
							p.getStudenti().remove(temp);
							break;
						}
					}
				}
			}

			stari.setGodina_studija(s.getGodina_studija());
			stari.setProsek(s.getProsek());
			stari.setStatus(s.getStatus());
			stari.setAdresa(s.getAdresa());
			stari.setTelefon(s.getTelefon());
			stari.setEmail(s.getEmail());

			return true;
		}
		else { //promenjen index pa moras proveriti da li taj index vec postoji
			for (Student temp : students) {
				if (temp.getIndex().equalsIgnoreCase(s.getIndex()))
					return false;
			}
			stari.setIme(s.getIme());
			stari.setPrezime(s.getPrezime());
			stari.setDatum(s.getDatum());
			stari.setDatum_upisa(s.getDatum_upisa());

			if (stari.getGodina_studija() != s.getGodina_studija()) {
				for (Predmet p : BazaPredmeta.getBazaPredmeta().getSubjects()) {
					for (Student temp : p.getStudenti()) {
						if (temp.getIndex().equalsIgnoreCase(stari.getIndex())) {
							p.getStudenti().remove(temp);
							break;
						}
					}
				}
			}

			stari.setGodina_studija(s.getGodina_studija());
			stari.setProsek(s.getProsek());
			stari.setStatus(s.getStatus());
			stari.setAdresa(s.getAdresa());
			stari.setTelefon(s.getTelefon());
			stari.setEmail(s.getEmail());
			stari.setIndex(s.getIndex());

			return true;
		}

	}
	
	public void removeStudent(Student s) {
		for (Student temp : database) {
			if (temp.getIndex().equalsIgnoreCase(s.getIndex())) {
				database.remove(temp);
				break;
			}
		}

		for (Student temp : students) {
			if (temp.getIndex().equalsIgnoreCase(s.getIndex())) {
				students.remove(temp);
				break;
			}
		}
		//students.remove(s);
	}

	public void undo_search() {
		students = database;
	}

	public void find(String ime, String prezime, String datum_rodjenja, String adresa, String telefon, String email, String index, String datum_upisa, int godina_stud, Status status, double prosek) {
		ArrayList<Student> search_result = new ArrayList<>();
		for (Student s : database) { //ides kroz database jer ako ides kroz students onda ne mozes da se vratis kriterijum iza

			if (s.search_ime(ime) && s.search_prezime(prezime) && s.search_datum_rodjenja(datum_rodjenja)
			&& s.search_adresa(adresa) && s.search_telefon(telefon) && s.search_email(email) &&
			s.search_index(index)  && s.search_godina_stud(godina_stud) &&
			s.search_status(status) && s.search_prosek(prosek) && s.search_datum_upisa(datum_upisa))
				search_result.add(s);

		}

		students=search_result;

	}
}
