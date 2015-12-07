package br.ufsc.sar.experimental;

import static org.junit.Assert.assertTrue;

import org.junit.Before;

import br.ufsc.ine.leb.projetos.estoria.Singular;
import br.ufsc.sar.service.CaracteristicaService;
import br.ufsc.sar.service.EventoProfissionalService;
import br.ufsc.sar.service.EventoService;
import br.ufsc.sar.service.ProfissionalService;
import br.ufsc.sar.serviceimpl.CaracteristicaServiceImpl;
import br.ufsc.sar.serviceimpl.EventoProfissionalServiceImpl;
import br.ufsc.sar.serviceimpl.EventoServiceImpl;
import br.ufsc.sar.serviceimpl.ProfissionalServiceImpl;

@Singular
public class BaseDeDadosZeradaTest {

	private EventoService eventoService;
	private ProfissionalService profissionalService;
	private CaracteristicaService caracteristicaService;
	private EventoProfissionalService eventoProfissionalService;

	// private EspacoService servicoEspaco;
	// private EventoCaracteristicaService servicoEventoCaracteristica;
	// private HorarioEventoService servicoHorarioEvento;
	// private HorarioProfissionalService servicoHorarioProfissional;
	// private HorarioEspacoService servicoHorarioEspaco;
	// private EventoEspacoService servicoEventoEspaco;

	@Before
	public void configurar() throws Exception {
		eventoService = new EventoServiceImpl();
		profissionalService = new ProfissionalServiceImpl();
		caracteristicaService = new CaracteristicaServiceImpl();
		eventoProfissionalService = new EventoProfissionalServiceImpl();
		eventoProfissionalService.exluirTodos();
		caracteristicaService.exluirTodos();
		profissionalService.exluirTodos();
		eventoService.exluirTodos();
		// servicoEspaco = new EspacoServiceImpl();
		// servicoHorarioEvento = new HorarioEventoServiceImpl();
		// servicoHorarioProfissional = new HorarioProfissionalServiceImpl();
		// servicoEventoCaracteristica = new EventoCaracteristicaServiceImpl();
		// servicoEventoEspaco = new EventoEspacoServiceImpl();
		// servicoHorarioEspaco = new HorarioEspacoServiceImpl();
		// servicoHorarioEspaco.exluirTodos();
		// servicoEventoEspaco.exluirTodos();
		// servicoEventoCaracteristica.exluirTodos();
		// servicoHorarioEvento.exluirTodos();
		// servicoHorarioProfissional.exluirTodos();
		// servicoEspaco.exluirTodos();
	}

	public void testar() throws Exception {
		assertTrue(eventoService.getList().isEmpty());
		assertTrue(profissionalService.getList().isEmpty());
		assertTrue(caracteristicaService.getList().isEmpty());
		assertTrue(eventoProfissionalService.getList().isEmpty());
		// assertTrue(servicoEspaco.getList().isEmpty());
		// assertTrue(servicoHorarioEvento.getList().isEmpty());
		// assertTrue(servicoHorarioProfissional.getList().isEmpty());
		// assertTrue(servicoEventoCaracteristica.getList().isEmpty());
		// assertTrue(servicoEventoEspaco.getList().isEmpty());
		// assertTrue(servicoHorarioEspaco.getList().isEmpty());
	}

}
