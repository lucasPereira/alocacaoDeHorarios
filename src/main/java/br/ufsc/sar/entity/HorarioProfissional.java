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
@Table (schema="sardb", name="horario_profissional")
public class HorarioProfissional extends Horario {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3023520337975006814L;	
	
	@ManyToOne
	@JoinColumn(name = "idprofissional", referencedColumnName = "id", nullable = false)
	private Profissional profissional;
	
	public Profissional getProfissional() {
		return profissional;
	}
	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}
}