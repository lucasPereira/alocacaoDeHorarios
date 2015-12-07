package br.ufsc.sar.experimental;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import br.ufsc.sar.entity.Evento;

public class EventoTransienteTest {

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

}
