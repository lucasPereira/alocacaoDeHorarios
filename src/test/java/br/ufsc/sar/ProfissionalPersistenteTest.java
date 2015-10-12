package br.ufsc.sar;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.ufsc.ine.leb.projetos.estoria.Fixture;
import br.ufsc.ine.leb.projetos.estoria.FixtureSetup;
import br.ufsc.sar.entity.Profissional;
import br.ufsc.sar.service.ProfissionalService;

@FixtureSetup(ProfissionalTransienteTest.class)
public class ProfissionalPersistenteTest {

	@Fixture private Profissional profissionalTransiente;
	@Fixture private ProfissionalService profissionalService;

	private Long identificador;

	@Before
	public void configurar() throws Exception {
		identificador = profissionalService.incluir(profissionalTransiente);
		assertNotNull(identificador);
	}

	@Test
	public void obter() throws Exception {
		Profissional profissional = profissionalService.getEntity(identificador);
		assertEquals(identificador, profissional.getId());
	}

	@Test
	public void listar() throws Exception {
		List<Profissional> profissionais = profissionalService.getList();
		assertEquals(1, profissionais.size());
		assertEquals(identificador, profissionais.get(0).getId());
	}

}
