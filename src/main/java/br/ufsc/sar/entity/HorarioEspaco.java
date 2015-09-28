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
@Table (schema="sardb", name="horario_espaco")
public class HorarioEspaco extends Horario {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3023520337975006814L;	
	
	@ManyToOne
	@JoinColumn(name = "idespaco", referencedColumnName = "id", nullable = false)
	private Espaco espaco;
	
	public Espaco getEspaco() {
		return espaco;
	}
	public void setEspaco(Espaco espaco) {
		this.espaco = espaco;
	}
}