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
		List<HorarioEvento> listaHorarios = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("select h ");
		sql.append("from HorarioEvento h ");
		sql.append("where h.evento = :evento");
		
		Query query = super.getEntityManager().createQuery(sql.toString());
		query.setParameter("evento", evento);
		
		try {
			listaHorarios = (List<HorarioEvento>) query.getResultList();
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
