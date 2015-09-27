/**
 * 
 */
package br.ufsc.sar.controller;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.event.TableModelEvent;

import br.ufsc.sar.entity.EventoProfissional;
import br.ufsc.sar.entity.Profissional;
import br.ufsc.sar.gui.EntityGUI;
import br.ufsc.sar.gui.EventoProfissionalGUI;
import br.ufsc.sar.gui.HorarioProfissionalGUI;
import br.ufsc.sar.gui.componentes.EventoProfissionalTableModel;
import br.ufsc.sar.service.EventoProfissionalService;
import br.ufsc.sar.serviceimpl.EventoProfissionalServiceImpl;

/**
 * @author João
 *
 */
public class EventoProfissionalController extends EntityController<EventoProfissional> {

	/**
	 * @param entityGUI
	 */
	public EventoProfissionalController(EventoProfissionalGUI eventoGUI) {
		super(eventoGUI);
	}

	@Override
	public EventoProfissionalService getEntityService() {
		return new EventoProfissionalServiceImpl();
	}

	@SuppressWarnings("unused")
	@Override
	public boolean tratarColunaEspecial(TableModelEvent e) {
		boolean retorno = false;
		int column = e.getColumn();
		int row = e.getFirstRow();	   
		EventoProfissionalTableModel etm = (EventoProfissionalTableModel)e.getSource();
		switch (column) {
		case 2:
			System.out.println("Abrir detalhes de características");
			retorno = true;
			break;
		case 3:
			System.out.println("Abrir detalhes de profissionais");
			//JTable target = (JTable)e.getSource();
			//JFrame newFrame = new JFrame();
	        //newFrame.setTitle("Detail Screen");
	        //newFrame.setVisible(true);
	        JPanel panelDetalhe = null;
	        String textoLabelEntity = null;
	               
	    	Profissional profissional = new Profissional();//(Profissional) buscarEntity(1);
			if(profissional != null) {
				panelDetalhe = new HorarioProfissionalGUI(EntityGUI.getAplicacaoGUI(), profissional);					
				textoLabelEntity = HorarioProfissionalGUI.GUI_LABEL;
			}			
	         
	        if(panelDetalhe != null) {
		        final JDialog frame = new JDialog(EntityGUI.getAplicacaoGUI(), textoLabelEntity, true);
		        panelDetalhe.setOpaque(true);
		        frame.getContentPane().add(panelDetalhe);
		        frame.setSize(800, 600);
		        //frame.pack();
		        frame.setVisible(true);
	        }
			retorno = true;
			break;
		case 4:
			System.out.println("Abrir detalhes de horarios");
			retorno = true;
			break;	
		case 5:
			System.out.println("Abrir detalhes de espaco");
			retorno = true;
			break;
		default:
			break;
		}
		return retorno;
	}

}
