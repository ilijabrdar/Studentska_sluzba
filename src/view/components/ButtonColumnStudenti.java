package view.components;

import view.dialogs.ShowStudsDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonColumnStudenti extends ButtonColumn {
    public ButtonColumnStudenti(JTable table, int column, String msg) {
        super(table, column, msg);
        addEditingAction(msg);
        this.isEditorActive = false;
        //dodajListerLista();
    }

    private void addEditingAction(String msg) {
        this.editorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();

                dialog = new ShowStudsDialog(MainFrame.getInstance(),msg,true);

//                int row = MainFrame.getInsance().getStable().getSelectedRow();
//                Predmet selected_predmet = BazaPredmeta.getBazaPredmeta().getRow(row);
//
//                for (Student s : selected_predmet.getStudenti()) {
//                    //DLM.addElement(s.getIndex()); TODO David: Popraviti
//                }
//
//                //list.setModel(DLM); //TODO David: popraviti
//
//                dialog.setVisible(true);

            }
        });
    }
/*
    public void dodajListerLista() {
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                System.out.println("Selektovan je student sa indexom " + list.getSelectedValue());
            }
        });
    }
    */ //TODO David: Popraviti

}
