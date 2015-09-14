package br.ufsc.sar;

import java.util.Random;

import org.junit.*;

import br.ufsc.sar.entity.Caracteristica;
import br.ufsc.sar.service.CaracteristicaService;
import br.ufsc.sar.serviceimpl.CaracteristicaServiceImpl;
import junit.framework.TestCase;

@Ignore
public class CaracateristicaTest extends TestCase{
	
	final static CaracteristicaService caracteristicaService = new CaracteristicaServiceImpl(); 
	
	public CaracateristicaTest(String testName){
		super(testName);		
	}
	
	@Test
	public void testInsertCaracteristica() throws Exception{
		Caracteristica caracteristica = new Caracteristica();
		caracteristica.setNome("Ar condicionado");
		caracteristica.setForauso(false);
		caracteristicaService.incluir(caracteristica);
	}
	
	@Test
	public void testAlterarCaracteristica() throws Exception{
		System.out.println("Alterar");
		Caracteristica caracteristica = (Caracteristica) caracteristicaService.getEntity(1L);
		System.out.println("Nome:" + caracteristica.getNome());
		caracteristica.setNome("" + new Random().nextInt());
		caracteristicaService.alterar(caracteristica);
	}
}
