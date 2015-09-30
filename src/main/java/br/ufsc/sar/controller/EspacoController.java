/**
 * 
 */
package br.ufsc.sar.controller;

import java.awt.List;

import javax.swing.event.TableModelEvent;

import br.ufsc.sar.entity.Caracteristica;
import br.ufsc.sar.entity.Espaco;
import br.ufsc.sar.gui.EspacoGUI;
import br.ufsc.sar.gui.FormularioEspacoGUI;
import br.ufsc.sar.gui.componentes.EspacoTableModel;
import br.ufsc.sar.service.CaracteristicaService;
import br.ufsc.sar.service.EspacoService;
import br.ufsc.sar.serviceimpl.CaracteristicaServiceImpl;
import br.ufsc.sar.serviceimpl.EspacoServiceImpl;

/**
 * @author ErnaniCésar
 *
 */
public class EspacoController extends EntityController<Espaco> {

	/**
	 * @param entityGUI
	 */
	private final static CaracteristicaService caracteristicaService = new CaracteristicaServiceImpl();
			
	public static CaracteristicaService getCaracteristicaService() {
		return caracteristicaService;
	}

	public EspacoController(EspacoGUI espacoGUI) {
		super(espacoGUI);
	}
	
	@Override
	public EspacoService getEntityService() {
		return new EspacoServiceImpl();
	}

	@Override
	public boolean tratarColunaEspecial(TableModelEvent e) {
		if(e.getColumn() == 5){
			this.getEntityGUI().removeAll();
			this.getEntityGUI().setVisible(false);
			FormularioEspacoGUI formulario = new FormularioEspacoGUI(this.getEntityGUI().getAplicacaoGUI());
			Long id = (Long)((EspacoTableModel)e.getSource()).getValueAt(e.getFirstRow(), 0);
			Espaco espaco = (Espaco) this.getEntityService().getEntity(id);
			formulario.getId().setText(espaco.getId().toString());
			formulario.getNome().setText(espaco.getNome());
			formulario.getDescricao().setText(espaco.getDescricao());
			formulario.getCapacidade().setText(espaco.getCapacidade().toString());
			formulario.getForauso().setSelected(espaco.isForauso());
			for(Caracteristica caracteristica : this.getCaracteristicaService().getList()){
				formulario.getCaracteristicas().add(caracteristica.getNome());
				if(espaco.getCaracteristicas().contains(caracteristica)){
					formulario.getCaracteristicas().select(formulario.getCaracteristicas().getItemCount() - 1);
				}
			}
			
			this.getEntityGUI().add(formulario);
			this.getEntityGUI().setVisible(true);
			return true;
		}
		return false;
	}

}
