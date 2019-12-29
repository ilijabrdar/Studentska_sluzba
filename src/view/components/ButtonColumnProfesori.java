package view.components;

import view.dialogs.ShowProfsDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonColumnProfesori extends ButtonColumn {

    public ButtonColumnProfesori(JTable table, int column, String msg) {
        super(table, column, msg);
        addEditingAction(msg);
        this.isEditorActive = false;
    }

    private void addEditingAction(String msg) {
        this.editorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
                dialog = new ShowProfsDialog(MainFrame.getInstance(), msg, true);
            }
        });
    }
}
