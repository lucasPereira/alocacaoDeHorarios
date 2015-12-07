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
		BaseDeDadosZerada.criarBase();
		ProfissionalService profissionalService = BaseDeDadosZerada.obterProfissionalService();
		Long identificadorProfissional = profissionalService.incluir(profissionalTransiente);
		Profissional profissional = profissionalService.getEntity(identificadorProfissional);
		assertEquals(identificadorProfissional, profissional.getId());
	}

	@Test
	public void listarProfissionalPersistente() throws Exception {
		BaseDeDadosZerada.criarBase();
		ProfissionalService profissionalService = BaseDeDadosZerada.obterProfissionalService();
		Long identificadorProfissional = profissionalService.incluir(profissionalTransiente);
		List<Profissional> profissionais = profissionalService.getList();
		assertEquals(1, profissionais.size());
		assertEquals(identificadorProfissional, profissionais.get(0).getId());
	}

	@Test
	public void obterProfissionalPersistenteAlterado() throws Exception {
		BaseDeDadosZerada.criarBase();
		ProfissionalService profissionalService = BaseDeDadosZerada.obterProfissionalService();
		Long identificadorProfissional = profissionalService.incluir(profissionalTransiente);
		profissionalTransiente.setNome("José Maria");
		profissionalService.alterar(profissionalTransiente);
		Profissional profissional = profissionalService.getEntity(identificadorProfissional);
		assertEquals(identificadorProfissional, profissional.getId());
		assertEquals("José Maria", profissional.getNome());
	}

	@Test
	public void listarProfissionalPersistenteAlterado() throws Exception {
		BaseDeDadosZerada.criarBase();
		ProfissionalService profissionalService = BaseDeDadosZerada.obterProfissionalService();
		Long identificadorProfissional = profissionalService.incluir(profissionalTransiente);
		profissionalTransiente.setNome("José Maria");
		profissionalService.alterar(profissionalTransiente);
		List<Profissional> profissionais = profissionalService.getList();
		assertEquals(1, profissionais.size());
		assertEquals(identificadorProfissional, profissionais.get(0).getId());
		assertEquals("José Maria", profissionais.get(0).getNome());
	}

	@Test
	public void obterProfissionalPersistenteRemovido() throws Exception {
		BaseDeDadosZerada.criarBase();
		ProfissionalService profissionalService = BaseDeDadosZerada.obterProfissionalService();
		Long identificadorProfissional = profissionalService.incluir(profissionalTransiente);
		profissionalService.excluir(identificadorProfissional);
		Profissional profissional = profissionalService.getEntity(identificadorProfissional);
		assertNull(profissional);
	}

	@Test
	public void listarProfissionalPersistenteRemovido() throws Exception {
		BaseDeDadosZerada.criarBase();
		ProfissionalService profissionalService = BaseDeDadosZerada.obterProfissionalService();
		Long identificadorProfissional = profissionalService.incluir(profissionalTransiente);
		profissionalService.excluir(identificadorProfissional);
		List<Profissional> profissionais = profissionalService.getList();
		assertEquals(0, profissionais.size());
	}

}
