/**
 * 
 */
package br.ufsc.sar.listener;

import br.ufsc.sar.entity.Evento;
import br.ufsc.sar.entity.EventoProfissional;
import br.ufsc.sar.gui.EventoProfissionalGUI;

/**
 * @author João
 *
 */
public class EventoProfissionalListener extends EntityListener<EventoProfissional> {

	public EventoProfissionalListener(EventoProfissionalGUI eventoGUI) {
		super(eventoGUI);
	}

	@Override
	public String getEntityName() {
		return Evento.class.getName();
	}

	@Override
	public EventoProfissionalListener.EntityTableListener<EventoProfissional> getEntityTableListener() {
		return new EntityTableListener<EventoProfissional>();
	}
}