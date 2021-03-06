package view.dialogs;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.SubjectController;
import model.entiteti.Predmet;

public class EditSubjectDialog extends SubjectDialog {

	private static final long serialVersionUID = 1L;
	
	public EditSubjectDialog(Frame owner, String title, boolean modal) {
		super(owner, title, modal, 1, 1);
		initDialog();
		btnOK.setEnabled(true);
		addActionsOK();
	}
	
	public void initDialog() {
		Predmet subj = SubjectController.getSubjectController().getSelectedSubjectByID();
		txt1.setText(subj.getSifra());
		txt2.setText(subj.getNaziv());
		combo1.setSelectedItem(subj.getSemestar());
		combo2.setSelectedItem(subj.getStrGodina());
	}

	public void addActionsOK() {
		btnOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String sifra = txt1.getText();
				String naziv = txt2.getText();
				String semestar = (String)combo1.getSelectedItem();
				String godina = (String) combo2.getSelectedItem();
				SubjectController sc = SubjectController.getSubjectController();
				Predmet p = new Predmet(sifra, naziv, semestar, godina);
				p.setStrGodina(godina);
				sc.editSubject(p, getDialog());
			}
		});
	}
}
