package br.ufsc.sar.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.ufsc.sar.entity.Caracteristica;
import br.ufsc.sar.service.CaracteristicaService;
import br.ufsc.serviceimpl.BaseServiceImpl;
import br.ufsc.util.exception.ParametroInvalidoException;

public class CaracteristicaServiceImpl extends BaseServiceImpl<Caracteristica> implements CaracteristicaService{
	
	public Long incluir(Caracteristica objeto) throws Exception{
		if(this.isNomeCaracteristica(objeto.getNome())){
			throw new ParametroInvalidoException("Já existe uma outra característica com este mesmo nome!");
		}
		return super.incluir(objeto);
	}

	public boolean excluir(Long id) throws Exception{
		return super.excluir(id);
	}

	public boolean alterar(Caracteristica objeto) throws Exception{
		for (Caracteristica carateristica : this.getListPorNome(objeto.getNome())){
			if(carateristica.getId() != objeto.getId()){
				throw new ParametroInvalidoException("Já existe uma outra característica com este mesmo nome!");
			}
		}
		return super.alterar(objeto);
	}

	public Caracteristica ping(){
		return (Caracteristica) super.ping();
	}
	
	public Caracteristica getEntity(Long id){
		return super.getEntity(id);
	}
	
	public List<Caracteristica> getList(){
		return super.getList();
	}
	
	public List<Caracteristica> getListAtivas(){
		List<Caracteristica> listCaracteristica = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("select c ");
		sql.append("from Caracteristica c ");
		sql.append("where (c.forauso = false or c.forauso = null)");
		
		Query query = super.getEntityManager().createQuery(sql.toString());
		
		try {
			listCaracteristica = (List<Caracteristica>) query.getResultList();
		} catch (NoResultException e) {
			//System.out.println("CaracteristicaServiceImpl.getListAtivas()");
			//System.out.println(":: Nenhum resultado encontrado ::");
			listCaracteristica = new ArrayList<Caracteristica>();
		}
		return listCaracteristica;
	}
	
	public List<Caracteristica> getListPorNome(String nome){
		
		List<Caracteristica> listCaracteristica = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("select c ");
		sql.append("from Caracteristica c ");
		sql.append("where Upper(c.nome) = :nome");
		
		Query query = super.getEntityManager().createQuery(sql.toString());
		query.setParameter("nome", nome.toUpperCase());
		
		try {
			listCaracteristica = (List<Caracteristica>) query.getResultList();
		} catch (NoResultException e) {
			//System.out.println("CaracteristicaServiceImpl.getListPorNome()");
			//System.out.println(":: Nenhum resultado encontrado ::");
			listCaracteristica = new ArrayList<Caracteristica>();
		}
		return listCaracteristica;
	}
	
	
	public Caracteristica getPorNome(String nome) throws ParametroInvalidoException{
		List<Caracteristica> listCaracteristica= this.getListPorNome(nome);
		try {
			if (listCaracteristica.size() != 1){
				throw new ParametroInvalidoException("Existe mais de uma característica com o mesmo nome, isto não pode acontecer!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listCaracteristica.get(0);
	}
	
	public boolean isNomeCaracteristica(String nome){
		if (this.getListPorNome(nome).isEmpty())
			return false;
		return true;
	}
}
