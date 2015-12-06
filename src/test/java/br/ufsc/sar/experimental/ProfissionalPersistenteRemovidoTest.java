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

	@Fixture private Long identificador;
	@Fixture private ProfissionalService profissionalService;

	@Before
	public void configurar() throws Exception {
		profissionalService.excluir(identificador);
	}

	@Test
	public void obter() throws Exception {
		Profissional profissional = profissionalService.getEntity(identificador);
		assertNull(profissional);
	}

	@Test
	public void listar() throws Exception {
		List<Profissional> profissionais = profissionalService.getList();
		assertEquals(0, profissionais.size());
	}

}
