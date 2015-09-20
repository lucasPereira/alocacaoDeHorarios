package br.ufsc.sar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import br.ufsc.entity.BaseEntity;

@Entity
@Table(schema="sardb", name="evento")
public class Evento extends BaseEntity{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -8002569933391195576L;
		
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name="nome", nullable = false)
	private String nome;
	
//	@OneToMany
//    @JoinTable(name = "evento_caracteristica", joinColumns = @JoinColumn(name="idevento", referencedColumnName = "id"),
//               inverseJoinColumns = @JoinColumn( name="ideventocaracteristica", referencedColumnName = "id")
//  )
//	private List<EventoCaracteristica> eventoCaracteristicas;
	
//	@OneToMany
//  @JoinTable(
//          name="evento_caracteristica",
//          joinColumns = @JoinColumn( name="idevento"),
//          inverseJoinColumns = @JoinColumn( name="idprofissional")
//  )
//	private HashMap<Caracteristica, Integer> eventoCaracteristicas;
	
//	@OneToMany(fetch = FetchType.EAGER, mappedBy = "evento", targetEntity = HorarioEvento.class)
//	private List<HorarioEvento> eventoHorarios;
	
//	private Set<Profissional> profissionais = new HashSet<Profissional>();
//	
//	@OneToMany
//    @JoinTable(
//            name="evento_profissional",
//            joinColumns = @JoinColumn( name="idevento"),
//            inverseJoinColumns = @JoinColumn( name="idprofissional")
//    )
//    public Set<Profissional> getProfissionais() {
//		return profissionais; 
//	}
//	
//    void setProfissionais(Set<Profissional> profissionais) {
//    	this.profissionais = profissionais; 
//    }
	
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
	
//	public List<EventoCaracteristica> getEventoCaracteristicas() {
//		return this.eventoCaracteristicas;
//	}
//	
//	public List<HorarioEvento> getEventoHorarios() {
//		return this.eventoHorarios;
//	}
	
}
