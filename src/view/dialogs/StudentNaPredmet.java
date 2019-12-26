package view.dialogs;

import controller.SubjectController;
import view.components.BackgroundPanel;
import view.components.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentNaPredmet extends JDialog{

    //TODO: serijalizacija

    protected JButton btnOK = null;
    protected JButton btnCANCEL = null;

    protected JTextField txt_indeks = null;


    public StudentNaPredmet(Frame parent, String title, boolean modal) {
        super(parent,title,modal);

        BackgroundPanel bigPanel = new BackgroundPanel();

        setSize(550,150);
        setLocationRelativeTo(parent);
        setResizable(false);
        this.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel indeks = new JLabel("Broj indeksa: ");
        txt_indeks = new JTextField();
        txt_indeks.setName("Indeks"); //TODO: key listener dodaj
        txt_indeks.setPreferredSize(new Dimension(100,28));

        panel.setOpaque(false);
        panel.add(indeks);
        panel.add(txt_indeks);

        bigPanel.add(panel);
        this.add(bigPanel,BorderLayout.CENTER);

        btnOK= new JButton("Sačuvaj");
        btnCANCEL = new JButton("Odustani");

        JPanel panel_button = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel_button.setBackground(Color.lightGray);
        panel_button.add(btnOK);
        panel_button.add(btnCANCEL);

        this.add(panel_button,BorderLayout.SOUTH);

        addActionsOK();
        addActionCancel();

    }

    public void addActionsOK() {
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String indeks = txt_indeks.getText();
                SubjectController sc = SubjectController.getSubjectController();
                if (!sc.addStudentToSubject(indeks))
                    JOptionPane.showMessageDialog(MainFrame.getInstance(), "Uneti student ne postoji ili je predmet vec dodeljen studentu.",
                            "Dodavanje studenta na predmet", JOptionPane.ERROR_MESSAGE);
                else
                    dispose();
            }
        });
    }


    public void addActionCancel() {
        btnCANCEL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

}
