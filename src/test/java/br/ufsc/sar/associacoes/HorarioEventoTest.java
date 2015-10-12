package br.ufsc.sar.associacoes;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.ufsc.sar.entity.Evento;
import br.ufsc.sar.entity.HorarioEvento;
import br.ufsc.sar.service.EventoService;
import br.ufsc.sar.service.HorarioEventoService;
import br.ufsc.sar.serviceimpl.EventoServiceImpl;
import br.ufsc.sar.serviceimpl.HorarioEventoServiceImpl;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HorarioEventoTest {

	final static EventoService eventoService = new EventoServiceImpl();
	final static HorarioEventoService horarioEventoService = new HorarioEventoServiceImpl();
	public static Evento createdEvento;
	public static Long createdHorarioEventoId;

	@Test
	public void test0_setUp() throws Exception {
		System.out.println("Setup");
		Evento pr = new Evento();
		pr.setNome("Evento " + (Math.abs(new Random().nextInt())));
		long createdEventoId = eventoService.incluir(pr);
		assertNotNull(createdEventoId);
		createdEvento = (Evento) eventoService.getEntity(createdEventoId);
		assertNotNull(createdEvento);
	}

	@Test
	// @Ignore
	public void test1_InsertHorarioEvento() throws Exception {
		System.out.println("Inserir");
		HorarioEvento hrEvento = new HorarioEvento();
		hrEvento.setEvento(createdEvento);
		hrEvento.setDatainicio(new Date());
		hrEvento.setDatatermino(new Date());
		hrEvento.setDiadasemana("Domingo");
		hrEvento.setHorainicio("16:00");
		hrEvento.setHoratermino("18:00");
		createdHorarioEventoId = horarioEventoService.incluir(hrEvento);
		assertNotNull(createdHorarioEventoId);
	}

	@Test
	public void test2_AlterarHorarioEvento() throws Exception {
		System.out.println("Alterar");
		HorarioEvento hrEvento = (HorarioEvento) horarioEventoService.getEntity(createdHorarioEventoId);
		assertNotNull(hrEvento);
		System.out.println("Nome:" + hrEvento.getEvento().getNome());
		System.out.println("Dia da semana:" + hrEvento.getDiadasemana());
		hrEvento.setDiadasemana("Sábado");
		assertTrue(horarioEventoService.alterar(hrEvento));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void test3_ListHorarioEvento() throws Exception {
		System.out.println("Listar");
		List<HorarioEvento> hrsEvento = (List<HorarioEvento>) horarioEventoService.getList();
		assertNotNull(hrsEvento);
		assertFalse(hrsEvento.isEmpty());
		HorarioEvento hrEvento = hrsEvento.get(0);
		assertNotNull(hrEvento);
		System.out.println("Nome:" + hrEvento.getEvento().getNome());
		System.out.println("Dia da semana:" + hrEvento.getDiadasemana());
		System.out.println("Data de início:" + hrEvento.getDatainicio());
		System.out.println("Data de término:" + hrEvento.getDatatermino());
		System.out.println("Hora de início:" + hrEvento.getHorainicio());
		System.out.println("Hora de término:" + hrEvento.getHoratermino());
		assertNotNull(hrEvento.getEvento());
		assertNotNull(hrEvento.getDiadasemana());
		assertFalse(hrEvento.getDiadasemana().trim().isEmpty());
	}

	@Test
	// @Ignore
	public void test4_DeleteHorarioEvento() throws Exception {
		System.out.println("Deletar");
		HorarioEvento hrEvento = (HorarioEvento) horarioEventoService.getEntity(createdHorarioEventoId);
		assertNotNull(hrEvento);
		assertTrue(horarioEventoService.excluir(createdHorarioEventoId));
		hrEvento = (HorarioEvento) horarioEventoService.getEntity(createdHorarioEventoId);
		assertNull(hrEvento);
	}

	@After
	public void test5_tearDown() throws Exception {
		System.out.println("Tear down");
		Long createdEventoId = createdEvento.getId();
		Evento evento = (Evento) eventoService.getEntity(createdEventoId);
		assertNotNull(evento);
		assertTrue(eventoService.excluir(createdEventoId));
		evento = (Evento) eventoService.getEntity(createdEventoId);
		assertNull(evento);
	}

}
