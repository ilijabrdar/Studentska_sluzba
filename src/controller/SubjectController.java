package controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import model.bazePodataka.BazaPredmeta;
import model.bazePodataka.BazaProfesora;
import model.bazePodataka.BazaStudenata;
import model.entiteti.Predmet;
import model.entiteti.Profesor;
import model.entiteti.Student;
import view.components.MainFrame;
import view.tables.SubjectTable;

public class SubjectController {
	
	private static SubjectController instance = null;
	private int selectedRow;
	
	public static SubjectController getSubjectController() {
		if(instance == null)
			instance = new SubjectController();
		return instance;
	}
	
	private SubjectController() {}
	
	public void addSubject(Predmet p, JDialog parent) {
		if(BazaPredmeta.getBazaPredmeta().addSubject(p))
			MainFrame.getInstance().updateTable();
		else
			JOptionPane.showMessageDialog(parent, "Predmet već postoji", "Greška", JOptionPane.ERROR_MESSAGE);
	}
	
	public void removeSubject() {
		Predmet subj = getSelectedSubjectByID();
		BazaPredmeta.getBazaPredmeta().removeSubject(subj);
		if(BazaPredmeta.getBazaPredmeta().getSubjects().isEmpty())
			retrieveTable();
		else
			MainFrame.getInstance().updateTable();
	}
	
	public Predmet getSelectedSubject() {
		selectedRow = SubjectTable.getSubjectTable().getSelectedRow();
		return BazaPredmeta.getBazaPredmeta().getRow(selectedRow); 
	}

	public Predmet getSelectedSubjectByID() {
		Predmet retVal = null;
		selectedRow = SubjectTable.getSubjectTable().getSelectedRow();
		String id = (String) SubjectTable.getSubjectTable().getValueAt(selectedRow, 0);
		for(Predmet p : BazaPredmeta.getBazaPredmeta().getSubjects()) //svejedno da li je ovo ili backup
			if(p.getSifra().equalsIgnoreCase(id))
				retVal = p;
		return retVal;
	}
	
	public void editSubject(Predmet subj, JDialog parent) {
		Predmet p = getSelectedSubjectByID();
		if(BazaPredmeta.getBazaPredmeta().editSubject(p.getSifra(), subj)) {
			MainFrame.getInstance().updateTable();
		}
		else
			JOptionPane.showMessageDialog(parent, "Predmet već postoji.", "Greška", JOptionPane.ERROR_MESSAGE);
		}
	
	public void findSubject(String search) throws Exception {
		String[] params = search.split("\\;");
		if(!search.matches("[ A-Za-zŠČĆŽĐšđčćž]+:[ A-Za-zŠČĆŽĐšđčćž0-9]+(;[ A-Za-zŠČĆŽĐšđčćž]+:[ A-Za-zŠČĆŽĐšđčćž0-9]+)*"))
			throw new Exception("Neispravno definisan kriterijum pretrage.");
		else {
			String sifra = "", naziv = "", semestar = "", godina = "";
			for (String p : params) {
				String[] subParams = p.split("\\:");
				switch (subParams[0].trim().toLowerCase()) {
					case "šifra":
						sifra = subParams[1].trim().toLowerCase();
						break;
					case "naziv":
						naziv = subParams[1].trim().toLowerCase();
						break;
					case "semestar":
						semestar = subParams[1].trim().toLowerCase();
						break;
					case "godina":
						godina = subParams[1].trim();
						break;
					default:
						throw new Exception("Neispravno uneto polje po kom se pretražuje.");
				}
			}
			BazaPredmeta bp = BazaPredmeta.getBazaPredmeta();
			bp.findSubject(sifra, naziv, semestar, godina);
			MainFrame.getInstance().updateTable();
		}
	}
	
	public void retrieveTable() {
		BazaPredmeta.getBazaPredmeta().swapTables();
		MainFrame.getInstance().updateTable();
	}
	
	public void saveToFile(String file) {
		PrintWriter out = null;
		ArrayList<Predmet> subj = BazaPredmeta.getBazaPredmeta().getBackup();
		try {
			out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
			for (Predmet p : subj) {
				String sifra = p.getSifra();
				String naziv = p.getNaziv();
				String semestar = p.getSemestar();
				String godina = p.getGodina();
				StringBuilder sb = new StringBuilder(500);
				sb.append(sifra);
				sb.append("|");
				sb.append(naziv);
				sb.append("|");
				sb.append(semestar);
				sb.append("|");
				sb.append(godina);
				out.println(sb.toString());
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

	
	public void saveProfToSubj(String file) {
		PrintWriter out = null;
		ArrayList<Predmet> subj = BazaPredmeta.getBazaPredmeta().getBackup();
		try {
			out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file)));
			for (Predmet p : subj) { 
				ArrayList<Profesor> prof = p.getProfs();
				StringBuilder sb = new StringBuilder(500);
				sb.append(p.getSifra());
				sb.append("|");
				for(Profesor pr : prof) {
					String ID = pr.getLicna();
					sb.append(ID);
					sb.append("|");
				}
				out.println(sb.toString());
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	
	public boolean addProfToSubj(String ID) {
		Predmet subj = getSelectedSubjectByID();
		Profesor p = BazaProfesora.getBazaProfesora().getProfesor(ID);
		if(p == null)
			return false;
		subj.addProfessor(p);
		return true;
	}

	public boolean addStudentToSubject(String indeks) {
		Predmet p = getSelectedSubject();
		Student s = BazaStudenata.getBazaStudenata().getStudentPrekoIndeksa(indeks);
		if (s == null) //provera  da li student sa datim indeksom postoji
			return false;

		boolean ret1 = p.addStudent(s);
		boolean ret2 = s.dodajPredmetStudentu(p);
		if (ret1==true && ret2==true)
			return true;
		else
			return false;
	}

	public void removeProfFromSubj(String ID) {
		for(Predmet p : BazaPredmeta.getBazaPredmeta().getSubjects()) {
			for(Profesor pr : p.getProfs()) {
				if(pr.getLicna().equalsIgnoreCase(ID)) {
					p.getProfs().remove(pr);
					break;
				}
			}
		}
	}

	//format : E214|ra-102-2017|
	public void saveStudentToSubject(String file) {
		PrintWriter out = null;
		ArrayList<Predmet> data = BazaPredmeta.getBazaPredmeta().getBackup();

		try {
			out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));

			for (Predmet p : data) {
				StringBuilder sb_output = new StringBuilder();
				String subID = p.getSifra();
				sb_output.append(subID + "|");

				for (Student s : p.getStudenti()) {
					sb_output.append(s.getIndex() + "|");
				}

				String output = sb_output.toString();
				out.println(output);

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

    public void removeStudFromSubj(String indeks) {
		for (Predmet p : BazaPredmeta.getBazaPredmeta().getSubjects()) {
			for (Student temp : p.getStudenti()) {
				if (temp.getIndex().equalsIgnoreCase(indeks)) {
					p.getStudenti().remove(temp);
					break; //resava problem ConcurrentModificationException
				}
			}
		}


    }
}
