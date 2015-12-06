package br.ufsc.sar.experimental;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.*;

import br.ufsc.ine.leb.projetos.estoria.*;
import br.ufsc.sar.entity.*;
import br.ufsc.sar.service.*;

@FixtureSetup(BaseDeDadosZeradaTest.class)
public class EventoTransienteTest {

	@Fixture private EventoService eventoService;

	private Evento eventoTransiente;

	@Before
	public void configurar() throws Exception {
		eventoTransiente = new Evento();
		eventoTransiente.setNome("Evento");
		eventoTransiente.setDescricao("Descrição do evento");
	}

	@Test
	public void testar() throws Exception {
		assertNull(eventoTransiente.getId());
		assertEquals("Evento", eventoTransiente.getNome());
		assertEquals("Descrição do evento", eventoTransiente.getDescricao());
	}

}
