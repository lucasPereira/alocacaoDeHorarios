package br.ufsc.sar.service;

import java.util.List;

import br.ufsc.sar.entity.Profissional;
import br.ufsc.service.BaseService;

public interface ProfissionalService extends BaseService<Profissional>{
	public Long incluir(Profissional objeto) throws Exception;

	public boolean excluir(Long id) throws Exception;

	public boolean alterar(Profissional objeto) throws Exception;

	public Profissional ping();
	
	public Profissional getEntity(Long id);
	
	public List<Profissional> getList();
	
	public List<Profissional> getListPorNome(String nome);
	
	public boolean isNomeProfissional(String nome);
}
