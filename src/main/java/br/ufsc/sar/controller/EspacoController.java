/**
 * 
 */
package br.ufsc.sar.controller;

import javax.swing.event.TableModelEvent;

import br.ufsc.sar.entity.Espaco;
import br.ufsc.sar.gui.EspacoGUI;
import br.ufsc.sar.gui.FormularioEspacoGUI;
import br.ufsc.sar.service.EspacoService;
import br.ufsc.sar.serviceimpl.EspacoServiceImpl;

/**
 * @author ErnaniCésar
 *
 */
public class EspacoController extends EntityController<Espaco> {

	/**
	 * @param entityGUI
	 */
	public EspacoController(EspacoGUI espacoGUI) {
		super(espacoGUI);
	}
	
	@Override
	public EspacoService getEntityService() {
		return new EspacoServiceImpl();
	}

	@Override
	public boolean tratarColunaEspecial(TableModelEvent e) {
		if(e.getColumn() == 5){
			this.getEntityGUI().removeAll();
			this.getEntityGUI().setVisible(false);
			this.getEntityGUI().add(new FormularioEspacoGUI(this.getEntityGUI().getAplicacaoGUI()));
			this.getEntityGUI().setVisible(true);
			return true;
		}
		return false;
	}

}
