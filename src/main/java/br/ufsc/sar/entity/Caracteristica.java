package br.ufsc.sar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;

import br.ufsc.entity.BaseEntity;

@Entity
@Table(schema="sardb", name="caracteristica")
public class Caracteristica extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6327434073729395717L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="forauso")
	private Boolean forauso;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Boolean getForauso() {
		return forauso;
	}
	public void setForauso(Boolean forauso) {
		this.forauso = forauso;
	} 

}
