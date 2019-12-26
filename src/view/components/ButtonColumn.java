package view.components;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;

public class ButtonColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, MouseListener {

	private static final long serialVersionUID = -9129041852936254519L;
	
	protected JButton renderButton;
	protected JButton editorButton;
	protected JTable table;
	protected boolean isEditorActive = false;
	protected JDialog dialog;

	public ButtonColumn(JTable table, int column, String msg) {
		this.table = table;
		this.table.getColumnModel().getColumn(column).setCellRenderer(this);
		this.table.getColumnModel().getColumn(column).setCellEditor(this);
		this.table.addMouseListener(this);

		this.renderButton = new JButton(msg);
		this.editorButton = new JButton(msg);
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
		return new SubjectTablePanel(this.editorButton, 10, 10);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		return new SubjectTablePanel(this.renderButton, 10, 10);
	}

}
