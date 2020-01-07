package view.abstractModel;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import model.bazePodataka.BazaPredmeta;

public class AbstractModelSubjects extends AbstractTableModel {

	private static final long serialVersionUID = -1345756913120704216L;
	
	public static String kolonaDugme = "Studenti";
	
	public AbstractModelSubjects() {
		super();
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex >= 4;
	}
	
	@Override
	public int getRowCount() {
		return BazaPredmeta.getBazaPredmeta().getSubjects().size();
	}

	@Override
	public int getColumnCount() {
		return BazaPredmeta.getBazaPredmeta().getColumnCount() + 1;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex<5)
			return BazaPredmeta.getBazaPredmeta().getValueAt(rowIndex, columnIndex);
		else if (columnIndex==5) {
			JButton btn = new JButton ("" + rowIndex);
			return btn;
		}
		
		return null;
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		super.setValueAt(aValue, rowIndex, columnIndex);
		if (columnIndex != 6) {
			return;
		}

	}
	
	@Override
	 public String getColumnName(int column) {
		if (column >= BazaPredmeta.getBazaPredmeta().getColumnCount())
			return kolonaDugme;
		
		return BazaPredmeta.getBazaPredmeta().getColumnName(column);
	}
	
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
		case 1:
		case 2:
		case 3:
		case 4:
			return String.class;
		case 5:
			return JButton.class;
		default:
			return null;
		}
	}
	
}
