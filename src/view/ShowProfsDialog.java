package view;

import controller.SubjectController;
import model.BazaPredmeta;
import model.BazaProfesora;
import model.Predmet;
import model.Profesor;

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
            JOptionPane.showMessageDialog(MainFrame.getInsance(), "Ne postoje unosi na predmetu.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
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
