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
        Predmet predmet = SubjectController.getSubjectController().getSelectedSubjectByID(); //dobijamo trenutno selektovani predmet
        for (Student s : predmet.getStudenti()) { //svakog studenta tog predmeta dodajemo u DLM
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
                if (index_list!= -1) { //brisanje se desava samo ukoliko je neki student selektovan
                    String ret = (String) DLM.remove(index_list);
                    String [] splits = ret.split("\\s"); //posto je sadrzaj DLM elementa index studenta sa imenom i
                                                                // prezimenom moramo splitovati po ' ' i uzeti prvi clan kako bismo uzeli index studenta za brisanje sa liste predmeta
                    String index = splits[0] + " " + splits[1]; //splits[0] je XX, splits[1] je XXX/YYYY i razmak izmedju da se sacini index

                    Predmet predmet = SubjectController.getSubjectController().getSelectedSubjectByID(); //uzimanje predmeta za brisanje
                    predmet.getStudenti().remove(BazaStudenata.getBazaStudenata().getStudentPrekoIndeksa(index));

                    Student s = BazaStudenata.getBazaStudenata().getStudentPrekoIndeksa(index);
                    s.getPredmeti().remove(predmet); //brisanje tog predmeta kod studenta

                }
                if (DLM.size() == 0) getDialog().dispose();
            }
        });
    }
}
