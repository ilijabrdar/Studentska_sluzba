package view.dialogs;

import model.bazePodataka.BazaStudenata;
import model.entiteti.Predmet;
import model.entiteti.Student;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.text.Format;

public class HelpDialog extends JDialog {

    public HelpDialog(Frame parent, String title, boolean modal) {
        super(parent,title,modal);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension scrSize = kit.getScreenSize();
        int height = scrSize.height;
        int width = scrSize.width;

        setSize(3 * width / 6, 3 * height / 4);
        setLocationRelativeTo(parent);
        setResizable(true);
        this.setLayout(new BorderLayout());

        JTextArea txt = new JTextArea(); //u text area se smesta ceo string iz Help.txt

        String msg = pullText();

        txt.setText(msg);
        txt.setEditable(false); //iskljucivanje mogucnosti da korisnik moze da promeni nas sadrzaj
        txt.setWrapStyleWord(true);

        JScrollPane sp = new JScrollPane(txt);
        this.add(sp,BorderLayout.CENTER);

    }

    private String pullText() {
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        String [] data;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream("resources/listings/help.txt"), "utf-8"));
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
