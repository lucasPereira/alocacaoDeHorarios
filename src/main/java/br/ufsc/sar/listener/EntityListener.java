package br.ufsc.sar.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import br.ufsc.entity.BaseEntity;
import br.ufsc.sar.controller.EntityController;
import br.ufsc.sar.gui.EntityGUI;

public abstract class EntityListener<T extends BaseEntity> implements ActionListener {

	private EntityGUI<T> entityGUI;
	
	private EntityController<T> controller = null;
	
	protected EntityListener(EntityGUI<T> entityGUI){
		this.entityGUI = entityGUI;
		controller = this.entityGUI.getEntityController();
		
	}
		
	public abstract String getEntityName();
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Novo")){
			//System.out.println("Acessando Cadastro -> " + getEntityName() + " -> Novo");
			controller.adicionarLinha();
		}
		else{
			if(e.getActionCommand().equals("Excluir")){
				//System.out.println("Excluindo Cadastro -> " + getEntityName());
				controller.excluirLinha(this.entityGUI.getTabelaEntity().getSelectedRows());
			}
			else{
				if(e.getActionCommand().equals("Salvar")){
					//System.out.println("Salvando Cadastro -> " + getEntityName());
					controller.salvar();
				} else {
					if (e.getActionCommand().contains("Editar@")) {
						controller.editarRegistroDependente(e.getActionCommand());								
					}
				}
					
				}
			}
		}
	
	public abstract EntityTableListener<T> getEntityTableListener();	 
	
	@SuppressWarnings("hiding")
	protected class EntityTableListener<T> implements TableModelListener {

		public void tableChanged(TableModelEvent e) {
			//Object source = e.getSource();
			int column = e.getColumn();
			int row = e.getFirstRow();
			//System.out.println("row: " + row + " column: " + column);	
			if(column >= 0) {
				if (controller.tratarColunaEspecial(e) == true){
//					//System.out.println(controller.getClass().getCanonicalName());
					//System.out.println("CellProperty: Célula especial");
				}
				else {
					//System.out.println("Não é célula especial (célula 'botão') ");
					controller.marcarLinhaAtualizada(row);				
				}	
			}
		}
	}
}