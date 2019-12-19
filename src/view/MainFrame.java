package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.AbstractTableModel;

import controller.StudentController;
import controller.SubjectController;
import model.BazaStudenata;

public class MainFrame extends JFrame{

	private static final long serialVersionUID = -8026416994513756565L;
	
	private static MainFrame instance = new MainFrame();
	
	private SubjectTable stable = null;
	private StudentsTable student_table = null;
	private ProfesorTable profesor_table = null;

	
	public static MainFrame getInsance() {
		return instance;
	}
	
	private MainFrame() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension scrSize = kit.getScreenSize();
		int height = scrSize.height;
		int width = scrSize.width;
		setSize(3 * width / 4, 3 * height / 4);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Studentska sluba");
		setIconImage(kit.getImage("imgs/ftn.png"));
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				JFrame frame = (JFrame) arg0.getComponent();
				int input = JOptionPane.showConfirmDialog(frame, "Da li želite da sačuvate izmene?", "Studentska služba", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				if (input == 0) {
				SubjectController sc = SubjectController.getSubjectController();
				sc.saveToFile("subjectListing.txt");
				sc.saveProfToSubj("ProfSubjListing.txt");

				StudentController student_controller = StudentController.getInstance();
				student_controller.saveToFile("studentListing.txt");
				}

			}
		});
		
		ToolBar toolbar = ToolBar.getInstance();
		this.add(toolbar, BorderLayout.NORTH);

		StatusBar status = new StatusBar();
		this.add(status, BorderLayout.SOUTH);
		
		Menu menu = new Menu();
		this.setJMenuBar(menu);
		
		stable = SubjectTable.getSubjectTable();
		stable.setAutoCreateRowSorter(true);
		student_table = StudentsTable.getStudentsTable();
		profesor_table = ProfesorTable.getProfesorTable();

		student_table.setAutoCreateRowSorter(true);
		profesor_table.setAutoCreateRowSorter(true);

		JScrollPane spane = new JScrollPane(stable);
		JScrollPane student_pane = new JScrollPane(student_table);
		JScrollPane profesor_pane = new JScrollPane(profesor_table);
		
		ImageIcon imageIcon_student = new ImageIcon(new ImageIcon("imgs/student+icon-1320184412790928936.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		ImageIcon imageIcon_subject = new ImageIcon(new ImageIcon("imgs/211-2112519_subject-icon-png-learn-english-icon-png.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		ImageIcon imageIcon_profesor = new ImageIcon(new ImageIcon("imgs/sociat_avatar_2.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));

		
		TabbedPane tabs = TabbedPane.getInstance();
		tabs.addTab("Studenti",imageIcon_student, student_pane);
		tabs.addTab("Profesori", imageIcon_profesor, profesor_pane);
		tabs.addTab("Predmeti",imageIcon_subject, spane);
		tabs.setTabPlacement(TabbedPane.TOP);
		this.add(tabs);
	}
	
	public void updateTable() {
		AbstractTableModel model;
		if (TabbedPane.getInstance().isRunning("Predmeti"))
			model = (AbstractModelSubjects) stable.getModel();
		else if (TabbedPane.getInstance().isRunning("Studenti"))
			model = (AbstractModelStudents) student_table.getModel();
		else //onda se Profesori runnuju, mora ovako zbog inicijalizacije promenljive model
			model = (AbstractModelProfesor) profesor_table.getModel();
		
		
		model.fireTableDataChanged();
		validate();
	}
	
}
