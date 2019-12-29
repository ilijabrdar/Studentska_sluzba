package view.listeners;

import view.components.MainFrame;
import view.components.TabbedPane;
import view.dialogs.NewProfesorDialog;
import view.dialogs.NewStudentDialog;
import view.dialogs.NewSubjectDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBtnActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        //ZAVISI KOJI JE TAB SELEKTOVAN
        if(TabbedPane.getInstance().isRunning("Predmeti")) {
            NewSubjectDialog sd = new  NewSubjectDialog(MainFrame.getInstance(), "Novi predmet", true, 0, 0);
            sd.setVisible(true);
        }
        else if (TabbedPane.getInstance().isRunning("Studenti")) {
            NewStudentDialog student_dialog = new NewStudentDialog(MainFrame.getInstance(), "Novi predmet", true);
            student_dialog.setVisible(true);
        }

        else if (TabbedPane.getInstance().isRunning("Profesori")) {
            NewProfesorDialog profesor_dialog = new NewProfesorDialog(MainFrame.getInstance(), "Novi profesor", true);
            profesor_dialog.setVisible(true);
        }
    }
}
