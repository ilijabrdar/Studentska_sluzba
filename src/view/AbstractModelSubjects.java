package view;

import javax.swing.table.AbstractTableModel;

import model.BazaPredmeta;

public class AbstractModelSubjects extends AbstractTableModel {

	private static final long serialVersionUID = -1345756913120704216L;

	public AbstractModelSubjects() {
		super();
	}
	
	@Override
	public int getRowCount() {
		return BazaPredmeta.getBazaPredmeta().getSubjects().size();
	}

	@Override
	public int getColumnCount() {
		return BazaPredmeta.getBazaPredmeta().getColumns().size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return BazaPredmeta.getBazaPredmeta().getValueAt(rowIndex, columnIndex);
	}
	
	@Override
	 public String getColumnName(int column) {
		return BazaPredmeta.getBazaPredmeta().getColumnName(column);
	}
}
