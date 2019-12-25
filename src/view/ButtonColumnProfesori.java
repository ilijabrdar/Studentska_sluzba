package view;

import model.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonColumnProfesori extends ButtonColumn {

    public ButtonColumnProfesori(JTable table, int column, String msg) {
        super(table, column, msg);
        //initList();
        addEditingAction(msg);
        this.isEditorActive = false; //??????????????????
       // dodajListerLista(); ?????????????????????????
    }

    private void addEditingAction(String msg) {
        this.editorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
                dialog = new ShowProfsDialog(MainFrame.getInsance(), msg, true);
                //dialog.setVisible(true);
            }
        });
    }

    /*
    public void dodajListerLista() {
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                //System.out.println("Selektovan je profa sa licnom " + list.getSelectedValue());
            }
        });
    } */
}
