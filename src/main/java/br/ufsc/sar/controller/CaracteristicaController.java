package br.ufsc.sar.controller;

import br.ufsc.sar.entity.Caracteristica;
import br.ufsc.sar.gui.CaracteristicaGUI;
import br.ufsc.sar.service.CaracteristicaService;
import br.ufsc.sar.serviceimpl.CaracteristicaServiceImpl;

public class CaracteristicaController {

	private static CaracteristicaGUI caracteristicaGUI = null;
	
	public CaracteristicaController(CaracteristicaGUI caracteristicaGUI) {
		CaracteristicaController.caracteristicaGUI = caracteristicaGUI;
	}

	final static CaracteristicaService service = new CaracteristicaServiceImpl();
	
	public static CaracteristicaGUI getCaracteristicaGUI() {
		return caracteristicaGUI;
	}

	public void adicionarLinha() {
		CaracteristicaController.getCaracteristicaGUI().getModeloTabelaCaracteristicas().addEmptyRow(new Caracteristica());	
	}
	
	
}
