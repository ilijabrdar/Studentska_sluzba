package view.components;

import javax.swing.*;
import java.awt.*;

public class IntroPanel extends JPanel {

    Image img;
    public IntroPanel(int height, int width) {
        ImageIcon im = new ImageIcon(new ImageIcon("resources/imgs/intro.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        img = im.getImage();
    }

    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }

}
