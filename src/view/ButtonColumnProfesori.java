package view;

import model.BazaPredmeta;
import model.Predmet;
import model.Profesor;
import model.Student;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonColumnProfesori extends ButtonColumn {

    public ButtonColumnProfesori(JTable table, int column, String msg) {
        super(table, column, msg);
        addEditinAction();
        this.isEditorActive = false;
        dodajListerLista();
    }

    private void addEditinAction() {
        this.editorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();


                int row = SubjectTable.getSubjectTable().getSelectedRow();
                Predmet predmet = BazaPredmeta.getBazaPredmeta().getRow(row);
                for (Profesor p : predmet.getProfs()) {
                    DLM.addElement(p.getLicna());
                }

                list.setModel(DLM);

                dialog.setVisible(true);

            }
        });
    }

    public void dodajListerLista() {
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                //System.out.println("Selektovan je profa sa licnom " + list.getSelectedValue());
            }
        });
    }
}
