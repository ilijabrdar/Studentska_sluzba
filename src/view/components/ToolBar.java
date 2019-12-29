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
import view.listeners.*;
import view.tables.ProfesorTable;
import view.tables.StudentsTable;
import view.tables.SubjectTable;

public class ToolBar extends JToolBar{

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
		
		btn_dodaj = new JButton(new ImageIcon("resources/imgs/plus.png"));
		btn_dodaj.setBackground(Color.WHITE);
		btn_dodaj.setBorderPainted(false);
		btn_dodaj.setToolTipText("Dodaj");
		btn_dodaj.setPreferredSize(new Dimension(35, 35));
		btn_dodaj.addMouseListener(new ButtonMouseListener());
		
		items.add(btn_dodaj);
		
		if(tab) {
			btn_dodaj_studenta = new JButton(new ImageIcon("resources/imgs/stud.png"));
			btn_dodaj_studenta.setBackground(Color.WHITE);
			btn_dodaj_studenta.setBorderPainted(false);
			btn_dodaj_studenta.setToolTipText("Dodaj studenta");
			btn_dodaj_studenta.setPreferredSize(new Dimension(35, 35));
			btn_dodaj_studenta.addMouseListener(new ButtonMouseListener());
			items.add(btn_dodaj_studenta);
			
			btn_dodaj_profesora = new JButton(new ImageIcon("resources/imgs/professor.png"));
			btn_dodaj_profesora.setBackground(Color.WHITE);
			btn_dodaj_profesora.setBorderPainted(false);
			btn_dodaj_profesora.setToolTipText("Dodaj profesora");
			btn_dodaj_profesora.setPreferredSize(new Dimension(34, 34));
			btn_dodaj_profesora.addMouseListener(new ButtonMouseListener());
			items.add(btn_dodaj_profesora);
		}
		
		btn_edit = new JButton( new ImageIcon("resources/imgs/edit1.png"));
		btn_edit.setBackground(Color.WHITE);
		btn_edit.setBorderPainted(false);
		btn_edit.setToolTipText("Izmeni");
		btn_edit.setPreferredSize(new Dimension(38, 38));
		btn_edit.addMouseListener(new ButtonMouseListener());
		items.add(btn_edit);
				
		btn_izbrisi = new JButton( new ImageIcon("resources/imgs/delete.png"));
		btn_izbrisi.setBackground(Color.WHITE);
		btn_izbrisi.setBorderPainted(false);
		btn_izbrisi.setToolTipText("Obriši");
		btn_izbrisi.setPreferredSize(new Dimension(35, 35));
		btn_izbrisi.addMouseListener(new ButtonMouseListener());
		items.add(btn_izbrisi);
		
		search = new JTextField();
		search.setPreferredSize(new Dimension(200, 28));
		right.add(search);
		
		btn_search = new JButton( new ImageIcon("resources/imgs/search.png"));
		btn_search.setPreferredSize(new Dimension(35, 35));
		btn_search.setBackground(Color.WHITE);
		btn_search.setBorderPainted(false);
		btn_search.setToolTipText("Pronađi");
		btn_search.addMouseListener(new ButtonMouseListener());
		right.add(btn_search);
		
		this.add(items, BorderLayout.WEST);
		this.add(right, BorderLayout.EAST);
	}
	
	public void setActions() { //OVDE SE DEFINISE KOJA AKCIJA CE SE POKRENUTI U ZAVISNOSTI KOJI TAB JE OTVOREN
		btn_dodaj.addActionListener(new AddBtnActionListener());
		
		btn_edit.addActionListener(new EditBtnActionListener());
		
		btn_izbrisi.addActionListener(new DeleteBtnActionListener());
		
		btn_search.addActionListener(new SearchBtnActionListener());
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
