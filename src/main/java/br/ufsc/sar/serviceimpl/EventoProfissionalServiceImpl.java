package br.ufsc.sar.serviceimpl;

import java.util.List;

import br.ufsc.sar.entity.Evento;
import br.ufsc.sar.entity.EventoProfissional;
import br.ufsc.sar.entity.Profissional;
import br.ufsc.sar.service.EventoProfissionalService;
import br.ufsc.serviceimpl.BaseServiceImpl;

/**
 * 
 * @author João
 *
 */
public class EventoProfissionalServiceImpl extends BaseServiceImpl<EventoProfissional> implements EventoProfissionalService{

	@SuppressWarnings("unchecked")
	@Override
	public List<Profissional> getProfissinaisNaoAssociadosAoEvento(Evento e) {
		
		//select * from profissional p where not exists (select 1 from evento_profissional x where x.idprofissional = p.id);
				
		return getEntityManager().createNativeQuery("SELECT p.* FROM profissional p " +
											  " WHERE not exists (select 1 from evento_profissional x " +
											  					  "where x.idevento = " + e.getId().longValue() +
										  					      "  and x.idprofissional = p.id)", Profissional.class).getResultList();
		
	}
	
}
