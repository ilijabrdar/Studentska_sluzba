package view.components;

import model.bazePodataka.BazaStudenata;
import model.entiteti.Student;
import view.dialogs.ShowSubjsOfStudDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonColumnPredmetiStudenta extends ButtonColumn {
    public ButtonColumnPredmetiStudenta(JTable table, int column, String msg) {
        super(table, column, msg);
        addEditingAction(msg);
        this.isEditorActive = false;
    }

    private void addEditingAction(String msg) {
        this.editorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();

                ShowSubjsOfStudDialog dialog = new ShowSubjsOfStudDialog(MainFrame.getInstance(), msg, true);
                boolean ret = dialog.initList(); //vraca false nije pronadjen nijedan predmet studenta

                if (!ret)
                    JOptionPane.showMessageDialog(MainFrame.getInstance(),"Ne postoje predmeti za tog studenta.");
                else
                    dialog.getDialog().setVisible(true);

            }
        });
    }
}
