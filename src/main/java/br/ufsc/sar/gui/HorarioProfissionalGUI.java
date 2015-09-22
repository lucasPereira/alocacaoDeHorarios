package br.ufsc.sar.gui;

import br.ufsc.entity.BaseEntity;
import br.ufsc.sar.controller.HorarioProfissionalController;
import br.ufsc.sar.entity.HorarioProfissional;
import br.ufsc.sar.entity.Profissional;
import br.ufsc.sar.gui.componentes.HorarioProfissionalTableModel;
import br.ufsc.sar.listener.HorarioProfissionalListener;
import br.ufsc.util.type.EntidadeDetalheInfo;

public class HorarioProfissionalGUI extends EntityGUI<HorarioProfissional> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6514408055127106463L;
	
	/**
	 * 
	 */
	public static final String GUI_LABEL = "Cadastro de Horários do Profissional";
	
	/**
	 * 
	 */
	public static final String COLUNA_DETALHE = "idprofissional";
		
	/**
	 * 
	 */
	private Profissional profissional;
	
		
	/**
	 * Create the panel.
	 */
	public HorarioProfissionalGUI(AppGUI app, Profissional profissional) {
		super(app, profissional);		
	}

	@Override
	public HorarioProfissionalTableModel getNewEntityTableModel() {		
		return new HorarioProfissionalTableModel(this.profissional);
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
	public HorarioProfissionalListener getEntityListener() {
		return new HorarioProfissionalListener(this);
	}

	@Override
	public HorarioProfissionalController getNewEntityController() {		
		return new HorarioProfissionalController(this);		
	}	
		
	@Override
	public EntidadeDetalheInfo<Profissional, HorarioProfissional> getEntidadeDetalheInfo() {
		EntidadeDetalheInfo<Profissional, HorarioProfissional> edinfo = new EntidadeDetalheInfo<Profissional, HorarioProfissional>();
		edinfo.setColunaDetalhe("profissional");//COLUNA_DETALHE);
		edinfo.setEntidadeRaiz(this.profissional);
		edinfo.setEntidadeDetalheClass(HorarioProfissional.class);
		return edinfo;
	}
	
	@Override
	public void setEntidadeDetalhe(BaseEntity entidade) {
		this.profissional = (Profissional)entidade;
	}
}