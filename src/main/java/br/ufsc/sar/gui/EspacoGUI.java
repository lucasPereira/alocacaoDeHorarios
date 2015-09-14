package br.ufsc.sar.gui;

import br.ufsc.entity.BaseEntity;
import br.ufsc.sar.controller.EspacoController;
import br.ufsc.sar.entity.Caracteristica;
import br.ufsc.sar.entity.Espaco;
import br.ufsc.sar.gui.componentes.EspacoTableModel;
import br.ufsc.sar.listner.EspacoListener;
import br.ufsc.util.type.EntidadeDetalheInfo;

/**
 * 
 * @author ErnaniCésar
 *
 */
public class EspacoGUI extends EntityGUI<Espaco> {

	/**
	 * Create the panel.
	 */
	public EspacoGUI(AppGUI app) {
		super(app);
	}

	@Override
	public EspacoTableModel getNewEntityTableModel() {
		return new EspacoTableModel();
	}

	@Override
	public String getTextoLabelEntity() {
		return "Cadastro de Espaço";
	}

//	@Override
//	public ProfissionalTableListener getEntityTableListener() {
//		return new ProfissionalTableListener();
//	}

	@Override
	public EspacoListener getEntityListener() {
		return new EspacoListener(this);
	}

	@Override
	public EspacoController getNewEntityController() {		
		return new EspacoController(this);		
	}		
	
	@Override
	public EntidadeDetalheInfo<? extends BaseEntity, Espaco> getEntidadeDetalheInfo() {
		return null;
	}
	
	@Override
	public void setEntidadeDetalhe(BaseEntity entidade) {}
}