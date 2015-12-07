package br.ufsc.sar.experimental;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.*;

import br.ufsc.ine.leb.projetos.estoria.*;
import br.ufsc.sar.entity.*;
import br.ufsc.sar.service.*;

@FixtureSetup(EventoPersistenteTest.class)
public class EventoPersistenteAlteradoTest {

	@Fixture private Long identificador;
	@Fixture private EventoService eventoService;
	@Fixture private Evento eventoTransiente;

	@Before
	public void configurarEvento() throws Exception {
		eventoTransiente.setNome("Evento alterado");
		eventoService.alterar(eventoTransiente);
	}

	@Test
	public void obterEventoPersistenteAlterado() throws Exception {
		Evento evento = eventoService.getEntity(identificador);
		assertEquals(identificador, evento.getId());
		assertEquals("Evento alterado", evento.getNome());
	}

	@Test
	public void listarEventoPersistenteAlterado() throws Exception {
		List<Evento> eventos = eventoService.getList();
		assertEquals(1, eventos.size());
		assertEquals(identificador, eventos.get(0).getId());
		assertEquals("Evento alterado", eventos.get(0).getNome());
	}

}
