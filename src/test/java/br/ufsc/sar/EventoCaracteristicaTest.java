package br.ufsc.sar;

import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.ufsc.sar.entity.Caracteristica;
import br.ufsc.sar.entity.Evento;
import br.ufsc.sar.entity.EventoCaracteristica;
import br.ufsc.sar.service.CaracteristicaService;
import br.ufsc.sar.service.EventoCaracteristicaService;
import br.ufsc.sar.service.EventoService;
import br.ufsc.sar.serviceimpl.CaracteristicaServiceImpl;
import br.ufsc.sar.serviceimpl.EventoCaracteristicaServiceImpl;
import br.ufsc.sar.serviceimpl.EventoServiceImpl;
import junit.framework.TestCase;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EventoCaracteristicaTest extends TestCase{
	
	final static EventoCaracteristicaService eventoCaracteristicaService = new EventoCaracteristicaServiceImpl(); 
	
	final static EventoService eventoService = new EventoServiceImpl();
	
	final static CaracteristicaService caracteristicaService = new CaracteristicaServiceImpl();
	
	public static Evento createdEvento;
	
	public static Caracteristica createdCaracteristica;
	
	public static Long createdEventoCaracteristicaId;
	
	public EventoCaracteristicaTest(String testName){
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
		
		Caracteristica c = new Caracteristica();
		c.setNome("Caracteristica " + (Math.abs(new Random().nextInt())));
		long createdCaracteristicaId = caracteristicaService.incluir(c);		
		assertNotNull(createdCaracteristicaId);
		createdCaracteristica = (Caracteristica) caracteristicaService.getEntity(createdCaracteristicaId);
		assertNotNull(createdCaracteristica);
	}
	
	@Test
	//@Ignore
	public void test1_InsertEventoCaracteristica() throws Exception{
		System.out.println("Inserir");
		EventoCaracteristica hrEventoCaracteristica = new EventoCaracteristica();
		hrEventoCaracteristica.setEvento(createdEvento);
		hrEventoCaracteristica.setCaracteristica(createdCaracteristica);
		hrEventoCaracteristica.setQuantidade(1);
		createdEventoCaracteristicaId = eventoCaracteristicaService.incluir(hrEventoCaracteristica);
		assertNotNull(createdEventoCaracteristicaId);
	}
		
	@Test
	public void test2_AlterarEventoCaracteristica() throws Exception{
		System.out.println("Alterar");
		EventoCaracteristica hrEventoCaracteristica = (EventoCaracteristica) eventoCaracteristicaService.getEntity(createdEventoCaracteristicaId);
		assertNotNull(hrEventoCaracteristica);
		System.out.println("Nome evento:" + hrEventoCaracteristica.getEvento().getNome());
		System.out.println("Nome caracteristica:" + hrEventoCaracteristica.getCaracteristica().getNome());
		System.out.println("Quantidade:" + hrEventoCaracteristica.getQuantidade());
		hrEventoCaracteristica.setQuantidade(45);
		assertTrue(eventoCaracteristicaService.alterar(hrEventoCaracteristica));			
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void test3_ListEventoCaracteristica() throws Exception{
		System.out.println("Listar");
		List<EventoCaracteristica> hrsEvento = (List<EventoCaracteristica>) eventoCaracteristicaService.getList();
		assertNotNull(hrsEvento);
		assertFalse(hrsEvento.isEmpty());
		EventoCaracteristica hrEvento = hrsEvento.get(0);
		assertNotNull(hrEvento);
		System.out.println("Nome evento:" + hrEvento.getEvento().getNome());
		System.out.println("Nome característica:" + hrEvento.getCaracteristica().getNome());
		System.out.println("Quantidade:" + hrEvento.getQuantidade());
		assertNotNull(hrEvento.getEvento());
		assertNotNull(hrEvento.getCaracteristica());
		assertNotNull(hrEvento.getQuantidade());
	}
	
	@Test
	//@Ignore
	public void test4_DeleteEventoCaracteristica() throws Exception{
		System.out.println("Deletar");
		EventoCaracteristica hrEvento = (EventoCaracteristica) eventoCaracteristicaService.getEntity(createdEventoCaracteristicaId);
		assertNotNull(hrEvento);
		assertTrue(eventoCaracteristicaService.excluir(createdEventoCaracteristicaId));
		hrEvento = (EventoCaracteristica) eventoCaracteristicaService.getEntity(createdEventoCaracteristicaId);
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
		
		Long createdCaracteristicaId = createdCaracteristica.getId();
		Caracteristica caracteristica = (Caracteristica) caracteristicaService.getEntity(createdCaracteristicaId);
		assertNotNull(caracteristica);
		assertTrue(caracteristicaService.excluir(createdCaracteristicaId));
		caracteristica = (Caracteristica) caracteristicaService.getEntity(createdCaracteristicaId);
		assertNull(caracteristica);	
	}
}
