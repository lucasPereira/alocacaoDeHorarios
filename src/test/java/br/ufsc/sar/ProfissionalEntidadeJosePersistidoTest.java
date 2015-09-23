package br.ufsc.sar;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.ufsc.ine.leb.projetos.estoria.Fixture;
import br.ufsc.ine.leb.projetos.estoria.FixtureSetup;
import br.ufsc.sar.entity.Profissional;
import br.ufsc.sar.service.ProfissionalService;

@FixtureSetup(ProfissionalIdentificadorDaEntidadeJosePersistidoTest.class)
public class ProfissionalEntidadeJosePersistidoTest {

	@Fixture private Long identificador;
	@Fixture private ProfissionalService profissionalService;

	private Profissional profissionalPersistido;

	@Before
	public void configurar() throws Exception {
		profissionalPersistido = profissionalService.getEntity(identificador);
	}

	@Test
	public void dadosDaEntidade() throws Exception {
		assertNotNull(profissionalPersistido);
		assertEquals(identificador, profissionalPersistido.getId());
		assertEquals("José", profissionalPersistido.getNome());
		assertEquals("Analista de Sistemas", profissionalPersistido.getProfissao());
	}

	@Test
	public void alterarEntidade() throws Exception {
		profissionalPersistido.setNome("José Maria");
		profissionalService.alterar(profissionalPersistido);
		Profissional profissionalAlterado = profissionalService.getEntity(identificador);
		assertEquals(identificador, profissionalAlterado.getId());
		assertEquals("José Maria", profissionalAlterado.getNome());
		assertEquals("Analista de Sistemas", profissionalAlterado.getProfissao());
	}

}
