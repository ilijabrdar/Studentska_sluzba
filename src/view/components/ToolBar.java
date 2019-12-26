package view.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controller.ProfesorController;
import controller.StudentController;
import controller.SubjectController;
import model.entiteti.Profesor;
import view.dialogs.*;
import view.dialogs.ProfessorToSubject;
import view.dialogs.StudentNaPredmet;
import view.tables.ProfesorTable;
import view.tables.StudentsTable;
import view.tables.SubjectTable;

public class ToolBar extends JToolBar{
	//TODO VEZANI AKCELERATORI???
	private static final long serialVersionUID = -6297787221312734786L;
	
	private JButton btn_dodaj = null, btn_edit = null, btn_izbrisi = null, btn_search = null, btn_dodaj_studenta = null, btn_dodaj_profesora = null;
	private JTextField search = null;
	
	private static ToolBar instance = null;
	
	public static ToolBar getInstance() {
		if(instance == null)
			instance = new ToolBar();
		return instance;
	}
	
	private ToolBar() {
		super(SwingConstants.HORIZONTAL);
		
		this.setBackground(Color.white);
				
		this.setLayout(new BorderLayout());
		
		updateToolBar(false);
		
		setFloatable(false);
	}
	
	public void updateToolBar(boolean tab) {
		this.removeAll();
		this.revalidate();
		this.repaint();
		setContent(tab);
		setActions();
		if(tab) setSubjectActions();
	}
	
	public void setContent(boolean tab) {
		
		JPanel items = new JPanel();
		JPanel right = new JPanel();
		items.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 4));
		right.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 4));
		
		items.setBackground(Color.white);
		right.setBackground(Color.white);
		
		btn_dodaj = new JButton(new ImageIcon("imgs/plus.png"));
		btn_dodaj.setBackground(Color.WHITE);
		btn_dodaj.setBorderPainted(false);
		btn_dodaj.setToolTipText("Dodaj");
		btn_dodaj.setPreferredSize(new Dimension(35, 35));
		
		items.add(btn_dodaj);
		
		if(tab) {
			btn_dodaj_studenta = new JButton(new ImageIcon("imgs/stud.png"));
			btn_dodaj_studenta.setBackground(Color.WHITE);
			btn_dodaj_studenta.setBorderPainted(false);
			btn_dodaj_studenta.setToolTipText("Dodaj studenta");
			btn_dodaj_studenta.setPreferredSize(new Dimension(35, 35));
			items.add(btn_dodaj_studenta);
			
			btn_dodaj_profesora = new JButton(new ImageIcon("imgs/professor.png"));
			btn_dodaj_profesora.setBackground(Color.WHITE);
			btn_dodaj_profesora.setBorderPainted(false);
			btn_dodaj_profesora.setToolTipText("Dodaj profesora");
			btn_dodaj_profesora.setPreferredSize(new Dimension(34, 34));
			items.add(btn_dodaj_profesora);
		}
		
		btn_edit = new JButton( new ImageIcon("imgs/edit1.png"));
		btn_edit.setBackground(Color.WHITE);
		btn_edit.setBorderPainted(false);
		btn_edit.setToolTipText("Izmeni");
		btn_edit.setPreferredSize(new Dimension(38, 38));
		items.add(btn_edit);
				
		btn_izbrisi = new JButton( new ImageIcon("imgs/delete.png"));
		btn_izbrisi.setBackground(Color.WHITE);
		btn_izbrisi.setBorderPainted(false);
		btn_izbrisi.setToolTipText("Obriši");
		btn_izbrisi.setPreferredSize(new Dimension(35, 35));
		items.add(btn_izbrisi);
		
		search = new JTextField();
		search.setPreferredSize(new Dimension(200, 28));
		right.add(search);
		
		btn_search = new JButton( new ImageIcon("imgs/search.png"));
		btn_search.setPreferredSize(new Dimension(35, 35));
		btn_search.setBackground(Color.WHITE);
		btn_search.setBorderPainted(false);
		btn_search.setToolTipText("Pronađi");
		right.add(btn_search);
		
		this.add(items, BorderLayout.WEST);
		this.add(right, BorderLayout.EAST);
	}
	
	public void setActions() { //OVDE SE DEFINISE KOJA AKCIJA CE SE POKRENUTI U ZAVISNOSTI KOJI TAB JE OTVOREN
		btn_dodaj.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			    //(Component) e.getSource()
				//ZAVISI KOJI JE TAB SELEKTOVAN
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
		
		btn_edit.addActionListener(new ActionListener() {
			
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
		
		btn_izbrisi.addActionListener(new ActionListener() {
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
		
		btn_search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(TabbedPane.getInstance().isRunning("Predmeti")) {
					SubjectController sc = SubjectController.getSubjectController();
					if(search.getText().trim().length() !=0)
						try {
							sc.findSubject(search.getText());
						} catch (Exception err) {
							JOptionPane.showMessageDialog(MainFrame.getInstance(), "Kriterijum pretrage je neispravno definisan.","Pretraga predmeta", JOptionPane.ERROR_MESSAGE);
						}
					else
						sc.retrieveTable();
				}
				else if(TabbedPane.getInstance().isRunning("Profesori")) {
					ProfesorController pc = ProfesorController.getInstance();
					if(search.getText().trim().length() != 0)
						try {
							pc.findProfessor(search.getText());
						} catch (Exception err) {
							JOptionPane.showMessageDialog(MainFrame.getInstance(), "Kriterijum pretrage je neispravno definisan.","Pretraga profesora", JOptionPane.ERROR_MESSAGE);
						}
					else
						pc.retrieveTable();
				}
				else if (TabbedPane.getInstance().isRunning("Studenti")) {
					StudentController sc = StudentController.getInstance();
					//sc.findStudent(search.getText());
					if (search.getText().trim().length()==0) {
						MainFrame.getInstance().getStudent_table().rowSorter.setRowFilter(null);
					}
					else {
						MainFrame.getInstance().getStudent_table().rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + search.getText())); //dinamicko sortiranje!
						MainFrame.getInstance().updateTable();
					}
				}
			}
		});
	}
	
	public void setSubjectActions() {
		btn_dodaj_profesora.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row_subject = SubjectTable.getSubjectTable().getSelectedRow();
				if(row_subject == -1)
					JOptionPane.showMessageDialog(MainFrame.getInstance(),
							"Pre dodavanja profesora selektujete predmet.", "Greška", JOptionPane.ERROR_MESSAGE);
				else {
					ProfessorToSubject ps = new ProfessorToSubject(MainFrame.getInstance(), "Dodavanje profesora na predmet " + SubjectTable.getSubjectTable().getValueAt(row_subject, 1), true);
					ps.setVisible(true);
				}
			}
		});
		
		btn_dodaj_studenta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row_subject = SubjectTable.getSubjectTable().getSelectedRow();
				if(row_subject == -1)
					JOptionPane.showMessageDialog(MainFrame.getInstance(),
							"Pre dodavanja studenta selektujete predmet.", "Greška", JOptionPane.ERROR_MESSAGE);
				else {
					StudentNaPredmet sp = new StudentNaPredmet(MainFrame.getInstance(),"Dodavanje studenta na predmet",true);
					sp.setVisible(true);
				}

				
			}
		});
		}

	
	}
