package br.ufsc.sar.service;

import java.util.List;

import br.ufsc.sar.entity.Espaco;
import br.ufsc.sar.entity.Evento;
import br.ufsc.sar.entity.EventoEspaco;
import br.ufsc.service.BaseService;

/**
 * 
 * @author João
 *
 */
public interface EventoEspacoService extends BaseService<EventoEspaco>{
	
	public List<Espaco> getEspacosNaoAssociadosAoEvento(Evento e);
	
	public boolean verificarAssociacaoEspacoAoEvento(Evento evento, Espaco espaco);
}
