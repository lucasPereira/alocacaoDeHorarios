package br.ufsc.util.type;

import br.ufsc.entity.BaseEntity;

public class EntidadeDetalheInfo<T extends BaseEntity,E extends BaseEntity> {
	
	private T entidadeRaiz;
	
	private E entidadeDetalhe;
	
	private Class<E> entidadeDetalheClass;
	
	private String colunaDetalhe;
	
	public T getEntidadeRaiz() {
		return entidadeRaiz;
	}

	public void setEntidadeRaiz(T entidadeRaiz) {
		this.entidadeRaiz = entidadeRaiz;
	}

	public E getEntidadeDetalhe() {
		return entidadeDetalhe;
	}

	public void setEntidadeDetalhe(E entidadeDetalhe) {
		this.entidadeDetalhe = entidadeDetalhe;
	}

	public Class<E> getEntidadeDetalheClass() {
		return entidadeDetalheClass;
	}

	public void setEntidadeDetalheClass(Class<E> entidadeDetalheClass) {
		this.entidadeDetalheClass = entidadeDetalheClass;
	}

	public String getColunaDetalhe() {
		return colunaDetalhe;
	}

	public void setColunaDetalhe(String colunaDetalhe) {
		this.colunaDetalhe = colunaDetalhe;
	}	
}