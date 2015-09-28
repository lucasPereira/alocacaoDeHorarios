package br.ufsc.sar;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.ufsc.sar.entity.Espaco;
import br.ufsc.sar.entity.Evento;
import br.ufsc.sar.entity.EventoEspaco;
import br.ufsc.sar.service.EspacoService;
import br.ufsc.sar.service.EventoEspacoService;
import br.ufsc.sar.service.EventoService;
import br.ufsc.sar.serviceimpl.EspacoServiceImpl;
import br.ufsc.sar.serviceimpl.EventoEspacoServiceImpl;
import br.ufsc.sar.serviceimpl.EventoServiceImpl;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EventoEspacoTest {

	final static EventoEspacoService eventoEspacoService = new EventoEspacoServiceImpl();
	final static EventoService eventoService = new EventoServiceImpl();
	final static EspacoService espacoService = new EspacoServiceImpl();
	public static Evento createdEvento;
	public static Espaco createdEspaco;
	public static Long createdEventoEspacolId;

	@Test
	public void test0_setUp() throws Exception {
		System.out.println("Setup");
		Evento pr = new Evento();
		pr.setNome("Evento " + (Math.abs(new Random().nextInt())));
		long createdEventoId = eventoService.incluir(pr);
		assertNotNull(createdEventoId);
		createdEvento = (Evento) eventoService.getEntity(createdEventoId);
		assertNotNull(createdEvento);

		Espaco p = new Espaco();
		p.setNome("Espaco " + (Math.abs(new Random().nextInt())));
		p.setDescricao("Espaco teste");
		p.setCapacidade((long)1);
		long createdEspacoId = espacoService.incluir(p);
		assertNotNull(createdEspacoId);
		createdEspaco = (Espaco) espacoService.getEntity(createdEspacoId);
		assertNotNull(createdEspaco);
	}

	@Test
	// @Ignore
	public void test1_InsertEventoEspaco() throws Exception {
		System.out.println("Inserir");
		EventoEspaco eventoEspacoEntity = new EventoEspaco();
		eventoEspacoEntity.setEvento(createdEvento);
		eventoEspacoEntity.setEspaco(createdEspaco);
		createdEventoEspacolId = eventoEspacoService.incluir(eventoEspacoEntity);
		assertNotNull(createdEventoEspacolId);
	}

	@Test
	public void test2_AlterarEventoEspaco() throws Exception {
		System.out.println("Alterar");
		EventoEspaco hrEventoProfissional = (EventoEspaco) eventoEspacoService.getEntity(createdEventoEspacolId);
		assertNotNull(hrEventoProfissional);
		System.out.println("Nome evento:" + hrEventoProfissional.getEvento().getNome());
		System.out.println("Nome espaco:" + hrEventoProfissional.getEspaco().getNome());
		assertTrue(eventoEspacoService.alterar(hrEventoProfissional));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void test3_ListEventoEspaco() throws Exception {
		System.out.println("Listar");
		List<EventoEspaco> hrsEvento = (List<EventoEspaco>) eventoEspacoService.getList();
		assertNotNull(hrsEvento);
		assertFalse(hrsEvento.isEmpty());
		EventoEspaco hrEvento = hrsEvento.get(0);
		assertNotNull(hrEvento);
		System.out.println("Nome evento:" + hrEvento.getEvento().getNome());
		System.out.println("Nome espaço:" + hrEvento.getEspaco().getNome());
		assertNotNull(hrEvento.getEvento());
		assertNotNull(hrEvento.getEspaco());
	}

	@Test
	// @Ignore
	public void test4_DeleteEventoEspaco() throws Exception {
		System.out.println("Deletar");
		EventoEspaco hrEvento = (EventoEspaco) eventoEspacoService.getEntity(createdEventoEspacolId);
		assertNotNull(hrEvento);
		assertTrue(eventoEspacoService.excluir(createdEventoEspacolId));
		hrEvento = (EventoEspaco) eventoEspacoService.getEntity(createdEventoEspacolId);
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

		Long createdEspacoId = createdEspaco.getId();
		Espaco espaco = (Espaco) espacoService.getEntity(createdEspacoId);
		assertNotNull(espaco);
		assertTrue(espacoService.excluir(createdEspacoId));
		espaco = (Espaco) espacoService.getEntity(createdEspacoId);
		assertNull(espaco);
	}
}