package br.ufsc.sar.experimental;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.*;

import org.junit.*;

import br.ufsc.ine.leb.projetos.estoria.*;
import br.ufsc.sar.entity.*;
import br.ufsc.sar.service.*;

@FixtureSetup(EventoPersistenteTest.class)
public class EventoPersistenteRemovidoTest {

	@Fixture private Long identificadorEvento;
	@Fixture private EventoService eventoService;

	@Before
	public void configurarEvento() throws Exception {
		eventoService.excluir(identificadorEvento);
	}

	@Test
	public void obterEventoPersistenteRemovido() throws Exception {
		Evento evento = eventoService.getEntity(identificadorEvento);
		assertNull(evento);
	}

	@Test
	public void listarEventoPersistenteRemovido() throws Exception {
		List<Evento> eventos = eventoService.getList();
		assertEquals(0, eventos.size());
	}

}
