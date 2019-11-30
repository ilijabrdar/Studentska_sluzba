package view;

import javax.swing.table.AbstractTableModel;

import model.BazaStudenata;

public class AbstractModelStudents extends AbstractTableModel{

	private static final long serialVersionUID = 1879467447579885068L;
	 
	public AbstractModelStudents() {
		super();
	}
	
	@Override
	public int getRowCount() {
		return BazaStudenata.getBazaStudenata().getStudents().size();
	}

	@Override
	public int getColumnCount() {
		return BazaStudenata.getBazaStudenata().getColumns().size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return BazaStudenata.getBazaStudenata().getValueAt(rowIndex, columnIndex);
	}
	
	@Override
	public String getColumnName(int column) {
		return BazaStudenata.getBazaStudenata().getColumnName(column);
	}

}
