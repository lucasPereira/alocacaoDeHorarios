package br.ufsc.sar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.*;

import br.ufsc.ine.leb.projetos.estoria.*;
import br.ufsc.sar.entity.*;
import br.ufsc.sar.service.*;

@FixtureSetup(BaseDeDadosZeradaTest.class)
public class ProfissionalEntidadeJoseTransienteTest {

	@Fixture private ProfissionalService profissionalService;

	private Profissional profissionalTransiente;

	@Before
	public void configurar() {
		profissionalTransiente = new Profissional();
		profissionalTransiente.setNome("José");
		profissionalTransiente.setProfissao("Analista de Sistemas");
	}

	@Test
	public void dadosDaEntidade() throws Exception {
		assertNull(profissionalTransiente.getId());
		assertEquals("José", profissionalTransiente.getNome());
		assertEquals("Analista de Sistemas", profissionalTransiente.getProfissao());
	}

}
