package view.dialogs;

import view.components.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowProfsStudsDialog extends JDialog {
    protected JButton delete = null, back = null;
    protected DefaultListModel DLM;
    protected JList list;

    public ShowProfsStudsDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);

        this.DLM = new DefaultListModel();
        this.list = new JList();
        this.list.setModel(DLM);

        this.setSize(200, 250);
        this.setLocationRelativeTo(MainFrame.getInstance());
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        /*
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                DLM.removeAllElements();

            }
        }); //TODO WTF JE OVO
        */
        JPanel closingPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        delete = new JButton("Obriši");
        back = new JButton("Nazad");
        closingPanel.add(delete);
        closingPanel.add(back);

        this.add(closingPanel, BorderLayout.SOUTH);

        JScrollPane sp = new JScrollPane();
        sp.setViewportView(list);
        this.add(sp, BorderLayout.CENTER);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getDialog().dispose();
            }
        });
    }

    public JDialog getDialog() {return this;}

    public DefaultListModel getDLM() {
        return DLM;
    }
}