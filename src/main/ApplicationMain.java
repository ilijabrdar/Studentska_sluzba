package main;

import view.components.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ApplicationMain {

	private static final int TIME_VISIBLE = 30000;

	public static void main(String[] args) {

		JOptionPane pane = new JOptionPane("Studentska sluzba (Premium Edition)   ", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
		JDialog dialog = pane.createDialog(null, "U pripremi...");
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.setModal(false);
		dialog.setVisible(true);
		Thread thread = new Thread();
		thread.start();

		JProgressBar pbar = new JProgressBar();
		pbar.setIndeterminate(true);
		pbar.setMaximum(100);
		pbar.setMaximum(0);

		dialog.setLayout(new BorderLayout());
		dialog.add(pbar, BorderLayout.SOUTH);
		int seconds = 0;
		int max = 3;
		int i = 0;

		while(seconds < max){
			 seconds++;
			 pbar.setValue(i+=10);

			 try {
				 pbar.updateUI(); //ovo izaziva gomilu gresaka, catch sve resava
			 }
			 catch(NullPointerException e) {

			}

			 try{
				Thread.sleep(1000);
			 } catch (InterruptedException exc){
			 };
		 }


		dialog.setVisible(false);

		MainFrame frame = MainFrame.getInstance();
		frame.setVisible(true);

	}

}
