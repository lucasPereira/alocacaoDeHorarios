package br.ufsc.sar.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.ufsc.sar.entity.Caracteristica;
import br.ufsc.sar.entity.Espaco;
import br.ufsc.sar.entity.Evento;
import br.ufsc.sar.entity.EventoEspaco;
import br.ufsc.sar.entity.Horario;
import br.ufsc.sar.entity.HorarioEspaco;
import br.ufsc.sar.entity.HorarioProfissional;
import br.ufsc.sar.entity.Profissional;
import br.ufsc.sar.service.EventoCaracteristicaService;
import br.ufsc.sar.service.EventoEspacoService;
import br.ufsc.sar.service.EventoProfissionalService;
import br.ufsc.sar.service.HorarioEspacoService;
import br.ufsc.sar.service.HorarioProfissionalService;
import br.ufsc.serviceimpl.BaseServiceImpl;
import br.ufsc.util.type.StatusVerificacao;

/**
 * 
 * @author João
 *
 */
public class EventoEspacoServiceImpl extends BaseServiceImpl<EventoEspaco> implements EventoEspacoService{
	
	private EventoCaracteristicaService eventoCaracteristicaService = new EventoCaracteristicaServiceImpl();
	
	private EventoProfissionalService eventoProfissionalService = new EventoProfissionalServiceImpl();
	
	private HorarioProfissionalService horarioProfissionalService = new HorarioProfissionalServiceImpl();
	
	private HorarioEspacoService horarioEspacoService = new HorarioEspacoServiceImpl();

	@SuppressWarnings("unchecked")
	@Override
	public List<Espaco> getEspacosNaoAssociadosAoEvento(Evento e) {
		
		//select * from profissional p where not exists (select 1 from evento_profissional x where x.idprofissional = p.id);
				
		return getEntityManager().createNativeQuery("SELECT p.* FROM local p " +
											  " WHERE not exists (select 1 from evento_espaco x " +
											  					  "where x.idevento = " + e.getId().longValue() +
										  					      "  and x.idespaco = p.id)", Espaco.class).getResultList();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Evento> getAgendaEventosPorEspaco(Espaco espaco){
		List<Evento> listEventos = new ArrayList<Evento>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ev ");
		sql.append(" FROM EventoEspaco ee");
		sql.append(" INNER JOIN ee.evento ev");
		sql.append(" INNER JOIN ee.espaco e");
		sql.append(" Where e.id = :idEspaco");
		
		Query query = super.getEntityManager().createQuery(sql.toString());
		query.setParameter("idEspaco", espaco.getId());
		
		try {
			listEventos = (List<Evento>) query.getResultList();
		} catch (NoResultException e) {
			//System.out.println("CaracteristicaServiceImpl.getListAtivas()");
			//System.out.println(":: Nenhum resultado encontrado ::");
		}
		
		return listEventos;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public StatusVerificacao verificarAssociacaoEspacoAoEvento(Evento evento, Espaco espaco) {
		StatusVerificacao statusVerificacao = new StatusVerificacao();
		/*
		 * Para associar espaço(s) ao evento, deve-se levar em consideração as características escolhidas
         * para o evento, e também os horários dos profissionais escolhidos para o evento. Desta forma,
         * apenas poderão ser cadastrados como espaços do evento, aqueles que possuírem características
         * que o evento precisar e horários "disponíveis" compatíveis com os horários dos profissionais do evento.
		 */
		boolean sucesso = true;
		if(espaco != null && evento != null) {
			List<Caracteristica> caracteristicasEvento = (List<Caracteristica>) this.eventoCaracteristicaService.getCaracteristicasEvento(evento);
			List<Caracteristica> caracteristicasEspaco = espaco.getCaracteristicas();			
			if(caracteristicasEvento == null || caracteristicasEvento.isEmpty()) {
				//System.out.println("\nSem características do espaço e/ou de evento cadastradas\n");
				statusVerificacao.setMensagemResultado("Sem características do espaço e/ou de evento cadastradas");
			}
			else {
//				Caracteristica c = new Caracteristica();
//				c.setId((long) 28);
//				c.setNome("Ar condicionado");
//				caracteristicasEspaco.add(c);
				//System.out.println("\nComparando características do espaço e do evento\n");
				if(!caracteristicasEspaco.containsAll(caracteristicasEvento)){
					//System.out.println("\nCaracterísticas do espaço e do evento não compatíveis\n");					
					statusVerificacao.setMensagemResultado("Características do espaço e do evento não compatíveis");
					sucesso = false;
				}
				else {
					//System.out.println("\nCaracterísticas encontradas\n");
					// Características válidas										
					
					List<Profissional> profissionaisEvento = (List<Profissional>) this.eventoProfissionalService.getProfissionaisEvento(evento);
					List<HorarioEspaco> horariosEspaco = (List<HorarioEspaco>) this.horarioEspacoService.getList("espaco.id = " + espaco.getId().longValue());
					if(profissionaisEvento == null || profissionaisEvento.isEmpty() || 
					   horariosEspaco == null || horariosEspaco.isEmpty()) 
					{
						//System.out.println("\nSem horários cadastrados para espaço e/ou profissionais\n");
						statusVerificacao.setMensagemResultado("Sem horários cadastrados para espaço e/ou profissionais");
					}
					else {
						// Verificar horários do(s) profissional(is)
						for (Profissional profissional : profissionaisEvento) {
							List<HorarioProfissional> horariosProfissional = (List<HorarioProfissional>) this.horarioProfissionalService.getList("profissional.id = " + profissional.getId().longValue());
							if(horariosProfissional == null) {	
								//System.out.println("\nProfissional " + profissional.getId() + "sem horários cadastrados\n");
								statusVerificacao.setMensagemResultado("Profissional " + profissional.getId() + "sem horários cadastrados");
							}
							else {
								if(!verificarListaHorarios(horariosEspaco, horariosProfissional)){
									//System.out.println("\nProfissional sem horário compatível com os horários do espaço\n");
									statusVerificacao.setMensagemResultado("Profissional " + profissional.getId() + " sem horário compatível com os horários do espaço");
									statusVerificacao.setBooleanResultado(false);
									return statusVerificacao;
								}
							}
						}						
					}
				}
			}			
		}
		
		statusVerificacao.setBooleanResultado(sucesso);
		return statusVerificacao;
	}

	/**
	 * 
	 * @param horariosEspaco
	 * @param horariosProfissional
	 * @return
	 */
	private boolean verificarListaHorarios(List<HorarioEspaco> horariosEspaco,List<HorarioProfissional> horariosProfissional) {
		int qtdeHorariosEncontrados = 0;
		boolean algumHorario = false;
		//System.out.println("\nComparando horários do espaço e do profissional\n");
		for (HorarioProfissional horarioProfissional : horariosProfissional) {
			algumHorario = false;
			for (HorarioEspaco horarioEspaco : horariosEspaco) {
				if(verificarHorarios(horarioEspaco, horarioProfissional)){
					qtdeHorariosEncontrados++;
					algumHorario = true;
				}				
			}
			if(!algumHorario){
				//System.out.println("\nEncontrado profissional sem horário compatível com os horários do espaço\n");
				return false;
			}
		}
				
		return qtdeHorariosEncontrados == horariosProfissional.size();
	}

	/**
	 * 
	 * @param h1
	 * @param h2
	 * @return
	 */
	private boolean verificarHorarios(Horario h1, Horario h2) {
		if(h1 == null || h2 == null ){
			return false;
		}
		if (h1.getDatainicio() == null) {
			if (h2.getDatainicio() != null) {
				return false;
			}
		} else if (!h1.getDatainicio().equals(h2.getDatainicio())) {
			return false;
		}
		if (h1.getDatatermino() == null) {
			if (h2.getDatatermino() != null) {
				return false;
			}
		} else if (!h1.getDatatermino().equals(h2.getDatatermino())) {
			return false;
		}
		if (h1.getDiadasemana() == null) {
			if (h2.getDiadasemana() != null) {
				return false;
			}
		} else if (!h1.getDiadasemana().equals(h2.getDiadasemana())) {
			return false;
		}
		if (h1.getHorainicio() == null) {
			if (h2.getHorainicio() != null) {
				return false;
			}
		} else if (!h1.getHorainicio().equals(h2.getHorainicio())) {
			return false;
		}
		if (h1.getHoratermino() == null) {
			if (h2.getHoratermino() != null) {
				return false;
			}
		} else if (!h1.getHoratermino().equals(h2.getHoratermino())) {
			return false;
		}
		
		return true;		
	}	
}