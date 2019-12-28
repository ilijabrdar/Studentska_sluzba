package view.dialogs;

import javax.swing.*;
import java.awt.*;

public class HelpDialog extends JDialog {

    public HelpDialog(Frame parent, String title, boolean modal) {
        super(parent,title,modal);

        setSize(600, 600);
        setLocationRelativeTo(parent);
        setResizable(false);
        this.setLayout(new BorderLayout());


        JTextArea txt = new JTextArea();
        txt.setSize(600,600);

        txt.setText("Ovo je help sekcija"); //TODO: Ova sekcija treba da sadrži detaljan opis o načinu korišćenja aplikacije. Potrebno je objasniti kako se svaka od dolenavedenih funkcionalnosti može sprovesti u delo i to u vidu niza korisničkih akcija. Takođe, potrebno je navesti prečice (akceleratore) koje naprednim korisnicima mogu olakšati rad.
        txt.setEditable(false);
        txt.setWrapStyleWord(true);

        JScrollPane sp = new JScrollPane(txt);
        this.add(sp,BorderLayout.CENTER);

    }

}
