package view.components;

import controller.StudentController;
import controller.SubjectController;

import java.awt.*;

import javax.swing.Icon;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TabbedPane extends JTabbedPane {
	
	private static final long serialVersionUID = -5941608496854985480L;
	
	public static TabbedPane instance = null;
	
	public static TabbedPane getInstance() {
		if(instance == null)
			instance = new TabbedPane();
		return instance;
	}
	
	private TabbedPane() {
		super();
		this.setFont(new Font(null, Font.PLAIN, 16));
		this.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				ToolBar tb = ToolBar.getInstance();
				tb.updateToolBar(isRunning("Predmeti"));
			}
		});
	}
	
	public void addNewTab(String title, Component component) {
		this.addTab(title, component);
	}
	
	public boolean isRunning(String name) {
		int currentTab = this.getSelectedIndex();
		if(name.equals(this.getTitleAt(currentTab)))
			return true;
		return false;
	}
	
	//ovo je dodato za ikonice
	@Override
	public void addTab(String title, Icon icon, Component component) {
		addTab(title, icon, component, null);
	}
}
