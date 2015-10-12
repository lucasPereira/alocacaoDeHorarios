/**
 * 
 */
package br.ufsc.sar.controller;

import java.util.ArrayList;
import java.util.List;

import br.ufsc.sar.entity.Caracteristica;
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
	private static final EspacoService espacoService = new EspacoServiceImpl();
	
	public static CaracteristicaService getCaracteristicaservice() {
		return caracteristicaService;
	}

	public static FormularioEspacoGUI getFormularioEspacoGUI() {
		return formularioEspacoGUI;
	}

	public FormularioEspacoController(FormularioEspacoGUI formularioEspacoGUI) {
		this.formularioEspacoGUI = formularioEspacoGUI;
	}
	
	public static EspacoService getEntityService() {
		return espacoService;
	}
	
	public boolean alterar() throws Exception{
		Espaco espaco = new Espaco();
		espaco.setId(Long.decode(this.getFormularioEspacoGUI().getId().getText().trim()));
		espaco.setNome(this.getFormularioEspacoGUI().getNome().getText());
		
		if(this.getFormularioEspacoGUI().getCapacidade().getText() != null &&
			!this.getFormularioEspacoGUI().getCapacidade().getText().equals("null") &&
			!this.getFormularioEspacoGUI().getCapacidade().getText().trim().equals("")){
			espaco.setCapacidade(Long.decode(this.getFormularioEspacoGUI().getCapacidade().getText()));
		}
		
		if(this.getFormularioEspacoGUI().getDescricao().getText() != null &&
				!this.getFormularioEspacoGUI().getDescricao().getText().equals("null") &&
				!this.getFormularioEspacoGUI().getDescricao().getText().trim().equals("")){
			espaco.setDescricao(this.getFormularioEspacoGUI().getDescricao().getText());
		}
		
		if(this.getFormularioEspacoGUI().getForauso() != null){
			espaco.setForauso(this.getFormularioEspacoGUI().getForauso().isSelected());
		}
		
		if (this.getFormularioEspacoGUI().getCaracteristicas().getSelectedItems().length > 0) {
			List<Caracteristica> listCaracteristicas = new ArrayList<Caracteristica>();
			for (String nome : this.getFormularioEspacoGUI().getCaracteristicas().getSelectedItems()){
				Caracteristica caracteristica = this.getCaracteristicaservice().getPorNome(nome);
				if(!listCaracteristicas.contains(caracteristica))
					listCaracteristicas.add(caracteristica);
			}
			espaco.setCaracteristicas(listCaracteristicas);
		} else {
			espaco.setCaracteristicas(null);
		}
		
	
		return this.getEntityService().alterar(espaco);
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public boolean excluir() throws Exception{
		Espaco espaco = new Espaco();
		espaco.setId(Long.decode(this.getFormularioEspacoGUI().getId().getText().trim()));
		if (this.getFormularioEspacoGUI().getCaracteristicas().getSelectedItems().length > 0) {
			List<Caracteristica> listCaracteristicas = new ArrayList<Caracteristica>();
			for (String nome : this.getFormularioEspacoGUI().getCaracteristicas().getSelectedItems()){
				Caracteristica caracteristica = this.getCaracteristicaservice().getPorNome(nome);
				if(!listCaracteristicas.contains(caracteristica))
					listCaracteristicas.add(caracteristica);
			}
			espaco.setCaracteristicas(listCaracteristicas);
		} else {
			espaco.setCaracteristicas(null);
		}
		boolean exclusao = this.getEntityService().excluirCaracteristicaEspaco(espaco);
		if(exclusao){
			int [] selectedIndexes = this.getFormularioEspacoGUI().getCaracteristicas().getSelectedIndexes();
			for (int i : selectedIndexes) {
				this.getFormularioEspacoGUI().getCaracteristicas().deselect(i);
			}
		}
		return exclusao;
	}
}
