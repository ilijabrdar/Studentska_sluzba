package view.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controller.ProfesorController;
import model.entiteti.Profesor;
import view.components.BackgroundPanel;
import view.listeners.DialogWindowListener;
import view.listeners.MyListenerProfesor;

public class NewProfesorDialog extends JDialog {

	private static final long serialVersionUID = -2809579264490259838L;
	
	protected JButton btnOK = null;
	protected JButton btnCANCEL = null;
	
	protected JTextField txt_ime = null;
	protected JTextField txt_prezime = null;
	protected JTextField txt_datum_rodjenja = null;
	protected JTextField txt_telefon = null;
	protected JTextField txt_adresa = null;
	protected JTextField txt_email = null;
	
	protected JTextField txt_kancelarija = null;
	protected JTextField txt_licna = null;

	protected JComboBox<String> combo_zvanje = null;
	protected JComboBox<String> combo_titula = null;


	public NewProfesorDialog(Frame parent, String title, boolean modal) {
		super(parent,title,modal);
		
		setSize(600, 600);
		setLocationRelativeTo(parent);
		setResizable(false);
		this.setLayout(new BorderLayout());
		
		setFields();
		
		addActionsWind();
		addKeyListeners();
		addActionsOK();
		addActionsCANCEL();
		
		
	}

	protected void addKeyListeners() {
		btnOK.setEnabled(false);

		MyListenerProfesor listenerProfesor = new MyListenerProfesor(btnOK,0);

		txt_adresa.addKeyListener(listenerProfesor);
		txt_datum_rodjenja.addKeyListener(listenerProfesor);
		txt_email.addKeyListener(listenerProfesor);
		txt_ime.addKeyListener(listenerProfesor);
		txt_prezime.addKeyListener(listenerProfesor);
		txt_telefon.addKeyListener(listenerProfesor);
		txt_kancelarija.addKeyListener(listenerProfesor);
		txt_licna.addKeyListener(listenerProfesor);

	}

	public void addActionsWind() {
		this.addWindowListener(new DialogWindowListener());
	}
	
	JDialog getDialog() { return this; }
	
	void setFields() {
		JLabel ime = new JLabel("Ime:*");
		JLabel prezime = new JLabel("Prezime:*");
		JLabel adresa = new JLabel("Adresa:*");
		JLabel datum_rodjenja = new JLabel("Datum rođenja:* ");
		JLabel telefon = new JLabel ("Telefon:* ");
		JLabel email = new JLabel("Email:* ");
		JLabel kancelarija = new JLabel("Kancelarija:* ");
		JLabel licna = new JLabel("Broj lične karte:* ");
		JLabel titula = new JLabel("Titula:* ");
		JLabel zvanje = new JLabel("Zvanje:* ");

		
		
		txt_ime = new JTextField();
		txt_ime.setName("Ime");
		txt_ime.setPreferredSize(new Dimension (300,28));
		
		txt_prezime = new JTextField();
		txt_prezime.setName("Prezime");
		txt_prezime.setPreferredSize(new Dimension (300,28));
		
		txt_adresa = new JTextField();
		txt_adresa.setName("Adresa");
		txt_adresa.setPreferredSize(new Dimension (300,28));
		
		txt_datum_rodjenja = new JTextField();
		txt_datum_rodjenja.setName("Datum rodjenja");
		txt_datum_rodjenja.setPreferredSize(new Dimension (300,28));
		
		txt_telefon = new JTextField();
		txt_telefon.setName("Telefon");
		txt_telefon.setPreferredSize(new Dimension (300,28));
		
		txt_email = new JTextField();
		txt_email.setName("Email");
		txt_email.setPreferredSize(new Dimension (300,28));
		
		txt_kancelarija = new JTextField();
		txt_kancelarija.setName("Kancelarija");
		txt_kancelarija.setPreferredSize(new Dimension (300,28));
		
		txt_licna = new JTextField();
		txt_licna.setName("Licna"); 
		txt_licna.setPreferredSize(new Dimension (300,28));

		String []titule = new String[] {"Asistent","Saradnik","Docent","Redovni profesor","Vanredni profesor", "Dekan"};
		combo_titula = new JComboBox<>(titule);
		combo_titula.setPreferredSize(new Dimension(300,28));

		String []zvanja = new String[] {"Dipl.","Mast.","Spec.","Dr","Struk.","Spec. Struk."};
		combo_zvanje = new JComboBox<>(zvanja);
		combo_zvanje.setPreferredSize(new Dimension(300,28));
		
		BackgroundPanel bigPanel = new BackgroundPanel();
		bigPanel.setBorder(new TitledBorder(new EtchedBorder(), "Podaci o profesoru"));
		bigPanel.setLayout(new BoxLayout(bigPanel, BoxLayout.Y_AXIS));
		
		JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		panel1.setOpaque(false);
		panel1.add(ime);
		panel1.add(txt_ime);
		
		JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		panel2.setOpaque(false);
		panel2.add(prezime);
		panel2.add(txt_prezime);
		
		JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		panel3.setOpaque(false);
		panel3.add(adresa);
		panel3.add(txt_adresa);
		
		JPanel panel4 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		panel4.setOpaque(false);
		panel4.add(datum_rodjenja);
		panel4.add(txt_datum_rodjenja);
		
		JPanel panel5 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		panel5.setOpaque(false);
		panel5.add(telefon);
		panel5.add(txt_telefon);
		
		JPanel panel6 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		panel6.setOpaque(false);
		panel6.add(email);
		panel6.add(txt_email);
		
		JPanel panel7 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		panel7.setOpaque(false);
		panel7.add(kancelarija);
		panel7.add(txt_kancelarija);
		
		JPanel panel8 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		panel8.setOpaque(false);
		panel8.add(licna);
		panel8.add(txt_licna);
		
		JPanel panel9 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		panel9.setOpaque(false);
		panel9.add(titula);
		panel9.add(combo_titula);
		
		JPanel panel10 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		panel10.setOpaque(false);
		panel10.add(zvanje);
		panel10.add(combo_zvanje);
		
		
		
		bigPanel.add(panel1);
		bigPanel.add(panel2);
		bigPanel.add(panel3);
		bigPanel.add(panel4);
		bigPanel.add(panel5);
		bigPanel.add(panel6);
		bigPanel.add(panel7);
		bigPanel.add(panel8);
		bigPanel.add(panel9);
		bigPanel.add(panel10);
		
		
		JPanel closingPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		closingPanel.setBackground(Color.white);
		btnOK = new JButton("Sačuvaj");
		btnOK.setEnabled(true);
		btnCANCEL = new JButton("Odustani");
		closingPanel.add(btnOK);
		closingPanel.add(btnCANCEL);
		
		this.add(bigPanel, BorderLayout.CENTER);
		this.add(closingPanel, BorderLayout.SOUTH);
	}
	
	
	public void addActionsOK() {
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					String ime = txt_ime.getText();
					String prezime = txt_prezime.getText();
					String datum_rodjenja = txt_datum_rodjenja.getText();
					String telefon = txt_telefon.getText();
					String adresa = txt_adresa.getText();
					String email = txt_email.getText();
					String kancelarija = txt_kancelarija.getText();
					String licna = txt_licna.getText();

					//{Dipl,Mast,Spec,Dr,Struk,SpecStruk};
					Profesor.Zvanje zvanje;
					if (combo_zvanje.getSelectedIndex() == 0)
						zvanje = Profesor.Zvanje.Dipl;
					else if (combo_zvanje.getSelectedIndex() == 1)
						zvanje = Profesor.Zvanje.Mast;
					else if (combo_zvanje.getSelectedIndex() == 2)
						zvanje = Profesor.Zvanje.Spec;
					else if (combo_zvanje.getSelectedIndex() == 3)
						zvanje = Profesor.Zvanje.Dr;
					else if (combo_zvanje.getSelectedIndex() == 4)
						zvanje = Profesor.Zvanje.Struk;
					else
						zvanje = Profesor.Zvanje.SpecStruk;

					Profesor.Titula titula;
					if (combo_titula.getSelectedIndex() == 0)
						titula = Profesor.Titula.Asistent;
					else if (combo_titula.getSelectedIndex() == 1)
						titula = Profesor.Titula.Saradnik;
					else if (combo_titula.getSelectedIndex() == 2)
						titula = Profesor.Titula.Docent;
					else if (combo_titula.getSelectedIndex() == 3)
						titula = Profesor.Titula.Redovniprofesor;
					else if (combo_titula.getSelectedIndex() == 4)
						titula = Profesor.Titula.Vanredniprofesor;
					else
						titula = Profesor.Titula.Dekan;

					Profesor p = new Profesor(ime, prezime, datum_rodjenja, adresa, telefon, email, kancelarija, licna, titula, zvanje);

					ProfesorController profesor_controller = ProfesorController.getInstance();
					if (profesor_controller.dodajProfesora(p)) {
						JOptionPane.showMessageDialog(getDialog(), "Uspešno dodat profesor.");
						getDialog().dispose();
					} else {
						JOptionPane.showMessageDialog(getDialog(), "Vec postoji profesor sa istim brojem licne karte", "Greška", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception ee) {
					JOptionPane.showMessageDialog(getDialog(),"Unesite ispravan datum u formatu dd/mm/yyyy");
				}
			}

		});
		
	}
	
	public void addActionsCANCEL() {
		btnCANCEL.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int code = JOptionPane.showConfirmDialog(getDialog(), "Da li ste sigurni da želite da obustavite unos?\nIzmene neće biti sačuvane.",
						"Novi profesor", JOptionPane.YES_NO_OPTION);
				
				if(code == JOptionPane.YES_OPTION)
					getDialog().dispose();
			}
		});
	}
}
