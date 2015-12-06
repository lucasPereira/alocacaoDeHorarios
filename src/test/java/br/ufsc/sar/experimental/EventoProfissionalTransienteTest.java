package br.ufsc.sar.experimental;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

import br.ufsc.ine.leb.projetos.estoria.Fixture;
import br.ufsc.ine.leb.projetos.estoria.FixtureSetup;
import br.ufsc.sar.entity.Evento;
import br.ufsc.sar.entity.EventoProfissional;
import br.ufsc.sar.entity.Profissional;

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
