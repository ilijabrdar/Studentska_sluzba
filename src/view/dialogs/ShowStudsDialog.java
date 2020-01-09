package view.dialogs;

import controller.SubjectController;
import model.bazePodataka.BazaPredmeta;
import model.bazePodataka.BazaStudenata;
import model.entiteti.Predmet;
import model.entiteti.Student;
import view.components.MainFrame;
import view.tables.SubjectTable;

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
            JOptionPane.showMessageDialog(MainFrame.getInstance(), "Ne postoje unosi na predmetu.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
        else getDialog().setVisible(true);
    }

    private void initList() {
        Predmet predmet = SubjectController.getSubjectController().getSelectedSubjectByID();
        for (Student s : predmet.getStudenti()) {
            DLM.addElement(s.getIndex() + " " + s.getIme() + " " + s.getPrezime());
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
                    String ret = (String) DLM.remove(index_list);
                    String [] splits = ret.split("\\s");
                    String index = splits[0] + " " + splits[1];
                    Predmet predmet = BazaPredmeta.getBazaPredmeta().getRow(selectedRow);
                    predmet.getStudenti().remove(BazaStudenata.getBazaStudenata().getStudentPrekoIndeksa(index));
                }
                if (DLM.size() == 0) getDialog().dispose();
            }
        });
    }
}
