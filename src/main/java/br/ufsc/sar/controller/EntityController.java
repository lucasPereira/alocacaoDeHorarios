package br.ufsc.sar.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

import br.ufsc.entity.BaseEntity;
import br.ufsc.sar.gui.EntityGUI;
import br.ufsc.service.BaseService;

public abstract class EntityController<T extends BaseEntity> {

	//private static EntityGUI entityGUI = null;
	private EntityGUI<T> entityGUI = null;
	
	private Set<Integer> linhasAtualizadas = new HashSet<Integer>();
	
	public EntityController(EntityGUI<T> entityGUI) {
		//EntityController.entityGUI = entityGUI;
		this.entityGUI = entityGUI;
	}
	
//	public static EntityGUI getEntityGUI() {
//		return entityGUI;
//	}
	
	public EntityGUI<T> getEntityGUI() {
		return entityGUI;
	}

	public void adicionarLinha() {
		this.getEntityGUI().getModeloTabelaEntity().addEmptyRow();	
	}
	
	public void marcarLinhaAtualizada(int row){
		// FIXME: Alterar para estratégia sugerida pelo Ernani, utilizando uma nova coluna na tabela para indicar se a linha foi atualizada
				System.out.println("Linha " + row + " atualizada.");
		T entity = this.getEntityGUI().getModeloTabelaEntity().getRow(row);
		if(entity != null){
			//entity.
		}
		linhasAtualizadas.add(row);
	}

	public abstract BaseService<T> getEntityService();
	
	@SuppressWarnings("unchecked")
	public void salvar() {
		// FIXME: Alterar para estratégia sugerida pelo Ernani, utilizando uma nova coluna na tabela para indicar se a linha foi atualizada
		if(linhasAtualizadas != null && !linhasAtualizadas.isEmpty()){
			for (Integer row : linhasAtualizadas) {
				System.out.println("Tentando persistir entidade da linha " + row + "...");				
				T entity = this.getEntityGUI().getModeloTabelaEntity().getRow(row);				
				BaseService<T> service = getEntityService();	
				boolean sucesso = false;
				T storedEntity = null;
				Long id = null;
				try {
					System.out.println("Service: " + service);
		    		storedEntity = (T)service.getEntity(entity.getIdValue().longValue());
		    		if(storedEntity == null){
		    			id = service.incluir(entity);
		    			if(id != null) {
		    				storedEntity = (T)service.getEntity(id);
		    				this.getEntityGUI().getModeloTabelaEntity().replaceRow(row, storedEntity);
		    			}
		    		}
		    		else {
		    			service.alterar(entity);
		    		}
				} catch (Exception e) {
					System.out.println("Erro: " + e.getMessage());
				}
				
				System.out.println("Entidade " + entity.toString() + "salva? " + sucesso);
			}
		}
		
	}

	@SuppressWarnings("unchecked")
	public void buscarTodos() {
		BaseService<T> service = getEntityService();
		List<T> storedEntities = null;
		try {
			System.out.println("Service: " + service);
			storedEntities = (List<T>)service.getList();
    		if(storedEntities != null){
    			this.getEntityGUI().getModeloTabelaEntity().addRows(storedEntities);
    			System.out.println(storedEntities.size() + " entidades encontradas");  
    		}
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		if(storedEntities == null){   
			adicionarLinha();
			System.out.println("Não há entidades armazenadas"); 
    	}   	  		
	}		
	
	@SuppressWarnings("unchecked")
	public void buscar(Long id) {
		BaseService<T> service = getEntityService();
		T storedEntity = null;
		try {
			System.out.println("Service: " + service);
			storedEntity = (T)service.getEntity(id);
    		if(storedEntity != null){
    			this.getEntityGUI().getModeloTabelaEntity().addRow(storedEntity);
    			System.out.println("Entidade " + storedEntity + " encontrada");  
    		}
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}  		
		
		if(storedEntity == null){
			System.out.println("Entidade não encontrada"); 
		}
	}		
	
	@SuppressWarnings("unchecked")
	public void buscar(Long id, Integer row) {
		BaseService<T> service = getEntityService();
		T storedEntity = null;
		try {
			System.out.println("Service: " + service);
			storedEntity = (T)service.getEntity(id);
    		if(storedEntity != null){
    			this.getEntityGUI().getModeloTabelaEntity().replaceRow(row, storedEntity);
    			System.out.println("Entidade " + storedEntity + " encontrada");  
    		}
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}  		
		
		if(storedEntity == null){
			System.out.println("Entidade não encontrada"); 
		}
	}

	/**
	 * 
	 * @param rows
	 */
	public void excluirLinha(int... rows) {
		if(rows.length > 0) {
			int retorno = JOptionPane.showConfirmDialog(this.entityGUI, "Você tem certeza que deseja excluir as linhas selecionadas?", "Confirmação de exclusão",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if(retorno == 0) {			
				BaseService<T> service = getEntityService();
				T storedEntity = null;
				boolean sucesso = false;
				try {
					System.out.println("Service: " + service);
					for (int i = 0; i < rows.length; i++) {
						storedEntity = this.getEntityGUI().getModeloTabelaEntity().getRow(i);
						if(storedEntity != null) {
							sucesso = sucesso && service.excluir(storedEntity.getIdValue().longValue());
						}
					}
					
					if(sucesso){
						this.getEntityGUI().getModeloTabelaEntity().removeRows(rows);
						System.out.println(rows.length + " entidades removidas com sucesso");  
						
						// FIXME: Alterar para estratégia sugerida pelo Ernani, utilizando uma nova coluna na tabela para indicar se a linha foi atualizada
						Set<Integer> linhasAtualizadasAux = new HashSet<Integer>();
						for (int i : rows) {
							linhasAtualizadas.remove(i);
							for (Integer linha : linhasAtualizadas) {
								if(linha > i) {
									linhasAtualizadasAux.add(linha - 1);
								}
								else {
									linhasAtualizadasAux.add(linha);
								}
							}
							
							linhasAtualizadas.clear();
							linhasAtualizadas.addAll(linhasAtualizadasAux);
						}
					}			   			
		    		
				} catch (Exception e) {
					System.out.println("Erro: " + e.getMessage());
				}  		
				
				if(storedEntity == null || !sucesso){
					System.out.println("Entidades não removidas"); 
				}		
			}
		}
	}		
}