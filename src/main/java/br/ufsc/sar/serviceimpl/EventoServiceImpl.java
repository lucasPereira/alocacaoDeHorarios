package br.ufsc.sar.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.ufsc.sar.entity.Evento;
import br.ufsc.sar.entity.EventoCaracteristica;
import br.ufsc.sar.entity.EventoProfissional;
import br.ufsc.sar.entity.HorarioEvento;
import br.ufsc.sar.service.EventoService;
import br.ufsc.serviceimpl.BaseServiceImpl;

/**
 * 
 * @author João
 *
 */
public class EventoServiceImpl extends BaseServiceImpl<Evento> implements EventoService{

	@SuppressWarnings("unchecked")
	@Override
	public List<HorarioEvento> getEventoHorarios(Evento evento) {
		List<HorarioEvento> listaHorarios = new ArrayList<HorarioEvento>();
		
		StringBuilder sql = new StringBuilder();		
//		sql.append("select h ");
//		sql.append("from HorarioEvento h ");
//		sql.append("where h.evento = :evento");
//		Query query = super.getEntityManager().createQuery(sql.toString());
//		query.setParameter("evento", evento);
		
//		listaHorarios = (List<HorarioEvento>) query.getResultList();
		
//		mysql> SELECT datainicio, datatermino, diadasemana, horainicio, horatermino, @rownum := @rownum + 1 as row_number  FROM horario_profissional 
//				cross join (select
//		@rownum := 0) r  WHERE idprofissional in (SELECT idprofissional FROM evento_prof
//		issional WHERE idevento = 12)  UNION SELECT datainicio, datatermino, diadasemana
//		, horainicio, horatermino, 1  FROM horario_espaco  WHERE idespaco in (SELECT ide
//		spaco FROM evento_espaco WHERE idevento = 12);
		
		sql.append("SELECT datainicio, datatermino, diadasemana, horainicio, horatermino " +
				   "  FROM horario_profissional " +
				   " WHERE idprofissional in (SELECT idprofissional FROM evento_profissional WHERE idevento = " + evento.getId() + ") " +
				   " UNION " +
				   "SELECT datainicio, datatermino, diadasemana, horainicio, horatermino " +
				   "  FROM horario_espaco " +
				   " WHERE idespaco in (SELECT idespaco FROM evento_espaco WHERE idevento = " + evento.getId() + ")");
		Query query = super.getEntityManager().createNativeQuery(sql.toString());
		
		try {
			long id = 1;
			List<Object> resultados = query.getResultList();
			HorarioEvento he = null;
			for (Object object : resultados) {
				Object [] objectHorarioEvento = (Object []) object;
				System.out.println("T: " + objectHorarioEvento);
				he = new HorarioEvento();
				he.setId(id);
				he.setEvento(evento);
				id++;
				he.setDatainicio((java.util.Date)objectHorarioEvento[0]);
				he.setDatatermino((java.util.Date)objectHorarioEvento[1]);
				he.setDiadasemana((String)objectHorarioEvento[2]);
				he.setHorainicio((String)objectHorarioEvento[3]);
				he.setHoratermino((String)objectHorarioEvento[4]);
				System.out.println("HorarioEvento : " + he );
				listaHorarios.add(he);				
			}			
		} catch (NoResultException e) {
			System.out.println("EventoServiceImpl.getEventoHorarios(evento)");
			System.out.println(":: Nenhum resultado encontrado ::");
			listaHorarios = new ArrayList<HorarioEvento>();
		}
		
		return listaHorarios;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EventoCaracteristica> getEventoCaracteristicas(Evento evento) {
		List<EventoCaracteristica> listaCaracteristicas = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("select c ");
		sql.append("from EventoCaracteristica c ");
		sql.append("where c.evento = :evento");
		
		Query query = super.getEntityManager().createQuery(sql.toString());
		query.setParameter("evento", evento);
		
		try {
			listaCaracteristicas = (List<EventoCaracteristica>) query.getResultList();
		} catch (NoResultException e) {
			System.out.println("EventoServiceImpl.getEventoCaracteristicas(evento)");
			System.out.println(":: Nenhum resultado encontrado ::");
			listaCaracteristicas = new ArrayList<EventoCaracteristica>();
		}
		return listaCaracteristicas;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EventoProfissional> getEventoProfissionais(Evento evento) {
		List<EventoProfissional> listaProfissionais = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("select p ");
		sql.append("from EventoProfissional p ");
		sql.append("where p.evento = :evento");
		
		Query query = super.getEntityManager().createQuery(sql.toString());
		query.setParameter("evento", evento);
		
		try {
			listaProfissionais = (List<EventoProfissional>) query.getResultList();
		} catch (NoResultException e) {
			System.out.println("EventoServiceImpl.getEventoProfissionais(evento)");
			System.out.println(":: Nenhum resultado encontrado ::");
			listaProfissionais = new ArrayList<EventoProfissional>();
		}
		return listaProfissionais;
	}
	
}
