package br.ufsc.sar.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author João
 *
 */
@Entity
@Table (schema="sardb", name="horario_evento")
public class HorarioEvento extends Horario {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3023520337975006814L;	
	
	@ManyToOne
	@JoinColumn(name = "idevento", referencedColumnName = "id", nullable = false)
	private Evento evento;
	
	public Evento getEvento() {
		return evento;
	}
	
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
}