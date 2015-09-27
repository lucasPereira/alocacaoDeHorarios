package br.ufsc.sar.gui.componentes;

import java.util.Arrays;

import br.ufsc.sar.entity.Caracteristica;
import br.ufsc.sar.entity.Evento;
import br.ufsc.sar.entity.EventoCaracteristica;
import br.ufsc.sar.service.CaracteristicaService;
import br.ufsc.sar.serviceimpl.CaracteristicaServiceImpl;

/**
 * 
 * @author João
 *
 */
@SuppressWarnings("unchecked")
public class EventoCaracteristicaTableModel extends EntityRowTableModel<EventoCaracteristica>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 318144832582474285L;
	
	// id, nome, profissão, data de nascimento, telefone e cpf
	private static String[] COLUMN_NAMES =
	{
		"ID",
		"ID Evento",
		"ID Característica",
		"Qtde",
		"Nome",
		"Fora de uso?"
	};

	private Evento evento;
		
	public EventoCaracteristicaTableModel(Evento evento)
	{
		super( Arrays.asList(COLUMN_NAMES) );
		setRowClass( Evento.class );

		setColumnClass(0, Long.class);
		setColumnClass(1, Long.class);
		setColumnClass(2, Long.class);
		setColumnClass(3, Integer.class);
		setColumnClass(4, String.class);
		setColumnClass(5, Boolean.class);
		
		setColumnEditable(0, false);
		setColumnEditable(1, false);
		setColumnEditable(2, false);
		setColumnEditable(3, true);
		setColumnEditable(4, false);
		setColumnEditable(5, false);
		
		this.evento = evento;
	}

	public Object getValueAt(int row, int column)
	{
		EventoCaracteristica eventoCaracteristica = getRow(row);

		switch (column)
        {
            case 0: 
            	return eventoCaracteristica.getId();
            case 1: 
            	return eventoCaracteristica.getEvento() != null ? eventoCaracteristica.getEvento().getId() : null;
            case 2: 
            	return eventoCaracteristica.getCaracteristica() != null ? eventoCaracteristica.getCaracteristica().getId() : null;
            case 3: 
            	return eventoCaracteristica.getCaracteristica() != null ? eventoCaracteristica.getQuantidade() : null;	
            case 4: 
            	return eventoCaracteristica.getCaracteristica() != null ? eventoCaracteristica.getCaracteristica().getNome() : null;
            case 5: 
            	return eventoCaracteristica.getCaracteristica() != null ? eventoCaracteristica.getCaracteristica().getForauso() : null;
            default: return null;
        }
	}

	@Override
	public void setValueAt(Object value, int row, int column)
	{
		EventoCaracteristica eventoCaracteristica = getRow(row);

		switch (column)
        {
            case 0: eventoCaracteristica.setId((Long)value); break;
            case 1: eventoCaracteristica.setEvento(this.evento); break;
            case 2: 
            		CaracteristicaService cs = new CaracteristicaServiceImpl();
            		Caracteristica c = cs.getEntity((Long)value);
            		eventoCaracteristica.setCaracteristica(c); 
            		break;
            case 3: eventoCaracteristica.setQuantidade((Integer)value); break;
            case 4: break;
            case 5: break;
        }

		fireTableCellUpdated(row, column);
	}
	
	@Override
	public boolean hasEmptyRow() {
		if(!super.hasEmptyRow()) {        
			EventoCaracteristica eventoCaracteristica = (EventoCaracteristica)this.modelData.get(this.modelData.size() - 1);	        
	        if (eventoCaracteristica != null && 
	        		eventoCaracteristica.getId() != null && eventoCaracteristica.getId().toString().trim().equals("") &&
	        		eventoCaracteristica.getEvento().getId() != null && eventoCaracteristica.getEvento().getNome() != null &&
	        		eventoCaracteristica.getCaracteristica().getId() != null && eventoCaracteristica.getCaracteristica().getNome() != null	 
	        	) 
	        {
	        	return true;
	        }
		}
       
       	return false;		
    }
	
    @Override
    public void addEmptyRow() {
    	EventoCaracteristica eventoCaracteristica = new EventoCaracteristica();
    	eventoCaracteristica.setEvento(this.evento);
        this.addRow(eventoCaracteristica);
    }

}
