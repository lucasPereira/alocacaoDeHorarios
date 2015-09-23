package br.ufsc.sar;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.ufsc.sar.entity.Caracteristica;
import br.ufsc.sar.entity.Evento;
import br.ufsc.sar.entity.EventoCaracteristica;
import br.ufsc.sar.entity.EventoProfissional;
import br.ufsc.sar.entity.HorarioEvento;
import br.ufsc.sar.entity.Profissional;
import br.ufsc.sar.service.CaracteristicaService;
import br.ufsc.sar.service.EventoCaracteristicaService;
import br.ufsc.sar.service.EventoProfissionalService;
import br.ufsc.sar.service.EventoService;
import br.ufsc.sar.service.HorarioEventoService;
import br.ufsc.sar.service.ProfissionalService;
import br.ufsc.sar.serviceimpl.CaracteristicaServiceImpl;
import br.ufsc.sar.serviceimpl.EventoCaracteristicaServiceImpl;
import br.ufsc.sar.serviceimpl.EventoProfissionalServiceImpl;
import br.ufsc.sar.serviceimpl.EventoServiceImpl;
import br.ufsc.sar.serviceimpl.HorarioEventoServiceImpl;
import br.ufsc.sar.serviceimpl.ProfissionalServiceImpl;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EventoTest {

	final static EventoService eventoService = new EventoServiceImpl();
	final static CaracteristicaService caracteristicaService = new CaracteristicaServiceImpl();
	final static HorarioEventoService horarioEventoService = new HorarioEventoServiceImpl();
	final static EventoCaracteristicaService eventoCaracteristicaService = new EventoCaracteristicaServiceImpl();
	final static ProfissionalService profissionalService = new ProfissionalServiceImpl();
	final static EventoProfissionalService eventoProfissionalService = new EventoProfissionalServiceImpl();
	public static Caracteristica createdCaracteristica;
	public static EventoCaracteristica createdEventoCaracteristica;
	public static HorarioEvento createdHorarioEvento;
	public static Profissional createdProfissional;
	public static EventoProfissional createdEventoProfissional;
	public static Long createdId;

	@Test
	public void test0_setUp() throws Exception {
		System.out.println("Setup");
		Caracteristica c = new Caracteristica();
		c.setNome("Caracteristica " + (Math.abs(new Random().nextInt())));
		long createdCaracteristicaId = caracteristicaService.incluir(c);
		assertNotNull(createdCaracteristicaId);
		createdCaracteristica = (Caracteristica) caracteristicaService.getEntity(createdCaracteristicaId);
		assertNotNull(createdCaracteristica);

		Profissional p = new Profissional();
		p.setNome("Profissional " + (Math.abs(new Random().nextInt())));
		p.setProfissao("Analista de Sistemas");
		long createdProfissionalId = profissionalService.incluir(p);
		assertNotNull(createdProfissionalId);
		createdProfissional = (Profissional) profissionalService.getEntity(createdProfissionalId);
		assertNotNull(createdProfissional);
	}

	@Test
	// @Ignore
	public void test1_InsertEvento() throws Exception {
		System.out.println("Inserir");
		Evento evento = new Evento();
		evento.setNome("Evento " + (Math.abs(new Random().nextInt())));
		createdId = eventoService.incluir(evento);
		assertNotNull(createdId);
	}

	@Test
	public void test2_AlterarEvento() throws Exception {
		System.out.println("Alterar");
		Evento evento = (Evento) eventoService.getEntity(createdId);
		assertNotNull(evento);
		System.out.println("Nome:" + evento.getNome());
		evento.setNome("Evento " + (Math.abs(new Random().nextInt())));
		assertTrue(eventoService.alterar(evento));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void test3_ListEvento() throws Exception {
		System.out.println("Listar");
		List<Evento> eventos = (List<Evento>) eventoService.getList();
		assertNotNull(eventos);
		assertFalse(eventos.isEmpty());
		Evento evento = eventos.get(0);
		assertNotNull(evento);
		System.out.println("Nome:" + evento.getNome());
		assertNotNull(evento.getNome());
		assertFalse(evento.getNome().trim().isEmpty());
	}

	@Test
	public void test4_getEventoCaracteristicas() throws Exception {
		System.out.println("getEventoCaracteristicas");
		Evento evento = (Evento) eventoService.getEntity(createdId);
		assertNotNull(evento);
		EventoCaracteristica ec = new EventoCaracteristica();
		ec.setCaracteristica(createdCaracteristica);
		ec.setEvento(evento);
		ec.setQuantidade(1);
		Long ecId = eventoCaracteristicaService.incluir(ec);
		assertNotNull(ecId);
		createdEventoCaracteristica = (EventoCaracteristica) eventoCaracteristicaService.getEntity(ecId);
		assertNotNull(createdEventoCaracteristica);
		List<EventoCaracteristica> eventoCaracteristicas = eventoService.getEventoCaracteristicas(evento);
		assertNotNull(eventoCaracteristicas);
		assertFalse(eventoCaracteristicas.isEmpty());
		for (EventoCaracteristica eventoCaracteristica : eventoCaracteristicas) {
			assertNotNull(eventoCaracteristica.getCaracteristica());
			assertNotNull(eventoCaracteristica.getEvento());
			assertTrue(eventoCaracteristica.getEvento().equals(evento));
		}

		Long createdEventoCaracteristicaId = createdEventoCaracteristica.getId();
		EventoCaracteristica eventoCaracteristica = (EventoCaracteristica) eventoCaracteristicaService.getEntity(createdEventoCaracteristicaId);
		assertNotNull(eventoCaracteristica);
		assertTrue(eventoCaracteristicaService.excluir(createdEventoCaracteristicaId));
		eventoCaracteristica = (EventoCaracteristica) eventoCaracteristicaService.getEntity(createdEventoCaracteristicaId);
		assertNull(eventoCaracteristica);
	}

	@Test
	public void test5_getEventoHorarios() throws Exception {
		System.out.println("getEventoHorarios");
		Evento evento = (Evento) eventoService.getEntity(createdId);
		assertNotNull(evento);
		HorarioEvento he = new HorarioEvento();
		he.setDiadasemana("Segunda");
		he.setEvento(evento);
		Long heId = horarioEventoService.incluir(he);
		assertNotNull(heId);
		createdHorarioEvento = (HorarioEvento) horarioEventoService.getEntity(heId);
		assertNotNull(createdHorarioEvento);
		List<HorarioEvento> horarioEventos = eventoService.getEventoHorarios(evento);
		assertNotNull(horarioEventos);
		assertFalse(horarioEventos.isEmpty());
		for (HorarioEvento horarioEvento : horarioEventos) {
			assertNotNull(horarioEvento.getDiadasemana());
			assertNotNull(horarioEvento.getEvento());
			assertTrue(horarioEvento.getEvento().equals(evento));
		}

		Long createdHorarioEventoId = createdHorarioEvento.getId();
		HorarioEvento horarioEvento = (HorarioEvento) horarioEventoService.getEntity(createdHorarioEventoId);
		assertNotNull(horarioEvento);
		assertTrue(horarioEventoService.excluir(createdHorarioEventoId));
		horarioEvento = (HorarioEvento) horarioEventoService.getEntity(createdHorarioEventoId);
		assertNull(horarioEvento);
	}

	@Test
	public void test6_getEventoProfissionais() throws Exception {
		System.out.println("getEventoProfissionais");
		Evento evento = (Evento) eventoService.getEntity(createdId);
		assertNotNull(evento);
		EventoProfissional ep = new EventoProfissional();
		ep.setEvento(evento);
		ep.setProfissional(createdProfissional);
		Long epId = eventoProfissionalService.incluir(ep);
		assertNotNull(epId);
		createdEventoProfissional = (EventoProfissional) eventoProfissionalService.getEntity(epId);
		assertNotNull(createdEventoProfissional);
		List<EventoProfissional> eventoProfissionais = eventoService.getEventoProfissionais(evento);
		assertNotNull(eventoProfissionais);
		assertFalse(eventoProfissionais.isEmpty());
		for (EventoProfissional eventoProfissional : eventoProfissionais) {
			assertNotNull(eventoProfissional.getProfissional());
			assertTrue(eventoProfissional.getProfissional().getId().equals(createdProfissional.getId()));
			assertNotNull(eventoProfissional.getEvento());
			assertTrue(eventoProfissional.getEvento().equals(evento));
		}

		Long createdEventoProfissionalId = createdEventoProfissional.getId();
		EventoProfissional eventoProfissional = (EventoProfissional) eventoProfissionalService.getEntity(createdEventoProfissionalId);
		assertNotNull(eventoProfissional);
		assertTrue(eventoProfissionalService.excluir(createdEventoProfissionalId));
		eventoProfissional = (EventoProfissional) eventoProfissionalService.getEntity(createdEventoProfissionalId);
		assertNull(eventoProfissional);
	}

	@Test
	// @Ignore
	public void test7_DeleteEvento() throws Exception {
		System.out.println("Deletar");
		Evento evento = (Evento) eventoService.getEntity(createdId);
		assertNotNull(evento);
		assertTrue(eventoService.excluir(createdId));
		evento = (Evento) eventoService.getEntity(createdId);
		assertNull(evento);
	}

	@After
	public void test8_tearDown() throws Exception {
		System.out.println("Tear down");

		Long createdCaracteristicaId = createdCaracteristica.getId();
		Caracteristica caracteristica = (Caracteristica) caracteristicaService.getEntity(createdCaracteristicaId);
		assertNotNull(caracteristica);
		assertTrue(caracteristicaService.excluir(createdCaracteristicaId));
		caracteristica = (Caracteristica) caracteristicaService.getEntity(createdCaracteristicaId);
		assertNull(caracteristica);

		Long createdProfissionalId = createdProfissional.getId();
		Profissional profissional = (Profissional) profissionalService.getEntity(createdProfissionalId);
		assertNotNull(profissional);
		assertTrue(profissionalService.excluir(createdProfissionalId));
		profissional = (Profissional) profissionalService.getEntity(createdProfissionalId);
		assertNull(profissional);
	}

}
