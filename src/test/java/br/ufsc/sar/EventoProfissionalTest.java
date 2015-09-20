package br.ufsc.sar;

import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.ufsc.sar.entity.Evento;
import br.ufsc.sar.entity.EventoProfissional;
import br.ufsc.sar.entity.Profissional;
import br.ufsc.sar.service.EventoProfissionalService;
import br.ufsc.sar.service.EventoService;
import br.ufsc.sar.service.ProfissionalService;
import br.ufsc.sar.serviceimpl.EventoProfissionalServiceImpl;
import br.ufsc.sar.serviceimpl.EventoServiceImpl;
import br.ufsc.sar.serviceimpl.ProfissionalServiceImpl;
import junit.framework.TestCase;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EventoProfissionalTest extends TestCase{
	
	final static EventoProfissionalService eventoProfissionalService = new EventoProfissionalServiceImpl(); 
	
	final static EventoService eventoService = new EventoServiceImpl();
	
	final static ProfissionalService profissionalService = new ProfissionalServiceImpl();
	
	public static Evento createdEvento;
	
	public static Profissional createdProfissional;
	
	public static Long createdEventoProfissionalId;
	
	public EventoProfissionalTest(String testName){
		super(testName);		
	}
	
	@Test
	public void test0_setUp() throws Exception {
		System.out.println("Setup");
		Evento pr = new Evento();
		pr.setNome("Evento " + (Math.abs(new Random().nextInt())));
		long createdEventoId = eventoService.incluir(pr);		
		assertNotNull(createdEventoId);
		createdEvento = (Evento) eventoService.getEntity(createdEventoId);
		assertNotNull(createdEvento);
		
		Profissional p = new Profissional();
		p.setNome("Profissional " + (Math.abs(new Random().nextInt())));
		p.setProfissao("Analista de Sistemas");
		long createdProfissionalId = profissionalService.incluir(p);		
		assertNotNull(createdProfissionalId);
		createdProfissional = (Profissional) profissionalService.getEntity(createdProfissionalId);
		assertNotNull(createdProfissional);
	}
	
	@Test
	//@Ignore
	public void test1_InsertEventoProfissional() throws Exception{
		System.out.println("Inserir");
		EventoProfissional hrEventoProfissional = new EventoProfissional();
		hrEventoProfissional.setEvento(createdEvento);
		hrEventoProfissional.setProfissional(createdProfissional);
		createdEventoProfissionalId = eventoProfissionalService.incluir(hrEventoProfissional);
		assertNotNull(createdEventoProfissionalId);
	}
		
	@Test
	public void test2_AlterarEventoProfissional() throws Exception{
		System.out.println("Alterar");
		EventoProfissional hrEventoProfissional = (EventoProfissional) eventoProfissionalService.getEntity(createdEventoProfissionalId);
		assertNotNull(hrEventoProfissional);
		System.out.println("Nome evento:" + hrEventoProfissional.getEvento().getNome());
		System.out.println("Nome caracteristica:" + hrEventoProfissional.getProfissional().getNome());
		assertTrue(eventoProfissionalService.alterar(hrEventoProfissional));			
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void test3_ListEventoProfissional() throws Exception{
		System.out.println("Listar");
		List<EventoProfissional> hrsEvento = (List<EventoProfissional>) eventoProfissionalService.getList();
		assertNotNull(hrsEvento);
		assertFalse(hrsEvento.isEmpty());
		EventoProfissional hrEvento = hrsEvento.get(0);
		assertNotNull(hrEvento);
		System.out.println("Nome evento:" + hrEvento.getEvento().getNome());
		System.out.println("Nome característica:" + hrEvento.getProfissional().getNome());
		assertNotNull(hrEvento.getEvento());
		assertNotNull(hrEvento.getProfissional());
	}
	
	@Test
	//@Ignore
	public void test4_DeleteEventoProfissional() throws Exception{
		System.out.println("Deletar");
		EventoProfissional hrEvento = (EventoProfissional) eventoProfissionalService.getEntity(createdEventoProfissionalId);
		assertNotNull(hrEvento);
		assertTrue(eventoProfissionalService.excluir(createdEventoProfissionalId));
		hrEvento = (EventoProfissional) eventoProfissionalService.getEntity(createdEventoProfissionalId);
		assertNull(hrEvento);		
	}
	
	@After
	public void test5_tearDown() throws Exception {
		System.out.println("Tear down");
		Long createdEventoId = createdEvento.getId();
		Evento evento = (Evento) eventoService.getEntity(createdEventoId);
		assertNotNull(evento);
		assertTrue(eventoService.excluir(createdEventoId));
		evento = (Evento) eventoService.getEntity(createdEventoId);
		assertNull(evento);	
		
		Long createdProfissionalId = createdProfissional.getId();
		Profissional profissional = (Profissional) profissionalService.getEntity(createdProfissionalId);
		assertNotNull(profissional);
		assertTrue(profissionalService.excluir(createdProfissionalId));
		profissional = (Profissional) profissionalService.getEntity(createdProfissionalId);
		assertNull(profissional);	
	}
}
