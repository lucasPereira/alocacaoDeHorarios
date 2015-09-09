package br.ufsc.sar.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

import br.ufsc.entity.BaseEntity;
import br.ufsc.sar.gui.EntityGUI;
import br.ufsc.service.BaseService;

public abstract class EntityController<T extends BaseEntity> {

	private EntityGUI<T> entityGUI = null;
	
	private Set<Integer> linhasAtualizadas = new HashSet<Integer>();
	
	public EntityController(EntityGUI<T> entityGUI) {
		this.entityGUI = entityGUI;
	}
	
	public EntityGUI<T> getEntityGUI() {
		return entityGUI;
	}

	public void adicionarLinha() {
		this.getEntityGUI().getModeloTabelaEntity().addEmptyRow();	
	}
	
	public void marcarLinhaAtualizada(int row){
		linhasAtualizadas.add(row);
	}

	public abstract BaseService<T> getEntityService();
	
	@SuppressWarnings("unchecked")
	public void salvar() {
		System.out.println(linhasAtualizadas.size());
		if(linhasAtualizadas != null && !linhasAtualizadas.isEmpty()){
			for (Integer row : linhasAtualizadas) {
				System.out.println("Tentando persistir entidade da linha " + row + "...");				
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
		    			}
		    		}
		    		else {
		    			sucesso = sucesso && service.alterar(entity);
		    		}
				} catch (Exception e) {
					sucesso = sucesso && false;
				}
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
			System.out.println("Excluir linha " + rows);
			System.out.println("Retorno: " + retorno);
			if(retorno == 0) {			
				BaseService<T> service = getEntityService();
				T storedEntity = null;
				boolean sucesso = true;
				try {
					System.out.println("Service: " + service);
					for (int i = 0; i < rows.length; i++) {
						storedEntity = this.getEntityGUI().getModeloTabelaEntity().getRow(i);
						if(storedEntity != null) {
							System.out.println(storedEntity.getIdValue());
							sucesso = sucesso && service.excluir(storedEntity.getIdValue().longValue());
							
						}
					}
					
					if(sucesso){
						this.getEntityGUI().getModeloTabelaEntity().removeRows(rows);
						System.out.println(rows.length + " entidades removidas com sucesso");  
						
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