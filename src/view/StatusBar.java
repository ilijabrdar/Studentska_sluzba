package view;

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
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -7960319300551657388L;

	public StatusBar() {
		
		this.setLayout(new BorderLayout());
		this.setBorder(new BevelBorder(BevelBorder.LOWERED)); //MMM efekat
		
		JPanel levi = new JPanel();
		JPanel desni = new JPanel();
		
		levi.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 4));
		desni.setLayout(new FlowLayout(FlowLayout.RIGHT,10,4));
		
		JLabel naziv = new JLabel("status");
		naziv.setText("Studentska Sluzba");
		
		
		//naziv.setHorizontalAlignment(SwingConstants.LEFT); //sta ovo radi?

		levi.add(naziv);
		
	
		
		//ovde sada resavas desni datum i vreme
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
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*JPanel statusbar = new JPanel();
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension scrSize = kit.getScreenSize();
		int width = scrSize.width;
		
		statusbar.setLayout(new FlowLayout(FlowLayout.LEFT, 500, 10));
		statusbar.setBackground(Color.white);
		statusbar.setPreferredSize(new Dimension(width,16));
		statusbar.setLayout(new BoxLayout(statusbar, BoxLayout.X_AXIS));
		JLabel statusLabel = new JLabel("status");
		statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
		statusbar.add(statusLabel);
		
		
		statusbar.setBorder(new BevelBorder(BevelBorder.LOWERED)); //BevelBorder je tipa 3D
		statusbar.setPreferredSize(new Dimension(3 * width / 4,20));
		
		statusbar.setLayout(new BorderLayout());
		//statusbar.setLayout(new BoxLayout(statusbar, BoxLayout.X_AXIS));
		
		JLabel statusLabel = new JLabel("status");
		statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
		statusLabel.setText("   Studentska Sluzba"); //PAZI OVDE, kako da namestim da se menja size
		statusbar.add(statusLabel);
		
		
		this.add(statusbar);*/
		
		
	}
	
}
