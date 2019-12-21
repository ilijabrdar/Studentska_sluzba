package controller;

import model.BazaProfesora;
import model.Profesor;
import view.MainFrame;

public class ProfesorController {
	
private static ProfesorController instance = new ProfesorController();
	
	public static ProfesorController getInstance() {
		return instance;
	}
	
	private ProfesorController() {}
	
	public void dodajProfesora(Profesor p) { 
		BazaProfesora.getBazaProfesora().addProfesor(p);
		MainFrame.getInsance().updateTable();
	}
	
	public void izbrisiProfesora(int rowSelectedIndex) {
		if (rowSelectedIndex < 0 )
			return;

		BazaProfesora.getBazaProfesora().updateArrayList();
		Profesor p = BazaProfesora.getBazaProfesora().getRow(rowSelectedIndex);
		BazaProfesora.getBazaProfesora().removeProfesor(p);
		MainFrame.getInsance().updateTable();
	}
	
	public void izmeniProfesora (int rowSelectedIndex, Profesor novi) {
		if (rowSelectedIndex < 0)
			return;
		
		BazaProfesora.getBazaProfesora().editProfesor(rowSelectedIndex, novi);
		MainFrame.getInsance().updateTable();
	}

	public void findProfessor(String search) {
		String[] params = search.split("\\;");
		String ime = "", prz = "", dat = "", adresa = "";
		String kanc = "", licna = "", titula = "", zvanje = "";
		//TODO Zavrsiti pretragu profesora
		//TODO Dodati pretragu po ostalim poljima?
		for(String p : params) {
			String[] subParams = p.split("\\:");
			switch(subParams[0].trim().toLowerCase()) {
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
				case "datum rodjenja":
					dat = subParams[1].trim();
			}


		}
	}

}
