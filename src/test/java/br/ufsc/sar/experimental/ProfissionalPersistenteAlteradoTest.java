package br.ufsc.sar.experimental;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

import br.ufsc.ine.leb.projetos.estoria.Fixture;
import br.ufsc.ine.leb.projetos.estoria.FixtureSetup;
import br.ufsc.sar.entity.Profissional;
import br.ufsc.sar.service.ProfissionalService;

@FixtureSetup(ProfissionalPersistenteTest.class)
public class ProfissionalPersistenteAlteradoTest {

	@Fixture private Long identificadorProfissional;
	@Fixture private Profissional profissionalTransiente;
	@Fixture private ProfissionalService profissionalService;

	@Before
	public void configurarProfissional() throws Exception {
		profissionalTransiente.setNome("José Maria");
		profissionalService.alterar(profissionalTransiente);
	}

	@Test
	public void obterProfissionalPersistenteAlterado() throws Exception {
		Profissional profissional = profissionalService.getEntity(identificadorProfissional);
		assertEquals(identificadorProfissional, profissional.getId());
		assertEquals("José Maria", profissional.getNome());
	}

	@Test
	public void listarProfissionalPersistenteAlterado() throws Exception {
		List<Profissional> profissionais = profissionalService.getList();
		assertEquals(1, profissionais.size());
		assertEquals(identificadorProfissional, profissionais.get(0).getId());
		assertEquals("José Maria", profissionais.get(0).getNome());
	}

}
