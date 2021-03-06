package view.components;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;


public class StatusBar extends JPanel {

	private static final long serialVersionUID = -7960319300551657388L;

	public StatusBar() {
		
		this.setLayout(new BorderLayout());
		this.setBorder(new BevelBorder(BevelBorder.LOWERED)); //kul efekat
		
		JPanel levi = new JPanel();
		JPanel desni = new JPanel();
		
		levi.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 4));
		desni.setLayout(new FlowLayout(FlowLayout.RIGHT,10,4));

		//u levi panel se stavlja naziv
		JLabel naziv = new JLabel("status");
		naziv.setText("Studentska Služba");

		levi.add(naziv);
		
		//ovde sada resavas desni - datum i vreme
		SimpleDateFormat sdf = new SimpleDateFormat( "hh: mm    dd MMM yyyy" );
		JLabel vreme = new JLabel (sdf.format( new GregorianCalendar().getTime()));
		Timer timer = new Timer (500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vreme.setText(DateFormat.getDateTimeInstance().format(new Date()));
			}
		});
		
		timer.setRepeats(true);
		timer.setCoalesce(true);
		timer.setInitialDelay(0);
		timer.start();
		
		desni.add(vreme);
		
		this.add(desni, BorderLayout.EAST);
		this.add(levi, BorderLayout.WEST);

	}
	
}
