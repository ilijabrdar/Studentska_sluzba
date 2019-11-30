package model;

import java.util.ArrayList;

public class BazaStudenata {
	
	private static BazaStudenata instance = new BazaStudenata();
	private ArrayList<String> columns;
	private ArrayList<Student> students;
	
	public static BazaStudenata getBazaStudenata() {
		return instance;
	}
	
	private BazaStudenata() {
		columns = new ArrayList<String>();
		students = new ArrayList<Student>();
		initColumns();
		initStudents();
	}
	

	private void initStudents() {
		Student s = new Student("Marko", "Markovic","", "adresa",063,"email","ra102-2019","", 12,Status.B ,9.1, null);
		students.add(s);
	}
	
	private void initColumns() {
		this.columns.add("Indeks");
		this.columns.add("Ime");
		this.columns.add("Prezime");
		this.columns.add("Godina studija");
		this.columns.add("Status");
		this.columns.add("Prosek");
	}

	public ArrayList<String> getColumns() {
		return columns;
	}

	public void setColumns(ArrayList<String> columns) {
		this.columns = columns;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
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
		default:
				return null;
		}
	}
	
	public Student getRow(int row) {
		return this.students.get(row);
	}
	
	public String getColumnName(int column) {
		return this.columns.get(column);
	}
	
	public void addStudent(Student s) {
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
