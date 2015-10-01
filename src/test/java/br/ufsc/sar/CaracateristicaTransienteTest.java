package br.ufsc.sar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.*;

import br.ufsc.ine.leb.projetos.estoria.*;
import br.ufsc.sar.entity.*;
import br.ufsc.sar.service.*;

@FixtureSetup(BaseDeDadosZeradaTest.class)
public class CaracateristicaTransienteTest {

	@Fixture private CaracteristicaService caracteristicaService;

	private Caracteristica caracteristicaTransiente;

	@Before
	public void configurar() throws Exception {
		caracteristicaTransiente = new Caracteristica();
		caracteristicaTransiente.setNome("Característica");
		caracteristicaTransiente.setForauso(false);
	}

	@Test
	public void testar() throws Exception {
		assertNull(caracteristicaTransiente.getId());
		assertEquals("Característica", caracteristicaTransiente.getNome());
		assertFalse(caracteristicaTransiente.getForauso());
	}
	
}
