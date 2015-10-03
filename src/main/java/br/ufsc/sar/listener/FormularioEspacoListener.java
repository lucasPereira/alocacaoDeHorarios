/**
 * 
 */
package br.ufsc.sar.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.ufsc.sar.controller.FormularioEspacoController;
import br.ufsc.sar.entity.Espaco;
import br.ufsc.sar.gui.EspacoGUI;
import br.ufsc.sar.gui.FormularioEspacoGUI;

/**
 * @author ErnaniCésar
 *
 */
public class FormularioEspacoListener extends AppListener {

	private static FormularioEspacoGUI formularioEspacoGUI = null;
	
	private static FormularioEspacoController formularioEspacoController = null;
	
	public FormularioEspacoController getFormularioEspacoController() {
		return formularioEspacoController;
	}

	public FormularioEspacoGUI getFormularioEspacoGUI() {
		return formularioEspacoGUI;
	}


	public FormularioEspacoListener(FormularioEspacoGUI form) {
		super(formularioEspacoGUI.getAplicacaoGUI());
		formularioEspacoGUI = form;
		formularioEspacoController = new FormularioEspacoController(formularioEspacoGUI);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Salvar")){
			try {
				formularioEspacoController.alterar();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(e.getActionCommand().equals("Excluir")){
			try {
				formularioEspacoController.excluir();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(e.getActionCommand().equals("Cancelar")){
			System.out.println("Cancelar e voltar para a tela de consulta!");
			this.getController().getEspacoGUI();
			
		}
		
	}

	

}