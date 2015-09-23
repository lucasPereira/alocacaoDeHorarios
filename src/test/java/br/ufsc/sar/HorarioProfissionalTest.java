package br.ufsc.sar;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.ufsc.sar.entity.HorarioProfissional;
import br.ufsc.sar.entity.Profissional;
import br.ufsc.sar.service.HorarioProfissionalService;
import br.ufsc.sar.service.ProfissionalService;
import br.ufsc.sar.serviceimpl.HorarioProfissionalServiceImpl;
import br.ufsc.sar.serviceimpl.ProfissionalServiceImpl;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HorarioProfissionalTest {

	final static ProfissionalService profissionalService = new ProfissionalServiceImpl();
	final static HorarioProfissionalService horarioProfissionalService = new HorarioProfissionalServiceImpl();
	public static Profissional createdProfissional;
	public static Long createdHorarioProfissionalId;

	@Test
	public void test0_setUp() throws Exception {
		System.out.println("Setup");
		Profissional pr = new Profissional();
		pr.setNome("Profissional " + (Math.abs(new Random().nextInt())));
		pr.setProfissao("Analista de Sistemas");
		long createdProfissionalId = profissionalService.incluir(pr);
		assertNotNull(createdProfissionalId);
		createdProfissional = profissionalService.getEntity(createdProfissionalId);
		assertNotNull(createdProfissional);
	}

	@Test
	// @Ignore
	public void test1_InsertHorarioProfissional() throws Exception {
		System.out.println("Inserir");
		HorarioProfissional hrProfissional = new HorarioProfissional();
		hrProfissional.setProfissional(createdProfissional);
		hrProfissional.setDatainicio(new Date());
		hrProfissional.setDatatermino(new Date());
		hrProfissional.setDiadasemana("Domingo");
		hrProfissional.setHorainicio("16:00");
		hrProfissional.setHoratermino("18:00");
		createdHorarioProfissionalId = horarioProfissionalService.incluir(hrProfissional);
		assertNotNull(createdHorarioProfissionalId);
	}

	@Test
	public void test2_AlterarHorarioProfissional() throws Exception {
		System.out.println("Alterar");
		HorarioProfissional hrProfissional = (HorarioProfissional) horarioProfissionalService.getEntity(createdHorarioProfissionalId);
		assertNotNull(hrProfissional);
		System.out.println("Nome:" + hrProfissional.getProfissional().getNome());
		System.out.println("Dia da semana:" + hrProfissional.getDiadasemana());
		hrProfissional.setDiadasemana("Sábado");
		assertTrue(horarioProfissionalService.alterar(hrProfissional));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void test3_ListHorarioProfissional() throws Exception {
		System.out.println("Listar");
		List<HorarioProfissional> hrsProfissional = (List<HorarioProfissional>) horarioProfissionalService.getList();
		assertNotNull(hrsProfissional);
		assertFalse(hrsProfissional.isEmpty());
		HorarioProfissional hrProfissional = hrsProfissional.get(0);
		assertNotNull(hrProfissional);
		System.out.println("Nome:" + hrProfissional.getProfissional().getNome());
		System.out.println("Dia da semana:" + hrProfissional.getDiadasemana());
		System.out.println("Data de início:" + hrProfissional.getDatainicio());
		System.out.println("Data de término:" + hrProfissional.getDatatermino());
		System.out.println("Hora de início:" + hrProfissional.getHorainicio());
		System.out.println("Hora de término:" + hrProfissional.getHoratermino());
		assertNotNull(hrProfissional.getProfissional());
		assertNotNull(hrProfissional.getDiadasemana());
		assertFalse(hrProfissional.getDiadasemana().trim().isEmpty());
	}

	@Test
	// @Ignore
	public void test4_DeleteHorarioProfissional() throws Exception {
		System.out.println("Deletar");
		HorarioProfissional hrProfissional = (HorarioProfissional) horarioProfissionalService.getEntity(createdHorarioProfissionalId);
		assertNotNull(hrProfissional);
		assertTrue(horarioProfissionalService.excluir(createdHorarioProfissionalId));
		hrProfissional = (HorarioProfissional) horarioProfissionalService.getEntity(createdHorarioProfissionalId);
		assertNull(hrProfissional);
	}

	@After
	public void test5_tearDown() throws Exception {
		System.out.println("Tear down");
		Long createdProfissionalId = createdProfissional.getId();
		Profissional profissional = profissionalService.getEntity(createdProfissionalId);
		assertNotNull(profissional);
		assertTrue(profissionalService.excluir(createdProfissionalId));
		profissional = profissionalService.getEntity(createdProfissionalId);
		assertNull(profissional);
	}

}
