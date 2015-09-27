package br.ufsc.sar.service;

import java.util.List;

import br.ufsc.sar.entity.Caracteristica;
import br.ufsc.sar.entity.Evento;
import br.ufsc.sar.entity.EventoCaracteristica;
import br.ufsc.service.BaseService;

/**
 * 
 * @author João
 *
 */
public interface EventoCaracteristicaService extends BaseService<EventoCaracteristica>{

	List<Caracteristica> getCaracteristicasNaoAssociadosAoEvento(Evento evento);

}
