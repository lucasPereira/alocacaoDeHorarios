package br.ufsc.sar.experimental;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.*;

import br.ufsc.ine.leb.projetos.estoria.*;
import br.ufsc.sar.entity.*;
import br.ufsc.sar.service.*;

@FixtureSetup(CaracateristicaPersistenteTest.class)
public class CaracateristicaPersistenteAlteradaTest {

	@Fixture private CaracteristicaService caracteristicaService;
	@Fixture private Caracteristica caracteristicaTransiente;
	@Fixture private Long identificador;

	@Before
	public void configurar() throws Exception {
		caracteristicaTransiente.setNome("Característica alterada");
		caracteristicaService.alterar(caracteristicaTransiente);
	}

	@Test
	public void obter() throws Exception {
		Caracteristica caracteristica = caracteristicaService.getEntity(identificador);
		assertEquals(identificador, caracteristica.getId());
		assertEquals("Característica alterada", caracteristica.getNome());
	}

	@Test
	public void listar() throws Exception {
		List<Caracteristica> caracteristicas = caracteristicaService.getList();
		assertEquals(1, caracteristicas.size());
		assertEquals(identificador, caracteristicas.get(0).getId());
		assertEquals("Característica alterada", caracteristicas.get(0).getNome());
	}

}
