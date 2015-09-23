package br.ufsc.sar;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.ufsc.ine.leb.projetos.estoria.Fixture;
import br.ufsc.ine.leb.projetos.estoria.FixtureSetup;
import br.ufsc.sar.entity.Profissional;
import br.ufsc.sar.service.ProfissionalService;

@FixtureSetup(ProfissionalEntidadeJoseTransienteTest.class)
public class ProfissionalIdentificadorDaEntidadeJosePersistidoTest {

	@Fixture private Profissional profissionalTransiente;
	@Fixture private ProfissionalService profissionalService;

	private Long identificador;

	@Before
	public void configurar() throws Exception {
		identificador = profissionalService.incluir(profissionalTransiente);
	}

	@Test
	public void identificadorDaEntidade() throws Exception {
		assertNotNull(identificador);
	}

	@Test
	public void listarEntidades() throws Exception {
		List<Profissional> profissionais = profissionalService.getList();
		assertNotNull(profissionais);
		assertEquals(1, profissionais.size());
		assertEquals(identificador, profissionais.get(0).getId());
	}

	@Test
	public void removerEntidade() throws Exception {
		profissionalService.excluir(identificador);
		Profissional profissionalRemovido = profissionalService.getEntity(identificador);
		assertNull(profissionalRemovido);
	}

}
