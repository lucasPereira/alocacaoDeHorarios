/**
 * 
 */
package br.ufsc.sar.listner;

import br.ufsc.sar.entity.HorarioProfissional;
import br.ufsc.sar.gui.HorarioProfissionalGUI;

/**
 * @author João
 *
 */
public class HorarioProfissionalListener extends EntityListener<HorarioProfissional> {

	public HorarioProfissionalListener(HorarioProfissionalGUI profissionalGUI) {
		super(profissionalGUI);
	}

	@Override
	public String getEntityName() {
		return HorarioProfissional.class.getName();
	}

	@Override
	public HorarioProfissionalListener.EntityTableListener<HorarioProfissional> getEntityTableListener() {
		return new EntityTableListener<HorarioProfissional>();
	}
}