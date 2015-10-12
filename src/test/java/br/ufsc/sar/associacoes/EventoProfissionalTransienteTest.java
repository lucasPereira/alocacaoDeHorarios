package br.ufsc.sar.associacoes;

import static org.junit.Assert.*;

import org.junit.*;

import br.ufsc.ine.leb.projetos.estoria.*;
import br.ufsc.sar.*;
import br.ufsc.sar.entity.*;

@FixtureSetup({ EventoTransienteTest.class, ProfissionalTransienteTest.class })
public class EventoProfissionalTransienteTest {

	@Fixture private Evento eventoTransiente;
	@Fixture private Profissional profissionalTransiente;

	private EventoProfissional eventoProfissionalTransiente;

	@Before
	public void configurar() {
		eventoProfissionalTransiente = new EventoProfissional();
		eventoProfissionalTransiente.setEvento(eventoTransiente);
		eventoProfissionalTransiente.setProfissional(profissionalTransiente);
	}

	@Test
	public void testar() throws Exception {
		assertNull(eventoProfissionalTransiente.getId());
		assertSame(eventoTransiente, eventoProfissionalTransiente.getEvento());
		assertSame(profissionalTransiente, eventoProfissionalTransiente.getProfissional());
	}

}
