package br.ufsc.sar.experimental;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import br.ufsc.sar.entity.Caracteristica;

public class CaracateristicaTransienteTest {

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
	
}
