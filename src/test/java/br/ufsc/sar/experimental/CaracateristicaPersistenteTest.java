package br.ufsc.sar.experimental;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.*;

import org.junit.*;

import br.ufsc.ine.leb.projetos.estoria.*;
import br.ufsc.sar.entity.*;
import br.ufsc.sar.service.*;

@FixtureSetup({ BaseDeDadosZerada.class, CaracateristicaTransienteTest.class })
public class CaracateristicaPersistenteTest {

	@Fixture private Caracteristica caracteristicaTransiente;
	@Fixture private CaracteristicaService caracteristicaService;

	private Long identificadorCaracteristica;

	@Before
	public void configurarCaracteristica() throws Exception {
		identificadorCaracteristica = caracteristicaService.incluir(caracteristicaTransiente);
		assertNotNull(identificadorCaracteristica);
	}

	@Test
	public void obterCaracteristicaPersistente() throws Exception {
		Caracteristica caracteristica = caracteristicaService.getEntity(identificadorCaracteristica);
		assertEquals(identificadorCaracteristica, caracteristica.getId());
	}

	@Test
	public void listarCaracteristicaPersistente() throws Exception {
		List<Caracteristica> caracteristicas = caracteristicaService.getList();
		assertEquals(1, caracteristicas.size());
		assertEquals(identificadorCaracteristica, caracteristicas.get(0).getId());
	}

}
