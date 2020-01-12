package view.dialogs;

import model.bazePodataka.BazaPredmeta;
import model.bazePodataka.BazaStudenata;
import model.entiteti.Predmet;
import model.entiteti.Student;
import view.components.MainFrame;
import view.dialogs.ShowProfsStudsDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowSubjsOfStudDialog extends JDialog {

    protected JButton back = null;
    protected DefaultListModel DLM;
    protected JList list;

    public ShowSubjsOfStudDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);

        this.DLM = new DefaultListModel();
        this.list = new JList();
        this.list.setModel(DLM);


        this.setTitle("Prikaz predmeta");

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension scrSize = kit.getScreenSize();
        int height = scrSize.height;
        int width = scrSize.width;

        this.setSize(width / 5, height / 4);
        this.setLocationRelativeTo(MainFrame.getInstance());
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        JPanel closingPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        back = new JButton("Nazad");
        closingPanel.add(back);

        this.add(closingPanel, BorderLayout.SOUTH);

        JScrollPane sp = new JScrollPane();
        sp.setViewportView(list);
        this.add(sp, BorderLayout.CENTER);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getDialog().dispose();
                MainFrame.getInstance().updateTable();
            }
        });

    }

    public boolean initList() {
        boolean nasao = false;
        BazaStudenata.getBazaStudenata().updateArrayList();
        Student selected = BazaStudenata.getBazaStudenata().getRow(MainFrame.getInstance().getStudent_table().getSelectedRow());
        for (Predmet p : BazaPredmeta.getBazaPredmeta().getSubjects()) {
            for (Student s : p.getStudenti()) {
                if (s.getIndex().equalsIgnoreCase(selected.getIndex())) {
                    DLM.addElement(p.getSifra() + "   " + p.getNaziv());
                    nasao = true;
                }
            }
        }
        return nasao;
    }

    public JDialog getDialog() {return this;}

    public DefaultListModel getDLM() {
        return DLM;
    }
}
