package view;

import com.sun.tools.javac.Main;
import controller.StudentController;
import controller.SubjectController;
import model.BazaPredmeta;
import model.BazaStudenata;
import model.Predmet;
import model.Student;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class ButtonColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, MouseListener {

	private static final long serialVersionUID = -9129041852936254519L;
	
	private JButton renderButton;
	private JButton editorButton;
	private JTable table;
	private boolean isEditorActive = false;
	private DefaultListModel DLM = new DefaultListModel();
	private JList list = new JList();
	private JDialog dialog;


	public ButtonColumn(JTable table, int column) {
		this.table = table;
		this.table.getColumnModel().getColumn(column).setCellRenderer(this);
		this.table.getColumnModel().getColumn(column).setCellEditor(this);
		this.table.addMouseListener(this);

		list.setBounds(200,200,200,200);
		dialog = new JDialog(MainFrame.getInsance(),"Studenti na predmetu " , true);//TODO: dodaj naziv predmeta

		dialog.setSize(600, 600);
		dialog.setLocationRelativeTo(MainFrame.getInsance());
		dialog.setResizable(false);
		dialog.setLayout(new BorderLayout());

		dialog.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				DLM.removeAllElements();

			}
		});

		dialog.add(list);
		
		this.renderButton = new JButton ("...");
		this.editorButton = new JButton("...");
		
		this.editorButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fireEditingStopped();


				int row = MainFrame.getInsance().getStable().getSelectedRow();
				Predmet selected_predmet = BazaPredmeta.getBazaPredmeta().getRow(row);

				for (Student s : selected_predmet.getStudenti()) {
					DLM.addElement(s.getIndex());
				}

				list.setModel(DLM);

				dialog.setVisible(true);

			}
		});
		
		this.isEditorActive = false;

		dodajListerLista();
	}

	private void dodajListerLista() {
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				System.out.println("Selektovan je student sa indexom " + list.getSelectedValue());
			}
		});
	}

	@Override
	public Object getCellEditorValue() {
		return null;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (table.isEditing() && table.getCellEditor() == this) {
			this.isEditorActive = true;
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (isEditorActive && table.isEditing()) {
			table.getCellEditor().stopCellEditing();
		}
		isEditorActive = false;
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		JPanel main = new JPanel();
		JPanel leftPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		
		main.setLayout(new BorderLayout());
		main.add(leftPanel, BorderLayout.WEST);
		leftPanel.setBackground(Color.LIGHT_GRAY);
		main.add(rightPanel, BorderLayout.EAST);
		rightPanel.setBackground(Color.LIGHT_GRAY);
		
		leftPanel.setPreferredSize(new Dimension(50, 50));
		rightPanel.setPreferredSize(new Dimension(50, 50));

		
		main.add(this.editorButton, BorderLayout.CENTER);
		
		main.setFocusable(true);
		main.setEnabled(true);
		
		return main;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		JPanel main = new JPanel();
		JPanel leftPanel = new JPanel();
		JPanel rightPanel = new JPanel();


		
		main.setLayout(new BorderLayout());
		main.add(leftPanel, BorderLayout.WEST);
		leftPanel.setBackground(Color.WHITE);
		main.add(rightPanel, BorderLayout.EAST);
		rightPanel.setBackground(Color.WHITE);
		
		leftPanel.setPreferredSize(new Dimension(50, 50));
		rightPanel.setPreferredSize(new Dimension(50, 50));
		
		main.setFocusable(true);
		main.setEnabled(true);

		main.add(this.renderButton, BorderLayout.CENTER);
		
		return main;
	}

}
