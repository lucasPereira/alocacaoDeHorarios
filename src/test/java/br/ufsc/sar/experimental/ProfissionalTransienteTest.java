package br.ufsc.sar.experimental;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.*;

import org.junit.*;

import br.ufsc.ine.leb.projetos.estoria.*;
import br.ufsc.sar.entity.*;
import br.ufsc.sar.service.*;

@FixtureSetup(BaseDeDadosZeradaTest.class)
public class ProfissionalTransienteTest {

	@Fixture private ProfissionalService profissionalService;

	private Profissional profissionalTransiente;

	@Before
	public void configurar() {
		profissionalTransiente = new Profissional();
		profissionalTransiente.setNome("José");
		profissionalTransiente.setProfissao("Analista de Sistemas");
		profissionalTransiente.setCpf("00011122233");
		profissionalTransiente.setTelefone("0011112222");
		profissionalTransiente.setDtnascimento(new Date(0));
	}

	@Test
	public void testar() throws Exception {
		assertNull(profissionalTransiente.getId());
		assertEquals("José", profissionalTransiente.getNome());
		assertEquals("Analista de Sistemas", profissionalTransiente.getProfissao());
		assertEquals("00011122233", profissionalTransiente.getCpf());
		assertEquals("0011112222", profissionalTransiente.getTelefone());
		assertEquals(new Date(0), profissionalTransiente.getDtnascimento());
	}

}
