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

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension scrSize = kit.getScreenSize();
        int height = scrSize.height;
        int width = scrSize.width;

        setSize(width / 6, height / 3);
        this.setLocationRelativeTo(MainFrame.getInstance());
        this.setResizable(false);
        this.setLayout(new BorderLayout());

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
