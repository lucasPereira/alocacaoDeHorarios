/**
 * 
 */
package br.ufsc.sar.controller;

import javax.swing.event.TableModelEvent;

import br.ufsc.sar.entity.HorarioEspaco;
import br.ufsc.sar.gui.HorarioEspacoGUI;
import br.ufsc.sar.service.HorarioEspacoService;
import br.ufsc.sar.serviceimpl.HorarioEspacoServiceImpl;

/**
 * @author João
 *
 */
public class HorarioEspacoController extends EntityController<HorarioEspaco> {

	/**
	 * @param entityGUI
	 */
	public HorarioEspacoController(HorarioEspacoGUI espacoGUI) {
		super(espacoGUI);
	}

	@Override
	public HorarioEspacoService getEntityService() {
		return new HorarioEspacoServiceImpl();
	}

	@Override
	public boolean tratarColunaEspecial(TableModelEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
}