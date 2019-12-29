package view.listeners;

import controller.ProfesorController;
import controller.StudentController;
import controller.SubjectController;
import model.entiteti.Profesor;
import view.components.MainFrame;
import view.components.TabbedPane;
import view.tables.ProfesorTable;
import view.tables.StudentsTable;
import view.tables.SubjectTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteBtnActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        int row_subject = SubjectTable.getSubjectTable().getSelectedRow();
        int row_student = StudentsTable.getStudentsTable().getSelectedRow();
        int row_prof = ProfesorTable.getProfesorTable().getSelectedRow();

        if(TabbedPane.getInstance().isRunning("Predmeti") && row_subject >= 0) {
            int code = JOptionPane.showConfirmDialog(MainFrame.getInstance(), "Da li ste sigurni da želite da obrišete predmet?",
                    "Brisanje predmeta", JOptionPane.YES_NO_OPTION);
            if(code == JOptionPane.YES_OPTION) {
                SubjectController sc = SubjectController.getSubjectController();
                sc.removeSubject();
            }
        }
        else if (TabbedPane.getInstance().isRunning("Predmeti") && row_subject == -1) { //row_subject=-1 ako nijedan red nije selektovan
            JOptionPane.showMessageDialog(MainFrame.getInstance(),
                    "Pre brisanja selektujete predmet.", "Greška", JOptionPane.ERROR_MESSAGE);
        }

        else if (TabbedPane.getInstance().isRunning("Studenti") && row_student >= 0) {
            int code = JOptionPane.showConfirmDialog(MainFrame.getInstance(), "Da li ste sigurni da želite da obrišete studenta?",
                    "Brisanje studenta", JOptionPane.YES_NO_OPTION);
            if(code == JOptionPane.YES_OPTION) {
                StudentController sc = StudentController.getInstance();
                sc.izbrisiStudenta(row_student);
            }
        }
        else if (TabbedPane.getInstance().isRunning("Studenti") && row_student == -1) {
            JOptionPane.showMessageDialog(MainFrame.getInstance(),
                    "Pre brisanja selektujete studenta.", "Greška", JOptionPane.ERROR_MESSAGE);
        }
        else if (TabbedPane.getInstance().isRunning("Profesori") && row_prof >= 0) {
            int code = JOptionPane.showConfirmDialog(MainFrame.getInstance(), "Da li ste sigurni da želite da obrišete profesora?",
                    "Brisanje profesora", JOptionPane.YES_NO_OPTION);
            if(code == JOptionPane.YES_OPTION) {
                ProfesorController pc = ProfesorController.getInstance();
                Profesor p = ProfesorController.getInstance().getProfessor(row_prof);
                pc.izbrisiProfesora(row_prof);
                SubjectController.getSubjectController().removeProfFromSubj(p.getLicna());
            }
        }
        else {
            JOptionPane.showMessageDialog(MainFrame.getInstance(),
                    "Pre brisanja selektujete profesora.", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }
}
