package br.ufsc.sar;

import static org.junit.Assert.assertTrue;

import org.junit.*;

import br.ufsc.sar.serviceimpl.*;

public class BaseDeDadosZeradaTest {

	private CaracteristicaServiceImpl caracteristicaService;
	private EspacoServiceImpl servicoEspaco;
	private EventoCaracteristicaServiceImpl servicoEventoCaracteristica;
	private EventoProfissionalServiceImpl servicoEventoProfissional;
	private EventoServiceImpl servicoEvento;
	private HorarioEventoServiceImpl servicoHorarioEvento;
	private HorarioProfissionalServiceImpl servicoHorarioProfissional;
	private ProfissionalServiceImpl servicoProfissional;

	@Before
	public void configurar() throws Exception {
		servicoEspaco = new EspacoServiceImpl();
		servicoEvento = new EventoServiceImpl();
		servicoProfissional = new ProfissionalServiceImpl();
		caracteristicaService = new CaracteristicaServiceImpl();
		servicoHorarioEvento = new HorarioEventoServiceImpl();
		servicoHorarioProfissional = new HorarioProfissionalServiceImpl();
		servicoEventoProfissional = new EventoProfissionalServiceImpl();
		servicoEventoCaracteristica = new EventoCaracteristicaServiceImpl();
		servicoEspaco.exluirTodos();
		servicoEvento.exluirTodos();
		servicoProfissional.exluirTodos();
		caracteristicaService.exluirTodos();
		servicoHorarioEvento.exluirTodos();
		servicoHorarioProfissional.exluirTodos();
		servicoEventoProfissional.exluirTodos();
		servicoEventoCaracteristica.exluirTodos();
	}

	@Test
	public void testar() throws Exception {
		assertTrue(servicoEspaco.getList().isEmpty());
		assertTrue(servicoEvento.getList().isEmpty());
		assertTrue(servicoProfissional.getList().isEmpty());
		assertTrue(caracteristicaService.getList().isEmpty());
		assertTrue(servicoHorarioEvento.getList().isEmpty());
		assertTrue(servicoHorarioProfissional.getList().isEmpty());
		assertTrue(servicoEventoProfissional.getList().isEmpty());
		assertTrue(servicoEventoCaracteristica.getList().isEmpty());
	}

}
