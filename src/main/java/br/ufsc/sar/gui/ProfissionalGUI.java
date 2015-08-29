package br.ufsc.sar.gui;

import br.ufsc.sar.controller.ProfissionalController;
import br.ufsc.sar.entity.Profissional;
import br.ufsc.sar.gui.componentes.ProfissionalTableModel;
import br.ufsc.sar.listner.ProfissionalListener;

public class ProfissionalGUI extends EntityGUI<Profissional> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6514408055127106463L;
	
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
		return "Cadastro de Profissionais";
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
}