package br.ufsc.sar.experimental;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.*;

import org.junit.*;

import br.ufsc.ine.leb.projetos.estoria.*;
import br.ufsc.sar.entity.*;
import br.ufsc.sar.service.*;

@FixtureSetup({ BaseDeDadosZerada.class, EventoTransienteTest.class })
public class EventoPersistenteTest {

	@Fixture private Evento eventoTransiente;
	@Fixture private EventoService eventoService;

	private Long identificadorEvento;

	@Before
	public void configurarEvento() throws Exception {
		identificadorEvento = eventoService.incluir(eventoTransiente);
		assertNotNull(identificadorEvento);
	}

	@Test
	public void obterEventoPersistente() throws Exception {
		Evento evento = eventoService.getEntity(identificadorEvento);
		assertEquals(identificadorEvento, evento.getId());
	}

	@Test
	public void listarEventoPersistente() throws Exception {
		List<Evento> eventos = eventoService.getList();
		assertEquals(1, eventos.size());
		assertEquals(identificadorEvento, eventos.get(0).getId());
	}

}
