package br.ufsc.sar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.*;

import org.junit.*;

import br.ufsc.ine.leb.projetos.estoria.*;
import br.ufsc.sar.entity.*;
import br.ufsc.sar.service.*;

@FixtureSetup(EventoPersistenteTest.class)
public class EventoPersistenteRemovidoTest {

	@Fixture private Long identificador;
	@Fixture private EventoService eventoService;

	@Before
	public void configurar() throws Exception {
		eventoService.excluir(identificador);
	}

	@Test
	public void obter() throws Exception {
		Evento evento = eventoService.getEntity(identificador);
		assertNull(evento);
	}

	@Test
	public void listar() throws Exception {
		List<Evento> eventos = eventoService.getList();
		assertEquals(0, eventos.size());
	}

}
