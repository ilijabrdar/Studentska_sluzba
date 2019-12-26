package view.dialogs;

import controller.ProfesorController;
import controller.SubjectController;
import model.bazePodataka.BazaPredmeta;
import model.bazePodataka.BazaProfesora;
import model.entiteti.Predmet;
import model.entiteti.Profesor;
import view.components.MainFrame;
import view.tables.SubjectTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowProfsDialog extends ShowProfsStudsDialog {


    public ShowProfsDialog(Frame owner, String title, boolean modal) {

        super(owner, title, modal);
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
        //fireEditingStopped(); ???????????????
        int selectedRow = SubjectTable.getSubjectTable().getSelectedRow();
        Predmet predmet = SubjectController.getSubjectController().getSelectedSubjectByID();
        for (Profesor p : predmet.getProfs()) {
                DLM.addElement(p.getLicna());
        }
    }

    private void addActions() {
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    delete.setEnabled(true);
                    int selectedRow = SubjectTable.getSubjectTable().getSelectedRow();
                    String idProf = (String) DLM.remove(list.getSelectedIndex());
                    Predmet predmet = BazaPredmeta.getBazaPredmeta().getRow(selectedRow);
                    predmet.getProfs().remove(BazaProfesora.getBazaProfesora().getProfesor(idProf));
                if (DLM.size() == 0) getDialog().dispose();
            }
        });
    }
}
