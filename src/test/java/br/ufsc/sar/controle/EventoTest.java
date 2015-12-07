package br.ufsc.sar.controle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.ufsc.sar.entity.Evento;
import br.ufsc.sar.service.EventoService;

public class EventoTest {

	private Evento eventoTransiente;

	@Before
	public void configurarEvento() throws Exception {
		eventoTransiente = new Evento();
		eventoTransiente.setNome("Evento");
		eventoTransiente.setDescricao("Descrição do evento");
	}

	@Test
	public void evento() throws Exception {
		assertNull(eventoTransiente.getId());
		assertEquals("Evento", eventoTransiente.getNome());
		assertEquals("Descrição do evento", eventoTransiente.getDescricao());
	}

	@Test
	public void obterEventoPersistente() throws Exception {
		BaseDeDadosZerada.criarBase();
		EventoService eventoService = BaseDeDadosZerada.obterEventoService();
		Long identificadorEvento = eventoService.incluir(eventoTransiente);
		Evento evento = eventoService.getEntity(identificadorEvento);
		assertEquals(identificadorEvento, evento.getId());
	}

	@Test
	public void listarEventoPersistente() throws Exception {
		BaseDeDadosZerada.criarBase();
		EventoService eventoService = BaseDeDadosZerada.obterEventoService();
		Long identificadorEvento = eventoService.incluir(eventoTransiente);
		List<Evento> eventos = eventoService.getList();
		assertEquals(1, eventos.size());
		assertEquals(identificadorEvento, eventos.get(0).getId());
	}

	@Test
	public void obterEventoPersistenteAlterado() throws Exception {
		BaseDeDadosZerada.criarBase();
		EventoService eventoService = BaseDeDadosZerada.obterEventoService();
		Long identificadorEvento = eventoService.incluir(eventoTransiente);
		eventoTransiente.setNome("Evento alterado");
		eventoService.alterar(eventoTransiente);
		Evento evento = eventoService.getEntity(identificadorEvento);
		assertEquals(identificadorEvento, evento.getId());
		assertEquals("Evento alterado", evento.getNome());
	}

	@Test
	public void listarEventoPersistenteAlterado() throws Exception {
		BaseDeDadosZerada.criarBase();
		EventoService eventoService = BaseDeDadosZerada.obterEventoService();
		Long identificadorEvento = eventoService.incluir(eventoTransiente);
		eventoTransiente.setNome("Evento alterado");
		eventoService.alterar(eventoTransiente);
		List<Evento> eventos = eventoService.getList();
		assertEquals(1, eventos.size());
		assertEquals(identificadorEvento, eventos.get(0).getId());
		assertEquals("Evento alterado", eventos.get(0).getNome());
	}

	@Test
	public void obterEventoPersistenteRemovido() throws Exception {
		BaseDeDadosZerada.criarBase();
		EventoService eventoService = BaseDeDadosZerada.obterEventoService();
		Long identificadorEvento = eventoService.incluir(eventoTransiente);
		eventoService.excluir(identificadorEvento);
		Evento evento = eventoService.getEntity(identificadorEvento);
		assertNull(evento);
	}

	@Test
	public void listarEventoPersistenteRemovido() throws Exception {
		BaseDeDadosZerada.criarBase();
		EventoService eventoService = BaseDeDadosZerada.obterEventoService();
		Long identificadorEvento = eventoService.incluir(eventoTransiente);
		eventoService.excluir(identificadorEvento);
		List<Evento> eventos = eventoService.getList();
		assertEquals(0, eventos.size());
	}

}
