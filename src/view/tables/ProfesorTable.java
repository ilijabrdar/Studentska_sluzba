package view.tables;

import view.abstractModel.AbstractModelProfesor;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

public class ProfesorTable extends JTable {

	private static final long serialVersionUID = -500762274265898052L;
	
	private static ProfesorTable instance = new ProfesorTable();
	
	public static ProfesorTable getProfesorTable() {
		return instance;
	}
	private ProfesorTable() {
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setModel(new AbstractModelProfesor());
	}
	
	@Override
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		Component c = super.prepareRenderer(renderer, row, column);
		if (isRowSelected(row)) {
			c.setBackground(Color.lightGray);
		} else {
			c.setBackground(Color.WHITE);
		}
		return c;
	}
	

}
