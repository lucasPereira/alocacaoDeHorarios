package br.ufsc.sar.gui;

import br.ufsc.entity.BaseEntity;
import br.ufsc.sar.controller.EventoProfissionalController;
import br.ufsc.sar.entity.Evento;
import br.ufsc.sar.entity.EventoProfissional;
import br.ufsc.sar.gui.componentes.EventoProfissionalTableModel;
import br.ufsc.sar.listener.EventoProfissionalListener;
import br.ufsc.util.type.EntidadeDetalheInfo;

/**
 * 
 * @author João
 *
 */
public class EventoProfissionalGUI extends EntityGUI<EventoProfissional> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6514408055127106463L;
	
	/**
	 * 
	 */
	public static final String GUI_LABEL = "Profissionais do evento";
	
	/**
	 * 
	 */
	private Evento evento;
	
	/**
	 * Create the panel.
	 * @throws Exception 
	 */
	public EventoProfissionalGUI(AppGUI app, BaseEntity entidade) {
		super(app, entidade);
	}

	@Override
	public EventoProfissionalTableModel getNewEntityTableModel() {
		return new EventoProfissionalTableModel(this.evento);
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
	public EventoProfissionalListener getEntityListener() {
		return new EventoProfissionalListener(this);
	}

	@Override
	public EventoProfissionalController getNewEntityController() {		
		return new EventoProfissionalController(this);		
	}	
	
	@Override
	public EntidadeDetalheInfo<? extends BaseEntity, EventoProfissional> getEntidadeDetalheInfo() {
		return null;
	}
	
	@Override
	public void setEntidadeDetalhe(BaseEntity entidade) {
		this.evento = (Evento) entidade;			
	}
}