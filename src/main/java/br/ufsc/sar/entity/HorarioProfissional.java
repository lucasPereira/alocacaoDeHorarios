package br.ufsc.sar.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;

import br.ufsc.entity.BaseEntity;

@Entity
@Table (schema="sardb", name="horarioprofissional")
public class HorarioProfissional extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3023520337975006814L;
	
	@Id
	@GeneratedValue
	private Long id;	
		
	@ManyToOne
	@JoinColumn(name = "idprofissional")
	private Profissional profissional;
	
	@Column(name="datainicio")
	@Temporal(TemporalType.DATE)
	private Date datainicio;
	
	@Column(name="datatermino")
	@Temporal(TemporalType.DATE)
	private Date datatermino;
	
	@Column(name="diadasemana")
	private String diadasemana;
	
	@Column(name="horainicio")
	private String horainicio;
	
	@Column(name="horatermino")
	private String horatermino;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Profissional getProfissional() {
		return profissional;
	}
	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}
	
	public String getDiadasemana() {
		return diadasemana;
	}
	public void setDiadasemana(String diadasemana) {
		this.diadasemana = diadasemana;
	}
	public String getHorainicio() {
		return horainicio;
	}
	public void setHorainicio(String horainicio) {
		this.horainicio = horainicio;
	}
	
	public String getHoratermino() {
		return horatermino;
	}
	public void setHoratermino(String horatermino) {
		this.horatermino = horatermino;
	}
	
	public Date getDatainicio() {
		return datainicio;
	}
	public void setDatainicio(Date datainicio) {
		this.datainicio = datainicio;
	} 
	
	public Date getDatatermino() {
		return datatermino;
	}
	public void setDatatermino(Date datatermino) {
		this.datatermino = datatermino;
	} 

}
