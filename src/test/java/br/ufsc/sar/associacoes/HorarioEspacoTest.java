package br.ufsc.sar.associacoes;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.ufsc.sar.entity.Espaco;
import br.ufsc.sar.entity.HorarioEspaco;
import br.ufsc.sar.service.EspacoService;
import br.ufsc.sar.service.HorarioEspacoService;
import br.ufsc.sar.serviceimpl.EspacoServiceImpl;
import br.ufsc.sar.serviceimpl.HorarioEspacoServiceImpl;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HorarioEspacoTest {

	final static EspacoService espacoService = new EspacoServiceImpl();
	final static HorarioEspacoService horarioEspacoService = new HorarioEspacoServiceImpl();
	public static Espaco createdEspaco;
	public static Long createdHorarioEspacoId;

	@Test
	public void test0_setUp() throws Exception {
		System.out.println("Setup");
		Espaco pr = new Espaco();
		pr.setNome("Espaço " + (Math.abs(new Random().nextInt())));
		pr.setDescricao("Teste " + System.currentTimeMillis());
		long createdEspacoId = espacoService.incluir(pr);
		assertNotNull(createdEspacoId);
		createdEspaco = (Espaco) espacoService.getEntity(createdEspacoId);
		assertNotNull(createdEspaco);
	}

	@Test
	// @Ignore
	public void test1_InsertHorarioEspaco() throws Exception {
		System.out.println("Inserir");
		HorarioEspaco hrEspaco = new HorarioEspaco();
		hrEspaco.setEspaco(createdEspaco);
		hrEspaco.setDatainicio(new Date());
		hrEspaco.setDatatermino(new Date());
		hrEspaco.setDiadasemana("Domingo");
		hrEspaco.setHorainicio("16:00");
		hrEspaco.setHoratermino("18:00");
		createdHorarioEspacoId = horarioEspacoService.incluir(hrEspaco);
		assertNotNull(createdHorarioEspacoId);
	}

	@Test
	public void test2_AlterarHorarioEspaco() throws Exception {
		System.out.println("Alterar");
		HorarioEspaco hrEspaco = (HorarioEspaco) horarioEspacoService.getEntity(createdHorarioEspacoId);
		assertNotNull(hrEspaco);
		System.out.println("Nome:" + hrEspaco.getEspaco().getNome());
		System.out.println("Dia da semana:" + hrEspaco.getDiadasemana());
		hrEspaco.setDiadasemana("Sábado");
		assertTrue(horarioEspacoService.alterar(hrEspaco));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void test3_ListHorarioEspaco() throws Exception {
		System.out.println("Listar");
		List<HorarioEspaco> hrsEspaco = (List<HorarioEspaco>) horarioEspacoService.getList();
		assertNotNull(hrsEspaco);
		assertFalse(hrsEspaco.isEmpty());
		HorarioEspaco hrEspaco = hrsEspaco.get(0);
		assertNotNull(hrEspaco);
		System.out.println("Nome:" + hrEspaco.getEspaco().getNome());
		System.out.println("Dia da semana:" + hrEspaco.getDiadasemana());
		System.out.println("Data de início:" + hrEspaco.getDatainicio());
		System.out.println("Data de término:" + hrEspaco.getDatatermino());
		System.out.println("Hora de início:" + hrEspaco.getHorainicio());
		System.out.println("Hora de término:" + hrEspaco.getHoratermino());
		assertNotNull(hrEspaco.getEspaco());
		assertNotNull(hrEspaco.getDiadasemana());
		assertFalse(hrEspaco.getDiadasemana().trim().isEmpty());
	}

	@Test
	// @Ignore
	public void test4_DeleteHorarioEspaco() throws Exception {
		System.out.println("Deletar");
		HorarioEspaco hrEspaco = (HorarioEspaco) horarioEspacoService.getEntity(createdHorarioEspacoId);
		assertNotNull(hrEspaco);
		assertTrue(horarioEspacoService.excluir(createdHorarioEspacoId));
		hrEspaco = (HorarioEspaco) horarioEspacoService.getEntity(createdHorarioEspacoId);
		assertNull(hrEspaco);
	}

	@After
	public void test5_tearDown() throws Exception {
		System.out.println("Tear down");
		Long createdEspacoId = createdEspaco.getId();
		Espaco espaco = (Espaco) espacoService.getEntity(createdEspacoId);
		assertNotNull(espaco);
		assertTrue(espacoService.excluir(createdEspacoId));
		espaco = (Espaco) espacoService.getEntity(createdEspacoId);
		assertNull(espaco);
	}

}
