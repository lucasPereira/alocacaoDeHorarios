package br.ufsc.sar.controle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.ufsc.sar.entity.Caracteristica;
import br.ufsc.sar.service.CaracteristicaService;

public class CaracateristicaTest {

	private Caracteristica caracteristicaTransiente;

	@Before
	public void configurarCaracteristica() throws Exception {
		caracteristicaTransiente = new Caracteristica();
		caracteristicaTransiente.setNome("Característica");
		caracteristicaTransiente.setForauso(false);
	}

	@Test
	public void caracteristica() throws Exception {
		assertNull(caracteristicaTransiente.getId());
		assertEquals("Característica", caracteristicaTransiente.getNome());
		assertFalse(caracteristicaTransiente.getForauso());
	}

	@Test
	public void obterCaracteristicaPersistente() throws Exception {
		AjudaDeTeste.criarBase();
		CaracteristicaService caracteristicaService = AjudaDeTeste.obterCaracteristicaService();
		Long identificador = caracteristicaService.incluir(caracteristicaTransiente);
		Caracteristica caracteristica = caracteristicaService.getEntity(identificador);
		assertEquals(identificador, caracteristica.getId());
	}

	@Test
	public void listarCaracteristicaPersistente() throws Exception {
		AjudaDeTeste.criarBase();
		CaracteristicaService caracteristicaService = AjudaDeTeste.obterCaracteristicaService();
		Long identificador = caracteristicaService.incluir(caracteristicaTransiente);
		List<Caracteristica> caracteristicas = caracteristicaService.getList();
		assertEquals(1, caracteristicas.size());
		assertEquals(identificador, caracteristicas.get(0).getId());
	}

	@Test
	public void obterCaracteristicaPersistenteAlterada() throws Exception {
		AjudaDeTeste.criarBase();
		CaracteristicaService caracteristicaService = AjudaDeTeste.obterCaracteristicaService();
		Long identificador = caracteristicaService.incluir(caracteristicaTransiente);
		caracteristicaTransiente.setNome("Característica alterada");
		caracteristicaService.alterar(caracteristicaTransiente);
		Caracteristica caracteristica = caracteristicaService.getEntity(identificador);
		assertEquals(identificador, caracteristica.getId());
		assertEquals("Característica alterada", caracteristica.getNome());
	}

	@Test
	public void listarCaracteristicaPersistenteAlterada() throws Exception {
		AjudaDeTeste.criarBase();
		CaracteristicaService caracteristicaService = AjudaDeTeste.obterCaracteristicaService();
		Long identificador = caracteristicaService.incluir(caracteristicaTransiente);
		caracteristicaTransiente.setNome("Característica alterada");
		caracteristicaService.alterar(caracteristicaTransiente);
		List<Caracteristica> caracteristicas = caracteristicaService.getList();
		assertEquals(1, caracteristicas.size());
		assertEquals(identificador, caracteristicas.get(0).getId());
		assertEquals("Característica alterada", caracteristicas.get(0).getNome());
	}

	@Test
	public void obterCaracteristicaPersistenteRemovida() throws Exception {
		AjudaDeTeste.criarBase();
		CaracteristicaService caracteristicaService = AjudaDeTeste.obterCaracteristicaService();
		Long identificador = caracteristicaService.incluir(caracteristicaTransiente);
		caracteristicaService.excluir(identificador);
		Caracteristica caracteristica = caracteristicaService.getEntity(identificador);
		assertNull(caracteristica);
	}

	@Test
	public void listarCaracteristicaPersistenteRemovida() throws Exception {
		AjudaDeTeste.criarBase();
		CaracteristicaService caracteristicaService = AjudaDeTeste.obterCaracteristicaService();
		Long identificador = caracteristicaService.incluir(caracteristicaTransiente);
		caracteristicaService.excluir(identificador);
		List<Caracteristica> caracteristicas = caracteristicaService.getList();
		assertEquals(0, caracteristicas.size());
	}

}
