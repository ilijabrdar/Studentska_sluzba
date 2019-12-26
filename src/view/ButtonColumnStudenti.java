package view;

import model.BazaPredmeta;
import model.Predmet;
import model.Student;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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

                dialog = new ShowStudsDialog(MainFrame.getInsance(),msg,true);

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
