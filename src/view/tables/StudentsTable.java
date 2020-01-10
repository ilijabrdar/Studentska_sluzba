package view.tables;

import view.abstractModel.AbstractModelStudents;
import view.components.ButtonColumnPredmetiStudenta;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.*;

public class StudentsTable extends JTable {

	private static final long serialVersionUID = 7256962006890098490L;
	
	private static StudentsTable instance = new StudentsTable();

	public static StudentsTable getStudentsTable() {
		return instance;
	}
	private StudentsTable() {
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setModel(new AbstractModelStudents());
		new ButtonColumnPredmetiStudenta(this,11,"...");

		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(this.getModel());
		sorter.setSortable(11,false);
		this.setRowSorter(sorter);
		this.setUpdateSelectionOnSort(true);


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
