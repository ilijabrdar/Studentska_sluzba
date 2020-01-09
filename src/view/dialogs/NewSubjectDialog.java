package view.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controller.SubjectController;
import model.entiteti.Predmet;
import view.components.BackgroundPanel;
import view.listeners.DialogWindowListener;
import view.listeners.MyKeyListener;

public class NewSubjectDialog extends SubjectDialog {

	public NewSubjectDialog(Frame owner, String title, boolean modal, int f1, int f2) {
		super(owner, title, modal, f1, f2);
		addActionsOK();
	}

	public void addActionsOK() {
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String sifra = txt1.getText().trim();
				String naziv = txt2.getText().trim();
				String semestar = (String)combo1.getSelectedItem();
				String godina = (String) combo2.getSelectedItem();
				SubjectController sc = SubjectController.getSubjectController();
				Predmet p = new Predmet(sifra, naziv, semestar, godina);
				p.setStrGodina(godina);
				sc.addSubject(p, getDialog());	
				sc.retrieveTable();
			}
		});
		
	}
	

}
