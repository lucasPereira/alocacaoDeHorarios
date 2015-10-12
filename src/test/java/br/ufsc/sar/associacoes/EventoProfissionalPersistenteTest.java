package br.ufsc.sar.associacoes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.*;

import org.junit.*;

import br.ufsc.ine.leb.projetos.estoria.*;
import br.ufsc.sar.*;
import br.ufsc.sar.entity.*;
import br.ufsc.sar.service.*;

@FixtureSetup({ EventoPersistenteTest.class, ProfissionalPersistenteTest.class })
public class EventoProfissionalPersistenteTest {

	@Fixture private Evento eventoTransiente;
	@Fixture private Profissional profissionalTransiente;
	@Fixture private EventoProfissional eventoProfissionalTransiente;
	@Fixture private EventoProfissionalService eventoProfissionalService;

	private Long identificador;

	@Before
	public void configurar() throws Exception {
		identificador = eventoProfissionalService.incluir(eventoProfissionalTransiente);
		assertNotNull(identificador);
	}

	@Test
	public void obter() throws Exception {
		EventoProfissional eventoProssifional = eventoProfissionalService.getEntity(identificador);
		assertEquals(identificador, eventoProssifional.getId());
		assertEquals(eventoTransiente.getId(), eventoProssifional.getEvento().getId());
		assertEquals(profissionalTransiente.getId(), eventoProssifional.getProfissional().getId());
	}

	@Test
	public void listar() throws Exception {
		List<EventoProfissional> eventosProfissionais = eventoProfissionalService.getList();
		assertEquals(1, eventosProfissionais.size());
		assertEquals(identificador, eventosProfissionais.get(0).getId());
		assertEquals(eventoTransiente.getId(), eventosProfissionais.get(0).getEvento().getId());
		assertEquals(profissionalTransiente.getId(), eventosProfissionais.get(0).getProfissional().getId());
	}

}
