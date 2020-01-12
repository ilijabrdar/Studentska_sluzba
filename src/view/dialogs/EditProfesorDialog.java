package view.dialogs;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import controller.ProfesorController;
import model.bazePodataka.BazaProfesora;
import model.entiteti.Profesor;
import view.components.MainFrame;
import view.listeners.MyListenerProfesor;
import view.tables.ProfesorTable;

public class EditProfesorDialog extends NewProfesorDialog {
	
	private static final long serialVersionUID = 144576929995287089L;

	public EditProfesorDialog(Frame parent, String title, boolean modal) {
		super(parent, title, modal);

		BazaProfesora.getBazaProfesora().updateArrayList();
		int index = ProfesorTable.getProfesorTable().getSelectedRow();
		Profesor p = BazaProfesora.getBazaProfesora().getRow(index);
		
		txt_ime.setText(p.getIme());
		txt_prezime.setText(p.getPrezime());
		txt_datum_rodjenja.setText(p.getDatum());
		txt_telefon.setText(p.getTelefon());
		txt_adresa.setText(p.getAdresa());
		txt_email.setText(p.getEmail());
		txt_kancelarija.setText(p.getKanc());
		txt_licna.setText(p.getLicna());

		//{Dipl,Mast,Spec,Dr,Struk,SpecStruk};
		if (p.getZvanje().equalsIgnoreCase("Dipl."))
			combo_zvanje.setSelectedIndex(0);
		else if (p.getZvanje().equalsIgnoreCase("Mast."))
			combo_zvanje.setSelectedIndex(1);
		else if (p.getZvanje().equalsIgnoreCase("Spec."))
			combo_zvanje.setSelectedIndex(2);
		else if (p.getZvanje().equalsIgnoreCase("Dr"))
			combo_zvanje.setSelectedIndex(3);
		else if (p.getZvanje().equalsIgnoreCase("Struk."))
			combo_zvanje.setSelectedIndex(4);
		else if (p.getZvanje().equalsIgnoreCase("Spec. Struk."))
			combo_zvanje.setSelectedIndex(5);
		else if (p.getZvanje().equalsIgnoreCase("Prof. dr"))
			combo_zvanje.setSelectedIndex(6);

		if (p.getTitula().equalsIgnoreCase("Asistent"))
			combo_titula.setSelectedIndex(0);
		else if (p.getTitula().equalsIgnoreCase("Saradnik"))
			combo_titula.setSelectedIndex(1);
		else if (p.getTitula().equalsIgnoreCase("Docent"))
			combo_titula.setSelectedIndex(2);
		else if (p.getTitula().equalsIgnoreCase("Redovni profesor"))
			combo_titula.setSelectedIndex(3);
		else if (p.getTitula().equalsIgnoreCase("Vanredni profesor"))
			combo_titula.setSelectedIndex(4);
		else if (p.getTitula().equalsIgnoreCase("Dekan"))
			combo_titula.setSelectedIndex(5);
	}

	@Override
	protected void addKeyListeners() {
		btnOK.setEnabled(true);

		MyListenerProfesor listenerProfesor = new MyListenerProfesor(btnOK,1);

		txt_adresa.addKeyListener(listenerProfesor);
		txt_datum_rodjenja.addKeyListener(listenerProfesor);
		txt_email.addKeyListener(listenerProfesor);
		txt_ime.addKeyListener(listenerProfesor);
		txt_prezime.addKeyListener(listenerProfesor);
		txt_telefon.addKeyListener(listenerProfesor);
		txt_kancelarija.addKeyListener(listenerProfesor);
		txt_licna.addKeyListener(listenerProfesor);



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
					//String zvanje = txt_zvanje.getText();
					String licna = txt_licna.getText();

					//{Dipl,Mast,Spec,Dr,Struk,SpecStruk};
					Profesor.Zvanje zvanje;
					if (combo_zvanje.getSelectedIndex() == 0)
						zvanje = Profesor.Zvanje.valueOf("Dipl");
					else if (combo_zvanje.getSelectedIndex() == 1)
						zvanje = Profesor.Zvanje.valueOf("Mast");
					else if (combo_zvanje.getSelectedIndex() == 2)
						zvanje = Profesor.Zvanje.valueOf("Spec");
					else if (combo_zvanje.getSelectedIndex() == 3)
						zvanje = Profesor.Zvanje.valueOf("Dr");
					else if (combo_zvanje.getSelectedIndex() == 4)
						zvanje = Profesor.Zvanje.valueOf("Struk");
					else if (combo_zvanje.getSelectedIndex() == 5)
						zvanje = Profesor.Zvanje.valueOf("SpecStruk");
					else
						zvanje = Profesor.Zvanje.valueOf("Profdr");

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
					int index = ProfesorTable.getProfesorTable().getSelectedRow();

					if (profesor_controller.izmeniProfesora(index, p)) {
						JOptionPane.showMessageDialog(getDialog(), "Uspešno izmenjen profesor.");
						getDialog().dispose();
					} else
						JOptionPane.showMessageDialog(getDialog(), "Već postoji profesor sa istim brojem lične karte.", "Greška", JOptionPane.ERROR_MESSAGE);
				} catch (Exception ee) {
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
						"Izmena profesora", JOptionPane.YES_NO_OPTION);
				
				if(code == JOptionPane.YES_OPTION) {
					getDialog().dispose();
					MainFrame.getInstance().updateTable();
				}
			}
		});
	}

}
