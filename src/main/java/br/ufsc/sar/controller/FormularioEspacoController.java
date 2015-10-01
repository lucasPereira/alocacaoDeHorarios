/**
 * 
 */
package br.ufsc.sar.controller;

import br.ufsc.sar.entity.Espaco;
import br.ufsc.sar.gui.FormularioEspacoGUI;
import br.ufsc.sar.service.CaracteristicaService;
import br.ufsc.sar.service.EspacoService;
import br.ufsc.sar.serviceimpl.CaracteristicaServiceImpl;
import br.ufsc.sar.serviceimpl.EspacoServiceImpl;

/**
 * @author ErnaniCésar
 *
 */
public class FormularioEspacoController {

	/**
	 * @param entityGUI
	 */
	private static FormularioEspacoGUI formularioEspacoGUI = null;
	private static final CaracteristicaService caracteristicaService = new CaracteristicaServiceImpl();
	
	public static CaracteristicaService getCaracteristicaservice() {
		return caracteristicaService;
	}

	public static FormularioEspacoGUI getFormularioEspacoGUI() {
		return formularioEspacoGUI;
	}

	public FormularioEspacoController(FormularioEspacoGUI formularioEspacoGUI) {
		this.formularioEspacoGUI = formularioEspacoGUI;
	}
	
	public EspacoService getEntityService() {
		return new EspacoServiceImpl();
	}
	
	public boolean alterar() throws Exception{
		Espaco espaco = new Espaco();
		espaco.setId(Long.getLong(this.getFormularioEspacoGUI().getId().getText()));
		espaco.setNome(this.getFormularioEspacoGUI().getNome().getText());
		espaco.setCapacidade(Long.getLong(this.getFormularioEspacoGUI().getCapacidade().getText()));
		espaco.setDescricao(this.getFormularioEspacoGUI().getDescricao().getText());
		espaco.setForauso(this.getFormularioEspacoGUI().getForauso().isSelected());
		if (this.getFormularioEspacoGUI().getCaracteristicas().getSelectedItems().length > 0) {
			espaco.setCaracteristicas(this.getCaracteristicaservice().getListPorListNome(this.getFormularioEspacoGUI().getCaracteristicas().getSelectedItems()));
		} else {
			espaco.setCaracteristicas(null);
		}
		
		
		return this.getEntityService().alterar(espaco);
	}
}
