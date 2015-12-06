package br.ufsc.sar.experimental;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.*;

import org.junit.*;

import br.ufsc.ine.leb.projetos.estoria.*;
import br.ufsc.sar.entity.*;
import br.ufsc.sar.service.*;

@FixtureSetup(CaracateristicaTransienteTest.class)
public class CaracateristicaPersistenteTest {

	@Fixture private CaracteristicaService caracteristicaService;
	@Fixture private Caracteristica caracteristicaTransiente;

	private Long identificador;

	@Before
	public void configurar() throws Exception {
		identificador = caracteristicaService.incluir(caracteristicaTransiente);
		assertNotNull(identificador);
	}

	@Test
	public void obter() throws Exception {
		Caracteristica caracteristica = caracteristicaService.getEntity(identificador);
		assertEquals(identificador, caracteristica.getId());
	}

	@Test
	public void listar() throws Exception {
		List<Caracteristica> caracteristicas = caracteristicaService.getList();
		assertEquals(1, caracteristicas.size());
		assertEquals(identificador, caracteristicas.get(0).getId());
	}

}
