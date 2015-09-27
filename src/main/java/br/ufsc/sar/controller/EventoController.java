/**
 * 
 */
package br.ufsc.sar.controller;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.event.TableModelEvent;

import br.ufsc.sar.entity.Evento;
import br.ufsc.sar.gui.EntityGUI;
import br.ufsc.sar.gui.EventoGUI;
import br.ufsc.sar.gui.EventoProfissionalGUI;
import br.ufsc.sar.gui.FormularioEventoGUI;
import br.ufsc.sar.gui.componentes.EventoTableModel;
import br.ufsc.sar.service.EventoService;
import br.ufsc.sar.serviceimpl.EventoServiceImpl;

/**
 * @author João
 *
 */
public class EventoController extends EntityController<Evento> {

	/**
	 * @param entityGUI
	 */
	public EventoController(EventoGUI eventoGUI) {
		super(eventoGUI);
	}

	@Override
	public EventoService getEntityService() {
		return new EventoServiceImpl();
	}

	@SuppressWarnings("static-access")
	@Override
	public boolean tratarColunaEspecial(TableModelEvent e) {		
		Evento evento = null;
		boolean retorno = false;
		int column = e.getColumn();
		int row = e.getFirstRow();	   
		EventoTableModel etm = (EventoTableModel)e.getSource();		
		switch (column) {
		case 2:
			if(verificarAtualizacaoPendente()){
				retorno = false;
			}
			else {
				System.out.println("Abrir detalhes do evento");
				evento = etm.getRow(row);
		    	if(evento != null) {
		    		this.getEntityGUI().removeAll();
					this.getEntityGUI().setVisible(false);
					this.getEntityGUI().add(new FormularioEventoGUI(this.getEntityGUI().getAplicacaoGUI(), evento));
					this.getEntityGUI().setVisible(true);
		     	}
				retorno = true;
			}
			break;
		case 3:
			// FIXME: Remover
			System.out.println("Abrir profissionais do evento");
			//JTable target = (JTable)e.getSource();
			 //JFrame newFrame = new JFrame();
	         //newFrame.setTitle("Detail Screen");
	         //newFrame.setVisible(true);
	         JPanel panelDetalhe = null;
	         String textoLabelEntity = null;
	               
	         evento = etm.getRow(row);
	    	 if(evento != null) {	    		 
				panelDetalhe = new EventoProfissionalGUI(EntityGUI.getAplicacaoGUI(), evento);				
     			textoLabelEntity = EventoProfissionalGUI.GUI_LABEL;
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
			System.out.println("Abrir horários do evento");
			retorno = true;
			break;	
		case 5:
			System.out.println("Abrir espaços do evento");
			retorno = true;
			break;
		default:
			break;
		}
		return retorno;
	}

}
