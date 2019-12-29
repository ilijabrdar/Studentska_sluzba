package view.listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RadioButtonListener implements ActionListener {

    private JButton btnOK = null;
    private JRadioButton r_btn_b = null;
    private JRadioButton r_btn_s = null;

    public RadioButtonListener(JButton btnOK, JRadioButton r_btn_b, JRadioButton r_btn_s) {
        this.btnOK = btnOK;
        this.r_btn_b = r_btn_b;
        this.r_btn_s = r_btn_s;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        btnOK.setEnabled(true);
    }
}
