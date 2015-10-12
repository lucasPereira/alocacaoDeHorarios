/**
 * 
 */
package br.ufsc.sar.controller;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.event.TableModelEvent;

import br.ufsc.sar.entity.Profissional;
import br.ufsc.sar.gui.EntityGUI;
import br.ufsc.sar.gui.HorarioProfissionalGUI;
import br.ufsc.sar.gui.ProfissionalGUI;
import br.ufsc.sar.gui.componentes.ProfissionalTableModel;
import br.ufsc.sar.service.ProfissionalService;
import br.ufsc.sar.serviceimpl.ProfissionalServiceImpl;

/**
 * @author João
 *
 */
public class ProfissionalController extends EntityController<Profissional> {

	/**
	 * @param entityGUI
	 */
	public ProfissionalController(ProfissionalGUI profissionalGUI) {
		super(profissionalGUI);
	}

	@Override
	public ProfissionalService getEntityService() {
		return new ProfissionalServiceImpl();
	}

	@Override
	public boolean tratarColunaEspecial(TableModelEvent e) {
		boolean retorno = false;
		int column = e.getColumn();
		int row = e.getFirstRow();	
		ProfissionalTableModel source = (ProfissionalTableModel)e.getSource();
		if(source != null && row >= 0) {
			switch (column) {
			case 6:
				System.out.println("Abrir horários do profissional");
				//JTable target = (JTable)e.getSource();
				//JFrame newFrame = new JFrame();
		        //newFrame.setTitle("Detail Screen");
		        //newFrame.setVisible(true);
		        JPanel panelDetalhe = null;
		        String textoLabelEntity = null;
		        if(source.getRow(row) != null) {		               
			    	Profissional profissional = (Profissional) getEntityService().getEntity(((Profissional) source.getRow(row)).getId());
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
		        }
				break;		
			default:
				break;
			}
		}
		return retorno;
	}

}
