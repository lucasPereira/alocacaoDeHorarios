package br.ufsc.sar.experimental;

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
public class BaseDeDadosZerada {

	private EventoService eventoService;
	private ProfissionalService profissionalService;
	private CaracteristicaService caracteristicaService;
	private EventoProfissionalService eventoProfissionalService;

	@Before
	public void criarBase() throws Exception {
		eventoService = new EventoServiceImpl();
		profissionalService = new ProfissionalServiceImpl();
		caracteristicaService = new CaracteristicaServiceImpl();
		eventoProfissionalService = new EventoProfissionalServiceImpl();
		eventoProfissionalService.exluirTodos();
		caracteristicaService.exluirTodos();
		profissionalService.exluirTodos();
		eventoService.exluirTodos();
	}

}
