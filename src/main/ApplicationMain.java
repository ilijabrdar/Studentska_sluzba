package main;

import view.components.MainFrame;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ApplicationMain {

	public static void main(String[] args) {
		
		MainFrame frame = MainFrame.getInstance();
		frame.setVisible(true);

//		String datum = "13/56/2001";
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//		LocalDate date = LocalDate.parse(datum,formatter);
//		System.out.println(date);
	}

}
