package br.ufsc.sar.gui;

import br.ufsc.entity.BaseEntity;
import br.ufsc.sar.controller.ProfissionalController;
import br.ufsc.sar.entity.Profissional;
import br.ufsc.sar.gui.componentes.ProfissionalTableModel;
import br.ufsc.sar.listener.ProfissionalListener;
import br.ufsc.util.type.EntidadeDetalheInfo;

/**
 * 
 * @author João
 *
 */
public class ProfissionalGUI extends EntityGUI<Profissional> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6514408055127106463L;
	
	/**
	 * 
	 */
	public static final String GUI_LABEL = "Cadastro de Profissionais";
	
	/**
	 * Create the panel.
	 */
	public ProfissionalGUI(AppGUI app) {
		super(app);
	}

	@Override
	public ProfissionalTableModel getNewEntityTableModel() {
		return new ProfissionalTableModel();
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
	public ProfissionalListener getEntityListener() {
		return new ProfissionalListener(this);
	}

	@Override
	public ProfissionalController getNewEntityController() {		
		return new ProfissionalController(this);		
	}	
	
	@Override
	public EntidadeDetalheInfo<? extends BaseEntity, Profissional> getEntidadeDetalheInfo() {
		return null;
	}
	
	@Override
	public void setEntidadeDetalhe(BaseEntity entidade) {}
}