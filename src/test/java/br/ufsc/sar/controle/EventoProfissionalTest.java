package br.ufsc.sar.controle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.ufsc.sar.entity.Evento;
import br.ufsc.sar.entity.EventoProfissional;
import br.ufsc.sar.entity.Profissional;
import br.ufsc.sar.service.EventoProfissionalService;
import br.ufsc.sar.service.EventoService;
import br.ufsc.sar.service.ProfissionalService;

public class EventoProfissionalTest {

	private Evento eventoTransiente;
	private Profissional profissionalTransiente;
	private EventoProfissional eventoProfissionalTransiente;

	@Before
	public void configurar() throws Exception {
		eventoTransiente = new Evento();
		eventoTransiente.setNome("Evento");
		eventoTransiente.setDescricao("Descrição do evento");
		profissionalTransiente = new Profissional();
		profissionalTransiente.setNome("José");
		profissionalTransiente.setProfissao("Analista de Sistemas");
		profissionalTransiente.setCpf("00011122233");
		profissionalTransiente.setTelefone("0011112222");
		profissionalTransiente.setDtnascimento(new Date(0));
		eventoProfissionalTransiente = new EventoProfissional();
		eventoProfissionalTransiente.setEvento(eventoTransiente);
		eventoProfissionalTransiente.setProfissional(profissionalTransiente);
	}

	@Test
	public void eventoProfissional() throws Exception {
		assertNull(eventoProfissionalTransiente.getId());
		assertSame(eventoTransiente, eventoProfissionalTransiente.getEvento());
		assertSame(profissionalTransiente, eventoProfissionalTransiente.getProfissional());
	}

	@Test
	public void obterEventoProfissionalPersistente() throws Exception {
		AjudaDeTeste.criarBase();
		EventoService eventoService = AjudaDeTeste.obterEventoService();
		ProfissionalService profissionalService = AjudaDeTeste.obterProfissionalService();
		EventoProfissionalService eventoProfissionalService = AjudaDeTeste.obterEventoProfissionalService();
		eventoService.incluir(eventoTransiente);
		profissionalService.incluir(profissionalTransiente);
		Long identificador = eventoProfissionalService.incluir(eventoProfissionalTransiente);
		EventoProfissional eventoProssifional = eventoProfissionalService.getEntity(identificador);
		assertEquals(identificador, eventoProssifional.getId());
		assertEquals(eventoTransiente.getId(), eventoProssifional.getEvento().getId());
		assertEquals(profissionalTransiente.getId(), eventoProssifional.getProfissional().getId());
	}

	@Test
	public void listarEventoProfissionalPersistente() throws Exception {
		AjudaDeTeste.criarBase();
		EventoService eventoService = AjudaDeTeste.obterEventoService();
		ProfissionalService profissionalService = AjudaDeTeste.obterProfissionalService();
		EventoProfissionalService eventoProfissionalService = AjudaDeTeste.obterEventoProfissionalService();
		eventoService.incluir(eventoTransiente);
		profissionalService.incluir(profissionalTransiente);
		Long identificador = eventoProfissionalService.incluir(eventoProfissionalTransiente);
		List<EventoProfissional> eventosProfissionais = eventoProfissionalService.getList();
		assertEquals(1, eventosProfissionais.size());
		assertEquals(identificador, eventosProfissionais.get(0).getId());
		assertEquals(eventoTransiente.getId(), eventosProfissionais.get(0).getEvento().getId());
		assertEquals(profissionalTransiente.getId(), eventosProfissionais.get(0).getProfissional().getId());
	}

}
