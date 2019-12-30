package view.components;

import view.dialogs.AboutDialog;
import view.dialogs.HelpDialog;
import view.listeners.AddBtnActionListener;
import view.listeners.DeleteBtnActionListener;
import view.listeners.EditBtnActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

public class Menu extends JMenuBar {

	private static final long serialVersionUID = 2401343171737574518L;

	public Menu() {
		//mnemonik je skracenica da ti udje u taj odeljak menija
		//akcelerator je da ti odradi do kraja

		this.setBackground(Color.WHITE);

		setFile();
		setEdit();
		setHelp();

	}

	private void setFile() {
		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);		//ALF+F
		JMenuItem file_new = new JMenuItem("New");
		file_new.addActionListener(new AddBtnActionListener());

		ImageIcon imageIcon_new = new ImageIcon(new ImageIcon("resources/imgs/069-plus.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		file_new.setIcon(imageIcon_new);
		file_new.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK)); //CTRL

		JMenuItem file_close = new JMenuItem("Close");
		ImageIcon imageIcon_close = new ImageIcon(new ImageIcon("resources/imgs/020-close.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)); //skaliranje ikonice
		file_close.setIcon(imageIcon_close);
		file_close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));//CTRL+C

		file_close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.getInstance().dispatchEvent(new WindowEvent(MainFrame.getInstance(),WindowEvent.WINDOW_CLOSING)); //trigeruje listener-a
			}
		});

		file.add(file_new);
		file.addSeparator();
		file.add(file_close);

		this.add(file);
	}

	private void setEdit() {
		JMenu edit = new JMenu("Edit");
		edit.setMnemonic(KeyEvent.VK_E);

		JMenuItem edit_edit = new JMenuItem("Edit");
		ImageIcon imageIcon_edit = new ImageIcon(new ImageIcon("resources/imgs/062-pencil.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		edit_edit.setIcon(imageIcon_edit);
		edit_edit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		edit_edit.addActionListener(new EditBtnActionListener());

		JMenuItem edit_delete = new JMenuItem("Delete");
		ImageIcon imageIcon_delete = new ImageIcon(new ImageIcon("resources/imgs/076-remove.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		edit_delete.setIcon(imageIcon_delete);
		edit_delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		edit_delete.addActionListener(new DeleteBtnActionListener());

		edit.add(edit_edit);
		edit.addSeparator();
		edit.add(edit_delete);

		this.add(edit);
	}

	private void setHelp() {

		JMenu help = new JMenu("Help");
		help.setMnemonic(KeyEvent.VK_H);

		JMenuItem help_help = new JMenuItem("Help");
		ImageIcon imageIcon_help = new ImageIcon(new ImageIcon("resources/imgs/050-info.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		help_help.setIcon(imageIcon_help);
		help_help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));

		help_help.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HelpDialog dialog = new HelpDialog(MainFrame.getInstance(),"Help",true);
				dialog.setVisible(true);
			}
		});

		JMenuItem help_about = new JMenuItem("About");
		ImageIcon imageIcon_about = new ImageIcon(new ImageIcon("resources/imgs/045-heart.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		help_about.setIcon(imageIcon_about);
		help_about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));

		help_about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AboutDialog dialog = new AboutDialog(MainFrame.getInstance(),"About",true);
				dialog.setVisible(true);
			}
		});

		help.add(help_help);
		help.addSeparator();
		help.add(help_about);

		this.add(help);
	}

}
