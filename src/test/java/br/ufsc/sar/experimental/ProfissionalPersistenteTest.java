package br.ufsc.sar.experimental;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.ufsc.ine.leb.projetos.estoria.Fixture;
import br.ufsc.ine.leb.projetos.estoria.FixtureSetup;
import br.ufsc.sar.entity.Profissional;
import br.ufsc.sar.service.ProfissionalService;

@FixtureSetup({ BaseDeDadosZerada.class, ProfissionalTransienteTest.class })
public class ProfissionalPersistenteTest {

	@Fixture private Profissional profissionalTransiente;
	@Fixture private ProfissionalService profissionalService;

	private Long identificadorProfissional;

	@Before
	public void configurarProfissional() throws Exception {
		identificadorProfissional = profissionalService.incluir(profissionalTransiente);
		assertNotNull(identificadorProfissional);
	}

	@Test
	public void obterProfissionalPersistente() throws Exception {
		Profissional profissional = profissionalService.getEntity(identificadorProfissional);
		assertEquals(identificadorProfissional, profissional.getId());
	}

	@Test
	public void listarProfissionalPersistente() throws Exception {
		List<Profissional> profissionais = profissionalService.getList();
		assertEquals(1, profissionais.size());
		assertEquals(identificadorProfissional, profissionais.get(0).getId());
	}

}
