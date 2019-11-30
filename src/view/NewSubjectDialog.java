package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controller.SubjectController;
import model.BazaPredmeta;
import model.Predmet;

public class NewSubjectDialog extends JDialog {

	private static final long serialVersionUID = 133958153971296240L;
	
	protected JTextField txt1 = null, txt2 = null, txt3 = null;
	protected JComboBox<String> combo1 = null, combo2 = null;
	protected JButton btnOK = null, btnCENCEL = null;
	
	public NewSubjectDialog(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		setSize(400, 400);
		setLocationRelativeTo(owner);
		setResizable(false);
		this.setLayout(new BorderLayout());
		setContent();
		addActionsWind();
		addActionsFields();
		addActionsOK();
		addActionsCENCEL();
	}
	
	JDialog getDialog() { return this; }
	
	public void setContent() {
		JLabel lab1 = new JLabel("Šifra*");
		JLabel lab2 = new JLabel("Naziv*");
		JLabel lab3 = new JLabel("Semestar*");
		JLabel lab4 = new JLabel("Godina studija*");
		JLabel lab5 = new JLabel("Profesor*");
		
		txt1 = new JTextField();
		txt1.setName("Sifra");
		txt1.setPreferredSize(new Dimension(300, 28));
		txt2 = new JTextField();
		txt2.setName("Naziv");
		txt2.setPreferredSize(new Dimension(300, 28));
		txt3 = new JTextField();
		txt3.setName("Profa");
		txt3.setPreferredSize(new Dimension(280, 28));
		
		String [] semesters = new String[] {"Letnji", "Zimski"};
		combo1 = new JComboBox<String>(semesters);
		combo1.setPreferredSize(new Dimension(100, 28));

		String [] year = new String[] {"Prva", "Druga", "Treća", "Četvrta"};
		combo2 = new JComboBox<String>(year);
		combo2.setPreferredSize(new Dimension(100, 28));
		
		BackgroundPanel bigPanel = new BackgroundPanel();
		bigPanel.setBorder(new TitledBorder(new EtchedBorder(), "Podaci o predmetu"));
		bigPanel.setLayout(new BoxLayout(bigPanel, BoxLayout.Y_AXIS));
		
		JPanel pane1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		pane1.setOpaque(false);
		pane1.add(lab1);
		pane1.add(txt1);
		
		JPanel pane2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		pane2.setOpaque(false);
		pane2.add(lab2);
		pane2.add(txt2);
		
		JPanel pane3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		pane3.setOpaque(false);
		pane3.add(lab3);
		pane3.add(Box.createHorizontalStrut(30));
		pane3.add(combo1);

		JPanel pane4 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		pane4.setOpaque(false);
		pane4.add(lab4);
		pane4.add(Box.createHorizontalStrut(7));
		pane4.add(combo2);
		
		JPanel pane5 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		pane5.setOpaque(false);
		pane5.add(lab5);
		pane5.add(txt3);
		
		bigPanel.add(pane1);
		bigPanel.add(pane2);
		bigPanel.add(pane3);
		bigPanel.add(pane4);
		bigPanel.add(pane5);
		bigPanel.add(Box.createVerticalStrut(30));
		
		JPanel closingPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		closingPanel.setBackground(Color.white);
		btnOK = new JButton("Sačuvaj");
		btnOK.setEnabled(false);
		btnCENCEL = new JButton("Odustani");
		closingPanel.add(btnOK);
		closingPanel.add(btnCENCEL);
		
		this.add(bigPanel, BorderLayout.CENTER);
		this.add(closingPanel, BorderLayout.SOUTH);
	}
	
	public void addActionsWind() {
		this.addWindowListener(new DialogWindowListener());
	}
	
	public void addActionsFields() {
		MyKeyListener kl = new MyKeyListener(btnOK);
		txt1.addKeyListener(kl);
		txt2.addKeyListener(kl);
		txt3.addKeyListener(kl);
	}
	
	public void addActionsOK() {
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String sifra = txt1.getText();
				String naziv = txt2.getText();
				String semestar = (String)combo1.getSelectedItem();
				String godina = (String) combo2.getSelectedItem();
				//RESENJE ZA PROFESORE OSTALO
				//1RESENJE ZA STUDENTE OSTALO
				SubjectController sc = SubjectController.getSubjectController();
				Predmet p = new Predmet(sifra, naziv, semestar, 0, null);
				p.setStrGodina(godina);
				sc.addSubject(p);
				
				//System.out.println(BazaPredmeta.getBazaPredmeta().getSubjects());
			}
		});
		
	}
	
	public void addActionsCENCEL() {
		btnCENCEL.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int code = JOptionPane.showConfirmDialog(getDialog(), "Da li ste sigurni da želite da obustavite unos?\nIzmene neće biti sačuvane.",
						"Novi predmet", JOptionPane.YES_NO_OPTION);
				
				if(code == JOptionPane.YES_OPTION)
					getDialog().dispose();
			}
		});
	}
}
