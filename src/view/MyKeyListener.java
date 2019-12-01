package view;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JTextField;

public class MyKeyListener implements KeyListener {

	private JButton btn = null;
	private int flag1, flag2;
	
	public MyKeyListener(JButton btn) {
		this.btn = btn;
		flag1 = 0;
		flag2 = 0;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		JTextField txt = (JTextField) e.getComponent();
		String content = txt.getText().trim();
		
		if(txt.getName().equals("Sifra"))
			if(content.equals("")) {
				txt.setBackground(Color.red);
				flag1 = 0;
			}
			else {
				txt.setBackground(Color.white);
				if(content.matches("[A-Za-z0-9]+"))
					flag1 = 1;
				else
					flag1= 0;
			}
		
		if(txt.getName().equals("Naziv"))
			if(content.equals("")) {
				txt.setBackground(Color.red);
				flag2 = 0;
			}
			else {
				txt.setBackground(Color.white);
				if(content.matches("[A-Z][a-z]+"))
					flag2 = 1;
				else
					flag2 = 0;
			}
		
		if(flag1 == 1 && flag2 == 1)
			btn.setEnabled(true);
		else
			btn.setEnabled(false);
	}

}
