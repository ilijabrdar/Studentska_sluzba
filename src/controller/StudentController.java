package controller;

import model.BazaStudenata;
import model.Student;
import view.MainFrame;

public class StudentController {
	
	private static StudentController instance = new StudentController();
	
	public static StudentController getInstance() {
		return instance;
	}
	
	private StudentController() {}
	
	public void dodajStudenta(Student s) {
		BazaStudenata.getBazaStudenata().addStudent(s);
		MainFrame.getInsance().updateTable();
	}
	
	public void izbrisiStudenta(int rowSelectedIndex) {
		if (rowSelectedIndex < 0 )
			return;
		
		Student s = BazaStudenata.getBazaStudenata().getRow(rowSelectedIndex);
		BazaStudenata.getBazaStudenata().removeStudent(s);
		MainFrame.getInsance().updateTable();
	}
	
	public void izmeniStudenta(int rowSelectedIndex) {
		if (rowSelectedIndex < 0)
			return;
		
		Student s = BazaStudenata.getBazaStudenata().getRow(rowSelectedIndex);
		//TODO: zavrsi izmenu studenata
		MainFrame.getInsance().updateTable();
	}
	
	
}
