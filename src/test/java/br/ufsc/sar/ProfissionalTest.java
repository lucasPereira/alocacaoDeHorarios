package br.ufsc.sar;

import java.util.Random;

import org.junit.Test;

import br.ufsc.sar.entity.Profissional;
import br.ufsc.sar.service.ProfissionalService;
import br.ufsc.sar.serviceimpl.ProfissionalServiceImpl;
import junit.framework.TestCase;

public class ProfissionalTest extends TestCase{
	
	final static ProfissionalService profissionalService = new ProfissionalServiceImpl(); 
	
	public ProfissionalTest(String testName){
		super(testName);		
	}
	
	@Test
	public void testInsertProfissional() throws Exception{
		Profissional profissional = new Profissional();
		profissional.setNome("João");
		profissional.setProfissao("Analista de Sistemas");
		profissionalService.incluir(profissional);
	}
	
	@Test
	public void testAlterarCaracteristica() throws Exception{
		System.out.println("Alterar");
		Profissional profissional = (Profissional) profissionalService.getEntity(1L);
		System.out.println("Nome:" + profissional.getNome());
		profissional.setNome("Profissional " + new Random().nextInt());
		profissionalService.alterar(profissional);
	}
}
