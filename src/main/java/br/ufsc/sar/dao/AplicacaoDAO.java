package br.ufsc.sar.dao;

import br.ufsc.sar.entity.Aplicacao;

public class AplicacaoDAO {
	
	private Long id;
	private String nome;
	private String descricao;
	private Long nrUsuarios;
	
	public AplicacaoDAO(){
		super();
	}
	
	public AplicacaoDAO(Aplicacao app){
		super();
		this.setId(app.getId());
		this.setNome(app.getNome());
		this.setDescricao(app.getDescricao());
	}
	
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
	public Long getNrUsuarios() {
		return nrUsuarios;
	}
	public void setNrUsuarios(Long nrUsuarios) {
		this.nrUsuarios = nrUsuarios;
	}
	
	

}
