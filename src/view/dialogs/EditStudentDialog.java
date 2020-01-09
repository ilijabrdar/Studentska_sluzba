package view.dialogs;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import controller.StudentController;
import model.bazePodataka.BazaStudenata;
import model.entiteti.Student;
import model.entiteti.Student.Status;
import view.listeners.MyListenerStudent;
import view.tables.StudentsTable;

public class EditStudentDialog extends NewStudentDialog {

	private static final long serialVersionUID = -6423347424244063431L;

	//TODO: da li treba pri promeni godine studija da se izbrisu svi predmeti studenta koji vise nisu u prethodnoj godini
	public EditStudentDialog(Frame parent, String title, boolean modal) {
		super(parent, title, modal);

		BazaStudenata.getBazaStudenata().updateArrayList();

		int row = StudentsTable.getStudentsTable().getSelectedRow();
		Student s = BazaStudenata.getBazaStudenata().getRow(row);

		txt_ime.setText(s.getIme());
		txt_prezime.setText(s.getPrezime());
		txt_datum_rodjenja.setText(s.getDatum());
		txt_telefon.setText(s.getTelefon());
		txt_adresa.setText(s.getAdresa());
		txt_email.setText(s.getEmail());
		txt_indeks.setText(s.getIndex());
		txt_datum_upisa.setText(s.getDatum_upisa());
		txt_prosek.setText(Double.toString(s.getProsek()));
		
		int godina_studija = s.getGodina_studija();
		if (godina_studija==1)
			trenutna_godina_studija.setSelectedItem("Prva");
		else if (godina_studija ==2)
			trenutna_godina_studija.setSelectedItem("Druga");
		else if (godina_studija ==3)
			trenutna_godina_studija.setSelectedItem("Treca");
		else
				trenutna_godina_studija.setSelectedItem("Cetvrta");
		
		Student.Status status = s.getStatus();
		if (status == Status.B) {
			rb_b.setSelected(true);
			rb_s.setSelected(false);
			
		}
		else {
			rb_b.setSelected(false);
			rb_s.setSelected(true);
		}
		
		

	}

	@Override
	protected void addKeyListeners() {
		btnOK.setEnabled(true);

		MyListenerStudent k = new MyListenerStudent(btnOK,rb_b,rb_s,1);
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
					if (prosek<6 || prosek>10) {
						throw new Exception();
					}
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
					int index_tabele = StudentsTable.getStudentsTable().getSelectedRow();
					if (student_controller.izmeniStudenta(index_tabele, s)) {
						JOptionPane.showMessageDialog(getDialog(), "Uspesno izmenjen student.");
						getDialog().dispose();
					}
					else {
						JOptionPane.showMessageDialog(getDialog(),"Vec postoji student sa istim indexom", "Greška", JOptionPane.ERROR_MESSAGE);
					}
				
				}
				catch(Exception ee) {
					if (prosek<6 || prosek>10)
						JOptionPane.showMessageDialog(getDialog(), "Unesite samo brojeve u rasponu 6.00 - 10.00 za prosek.");
					else
						JOptionPane.showMessageDialog(getDialog(),"Unesite ispravan datum u formatu dd.mm.yyyy.");
				}
				
				
				
								
			}
		});
		
	}
	
	public void addActionsCANCEL() {
		btnCANCEL.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int code = JOptionPane.showConfirmDialog(getDialog(), "Da li ste sigurni da želite da obustavite unos?\nIzmene neće biti sačuvane.",
						"Izmena studenta", JOptionPane.YES_NO_OPTION);
				
				if(code == JOptionPane.YES_OPTION)
					getDialog().dispose();
			}
		});
	}

}
