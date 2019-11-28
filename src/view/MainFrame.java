package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.table.AbstractTableModel;

public class MainFrame extends JFrame{

	private static final long serialVersionUID = -8026416994513756565L;
	
	private static MainFrame instance = new MainFrame();
	
	private SubjectTable stable = null;
	
	public static MainFrame getInsance() {
		return instance;
	}
	
	private MainFrame() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension scrSize = kit.getScreenSize();
		int height = scrSize.height;
		int width = scrSize.width;
		setSize(3 * width / 4, 3 * height / 4);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Studentska sluba");
		setIconImage(kit.getImage("imgs/ftn.png"));
		
		ToolBar toolbar = new ToolBar();
		this.add(toolbar, BorderLayout.NORTH);
		
		stable = new SubjectTable();
		
		JScrollPane spane = new JScrollPane(stable);
		
		TabbedPane tabs = TabbedPane.getInstance();
		tabs.addNewTab("Profesori", spane);
		tabs.setTabPlacement(TabbedPane.BOTTOM);
		this.add(tabs);
	}
	
	public void updateTable() {
		AbstractTableModel model = (AbstractModelSubjects) stable.getModel();
		model.fireTableDataChanged();
		validate();
	}
	
}
