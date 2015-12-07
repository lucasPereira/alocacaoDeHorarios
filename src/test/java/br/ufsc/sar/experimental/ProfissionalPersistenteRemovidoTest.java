package br.ufsc.sar.experimental;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.ufsc.ine.leb.projetos.estoria.Fixture;
import br.ufsc.ine.leb.projetos.estoria.FixtureSetup;
import br.ufsc.sar.entity.Profissional;
import br.ufsc.sar.service.ProfissionalService;

@FixtureSetup(ProfissionalPersistenteTest.class)
public class ProfissionalPersistenteRemovidoTest {

	@Fixture private Long identificadorProfissional;
	@Fixture private ProfissionalService profissionalService;

	@Before
	public void configurarProfissional() throws Exception {
		profissionalService.excluir(identificadorProfissional);
	}

	@Test
	public void obterProfissionalPersistenteRemovido() throws Exception {
		Profissional profissional = profissionalService.getEntity(identificadorProfissional);
		assertNull(profissional);
	}

	@Test
	public void listarProfissionalPersistenteRemovido() throws Exception {
		List<Profissional> profissionais = profissionalService.getList();
		assertEquals(0, profissionais.size());
	}

}
