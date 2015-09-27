package br.ufsc.sar.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import br.ufsc.entity.BaseEntity;

/**
 * 
 * @author João
 *
 */
@Entity
@Table(schema="sardb", name="profissional")
public class Profissional extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6327434073729395717L;
	
	/**
	 * nome, profissão, data de nascimento, telefone e cpf. Destes, só o nome e a profissão são obrigatórias.
	 */
	
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name="nome", nullable = false)
	private String nome;
	
	@Column(name="profissao", nullable = false)
	private String profissao;
	
	@Column(name="dtnascimento")
	private Date dtnascimento;
	
	@Column(name="telefone")
	private String telefone;
	
	@Column(name="cpf")
	private String cpf;
	
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
	public String getProfissao() {
		return profissao;
	}
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}
	public Date getDtnascimento() {
		return dtnascimento;
	}
	public void setDtnascimento(Date dtnascimento) {
		this.dtnascimento = dtnascimento;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
