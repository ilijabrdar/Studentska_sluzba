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

}
