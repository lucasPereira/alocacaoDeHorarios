package br.ufsc.sar.experimental;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.ufsc.ine.leb.projetos.estoria.Fixture;
import br.ufsc.ine.leb.projetos.estoria.FixtureSetup;
import br.ufsc.sar.entity.Evento;
import br.ufsc.sar.entity.EventoProfissional;
import br.ufsc.sar.entity.Profissional;
import br.ufsc.sar.service.EventoProfissionalService;

@FixtureSetup({ EventoPersistenteTest.class, ProfissionalPersistenteTest.class, BaseDeDadosZerada.class })
public class EventoProfissionalPersistenteTest {

	@Fixture private Evento eventoTransiente;
	@Fixture private Profissional profissionalTransiente;
	@Fixture private EventoProfissionalService eventoProfissionalService;

	private Long identificador;

	@Before
	public void configurarEventoProfissional() throws Exception {
		EventoProfissional eventoProfissional = new EventoProfissional();
		eventoProfissional.setEvento(eventoTransiente);
		eventoProfissional.setProfissional(profissionalTransiente);
		identificador = eventoProfissionalService.incluir(eventoProfissional);
		assertNotNull(identificador);
	}

	@Test
	public void obterEventoProfissionalPersistente() throws Exception {
		EventoProfissional eventoProssifional = eventoProfissionalService.getEntity(identificador);
		assertEquals(identificador, eventoProssifional.getId());
		assertEquals(eventoTransiente.getId(), eventoProssifional.getEvento().getId());
		assertEquals(profissionalTransiente.getId(), eventoProssifional.getProfissional().getId());
	}

	@Test
	public void listarEventoProfissionalPersistente() throws Exception {
		List<EventoProfissional> eventosProfissionais = eventoProfissionalService.getList();
		assertEquals(1, eventosProfissionais.size());
		assertEquals(identificador, eventosProfissionais.get(0).getId());
		assertEquals(eventoTransiente.getId(), eventosProfissionais.get(0).getEvento().getId());
		assertEquals(profissionalTransiente.getId(), eventosProfissionais.get(0).getProfissional().getId());
	}

}
