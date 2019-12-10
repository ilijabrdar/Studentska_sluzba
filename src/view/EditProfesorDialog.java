package view;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import controller.ProfesorController;
import model.BazaProfesora;
import model.Profesor;

public class EditProfesorDialog extends NewProfesorDialog {
	
	private static final long serialVersionUID = 144576929995287089L;

	public EditProfesorDialog(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		
		int index = ProfesorTable.getProfesorTable().getSelectedRow();
		Profesor p = BazaProfesora.getBazaProfesora().getRow(index);
		
		txt_ime.setText(p.getIme());
		txt_prezime.setText(p.getPrezime());
		txt_datum_rodjenja.setText(p.getDatum());
		txt_telefon.setText(p.getTelefon());
		txt_adresa.setText(p.getAdresa());
		txt_email.setText(p.getEmail());
		txt_kancelarija.setText(p.getKanc());
		txt_titula.setText(p.getTitula());
		txt_zvanje.setText(p.getZvanje());
		txt_licna.setText(p.getLicna());
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
				int index = ProfesorTable.getProfesorTable().getSelectedRow();
				profesor_controller.izmeniProfesora(index, p);
					
				JOptionPane.showMessageDialog(getDialog(), "Uspesno izmenjen profesor.");
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
						"Izmena profesora", JOptionPane.YES_NO_OPTION);
				
				if(code == JOptionPane.YES_OPTION)
					getDialog().dispose();
			}
		});
	}

}
