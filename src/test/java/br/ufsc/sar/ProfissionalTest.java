package br.ufsc.sar;

import java.util.List;
import java.util.Random;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.ufsc.sar.entity.Profissional;
import br.ufsc.sar.service.ProfissionalService;
import br.ufsc.sar.serviceimpl.ProfissionalServiceImpl;
import junit.framework.TestCase;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProfissionalTest extends TestCase{
	
	final static ProfissionalService profissionalService = new ProfissionalServiceImpl(); 
	
	public static Long createdId;
	
	public ProfissionalTest(String testName){
		super(testName);		
	}
	
	@Test
	//@Ignore
	public void test1_InsertProfissional() throws Exception{
		System.out.println("Inserir");
		Profissional profissional = new Profissional();
		profissional.setNome("Profissional " + (Math.abs(new Random().nextInt())));
		profissional.setProfissao("Analista de Sistemas");
		createdId = profissionalService.incluir(profissional);
		assertNotNull(createdId);
	}
		
	@Test
	public void test2_AlterarProfissional() throws Exception{
		System.out.println("Alterar");
		Profissional profissional = profissionalService.getEntity(createdId);
		assertNotNull(profissional);
		System.out.println("Nome:" + profissional.getNome());
		profissional.setNome("Profissional " + (Math.abs(new Random().nextInt())));
		assertTrue(profissionalService.alterar(profissional));			
	}
	
	@Test
	public void test3_ListProfissional() throws Exception{
		System.out.println("Listar");
		List<Profissional> profissionais = profissionalService.getList();
		assertNotNull(profissionais);
		assertFalse(profissionais.isEmpty());
		Profissional profissional = profissionais.get(0);
		assertNotNull(profissional);
		System.out.println("Nome:" + profissional.getNome());
		assertNotNull(profissional.getNome());
		assertFalse(profissional.getNome().trim().isEmpty());
	}
	
	@Test
	//@Ignore
	public void test4_DeleteProfissional() throws Exception{
		System.out.println("Deletar");
		Profissional profissional = profissionalService.getEntity(createdId);
		assertNotNull(profissional);
		assertTrue(profissionalService.excluir(createdId));
		profissional = profissionalService.getEntity(createdId);
		assertNull(profissional);		
	}
}
