package model.bazePodataka;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
			reader = new BufferedReader(new InputStreamReader(new FileInputStream("studentListing.txt"), "utf-8"));
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
		this.columns.add("Indeks");
		this.columns.add("Ime");
		this.columns.add("Prezime");
		this.columns.add("Godina studija");
		this.columns.add("Status");
		this.columns.add("Prosek");
		this.columns.add("Datum rodjenja");
		this.columns.add("Adresa");
		this.columns.add("Telefon");
		this.columns.add("Email");
		this.columns.add("Datum upisa");

	}

	public ArrayList<String> getColumns() {
		return columns;
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
			double prosek_d = Double.parseDouble(prosek);

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
		for (Student s : students) {
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
	
	public void addStudent(Student s) {
		for (Student temp : students)  {
			if (temp.getIndex().equalsIgnoreCase(s.getIndex()))
				return;
		}
		
		students.add(s);
	}
	
	public void editStudent(int index, Student s) {
		students.remove(index);
		students.add(index, s);
	}
	
	public void removeStudent(Student s) {
		students.remove(s);
	}

}
