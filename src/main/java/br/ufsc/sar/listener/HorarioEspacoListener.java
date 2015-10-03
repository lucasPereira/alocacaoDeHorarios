/**
 * 
 */
package br.ufsc.sar.listener;

import br.ufsc.sar.entity.HorarioEspaco;
import br.ufsc.sar.gui.HorarioEspacoGUI;

/**
 * @author João
 *
 */
public class HorarioEspacoListener extends EntityListener<HorarioEspaco> {

	public HorarioEspacoListener(HorarioEspacoGUI espacoGUI) {
		super(espacoGUI);
	}

	@Override
	public String getEntityName() {
		return HorarioEspaco.class.getName();
	}

	@Override
	public HorarioEspacoListener.EntityTableListener<HorarioEspaco> getEntityTableListener() {
		return new EntityTableListener<HorarioEspaco>();
	}
}