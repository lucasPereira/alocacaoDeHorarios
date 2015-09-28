package br.ufsc.sar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.ufsc.entity.BaseEntity;

/**
 * 
 * @author João
 *
 */
@Entity
@Table (schema="sardb", name="evento_espaco")
public class EventoEspaco extends BaseEntity {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5122527311679797907L;

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "idevento", referencedColumnName = "id", nullable = false)
	private Evento evento;
	
	@ManyToOne
	@JoinColumn(name = "idespaco", referencedColumnName = "id", nullable = false)
	private Espaco espaco;
		
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
	
	public Espaco getEspaco() {
		return espaco;
	}
	public void setEspaco(Espaco espaco) {
		this.espaco = espaco;
	}
}