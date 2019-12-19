package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

import model.BazaPredmeta;
import model.Predmet;

public class SubjectTable extends JTable{

	private static final long serialVersionUID = -5839231427187920524L;
	
	private static SubjectTable instance = null;
	
	private SubjectTable() {
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setModel(new AbstractModelSubjects());
		new ButtonColumn(this,5);
	}
	
	public static SubjectTable getSubjectTable() {
		if(instance == null)
			instance = new SubjectTable();
		return instance;
	}
	
	@Override
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		Component c = super.prepareRenderer(renderer, row, column);
		if (isRowSelected(row)) {
			c.setBackground(Color.LIGHT_GRAY);
		} else {
			c.setBackground(Color.WHITE);
		}
		return c;
	}
	

}
