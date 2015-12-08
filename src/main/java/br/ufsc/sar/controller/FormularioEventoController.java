/**
 * 
 */
package br.ufsc.sar.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

import br.ufsc.entity.BaseEntity;
import br.ufsc.sar.entity.Caracteristica;
import br.ufsc.sar.entity.Espaco;
import br.ufsc.sar.entity.Evento;
import br.ufsc.sar.entity.EventoCaracteristica;
import br.ufsc.sar.entity.EventoEspaco;
import br.ufsc.sar.entity.EventoProfissional;
import br.ufsc.sar.entity.HorarioEvento;
import br.ufsc.sar.entity.Profissional;
import br.ufsc.sar.gui.FormularioEventoGUI;
import br.ufsc.sar.gui.componentes.EntityRowTableModel;
import br.ufsc.sar.service.CaracteristicaService;
import br.ufsc.sar.service.EspacoService;
import br.ufsc.sar.service.EventoCaracteristicaService;
import br.ufsc.sar.service.EventoEspacoService;
import br.ufsc.sar.service.EventoProfissionalService;
import br.ufsc.sar.service.EventoService;
import br.ufsc.sar.service.ProfissionalService;
import br.ufsc.sar.serviceimpl.CaracteristicaServiceImpl;
import br.ufsc.sar.serviceimpl.EspacoServiceImpl;
import br.ufsc.sar.serviceimpl.EventoCaracteristicaServiceImpl;
import br.ufsc.sar.serviceimpl.EventoEspacoServiceImpl;
import br.ufsc.sar.serviceimpl.EventoProfissionalServiceImpl;
import br.ufsc.sar.serviceimpl.EventoServiceImpl;
import br.ufsc.sar.serviceimpl.ProfissionalServiceImpl;
import br.ufsc.service.BaseService;
import br.ufsc.util.type.StatusVerificacao;

/**
 * @author ErnaniCésar
 *
 */
public class FormularioEventoController {
	
	/**
	 * 
	 */
	private FormularioEventoGUI formularioEventoGUI;
	
	/**
	 * 
	 */
	private Evento evento;
			
	/**
	 * 
	 */
	private EventoService eventoService;
	
	/**
	 * 
	 */
	private ProfissionalService profissionalService;
	
	/**
	 * 
	 */
	private EventoProfissionalService eventoProfissionalService;
	
	/**
	 * 
	 */
	private CaracteristicaService caracteristicaService;
	
	/**
	 * 
	 */
	private EventoCaracteristicaService eventoCaracteristicaService;
	
	/**
	 * 
	 */
	private Set<Integer> linhasEventoCaracteristicaAtualizadas = new HashSet<Integer>();
	
	/**
	 * 
	 */
	private EspacoService espacoService;
	
	/**
	 * 
	 */
	private EventoEspacoService eventoEspacoService;
	
	/**
	 * @param entityGUI
	 */
	public FormularioEventoController(FormularioEventoGUI formularioEventoGUI, Evento evento) {
		this.formularioEventoGUI = formularioEventoGUI;		
		this.evento = evento;
	}
	
	/**
	 * 
	 * @return
	 */
	public FormularioEventoGUI getFormularioEventoGUI(){
		return this.formularioEventoGUI;
	}
		
	/**
	 * 
	 * @param rows
	 * @param entidade
	 */
	public void excluirLinha(BaseService<? extends BaseEntity> service, EntityRowTableModel<? extends BaseEntity> tabelaModelo, int... rows) {				
		if(rows.length > 0) {
			int retorno = JOptionPane.showConfirmDialog(this.formularioEventoGUI, "Você tem certeza que deseja excluir as linhas selecionadas?", "Confirmação de exclusão",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//			//System.out.println("Excluir linha " + rows);
//			//System.out.println("Retorno: " + retorno);
			if(retorno == 0) {			
				BaseEntity storedEntity = null;
				boolean sucesso = true;
				try {
//					//System.out.println("Service: " + service);
					// Garantir a ordem inversa
					Arrays.sort(rows);
					for (int i = rows.length - 1; i >= 0; i--) {
						int row = rows[i];
						storedEntity = tabelaModelo.getRow(row);
						if(storedEntity != null) {
//							//System.out.println(storedEntity.getIdValue());
							sucesso = service.excluir(storedEntity.getIdValue().longValue());
							if(sucesso) {
								tabelaModelo.removeRowRange(row,row);
//								//System.out.println("Entidade " + storedEntity.getIdValue() + " removida com sucesso");
							}
						}
					}				    		
				} catch (Exception e) {
					//System.out.println("Erro: " + e.getMessage());
				}  		
				
				if(storedEntity == null || !sucesso){
					//System.out.println("Entidades não removidas"); 
				}		
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	public boolean verificarAtualizacaoPendente(){
		if(!linhasEventoCaracteristicaAtualizadas.isEmpty()) {
			JOptionPane.showMessageDialog(this.formularioEventoGUI, "Para realizar a ação, é necessário efetivar o salvamento das alterações pendentes", "Impedimento de ação", JOptionPane.WARNING_MESSAGE);
			return true;
		}
		
		return false;
	}
	
	
	// Evento
	
	/**
	 * 
	 * @return
	 */
	public Evento getEvento(){
		return this.evento;
	}
	
	/**
	 * 
	 * @return
	 */
	public EventoService getEventoService() {
		if(this.eventoService == null) {
			this.eventoService =  new EventoServiceImpl();
		}		
		return this.eventoService;
	}
	
	/**
	 * 
	 */
	public boolean salvarEvento() {
		BaseService<Evento> service = getEventoService();	
		boolean sucesso = false;
		try {
			this.evento.setNome(this.getFormularioEventoGUI().getCampoNome().getText());
			this.evento.setDescricao(this.formularioEventoGUI.getCampoDescricao().getText());
			if(service.alterar(evento)){
    			JOptionPane.showMessageDialog(this.getFormularioEventoGUI(), "Dados salvos com sucesso.",
    					"Informação", JOptionPane.INFORMATION_MESSAGE);
    			sucesso = true;
    		}
    		else {
    			JOptionPane.showMessageDialog(this.getFormularioEventoGUI(), "Dados não foram salvos.",
    					"Erro", JOptionPane.ERROR_MESSAGE);
    		}
		} catch (Exception e) {
			//
//			//System.out.println("Erro: " + e.getMessage());
			JOptionPane.showMessageDialog(this.getFormularioEventoGUI(), 
					"Erro ao tentar salvar:\n" + e.getLocalizedMessage(),
					"Erro de salvamento", JOptionPane.ERROR_MESSAGE);
		}
		
		return sucesso;
	}
	
	
	// Profissionais
	
	/**
	 * 
	 * @return
	 */
	public ProfissionalService getProfissionalService() {
		if(this.profissionalService == null) {
			this.profissionalService =  new ProfissionalServiceImpl();
		}		
		return this.profissionalService;
	}
	
	public EventoProfissionalService getEventoProfissionalService() {
		if(this.eventoProfissionalService == null) {
			this.eventoProfissionalService =  new EventoProfissionalServiceImpl();
		}		
		return this.eventoProfissionalService;
	}
	
	/**
	 * 
	 */
	public void buscarProfissionaisDoEvento() {		
		List<EventoProfissional> storedEntities = null;
		//List<String> nomeProfissionais = new ArrayList<String>();
		try {
			String filtroConsulta = "evento = " + this.evento.getId().toString();
			storedEntities = (List<EventoProfissional>)getEventoProfissionalService().getList(filtroConsulta);
    		if(storedEntities != null && !storedEntities.isEmpty()){
    			this.getFormularioEventoGUI().getModeloTabelaProfissionaisEvento().addRows(storedEntities);
//    			for (BaseEntity baseEntity : storedEntities) {
//    				if(baseEntity instanceof Profissional) {
//    					Profissional p = (Profissional)baseEntity;
//    					nomeProfissionais.add(p.getNome());
//    				}
//    			}
//    			this.getFormularioEventoGUI().setProfissionaisEvento(nomeProfissionais);
//    			//System.out.println(storedEntities.size() + " profissionais encontrados");  
    		}
		} catch (Exception e) {
//			//System.out.println("Erro: " + e.getMessage());
		}
		
		if(storedEntities == null || storedEntities.isEmpty()){   
//			//System.out.println("Não há profissionais associados ao evento"); 
    	} 
	}

	/**
	 * 
	 */
	public void salvarProfissionalEvento() {
		try {			
			EventoProfissional ep = null;
			Profissional p = null;
			List<EventoProfissional> eventoProfissionais = new ArrayList<EventoProfissional>();	
			List<Integer> rowsTratadas = new ArrayList<Integer>();
			for (int row : this.formularioEventoGUI.getTabelaProfissionaisSelecionados()) {
				p = this.formularioEventoGUI.getModeloTabelaProfissionais().getRow(row);
				if(p != null) {
					Long idEventoProfissional = null;
//					//System.out.println(p.getIdValue());
					try{
						ep = new EventoProfissional();
						ep.setEvento(this.evento);
						ep.setProfissional(p);
						idEventoProfissional = getEventoProfissionalService().incluir(ep);
						if(idEventoProfissional > 0){
							ep.setId(idEventoProfissional);
			    			eventoProfissionais.add(ep);
			    			rowsTratadas.add(row);
			    		}
			    		else {
			    			JOptionPane.showMessageDialog(this.getFormularioEventoGUI(), "Profissional " + p.getId() + " não foi associado ao evento.",
			    					"Erro", JOptionPane.ERROR_MESSAGE);
			    		}
					}
					catch(NumberFormatException nfe){
						JOptionPane.showMessageDialog(this.getFormularioEventoGUI(), "Profissional " + p.getId() + " não foi associado ao evento.",
		    					"ID inválido", JOptionPane.ERROR_MESSAGE);
					}
				}				
			}
			
			if(!eventoProfissionais.isEmpty()){
				this.getFormularioEventoGUI().getModeloTabelaProfissionaisEvento().addRows(eventoProfissionais);
				this.getFormularioEventoGUI().getTabelaProfissionais().clearSelection();
				Collections.sort(rowsTratadas, Collections.reverseOrder());
				for (int i = 0; i < rowsTratadas.size(); i++) {
					Integer item = rowsTratadas.get(i);
					this.getFormularioEventoGUI().getModeloTabelaProfissionais().removeRowRange(item, item);
				}
				//
				recarregarTableModelHorariosEvento();
			}
			
		} catch (Exception e) {
			//
//			//System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
			JOptionPane.showMessageDialog(this.getFormularioEventoGUI(), 
					"Erro ao tentar salvar:\n" + e.getLocalizedMessage(),
					"Erro de salvamento", JOptionPane.ERROR_MESSAGE);
		}		
	}

	
	/**
	 * 
	 */
	@Deprecated
	public void salvarProfissionalEvento_old() {
		try {			
			EventoProfissional ep = null;
			Profissional p = null;
			List<EventoProfissional> eventoProfissionais = new ArrayList<EventoProfissional>();	
			List<StringBuilder> elementosStringTratados = new ArrayList<StringBuilder>();
			StringBuilder temp = null;
			for (String stringProfissional : this.formularioEventoGUI.getListaProfissionaisSelecionados()) {
				temp = new StringBuilder(stringProfissional);
				CharSequence cs = stringProfissional.subSequence(FormularioEventoGUI.PREFIXO_ID_PROFISSIONAL.length(), stringProfissional.indexOf(FormularioEventoGUI.SUFIXO_ID_PROFISSIONAL));
				String stringIdProfissional = cs.toString();
				Long idProfissional = null;
				Long idEventoProfissional = null;
				try{
					idProfissional = Long.parseLong(stringIdProfissional);
					p = getProfissionalService().getEntity(idProfissional);
					ep = new EventoProfissional();
					ep.setEvento(this.evento);
					ep.setProfissional(p);
					idEventoProfissional = getEventoProfissionalService().incluir(ep);
					if(idEventoProfissional > 0){
						ep.setId(idEventoProfissional);
		    			eventoProfissionais.add(ep);
		    			elementosStringTratados.add(temp);
		    		}
		    		else {
		    			JOptionPane.showMessageDialog(this.getFormularioEventoGUI(), "Profissional " + idProfissional + " não foi associado ao evento.",
		    					"Erro", JOptionPane.ERROR_MESSAGE);
		    		}
				}
				catch(NumberFormatException nfe){
					JOptionPane.showMessageDialog(this.getFormularioEventoGUI(), "Profissional " + idProfissional + " não foi associado ao evento.",
	    					"ID inválido", JOptionPane.ERROR_MESSAGE);
				}
			}
			
			if(!eventoProfissionais.isEmpty()){
				this.getFormularioEventoGUI().getModeloTabelaProfissionaisEvento().addRows(eventoProfissionais);
				this.getFormularioEventoGUI().getProfissionais().clearSelection();
				for (StringBuilder item : elementosStringTratados) {
					this.getFormularioEventoGUI().getListModel().removeElement(item.toString());
				}
			}
			
		} catch (Exception e) {
			//
//			//System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
			JOptionPane.showMessageDialog(this.getFormularioEventoGUI(), 
					"Erro ao tentar salvar:\n" + e.getLocalizedMessage(),
					"Erro de salvamento", JOptionPane.ERROR_MESSAGE);
		}		
	}

	/**
	 * 
	 * @return
	 */
	public java.util.List<Profissional> getListaProfissionaisDisponiveis() {
		return getEventoProfissionalService().getProfissinaisNaoAssociadosAoEvento(this.evento);
	}

	/**
	 * 
	 */
	public void buscarProfissionais() {
		List<Profissional> storedEntities = null;
		try {
			storedEntities = getListaProfissionaisDisponiveis();
    		if(storedEntities != null && !storedEntities.isEmpty()){
    			this.getFormularioEventoGUI().getModeloTabelaProfissionais().addRows(storedEntities);
//    			//System.out.println(storedEntities.size() + " profissionais encontrados");  
    		}
		} catch (Exception e) {
//			//System.out.println("Erro: " + e.getMessage());
		}
		
		if(storedEntities == null || storedEntities.isEmpty()){   
//			//System.out.println("Não há profissionais"); 
    	} 
	}
	
	/**
	 * 
	 */
	public void recarregarTableModelProfissionais(){
		try{
			int rowCount = this.getFormularioEventoGUI().getModeloTabelaProfissionais().getRowCount();
			if(rowCount > 0) {
				this.getFormularioEventoGUI().getModeloTabelaProfissionais().removeRowRange(0, rowCount - 1);				
			}
			buscarProfissionais();
		}
		catch(Exception e) {
//			//System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
			JOptionPane.showMessageDialog(this.getFormularioEventoGUI(), 
					"Erro ao tentar recarregar lista de profissionais:\n" + e.getLocalizedMessage(),
					"Erro de recarregamento", JOptionPane.ERROR_MESSAGE);
		}		
	}
	
	/**
	 * 
	 */
	public void excluirProfissionalEvento() {		
		boolean sucesso = false;
		try {
			excluirLinha(getEventoProfissionalService(), this.formularioEventoGUI.getModeloTabelaProfissionaisEvento(),
				this.formularioEventoGUI.getTabelaProfissionaisEvento().getSelectedRows());	
			sucesso = true;
		}
		catch(Exception e){
//			//System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
			JOptionPane.showMessageDialog(this.getFormularioEventoGUI(), 
					"Erro ao tentar excluir lista de profissionais:\n" + e.getLocalizedMessage(),
					"Erro de exclusão", JOptionPane.ERROR_MESSAGE);
		}
		
		if(sucesso){
			recarregarTableModelProfissionais();
			recarregarTableModelHorariosEvento();	
		}
	}	
	
	
	// Características
	
	/**
	 * 
	 * @return
	 */
	public CaracteristicaService getCaracteristicaService() {
		if(this.caracteristicaService == null) {
			this.caracteristicaService =  new CaracteristicaServiceImpl();
		}		
		return this.caracteristicaService;
	}
	
	public EventoCaracteristicaService getEventoCaracteristicaService() {
		if(this.eventoCaracteristicaService == null) {
			this.eventoCaracteristicaService =  new EventoCaracteristicaServiceImpl();
		}		
		return this.eventoCaracteristicaService;
	}
	
	public void buscarCaracteristicas() {
		List<Caracteristica> storedEntities = null;
		try {
			storedEntities = getListaCaracteristicasDisponiveis();
    		if(storedEntities != null && !storedEntities.isEmpty()){
    			this.getFormularioEventoGUI().getModeloTabelaCaracteristicas().addRows(storedEntities);
//    			//System.out.println(storedEntities.size() + " características encontradas");  
    		}
		} catch (Exception e) {
//			//System.out.println("Erro: " + e.getMessage());
		}
		
		if(storedEntities == null || storedEntities.isEmpty()){   
//			//System.out.println("Não há características"); 
    	} 		
	}

	private List<Caracteristica> getListaCaracteristicasDisponiveis() {
		return getEventoCaracteristicaService().getCaracteristicasNaoAssociadosAoEvento(this.evento);
	}

	public void buscarCaracteristicasDoEvento() {
		List<EventoCaracteristica> storedEntities = null;
		try {
			String filtroConsulta = "evento = " + this.evento.getId().toString();
			storedEntities = (List<EventoCaracteristica>)getEventoCaracteristicaService().getList(filtroConsulta);
    		if(storedEntities != null && !storedEntities.isEmpty()){
    			this.getFormularioEventoGUI().getModeloTabelaCaracteristicasEvento().addRows(storedEntities);
//    			//System.out.println(storedEntities.size() + " características encontradas");  
    		}
		} catch (Exception e) {
//			//System.out.println("Erro: " + e.getMessage());
		}
		
		if(storedEntities == null || storedEntities.isEmpty()){   
//			//System.out.println("Não há características associadas ao evento"); 
    	} 		
	}

	public void incluirCaracteristicaEvento() {
		if(!verificarAtualizacaoPendente()){			
			try {			
				EventoCaracteristica ep = null;
				Caracteristica p = null;
				List<EventoCaracteristica> eventoCaracteristicas = new ArrayList<EventoCaracteristica>();	
				List<Integer> rowsTratadas = new ArrayList<Integer>();
				for (int row : this.formularioEventoGUI.getTabelaCaracteristicasSelecionados()) {
					p = this.formularioEventoGUI.getModeloTabelaCaracteristicas().getRow(row);
					if(p != null) {
						Long idEventoCaracteristica = null;
						//System.out.println(p.getIdValue());
						try{
							ep = new EventoCaracteristica();
							ep.setEvento(this.evento);
							ep.setCaracteristica(p);
							idEventoCaracteristica = getEventoCaracteristicaService().incluir(ep);
							if(idEventoCaracteristica > 0){
								ep.setId(idEventoCaracteristica);
				    			eventoCaracteristicas.add(ep);
				    			rowsTratadas.add(row);
				    		}
				    		else {
				    			JOptionPane.showMessageDialog(this.getFormularioEventoGUI(), "Característica " + p.getId() + " não foi associada ao evento.",
				    					"Erro", JOptionPane.ERROR_MESSAGE);
				    		}
						}
						catch(NumberFormatException nfe){
							JOptionPane.showMessageDialog(this.getFormularioEventoGUI(), "Característica " + p.getId() + " não foi associada ao evento.",
			    					"ID inválido", JOptionPane.ERROR_MESSAGE);
						}
					}				
				}
				
				if(!eventoCaracteristicas.isEmpty()){
					this.getFormularioEventoGUI().getModeloTabelaCaracteristicasEvento().addRows(eventoCaracteristicas);
					this.getFormularioEventoGUI().getTabelaCaracteristicas().clearSelection();
					Collections.sort(rowsTratadas, Collections.reverseOrder());
					for (int i = 0; i < rowsTratadas.size(); i++) {
						Integer item = rowsTratadas.get(i);
						this.getFormularioEventoGUI().getModeloTabelaCaracteristicas().removeRowRange(item, item);
					}
				}
				
			} catch (Exception e) {
				//
				//System.out.println("Erro: " + e.getMessage());
				e.printStackTrace();
				JOptionPane.showMessageDialog(this.getFormularioEventoGUI(), 
						"Erro ao tentar salvar:\n" + e.getLocalizedMessage(),
						"Erro de salvamento", JOptionPane.ERROR_MESSAGE);
			}				
		}		
	}

	public void excluirCaracteristicaEvento() {
		if(!verificarAtualizacaoPendente() && this.formularioEventoGUI.getModeloTabelaCaracteristicasEvento().getRowCount() > 0){
			boolean sucesso = false;
			try {
				excluirLinha(getEventoCaracteristicaService(), this.formularioEventoGUI.getModeloTabelaCaracteristicasEvento(),
					this.formularioEventoGUI.getTabelaCaracteristicasEvento().getSelectedRows());	
				sucesso = true;
			}
			catch(Exception e){
				//System.out.println("Erro: " + e.getMessage());
				e.printStackTrace();
				JOptionPane.showMessageDialog(this.getFormularioEventoGUI(), 
						"Erro ao tentar excluir lista de características:\n" + e.getLocalizedMessage(),
						"Erro de exclusão", JOptionPane.ERROR_MESSAGE);
			}
			
			if(sucesso){
				recarregarTableModelCaracteristicas();
			}
		}
	}

	public void alterarCaracteristicaEvento() {
		//System.out.println(linhasEventoCaracteristicaAtualizadas.size());
		if(linhasEventoCaracteristicaAtualizadas != null && !linhasEventoCaracteristicaAtualizadas.isEmpty()){
			List<Integer> indexLinhaAtualizadaSucesso = new ArrayList<Integer>();
			for (Integer row : linhasEventoCaracteristicaAtualizadas) {
				//System.out.println("Tentando persistir entidade da linha " + row + "...");				
				EventoCaracteristica entity = this.formularioEventoGUI.getModeloTabelaCaracteristicasEvento().getRow(row);				
				try {
					if(getEventoCaracteristicaService().alterar(entity)){
		    			indexLinhaAtualizadaSucesso.add(row);		  
		    			JOptionPane.showMessageDialog(this.getFormularioEventoGUI(), "Dados salvos com sucesso.",
		    					"Informação", JOptionPane.INFORMATION_MESSAGE);
		    		}
					else {
						JOptionPane.showMessageDialog(this.getFormularioEventoGUI(), "Dados não foram salvos.",
		    					"Erro", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e) {
					//
					//System.out.println("Erro: " + e.getMessage());
					JOptionPane.showMessageDialog(this.formularioEventoGUI, 
							"Erro ao tentar excluir a linha " + row + ":\n" + e.getLocalizedMessage(),
							"Erro de exclusão", JOptionPane.ERROR_MESSAGE);
				}
			}
			
			if(!indexLinhaAtualizadaSucesso.isEmpty()){
				for (Integer integer : indexLinhaAtualizadaSucesso) {
					linhasEventoCaracteristicaAtualizadas.remove(integer);
				}				
			}
		}
	}
	
	public void recarregarTableModelCaracteristicas(){
		try{
			int rowCount = this.getFormularioEventoGUI().getModeloTabelaCaracteristicas().getRowCount();
			if(rowCount > 0) {
				this.getFormularioEventoGUI().getModeloTabelaCaracteristicas().removeRowRange(0, rowCount - 1);				
			}
			buscarCaracteristicas();
		}
		catch(Exception e) {
			//System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
			JOptionPane.showMessageDialog(this.getFormularioEventoGUI(), 
					"Erro ao tentar recarregar lista de características:\n" + e.getLocalizedMessage(),
					"Erro de recarregamento", JOptionPane.ERROR_MESSAGE);
		}		
	}
	
	public void recarregarTableModelCaracteristicasEvento(){
		try{
			int rowCount = this.getFormularioEventoGUI().getModeloTabelaCaracteristicasEvento().getRowCount();
			if(rowCount > 0) {
				this.getFormularioEventoGUI().getModeloTabelaCaracteristicasEvento().removeRowRange(0, rowCount - 1);	
				buscarCaracteristicasDoEvento();
			}				
		}
		catch(Exception e) {
			//System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
			JOptionPane.showMessageDialog(this.getFormularioEventoGUI(), 
					"Erro ao tentar recarregar lista de características do evento:\n" + e.getLocalizedMessage(),
					"Erro de recarregamento", JOptionPane.ERROR_MESSAGE);
		}		
	}

	
	/**
	 * 
	 * @param row
	 */
	public void marcarLinhaEventoCaracteristicaAtualizada(int row){
		linhasEventoCaracteristicaAtualizadas.add(row);
	}
	
	
	// Espaços
	
	/**
	 * 
	 * @return
	 */
	public EspacoService getEspacoService() {
		if(this.espacoService == null) {
			this.espacoService =  new EspacoServiceImpl();
		}		
		return this.espacoService;
	}
	
	public EventoEspacoService getEventoEspacoService() {
		if(this.eventoEspacoService == null) {
			this.eventoEspacoService =  new EventoEspacoServiceImpl();
		}		
		return this.eventoEspacoService;
	}
	
	private List<Espaco> getListaEspacosDisponiveis() {
		return getEventoEspacoService().getEspacosNaoAssociadosAoEvento(this.evento);
	}

	public void buscarEspacos() {
		List<Espaco> storedEntities = null;
		try {
			storedEntities = getListaEspacosDisponiveis();
    		if(storedEntities != null && !storedEntities.isEmpty()){
    			this.getFormularioEventoGUI().getModeloTabelaEspacos().addRows(storedEntities);
    			//System.out.println(storedEntities.size() + " espaços encontrados");  
    		}
		} catch (Exception e) {
			//System.out.println("Erro: " + e.getMessage());
		}
		
		if(storedEntities == null || storedEntities.isEmpty()){   
			//System.out.println("Não há espaços cadastrados"); 
    	} 		
	}

	public void buscarEspacosDoEvento() {
		List<EventoEspaco> storedEntities = null;
		try {
			String filtroConsulta = "evento = " + this.evento.getId().toString();
			storedEntities = (List<EventoEspaco>)getEventoEspacoService().getList(filtroConsulta);
    		if(storedEntities != null && !storedEntities.isEmpty()){
    			this.getFormularioEventoGUI().getModeloTabelaEspacosEvento().addRows(storedEntities);
    			//System.out.println(storedEntities.size() + " espaços encontrados");  
    		}
		} catch (Exception e) {
			//System.out.println("Erro: " + e.getMessage());
		}
		
		if(storedEntities == null || storedEntities.isEmpty()){   
			//System.out.println("Não há espaços associadas ao evento"); 
    	} 		
	}

	public void excluirEspacoEvento() {
		if(this.formularioEventoGUI.getModeloTabelaEspacosEvento().getRowCount() > 0){
			boolean sucesso = false;
			try {
				excluirLinha(getEventoEspacoService(), this.formularioEventoGUI.getModeloTabelaEspacosEvento(),
					this.formularioEventoGUI.getTabelaEspacosEvento().getSelectedRows());	
				sucesso = true;
			}
			catch(Exception e){
				//System.out.println("Erro: " + e.getMessage());
				e.printStackTrace();
				JOptionPane.showMessageDialog(this.getFormularioEventoGUI(), 
						"Erro ao tentar excluir lista de espaços:\n" + e.getLocalizedMessage(),
						"Erro de exclusão", JOptionPane.ERROR_MESSAGE);
			}
			
			if(sucesso){
				recarregarTableModelEspacos();
				recarregarTableModelHorariosEvento();				
			}
		}		
	}

	public void incluirEspacoEvento() {				
		try {			
			EventoEspaco ep = null;
			Espaco espaco = null;
			List<EventoEspaco> eventoEspacos = new ArrayList<EventoEspaco>();	
			List<Integer> rowsTratadas = new ArrayList<Integer>();
			for (int row : this.formularioEventoGUI.getTabelaEspacosSelecionados()) {
				espaco = this.formularioEventoGUI.getModeloTabelaEspacos().getRow(row);
				if(espaco != null) {
					StatusVerificacao statusVerificacao = getEventoEspacoService().verificarAssociacaoEspacoAoEvento(evento, espaco);
					if(!statusVerificacao.getBooleanResultado()){
						JOptionPane.showMessageDialog(this.getFormularioEventoGUI(), statusVerificacao.getMensagemResultado(),
		    					"Associação não permitida", JOptionPane.WARNING_MESSAGE);
					}
					else {
						Long idEventoEspaco = null;
						//System.out.println(espaco.getIdValue());
						try{
							ep = new EventoEspaco();
							ep.setEvento(this.evento);
							ep.setEspaco(espaco);
							idEventoEspaco = getEventoEspacoService().incluir(ep);
							if(idEventoEspaco > 0){
								ep.setId(idEventoEspaco);
				    			eventoEspacos.add(ep);
				    			rowsTratadas.add(row);
				    		}
				    		else {
				    			JOptionPane.showMessageDialog(this.getFormularioEventoGUI(), "Espaço " + espaco.getId() + " não foi associado ao evento.",
				    					"Erro", JOptionPane.ERROR_MESSAGE);
				    		}
						}
						catch(NumberFormatException nfe){
							JOptionPane.showMessageDialog(this.getFormularioEventoGUI(), "Espaço " + espaco.getId() + " não foi associado ao evento.",
			    					"ID inválido", JOptionPane.ERROR_MESSAGE);
						}
					}	
				}
			}
			
			if(!eventoEspacos.isEmpty()){
				this.getFormularioEventoGUI().getModeloTabelaEspacosEvento().addRows(eventoEspacos);
				this.getFormularioEventoGUI().getTabelaEspacos().clearSelection();
				Collections.sort(rowsTratadas, Collections.reverseOrder());
				for (int i = 0; i < rowsTratadas.size(); i++) {
					Integer item = rowsTratadas.get(i);
					this.getFormularioEventoGUI().getModeloTabelaEspacos().removeRowRange(item, item);
				}
				//
				recarregarTableModelHorariosEvento();
			}
			
		} catch (Exception e) {
			//
			//System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
			JOptionPane.showMessageDialog(this.getFormularioEventoGUI(), 
					"Erro ao tentar salvar:\n" + e.getLocalizedMessage(),
					"Erro de salvamento", JOptionPane.ERROR_MESSAGE);
		}			
	}
	

	/**
	 * 
	 */
	public void recarregarTableModelEspacos(){
		try{
			int rowCount = this.getFormularioEventoGUI().getModeloTabelaEspacos().getRowCount();
			if(rowCount > 0) {
				this.getFormularioEventoGUI().getModeloTabelaEspacos().removeRowRange(0, rowCount - 1);				
			}
			buscarEspacos();
		}
		catch(Exception e) {
			//System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
			JOptionPane.showMessageDialog(this.getFormularioEventoGUI(), 
					"Erro ao tentar recarregar lista de espaços:\n" + e.getLocalizedMessage(),
					"Erro de recarregamento", JOptionPane.ERROR_MESSAGE);
		}		
	}
	
	public void recarregarTableModelEspacosEvento(){
		try{
			int rowCount = this.getFormularioEventoGUI().getModeloTabelaEspacosEvento().getRowCount();
			if(rowCount > 0) {
				this.getFormularioEventoGUI().getModeloTabelaEspacosEvento().removeRowRange(0, rowCount - 1);	
				buscarEspacosDoEvento();
			}				
		}
		catch(Exception e) {
			//System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
			JOptionPane.showMessageDialog(this.getFormularioEventoGUI(), 
					"Erro ao tentar recarregar lista de espaços do evento:\n" + e.getLocalizedMessage(),
					"Erro de recarregamento", JOptionPane.ERROR_MESSAGE);
		}		
	}

	
	// Horários do Evento
	
	/**
	 * 
	 */
	public void buscarHorariosDoEvento() {
		List<HorarioEvento> storedEntities = null;
		try {
			storedEntities = (List<HorarioEvento>)getEventoService().getEventoHorarios(this.evento);
    		if(storedEntities != null && !storedEntities.isEmpty()){
    			this.getFormularioEventoGUI().getModeloTabelaHorariosEvento().addRows(storedEntities);
    			//System.out.println(storedEntities.size() + " horários do evento encontrados");  
    		}
		} catch (Exception e) {
			//System.out.println("Erro: " + e.getMessage());
		}
		
		if(storedEntities == null || storedEntities.isEmpty()){   
			//System.out.println("Não há horários definidos para o evento"); 
    	}
	}
	
	/**
	 * 
	 */
	public void recarregarTableModelHorariosEvento(){
		try{
			int rowCount = this.getFormularioEventoGUI().getModeloTabelaHorariosEvento().getRowCount();
			if(rowCount > 0) {
				this.getFormularioEventoGUI().getModeloTabelaHorariosEvento().removeRowRange(0, rowCount - 1);	
				buscarHorariosDoEvento();
			}				
		}
		catch(Exception e) {
			//System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
			JOptionPane.showMessageDialog(this.getFormularioEventoGUI(), 
					"Erro ao tentar recarregar lista de horários do evento:\n" + e.getLocalizedMessage(),
					"Erro de recarregamento", JOptionPane.ERROR_MESSAGE);
		}		
	}
}