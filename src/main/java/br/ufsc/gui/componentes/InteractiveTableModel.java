package br.ufsc.gui.componentes;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.ufsc.entity.BaseEntity;
import br.ufsc.sar.gui.componentes.InteractiveTableCaracteristica;
import br.ufsc.util.type.Coluna;

public abstract class InteractiveTableModel<E extends BaseEntity> extends AbstractTableModel {

    
    /**
	 * 
	 */
	private static final long serialVersionUID = -3718748173806207569L;

	public String[] columnNames = null;
    
    private List<E> list = null;
    
    private List<Coluna> columns = null;
    
    public InteractiveTableModel(List<E> list, List<Coluna> columns) {
    	this.columns = columns;
    	if (this.getColumns().size() > 0){
	    	columnNames = new String[this.getColumns().size()];
	    	for (Coluna column : this.getColumns()){
	    		columnNames[column.getId()] = column.getLabel();
	    	}
	    	this.setColumnNames(columnNames);
    	}
    	this.setList(list);
       
    }
    
    @Override
    public abstract Class getColumnClass(int column);
    
    @Override
    public abstract boolean isCellEditable(int row, int column);
    
    public void setValueAt(Object value, int row, int column){
    	E object = this.getList().get(row);
	    for (Coluna coluna : this.getColumns()){
	    		if(coluna.getId() == column){
	    			try {
						Field field = object.getClass().getDeclaredField(coluna.getField());
						field.setAccessible(true);
						try {
							field.set(object, value);
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						field.setAccessible(false);
					} catch (NoSuchFieldException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		}
	    }
	    	
    	    	
    	this.getList().set(row, object);
    }
    public Object getValueAt(int row, int column) {
    	E object = ((E) this.getList().get(row));
    	Object value = null;
	    for (Coluna coluna : this.getColumns()){
	    		if(coluna.getId() == column){
	    			try {
						Field field = object.getClass().getDeclaredField(coluna.getField());
						field.setAccessible(true);
						
						try {
							value = field.get(object);
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						field.setAccessible(false);
						
					} catch (NoSuchFieldException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		}
	    }
	    return value;
      }
    
    public String getColumnName(int column) {
        return this.getColumnNames()[column];
    }

   	public int getRowCount() {
		return this.getList().size();	
	}

	public int getColumnCount() {
		return this.getColumnNames().length;
	}

	public List<E> getList () {
		if (this.list == null){
			this.list = new ArrayList<E>();
		}
		return list;
	}

	public void setList(List<E> list) {
		this.list = list;
	}

	public String[] getColumnNames() {
		return this.columnNames;
	}
	
	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}

	
	 

	public boolean hasEmptyRow() {
        if (this.getList().size() == 0) 
        	return false;
        
        E objeto = (E)this.getList().get(this.getList().size() - 1);

        boolean empty = true;
        for(Field field : objeto.getClass().getDeclaredFields()){
        	try {
        		field.setAccessible(true);
				if(field.get(objeto).toString() != null){
					empty = false;
				}
				field.setAccessible(false);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
        }
        
        return empty;
    }

    public void addEmptyRow(E objeto) {
        this.getList().add(objeto);
        fireTableRowsInserted((this.getList().size() - 1), (this.getList().size() - 1));
    }

	public List<Coluna> getColumns() {
		return columns;
	}

	public void setColumns(List<Coluna> columns) {
		this.columns = columns;
	}

}
