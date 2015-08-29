/**
 * 
 */
package br.ufsc.sar.listner;

import br.ufsc.sar.entity.Profissional;
import br.ufsc.sar.gui.ProfissionalGUI;

/**
 * @author João
 *
 */
public class ProfissionalListener extends EntityListener<Profissional> {

	public ProfissionalListener(ProfissionalGUI profissionalGUI) {
		super(profissionalGUI);
	}

	@Override
	public String getEntityName() {
		return Profissional.class.getName();
	}

	@Override
	public ProfissionalListener.EntityTableListener<Profissional> getEntityTableListener() {
		return new EntityTableListener<Profissional>();
	}
}