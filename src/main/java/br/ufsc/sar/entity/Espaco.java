package br.ufsc.sar.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.ufsc.entity.BaseEntity;

@Entity
@Table (schema="sardb", name="local")
public class Espaco extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3023520337975006814L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="nome", nullable=false,unique=true)
	private String nome;
	
	@Column(name="descricao", nullable=false)
	private String descricao;
	
	@Column(name="capacidade")
	private Long capacidade;
	
	@Column(name="forauso")
	private boolean forauso;
	
	public boolean isForauso() {
		return forauso;
	}

	public void setForauso(boolean forauso) {
		this.forauso = forauso;
	}

	@OneToMany(fetch=FetchType.EAGER)
	private List<Caracteristica> caracteristicas;
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

	public Long getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(Long capacidade) {
		this.capacidade = capacidade;
	}

	public List<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(List<Caracteristica> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

}
