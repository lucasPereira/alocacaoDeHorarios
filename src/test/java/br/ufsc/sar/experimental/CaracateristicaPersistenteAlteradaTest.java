package br.ufsc.sar.experimental;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.*;

import br.ufsc.ine.leb.projetos.estoria.*;
import br.ufsc.sar.entity.*;
import br.ufsc.sar.service.*;

@FixtureSetup(CaracateristicaPersistenteTest.class)
public class CaracateristicaPersistenteAlteradaTest {

	@Fixture private Long identificadorCaracteristica;
	@Fixture private Caracteristica caracteristicaTransiente;
	@Fixture private CaracteristicaService caracteristicaService;

	@Before
	public void configurarCaracteristica() throws Exception {
		caracteristicaTransiente.setNome("Característica alterada");
		caracteristicaService.alterar(caracteristicaTransiente);
	}

	@Test
	public void obterCaracteristicaPersistenteAlterada() throws Exception {
		Caracteristica caracteristica = caracteristicaService.getEntity(identificadorCaracteristica);
		assertEquals(identificadorCaracteristica, caracteristica.getId());
		assertEquals("Característica alterada", caracteristica.getNome());
	}

	@Test
	public void listarCaracteristicaPersistenteAlterada() throws Exception {
		List<Caracteristica> caracteristicas = caracteristicaService.getList();
		assertEquals(1, caracteristicas.size());
		assertEquals(identificadorCaracteristica, caracteristicas.get(0).getId());
		assertEquals("Característica alterada", caracteristicas.get(0).getNome());
	}

}
