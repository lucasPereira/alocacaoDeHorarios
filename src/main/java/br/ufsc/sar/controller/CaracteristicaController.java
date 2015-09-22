/**
 * 
 */
package br.ufsc.sar.controller;

import javax.swing.event.TableModelEvent;

import br.ufsc.sar.entity.Caracteristica;
import br.ufsc.sar.gui.CaracteristicaGUI;
import br.ufsc.sar.service.CaracteristicaService;
import br.ufsc.sar.serviceimpl.CaracteristicaServiceImpl;

/**
 * @author João
 *
 */
public class CaracteristicaController extends EntityController<Caracteristica> {

	/**
	 * @param entityGUI
	 */
	public CaracteristicaController(CaracteristicaGUI CaracteristicaGUI) {
		super(CaracteristicaGUI);
	}

	@Override
	public CaracteristicaService getEntityService() {
		return new CaracteristicaServiceImpl();
	}

	@Override
	public boolean tratarColunaEspecial(TableModelEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

}
