package br.ufsc.sar.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.ufsc.sar.controller.AppController;
import br.ufsc.sar.gui.AppGUI;

public class AppListener implements ActionListener {

	private static AppController controller = null;
		
	public AppListener(AppGUI app){
		controller = new AppController(app);
	}
	
	public void actionPerformed(ActionEvent e) {
		// Iniciar cadastro de características
		if (e.getActionCommand().equals("Característica")){
			System.out.println("Acessando Cadastro -> Caracterísitca");
			controller.getCaracteristicaGUI();
		}
		
		// Iniciar cadstro de profissional
		if (e.getActionCommand().equals("Profissional")){
			System.out.println("Profissional");
			controller.getProfissionalGUI();
		}
		
		// Iniciar cadstro de espaço
		if (e.getActionCommand().equals("Espaço")){
			System.out.println("Espaço");
			controller.getEspacoGUI();
		}		
	}

	public static AppController getController() {
		return controller;
	}
}