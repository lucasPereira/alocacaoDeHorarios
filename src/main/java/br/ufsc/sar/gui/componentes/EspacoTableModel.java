package br.ufsc.sar.gui.componentes;

import java.util.Arrays;

import br.ufsc.sar.entity.Espaco;

public class EspacoTableModel extends EntityRowTableModel<Espaco>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 318144832582474285L;
	
	// id, nome, profissão, data de nascimento, telefone e cpf
	private static String[] COLUMN_NAMES =
	{
		"ID",
		"Nome",
		"Descrição",
		"Capacidade",
		"Fora uso?"
	};

	public EspacoTableModel()
	{
		super( Arrays.asList(COLUMN_NAMES) );
		setRowClass( Espaco.class );

		setColumnClass(0, Long.class);
		setColumnClass(1, String.class);
		setColumnClass(2, String.class);
		setColumnClass(3, Long.class);
		setColumnClass(4, Boolean.class);
		
		setColumnEditable(0, false);
		setColumnEditable(1, false);
		setColumnEditable(2, false);
		setColumnEditable(3, false);
		setColumnEditable(4, false);
	}

	public Object getValueAt(int row, int column)
	{
		Espaco espaco = getRow(row);

		switch (column)
        {
            case 0: return espaco.getId();
            case 1: return espaco.getNome();
            case 2: return espaco.getDescricao();
            case 3: return espaco.getCapacidade();
            case 4: return espaco.isForauso();
            default: return null;
        }
	}

	@Override
	public void setValueAt(Object value, int row, int column)
	{
		Espaco espaco = getRow(row);

		switch (column)
        {
            case 0: espaco.setId((Long)value); break;
            case 1: espaco.setNome((String)value); break;
            case 2: espaco.setDescricao((String)value); break;
            case 3: espaco.setCapacidade((Long)value); break;
            case 4: espaco.setForauso((Boolean)value); break;
        }

		fireTableCellUpdated(row, column);
	}
	
	@Override
	public boolean hasEmptyRow() {
		if(!super.hasEmptyRow()) {        
			Espaco espaco = (Espaco)this.modelData.get(this.modelData.size() - 1);	        
	        if (espaco != null && 
	        		espaco.getId() != null && espaco.getId().toString().trim().equals("") &&
	        		espaco.getNome() != null && espaco.getNome().trim().equals("") &&
	        		espaco.getDescricao()!= null && espaco.getDescricao().toString().trim().equals("") &&
	        		espaco.getCapacidade() != null && espaco.getCapacidade().toString().trim().equals("")
	        	) 
	        {
	        	return true;
	        }
		}
       
       	return false;		
    }
	
    @Override
    public void addEmptyRow() {
        this.addRow(new Espaco());
    }

}
