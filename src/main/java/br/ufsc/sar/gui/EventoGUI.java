package br.ufsc.sar.gui;

import br.ufsc.entity.BaseEntity;
import br.ufsc.sar.controller.EventoController;
import br.ufsc.sar.entity.Evento;
import br.ufsc.sar.gui.componentes.EventoTableModel;
import br.ufsc.sar.listener.EventoListener;
import br.ufsc.util.type.EntidadeDetalheInfo;

/**
 * 
 * @author João
 *
 */
public class EventoGUI extends EntityGUI<Evento> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6514408055127106463L;
	
	/**
	 * 
	 */
	public static final String GUI_LABEL = "Cadastro de Eventos";
	
	/**
	 * Create the panel.
	 */
	public EventoGUI(AppGUI app) {
		super(app);
	}

	@Override
	public EventoTableModel getNewEntityTableModel() {
		return new EventoTableModel();
	}

	@Override
	public String getTextoLabelEntity() {
		return GUI_LABEL;
	}

//	@Override
//	public ProfissionalTableListener getEntityTableListener() {
//		return new ProfissionalTableListener();
//	}

	@Override
	public EventoListener getEntityListener() {
		return new EventoListener(this);
	}

	@Override
	public EventoController getNewEntityController() {		
		return new EventoController(this);		
	}	
	
	@Override
	public EntidadeDetalheInfo<? extends BaseEntity, Evento> getEntidadeDetalheInfo() {
		return null;
	}
	
	@Override
	public void setEntidadeDetalhe(BaseEntity entidade) {}
}