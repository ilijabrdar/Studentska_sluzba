package view.components;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel {

	private static final long serialVersionUID = -3079222464936272455L;
	
	Image img;
	public BackgroundPanel() {
		img = new ImageIcon("resources/imgs/p2.jpg").getImage();
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(img, -10, -70, null);
	}
	
}
