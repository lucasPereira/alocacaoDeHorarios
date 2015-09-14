package br.ufsc.sar.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import br.ufsc.entity.BaseEntity;

@Entity
@Table(schema="sardb", name="aplicacao")
public class Aplicacao extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3771170952857163859L;
	
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	private String descricao;
	
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
