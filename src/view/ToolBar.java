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

import controller.StudentController;
import controller.SubjectController;

public class ToolBar extends JToolBar{
	//TODO VEZANI AKCELERATORI???
	private static final long serialVersionUID = -6297787221312734786L;
	
	private JButton btn1 = null, btn2 = null, btn3 = null, btn4 = null;
	
	public ToolBar() {
		super(SwingConstants.HORIZONTAL);
		
		this.setBackground(Color.white);
				
		this.setLayout(new BorderLayout());
		
		setContent();
		setActions();
		
		setFloatable(false);
	}
	
	public void setContent() {
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
		
		this.addSeparator();
		
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
		
		JTextField search = new JTextField();
		search.setPreferredSize(new Dimension(200, 28));
		this.add(search);
		
		btn4 = new JButton( new ImageIcon("imgs/search.png"));
		btn4.setPreferredSize(new Dimension(35, 35));
		btn4.setBackground(Color.WHITE);
		btn4.setBorderPainted(false);
		btn4.setToolTipText("Pronađi");
		right.add(search);
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
					// TODO: dodavanje profesora
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
					// TODO: izmena studenta
				}
				else if (TabbedPane.getInstance().isRunning("Studenti") && row_student == -1) {
					JOptionPane.showMessageDialog(MainFrame.getInsance(), 
							"Pre izmene selektujete studenta.", "Greška", JOptionPane.ERROR_MESSAGE);
				}
				
				else if (TabbedPane.getInstance().isRunning("Profesori") &&  row_profesor >= 0) {
					// TODO: izmena studenta
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
				
				if(TabbedPane.getInstance().isRunning("Predmeti") && row_subject >= 0) {
					int code = JOptionPane.showConfirmDialog(MainFrame.getInsance(), "Da li ste sigurni da želite da obrišete predmet?",
							"Brisanje predmeta", JOptionPane.YES_NO_OPTION);
					if(code == JOptionPane.YES_OPTION) {
						SubjectController sc = SubjectController.getSubjectController();
						sc.removeSubject();
						sc.saveToFile("subjectListing.txt");
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
				
				
			}
		});
		}

	
	}
