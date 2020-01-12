package view.listeners;

import controller.ProfesorController;
import controller.StudentController;
import controller.SubjectController;
import view.components.MainFrame;
import view.components.TabbedPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchBtnActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton comp = (JButton) e.getSource();
        JPanel pane = (JPanel) comp.getParent();
        JTextField search = (JTextField) pane.getComponent(0);

        if(TabbedPane.getInstance().isRunning("Predmeti")) {
            SubjectController sc = SubjectController.getSubjectController();
            if(search.getText().trim().length() !=0)

                try {
                    sc.findSubject(search.getText());
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(MainFrame.getInstance(), err.getMessage(),"Pretraga predmeta", JOptionPane.ERROR_MESSAGE);
                }

            else
                sc.retrieveTable();
        }
        else if(TabbedPane.getInstance().isRunning("Profesori")) {
            ProfesorController pc = ProfesorController.getInstance();
            if(search.getText().trim().length() != 0)
                try {
                    pc.findProfessor(search.getText());
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(MainFrame.getInstance(), err.getMessage(),"Pretraga profesora", JOptionPane.ERROR_MESSAGE);
                }
            else
                pc.retrieveTable();
        }
        else if (TabbedPane.getInstance().isRunning("Studenti")) {
            StudentController sc = StudentController.getInstance();
            if (search.getText().trim().length()!=0)
                try {
                    sc.findStudent(search.getText());
                } catch (Exception error) {
                    JOptionPane.showMessageDialog(MainFrame.getInstance(), error.getMessage(),"Pretraga studenata", JOptionPane.ERROR_MESSAGE);
                }
            else
                sc.undo_search(); //povratak na pocetno stanje tabele ukoliko je prazno polje za unos

        }
    }
}
