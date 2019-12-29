package view.listeners;

import view.components.MainFrame;
import view.components.TabbedPane;
import view.dialogs.EditProfesorDialog;
import view.dialogs.EditStudentDialog;
import view.dialogs.EditSubjectDialog;
import view.tables.ProfesorTable;
import view.tables.StudentsTable;
import view.tables.SubjectTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditBtnActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        int row_subject = SubjectTable.getSubjectTable().getSelectedRow();
        int row_student = StudentsTable.getStudentsTable().getSelectedRow();
        int row_profesor = ProfesorTable.getProfesorTable().getSelectedRow();

        if(TabbedPane.getInstance().isRunning("Predmeti") &&  row_subject >= 0) {
            EditSubjectDialog ed = new EditSubjectDialog(MainFrame.getInstance(), "Izmena predmeta", true);
            ed.setVisible(true);
        }
        else if (TabbedPane.getInstance().isRunning("Predmeti") && row_subject == -1) {
            JOptionPane.showMessageDialog(MainFrame.getInstance(),
                    "Pre izmene selektujete predmet.", "Greška", JOptionPane.ERROR_MESSAGE);
        }

        else if (TabbedPane.getInstance().isRunning("Studenti") &&  row_student >= 0) {
            EditStudentDialog edit_student = new EditStudentDialog(MainFrame.getInstance(), "Izmena studenta", true);
            edit_student.setVisible(true);
        }
        else if (TabbedPane.getInstance().isRunning("Studenti") && row_student == -1) {
            JOptionPane.showMessageDialog(MainFrame.getInstance(),
                    "Pre izmene selektujete studenta.", "Greška", JOptionPane.ERROR_MESSAGE);
        }

        else if (TabbedPane.getInstance().isRunning("Profesori") &&  row_profesor >= 0) {
            EditProfesorDialog edit_profesor = new EditProfesorDialog(MainFrame.getInstance(), "Izmena profesora", true);
            edit_profesor.setVisible(true);
        }
        else if (TabbedPane.getInstance().isRunning("Profesori") && row_profesor == -1) {
            JOptionPane.showMessageDialog(MainFrame.getInstance(),
                    "Pre izmene selektujete profesora.", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }
}
