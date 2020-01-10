package view.exceptions;

import javax.swing.*;

public class ExceptionYearDifferenceIndeksUpis extends Exception{
    public ExceptionYearDifferenceIndeksUpis(JDialog dialog) {
        JOptionPane.showMessageDialog(dialog,"Godina indexa mora biti veca ili jednaka godini upisa.", "Greška", JOptionPane.ERROR_MESSAGE);
    }
}
