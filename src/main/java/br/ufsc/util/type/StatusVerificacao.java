package br.ufsc.util.type;

/**
 * 
 * @author João
 *
 */
public class StatusVerificacao {
	
	private Boolean booleanResultado;
	
	private String mensagemResultado;

	/**
	 * 
	 */
	public StatusVerificacao() {}
	
	/**
	 * 
	 * @param booleanResultado
	 * @param mensagemResultado
	 */
	public StatusVerificacao(Boolean booleanResultado, String mensagemResultado) {
		this.booleanResultado = booleanResultado;
		this.mensagemResultado = mensagemResultado;
	}
	
	/**
	 * 
	 * @param booleanResultado
	 * @param mensagemResultado
	 */
	public StatusVerificacao(Boolean booleanResultado) {
		this.booleanResultado = booleanResultado;
	}

	/**
	 * @return the booleanResultado
	 */
	public Boolean getBooleanResultado() {
		return booleanResultado;
	}

	/**
	 * @param booleanResultado the booleanResultado to set
	 */
	public void setBooleanResultado(Boolean booleanResultado) {
		this.booleanResultado = booleanResultado;
	}

	/**
	 * @return the mensagemResultado
	 */
	public String getMensagemResultado() {
		return mensagemResultado;
	}

	/**
	 * @param mensagemResultado the mensagemResultado to set
	 */
	public void setMensagemResultado(String mensagemResultado) {
		this.mensagemResultado = mensagemResultado;
	}
}