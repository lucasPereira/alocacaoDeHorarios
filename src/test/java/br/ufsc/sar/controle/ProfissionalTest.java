package br.ufsc.sar.controle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.ufsc.sar.entity.Profissional;
import br.ufsc.sar.service.ProfissionalService;

public class ProfissionalTest {

	private Profissional profissionalTransiente;

	@Before
	public void configurarProfissional() {
		profissionalTransiente = new Profissional();
		profissionalTransiente.setNome("José");
		profissionalTransiente.setProfissao("Analista de Sistemas");
		profissionalTransiente.setCpf("00011122233");
		profissionalTransiente.setTelefone("0011112222");
		profissionalTransiente.setDtnascimento(new Date(0));
	}

	@Test
	public void profissional() throws Exception {
		assertNull(profissionalTransiente.getId());
		assertEquals("José", profissionalTransiente.getNome());
		assertEquals("Analista de Sistemas", profissionalTransiente.getProfissao());
		assertEquals("00011122233", profissionalTransiente.getCpf());
		assertEquals("0011112222", profissionalTransiente.getTelefone());
		assertEquals(new Date(0), profissionalTransiente.getDtnascimento());
	}

	@Test
	public void obterProfissionalPersistente() throws Exception {
		AjudaDeTeste.criarBase();
		ProfissionalService profissionalService = AjudaDeTeste.obterProfissionalService();
		Long identificador = profissionalService.incluir(profissionalTransiente);
		Profissional profissional = profissionalService.getEntity(identificador);
		assertEquals(identificador, profissional.getId());
	}

	@Test
	public void listarProfissionalPersistente() throws Exception {
		AjudaDeTeste.criarBase();
		ProfissionalService profissionalService = AjudaDeTeste.obterProfissionalService();
		Long identificador = profissionalService.incluir(profissionalTransiente);
		List<Profissional> profissionais = profissionalService.getList();
		assertEquals(1, profissionais.size());
		assertEquals(identificador, profissionais.get(0).getId());
	}

	@Test
	public void obterProfissionalPersistenteAlterado() throws Exception {
		AjudaDeTeste.criarBase();
		ProfissionalService profissionalService = AjudaDeTeste.obterProfissionalService();
		Long identificador = profissionalService.incluir(profissionalTransiente);
		profissionalTransiente.setNome("José Maria");
		profissionalService.alterar(profissionalTransiente);
		Profissional profissional = profissionalService.getEntity(identificador);
		assertEquals(identificador, profissional.getId());
		assertEquals("José Maria", profissional.getNome());
	}

	@Test
	public void listarProfissionalPersistenteAlterado() throws Exception {
		AjudaDeTeste.criarBase();
		ProfissionalService profissionalService = AjudaDeTeste.obterProfissionalService();
		Long identificador = profissionalService.incluir(profissionalTransiente);
		profissionalTransiente.setNome("José Maria");
		profissionalService.alterar(profissionalTransiente);
		List<Profissional> profissionais = profissionalService.getList();
		assertEquals(1, profissionais.size());
		assertEquals(identificador, profissionais.get(0).getId());
		assertEquals("José Maria", profissionais.get(0).getNome());
	}

	@Test
	public void obterProfissionalPersistenteRemovido() throws Exception {
		AjudaDeTeste.criarBase();
		ProfissionalService profissionalService = AjudaDeTeste.obterProfissionalService();
		Long identificador = profissionalService.incluir(profissionalTransiente);
		profissionalService.excluir(identificador);
		Profissional profissional = profissionalService.getEntity(identificador);
		assertNull(profissional);
	}

	@Test
	public void listarProfissionalPersistenteRemovido() throws Exception {
		AjudaDeTeste.criarBase();
		ProfissionalService profissionalService = AjudaDeTeste.obterProfissionalService();
		Long identificador = profissionalService.incluir(profissionalTransiente);
		profissionalService.excluir(identificador);
		List<Profissional> profissionais = profissionalService.getList();
		assertEquals(0, profissionais.size());
	}

}
