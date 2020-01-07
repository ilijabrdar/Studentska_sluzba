package main;

import view.components.MainFrame;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ApplicationMain {

	public static void main(String[] args) {
		
		MainFrame frame = MainFrame.getInstance();
		frame.setVisible(true);

	}

}
