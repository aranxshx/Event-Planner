import javax.swing.table.AbstractTableModel;

class CustomTableModel extends AbstractTableModel {
    private String[] columnNames;
    private Object[][] data;

    public CustomTableModel(String[] columnNames, Object[][] data) {
        this.columnNames = columnNames;
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    // Optionally override other methods like setValueAt, isCellEditable, etc.
}
