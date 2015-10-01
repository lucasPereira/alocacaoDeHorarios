package br.ufsc.sar;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;

import br.ufsc.ine.leb.projetos.estoria.*;
import br.ufsc.sar.entity.*;
import br.ufsc.sar.service.*;

@FixtureSetup(CaracateristicaPersistenteTest.class)
public class CaracateristicaPersistenteRemovidaTest {

	@Fixture private CaracteristicaService caracteristicaService;
	@Fixture private Long identificador;

	@Before
	public void configurar() throws Exception {
		caracteristicaService.excluir(identificador);
	}

	@Test
	public void obter() throws Exception {
		Caracteristica caracteristica = caracteristicaService.getEntity(identificador);
		assertNull(caracteristica);
	}

	@Test
	public void listar() throws Exception {
		List<Caracteristica> caracteristicas = caracteristicaService.getList();
		assertEquals(0, caracteristicas.size());
	}

}
