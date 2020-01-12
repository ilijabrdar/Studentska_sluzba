package view.listeners;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

        if (j_txt.getName().equalsIgnoreCase("ime")) //mora poceti veikim slovom
            provera_ime(text,j_txt);

        else if (j_txt.getName().equalsIgnoreCase("prezime")) //mora poceti veikim slovom
            provera_prezime(text,j_txt);

        else if (j_txt.getName().equalsIgnoreCase("datum rodjenja")) //ne sme biti posle trenutnog datuma, zabrana neispravnih datuma tipa 00.01.0000. se postize try/catch blokom
            provera_datum_rodjenja(text,j_txt);

        else if (j_txt.getName().equalsIgnoreCase("telefon")) //ne sme biti prazan, korisnik sam odlucuje koji karakter da stavi ako objekat nema telefon
            provera_telefon(text,j_txt);

        else if (j_txt.getName().equalsIgnoreCase("email"))//ne sme biti prazan, korisnik sam odlucuje koji karakter da stavi ako objekat nema email
            provera_email(text,j_txt);

        else if (j_txt.getName().equalsIgnoreCase("adresa")) //ne sme biti prazan, korisnik sam odlucuje koji karakter da stavi ako objekat nema adresu
            provera_adresa(text,j_txt);

        else if (j_txt.getName().equalsIgnoreCase("kancelarija")) //ne sme biti prazan, korisnik sam odlucuje koji karakter da stavi ako objekat nema kancelariju
            provera_kancelarija(text,j_txt);

        else if (j_txt.getName().equalsIgnoreCase("licna")) //mora sadrzati brojeve 0-9
            provera_licna(text,j_txt);

        if (flag_adresa==1 && flag_email==1 && flag_telefon==1 && flag_kanc==1 && flag_datum_r==1 && flag_prezime==1 && flag_ime==1 && flag_licna==1)
            btnOK.setEnabled(true);

    }

    private void provera_licna(String text, JTextField j_txt) {
        if (!text.trim().matches("[0-9]+")) {
            btnOK.setEnabled(false);
            j_txt.setBackground(Color.pink);
            flag_licna=0;
        } else {
            flag_licna=1;
            j_txt.setBackground(Color.white);
        }
    }

    private void provera_kancelarija(String text, JTextField j_txt) {
        if (text.trim().matches("")) {
            btnOK.setEnabled(false);
            j_txt.setBackground(Color.pink);
            flag_kanc=0;
        } else {
            flag_kanc=1;
            j_txt.setBackground(Color.white);
        }
    }

    private void provera_adresa(String text, JTextField j_txt) {
        if (text.trim().matches("")) {
            btnOK.setEnabled(false);
            j_txt.setBackground(Color.pink);
            flag_adresa=0;
        } else {
            flag_adresa=1;
            j_txt.setBackground(Color.white);
        }
    }

    private void provera_email(String text, JTextField j_txt) {
        if (text.trim().matches("")) {
            btnOK.setEnabled(false);
            j_txt.setBackground(Color.pink);
            flag_email=0;
        } else {
            flag_email=1;
            j_txt.setBackground(Color.white);
        }
    }

    private void provera_telefon(String text, JTextField j_txt) {
        if (text.trim().matches("")) {
            btnOK.setEnabled(false);
            j_txt.setBackground(Color.pink);
            flag_telefon=0;
        } else {
            flag_telefon=1;
            j_txt.setBackground(Color.white);
        }
    }

    private void provera_datum_rodjenja(String text, JTextField j_txt) {
        if (!text.matches("[0-3][0-9]\\.[0-9][0-9]\\.[0-9][0-9][0-9][0-9]\\.")) {
            btnOK.setEnabled(false);
            j_txt.setBackground(Color.pink);
            flag_datum_r = 0;
        } else {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
                LocalDate datum_rodjenja = LocalDate.parse(text, formatter);
                LocalDate trenutni = LocalDate.now();
                if (!datum_rodjenja.isBefore(trenutni)) {
                    btnOK.setEnabled(false);
                    j_txt.setBackground(Color.pink);
                    flag_datum_r = 0;
                } else {
                    j_txt.setBackground(Color.white);
                    flag_datum_r = 1;
                }
            } catch (Exception ee) {

            }

        }
    }

    private void provera_prezime(String text, JTextField j_txt) {
        if (!text.matches("[A-ZČĆŽŠĐ].+")) {
            btnOK.setEnabled(false);
            flag_prezime=0;
            j_txt.setBackground(Color.pink);
        } else {
            j_txt.setBackground(Color.white);
            flag_prezime=1;
        }
    }

    private void provera_ime(String text, JTextField j_txt) {
        if (!text.matches("[A-ZČĆŽŠĐ].+")) {
            btnOK.setEnabled(false);
            flag_ime=0;
            j_txt.setBackground(Color.pink);
        } else {
            j_txt.setBackground(Color.white);
            flag_ime=1;
        }
    }
}
