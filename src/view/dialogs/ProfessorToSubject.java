package view.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import controller.SubjectController;
import view.components.BackgroundPanel;

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
	
		btnOK.setEnabled(false);
	}
	
	public ProfessorToSubject getDialog() { return this; }
	
	public void setActions() {
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("USAO U LISTENER");
				String ID = txt.getText().trim();
				SubjectController sc = SubjectController.getSubjectController();
				int param = sc.addProfToSubj(ID);
				if( param == 0)
					JOptionPane.showMessageDialog(getDialog(), "Uneti profesor ne postoji.", "Dodavanje profesora na predet", JOptionPane.ERROR_MESSAGE);
				else if(param == 2)
					JOptionPane.showMessageDialog(getDialog(), "Profesor je već unet na predmet.", "Dodavanje profesora na predet", JOptionPane.INFORMATION_MESSAGE);
				else
					dispose();
			}

		});
		
		btnCENCEL.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		txt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				JTextField txt = (JTextField) e.getComponent();
				String content = txt.getText().trim();
				if(!content.matches("[0-9]+")) {
					txt.setBackground(Color.pink);
					btnOK.setEnabled(false);
				}
				else {
					txt.setBackground(Color.white);
					btnOK.setEnabled(true);
				}
			}
		});
	}


}
