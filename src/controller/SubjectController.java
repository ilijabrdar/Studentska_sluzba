package controller;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.security.auth.Subject;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import model.BazaPredmeta;
import model.Predmet;
import view.MainFrame;
import view.NewSubjectDialog;
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
	
	public void addSubject(Predmet p, JDialog parent) {
		if(BazaPredmeta.getBazaPredmeta().addSubject(p))
			MainFrame.getInsance().updateTable();
		else
			JOptionPane.showMessageDialog(parent, "Predmet već postoji", "Greška", JOptionPane.ERROR_MESSAGE);
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
	
	public void saveToFile(String file) {
		PrintWriter out = null;
		ArrayList<Predmet> subj = BazaPredmeta.getBazaPredmeta().getSubjects();
		try {
			out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
			for (Predmet p : subj) {
				String sifra = p.getSifra();
				String naziv = p.getNaziv();
				String semestar = p.getSemestar();
				int godina = p.getGodina();
				StringBuilder sb = new StringBuilder(500);
				sb.append(sifra);
				sb.append("|");
				sb.append(naziv);
				sb.append("|");
				sb.append(semestar);
				sb.append("|");
				sb.append(String.valueOf(godina));
				out.println(sb.toString());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}
