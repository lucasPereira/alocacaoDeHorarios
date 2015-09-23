package br.ufsc.sar;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.ufsc.sar.entity.Profissional;
import br.ufsc.sar.service.ProfissionalService;
import br.ufsc.sar.serviceimpl.ProfissionalServiceImpl;

public class ProfissionalEntidadeJoseTransienteTest {

	private Profissional profissionalTransiente;
	private ProfissionalService profissionalService;

	@Before
	public void configurar() {
		profissionalService = new ProfissionalServiceImpl();
		profissionalService.exluirTodos();
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
