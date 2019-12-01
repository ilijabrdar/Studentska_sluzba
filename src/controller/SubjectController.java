package controller;

import javax.security.auth.Subject;
import javax.swing.JOptionPane;

import model.BazaPredmeta;
import model.Predmet;
import view.MainFrame;
import view.SubjectTable;

public class SubjectController {
	
	private static SubjectController instance = null;
	private int selectedRow;
	
	public static SubjectController getSubjectController() {
		if(instance == null)
			instance = new SubjectController();
		return instance;
	}
	
	private SubjectController() {}
	
	public void addSubject(Predmet p) {
		BazaPredmeta.getBazaPredmeta().addSubject(p);
		MainFrame.getInsance().updateTable();
	}
	
	public void removeSubject() {
		Predmet subj = BazaPredmeta.getBazaPredmeta().getRow(selectedRow);
		BazaPredmeta.getBazaPredmeta().removeSubject(subj);
		MainFrame.getInsance().updateTable();
	}
	
	public Predmet getSelectedSubject() {
		selectedRow = SubjectTable.getSubjectTable().getSelectedRow();
		return BazaPredmeta.getBazaPredmeta().getRow(selectedRow); 
	}
	
	public void editSubject(Predmet subj) {
		BazaPredmeta.getBazaPredmeta().editSubject(selectedRow, subj);
		MainFrame.getInsance().updateTable();
	}
}
