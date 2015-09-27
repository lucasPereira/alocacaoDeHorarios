package br.ufsc.sar.gui.componentes;

import java.util.Arrays;

import br.ufsc.sar.entity.Evento;
import br.ufsc.sar.entity.EventoProfissional;
import br.ufsc.sar.entity.Profissional;
import br.ufsc.sar.service.ProfissionalService;
import br.ufsc.sar.serviceimpl.ProfissionalServiceImpl;

/**
 * 
 * @author João
 *
 */
@SuppressWarnings("unchecked")
public class EventoProfissionalTableModel extends EntityRowTableModel<EventoProfissional>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 318144832582474285L;
	
	// id, nome, profissão, data de nascimento, telefone e cpf
	private static String[] COLUMN_NAMES =
	{
		"ID",
		"ID Evento",
		"ID Profissional",
		"Nome",
		"Profissão",
		"Data de Nasc.",
		"Telefone",
		"CPF"
	};

	private Evento evento;
		
	public EventoProfissionalTableModel(Evento evento)
	{
		super( Arrays.asList(COLUMN_NAMES) );
		setRowClass( Evento.class );

		setColumnClass(0, Long.class);
		setColumnClass(1, Long.class);
		setColumnClass(2, Long.class);
		setColumnClass(3, String.class);
		setColumnClass(4, String.class);
		setColumnClass(5, String.class);
		setColumnClass(6, String.class);
		setColumnClass(7, String.class);
		
		setColumnEditable(0, false);
		setColumnEditable(1, false);
		setColumnEditable(2, false);
		setColumnEditable(3, false);
		setColumnEditable(4, false);
		setColumnEditable(5, false);
		setColumnEditable(6, false);
		setColumnEditable(7, false);
		
		this.evento = evento;
	}

	public Object getValueAt(int row, int column)
	{
		EventoProfissional eventoProfissional = getRow(row);

		switch (column)
        {
            case 0: 
            	return eventoProfissional.getId();
            case 1: 
            	return eventoProfissional.getEvento() != null ? eventoProfissional.getEvento().getId() : null;
            case 2: 
            	return eventoProfissional.getProfissional() != null ? eventoProfissional.getProfissional().getId() : null;
            case 3: 
            	return eventoProfissional.getProfissional() != null ? eventoProfissional.getProfissional().getNome() : null;
            case 4: 
            	return eventoProfissional.getProfissional() != null ? eventoProfissional.getProfissional().getProfissao() : null;
            case 5: 
            	return eventoProfissional.getProfissional() != null ? eventoProfissional.getProfissional().getDtnascimento() : null;
            case 6: 
            	return eventoProfissional.getProfissional() != null ? eventoProfissional.getProfissional().getTelefone() : null;	
            case 7: 
            	return eventoProfissional.getProfissional() != null ? eventoProfissional.getProfissional().getCpf() : null;	
            default: return null;
        }
	}

	@Override
	public void setValueAt(Object value, int row, int column)
	{
		EventoProfissional eventoProfissional = getRow(row);

		switch (column)
        {
            case 0: eventoProfissional.setId((Long)value); break;
            case 1: eventoProfissional.setEvento(this.evento); break;
            case 2: 
            		ProfissionalService ps = new ProfissionalServiceImpl();
            		Profissional p = ps.getEntity((Long)value);
            		eventoProfissional.setProfissional(p); 
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
			EventoProfissional eventoProfissional = (EventoProfissional)this.modelData.get(this.modelData.size() - 1);	        
	        if (eventoProfissional != null && 
	        		eventoProfissional.getId() != null && eventoProfissional.getId().toString().trim().equals("") &&
	        		eventoProfissional.getEvento().getId() != null && eventoProfissional.getEvento().getNome() != null &&
	        		eventoProfissional.getProfissional().getId() != null && eventoProfissional.getProfissional().getNome() != null	 
	        	) 
	        {
	        	return true;
	        }
		}
       
       	return false;		
    }
	
    @Override
    public void addEmptyRow() {
    	EventoProfissional eventoProfissional = new EventoProfissional();
    	eventoProfissional.setEvento(this.evento);
        this.addRow(eventoProfissional);
    }

}
