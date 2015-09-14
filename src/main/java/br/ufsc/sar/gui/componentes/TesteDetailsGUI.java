package br.ufsc.sar.gui.componentes;

import java.awt.EventQueue;
import java.util.Date;

import br.ufsc.sar.entity.Profissional;

// FIXME: REMOVER. Criada apenas para testes
public class TesteDetailsGUI {	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("unused")
			public void run() {
				try {
					TesteDetailsGUI window = new TesteDetailsGUI();
					//window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TesteDetailsGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "deprecation", "unused" })
	private void initialize() {
		Profissional profissional = new Profissional();
		profissional.setId(Long.parseLong("1000"));
		profissional.setNome("Teste 1");
		profissional.setDtnascimento(new Date(1982, 7, 12));
		ProfissionalDetailsGUI pdg = new ProfissionalDetailsGUI(profissional);		
	}
}
