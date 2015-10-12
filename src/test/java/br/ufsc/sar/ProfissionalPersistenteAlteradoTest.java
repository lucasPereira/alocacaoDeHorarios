package br.ufsc.sar;

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

	@Fixture private Long identificador;
	@Fixture private Profissional profissionalTransiente;
	@Fixture private ProfissionalService profissionalService;

	@Before
	public void configurar() throws Exception {
		profissionalTransiente.setNome("José Maria");
		profissionalService.alterar(profissionalTransiente);
	}

	@Test
	public void obter() throws Exception {
		Profissional profissional = profissionalService.getEntity(identificador);
		assertEquals(identificador, profissional.getId());
		assertEquals("José Maria", profissional.getNome());
	}

	@Test
	public void listar() throws Exception {
		List<Profissional> profissionais = profissionalService.getList();
		assertEquals(1, profissionais.size());
		assertEquals(identificador, profissionais.get(0).getId());
		assertEquals("José Maria", profissionais.get(0).getNome());
	}

}
