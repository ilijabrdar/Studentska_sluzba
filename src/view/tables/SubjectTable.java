package view.tables;

import view.components.ButtonColumnProfesori;
import view.components.ButtonColumnStudenti;
import view.abstractModel.AbstractModelSubjects;

import java.awt.Color;
import java.awt.Component;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class SubjectTable extends JTable{

	private static final long serialVersionUID = -5839231427187920524L;
	
	private static SubjectTable instance = null;
	
	private SubjectTable() {
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setModel(new AbstractModelSubjects());
		new ButtonColumnStudenti(this,5, "Prikaži studente");
		new ButtonColumnProfesori(this,4, "Prikaži profesore");
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
