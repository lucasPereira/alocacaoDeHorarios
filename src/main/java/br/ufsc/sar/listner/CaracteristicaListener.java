package br.ufsc.sar.listner;

import br.ufsc.sar.entity.Caracteristica;
import br.ufsc.sar.gui.CaracteristicaGUI;

public class CaracteristicaListener extends EntityListener<Caracteristica> {
	
	public CaracteristicaListener(CaracteristicaGUI caracteristicaGUI) {
		super(caracteristicaGUI);
	}

	@Override
	public String getEntityName() {
		return Caracteristica.class.getName();
	}

	@Override
	public EntityListener<Caracteristica>.EntityTableListener<Caracteristica> getEntityTableListener() {
		return new EntityTableListener<Caracteristica>();
	}

}
