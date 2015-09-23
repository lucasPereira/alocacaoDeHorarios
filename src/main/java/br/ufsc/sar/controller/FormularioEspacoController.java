/**
 * 
 */
package br.ufsc.sar.controller;

import br.ufsc.sar.gui.FormularioEspacoGUI;
import br.ufsc.sar.service.EspacoService;
import br.ufsc.sar.serviceimpl.EspacoServiceImpl;

/**
 * @author ErnaniCésar
 *
 */
public class FormularioEspacoController {

	/**
	 * @param entityGUI
	 */
	public FormularioEspacoController(FormularioEspacoGUI formularioEspacoGUI) {
		
	}
	
	public EspacoService getEntityService() {
		return new EspacoServiceImpl();
	}
}
