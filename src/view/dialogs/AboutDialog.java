package view.dialogs;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class AboutDialog extends JDialog {

    public AboutDialog(Frame parent, String title, boolean modal) {
        super(parent,title,modal);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension scrSize = kit.getScreenSize();
        int height = scrSize.height;
        int width = scrSize.width;
        setSize(3 * width / 6, 3 * height / 4);
        setLocationRelativeTo(parent);
        setResizable(true);
        this.setLayout(new BorderLayout());


        JTextArea txt = new JTextArea();

        String msg = pullText();

        txt.setText(msg);
        txt.setEditable(false);
        txt.setWrapStyleWord(true);

        JScrollPane sp = new JScrollPane(txt);
        this.add(sp,BorderLayout.CENTER);
    }

    private String pullText() { //TODO: specijalni karakteri
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        String [] data;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream("resources/listings/about.txt"), "utf-8"));
            String line = null;
            while((line = reader.readLine()) != null) {
                sb.append(line + "\n");


            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }

}
