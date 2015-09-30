package br.ufsc.sar.gui.componentes;

import java.util.Arrays;

import br.ufsc.sar.entity.Evento;

/**
 * 
 * @author João
 *
 */
@SuppressWarnings("unchecked")
public class EventoTableModel extends EntityRowTableModel<Evento>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 318144832582474285L;
	
	// id, nome, profissão, data de nascimento, telefone e cpf
	private static String[] COLUMN_NAMES =
	{
		"ID",
		"Nome",
//		"Caracteristicas",
//		"Profissionais",
//		"Horarios",
//		"Espaco"
		"Detalhes"
	};

	public EventoTableModel()
	{
		super( Arrays.asList(COLUMN_NAMES) );
		setRowClass( Evento.class );

		setColumnClass(0, Long.class);
		setColumnClass(1, String.class);
		setColumnClass(2, String.class);
//		setColumnClass(3, String.class);
//		setColumnClass(4, String.class);
//		setColumnClass(5, String.class);
		
		setColumnEditable(0, false);
		setColumnEditable(1, true);
		setColumnEditable(2, true);
//		setColumnEditable(3, true);
//		setColumnEditable(4, true);
//		setColumnEditable(5, false);
	}

	public Object getValueAt(int row, int column)
	{
		Evento evento = getRow(row);

		switch (column)
        {
            case 0: 
            	return evento.getId();
            case 1: 
            	return evento.getNome();
            case 2: 
            	return "...";
//            case 3: 
//            	return "...";
//            case 4: 
//            	return "...";
//            case 5: 
//            	return "...";	
            default: return null;
        }
	}

	@Override
	public void setValueAt(Object value, int row, int column)
	{
		Evento evento = getRow(row);

		switch (column)
        {
            case 0: evento.setId((Long)value); break;
            case 1: evento.setNome((String)value); break;
            case 2: break;
//            case 3: break;
//            case 4: break;
//            case 5: break;
        }

		fireTableCellUpdated(row, column);
	}
	
	@Override
	public boolean hasEmptyRow() {
		if(!super.hasEmptyRow()) {        
			Evento evento = (Evento)this.modelData.get(this.modelData.size() - 1);	        
	        if (evento != null && 
	        		evento.getId() != null && evento.getId().toString().trim().equals("") &&
	        		evento.getNome() != null && evento.getNome().trim().equals("")
	        	) 
	        {
	        	return true;
	        }
		}
       
       	return false;		
    }
	
    @Override
    public void addEmptyRow() {
        this.addRow(new Evento());
    }

}
