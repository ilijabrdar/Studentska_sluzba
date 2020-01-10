package view.listeners;

import view.dialogs.NewStudentDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//TODO: srediti klasu malo
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




        if (j_txt.getName().equalsIgnoreCase("prosek")) {
            if (!text.matches("/|([0-9]?[0-9]\\.[0-9][0-9])")) {
                btnOK.setEnabled(false);
                flag_prosek=0; //ako dobro unesem pa izbrisem i dalje ce biti ukljucen btnOK sto ne treba
                j_txt.setBackground(Color.pink);
            } else {
                if (text.matches("[0-9]?[0-9]\\.[0-9][0-9]")) {
                    double br = Double.parseDouble(text);
                    if (br<6 || br>10) {
                        btnOK.setEnabled(false);
                        flag_prosek=0; //ako dobro unesem pa izbrisem i dalje ce biti ukljucen btnOK sto ne treba
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
        else if (j_txt.getName().equalsIgnoreCase("ime")) {
            if (!text.matches("[A-ZČĆŽŠĐ].+")) {
                btnOK.setEnabled(false);
                flag_ime=0;
                j_txt.setBackground(Color.pink);
            } else {
                j_txt.setBackground(Color.white);
                flag_ime=1;
            }
        }

        else if (j_txt.getName().equalsIgnoreCase("prezime")) {
            if (!text.matches("[A-ZČĆŽŠĐ].+")) {
                btnOK.setEnabled(false);
                flag_prezime=0;
                j_txt.setBackground(Color.pink);
            } else {
                j_txt.setBackground(Color.white);
                flag_prezime=1;
            }
        }
        else if (j_txt.getName().equalsIgnoreCase("datum rodjenja")) {
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
                    } else if (!NewStudentDialog.getTxt_datum_upisa().getText().equalsIgnoreCase("")) {
                        LocalDate datum_upisa = LocalDate.parse(NewStudentDialog.getTxt_datum_upisa().getText(), formatter);

                        if (!datum_rodjenja.isBefore(datum_upisa)) {
                            btnOK.setEnabled(false);
                            j_txt.setBackground(Color.pink);
                            flag_datum_r = 0;
                        } else {
                            j_txt.setBackground(Color.white);
                            flag_datum_r = 1;
                            
                            flag_datum_u = 1; //TODO: da li ovo stvarno popravlja stvari?
                            NewStudentDialog.getTxt_datum_upisa().setBackground(Color.white);
                        }
                    }
                    else {
                        j_txt.setBackground(Color.white);
                        flag_datum_r = 1;
                    }
                } catch (Exception ee) {

                }
            }

        }
        else if (j_txt.getName().equalsIgnoreCase("datum upisa")) {//TODO: proveri datume
            if (!text.matches("[0-3][0-9]\\.[0-9][0-9]\\.[0-9][0-9][0-9][0-9]\\.")) {
                btnOK.setEnabled(false);
                j_txt.setBackground(Color.pink);
                flag_datum_u=0;
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
                try {
                    LocalDate datum_upisa = LocalDate.parse(text, formatter);

                    LocalDate trenutni = LocalDate.now();
                    if (!datum_upisa.isBefore(trenutni)) { //ako je datum_upisa veci od trenutnog datuma onda false i izlazak iz petlje
                        btnOK.setEnabled(false);
                        j_txt.setBackground(Color.pink);
                        flag_datum_u = 0;
                    } else if (!NewStudentDialog.getTxt_datum_rodjenja().getText().equalsIgnoreCase("")) { //ako je datum_upisa manji od trenutnog i txt_datum_rodjenja nije "" onda dalje provere
                        LocalDate datum_rodjenja = LocalDate.parse(NewStudentDialog.getTxt_datum_rodjenja().getText(), formatter);

                        if (!datum_rodjenja.isBefore(datum_upisa)) {
                            btnOK.setEnabled(false);
                            j_txt.setBackground(Color.pink);
                            flag_datum_u = 0;
                        } else {
                            j_txt.setBackground(Color.white);
                            flag_datum_u = 1;

                            NewStudentDialog.getTxt_datum_rodjenja().setBackground(Color.white);
                            flag_datum_r = 1;
                        }
                    }
                    else {
                        j_txt.setBackground(Color.white);
                        flag_datum_u = 1;
                    }

                } catch (Exception ee) {

                }

            }
        }
//        else if (j_txt.getName().equalsIgnoreCase("datum upisa")) {
//            if (!text.matches("[0-3][0-9]\\.[0-9][0-9]\\.[0-9][0-9][0-9][0-9]\\.")) {
//                btnOK.setEnabled(false);
//                j_txt.setBackground(Color.pink);
//                flag_datum_u=0;
//            } else {
//                boolean r1 = pre_trenutne(text);
//                boolean r2 = posle_rodjenja(text);
//                boolean r3 = pre_godine_indexa(text);
//
//                if (r1 && r2 && r3) {
//                    j_txt.setBackground(Color.white);
//                        flag_datum_u = 1;
//                }
//                else {
//                    btnOK.setEnabled(false);
//                    j_txt.setBackground(Color.pink);
//                    flag_datum_u = 0;
//                }
//            }
//        }
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
        else if (j_txt.getName().equalsIgnoreCase("indeks")) {
            if (!text.trim().matches("[A-Z][A-Z] [0-9]?[0-9]?[0-9]/[0-9][0-9][0-9][0-9]")) {
                btnOK.setEnabled(false);
                j_txt.setBackground(Color.pink);
                flag_indeks=0;
            } else {
                try {
                    String[] splits = text.trim().split("/");
                    String[] splits_datum_upisa = NewStudentDialog.getTxt_datum_upisa().getText().split("\\.");
                    LocalDate trenutno = LocalDate.now();
                    if (Integer.parseInt(splits[1]) > trenutno.getYear()) { //ne moze godina indexa biti veca od trenutne ili manja od godine upisa
                        btnOK.setEnabled(false);
                        j_txt.setBackground(Color.pink);
                        flag_indeks = 0;
                    } else {
                        if (NewStudentDialog.getTxt_datum_upisa().getText().equalsIgnoreCase("")) {
                            flag_indeks = 1;
                            j_txt.setBackground(Color.white);
                        }
                        else {
                            if (Integer.parseInt(splits[1]) < Integer.parseInt(splits_datum_upisa[2])) {
                                btnOK.setEnabled(false);
                                j_txt.setBackground(Color.pink);
                                flag_indeks = 0;
                            }
                            else {
                                flag_indeks = 1;
                                j_txt.setBackground(Color.white);
                            }
                        }
                    }
                } catch (Exception ee) {

                }
            }
        }
        if (flag_indeks==1 && flag_adresa==1 && flag_email==1 && flag_telefon==1 && flag_datum_u==1 && flag_datum_r==1 && flag_prezime==1 && flag_ime==1 && flag_prosek==1) {
            if (r_btn_s.isSelected() || r_btn_b.isSelected())
                btnOK.setEnabled(true);
            else
                flag_btnOK = 1;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (flag_btnOK==1)
            btnOK.setEnabled(true);
    }

}
