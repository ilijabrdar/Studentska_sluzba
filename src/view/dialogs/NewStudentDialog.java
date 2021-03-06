package view.dialogs;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

import view.components.MainFrame;
import controller.StudentController;
import model.entiteti.Student;
import model.entiteti.Student.Status;
import view.components.BackgroundPanel;
import view.listeners.DialogWindowListener;
import view.listeners.MyListenerStudent;

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

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension scrSize = kit.getScreenSize();
		int height = scrSize.height;
		int width = scrSize.width;

		setSize(3 * width / 8, 3 * height / 4);
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

		MyListenerStudent k = new MyListenerStudent(btnOK,rb_b,rb_s,0);
		txt_prosek.addKeyListener(k);
		txt_ime.addKeyListener(k);
		txt_prezime.addKeyListener(k);
		txt_datum_rodjenja.addKeyListener(k);
		txt_indeks.addKeyListener(k);
		txt_telefon.addKeyListener(k);
		txt_email.addKeyListener(k);
		txt_adresa.addKeyListener(k);
		txt_datum_upisa.addKeyListener(k);

		rb_b.addActionListener(k);
		rb_s.addActionListener(k);

	}


	public JDialog getDialog() { return this; }

	private void setFields() {
		JLabel ime = new JLabel("Ime:*");
		JLabel prezime = new JLabel("Prezime:*");
		JLabel adresa = new JLabel("Adresa stanovanja:*");
		JLabel datum_rodjenja = new JLabel("Datum rođenja:*");
		JLabel telefon = new JLabel ("Kontakt telefon:* ");
		JLabel email = new JLabel("E-mail adresa:* ");
		JLabel indeks = new JLabel("Broj indeksa:* ");
		JLabel datum_upisa = new JLabel("Datum upisa:* ");
		JLabel godina_studija = new JLabel("Trenutna godina studija:* ");
		JLabel prosek = new JLabel("Prosek:* ");
		JLabel status = new JLabel("Status:* ");
		
		
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
		txt_datum_upisa.setName("Datum upisa");
		txt_datum_upisa.setPreferredSize(new Dimension (300,28));
		
		txt_prosek = new JTextField();
		txt_prosek.setName("Prosek");
		txt_prosek.setPreferredSize(new Dimension (300,28));
		
		rb_s = new JRadioButton("Samofinansiranje: ");
		rb_b = new JRadioButton("Budžet: ");
		rb_s.setOpaque(false);
		rb_b.setOpaque(false);
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(rb_s);
		btnGroup.add(rb_b);

		
		String[] godine = new String[] {"Prva", "Druga", "Treća", "Četvrta" };
		trenutna_godina_studija = new JComboBox<String>(godine);
		trenutna_godina_studija.setName("trenutna godina studija");
		trenutna_godina_studija.setPreferredSize(new Dimension(100, 28));
		
		
		BackgroundPanel bigPanel = new BackgroundPanel(); //komponente se prvo stavljaju u male panele, pa onda svaki mali panel u veliki i onda na kraju veliki u CENTER
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


					if (prosek_str.equalsIgnoreCase("/"))	// '/' za prosek predstavlja 0, maskira se korisniku da zapravo radi sa 0 sve vreme
						prosek = 0.0;
					else
						prosek = Double.parseDouble(prosek_str);



					if (prosek!=0.0 && trenutna_godina==1)
						throw new Exception("Studenti prve godine nemaju prosek (unesite /).");
					else if (prosek==0.0 && trenutna_godina!=1)
						throw new Exception("Za studente viših godina morate uneti prosek.");

					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
					LocalDate datum_rodj = LocalDate.parse(datum_rodjenja, formatter);
					LocalDate datum_up = LocalDate.parse(datum_upisa,formatter);

					if (datum_up.getYear() - datum_rodj.getYear() < 10)
						throw new Exception("Godina rođenja i godina upisa se moraju razlikovati bar za 10.");

					String [] index_splits = indeks.split("/");

					if (Integer.parseInt(index_splits[1]) < datum_up.getYear())
						throw new Exception("Godina broja indeksa mora biti veća ili jednaka godini upisa.");
					
					Status status;
					
					if (rb_s.isSelected()) 	//samo jedno do radioButton-a mogu biti selektovani zato sto pripadaju isto ButtonGroup
						status = Status.S;
					else
						status= Status.B;


					Student s = new Student(ime,prezime,datum_rodjenja,adresa,telefon,email,indeks,datum_upisa,trenutna_godina,
							status,prosek);
					
					StudentController student_controller = StudentController.getInstance();
					if (student_controller.dodajStudenta(s)) { //dodavanje uspesno ukoliko ne postoji student sa tim indexom u bazi podataka
						JOptionPane.showMessageDialog(getDialog(), "Uspešno dodat student.");
						getDialog().dispose();
					}
					else {
						JOptionPane.showMessageDialog(getDialog(),"Već postoji student sa istim brojem indeksa", "Greška", JOptionPane.ERROR_MESSAGE);
					}
				
				}
				catch(Exception ee) {
					JOptionPane.showMessageDialog(MainFrame.getInstance(), ee.getMessage(),"Dodavanje studenta", JOptionPane.ERROR_MESSAGE);
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
