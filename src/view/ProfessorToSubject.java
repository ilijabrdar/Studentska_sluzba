package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.SubjectController;

public class ProfessorToSubject extends JDialog{

	private static final long serialVersionUID = -8999571478176283022L;
	
	private JButton btnOK = null, btnCENCEL = null;
	private JTextField txt = null;
	
	public ProfessorToSubject(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		setSize(new Dimension(550, 150));
		setLocationRelativeTo(owner);
		setResizable(false);
		setLayout(new BorderLayout());
		setContent();
		setActions();
	}
	
	public void setContent() {
		JLabel lab = new JLabel("Broj lične karte profesora*");
		txt = new JTextField();
		txt.setPreferredSize(new Dimension(300, 28));
		btnOK = new JButton("Potvrda");
		btnCENCEL = new JButton("Odustanak");
		
		BackgroundPanel bigPanel = new BackgroundPanel();
		bigPanel.add(Box.createVerticalStrut(75));
		bigPanel.add(lab);
		bigPanel.add(Box.createHorizontalStrut(20));
		bigPanel.add(txt);
		
		JPanel closingPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		closingPanel.add(btnOK);
		closingPanel.add(btnCENCEL);
		
		this.add(closingPanel, BorderLayout.SOUTH);
		this.add(bigPanel, BorderLayout.CENTER);
	}
	
	public ProfessorToSubject getDialog() { return this; }
	
	public void setActions() {
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String ID = txt.getText();
				SubjectController sc = SubjectController.getSubjectController();
				if(!sc.addProfToSubj(ID))
					JOptionPane.showMessageDialog(getDialog(), "Uneti profesor ne postoji.", "Dodavanje profesora na predet", JOptionPane.ERROR_MESSAGE);
				dispose();
			}
		});
	}


}
