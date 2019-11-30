package view;

import javax.swing.table.AbstractTableModel;

import model.BazaPredmeta;
import model.BazaProfesora;

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
		return BazaProfesora.getBazaProfesora().getValueAt(rowIndex, columnIndex);
	}
	
	@Override
	 public String getColumnName(int column) {
		return BazaProfesora.getBazaProfesora().getColumnName(column);
	}

}
