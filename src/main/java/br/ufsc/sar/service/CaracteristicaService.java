package br.ufsc.sar.service;

import java.util.List;

import br.ufsc.sar.entity.Caracteristica;
import br.ufsc.service.BaseService;
import br.ufsc.util.exception.ParametroInvalidoException;

public interface CaracteristicaService extends BaseService<Caracteristica>{

	public Long incluir(Caracteristica objeto) throws Exception;

	public boolean excluir(Long id) throws Exception;

	public boolean alterar(Caracteristica objeto) throws Exception;

	public Caracteristica ping();
	
	public Caracteristica getEntity(Long id);
	
	public List<Caracteristica> getList();
	
	public List<Caracteristica> getListPorNome(String nome);
	
	public boolean isNomeCaracteristica(String nome);
	
	public Caracteristica getPorNome(String nome) throws ParametroInvalidoException;
}
