package br.ufsc.sar.gui.componentes;

import java.util.Arrays;

import br.ufsc.sar.entity.Espaco;
import br.ufsc.sar.entity.Evento;
import br.ufsc.sar.entity.EventoEspaco;
import br.ufsc.sar.service.EspacoService;
import br.ufsc.sar.serviceimpl.EspacoServiceImpl;

/**
 * 
 * @author João
 *
 */
@SuppressWarnings("unchecked")
public class EventoEspacoTableModel extends EntityRowTableModel<EventoEspaco>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 318144832582474285L;
	
	// id, nome, profissão, data de nascimento, telefone e cpf
	private static String[] COLUMN_NAMES =
	{
		"ID",
		"ID Evento",
		"ID Espaco",
		"Nome",
		"Capacidade",
		"Descricao",
		"Fora de uso?",
		"Caracteristicas"
	};

	private Evento evento;
		
	public EventoEspacoTableModel(Evento evento)
	{
		super( Arrays.asList(COLUMN_NAMES) );
		setRowClass( Evento.class );

		setColumnClass(0, Long.class);
		setColumnClass(1, Long.class);
		setColumnClass(2, Long.class);		
		setColumnClass(3, String.class);
		setColumnClass(4, Integer.class);
		setColumnClass(5, String.class);
		setColumnClass(6, Boolean.class);
		setColumnClass(7, String.class);
		
		setColumnEditable(0, false);
		setColumnEditable(1, false);
		setColumnEditable(2, false);
		setColumnEditable(3, false);
		setColumnEditable(4, false);
		setColumnEditable(5, false);
		setColumnEditable(6, false);
		setColumnEditable(7, true);
		
		this.evento = evento;
	}

	public Object getValueAt(int row, int column)
	{
		EventoEspaco eventoEspaco = getRow(row);

		switch (column)
        {
            case 0: 
            	return eventoEspaco.getId();
            case 1: 
            	return eventoEspaco.getEvento() != null ? eventoEspaco.getEvento().getId() : null;
            case 2: 
            	return eventoEspaco.getEspaco() != null ? eventoEspaco.getEspaco().getId() : null;
            case 3: 
            	return eventoEspaco.getEspaco() != null ? eventoEspaco.getEspaco().getNome() : null;	
            case 4: 
            	return eventoEspaco.getEspaco() != null ? eventoEspaco.getEspaco().getCapacidade() : null;
            case 5: 
            	return eventoEspaco.getEspaco() != null ? eventoEspaco.getEspaco().getDescricao() : null;
            case 6: 
            	return eventoEspaco.getEspaco() != null ? eventoEspaco.getEspaco().isForauso() : null;
            case 7: 
            	return "...";
            default: return null;
        }
	}

	@Override
	public void setValueAt(Object value, int row, int column)
	{
		EventoEspaco eventoEspaco = getRow(row);

		switch (column)
        {
            case 0: eventoEspaco.setId((Long)value); break;
            case 1: eventoEspaco.setEvento(this.evento); break;
            case 2: 
            		EspacoService cs = new EspacoServiceImpl();
            		Espaco c = (Espaco) cs.getEntity((Long)value);
            		eventoEspaco.setEspaco(c); 
            		break;
            case 3: break;
            case 4: break;
            case 5: break;
            case 6: break;
            case 7: break;
        }

		fireTableCellUpdated(row, column);
	}
	
	@Override
	public boolean hasEmptyRow() {
		if(!super.hasEmptyRow()) {        
			EventoEspaco eventoEspaco = (EventoEspaco)this.modelData.get(this.modelData.size() - 1);	        
	        if (eventoEspaco != null && 
	        		eventoEspaco.getId() != null && eventoEspaco.getId().toString().trim().equals("") &&
	        		eventoEspaco.getEvento().getId() != null && eventoEspaco.getEvento().getNome() != null &&
	        		eventoEspaco.getEspaco().getId() != null && eventoEspaco.getEspaco().getNome() != null	 
	        	) 
	        {
	        	return true;
	        }
		}
       
       	return false;		
    }
	
    @Override
    public void addEmptyRow() {
    	EventoEspaco eventoEspaco = new EventoEspaco();
    	eventoEspaco.setEvento(this.evento);
        this.addRow(eventoEspaco);
    }

}
