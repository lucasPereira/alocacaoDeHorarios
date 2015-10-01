package br.ufsc.service;

import java.util.List;

import br.ufsc.entity.BaseEntity;

public interface BaseService<E extends BaseEntity> {

	/**
	 * Incluir um objeto
	 * 
	 * @param objeto
	 *            Objeto a ser incluido.
	 * @return Long
	 * @throws java.lang.Exception
	 */
	public Long incluir(E objeto) throws Exception;

	/**
	 * Exclui um objeto com base no seu código.
	 * 
	 * @param Objeto
	 *            a ser excluído
	 * @return boolean <code>true</code> se o objeto foi excluído.
	 *         <code>false</code> se o objeto não foi excluído.
	 * @throws java.lang.Exception
	 */
	public boolean excluir(Long id) throws Exception;

	public void exluirTodos();

	/**
	 * Altera (atualizar) um objeto.
	 * 
	 * @param objeto
	 *            a ser atualizado.
	 * @return boolean <code>true</code> se o objeto foi alterado.
	 *         <code>false</code> se o objeto não foi alterado.
	 * @throws java.lang.Exception
	 */
	public boolean alterar(E objeto) throws Exception;

	/**
	 * Executa um <i>ping</i> no serviço.
	 * <p>
	 * O objetivo do método é testar se o serviço está disponível e executando
	 * corretamente.
	 * <p>
	 * A implementação padrão retorna um <i>timestamp/i>.
	 * 
	 * @return {@link Object} <code>long</code> timestamp na implementação
	 *         padrão.
	 */
	public Object ping();

	/**
	 * Retorna um objeto por Id
	 * 
	 * @param id
	 * @return objeto
	 */
	public E getEntity(Long id);

	/**
	 * Retorna uma lista de objetos que estão na base
	 * 
	 * @return List<?>
	 */
	public List<E> getList();

	/**
	 * Retorna uma lista de objetos que estão na base, segundo o filtro
	 * (clausula WHERE)
	 * 
	 * @param filtro
	 * @return
	 */
	public List<E> getList(String filtro);

	/**
	 * 
	 * @param ids
	 * @return
	 */
	public List<E> getListPorID(List<Long> ids);

}
