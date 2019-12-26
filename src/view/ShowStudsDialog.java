package view;

import controller.SubjectController;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowStudsDialog extends ShowProfsStudsDialog{

    public ShowStudsDialog(Frame owner, String title, boolean modal) {

        super(owner,title,modal);
        initList();
        addActions();
        validateList();
    }

    private void validateList() {
        if(DLM.size() == 0)
            JOptionPane.showMessageDialog(MainFrame.getInsance(), "Ne postoje unosi na predmetu.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
        else getDialog().setVisible(true);
    }

    private void initList() {
        int selectedRow = SubjectTable.getSubjectTable().getSelectedRow();
        Predmet predmet = SubjectController.getSubjectController().getSelectedSubjectByID();
        for (Student s : predmet.getStudenti()) {
            DLM.addElement(s.getIndex());
        }
    }

    private void addActions() {
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete.setEnabled(true);
                int selectedRow = SubjectTable.getSubjectTable().getSelectedRow();
                int index_list = list.getSelectedIndex();
                if (index_list!= -1) {
                    String index = (String) DLM.remove(index_list);
                    Predmet predmet = BazaPredmeta.getBazaPredmeta().getRow(selectedRow);
                    predmet.getStudenti().remove(BazaStudenata.getBazaStudenata().getStudentPrekoIndeksa(index));
                }
                if (DLM.size() == 0) getDialog().dispose();
            }
        });
    }
}
