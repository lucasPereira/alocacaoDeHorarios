package br.ufsc.sar.gui.componentes;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import br.ufsc.sar.entity.Caracteristica;

public class InteractiveTableCaracteristica extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8859491706722120055L;
	
	public static final int ID_INDEX = 0;
    public static final int NOME_INDEX = 1;
    public static final int FORA_USO_INDEX = 3;
    
    public static final String[] columnNames = {"Código", "Descrição", "Fora Uso?"};
    
    private Vector<Caracteristica> dataVector = null;
    
    public InteractiveTableCaracteristica() {
       
    }

    public String getColumnName(int column) {
        return this.getColumnNames()[column];
    }

    public boolean isCellEditable(int row, int column) {
        if (column == ID_INDEX) return false;
        else return true;
    }
    
    public Class<?> getColumnClass(int column) {
        switch (column) {
            case ID_INDEX:
            	return Long.class;
            case NOME_INDEX:
            	return String.class;
            case FORA_USO_INDEX:
               return Boolean.class;
        }
		return Object.class;
    }
    
	public int getRowCount() {
		return this.getDataVector().size();
		
	}

	public int getColumnCount() {
		return this.getColumnNames().length;
	}

	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		Caracteristica caracteristica = (Caracteristica)dataVector.get(rowIndex);
		 switch (columnIndex) {
         case ID_INDEX:
        	 caracteristica.setId((Long)value);
            break;
         case NOME_INDEX:
        	 caracteristica.setNome((String)value);
            break;
         case FORA_USO_INDEX:
        	 caracteristica.setForauso((Boolean)value);
            break;
         default:
            System.out.println("invalid index");
     }
     fireTableCellUpdated(rowIndex, columnIndex);
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		Caracteristica caracteristica = (Caracteristica)dataVector.get(rowIndex);
		 switch (columnIndex) {
         case ID_INDEX:
        	 return caracteristica.getId();
         case NOME_INDEX:
        	 return caracteristica.getNome();
         case FORA_USO_INDEX:
        	return caracteristica.getForauso();
         default:
            return new Object();
		}
	}

	public Vector<Caracteristica> getDataVector() {
		if (this.dataVector == null){
			this.dataVector = new Vector<Caracteristica>();
		}
		return dataVector;
	}

	public void setDataVector(Vector<Caracteristica> dataVector) {
		this.dataVector = dataVector;
	}

	public String[] getColumnNames() {
		return columnNames;
	}

	public boolean hasEmptyRow() {
        if (this.getDataVector().size() == 0) 
        	return false;
        
        Caracteristica caracteristica = (Caracteristica)this.getDataVector().get(this.getDataVector().size() - 1);
        
        if (caracteristica.getId().toString().trim().equals("") && caracteristica.getNome().trim().equals("") && caracteristica.getForauso().toString().trim().equals(""))
        	return true;
        else 
        	return false;
    }

    public void addEmptyRow() {
        this.getDataVector().add(new Caracteristica());
        fireTableRowsInserted(this.getDataVector().size() - 1, this.getDataVector().size() - 1);
    }
	
	

}
