package view.dialogs;

import controller.SubjectController;
import model.bazePodataka.BazaPredmeta;
import model.bazePodataka.BazaStudenata;
import model.entiteti.Predmet;
import model.entiteti.Student;
import view.components.BackgroundPanel;
import view.components.MainFrame;
import view.tables.SubjectTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class StudentNaPredmet extends JDialog{

    protected JButton btnOK = null;
    protected JButton btnCANCEL = null;

    protected JTextField txt_indeks = null;


    public StudentNaPredmet(Frame parent, String title, boolean modal) {
        super(parent,title,modal);

        BackgroundPanel bigPanel = new BackgroundPanel();

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension scrSize = kit.getScreenSize();
        int height = scrSize.height;
        int width = scrSize.width;

        setSize(width / 4,height / 6);
        setLocationRelativeTo(parent);
        setResizable(false);
        this.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel indeks = new JLabel("Broj indeksa: ");
        txt_indeks = new JTextField();
        txt_indeks.setName("Indeks");
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

        btnOK.setEnabled(false);
        addActionsOK();
        addActionCancel();
        addKeyListeners();

    }

    private void addKeyListeners() {
        txt_indeks.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) { //provera da li uneseni index postoji i da li se godina studija tog studenta poklapa sa godinom predmeta
                String indeks = txt_indeks.getText();
                Predmet p =  SubjectController.getSubjectController().getSelectedSubjectByID();
                int p_godina = Integer.parseInt(p.getGodina());
                Student s = BazaStudenata.getBazaStudenata().getStudentPrekoIndeksa(indeks);

                if (s!=null) {
                    if (s.getGodina_studija()!=p_godina) {//ako postoji i godina mu je razlicita
                        btnOK.setEnabled(false);
                        txt_indeks.setBackground(Color.pink);
                    }
                    else { //postoji i godina ista
                        btnOK.setEnabled(true);
                        txt_indeks.setBackground(Color.white);
                    }
                }
                else { //ako student ne postoji
                    btnOK.setEnabled(false);
                    txt_indeks.setBackground(Color.pink);
                }


            }
        });
    }


    public void addActionsOK() {
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String indeks = txt_indeks.getText();
                SubjectController sc = SubjectController.getSubjectController();
                Predmet p = SubjectController.getSubjectController().getSelectedSubjectByID();

                if (!sc.addStudentToSubject(indeks))
                    JOptionPane.showMessageDialog(MainFrame.getInstance(), "Unetom studentu je već dodeljen predmet.",
                            "Dodavanje studenta na predmet | " + p.getNaziv(), JOptionPane.ERROR_MESSAGE);
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
