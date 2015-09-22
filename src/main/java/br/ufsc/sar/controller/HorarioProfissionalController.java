/**
 * 
 */
package br.ufsc.sar.controller;

import javax.swing.event.TableModelEvent;

import br.ufsc.sar.entity.HorarioProfissional;
import br.ufsc.sar.gui.HorarioProfissionalGUI;
import br.ufsc.sar.service.HorarioProfissionalService;
import br.ufsc.sar.serviceimpl.HorarioProfissionalServiceImpl;

/**
 * @author João
 *
 */
public class HorarioProfissionalController extends EntityController<HorarioProfissional> {

	/**
	 * @param entityGUI
	 */
	public HorarioProfissionalController(HorarioProfissionalGUI profissionalGUI) {
		super(profissionalGUI);
	}

	@Override
	public HorarioProfissionalService getEntityService() {
		return new HorarioProfissionalServiceImpl();
	}

	@Override
	public boolean tratarColunaEspecial(TableModelEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
}