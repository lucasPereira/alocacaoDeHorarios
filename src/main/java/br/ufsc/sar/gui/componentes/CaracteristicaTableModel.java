package br.ufsc.sar.gui.componentes;

import java.util.Arrays;

import br.ufsc.sar.entity.Caracteristica;

@SuppressWarnings("unchecked")
public class CaracteristicaTableModel extends EntityRowTableModel<Caracteristica>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9063519398508764694L;
	private static String[] COLUMN_NAMES =
	{
		"ID",
		"Nome",
		"Fora Uso?"
	};

	public CaracteristicaTableModel()
	{
		super( Arrays.asList(COLUMN_NAMES) );
		setRowClass( Caracteristica.class );

		setColumnClass(0, Long.class);
		setColumnClass(1, String.class);
		setColumnClass(2, Boolean.class);
	}

	
	public Object getValueAt(int row, int column)
	{
		Caracteristica carac = getRow(row);

		switch (column)
        {
            case 0: return carac.getId();
            case 1: return carac.getNome();
            case 2: return carac.getForauso();
            default: return null;
        }
	}

	@Override
	public void setValueAt(Object value, int row, int column)
	{
		Caracteristica carac = getRow(row);

		switch (column)
        {
            case 0: carac.setId((Long)value); break;
            case 1: carac.setNome((String)value); break;
            case 2: carac.setForauso((Boolean)value); break;
        }

		fireTableCellUpdated(row, column);
	}

	@Override
	public boolean hasEmptyRow() {
		if(!super.hasEmptyRow()) {        
	        Caracteristica caracteristica = (Caracteristica)this.modelData.get(this.modelData.size() - 1);	        
	        if (caracteristica.getId().toString().trim().equals("") && caracteristica.getNome().trim().equals("") && caracteristica.getForauso().toString().trim().equals("")) {
	        	return true;
	        }
		}
       
       	return false;		
    }

	@Override
    public void addEmptyRow() {
        this.addRow(new Caracteristica());
    }
}
