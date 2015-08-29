/**
 * 
 */
package br.ufsc.sar.controller;

import br.ufsc.sar.entity.Profissional;
import br.ufsc.sar.gui.ProfissionalGUI;
import br.ufsc.sar.service.ProfissionalService;
import br.ufsc.sar.serviceimpl.ProfissionalServiceImpl;

/**
 * @author João
 *
 */
public class ProfissionalController extends EntityController<Profissional> {

	/**
	 * @param entityGUI
	 */
	public ProfissionalController(ProfissionalGUI profissionalGUI) {
		super(profissionalGUI);
	}

	@Override
	public ProfissionalService getEntityService() {
		return new ProfissionalServiceImpl();
	}

}
