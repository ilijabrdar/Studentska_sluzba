package view.dialogs;

import javax.swing.*;
import java.awt.*;

public class AboutDialog extends JDialog {

    public AboutDialog(Frame parent, String title, boolean modal) {
        super(parent,title,modal);

        setSize(600, 600);
        setLocationRelativeTo(parent);
        setResizable(false);
        this.setLayout(new BorderLayout());


        JTextArea txt = new JTextArea();
        txt.setSize(600,600);

        txt.setText("Ovo je about sekcija"); //TODO: Prikaz verzije aplikacije, kao i kratak opis iste. Nakon toga treba da sledi sažeta biografija svakog autora.
        txt.setEditable(false);
        txt.setWrapStyleWord(true);

        JScrollPane sp = new JScrollPane(txt);
        this.add(sp,BorderLayout.CENTER);
    }
}
