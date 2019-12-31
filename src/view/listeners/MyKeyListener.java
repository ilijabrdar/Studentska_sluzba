package view.listeners;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JTextField;

public class MyKeyListener implements KeyListener {

	private JButton btn = null;
	private int flag1, flag2;
	
	public MyKeyListener(JButton btn, int f1, int f2) {
		this.btn = btn;
		flag1 = f1;
		flag2 = f2;
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
			if(!content.matches("[A-Za-zČĆŽĐŠčćžšđ0-9]+")) {
				txt.setBackground(Color.pink);
				flag1 = 0;
			}
			else {
				txt.setBackground(Color.white);
				flag1 = 1;
			}
		
		if(txt.getName().equals("Naziv"))
			if(!content.matches("[A-ZČĆŽŠĐ][a-zčćžšđ]+( (- )?[a-zčćžšđ]+)*( [0-9])?")) {
				txt.setBackground(Color.pink);
				flag2 = 0;
			}
			else {
				txt.setBackground(Color.white);
				flag2 = 1;
			}
		
		if(flag1 == 1 && flag2 == 1)
			btn.setEnabled(true);
		else
			btn.setEnabled(false);
	}

}
