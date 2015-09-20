package br.ufsc.sar.service;

import java.util.List;

import br.ufsc.sar.entity.Evento;
import br.ufsc.sar.entity.EventoCaracteristica;
import br.ufsc.sar.entity.EventoProfissional;
import br.ufsc.sar.entity.HorarioEvento;
import br.ufsc.service.BaseService;

public interface EventoService extends BaseService<Evento>{
	
	public List<HorarioEvento> getEventoHorarios(Evento evento);
	
	public List<EventoCaracteristica> getEventoCaracteristicas(Evento evento);
	
	public List<EventoProfissional> getEventoProfissionais(Evento evento);

}
