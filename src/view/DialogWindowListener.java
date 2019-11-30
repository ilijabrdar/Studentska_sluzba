package view;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DialogWindowListener implements WindowListener {

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		JDialog diag = (JDialog) e.getComponent();
		int code = JOptionPane.showConfirmDialog(diag, "Da li ste sigurni da želite da obustavite unos?\nIzmene neće biti sačuvane.",
				diag.getTitle(), JOptionPane.YES_NO_OPTION);
		if(code != JOptionPane.YES_OPTION)
			diag.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		else
			diag.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
