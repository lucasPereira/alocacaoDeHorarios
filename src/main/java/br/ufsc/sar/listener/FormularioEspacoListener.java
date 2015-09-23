/**
 * 
 */
package br.ufsc.sar.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.ufsc.sar.entity.Espaco;
import br.ufsc.sar.gui.EspacoGUI;
import br.ufsc.sar.gui.FormularioEspacoGUI;

/**
 * @author ErnaniCésar
 *
 */
public class FormularioEspacoListener extends AppListener {

	private static FormularioEspacoGUI formularioEspacoGUI = null;
	
	public static FormularioEspacoGUI getFormularioEspacoGUI() {
		return formularioEspacoGUI;
	}


	public static void setFormularioEspacoGUI(FormularioEspacoGUI formularioEspacoGUI) {
		FormularioEspacoListener.formularioEspacoGUI = formularioEspacoGUI;
	}


	public FormularioEspacoListener(FormularioEspacoGUI formularioEspacoGUI) {
		super(formularioEspacoGUI.getAplicacaoGUI());
		this.setFormularioEspacoGUI(formularioEspacoGUI);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Salvar")){
			System.out.println("Salvar os dados");
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