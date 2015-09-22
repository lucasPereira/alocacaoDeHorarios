/**
 * 
 */
package br.ufsc.sar.listener;

import br.ufsc.sar.entity.Espaco;
import br.ufsc.sar.gui.EspacoGUI;

/**
 * @author ErnaniCésar
 *
 */
public class EspacoListener extends EntityListener<Espaco> {

	public EspacoListener(EspacoGUI espacoGUI) {
		super(espacoGUI);
	}

	@Override
	public String getEntityName() {
		return Espaco.class.getName();
	}

	@Override
	public EspacoListener.EntityTableListener<Espaco> getEntityTableListener() {
		return new EntityTableListener<Espaco>();
	}
}