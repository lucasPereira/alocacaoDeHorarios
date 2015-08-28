package br.ufsc.sar.controller;

import br.ufsc.sar.gui.AppGUI;
import br.ufsc.sar.gui.CaracteristicaGUI;
import br.ufsc.sar.service.AppService;
import br.ufsc.sar.serviceimpl.AppServiceImpl;

public class AppController {

	private static AppGUI aplicacaoGUI = null;
	
	public AppController(AppGUI app) {
		AppController.aplicacaoGUI = app;
	}

	final static AppService service = new AppServiceImpl();
	
	public static AppGUI getAplicacaoGUI() {
		return aplicacaoGUI;
	}
	
	public void getCaracteristicaGUI() {
		System.out.println("Remover todos os atributos do painel");
		aplicacaoGUI.getContentPane().removeAll();
		System.out.println("Adicionar novos componentes painel");
		aplicacaoGUI.getContentPane().setVisible(false);
		aplicacaoGUI.getContentPane().add(new CaracteristicaGUI(aplicacaoGUI));
		aplicacaoGUI.getContentPane().setVisible(true);
	}

}
