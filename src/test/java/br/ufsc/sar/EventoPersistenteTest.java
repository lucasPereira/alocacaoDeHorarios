package br.ufsc.sar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.*;

import org.junit.*;

import br.ufsc.ine.leb.projetos.estoria.*;
import br.ufsc.sar.entity.*;
import br.ufsc.sar.service.*;

@FixtureSetup(EventoTransienteTest.class)
public class EventoPersistenteTest {

	@Fixture private EventoService eventoService;
	@Fixture private Evento eventoTransiente;

	private Long identificador;

	@Before
	public void configurar() throws Exception {
		identificador = eventoService.incluir(eventoTransiente);
		assertNotNull(identificador);
	}

	@Test
	public void obter() throws Exception {
		Evento evento = eventoService.getEntity(identificador);
		assertEquals(identificador, evento.getId());
	}

	@Test
	public void listar() throws Exception {
		List<Evento> eventos = eventoService.getList();
		assertEquals(1, eventos.size());
		assertEquals(identificador, eventos.get(0).getId());
	}

}
