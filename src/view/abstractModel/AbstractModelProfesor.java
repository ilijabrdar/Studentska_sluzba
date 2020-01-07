package view.abstractModel;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import model.bazePodataka.BazaPredmeta;
import model.bazePodataka.BazaProfesora;

public class AbstractModelProfesor extends AbstractTableModel {

	private static final long serialVersionUID = 5768596851376464829L;

	public AbstractModelProfesor() {
		super();
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex >= 10;
	}

	@Override
	public int getRowCount() {
		return BazaProfesora.getBazaProfesora().getProfesori().size();
	}

	@Override
	public int getColumnCount() {
		return BazaProfesora.getBazaProfesora().getColumns().size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex<10)
			return BazaProfesora.getBazaProfesora().getValueAt(rowIndex, columnIndex);
		else if (columnIndex==5) {
			JButton btn = new JButton ("" + rowIndex);
			return btn;
		}

		return null;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		super.setValueAt(aValue, rowIndex, columnIndex);
		if (columnIndex != 11) {
			return;
		}

	}

	@Override
	public String getColumnName(int column) {
		return BazaProfesora.getBazaProfesora().getColumnName(column);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
			case 10:
				return String.class;
			default:
				return null;
		}
	}

}


/*
package view.abstractModel;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import model.bazePodataka.BazaProfesora;

public class AbstractModelProfesor extends AbstractTableModel {

	private static final long serialVersionUID = 5768596851376464829L;

	public AbstractModelProfesor() {
		super();
	}
	
	@Override
	public int getRowCount() {
		return BazaProfesora.getBazaProfesora().getProfesori().size();
	}

	@Override
	public int getColumnCount() {
		return BazaProfesora.getBazaProfesora().getColumns().size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex < 10)
			return BazaProfesora.getBazaProfesora().getValueAt(rowIndex, columnIndex);
		else return null;
	}
	
	@Override
	 public String getColumnName(int column) {
		return BazaProfesora.getBazaProfesora().getColumnName(column);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
			case 10:
				return JButton.class;
			default:
				return null;
		}
	}

}
*/