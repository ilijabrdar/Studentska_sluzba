package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import controller.ProfesorController;
import controller.StudentController;
import controller.SubjectController;

public class ToolBar extends JToolBar{
	//TODO VEZANI AKCELERATORI???
	private static final long serialVersionUID = -6297787221312734786L;
	
	private JButton btn1 = null, btn2 = null, btn3 = null, btn4 = null, btn5 = null, btn6 = null;
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
		
		btn1 = new JButton(new ImageIcon("imgs/plus.png"));
		btn1.setBackground(Color.WHITE);
		btn1.setBorderPainted(false);
		btn1.setToolTipText("Dodaj");
		btn1.setPreferredSize(new Dimension(35, 35));
		
		items.add(btn1);
		
		if(tab) {
			btn5 = new JButton(new ImageIcon("imgs/stud.png"));
			btn5.setBackground(Color.WHITE);
			btn5.setBorderPainted(false);
			btn5.setToolTipText("Dodaj studenta");
			btn5.setPreferredSize(new Dimension(35, 35));
			items.add(btn5);
			
			btn6 = new JButton(new ImageIcon("imgs/professor.png"));
			btn6.setBackground(Color.WHITE);
			btn6.setBorderPainted(false);
			btn6.setToolTipText("Dodaj profesora");
			btn6.setPreferredSize(new Dimension(34, 34));
			items.add(btn6);
		}
		
		btn2 = new JButton( new ImageIcon("imgs/edit1.png"));
		btn2.setBackground(Color.WHITE);
		btn2.setBorderPainted(false);
		btn2.setToolTipText("Izmeni");
		btn2.setPreferredSize(new Dimension(38, 38));
		items.add(btn2);
				
		btn3 = new JButton( new ImageIcon("imgs/delete.png"));
		btn3.setBackground(Color.WHITE);
		btn3.setBorderPainted(false);
		btn3.setToolTipText("Obriši");
		btn3.setPreferredSize(new Dimension(35, 35));
		items.add(btn3);
		
		search = new JTextField();
		search.setPreferredSize(new Dimension(200, 28));
		right.add(search);
		
		btn4 = new JButton( new ImageIcon("imgs/search.png"));
		btn4.setPreferredSize(new Dimension(35, 35));
		btn4.setBackground(Color.WHITE);
		btn4.setBorderPainted(false);
		btn4.setToolTipText("Pronađi");
		right.add(btn4);
		
		this.add(items, BorderLayout.WEST);
		this.add(right, BorderLayout.EAST);
	}
	
	public void setActions() { //OVDE SE DEFINISE KOJA AKCIJA CE SE POKRENUTI U ZAVISNOSTI KOJI TAB JE OTVOREN
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//ZAVISI KOJI JE TAB SELEKTOVAN
				if(TabbedPane.getInstance().isRunning("Predmeti")) {
					NewSubjectDialog sd = new  NewSubjectDialog(MainFrame.getInsance(), "Novi predmet", true, 0, 0);
					sd.setVisible(true);
				}
				else if (TabbedPane.getInstance().isRunning("Studenti")) {
					NewStudentDialog student_dialog = new NewStudentDialog(MainFrame.getInsance(), "Novi predmet", true);
					student_dialog.setVisible(true);
				}
				
				else if (TabbedPane.getInstance().isRunning("Profesori")) {
					NewProfesorDialog profesor_dialog = new NewProfesorDialog(MainFrame.getInsance(), "Novi profesor", true);
					profesor_dialog.setVisible(true);
				}
			}
		});
		
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row_subject = SubjectTable.getSubjectTable().getSelectedRow();
				int row_student = StudentsTable.getStudentsTable().getSelectedRow();
				int row_profesor = ProfesorTable.getProfesorTable().getSelectedRow();
				
				if(TabbedPane.getInstance().isRunning("Predmeti") &&  row_subject >= 0) {
					EditSubjectDialog ed = new EditSubjectDialog(MainFrame.getInsance(), "Izmena predmeta", true);
					ed.setVisible(true);
				}
				else if (TabbedPane.getInstance().isRunning("Predmeti") && row_subject == -1) {
					JOptionPane.showMessageDialog(MainFrame.getInsance(), 
							"Pre izmene selektujete predmet.", "Greška", JOptionPane.ERROR_MESSAGE);
				}
				
				else if (TabbedPane.getInstance().isRunning("Studenti") &&  row_student >= 0) {
					EditStudentDialog edit_student = new EditStudentDialog(MainFrame.getInsance(), "Izmena studenta", true);
					edit_student.setVisible(true);
				}
				else if (TabbedPane.getInstance().isRunning("Studenti") && row_student == -1) {
					JOptionPane.showMessageDialog(MainFrame.getInsance(), 
							"Pre izmene selektujete studenta.", "Greška", JOptionPane.ERROR_MESSAGE);
				}
				
				else if (TabbedPane.getInstance().isRunning("Profesori") &&  row_profesor >= 0) {
					EditProfesorDialog edit_profesor = new EditProfesorDialog(MainFrame.getInsance(), "Izmena profesora", true);
					edit_profesor.setVisible(true);
				}
				else if (TabbedPane.getInstance().isRunning("Profesori") && row_profesor == -1) {
					JOptionPane.showMessageDialog(MainFrame.getInsance(), 
							"Pre izmene selektujete profesora.", "Greška", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row_subject = SubjectTable.getSubjectTable().getSelectedRow();
				int row_student = StudentsTable.getStudentsTable().getSelectedRow();
				int row_prof = ProfesorTable.getProfesorTable().getSelectedRow();
				
				if(TabbedPane.getInstance().isRunning("Predmeti") && row_subject >= 0) {
					int code = JOptionPane.showConfirmDialog(MainFrame.getInsance(), "Da li ste sigurni da želite da obrišete predmet?",
							"Brisanje predmeta", JOptionPane.YES_NO_OPTION);
					if(code == JOptionPane.YES_OPTION) {
						SubjectController sc = SubjectController.getSubjectController();
						sc.removeSubject(row_subject);
					}	
				} 
				else if (TabbedPane.getInstance().isRunning("Predmeti") && row_subject == -1) { //row_subject=-1 ako nijedan red nije selektovan
					JOptionPane.showMessageDialog(MainFrame.getInsance(), 
							"Pre brisanja selektujete predmet.", "Greška", JOptionPane.ERROR_MESSAGE);
				}
				
				else if (TabbedPane.getInstance().isRunning("Studenti") && row_student >= 0) {
					int code = JOptionPane.showConfirmDialog(MainFrame.getInsance(), "Da li ste sigurni da želite da obrišete studenta?",
							"Brisanje studenta", JOptionPane.YES_NO_OPTION);
					if(code == JOptionPane.YES_OPTION) {
						StudentController sc = StudentController.getInstance();
						sc.izbrisiStudenta(row_student);
					}	
				}
				else if (TabbedPane.getInstance().isRunning("Studenti") && row_student == -1) { 
					JOptionPane.showMessageDialog(MainFrame.getInsance(), 
							"Pre brisanja selektujete studenta.", "Greška", JOptionPane.ERROR_MESSAGE);
				}
				else if (TabbedPane.getInstance().isRunning("Profesori") && row_prof >= 0) {
					int code = JOptionPane.showConfirmDialog(MainFrame.getInsance(), "Da li ste sigurni da želite da obrišete profesora?",
							"Brisanje profesora", JOptionPane.YES_NO_OPTION);
					if(code == JOptionPane.YES_OPTION) {
						ProfesorController pc = ProfesorController.getInstance();
						pc.izbrisiProfesora(row_prof);
					}	
				}
				else {
					JOptionPane.showMessageDialog(MainFrame.getInsance(), 
							"Pre brisanja selektujete profesora.", "Greška", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		btn4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(TabbedPane.getInstance().isRunning("Predmeti")) {
					SubjectController sc = SubjectController.getSubjectController();
					if(search.getText().trim().length() !=0) {
						sc.findSubject(search.getText());
					}
					else {
						sc.retriveTable();
					}
				}
			}
		});
	}
	
	public void setSubjectActions() {
		btn6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row_subject = SubjectTable.getSubjectTable().getSelectedRow();
				if(row_subject == -1)
					JOptionPane.showMessageDialog(MainFrame.getInsance(), 
							"Pre dodavanja profesora selektujete predmet.", "Greška", JOptionPane.ERROR_MESSAGE);
				else {
					ProfessorToSubject ps = new ProfessorToSubject(MainFrame.getInsance(), "Dodavanje profesora na predmet " + SubjectTable.getSubjectTable().getValueAt(row_subject, 1), true);
					ps.setVisible(true);
				}
			}
		});
		
		btn5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row_subject = SubjectTable.getSubjectTable().getSelectedRow();
				if(row_subject == -1)
					JOptionPane.showMessageDialog(MainFrame.getInsance(), 
							"Pre dodavanja studenta selektujete predmet.", "Greška", JOptionPane.ERROR_MESSAGE);
				else
					;//TODO: Dijalog za dodavanje studenta na predmet;
				
				
			}
		});
		}

	
	}
