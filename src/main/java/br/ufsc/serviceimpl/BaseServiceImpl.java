package br.ufsc.serviceimpl;


import java.lang.reflect.ParameterizedType;
import java.security.InvalidParameterException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.ufsc.entity.BaseEntity;
import br.ufsc.sar.entity.Caracteristica;
import br.ufsc.service.BaseService;

public abstract class BaseServiceImpl<E extends BaseEntity> implements BaseService<E> {

	private EntityManager em = null;
	
	private Class<E> baseentity;

	public BaseServiceImpl() {

	}

	public boolean alterar(E objeto) throws Exception {
		this.getEntityManager().getTransaction().begin();
		this.getEntityManager().merge(objeto);
		this.getEntityManager().getTransaction().commit();
		return true;
	}

	public boolean excluir(Long id) throws Exception {
		E entity = this.getEntity(id);
		if (entity != null) {
			this.getEntityManager().getTransaction().begin();
			this.getEntityManager().remove(entity);
			this.getEntityManager().getTransaction().commit();
		}
		return true;
	}

	public E getEntity(Long id) {
		System.out.println("BASE ENTITY: " + this.getBaseentity().getClass().getName());
		return getEntityManager().find(this.getBaseentity(), id);
	}

	public Long incluir(E objeto) throws Exception {
		System.out.println("Nome da Classe: " + objeto.getClass().getName());
		if ( objeto.getClass().getName().equals(Caracteristica.class.getName()))
			System.out.println("Nome: " + ((Caracteristica) objeto).getNome());
		else
			System.out.println("Ops!");
		
		this.getEntityManager().getTransaction().begin();
		this.getEntityManager().persist(objeto);
		this.getEntityManager().getTransaction().commit();
		
		System.out.println(objeto.getIdValue());
		Number result = ((BaseEntity) objeto).getIdValue();
		if (result == null) {
			throw new Exception("Não foi possível ((BaseEntity) objeto).getIdValue(); ");
		}
		return result.longValue();
	}

	@SuppressWarnings("unchecked")
	public List<E> getList() {
		return getEntityManager().createQuery("SELECT u FROM " + this.getBaseentity().getSimpleName() + " u").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<E> getList(String filtro) {
		return getEntityManager().createQuery("SELECT u FROM " + this.getBaseentity().getSimpleName() + " u WHERE u." + filtro).getResultList();
	}	
	
	protected EntityManager getEntityManager() {
		if (this.em == null) {
			this.em = Persistence.createEntityManagerFactory("sarDB").createEntityManager();
			System.out.println("Propriedades:" + this.em.getProperties().toString());
		}
		return this.em;
	}

	public void validateMandatoryParameters(Object[][] parameters) throws InvalidParameterException {
		for (int i = 0; i < parameters.length; i++) {
			if (parameters[i][1] == null) {
				throw new InvalidParameterException("O parâmetro '" + parameters[i][0] + "' não foi informado.");
			}
		}
	}

	public Object ping() {
		return new Date().getTime();
	}

	@SuppressWarnings("unchecked")
	public Class<E> getBaseentity() {
		if (this.baseentity == null){
			try {
				this.baseentity = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
			} catch (ClassCastException cc) {
				this.baseentity = (Class<E>) ((ParameterizedType) getClass().getSuperclass().getGenericSuperclass()).getActualTypeArguments()[0];
			}
		}
		return this.baseentity;
	}

	public void setBaseentity(Class<E> baseentity) {
		this.baseentity = baseentity;
	}
}