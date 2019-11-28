package controller;

import javax.security.auth.Subject;

import model.BazaPredmeta;
import model.Predmet;
import view.MainFrame;

public class SubjectController {
	
	private static SubjectController instance = null;
	
	public static SubjectController getSubjectController() {
		if(instance == null)
			instance = new SubjectController();
		return instance;
	}
	
	private SubjectController() {}
	
	public boolean addSubject(Predmet p) {
		boolean retVal = false;
		retVal = BazaPredmeta.getBazaPredmeta().addSubject(p);
		MainFrame.getInsance().updateTable();
		return retVal;
	}
	
	public boolean removeSubject(int r) {
		if(r < 0)
			return false;
		Predmet subj = BazaPredmeta.getBazaPredmeta().getRow(r);
		return BazaPredmeta.getBazaPredmeta().removeSubject(subj);
	}
	
	//IZMENI PREDMET
}
