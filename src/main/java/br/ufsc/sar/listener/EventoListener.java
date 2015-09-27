/**
 * 
 */
package br.ufsc.sar.listener;

import br.ufsc.sar.entity.Evento;
import br.ufsc.sar.gui.EventoGUI;

/**
 * @author João
 *
 */
public class EventoListener extends EntityListener<Evento> {

	public EventoListener(EventoGUI eventoGUI) {
		super(eventoGUI);
	}

	@Override
	public String getEntityName() {
		return Evento.class.getName();
	}

	@Override
	public EventoListener.EntityTableListener<Evento> getEntityTableListener() {
		return new EntityTableListener<Evento>();
	}
}