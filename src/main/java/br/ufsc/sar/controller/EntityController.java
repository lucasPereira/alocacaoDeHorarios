package br.ufsc.sar.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;

import br.ufsc.entity.BaseEntity;
import br.ufsc.sar.gui.EntityGUI;
import br.ufsc.service.BaseService;
import br.ufsc.util.type.EntidadeDetalheInfo;

/**
 * 
 * @author João
 *
 * @param <T>
 */
public abstract class EntityController<T extends BaseEntity> {

	/**
	 * 
	 */
	private EntityGUI<T> entityGUI = null;
	
	/**
	 * 
	 */
	private Set<Integer> linhasAtualizadas = new HashSet<Integer>();
		
	/**
	 * 
	 * @param entityGUI
	 */
	public EntityController(EntityGUI<T> entityGUI) {
		this.entityGUI = entityGUI;
	}
	
	/**
	 * 
	 * @return
	 */
	public EntityGUI<T> getEntityGUI() {
		return entityGUI;
	}

	/**
	 * 
	 */
	public void adicionarLinha() {
		this.getEntityGUI().getModeloTabelaEntity().addEmptyRow();	
	}
	
	/**
	 * 
	 * @param row
	 */
	public void marcarLinhaAtualizada(int row){
		linhasAtualizadas.add(row);
	}

	/**
	 * 
	 * @return
	 */
	public abstract BaseService<T> getEntityService();
	
	/**
	 * 
	 */
	public void salvar() {
//		//System.out.println(linhasAtualizadas.size());
		if(linhasAtualizadas != null && !linhasAtualizadas.isEmpty()){
			List<Integer> indexLinhaAtualizadaSucesso = new ArrayList<Integer>();
			for (Integer row : linhasAtualizadas) {
//				//System.out.println("Tentando persistir entidade da linha " + row + "...");				
				T entity = this.getEntityGUI().getModeloTabelaEntity().getRow(row);				
				BaseService<T> service = getEntityService();	
				boolean sucesso = true;
				T storedEntity = null;
				Long id = null;
				try {
					if(entity.getIdValue() != null)
						storedEntity = (T)service.getEntity(entity.getIdValue().longValue());
		    		if(storedEntity == null){
		    			id = service.incluir(entity);
		    			if(id != null) {
		    				sucesso = sucesso && true;
		    				storedEntity = (T)service.getEntity(id);
		    				this.getEntityGUI().getModeloTabelaEntity().replaceRow(row, storedEntity);
		    				indexLinhaAtualizadaSucesso.add(row);
		    			}
		    		}
		    		else {
		    			if(service.alterar(entity)){
		    				indexLinhaAtualizadaSucesso.add(row);
		    			}
		    		}
				} catch (Exception e) {
					//
//					//System.out.println("Erro: " + e.getMessage());
					JOptionPane.showMessageDialog(this.entityGUI, 
							"Erro ao tentar excluir a linha " + row + ":\n" + (e.getLocalizedMessage() == null ? "Obrigatório informar o nome" : e.getLocalizedMessage()),
							"Erro de exclusão", JOptionPane.ERROR_MESSAGE);
				}
			}
			
			if(!indexLinhaAtualizadaSucesso.isEmpty()){
				for (Integer integer : indexLinhaAtualizadaSucesso) {
					linhasAtualizadas.remove(integer);
				}				
			}
		}		
	}

	/**
	 * 
	 */
	public void buscarTodos() {
		BaseService<T> service = getEntityService();
		List<T> storedEntities = null;
		try {
//			//System.out.println("Service: " + service);
			storedEntities = (List<T>)service.getList();
    		if(storedEntities != null && !storedEntities.isEmpty()){
    			this.getEntityGUI().getModeloTabelaEntity().addRows(storedEntities);
//    			//System.out.println(storedEntities.size() + " entidades encontradas");  
    		}
		} catch (Exception e) {
//			//System.out.println("Erro: " + e.getMessage());
		}
		
		if(storedEntities == null || storedEntities.isEmpty()){   
			adicionarLinha();
//			//System.out.println("Não há entidades armazenadas"); 
    	}   	  		
	}		
	
	/**
	 * 
	 */
	public void buscarTodos(EntidadeDetalheInfo<? extends BaseEntity, T> edinfo) {
		if(edinfo != null) {
			BaseService<T> service = getEntityService();
			List<T> storedEntities = null;
			try {
//				//System.out.println("Service: " + service);
				String filtroConsulta = edinfo.getColunaDetalhe() + " = " + edinfo.getEntidadeRaiz().getIdValue().longValue();
				storedEntities = (List<T>)service.getList(filtroConsulta);
	    		if(storedEntities != null && !storedEntities.isEmpty()){
	    			this.getEntityGUI().getModeloTabelaEntity().addRows(storedEntities);
//	    			//System.out.println(storedEntities.size() + " entidades encontradas");  
	    		}
			} catch (Exception e) {
//				//System.out.println("Erro: " + e.getMessage());
			}
			
			if(storedEntities == null || storedEntities.isEmpty()){   
				adicionarLinha();
//				//System.out.println("Não há entidades armazenadas"); 
	    	}   
		}
	}		
	
	/**
	 * 
	 * @param id
	 */
	public void buscar(Long id) {
		BaseService<T> service = getEntityService();
		T storedEntity = null;
		try {
//			//System.out.println("Service: " + service);
			storedEntity = (T)service.getEntity(id);
    		if(storedEntity != null){
    			this.getEntityGUI().getModeloTabelaEntity().addRow(storedEntity);
//    			//System.out.println("Entidade " + storedEntity + " encontrada");  
    		}
		} catch (Exception e) {
//			//System.out.println("Erro: " + e.getMessage());
		}  		
		
		if(storedEntity == null){
//			//System.out.println("Entidade não encontrada"); 
		}
	}		
	
	/**
	 * 
	 * @param id
	 * @param row
	 */
	public void buscar(Long id, Integer row) {
		BaseService<T> service = getEntityService();
		T storedEntity = null;
		try {
//			//System.out.println("Service: " + service);
			storedEntity = (T)service.getEntity(id);
    		if(storedEntity != null){
    			this.getEntityGUI().getModeloTabelaEntity().replaceRow(row, storedEntity);
//    			//System.out.println("Entidade " + storedEntity + " encontrada");  
    		}
		} catch (Exception e) {
//			//System.out.println("Erro: " + e.getMessage());
		}  		
		
		if(storedEntity == null){
//			//System.out.println("Entidade não encontrada"); 
		}
	}
	
	/**
	 * 
	 * @param id
	 */
	public T buscarEntity(int row) {
		T entity = this.getEntityGUI().getModeloTabelaEntity().getRow(row);	
		if(entity != null){
			BaseService<T> service = getEntityService();
			T storedEntity = null;
			try {
//				//System.out.println("Service: " + service);
				storedEntity = (T)service.getEntity(entity.getIdValue().longValue());
	    		if(storedEntity != null){
//	    			//System.out.println("Entidade " + storedEntity + " encontrada");  
	    			return storedEntity;
	    		}
			} catch (Exception e) {
//				//System.out.println("Erro: " + e.getMessage());
			}  		
			
			if(storedEntity == null){
//				//System.out.println("Entidade não encontrada"); 
			}
		}
		
		return null;
	}	

	/**
	 * 
	 * @param rows
	 */
	public void excluirLinha(int... rows) {
		if(!verificarAtualizacaoPendente()){			
			if(rows.length > 0) {
				int retorno = JOptionPane.showConfirmDialog(this.entityGUI, "Você tem certeza que deseja excluir as linhas selecionadas?", "Confirmação de exclusão",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//				//System.out.println("Excluir linha " + rows);
//				//System.out.println("Retorno: " + retorno);
				if(retorno == 0) {			
					BaseService<T> service = getEntityService();
					T storedEntity = null;
					boolean sucesso = true;
					try {
//						//System.out.println("Service: " + service);
						// Garantir a ordem inversa
						Arrays.sort(rows);
						for (int i = rows.length - 1; i >= 0; i--) {
							int row = rows[i];
							storedEntity = this.getEntityGUI().getModeloTabelaEntity().getRow(row);
							if(storedEntity != null) {
//								//System.out.println(storedEntity.getIdValue());
								sucesso = service.excluir(storedEntity.getIdValue().longValue());
								if(sucesso) {
									this.getEntityGUI().getModeloTabelaEntity().removeRowRange(row,row);
//									//System.out.println("Entidade " + storedEntity.getIdValue() + " removida com sucesso");
								}
							}
						}				    		
					} catch (Exception e) {
//						//System.out.println("Erro: " + e.getMessage());
					}  		
					
					if(storedEntity == null || !sucesso){
//						//System.out.println("Entidades não removidas"); 
					}		
				}
			}
		}
	}

	public void editarRegistroDependente(String actionCommand) {
//		//System.out.println("editarRegistroDependente");
		
	}

	public abstract boolean tratarColunaEspecial(TableModelEvent e);
	
	/**
	 * 
	 * @return
	 */
	public boolean verificarAtualizacaoPendente(){
		if(!linhasAtualizadas.isEmpty()) {
			JOptionPane.showMessageDialog(this.entityGUI, "Para realizar a ação, é necessário efetivar o salvamento das alterações pendentes", "Impedimento de ação", JOptionPane.WARNING_MESSAGE);
			return true;
		}
		
		return false;
	}

	
}