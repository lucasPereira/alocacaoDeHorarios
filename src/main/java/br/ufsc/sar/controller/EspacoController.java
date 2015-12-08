/**
 * 
 */
package br.ufsc.sar.controller;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.event.TableModelEvent;

import br.ufsc.sar.entity.Caracteristica;
import br.ufsc.sar.entity.Espaco;
import br.ufsc.sar.entity.Evento;
import br.ufsc.sar.gui.EntityGUI;
import br.ufsc.sar.gui.EspacoGUI;
import br.ufsc.sar.gui.FormularioEspacoGUI;
import br.ufsc.sar.gui.HorarioEspacoGUI;
import br.ufsc.sar.gui.componentes.EspacoTableModel;
import br.ufsc.sar.service.CaracteristicaService;
import br.ufsc.sar.service.EspacoService;
import br.ufsc.sar.service.EventoEspacoService;
import br.ufsc.sar.serviceimpl.CaracteristicaServiceImpl;
import br.ufsc.sar.serviceimpl.EspacoServiceImpl;
import br.ufsc.sar.serviceimpl.EventoEspacoServiceImpl;

/**
 * @author ErnaniCésar
 *
 */
public class EspacoController extends EntityController<Espaco> {

	/**
	 * @param entityGUI
	 */
	private final static CaracteristicaService caracteristicaService = new CaracteristicaServiceImpl();
	private final static EventoEspacoService eventoEspacoService = new EventoEspacoServiceImpl();

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
		if (e.getColumn() == 5) {
			this.getEntityGUI().removeAll();
			this.getEntityGUI().setVisible(false);

			FormularioEspacoGUI formulario = new FormularioEspacoGUI(EntityGUI.getAplicacaoGUI());
			Long id = (Long) ((EspacoTableModel) e.getSource()).getValueAt(e.getFirstRow(), 0);
			Espaco espaco = (Espaco) this.getEntityService().getEntity(id);
			formulario.getId().setText(espaco.getId().toString());

			formulario.getNome().setText(espaco.getNome());

			if (formulario.getDescricao() != null) {
				formulario.getDescricao().setText(espaco.getDescricao());
			}

			if (formulario.getCapacidade() != null && espaco.getCapacidade() != null
					&& espaco.getCapacidade().toString() != null
					&& !espaco.getCapacidade().toString().equals("null")
					&& !espaco.getCapacidade().toString().trim().equals("")) {
				formulario.getCapacidade().setText(espaco.getCapacidade().toString());
			}

			if (formulario.getForauso() != null) {
				formulario.getForauso().setSelected(espaco.isForauso());
			}

			int index = 0;

			for (Caracteristica caracteristica : EspacoController.getCaracteristicaService().getList()) {
				formulario.getCaracteristicas().add(caracteristica.getNome());
				if (espaco.getCaracteristicas().contains(caracteristica)) {
					formulario.getCaracteristicas().select(index);
				}
				index++;

			}

			for (Evento evento : eventoEspacoService.getAgendaEventosPorEspaco(espaco)) {
				formulario.getAgendaEventoTableModel().addRow(evento);
			}

			this.getEntityGUI().add(formulario);
			this.getEntityGUI().setVisible(true);
			return true;
		}
		else {
			if (e.getColumn() == 6) {
				// //System.out.println("Abrir horários do espaço");
				// JTable target = (JTable)e.getSource();
				// JFrame newFrame = new JFrame();
				// newFrame.setTitle("Detail Screen");
				// newFrame.setVisible(true);
				JPanel panelDetalhe = null;
				String textoLabelEntity = null;
				int row = e.getFirstRow();
				EspacoTableModel source = (EspacoTableModel) e.getSource();
				if (source.getRow(row) != null) {
					Espaco espaco = (Espaco) getEntityService().getEntity(((Espaco) source.getRow(row)).getId());
					if (espaco != null) {
						panelDetalhe = new HorarioEspacoGUI(EntityGUI.getAplicacaoGUI(), espaco);
						textoLabelEntity = HorarioEspacoGUI.GUI_LABEL;
					}

					if (panelDetalhe != null) {
						final JDialog frame = new JDialog(EntityGUI.getAplicacaoGUI(), textoLabelEntity, true);
						panelDetalhe.setOpaque(true);
						frame.getContentPane().add(panelDetalhe);
						frame.setSize(800, 600);
						// frame.pack();
						frame.setVisible(true);
					}
					return true;
				}
			}
		}
		return false;
	}

}
