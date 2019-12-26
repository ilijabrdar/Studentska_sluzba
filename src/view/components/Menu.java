package view.components;

import view.dialogs.NewProfesorDialog;
import view.dialogs.NewStudentDialog;
import view.dialogs.NewSubjectDialog;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class Menu extends JMenuBar {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2401343171737574518L;

	public Menu() {
		//mnemonik je skracenica da ti udje u taj odeljak menija
		//accelerator je da ti odradi do kraja
		
		this.setBackground(Color.WHITE);
		
		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);		//ALF+F
		JMenuItem file_new = new JMenuItem("New");
		file_new.addActionListener(new ActionListener() { //NOVA KLASA?
			@Override
			public void actionPerformed(ActionEvent e) {
				if(TabbedPane.getInstance().isRunning("Predmeti")) {
					NewSubjectDialog sd = new  NewSubjectDialog(MainFrame.getInstance(), "Novi predmet", true, 0, 0);
					sd.setVisible(true);
				}
				else if (TabbedPane.getInstance().isRunning("Studenti")) {
					NewStudentDialog student_dialog = new NewStudentDialog(MainFrame.getInstance(), "Novi predmet", true);
					student_dialog.setVisible(true);
				}

				else if (TabbedPane.getInstance().isRunning("Profesori")) {
					NewProfesorDialog profesor_dialog = new NewProfesorDialog(MainFrame.getInstance(), "Novi profesor", true);
					profesor_dialog.setVisible(true);
				}
			}
		});
		
		ImageIcon imageIcon_new = new ImageIcon(new ImageIcon("imgs/069-plus.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		file_new.setIcon(imageIcon_new);
		file_new.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK)); //CTRL
		
		JMenuItem file_close = new JMenuItem("Close");
		ImageIcon imageIcon_close = new ImageIcon(new ImageIcon("imgs/020-close.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)); //skaliranje ikonice 
		file_close.setIcon(imageIcon_close);
		file_close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));//CTRL+C

		
		file.add(file_new);
		file.addSeparator();
		file.add(file_close);
		add(file);
		
		JMenu edit = new JMenu("Edit");
		edit.setMnemonic(KeyEvent.VK_E);
		
		JMenuItem edit_edit = new JMenuItem("Edit");
		ImageIcon imageIcon_edit = new ImageIcon(new ImageIcon("imgs/062-pencil.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		edit_edit.setIcon(imageIcon_edit);
		edit_edit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		
		
		JMenuItem edit_delete = new JMenuItem("Delete");
		ImageIcon imageIcon_delete = new ImageIcon(new ImageIcon("imgs/076-remove.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		edit_delete.setIcon(imageIcon_delete);
		edit_delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		
		edit.add(edit_edit);
		edit.addSeparator();
		edit.add(edit_delete);
		add(edit);
		
		JMenu help = new JMenu("Help");
		help.setMnemonic(KeyEvent.VK_H);
		
		JMenuItem help_help = new JMenuItem("Help");
		ImageIcon imageIcon_help = new ImageIcon(new ImageIcon("imgs/050-info.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		help_help.setIcon(imageIcon_help);
		help_help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
		
		JMenuItem help_about = new JMenuItem("About");
		ImageIcon imageIcon_about = new ImageIcon(new ImageIcon("imgs/045-heart.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		help_about.setIcon(imageIcon_about);
		help_about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		
		help.add(help_help);
		help.addSeparator();
		help.add(help_about);
		add(help);
		
		
	}
	
}
