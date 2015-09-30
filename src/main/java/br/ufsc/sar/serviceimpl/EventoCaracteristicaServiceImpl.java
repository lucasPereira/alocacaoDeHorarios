package br.ufsc.sar.serviceimpl;

import java.util.List;

import br.ufsc.sar.entity.Caracteristica;
import br.ufsc.sar.entity.Evento;
import br.ufsc.sar.entity.EventoCaracteristica;
import br.ufsc.sar.service.EventoCaracteristicaService;
import br.ufsc.serviceimpl.BaseServiceImpl;

/**
 * 
 * @author João
 *
 */
public class EventoCaracteristicaServiceImpl extends BaseServiceImpl<EventoCaracteristica> implements EventoCaracteristicaService{

	@SuppressWarnings("unchecked")
	@Override
	public List<Caracteristica> getCaracteristicasNaoAssociadosAoEvento(Evento e) {		
		return getEntityManager().createNativeQuery("SELECT c.* FROM caracteristica c " +
											  " WHERE not exists (select 1 from evento_caracteristica x " +
											  					  "where x.idevento = " + e.getId().longValue() +
										  					      "  and x.idcaracteristica = c.id)" +
										  	   "  AND (c.forauso is null or c.forauso = 0)", Caracteristica.class).getResultList();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Caracteristica> getCaracteristicasEvento(Evento evento) {
		return getEntityManager().createNativeQuery("SELECT c.* FROM caracteristica c " +
				  " WHERE exists (select 1 from evento_caracteristica x " +
				  					  "where x.idevento = " + evento.getId().longValue() +
			  					      "  and x.idcaracteristica = c.id)", Caracteristica.class).getResultList();
	}
	
}
