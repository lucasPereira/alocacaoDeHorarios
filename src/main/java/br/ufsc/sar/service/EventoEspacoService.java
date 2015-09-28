package br.ufsc.sar.service;

import java.util.List;

import br.ufsc.sar.entity.Espaco;
import br.ufsc.sar.entity.Evento;
import br.ufsc.sar.entity.EventoEspaco;
import br.ufsc.service.BaseService;
import br.ufsc.util.type.StatusVerificacao;

/**
 * 
 * @author João
 *
 */
public interface EventoEspacoService extends BaseService<EventoEspaco>{
	
	public List<Espaco> getEspacosNaoAssociadosAoEvento(Evento e);
	
	public StatusVerificacao verificarAssociacaoEspacoAoEvento(Evento evento, Espaco espaco);
}
