package view;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import controller.StudentController;
import model.BazaStudenata;
import model.Student;
import model.Student.Status;

public class EditStudentDialog extends NewStudentDialog {

	private static final long serialVersionUID = -6423347424244063431L;

	public EditStudentDialog(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		
		int index = StudentsTable.getStudentsTable().getSelectedRow();
		Student s = BazaStudenata.getBazaStudenata().getRow(index);
		
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
							status,prosek,null);
					
					StudentController student_controller = StudentController.getInstance();
					int index = StudentsTable.getStudentsTable().getSelectedRow();
					student_controller.izmeniStudenta(index, s);
					
					JOptionPane.showMessageDialog(getDialog(), "Uspesno izmenjen student.");
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
						"Izmena studenta", JOptionPane.YES_NO_OPTION);
				
				if(code == JOptionPane.YES_OPTION)
					getDialog().dispose();
			}
		});
	}

}
