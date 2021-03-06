package view.dialogs;

import view.components.BackgroundPanel;
import view.listeners.DialogWindowListener;
import view.listeners.MyKeyListener;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubjectDialog extends JDialog {

    private static final long serialVersionUID = 133958153971296240L;

    protected JTextField txt1 = null, txt2 = null;
    protected JComboBox<String> combo1 = null, combo2 = null;
    protected JButton btnOK = null, btnCENCEL = null;
    protected int flag1, flag2;

    public SubjectDialog(Frame owner, String title, boolean modal, int f1, int f2) {
        super(owner, title, modal);
        flag1 = f1; flag2 = f2;
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension scrSize = kit.getScreenSize();
        int height = scrSize.height;
        int width = scrSize.width;
        setSize(width / 3, height / 2);
        //setSize(400, 400);
        setLocationRelativeTo(owner);
        setResizable(false);
        this.setLayout(new BorderLayout());
        setContent();
        addActionsWind();
        addActionsFields(flag1, flag2);
        addActionsCENCEL();
    }

    public JDialog getDialog() { return this; }

    public void setContent() {
        JLabel lab1 = new JLabel("Šifra*");
        JLabel lab2 = new JLabel("Naziv*");
        JLabel lab3 = new JLabel("Semestar*");
        JLabel lab4 = new JLabel("Godina studija*");

        txt1 = new JTextField();
        txt1.setName("Sifra");
        txt1.setPreferredSize(new Dimension(300, 28));
        txt2 = new JTextField();
        txt2.setName("Naziv");
        txt2.setPreferredSize(new Dimension(300, 28));

        String [] semesters = new String[] {"Letnji", "Zimski"};
        combo1 = new JComboBox<String>(semesters);
        combo1.setPreferredSize(new Dimension(100, 28));

        String [] year = new String[] {"Prva", "Druga", "Treća", "Četvrta"};
        combo2 = new JComboBox<String>(year);
        combo2.setPreferredSize(new Dimension(100, 28));

        BackgroundPanel bigPanel = new BackgroundPanel();
        bigPanel.setBorder(new TitledBorder(new EtchedBorder(), "Podaci o predmetu"));
        bigPanel.setLayout(new BoxLayout(bigPanel, BoxLayout.Y_AXIS));

        JPanel pane1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        pane1.setOpaque(false);
        pane1.add(lab1);
        pane1.add(txt1);

        JPanel pane2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        pane2.setOpaque(false);
        pane2.add(lab2);
        pane2.add(txt2);

        JPanel pane3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        pane3.setOpaque(false);
        pane3.add(lab3);
        pane3.add(Box.createHorizontalStrut(30));
        pane3.add(combo1);

        JPanel pane4 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        pane4.setOpaque(false);
        pane4.add(lab4);
        pane4.add(Box.createHorizontalStrut(7));
        pane4.add(combo2);

        bigPanel.add(pane1);
        bigPanel.add(pane2);
        bigPanel.add(pane3);
        bigPanel.add(pane4);
        bigPanel.add(Box.createVerticalStrut(50));

        JPanel closingPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        closingPanel.setBackground(Color.white);
        btnOK = new JButton("Sačuvaj");
        btnOK.setName("NoviPredmetOK");
        btnOK.setEnabled(false);
        btnCENCEL = new JButton("Odustani");
        closingPanel.add(btnOK);
        closingPanel.add(btnCENCEL);

        this.add(bigPanel, BorderLayout.CENTER);
        this.add(closingPanel, BorderLayout.SOUTH);
    }

    public void addActionsWind() {
        this.addWindowListener(new DialogWindowListener());
    }

    public void addActionsFields(int flag1, int flag2) {
        MyKeyListener kl = new MyKeyListener(btnOK, flag1, flag2);
        txt1.addKeyListener(kl);
        txt2.addKeyListener(kl);
    }

    public void addActionsCENCEL() {
        btnCENCEL.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int code = JOptionPane.showConfirmDialog(getDialog(), "Da li ste sigurni da želite da obustavite unos?\nIzmene neće biti sačuvane.",
                        "Novi predmet", JOptionPane.YES_NO_OPTION);

                if(code == JOptionPane.YES_OPTION)
                    getDialog().dispose();
            }
        });
    }

}
