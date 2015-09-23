package br.ufsc.sar;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.ufsc.sar.entity.Profissional;
import br.ufsc.sar.service.ProfissionalService;
import br.ufsc.sar.serviceimpl.ProfissionalServiceImpl;

public class Profissional0Test {

	private ProfissionalService profissionalService;

	@Before
	public void configurar() {
		profissionalService = new ProfissionalServiceImpl();
		profissionalService.exluirTodos();
	}

	@Test
	public void testar0_Profissional() throws Exception {
		Profissional profissionalTransiente = new Profissional();
		profissionalTransiente.setNome("José");
		profissionalTransiente.setProfissao("Analista de Sistemas");

		assertNull(profissionalTransiente.getId());
		assertEquals("José", profissionalTransiente.getNome());
		assertEquals("Analista de Sistemas", profissionalTransiente.getProfissao());
	}

	@Test
	public void testar1_InserirProfissional() throws Exception {
		Profissional profissionalTransiente = new Profissional();
		profissionalTransiente.setNome("José");
		profissionalTransiente.setProfissao("Analista de Sistemas");

		Long identificador = profissionalService.incluir(profissionalTransiente);

		assertNotNull(identificador);
	}

	@Test
	public void testar2_ObterProfissional() throws Exception {
		Profissional profissionalTransiente = new Profissional();
		profissionalTransiente.setNome("José");
		profissionalTransiente.setProfissao("Analista de Sistemas");
		Long identificador = profissionalService.incluir(profissionalTransiente);

		Profissional profissionalPersistido = profissionalService.getEntity(identificador);

		assertNotNull(profissionalPersistido);
		assertEquals(identificador, profissionalPersistido.getId());
		assertEquals("José", profissionalPersistido.getNome());
		assertEquals("Analista de Sistemas", profissionalPersistido.getProfissao());
	}

	@Test
	public void testar3_AlterarProfissional() throws Exception {
		Profissional profissionalTransiente = new Profissional();
		profissionalTransiente.setNome("José");
		profissionalTransiente.setProfissao("Analista de Sistemas");
		Long identificador = profissionalService.incluir(profissionalTransiente);
		Profissional profissionalPersistido = profissionalService.getEntity(identificador);

		profissionalPersistido.setNome("José Maria");
		profissionalService.alterar(profissionalPersistido);
		Profissional profissionalAlterado = profissionalService.getEntity(identificador);

		assertEquals(identificador, profissionalAlterado.getId());
		assertEquals("José Maria", profissionalAlterado.getNome());
		assertEquals("Analista de Sistemas", profissionalAlterado.getProfissao());
	}

	@Test
	public void testar4_ListarProfissional() throws Exception {
		Profissional profissionalTransiente = new Profissional();
		profissionalTransiente.setNome("José");
		profissionalTransiente.setProfissao("Analista de Sistemas");
		Long identificador = profissionalService.incluir(profissionalTransiente);

		List<Profissional> profissionais = profissionalService.getList();

		assertNotNull(profissionais);
		assertEquals(1, profissionais.size());
		assertEquals(identificador, profissionais.get(0).getId());
	}

	@Test
	public void testar5_RemoverProfissional() throws Exception {
		Profissional profissionalTransiente = new Profissional();
		profissionalTransiente.setNome("José");
		profissionalTransiente.setProfissao("Analista de Sistemas");
		Long identificador = profissionalService.incluir(profissionalTransiente);

		profissionalService.excluir(identificador);
		Profissional profissionalRemovido = profissionalService.getEntity(identificador);

		assertNull(profissionalRemovido);
	}

}
