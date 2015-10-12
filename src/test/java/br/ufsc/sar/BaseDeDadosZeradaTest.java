package br.ufsc.sar;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.ufsc.sar.service.CaracteristicaService;
import br.ufsc.sar.service.EspacoService;
import br.ufsc.sar.service.EventoCaracteristicaService;
import br.ufsc.sar.service.EventoEspacoService;
import br.ufsc.sar.service.EventoProfissionalService;
import br.ufsc.sar.service.EventoService;
import br.ufsc.sar.service.HorarioEspacoService;
import br.ufsc.sar.service.HorarioEventoService;
import br.ufsc.sar.service.HorarioProfissionalService;
import br.ufsc.sar.service.ProfissionalService;
import br.ufsc.sar.serviceimpl.CaracteristicaServiceImpl;
import br.ufsc.sar.serviceimpl.EspacoServiceImpl;
import br.ufsc.sar.serviceimpl.EventoCaracteristicaServiceImpl;
import br.ufsc.sar.serviceimpl.EventoEspacoServiceImpl;
import br.ufsc.sar.serviceimpl.EventoProfissionalServiceImpl;
import br.ufsc.sar.serviceimpl.EventoServiceImpl;
import br.ufsc.sar.serviceimpl.HorarioEspacoServiceImpl;
import br.ufsc.sar.serviceimpl.HorarioEventoServiceImpl;
import br.ufsc.sar.serviceimpl.HorarioProfissionalServiceImpl;
import br.ufsc.sar.serviceimpl.ProfissionalServiceImpl;

public class BaseDeDadosZeradaTest {

	private CaracteristicaService caracteristicaService;
	private EspacoService servicoEspaco;
	private EventoCaracteristicaService servicoEventoCaracteristica;
	private EventoProfissionalService servicoEventoProfissional;
	private EventoService eventoService;
	private HorarioEventoService servicoHorarioEvento;
	private HorarioProfissionalService servicoHorarioProfissional;
	private ProfissionalService profissionalService;
	private HorarioEspacoService servicoHorarioEspaco;
	private EventoEspacoService servicoEventoEspaco;

	@Before
	public void configurar() throws Exception {
		servicoEspaco = new EspacoServiceImpl();
		eventoService = new EventoServiceImpl();
		profissionalService = new ProfissionalServiceImpl();
		caracteristicaService = new CaracteristicaServiceImpl();
		servicoHorarioEvento = new HorarioEventoServiceImpl();
		servicoHorarioProfissional = new HorarioProfissionalServiceImpl();
		servicoEventoProfissional = new EventoProfissionalServiceImpl();
		servicoEventoCaracteristica = new EventoCaracteristicaServiceImpl();	
		servicoEventoEspaco = new EventoEspacoServiceImpl();
		servicoHorarioEspaco = new HorarioEspacoServiceImpl();
		servicoHorarioEspaco.exluirTodos();
		servicoEventoEspaco.exluirTodos();
		servicoEventoProfissional.exluirTodos();
		servicoEventoCaracteristica.exluirTodos();
		servicoHorarioEvento.exluirTodos();
		servicoHorarioProfissional.exluirTodos();
		eventoService.exluirTodos();
		servicoEspaco.exluirTodos();		
		profissionalService.exluirTodos();
		caracteristicaService.exluirTodos();		
	}

	@Test
	public void testar() throws Exception {
		assertTrue(servicoEspaco.getList().isEmpty());
		assertTrue(eventoService.getList().isEmpty());
		assertTrue(profissionalService.getList().isEmpty());
		assertTrue(caracteristicaService.getList().isEmpty());
		assertTrue(servicoHorarioEvento.getList().isEmpty());
		assertTrue(servicoHorarioProfissional.getList().isEmpty());
		assertTrue(servicoEventoProfissional.getList().isEmpty());
		assertTrue(servicoEventoCaracteristica.getList().isEmpty());
		assertTrue(servicoEventoEspaco.getList().isEmpty());
		assertTrue(servicoHorarioEspaco.getList().isEmpty());
	}

}
