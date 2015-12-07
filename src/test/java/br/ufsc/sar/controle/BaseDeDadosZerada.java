package br.ufsc.sar.controle;

import br.ufsc.sar.service.CaracteristicaService;
import br.ufsc.sar.service.EventoProfissionalService;
import br.ufsc.sar.service.EventoService;
import br.ufsc.sar.service.ProfissionalService;
import br.ufsc.sar.serviceimpl.CaracteristicaServiceImpl;
import br.ufsc.sar.serviceimpl.EventoProfissionalServiceImpl;
import br.ufsc.sar.serviceimpl.EventoServiceImpl;
import br.ufsc.sar.serviceimpl.ProfissionalServiceImpl;

public class BaseDeDadosZerada {

	private static EventoService eventoService;
	private static ProfissionalService profissionalService;
	private static CaracteristicaService caracteristicaService;
	private static EventoProfissionalService eventoProfissionalService;

	public static void criarBase() {
		eventoService = new EventoServiceImpl();
		profissionalService = new ProfissionalServiceImpl();
		caracteristicaService = new CaracteristicaServiceImpl();
		eventoProfissionalService = new EventoProfissionalServiceImpl();
		eventoProfissionalService.exluirTodos();
		caracteristicaService.exluirTodos();
		profissionalService.exluirTodos();
		eventoService.exluirTodos();
	}

	public static EventoService obterEventoService() {
		return eventoService;
	}

	public static ProfissionalService obterProfissionalService() {
		return profissionalService;
	}

	public static CaracteristicaService obterCaracteristicaService() {
		return caracteristicaService;
	}

	public static EventoProfissionalService obterEventoProfissionalService() {
		return eventoProfissionalService;
	}

}
