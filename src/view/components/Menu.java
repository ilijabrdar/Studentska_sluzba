package view.components;

import controller.ProfesorController;
import controller.StudentController;
import controller.SubjectController;
import model.entiteti.Profesor;
import view.dialogs.*;
import view.tables.ProfesorTable;
import view.tables.StudentsTable;
import view.tables.SubjectTable;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.*;

import javax.swing.*;

public class Menu extends JMenuBar {

	/**
	 *
	 */
	private static final long serialVersionUID = 2401343171737574518L;

	public Menu() {
		//mnemonik je skracenica da ti udje u taj odeljak menija
		//accelerator je da ti odradi do kraja

		this.setBackground(Color.WHITE);

		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);		//ALF+F
		JMenuItem file_new = new JMenuItem("New");
		file_new.addActionListener(new ActionListener() { //NOVA KLASA?
			@Override
			public void actionPerformed(ActionEvent e) {
				if(TabbedPane.getInstance().isRunning("Predmeti")) {
					NewSubjectDialog sd = new  NewSubjectDialog(MainFrame.getInstance(), "Novi predmet", true, 0, 0);
					sd.setVisible(true);
				}
				else if (TabbedPane.getInstance().isRunning("Studenti")) {
					NewStudentDialog student_dialog = new NewStudentDialog(MainFrame.getInstance(), "Novi predmet", true);
					student_dialog.setVisible(true);
				}

				else if (TabbedPane.getInstance().isRunning("Profesori")) {
					NewProfesorDialog profesor_dialog = new NewProfesorDialog(MainFrame.getInstance(), "Novi profesor", true);
					profesor_dialog.setVisible(true);
				}
			}
		});

		ImageIcon imageIcon_new = new ImageIcon(new ImageIcon("resources/imgs/069-plus.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		file_new.setIcon(imageIcon_new);
		file_new.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK)); //CTRL

		JMenuItem file_close = new JMenuItem("Close");
		ImageIcon imageIcon_close = new ImageIcon(new ImageIcon("resources/imgs/020-close.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)); //skaliranje ikonice
		file_close.setIcon(imageIcon_close);
		file_close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));//CTRL+C

		file_close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.getInstance().dispatchEvent(new WindowEvent(MainFrame.getInstance(),WindowEvent.WINDOW_CLOSING)); //trigeruje listener-a
			}
		});

		file.add(file_new);
		file.addSeparator();
		file.add(file_close);
		add(file);

		JMenu edit = new JMenu("Edit");
		edit.setMnemonic(KeyEvent.VK_E);

		JMenuItem edit_edit = new JMenuItem("Edit");
		ImageIcon imageIcon_edit = new ImageIcon(new ImageIcon("resources/imgs/062-pencil.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		edit_edit.setIcon(imageIcon_edit);
		edit_edit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));

		edit_edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row_subject = SubjectTable.getSubjectTable().getSelectedRow();
				int row_student = StudentsTable.getStudentsTable().getSelectedRow();
				int row_profesor = ProfesorTable.getProfesorTable().getSelectedRow();

				if(TabbedPane.getInstance().isRunning("Predmeti") &&  row_subject >= 0) {
					EditSubjectDialog ed = new EditSubjectDialog(MainFrame.getInstance(), "Izmena predmeta", true);
					ed.setVisible(true);
				}
				else if (TabbedPane.getInstance().isRunning("Predmeti") && row_subject == -1) {
					JOptionPane.showMessageDialog(MainFrame.getInstance(),
							"Pre izmene selektujete predmet.", "Greška", JOptionPane.ERROR_MESSAGE);
				}

				else if (TabbedPane.getInstance().isRunning("Studenti") &&  row_student >= 0) {
					EditStudentDialog edit_student = new EditStudentDialog(MainFrame.getInstance(), "Izmena studenta", true);
					edit_student.setVisible(true);
				}
				else if (TabbedPane.getInstance().isRunning("Studenti") && row_student == -1) {
					JOptionPane.showMessageDialog(MainFrame.getInstance(),
							"Pre izmene selektujete studenta.", "Greška", JOptionPane.ERROR_MESSAGE);
				}

				else if (TabbedPane.getInstance().isRunning("Profesori") &&  row_profesor >= 0) {
					EditProfesorDialog edit_profesor = new EditProfesorDialog(MainFrame.getInstance(), "Izmena profesora", true);
					edit_profesor.setVisible(true);
				}
				else if (TabbedPane.getInstance().isRunning("Profesori") && row_profesor == -1) {
					JOptionPane.showMessageDialog(MainFrame.getInstance(),
							"Pre izmene selektujete profesora.", "Greška", JOptionPane.ERROR_MESSAGE);
				}
			}
		});


		JMenuItem edit_delete = new JMenuItem("Delete");
		ImageIcon imageIcon_delete = new ImageIcon(new ImageIcon("resources/imgs/076-remove.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		edit_delete.setIcon(imageIcon_delete);
		edit_delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));

		edit_delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row_subject = SubjectTable.getSubjectTable().getSelectedRow();
				int row_student = StudentsTable.getStudentsTable().getSelectedRow();
				int row_prof = ProfesorTable.getProfesorTable().getSelectedRow();

				if(TabbedPane.getInstance().isRunning("Predmeti") && row_subject >= 0) {
					int code = JOptionPane.showConfirmDialog(MainFrame.getInstance(), "Da li ste sigurni da želite da obrišete predmet?",
							"Brisanje predmeta", JOptionPane.YES_NO_OPTION);
					if(code == JOptionPane.YES_OPTION) {
						SubjectController sc = SubjectController.getSubjectController();
						sc.removeSubject();
					}
				}
				else if (TabbedPane.getInstance().isRunning("Predmeti") && row_subject == -1) { //row_subject=-1 ako nijedan red nije selektovan
					JOptionPane.showMessageDialog(MainFrame.getInstance(),
							"Pre brisanja selektujete predmet.", "Greška", JOptionPane.ERROR_MESSAGE);
				}

				else if (TabbedPane.getInstance().isRunning("Studenti") && row_student >= 0) {
					int code = JOptionPane.showConfirmDialog(MainFrame.getInstance(), "Da li ste sigurni da želite da obrišete studenta?",
							"Brisanje studenta", JOptionPane.YES_NO_OPTION);
					if(code == JOptionPane.YES_OPTION) {
						StudentController sc = StudentController.getInstance();
						sc.izbrisiStudenta(row_student);
					}
				}
				else if (TabbedPane.getInstance().isRunning("Studenti") && row_student == -1) {
					JOptionPane.showMessageDialog(MainFrame.getInstance(),
							"Pre brisanja selektujete studenta.", "Greška", JOptionPane.ERROR_MESSAGE);
				}
				else if (TabbedPane.getInstance().isRunning("Profesori") && row_prof >= 0) {
					int code = JOptionPane.showConfirmDialog(MainFrame.getInstance(), "Da li ste sigurni da želite da obrišete profesora?",
							"Brisanje profesora", JOptionPane.YES_NO_OPTION);
					if(code == JOptionPane.YES_OPTION) {
						ProfesorController pc = ProfesorController.getInstance();
						Profesor p = ProfesorController.getInstance().getProfessor(row_prof);
						pc.izbrisiProfesora(row_prof);
						SubjectController.getSubjectController().removeProfFromSubj(p.getLicna());
					}
				}
				else {
					JOptionPane.showMessageDialog(MainFrame.getInstance(),
							"Pre brisanja selektujete profesora.", "Greška", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		edit.add(edit_edit);
		edit.addSeparator();
		edit.add(edit_delete);
		add(edit);

		JMenu help = new JMenu("Help");
		help.setMnemonic(KeyEvent.VK_H);

		JMenuItem help_help = new JMenuItem("Help");
		ImageIcon imageIcon_help = new ImageIcon(new ImageIcon("resources/imgs/050-info.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		help_help.setIcon(imageIcon_help);
		help_help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));

		help_help.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HelpDialog dialog = new HelpDialog(MainFrame.getInstance(),"Help",true);
				dialog.setVisible(true);
			}
		});

		JMenuItem help_about = new JMenuItem("About");
		ImageIcon imageIcon_about = new ImageIcon(new ImageIcon("resources/imgs/045-heart.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		help_about.setIcon(imageIcon_about);
		help_about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));

		help_about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AboutDialog dialog = new AboutDialog(MainFrame.getInstance(),"About",true);
				dialog.setVisible(true);
			}
		});

		help.add(help_help);
		help.addSeparator();
		help.add(help_about);
		add(help);


	}

}
