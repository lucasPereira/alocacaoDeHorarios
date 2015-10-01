package br.ufsc.sar;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Random;

import org.junit.*;
import org.junit.runners.MethodSorters;

import br.ufsc.ine.leb.projetos.estoria.*;
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

@FixtureSetup(BaseDeDadosZeradaTest.class)
public class EventoTest {

	@Fixture private EventoService eventoService;

	private Evento evento;

	@Before
	public void configurar() throws Exception {
		evento = new Evento();
		evento.setNome("Evento");
		eventoService.incluir(evento);
		evento = eventoService.getEntity(evento.getId());
	}

	@Test
	public void inserido() throws Exception {
		assertNotNull(evento.getId());
		assertEquals("Evento", evento.getNome());
		assertNull(evento.getDescricao());
	}

	@Test
	public void listar() throws Exception {
		List<Evento> eventos = eventoService.getList();

		assertEquals(1, eventos.size());
		assertEquals(evento.getId(), eventos.get(0).getId());
		assertEquals(evento.getNome(), eventos.get(0).getNome());
		assertEquals(evento.getDescricao(), eventos.get(0).getDescricao());
	}

	@Test
	public void alterar() throws Exception {
		evento.setNome("Evento alterado");
		eventoService.alterar(evento);
		Evento eventoAlterado = eventoService.getEntity(evento.getId());

		assertEquals(evento.getId(), eventoAlterado.getId());
		assertEquals("Evento alterado", eventoAlterado.getNome());
		assertEquals(evento.getDescricao(), eventoAlterado.getDescricao());
	}

	@Test
	public void remover() throws Exception {
		eventoService.excluir(evento.getId());
		Evento eventoRemovido = eventoService.getEntity(evento.getId());

		assertNull(eventoRemovido);
	}

	@Test
	public void test4_getEventoCaracteristicas() throws Exception {
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

	// @Test
	// public void test5_getEventoHorarios() throws Exception {
	// Evento evento = (Evento) eventoService.getEntity(createdId);
	// assertNotNull(evento);
	// HorarioEvento he = new HorarioEvento();
	// he.setDiadasemana("Segunda");
	// he.setEvento(evento);
	// Long heId = horarioEventoService.incluir(he);
	// assertNotNull(heId);
	// createdHorarioEvento = (HorarioEvento)
	// horarioEventoService.getEntity(heId);
	// assertNotNull(createdHorarioEvento);
	// List<HorarioEvento> horarioEventos =
	// eventoService.getEventoHorarios(evento);
	// assertNotNull(horarioEventos);
	// assertFalse(horarioEventos.isEmpty());
	// for (HorarioEvento horarioEvento : horarioEventos) {
	// assertNotNull(horarioEvento.getDiadasemana());
	// assertNotNull(horarioEvento.getEvento());
	// assertTrue(horarioEvento.getEvento().equals(evento));
	// }
	//
	// Long createdHorarioEventoId = createdHorarioEvento.getId();
	// HorarioEvento horarioEvento = (HorarioEvento)
	// horarioEventoService.getEntity(createdHorarioEventoId);
	// assertNotNull(horarioEvento);
	// assertTrue(horarioEventoService.excluir(createdHorarioEventoId));
	// horarioEvento = (HorarioEvento)
	// horarioEventoService.getEntity(createdHorarioEventoId);
	// assertNull(horarioEvento);
	// }

	// @Test
	// public void test6_getEventoProfissionais() throws Exception {
	// Evento evento = (Evento) eventoService.getEntity(createdId);
	// assertNotNull(evento);
	// EventoProfissional ep = new EventoProfissional();
	// ep.setEvento(evento);
	// ep.setProfissional(createdProfissional);
	// Long epId = eventoProfissionalService.incluir(ep);
	// assertNotNull(epId);
	// createdEventoProfissional = (EventoProfissional)
	// eventoProfissionalService.getEntity(epId);
	// assertNotNull(createdEventoProfissional);
	// List<EventoProfissional> eventoProfissionais =
	// eventoService.getEventoProfissionais(evento);
	// assertNotNull(eventoProfissionais);
	// assertFalse(eventoProfissionais.isEmpty());
	// for (EventoProfissional eventoProfissional : eventoProfissionais) {
	// assertNotNull(eventoProfissional.getProfissional());
	// assertTrue(eventoProfissional.getProfissional().getId().equals(createdProfissional.getId()));
	// assertNotNull(eventoProfissional.getEvento());
	// assertTrue(eventoProfissional.getEvento().equals(evento));
	// }
	//
	// Long createdEventoProfissionalId = createdEventoProfissional.getId();
	// EventoProfissional eventoProfissional = (EventoProfissional)
	// eventoProfissionalService.getEntity(createdEventoProfissionalId);
	// assertNotNull(eventoProfissional);
	// assertTrue(eventoProfissionalService.excluir(createdEventoProfissionalId));
	// eventoProfissional = (EventoProfissional)
	// eventoProfissionalService.getEntity(createdEventoProfissionalId);
	// assertNull(eventoProfissional);
	// }

	// @Test
	// public void test7_DeleteEvento() throws Exception {
	// Evento evento = (Evento) eventoService.getEntity(createdId);
	// assertNotNull(evento);
	// assertTrue(eventoService.excluir(createdId));
	// evento = (Evento) eventoService.getEntity(createdId);
	// assertNull(evento);
	// }

	// @After
	// public void test8_tearDown() throws Exception {
	// Long createdCaracteristicaId = createdCaracteristica.getId();
	// Caracteristica caracteristica = (Caracteristica)
	// caracteristicaService.getEntity(createdCaracteristicaId);
	// assertNotNull(caracteristica);
	// assertTrue(caracteristicaService.excluir(createdCaracteristicaId));
	// caracteristica = (Caracteristica)
	// caracteristicaService.getEntity(createdCaracteristicaId);
	// assertNull(caracteristica);
	// Long createdProfissionalId = createdProfissional.getId();
	// Profissional profissional = (Profissional)
	// profissionalService.getEntity(createdProfissionalId);
	// assertNotNull(profissional);
	// assertTrue(profissionalService.excluir(createdProfissionalId));
	// profissional = (Profissional)
	// profissionalService.getEntity(createdProfissionalId);
	// assertNull(profissional);
	// }

}
