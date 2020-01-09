package main;

import view.components.IntroPanel;
import view.components.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.lang.Thread.*;

public class ApplicationMain {

	private static final int TIME_VISIBLE = 30000;

	public static void main(String[] args) {
		runIntroPanel();

		MainFrame frame = MainFrame.getInstance();
		frame.setVisible(true);

	}

	private static void runIntroPanel() {
		JWindow window = new JWindow();
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension scrSize = kit.getScreenSize();
		int height = scrSize.height;
		int width = scrSize.width;
		window.setSize(width / 2, height / 2);
		window.setLocationRelativeTo(null);

		JPanel pane = new JPanel();
		window.add(pane);


		JProgressBar pbar = new JProgressBar();
		pbar.setForeground(Color.gray);
		pbar.setValue(0);

		pane.setLayout(new BorderLayout());
		pane.add(pbar, BorderLayout.SOUTH);
		IntroPanel intro = new IntroPanel(height/2, width/2);
		pane.add(intro, BorderLayout.CENTER);
		window.setVisible(true);

		int i = 0;
		try {
			while (i <= 100) {
				pbar.setValue(i + 10);
				Thread.sleep(1000);
				i += 20;
			}
		} catch (Exception e) { e.printStackTrace(); }

		window.setVisible(false);
	}

}
