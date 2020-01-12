package view.dialogs;

import controller.SubjectController;
import model.bazePodataka.BazaProfesora;
import model.entiteti.Predmet;
import model.entiteti.Profesor;
import view.components.MainFrame;
import view.tables.ProfesorTable;
import view.tables.SubjectTable;

import javax.swing.*;
import java.awt.*;
// ZA PRIKAZ PREDMETA KOJE PROFESOR PREDAJE
public class ShowSubjProfDialog extends JDialog {

    private DefaultListModel DLM;
    private JList list;

    public ShowSubjProfDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);

        this.DLM = new DefaultListModel();
        this.list = new JList();
        this.list.setModel(DLM);

        this.setSize(200, 250);
        this.setLocationRelativeTo(MainFrame.getInstance());
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        JScrollPane sp = new JScrollPane();
        sp.setViewportView(list);
        this.add(sp, BorderLayout.CENTER);

        initList();
        validateList();
    }

    public JDialog getDialog() {return this;}

    private void initList() {
        BazaProfesora.getBazaProfesora().updateArrayList();
        int index = ProfesorTable.getProfesorTable().getSelectedRow();
        Profesor profa = BazaProfesora.getBazaProfesora().getRow(index);
        for (Predmet p : profa.getPredmeti()) {
            DLM.addElement(p.getSifra() + " " + p.getNaziv());
        }
    }

    private void validateList() {
        if(DLM.size() == 0)
            JOptionPane.showMessageDialog(MainFrame.getInstance(), "Ne postoje unosi predmeta.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
        else getDialog().setVisible(true);
    }
}
