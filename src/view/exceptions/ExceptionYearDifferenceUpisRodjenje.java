package view.exceptions;

import javax.swing.*;

public class ExceptionYearDifferenceUpisRodjenje extends Exception {
    public ExceptionYearDifferenceUpisRodjenje(JDialog dialog) {
        JOptionPane.showMessageDialog(dialog,"Godina rodjenja i godina upisa se moraju razlikovati bar za 10.", "Greška", JOptionPane.ERROR_MESSAGE);
    }
}
