package view.listeners;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyListenerProfesor implements KeyListener {

    private JButton btnOK;

    private int flag_ime;
    private int flag_prezime;
    private int flag_adresa;
    private int flag_telefon;
    private int flag_email;
    private int flag_datum_r;
    private int flag_kanc;
    private int flag_licna;

    public MyListenerProfesor(JButton btnOK, int flag) {
        this.btnOK=btnOK;

        flag_ime = flag;
        flag_prezime = flag;
        flag_adresa = flag;
        flag_telefon = flag;
        flag_email = flag;
        flag_datum_r = flag;
        flag_kanc = flag;
        flag_licna = flag;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        JTextField j_txt = (JTextField) e.getComponent();
        String text = j_txt.getText();

        if (j_txt.getName().equalsIgnoreCase("ime")) {
            if (!text.matches("[A-ZČĆŽŠĐ][a-zčćžšđ]+")) {//TODO: McClean?
                btnOK.setEnabled(false);
                flag_ime=0;
                j_txt.setBackground(Color.pink);
            } else {
                j_txt.setBackground(Color.white);
                flag_ime=1;
            }
        }

        else if (j_txt.getName().equalsIgnoreCase("prezime")) {
            if (!text.matches("[A-ZČĆŽŠĐ][a-zčćžšđ]+")) {//TODO: McClean?
                btnOK.setEnabled(false);
                flag_prezime=0;
                j_txt.setBackground(Color.pink);
            } else {
                j_txt.setBackground(Color.white);
                flag_prezime=1;
            }
        }
        else if (j_txt.getName().equalsIgnoreCase("datum rodjenja")) {
            if (!text.matches("[0-3][0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9]")) {//TODO: ispravan unos
                btnOK.setEnabled(false);
                j_txt.setBackground(Color.pink);
                flag_datum_r = 0;
            } else {
                j_txt.setBackground(Color.white);
                flag_datum_r=1;
            }
        }

        else if (j_txt.getName().equalsIgnoreCase("telefon")) {
            if (text.trim().matches("")) {
                btnOK.setEnabled(false);
                j_txt.setBackground(Color.pink);
                flag_telefon=0;
            } else {
                flag_telefon=1;
                j_txt.setBackground(Color.white);
            }
        }
        else if (j_txt.getName().equalsIgnoreCase("email")) {
            if (text.trim().matches("")) {
                btnOK.setEnabled(false);
                j_txt.setBackground(Color.pink);
                flag_email=0;
            } else {
                flag_email=1;
                j_txt.setBackground(Color.white);
            }
        }
        else if (j_txt.getName().equalsIgnoreCase("adresa")) {
            if (text.trim().matches("")) {
                btnOK.setEnabled(false);
                j_txt.setBackground(Color.pink);
                flag_adresa=0;
            } else {
                flag_adresa=1;
                j_txt.setBackground(Color.white);
            }
        }
        else if (j_txt.getName().equalsIgnoreCase("kancelarija")) {
            if (text.trim().matches("")) {
                btnOK.setEnabled(false);
                j_txt.setBackground(Color.pink);
                flag_kanc=0;
            } else {
                flag_kanc=1;
                j_txt.setBackground(Color.white);
            }
        }

        else if (j_txt.getName().equalsIgnoreCase("licna")) {
            if (!text.trim().matches("[0-9]+")) {
                btnOK.setEnabled(false);
                j_txt.setBackground(Color.pink);
                flag_licna=0;
            } else {
                flag_licna=1;
                j_txt.setBackground(Color.white);
            }
        }
        if (flag_adresa==1 && flag_email==1 && flag_telefon==1 && flag_kanc==1 && flag_datum_r==1 && flag_prezime==1 && flag_ime==1 && flag_licna==1) {
            btnOK.setEnabled(true);
        }
    }
}
