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
        Predmet predmet = SubjectController.getSubjectController().getSelectedSubjectByID();
        for (Profesor p : predmet.getProfs()) {
            System.out.println(p);
            System.out.println();
            DLM.addElement(p.getLicna() + " " + p.getPrezime() + " " + p.getIme());
            //DLM.addElement(p.getLicna());
        }
    }

    private void addActions() {
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete.setEnabled(true);

                Predmet selectedSubj = SubjectController.getSubjectController().getSelectedSubjectByID();
                try {

                    String content = (String) DLM.remove(list.getSelectedIndex());
                    String[] splits = content.split(" ");
                    String ID = splits[0];

                    //String ID = (String) DLM.remove(list.getSelectedIndex());
                    Profesor profa = BazaProfesora.getBazaProfesora().getProfesor(ID);
                    profa.getPredmeti().remove(selectedSubj);
                    selectedSubj.removeByID(ID);
                    System.out.println(selectedSubj.getProfs());
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(getDialog(), "Nema selektovanih profesora.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
                if (DLM.size() == 0) getDialog().dispose();
            }
        });
    }
}
