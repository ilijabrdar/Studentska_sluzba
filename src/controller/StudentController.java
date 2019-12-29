package controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import model.bazePodataka.BazaStudenata;
import model.entiteti.Student;
import view.components.MainFrame;

public class StudentController {
	
	private static StudentController instance = new StudentController();
	
	public static StudentController getInstance() {
		return instance;
	}
	
	private StudentController() {}
	
	public boolean dodajStudenta(Student s) {//TODO: nekada doda dva ista
		boolean ret = BazaStudenata.getBazaStudenata().addStudent(s);
		undo_search();

		return ret;
	}
	
	public void izbrisiStudenta(int rowSelectedIndex) {
		if (rowSelectedIndex < 0 )
			return;

		BazaStudenata.getBazaStudenata().updateArrayList();
		Student s = BazaStudenata.getBazaStudenata().getRow(rowSelectedIndex);
		BazaStudenata.getBazaStudenata().removeStudent(s);
		MainFrame.getInstance().updateTable();
	}
	
	public void izmeniStudenta(int rowSelectedIndex, Student novi) {
		if (rowSelectedIndex < 0)
			return;
		
		BazaStudenata.getBazaStudenata().editStudent(rowSelectedIndex, novi);
		undo_search();
	}
	
	public void saveToFile(String file) {
		PrintWriter out = null;
		ArrayList<Student> data = BazaStudenata.getBazaStudenata().getDatabase();

		try {
			out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
			for (Student s : data) {
				String index = s.getIndex();
				String ime = s.getIme();
				String prezime = s.getPrezime();
				String godina_s = Integer.toString(s.getGodina_studija());
				String status = s.getStatus().toString();
				String prosek = Double.toString(s.getProsek());
				String datum_r = s.getDatum();
				String adresa = s.getAdresa();
				String telefon = s.getTelefon();
				String email = s.getEmail();
				String datum_u = s.getDatum_upisa();
				
				String input;
				input = index + "|" + ime + "|" + prezime + "|" + godina_s + "|" + status + "|" + prosek + "|" + datum_r + "|" + adresa + "|" + telefon + "|" + email + "|" + datum_u;
				out.println(input);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	//ime:Marko;prezime:Marković;indeks:sw-1-2019
	public void findStudent(String text) {
		String []splits = text.split(";");
		String ime = "";
		String prezime = "";
		String datum_rodjenja = "";
		String adresa = "";
		String telefon = "";
		String email = "";

		String index = "";
		String datum_upisa = "";
		int godina_stud = 0;
		Student.Status status = null;
		double prosek = 0;



		for (String data : splits) {
			String []search = data.split(":");
			if (search[0].equalsIgnoreCase("ime")) {
				ime = search[1].trim();
			}
			else if (search[0].equalsIgnoreCase("prezime")) {
				prezime = search[1].trim();
			}
			else if (search[0].equalsIgnoreCase("datum rodjenja")) {//TODO: date?
				datum_rodjenja = search[1].trim();
			}
			else if (search[0].equalsIgnoreCase("adresa")) {
				adresa = search[1].trim();
			}
			else if (search[0].equalsIgnoreCase("telefon")) {
				telefon = search[1].trim();
			}
			else if (search[0].equalsIgnoreCase("email")) {
				email = search[1].trim();
			}
			else if (search[0].equalsIgnoreCase("index")) {
				index = search[1].trim();
			}
			else if (search[0].equalsIgnoreCase("datum upisa")) {//TODO: date?
				datum_upisa = search[1].trim();
			}
			else if (search[0].equalsIgnoreCase("godina studija")) {
				godina_stud = Integer.parseInt(search[1].trim());
			}
			else if (search[0].equalsIgnoreCase("status")) {
				status = Student.Status.valueOf(search[1].trim());
			}
			else if (search[0].equalsIgnoreCase("prosek")) {
				prosek = Double.parseDouble(search[1].trim());
			}
		}

		BazaStudenata.getBazaStudenata().find(ime,prezime,datum_rodjenja,adresa,telefon,email,index,datum_upisa,godina_stud,status,prosek);
		MainFrame.getInstance().updateTable();



	}

	public void undo_search() {
		BazaStudenata.getBazaStudenata().undo_search();
		MainFrame.getInstance().updateTable();
	}
}
