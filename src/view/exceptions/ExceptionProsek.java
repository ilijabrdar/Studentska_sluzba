package view.exceptions;

import javax.swing.*;

public class ExceptionProsek extends Exception {
    public ExceptionProsek(JDialog dialog, JComboBox combo_trenutna_godina_studija, double prosek) {
        int trenutna_godina;
        if ( combo_trenutna_godina_studija.getSelectedIndex()==0)
            trenutna_godina = 1;
        else if (combo_trenutna_godina_studija.getSelectedIndex()==1)
            trenutna_godina = 2;

        else if (combo_trenutna_godina_studija.getSelectedIndex()==2)
            trenutna_godina = 3;
        else
            trenutna_godina=4;

        if (trenutna_godina==1 && prosek!=0)
            JOptionPane.showMessageDialog(dialog, "Studenti prve godine nemaju prosek (unesite /).","Greška", JOptionPane.ERROR_MESSAGE);
        else if (trenutna_godina!=1 && prosek==0)
            JOptionPane.showMessageDialog(dialog, "Za studente visih godina morate uneti prosek.","Greška", JOptionPane.ERROR_MESSAGE);

    }
}
