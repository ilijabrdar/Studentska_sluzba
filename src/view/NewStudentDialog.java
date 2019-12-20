package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controller.StudentController;
import model.Student;
import model.Student.Status;

public class NewStudentDialog extends JDialog {


	private static final long serialVersionUID = 6377454185713167341L;
	
	protected JButton btnOK = null;
	protected JButton btnCANCEL = null;
	
	protected JTextField txt_ime = null;
	protected JTextField txt_prezime = null;
	protected JTextField txt_datum_rodjenja = null;
	protected JTextField txt_telefon = null;
	protected JTextField txt_adresa = null;
	protected JTextField txt_email = null;
	
	protected JTextField txt_indeks = null;
	protected JTextField txt_datum_upisa = null;
	protected JTextField txt_prosek = null;
	
	protected JComboBox<String> trenutna_godina_studija = null;	
	
	protected JRadioButton rb_s = null;
	protected JRadioButton rb_b = null;
	
	
	
	public NewStudentDialog(Frame parent, String title, boolean modal) {
		super(parent, title, modal);

		setSize(600, 600);
		setLocationRelativeTo(parent);
		setResizable(false);
		this.setLayout(new BorderLayout());
		
		setFields();
		
		addActionsWind();
		//addActionsFields(); //TODO: dodaj key listenere
		addActionsOK();
		addActionsCANCEL();
		
		
	}
	
	JDialog getDialog() { return this; }

	private void setFields() {
		JLabel ime = new JLabel("Ime:");
		JLabel prezime = new JLabel("Prezime:");
		JLabel adresa = new JLabel("Adresa:");
		JLabel datum_rodjenja = new JLabel("Datum rodjenja: ");
		JLabel telefon = new JLabel ("Telefon: ");
		JLabel email = new JLabel("Email: ");
		JLabel indeks = new JLabel("Indeks: ");
		JLabel datum_upisa = new JLabel("Datum upisa: ");
		JLabel godina_studija = new JLabel("Trenutna godina studija: ");
		JLabel prosek = new JLabel("Prosek: ");
		JLabel status = new JLabel("Status: ");
		
		
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
		
		txt_indeks = new JTextField();
		txt_indeks.setName("Indeks");
		txt_indeks.setPreferredSize(new Dimension (300,28));
		
		txt_datum_upisa = new JTextField();
		txt_datum_upisa.setName("Datum upisa"); // TODO: provera ispravnosti formata datuma upisa i rodjenja
		txt_datum_upisa.setPreferredSize(new Dimension (300,28));
		
		txt_prosek = new JTextField();
		txt_prosek.setName("Prosek");
		txt_prosek.setPreferredSize(new Dimension (300,28));
		
		rb_s = new JRadioButton("Samofinansiranje: ");
		rb_b = new JRadioButton("Budzet: ");
		rb_s.setOpaque(false);
		rb_b.setOpaque(false);
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(rb_s);
		btnGroup.add(rb_b);
		
		String[] godine = new String[] {"Prva", "Druga", "Treca", "Cetvrta" };
		trenutna_godina_studija = new JComboBox<String>(godine);
		trenutna_godina_studija.setPreferredSize(new Dimension(100, 28));
		
		
		BackgroundPanel bigPanel = new BackgroundPanel();
		bigPanel.setBorder(new TitledBorder(new EtchedBorder(), "Podaci o studentu"));
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
		panel7.add(indeks);
		panel7.add(txt_indeks);
		
		JPanel panel8 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		panel8.setOpaque(false);
		panel8.add(datum_upisa);
		panel8.add(txt_datum_upisa);
		
		JPanel panel9 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		panel9.setOpaque(false);
		panel9.add(godina_studija);
		panel9.add(trenutna_godina_studija);
		
		JPanel panel10 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		panel10.setOpaque(false);
		panel10.add(prosek);
		panel10.add(txt_prosek);
		
		JPanel panel11 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		panel11.setOpaque(false);
		panel11.add(status);
		panel11.add(rb_s);
		panel11.add(rb_b);
		
		
		
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
		bigPanel.add(panel11);
		
		
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
	
	public void addActionsWind() {
		this.addWindowListener(new DialogWindowListener());
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
				String indeks = txt_indeks.getText();
				
				String prosek_str = txt_prosek.getText();
				Double prosek=0.0;
				
				try{
					
					prosek = Double.parseDouble(prosek_str);
					
					String datum_upisa = txt_datum_upisa.getText();
					
					int trenutna_godina;
					if ( trenutna_godina_studija.getSelectedIndex()==0)
						trenutna_godina = 1;
					else if (trenutna_godina_studija.getSelectedIndex()==1)
						trenutna_godina = 2;
					
					else if (trenutna_godina_studija.getSelectedIndex()==2)
						trenutna_godina = 3;
					else
						trenutna_godina=4;
					
					
					Status status;
					
					if (rb_s.isSelected()) 
						status = Status.S;
					else
						status= Status.B;


					Student s = new Student(ime,prezime,datum_rodjenja,adresa,telefon,email,indeks,datum_upisa,trenutna_godina,
							status,prosek);
					
					StudentController student_controller = StudentController.getInstance();
					student_controller.dodajStudenta(s);
					
					JOptionPane.showMessageDialog(getDialog(), "Uspesno dodat student.");
					txt_ime.setText("");
					txt_prezime.setText("");
					txt_datum_rodjenja.setText("");
					txt_telefon.setText("");
					txt_adresa.setText("");
					txt_email.setText("");
					txt_indeks.setText("");
					txt_datum_upisa.setText("");
					txt_prosek.setText("");
					
					getDialog().dispose();
				
				}
				catch(Exception ee) {
					JOptionPane.showMessageDialog(getDialog(), "Unesite samo brojeve za prosek.");
				}
				
				
				
								
			}
		});
		
	}
	
	public void addActionsCANCEL() {
		btnCANCEL.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int code = JOptionPane.showConfirmDialog(getDialog(), "Da li ste sigurni da želite da obustavite unos?\nIzmene neće biti sačuvane.",
						"Novi student", JOptionPane.YES_NO_OPTION);
				
				if(code == JOptionPane.YES_OPTION)
					getDialog().dispose();
			}
		});
	}

	
	
	
}
