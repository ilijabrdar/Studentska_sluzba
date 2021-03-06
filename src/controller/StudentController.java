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
	
	public boolean dodajStudenta(Student s) {
		boolean ret = BazaStudenata.getBazaStudenata().addStudent(s);
		undo_search(); //undo_search se koristi za povratak tabele u pocetno stanje ukoliko je uradjena pretraga, listu students prevezujemo na database kako bi se ukljucio i najnoviji rezultat

		return ret;
	}
	
	public void izbrisiStudenta(String indeks_studenta) {

		BazaStudenata.getBazaStudenata().updateArrayList();
		Student s = BazaStudenata.getBazaStudenata().getStudentPrekoIndeksa(indeks_studenta);
		BazaStudenata.getBazaStudenata().removeStudent(s);

		if (BazaStudenata.getBazaStudenata().getStudents().isEmpty()) //ukoliko lista studenata postane prazna, mozda zbog brisanja objekta u pretrazi, vraca se njeno stanje u pocetno, pre pretrage
			undo_search();
		else
			MainFrame.getInstance().updateTable();
	}
	
	public boolean izmeniStudenta(int rowSelectedIndex, Student novi) {
		if (rowSelectedIndex < 0)
			return false;
		
		boolean ret = BazaStudenata.getBazaStudenata().editStudent(rowSelectedIndex, novi);
		if (ret) // ukoliko je edit bio uspesan tabela studenata se vraca u pocetno stanje sa izmenjenim studentom, radi se update tabele students
			undo_search();
		return ret;
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
	public void findStudent(String text) throws Exception { //mora biti ispostovan format unosa

		//ideja pretrage: postavim sve kriterijume pretrage na neke default vrednosti i onda proveravam koji kriterijum pretrage mi je poslat
		//ako je poslato npr. ime, onda polje kriterijuma ime postavim na prosledjeno ime. Ostala polja ostanu defaultna.
		//Posto su mi ostala polja defaultna, to znaci da se svaki student ispunjava kriterijume tih default polja i stoga metoda search_prosek, search_prezime... vraca
		//true. U metodi search_ime, proverava se da li je kriterijum imena default, posto nije onda ce se uporediti ime trenutnog studenta sa prosledjenom vrednoscu imena.
		// Ako se poklapa onda se dodaje u novu listu (search_list) i kada se prodje kroz sve studente i utvrdi ko ima to ime, onda se lista students koja sadrzi trenutni
		// prikaz tabele izjednacava sa listom search_result.

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
		double prosek = 1; //mora da se promeni u 1 jer prosek moze biti 0 ako je student prva godina

		
		for (String data : splits) {
			String []search = data.split(":");
			if (search[0].equalsIgnoreCase("ime"))
				ime = search[1].trim();

			else if (search[0].equalsIgnoreCase("prezime"))
				prezime = search[1].trim();

			else if (search[0].equalsIgnoreCase("datum rođenja"))
				datum_rodjenja = search[1].trim();

			else if (search[0].equalsIgnoreCase("adresa stanovanja"))
				adresa = search[1].trim();

			else if (search[0].equalsIgnoreCase("kontakt telefon"))
				telefon = search[1].trim();

			else if (search[0].equalsIgnoreCase("e-mail adresa"))
				email = search[1].trim();

			else if (search[0].equalsIgnoreCase("broj indeksa"))
				index = search[1].trim();

			else if (search[0].equalsIgnoreCase("datum upisa"))
				datum_upisa = search[1].trim();

			else if (search[0].equalsIgnoreCase("godina studija"))
				godina_stud = Integer.parseInt(search[1].trim());

			else if (search[0].equalsIgnoreCase("status"))
				status = Student.Status.valueOf(search[1].trim());

			else if (search[0].equalsIgnoreCase("prosek")) {
				if (search[1].trim().equalsIgnoreCase("/"))
					prosek = 0;
				else
					prosek = Double.parseDouble(search[1].trim());
			}
			else
				throw new Exception("Neispravno uneto polje po kom se pretražuje.");
		}

		BazaStudenata.getBazaStudenata().find(ime,prezime,datum_rodjenja,adresa,telefon,email,index,datum_upisa,godina_stud,status,prosek);
		MainFrame.getInstance().updateTable();

	}

	public void undo_search() {
		BazaStudenata.getBazaStudenata().undo_search();
		MainFrame.getInstance().updateTable();
	}
}
