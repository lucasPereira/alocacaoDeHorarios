package br.ufsc.sar.controller;

import javax.swing.JPanel;

import br.ufsc.sar.gui.AppGUI;
import br.ufsc.sar.gui.CaracteristicaGUI;
import br.ufsc.sar.gui.EspacoGUI;
import br.ufsc.sar.gui.ProfissionalGUI;
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
	
	/**
	 * 
	 * @param gui
	 */
	private void getEntityGUI(JPanel entityGUI){
		System.out.println("Remover todos os atributos do painel");
		aplicacaoGUI.getContentPane().removeAll();
		System.out.println("Adicionar novos componentes painel");
		aplicacaoGUI.getContentPane().setVisible(false);
		aplicacaoGUI.getContentPane().add(entityGUI);
		entityGUI.setOpaque(true);
		aplicacaoGUI.getContentPane().setVisible(true);
	}
	
	public void getCaracteristicaGUI() {
		getEntityGUI(new CaracteristicaGUI(aplicacaoGUI));
	}
	
	public void getProfissionalGUI() {
		getEntityGUI(new ProfissionalGUI(aplicacaoGUI));
	}

	public void getEspacoGUI() {
		getEntityGUI(new EspacoGUI(aplicacaoGUI));
		
	}

}
