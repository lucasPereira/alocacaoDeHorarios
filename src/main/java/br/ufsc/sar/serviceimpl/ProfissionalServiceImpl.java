package br.ufsc.sar.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.ufsc.sar.entity.Profissional;
import br.ufsc.sar.service.ProfissionalService;
import br.ufsc.serviceimpl.BaseServiceImpl;
import br.ufsc.util.exception.ParametroInvalidoException;

/**
 * 
 * @author João
 *
 */
public class ProfissionalServiceImpl extends BaseServiceImpl<Profissional> implements ProfissionalService{
	
	public Long incluir(Profissional objeto) throws Exception{
		if(this.isNomeProfissional(objeto.getNome())){
			throw new ParametroInvalidoException("Já existe um outro profissional com este mesmo nome!");
		}
		return super.incluir(objeto);
	}

	public boolean excluir(Long id) throws Exception{
		return super.excluir(id);
	}

	public boolean alterar(Profissional objeto) throws Exception{
		for (Profissional profissional : this.getListPorNome(objeto.getNome())){
			if(profissional.getId() != objeto.getId()){
				throw new ParametroInvalidoException("Já existe um outro profissional com este mesmo nome!");
			}
		}
		return super.alterar(objeto);
	}

	public Profissional ping(){
		return (Profissional) super.ping();
	}
	
	public Profissional getEntity(Long id){
		return super.getEntity(id);
	}
	
	public List<Profissional> getList(){
		return super.getList();
	}
			
	@SuppressWarnings("unchecked")
	public List<Profissional> getListPorNome(String nome){
		
		List<Profissional> listProfissional = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("select c ");
		sql.append("from Profissional c ");
		sql.append("where Upper(c.nome) = :nome");
		
		Query query = super.getEntityManager().createQuery(sql.toString());
		query.setParameter("nome", nome.toUpperCase());
		
		try {
			listProfissional = (List<Profissional>) query.getResultList();
		} catch (NoResultException e) {
			//System.out.println("ProfissionalServiceImpl.getListPorNome()");
			//System.out.println(":: Nenhum resultado encontrado ::");
			listProfissional = new ArrayList<Profissional>();
		}
		return listProfissional;
	}
	
	public boolean isNomeProfissional(String nome){
		if (this.getListPorNome(nome).isEmpty())
			return false;
		return true;
	}


	
}
