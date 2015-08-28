package br.ufsc.sar.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import br.ufsc.entity.BaseEntity;

@Entity
@Table (schema="sardb", name="local")
public class Local extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3023520337975006814L;
	
	@Id
	@GeneratedValue
	private Long id;

}
