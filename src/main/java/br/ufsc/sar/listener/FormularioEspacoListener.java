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

	private FormularioEspacoGUI formularioEspacoGUI = null;
	
	private FormularioEspacoController formularioEspacoController = null;
	
	public FormularioEspacoController getFormularioEspacoController() {
		return formularioEspacoController;
	}


	public void setFormularioEspacoController(FormularioEspacoController formularioEspacoController) {
		this.formularioEspacoController = formularioEspacoController;
	}


	public FormularioEspacoGUI getFormularioEspacoGUI() {
		return this.formularioEspacoGUI;
	}


	public void setFormularioEspacoGUI(FormularioEspacoGUI formularioEspacoGUI) {
		this.formularioEspacoGUI = formularioEspacoGUI;
	}


	public FormularioEspacoListener(FormularioEspacoGUI formularioEspacoGUI) {
		super(formularioEspacoGUI.getAplicacaoGUI());
		this.setFormularioEspacoGUI(formularioEspacoGUI);
		this.setFormularioEspacoController(new FormularioEspacoController(this.getFormularioEspacoGUI()));
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Salvar")){
			try {
				this.getFormularioEspacoController().alterar();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(e.getActionCommand().equals("Excluir")){
			System.out.println("Excluir o registro");
		}
		
		if(e.getActionCommand().equals("Cancelar")){
			System.out.println("Cancelar e voltar para a tela de consulta!");
			this.getController().getEspacoGUI();
			
		}
		
	}

	

}