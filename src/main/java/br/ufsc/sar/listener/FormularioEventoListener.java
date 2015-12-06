/**
 * 
 */
package br.ufsc.sar.listener;

import java.awt.event.ActionEvent;

import br.ufsc.sar.gui.FormularioEventoGUI;

/**
 * @author ErnaniCésar
 *
 */
public class FormularioEventoListener extends AppListener {

	private static FormularioEventoGUI formularioEventoGUI = null;
	
	public static FormularioEventoGUI getFormularioEventoGUI() {
		return formularioEventoGUI;
	}


	public static void setFormularioEventoGUI(FormularioEventoGUI formularioEventoGUI) {
		FormularioEventoListener.formularioEventoGUI = formularioEventoGUI;
	}


	@SuppressWarnings("static-access")
	public FormularioEventoListener(FormularioEventoGUI formularioEventoGUI) {
		super(formularioEventoGUI.getAplicacaoGUI());
		this.setFormularioEventoGUI(formularioEventoGUI);
	}

	
	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Salvar")){
			//System.out.println("Salvar os dados");
			this.getFormularioEventoGUI().getController().salvarEvento();
		}
		
		if(e.getActionCommand().equals("Excluir")){
			//System.out.println("Excluir o registro");
		}
		
		if(e.getActionCommand().equals("Cancelar")){
			//System.out.println("Cancelar e voltar para a tela de consulta!");
			this.getController().getEventoGUI();			
		}		
	}
}