package view;

import javax.swing.*;
import java.awt.*;

public class SubjectTablePanel extends JPanel {

    JPanel leftPanel = new JPanel();
    JPanel rightPanel = new JPanel();

    public SubjectTablePanel() {
        setLayout(new BorderLayout());
        add(leftPanel, BorderLayout.WEST);
        leftPanel.setBackground(Color.LIGHT_GRAY);
        add(rightPanel, BorderLayout.EAST);
        rightPanel.setBackground(Color.LIGHT_GRAY);
    }

    public SubjectTablePanel(Component c, int leftGap, int rightGap) {
        this();
        leftPanel.setPreferredSize(new Dimension(leftGap, rightGap));
        rightPanel.setPreferredSize(new Dimension(leftGap, rightGap));

        add(c, BorderLayout.CENTER);
/*
        setFocusable(true);
        setEnabled(true);
 */
    }

    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
        if (leftPanel == null || rightPanel == null)
            return;
        leftPanel.setBackground(bg);
        rightPanel.setBackground(bg);
    }
}
