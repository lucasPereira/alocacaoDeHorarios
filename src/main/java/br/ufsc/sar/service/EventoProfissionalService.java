package br.ufsc.sar.service;

import java.util.List;

import br.ufsc.sar.entity.Evento;
import br.ufsc.sar.entity.EventoProfissional;
import br.ufsc.sar.entity.Profissional;
import br.ufsc.service.BaseService;

/**
 * 
 * @author João
 *
 */
public interface EventoProfissionalService extends BaseService<EventoProfissional>{
	
	public List<Profissional> getProfissinaisNaoAssociadosAoEvento(Evento e);

}
