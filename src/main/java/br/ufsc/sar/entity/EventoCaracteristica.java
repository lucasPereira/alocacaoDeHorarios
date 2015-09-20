package br.ufsc.sar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.ufsc.entity.BaseEntity;

@Entity
@Table (schema="sardb", name="evento_caracteristica")
public class EventoCaracteristica extends BaseEntity {	

	/**
	 * 
	 */
	private static final long serialVersionUID = 8068085620364889770L;

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "idevento", referencedColumnName = "id", nullable = false)
	private Evento evento;
	
	@ManyToOne
	@JoinColumn(name = "idcaracteristica", referencedColumnName = "id", nullable = false)
	private Caracteristica caracteristica;
	
	@Column(name="quantidade")
	private Integer quantidade;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	
	public Caracteristica getCaracteristica() {
		return caracteristica;
	}
	public void setCaracteristica(Caracteristica caracteristica) {
		this.caracteristica = caracteristica;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}