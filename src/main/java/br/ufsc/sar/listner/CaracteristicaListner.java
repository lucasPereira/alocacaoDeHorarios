package br.ufsc.sar.listner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.ufsc.entity.BaseEntity;
import br.ufsc.sar.controller.AppController;
import br.ufsc.sar.controller.CaracteristicaController;
import br.ufsc.sar.gui.AppGUI;
import br.ufsc.sar.gui.CaracteristicaGUI;
import br.ufsc.sar.gui.componentes.EntityRowTableModel;

public class CaracteristicaListner implements ActionListener {

	private static CaracteristicaController controller = null;
	
	public CaracteristicaListner(CaracteristicaGUI caracteristicaGUI){
		controller = new CaracteristicaController(caracteristicaGUI);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Novo")){
			System.out.println("Acessando Cadastro -> Caracterísitca -> Novo");
			controller.adicionarLinha();
		}

	}

}
