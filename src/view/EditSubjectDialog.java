package view;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import controller.SubjectController;
import model.Predmet;

public class EditSubjectDialog extends NewSubjectDialog {

	private static final long serialVersionUID = 1L;
	
	public EditSubjectDialog(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		initDialog();
		btnOK.setEnabled(true);
		addActionsOK();
	}
	
	public void initDialog() {
		Predmet subj = SubjectController.getSubjectController().getSelectedSubject();
		txt1.setText(subj.getSifra());
		txt2.setText(subj.getNaziv());
		combo1.setSelectedItem(subj.getSemestar());
		combo2.setSelectedItem(subj.getStrGodina());
	}
	
	@Override
	public void addActionsOK() {
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String sifra = txt1.getText();
				String naziv = txt2.getText();
				String semestar = (String)combo1.getSelectedItem();
				String godina = (String) combo2.getSelectedItem();
				SubjectController sc = SubjectController.getSubjectController();
				Predmet p = new Predmet(sifra, naziv, semestar, 0, null);
				p.setStrGodina(godina);
				sc.editSubject(p);
			}
		});
	}
}
