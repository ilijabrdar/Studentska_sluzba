package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controller.ProfesorController;
import model.Profesor;

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
	protected JTextField txt_titula = null;
	protected JTextField txt_zvanje = null;
	
	public NewProfesorDialog(Frame parent, String title, boolean modal) {
		super(parent,title,modal);
		
		setSize(600, 600);
		setLocationRelativeTo(parent);
		setResizable(false);
		this.setLayout(new BorderLayout());
		
		setFields();
		
		addActionsWind();
		//addActionsFields();
		addActionsOK();
		addActionsCANCEL();
		
		
	}
	
	public void addActionsWind() {
		this.addWindowListener(new DialogWindowListener());
	}
	
	JDialog getDialog() { return this; }
	
	void setFields() {
		JLabel ime = new JLabel("Ime:");
		JLabel prezime = new JLabel("Prezime:");
		JLabel adresa = new JLabel("Adresa:");
		JLabel datum_rodjenja = new JLabel("Datum rodjenja: ");
		JLabel telefon = new JLabel ("Telefon: ");
		JLabel email = new JLabel("Email: ");
		JLabel kancelarija = new JLabel("Kancelarija: ");
		JLabel licna = new JLabel("Broj licne karte: ");
		JLabel titula = new JLabel("Titula: ");
		JLabel zvanje = new JLabel("Zvanje: ");

		
		
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
		
		txt_titula = new JTextField();
		txt_titula.setName("Titula");
		txt_titula.setPreferredSize(new Dimension (300,28));
		
		txt_zvanje = new JTextField();
		txt_zvanje.setName("Zvanje");
		txt_zvanje.setPreferredSize(new Dimension (300,28));
		
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
		panel9.add(txt_titula);
		
		JPanel panel10 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		panel10.setOpaque(false);
		panel10.add(zvanje);
		panel10.add(txt_zvanje);
		
		
		
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

				
				String ime = txt_ime.getText();
				String prezime = txt_prezime.getText();
				String datum_rodjenja = txt_datum_rodjenja.getText();
				String telefon = txt_telefon.getText();
				String adresa = txt_adresa.getText();
				String email = txt_email.getText();
				String kancelarija = txt_kancelarija.getText();
				String titula = txt_titula.getText();
				String zvanje = txt_zvanje.getText();
				String licna = txt_licna.getText();
				
				 Profesor p = new Profesor(ime,prezime,datum_rodjenja,adresa,telefon,email,kancelarija,licna,titula,zvanje,null);
					
				ProfesorController profesor_controller = ProfesorController.getInstance();
				profesor_controller.dodajProfesora(p);
					
				JOptionPane.showMessageDialog(getDialog(), "Uspesno dodat profesor.");
				txt_ime.setText("");
				txt_prezime.setText("");
				txt_datum_rodjenja.setText("");
				txt_telefon.setText("");
				txt_adresa.setText("");
				txt_email.setText("");
				
				getDialog().dispose();	
				
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
