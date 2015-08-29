package br.ufsc.sar.gui;

import br.ufsc.sar.controller.CaracteristicaController;
import br.ufsc.sar.entity.Caracteristica;
import br.ufsc.sar.gui.componentes.CaracteristicaTableModel;
import br.ufsc.sar.listner.CaracteristicaListener;

public class CaracteristicaGUI extends EntityGUI<Caracteristica> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6514408055127106463L;
	
	/**
	 * Create the panel.
	 */
	public CaracteristicaGUI(AppGUI app) {
		super(app);
	}

	@Override
	public CaracteristicaTableModel getNewEntityTableModel() {
		return new CaracteristicaTableModel();
	}

	@Override
	public String getTextoLabelEntity() {
		return "Cadastro de Características";
	}

	@Override
	public CaracteristicaListener getEntityListener() {
		return new CaracteristicaListener(this);
	}

	@Override
	public CaracteristicaController getNewEntityController() {		
		return new CaracteristicaController(this);		
	}		
}