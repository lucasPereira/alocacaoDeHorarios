package br.ufsc.sar;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import br.ufsc.sar.entity.Profissional;
import br.ufsc.sar.service.ProfissionalService;
import br.ufsc.sar.serviceimpl.ProfissionalServiceImpl;

public class Profissional3Test {

	private Long identificador;
	private ProfissionalService profissionalService;
	private Profissional profissionalPersistido;
	private Profissional profissionalTransiente;

	@Before
	public void configurar() throws Exception {
		profissionalService = new ProfissionalServiceImpl();
		profissionalService.exluirTodos();
		profissionalTransiente = new Profissional();
		profissionalTransiente.setNome("José");
		profissionalTransiente.setProfissao("Analista de Sistemas");
		identificador = profissionalService.incluir(profissionalTransiente);
		profissionalPersistido = profissionalService.getEntity(identificador);
	}

	@Ignore
	@Test
	public void testar0_Profissional() throws Exception {
		assertNull(profissionalTransiente.getId());
		assertEquals("José", profissionalTransiente.getNome());
		assertEquals("Analista de Sistemas", profissionalTransiente.getProfissao());
	}

	@Test
	public void testar1_InserirProfissional() throws Exception {
		assertNotNull(identificador);
	}

	@Test
	public void testar2_ObterProfissional() throws Exception {
		assertNotNull(profissionalPersistido);
		assertEquals(identificador, profissionalPersistido.getId());
		assertEquals("José", profissionalPersistido.getNome());
		assertEquals("Analista de Sistemas", profissionalPersistido.getProfissao());
	}

	@Test
	public void testar3_AlterarProfissional() throws Exception {
		profissionalPersistido.setNome("José Maria");
		profissionalService.alterar(profissionalPersistido);
		Profissional profissionalAlterado = profissionalService.getEntity(identificador);

		assertEquals(identificador, profissionalAlterado.getId());
		assertEquals("José Maria", profissionalAlterado.getNome());
		assertEquals("Analista de Sistemas", profissionalAlterado.getProfissao());
	}

	@Test
	public void testar4_ListarProfissional() throws Exception {
		List<Profissional> profissionais = profissionalService.getList();

		assertNotNull(profissionais);
		assertEquals(1, profissionais.size());
		assertEquals(identificador, profissionais.get(0).getId());
	}

	@Test
	public void testar5_RemoverProfissional() throws Exception {
		profissionalService.excluir(identificador);
		Profissional profissionalRemovido = profissionalService.getEntity(identificador);

		assertNull(profissionalRemovido);
	}

}
