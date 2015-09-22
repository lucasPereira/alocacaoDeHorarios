package br.ufsc.sar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.*;

import br.ufsc.ine.leb.projetos.estoria.*;
import br.ufsc.sar.entity.*;
import br.ufsc.sar.service.*;

@FixtureSetup(BaseDeDadosZeradaTest.class)
public class CaracateristicaTest {

	@Fixture private CaracteristicaService caracteristicaService;

	private Caracteristica caracteristica;

	@Before
	public void configurar() throws Exception {
		caracteristica = new Caracteristica();
		caracteristica.setNome("Ar condicionado");
		caracteristica.setForauso(false);
		caracteristicaService.incluir(caracteristica);
		caracteristica = caracteristicaService.getList().get(0);
	}

	@Test
	public void testInsertCaracteristica() throws Exception {
		assertEquals("Ar condicionado", caracteristica.getNome());
		assertFalse(caracteristica.getForauso());
	}

	@Test
	public void testAlterarCaracteristica() throws Exception {
		caracteristica.setNome("Ar condicionado alterado");
		caracteristicaService.alterar(caracteristica);
		Caracteristica caracteristicaAlterada = caracteristicaService.getList().get(0);

		assertEquals("Ar condicionado alterado", caracteristicaAlterada.getNome());
		assertEquals(caracteristica.getId(), caracteristicaAlterada.getId());
		assertFalse(caracteristicaAlterada.getForauso());
	}

}
