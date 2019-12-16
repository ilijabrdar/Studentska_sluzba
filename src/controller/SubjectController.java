package controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import model.BazaPredmeta;
import model.BazaProfesora;
import model.Predmet;
import model.Profesor;
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
	
	public void addSubject(Predmet p, JDialog parent) {
		if(BazaPredmeta.getBazaPredmeta().addSubject(p))
			MainFrame.getInsance().updateTable();
		else
			JOptionPane.showMessageDialog(parent, "Predmet već postoji", "Greška", JOptionPane.ERROR_MESSAGE);
	}
	
	public void removeSubject(int row) {
		selectedRow = row;
		Predmet subj = BazaPredmeta.getBazaPredmeta().getRow(selectedRow);
		BazaPredmeta.getBazaPredmeta().removeSubject(subj);
		if(BazaPredmeta.getBazaPredmeta().getSubjects().isEmpty())
			retriveTable();
		else
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
	
	public void findSubject(String search) {
		String[] params = search.split("\\;");
		String sifra = "", naziv = "", semestar = "", godina = "";
		for(String p : params) {
			String[] subParams = p.split("\\:");
			switch(subParams[0].trim().toLowerCase()) {
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
			}
		}
		BazaPredmeta bp = BazaPredmeta.getBazaPredmeta();
		bp.findSubject(sifra, naziv, semestar, godina);
		MainFrame.getInsance().updateTable();
	}
	
	public void retriveTable() {
		BazaPredmeta.getBazaPredmeta().swapTables();
		MainFrame.getInsance().updateTable();
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
		Predmet subj = getSelectedSubject();
		Profesor p = BazaProfesora.getBazaProfesora().getProfesor(ID);
		if(p == null)
			return false;
		subj.addProfessor(p);
		return true;
			
	}
}
