package br.ufsc.sar.gui.componentes;

import br.ufsc.sar.entity.Profissional;


public class ProfissionalDetailsGUI extends EntityDetailsGUI<Profissional>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 608098269898547296L;

	/**
	 * Create the application.
	 */
	public ProfissionalDetailsGUI(Profissional profisional) {
		super("Cadastro de " + Profissional.class.getSimpleName());
		initialize(profisional);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Profissional profissional) {
	    String[] labels = {"ID:",
				"Nome:",
				"Profissão:", 
				"Data de Nascimento:",
				"Telefone:",
				"CPF:"};
		
		Class[] classes = {Long.class, String.class, String.class, java.util.Date.class, String.class, String.class};
		
		Boolean[] editables = {false, true, true, true, true, true};
				
		Object [] values = {profissional.getId(), profissional.getNome(), profissional.getProfissao(), profissional.getDtnascimento(),
							profissional.getTelefone(), profissional.getCpf()};
		
		generateEntityGUI(labels, classes, editables, values);
		
	}
}
