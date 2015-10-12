package br.ufsc.sar.gui;

import br.ufsc.entity.BaseEntity;
import br.ufsc.sar.controller.HorarioEspacoController;
import br.ufsc.sar.entity.Espaco;
import br.ufsc.sar.entity.HorarioEspaco;
import br.ufsc.sar.gui.componentes.HorarioEspacoTableModel;
import br.ufsc.sar.listener.HorarioEspacoListener;
import br.ufsc.util.type.EntidadeDetalheInfo;

/**
 * 
 * @author João
 *
 */
public class HorarioEspacoGUI extends EntityGUI<HorarioEspaco> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6514408055127106463L;
	
	/**
	 * 
	 */
	public static final String GUI_LABEL = "Cadastro de Horários do Espaço";
	
	/**
	 * 
	 */
	public static final String COLUNA_DETALHE = "idespaco";
		
	/**
	 * 
	 */
	private Espaco espaco;
	
		
	/**
	 * Create the panel.
	 * @throws Exception 
	 */
	public HorarioEspacoGUI(AppGUI app, BaseEntity espaco) {
		super(app, espaco);		
	}

	@Override
	public HorarioEspacoTableModel getNewEntityTableModel() {		
		return new HorarioEspacoTableModel(this.espaco);
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
	public HorarioEspacoListener getEntityListener() {
		return new HorarioEspacoListener(this);
	}

	@Override
	public HorarioEspacoController getNewEntityController() {		
		return new HorarioEspacoController(this);		
	}	
		
	@Override
	public EntidadeDetalheInfo<Espaco, HorarioEspaco> getEntidadeDetalheInfo() {
		EntidadeDetalheInfo<Espaco, HorarioEspaco> edinfo = new EntidadeDetalheInfo<Espaco, HorarioEspaco>();
		edinfo.setColunaDetalhe("espaco");//COLUNA_DETALHE);
		edinfo.setEntidadeRaiz(this.espaco);
		edinfo.setEntidadeDetalheClass(HorarioEspaco.class);
		return edinfo;
	}
	
	@Override
	public void setEntidadeDetalhe(BaseEntity entidade) {
		this.espaco = (Espaco) entidade;			
	}
}