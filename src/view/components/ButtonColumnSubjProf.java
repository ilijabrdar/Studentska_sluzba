package view.components;

import view.dialogs.ShowProfsDialog;
import view.dialogs.ShowSubjProfDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonColumnSubjProf extends ButtonColumn {

    public ButtonColumnSubjProf(JTable table, int column, String msg) {
        super(table, column, msg);
        addEditingAction(msg);
        this.isEditorActive = false;
    }

    private void addEditingAction(String msg) {
        this.editorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
                dialog = new ShowSubjProfDialog(MainFrame.getInstance(), msg, true);
            }
        });
    }

}
