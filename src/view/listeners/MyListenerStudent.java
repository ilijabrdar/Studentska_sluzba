package view.listeners;

import view.dialogs.NewStudentDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MyListenerStudent implements KeyListener, ActionListener {


    private JButton btnOK;
    private JRadioButton r_btn_b;
    private JRadioButton r_btn_s;

    private int flag_ime = 0;
    private int flag_prezime = 0;
    private int flag_prosek = 0;
    private int flag_adresa = 0;
    private int flag_telefon = 0;
    private int flag_email = 0;
    private int flag_datum_r = 0;
    private int flag_datum_u = 0;
    private int flag_indeks = 0;

    private  int flag_btnOK = 0;

    public MyListenerStudent(JButton btnOK, JRadioButton r_btn_b, JRadioButton r_btn_s, int flag) {
        this.btnOK = btnOK;
        this.r_btn_b = r_btn_b;
        this.r_btn_s = r_btn_s;

        flag_ime=flag;
        flag_prezime=flag;
        flag_prosek=flag;
        flag_adresa=flag;
        flag_telefon=flag;
        flag_email=flag;
        flag_datum_u=flag;
        flag_datum_r=flag;
        flag_indeks=flag;

    }

    public int getFlag_btnOK() {
        return flag_btnOK;
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

        if (j_txt.getName().equalsIgnoreCase("prosek")) //kontrola da moze biti ili '/' ili 6-10 opseg, dvostruka preciznost mora
            provera_prosek(text, j_txt);

        else if (j_txt.getName().equalsIgnoreCase("ime")) //mora poceti veikim slovom
            provera_ime(text, j_txt);

        else if (j_txt.getName().equalsIgnoreCase("prezime"))//mora poceti veikim slovom
            provera_prezime(text,j_txt);

        else if (j_txt.getName().equalsIgnoreCase("datum rodjenja")) //ne sme biti posle trenutnog datuma, ostale kontrole se prebacuju u dijalog preko Exceptiona, zabrana neispravnih datuma tipa 00.01.0000. se postize try/catch blokom
            provera_datum_rodjenja(text, j_txt);

        else if (j_txt.getName().equalsIgnoreCase("datum upisa")) //ne sme biti posle trenutnog datuma, ostale kontrole se prebacuju u dijalog preko Exceptiona, zabrana neispravnih datuma tipa 00.01.0000. se postize try/catch blokom
            provera_datum_upisa(text, j_txt);

        else if (j_txt.getName().equalsIgnoreCase("telefon")) //ne sme biti prazan, korisnik sam odlucuje koji karakter da stavi ako objekat nema telefon
            provera_telefon(text, j_txt);

        else if (j_txt.getName().equalsIgnoreCase("email"))//ne sme biti prazan, korisnik sam odlucuje koji karakter da stavi ako objekat nema email
            provera_email(text, j_txt);

        else if (j_txt.getName().equalsIgnoreCase("adresa"))//ne sme biti prazan, korisnik sam odlucuje koji karakter da stavi ako objekat nema adresu
            provera_adresa(text, j_txt);

        else if (j_txt.getName().equalsIgnoreCase("indeks")) //kontrola da mora biti u formatu SS XXX/YYYY i da godina indeksa ne sme biti posle trenutne
            provera_indeks(text, j_txt);


        if (flag_indeks==1 && flag_adresa==1 && flag_email==1 && flag_telefon==1 && flag_datum_u==1 && flag_datum_r==1 && flag_prezime==1 && flag_ime==1 && flag_prosek==1) {
            if (r_btn_s.isSelected() || r_btn_b.isSelected())
                btnOK.setEnabled(true);
            else
                flag_btnOK = 1;
        }

    }

    private void provera_indeks(String text, JTextField j_txt) {
        if (!text.trim().matches("[A-Z][A-Z] [0-9]?[0-9]?[0-9]/[0-9][0-9][0-9][0-9]")) {
            btnOK.setEnabled(false);
            j_txt.setBackground(Color.pink);
            flag_indeks=0;
        } else {
            try {
                String[] splits = text.trim().split("/");
                LocalDate trenutno = LocalDate.now();
                if (Integer.parseInt(splits[1]) > trenutno.getYear()) { //ne moze godina indexa biti veca od trenutne
                    btnOK.setEnabled(false);
                    j_txt.setBackground(Color.pink);
                    flag_indeks = 0;
                } else {
                    flag_indeks=1;
                    j_txt.setBackground(Color.white);
                }
            } catch (Exception ee) {

            }
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

    private void provera_datum_upisa(String text, JTextField j_txt) {
        if (!text.matches("[0-3][0-9]\\.[0-9][0-9]\\.[0-9][0-9][0-9][0-9]\\.")) {
            btnOK.setEnabled(false);
            j_txt.setBackground(Color.pink);
            flag_datum_u=0;
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
            try {
                LocalDate datum_upisa = LocalDate.parse(text, formatter);

                LocalDate trenutni = LocalDate.now();
                if (datum_upisa.compareTo(trenutni) > 0) { //datum_upisa ne sme biti veci od trenutnog datuma,a mogu biti isti datumi i zato se ne koristi .isBefore(trenutni) vec compare to
                    btnOK.setEnabled(false);
                    j_txt.setBackground(Color.pink);
                    flag_datum_u = 0;
                } else {
                    j_txt.setBackground(Color.white);
                    flag_datum_u = 1;
                }

            } catch (Exception ee) {

            }

        }
    }

    private void provera_datum_rodjenja(String text, JTextField j_txt) {
        if (!text.matches("[0-3][0-9]\\.[0-9][0-9]\\.[0-9][0-9][0-9][0-9]\\.")) {
            btnOK.setEnabled(false);
            j_txt.setBackground(Color.pink);
            flag_datum_r = 0;
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
            try {
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

    private void provera_prosek(String text, JTextField j_txt) {
        if (!text.matches("/|([0-9]?[0-9]\\.[0-9][0-9])")) {
            btnOK.setEnabled(false);
            flag_prosek=0;
            j_txt.setBackground(Color.pink);
        } else {
            if (text.matches("[0-9]?[0-9]\\.[0-9][0-9]")) {
                double br = Double.parseDouble(text);
                if (br<6 || br>10) {
                    btnOK.setEnabled(false);
                    flag_prosek=0;
                    j_txt.setBackground(Color.pink);
                }
                else {
                    j_txt.setBackground(Color.white);
                    flag_prosek = 1;
                }
            }
            else if (text.matches("/")) {
                j_txt.setBackground(Color.white);
                flag_prosek = 1;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (flag_btnOK==1)
            btnOK.setEnabled(true);
    }

}
