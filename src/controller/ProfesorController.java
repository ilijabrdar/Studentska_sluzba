package controller;
import model.bazePodataka.BazaProfesora;
import model.entiteti.Predmet;
import model.entiteti.Profesor;
import view.components.MainFrame;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class ProfesorController {
	
private static ProfesorController instance = new ProfesorController();
	
	public static ProfesorController getInstance() {
		return instance;
	}
	
	private ProfesorController() {}
	
	public boolean dodajProfesora(Profesor p) {
		boolean ret = BazaProfesora.getBazaProfesora().addProfesor(p);
		retrieveTable(); //posle search-a da se vrati, a i posle dodavanja da se zamene tabele

		return ret;
	}
	
	public void izbrisiProfesora(int rowSelectedIndex) {
		if (rowSelectedIndex < 0 )
			return;

		BazaProfesora.getBazaProfesora().updateArrayList();
		Profesor p = BazaProfesora.getBazaProfesora().getRow(rowSelectedIndex);
		BazaProfesora.getBazaProfesora().removeProfesor(p);

		if(BazaProfesora.getBazaProfesora().getProfesori().isEmpty())
			retrieveTable();
		else
			MainFrame.getInstance().updateTable();

	}

	public Profesor getProfessor(int rowSelectedIndex) {
		BazaProfesora.getBazaProfesora().updateArrayList();
		Profesor p = BazaProfesora.getBazaProfesora().getRow(rowSelectedIndex);
		return p;
	}
	
	public boolean izmeniProfesora (int rowSelectedIndex, Profesor novi) {
		if (rowSelectedIndex < 0)
			return false;
		
		boolean ret = BazaProfesora.getBazaProfesora().editProfesor(rowSelectedIndex, novi);
		if (ret)
			retrieveTable();

		return ret;
	}

	public void findProfessor(String search) throws Exception {
		String[] params = search.split("\\;");
		if(!search.matches("[ A-Za-zŠČĆŽĐšđčćž]+:[ A-Za-zŠČĆŽĐšđčćž0-9.]+(;[ A-Za-zŠČĆŽĐšđčćž]+:[ A-Za-zŠČĆŽĐšđčćž0-9.]+)*"))
			throw new Exception("Neispravno definisan kriterijum pretrage.");
		else {
			String ime = "", prz = "", adresa = "";
			String kanc = "", licna = "", titula = "", zvanje = "";
			String email = "", datum = "", tel = "";
			for (String p : params) {
				String[] subParams = p.split("\\:");
				switch (subParams[0].trim().toLowerCase()) {
					case "ime":
						ime = subParams[1].trim().toLowerCase();
						break;
					case "prezime":
						prz = subParams[1].trim().toLowerCase();
						break;
					case "broj lične karte":
						licna = subParams[1].trim().toLowerCase();
						break;
					case "titula":
						titula = subParams[1].trim().toLowerCase();
						break;
					case "zvanje":
						zvanje = subParams[1].trim().toLowerCase();
						break;
					case "adresa kancelarije":
						kanc = subParams[1].trim().toLowerCase();
						break;
					case "e-mail adresa":
						email = subParams[1].trim().toLowerCase();
						break;
					case "datum rođenja":
						datum = subParams[1].trim().toLowerCase();
						break;
					case "kontakt telefon":
						tel = subParams[1].trim().toLowerCase();
						break;
					case "adresa stanovanja":
						adresa = subParams[1].trim().toLowerCase();
						break;
					default:
						throw new Exception("Neispravno uneto polje po kom se pretražuje.");
				}
			}
			BazaProfesora bp = BazaProfesora.getBazaProfesora();
			bp.findProfessor(ime, prz, licna, titula, zvanje, kanc, email, datum, tel, adresa);
			MainFrame.getInstance().updateTable();
		}
	}

	public void saveToFile(String file) {
		PrintWriter out = null;
		ArrayList<Profesor> data = BazaProfesora.getBazaProfesora().getDatabase();
		data = BazaProfesora.getBazaProfesora().getProfesori();
		try {
			out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
			for (Profesor p : data) {

				String ime = p.getIme();
				String prezime = p.getPrezime();
				String licna = p.getLicna();
				String titula = p.getTitula();
				String zvanje = p.getZvanje();
				String kanc = p.getKanc();
				String adresa = p.getAdresa();
				String telefon = p.getTelefon();
				String email = p.getEmail();
				String datum_r = p.getDatum();

				String input;
				input = ime + "|" + prezime + "|" + licna + "|" + titula + "|" + zvanje +"|" + kanc + "|"
						+ email + "|" + datum_r + "|" + telefon + "|" + adresa;
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

	public void retrieveTable () {
		BazaProfesora.getBazaProfesora().swapTables();
		MainFrame.getInstance().updateTable();
	}

	public void removeSubjectFromProfessor(String ID) {
		BazaProfesora bp = BazaProfesora.getBazaProfesora();

		for(int i = 0; i < bp.getProfesori().size(); i++) {
			Profesor p = bp.getProfesori().get(i);
			for(int j = 0; j < p.getPredmeti().size(); j++) {
				Predmet pr = p.getPredmeti().get(j);
				if(pr.getSifra().equals(ID))
					p.getPredmeti().remove(pr);
			}
		}
	}

}
