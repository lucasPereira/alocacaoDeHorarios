package br.ufsc.sar.gui.componentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

import br.ufsc.sar.gui.AppGUI;
import br.ufsc.sar.listener.AppListener;

public class AppMenuBar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7489722476297260548L;
	
	protected static final String SEPARADOR_UNDERSCORE = "____________________________________________________________________________________";
	
	// **************** Opções do Menu ************************************
	private JMenu cadastro = null;
	private JMenu arquivo = null;
	private JMenu ajuda = null;
	
	
	// ***************  Itens de Menu *************************************
	private JMenuItem caracteristica = null;
	private JMenuItem profissional = null;
	private JMenuItem espaco = null;
	private JMenuItem evento = null;
	private JMenuItem sair = null;
	private JMenuItem dicas = null;
	private JMenuItem sobre = null;
	
	private static AppGUI aplicacao = null;
	
	public AppMenuBar(AppGUI app){
		super();
		aplicacao = app;		
		this.add(this.getArquivo());
		this.add(this.getCadastro());
		this.add(this.getAjuda());
		this.setVisible(true);
	}
	
	/**
	 * 
	 * @param entityMenuItem
	 * @param entityName
	 * @param keyEvent
	 * @return
	 */
	private JMenuItem getEntity(JMenuItem entityMenuItem, String entityName, int keyEvent) {
		if(entityMenuItem == null){
			entityMenuItem = new JMenuItem(entityName, keyEvent);
			entityMenuItem.addActionListener(new AppListener(aplicacao));
			this.setVisible(true);
		}
		return entityMenuItem;
	}
	
	//Cadastro->Caracteristica
	public JMenuItem getCaracteristica() {
		if(this.caracteristica == null){
			this.caracteristica = new JMenuItem("Característica",KeyEvent.VK_C);
			this.caracteristica.addActionListener(new AppListener(aplicacao));
			this.setVisible(true);
		}
		return this.caracteristica;
	}
	public void setCaracteristica(JMenuItem caracteristica) {
		this.caracteristica = caracteristica;
	}
		
	//Cadastro->Profissional
		public JMenuItem getProfissional() {
			return getEntity(this.profissional,"Profissional",KeyEvent.VK_P);
		}
		
		public void setProfissional(JMenuItem profissional) {
			this.profissional = profissional;
		}
	
	//Cadastro->Espaço
	public JMenuItem getEspaco() {
		if(this.espaco == null){
			this.espaco = new JMenuItem("Espaço",KeyEvent.VK_E);
			this.espaco.addActionListener(new AppListener(aplicacao));
			this.setVisible(true);
		}
		return this.espaco;
	}
	public void setEspaco(JMenuItem espaco) {
		this.espaco = espaco;
	}
	
	//Cadastro->Evento
	public JMenuItem getEvento() {
		return getEntity(this.evento,"Evento",KeyEvent.VK_N);
	}
	
	public void setEvento(JMenuItem evento) {
		this.evento = evento;
	}

	//Arquivo
	public JMenu getArquivo() {
		if(this.arquivo == null){
			this.arquivo = new JMenu("Arquivo");
			//Cadastro -> Caracteristica
			this.arquivo.add(this.getSair());
		}
		return this.arquivo;
	}
	
	//Ajuda
	public JMenu getAjuda() {
		if(this.ajuda == null){
			this.ajuda = new JMenu("Ajuda");
			//Cadastro -> Caracteristica
			this.ajuda.add(this.getDicas());
			this.ajuda.add(new JSeparator());
			this.ajuda.add(this.getSobre());
		}
		return this.ajuda;
	}

	//Cadastro
	public JMenu getCadastro() {
		if(this.cadastro == null){
			this.cadastro = new JMenu("Cadastro");
			//Cadastro -> Caracteristica
			this.cadastro.add(this.getCaracteristica());
			//Cadastro -> Espaco
			this.cadastro.add(this.getEspaco());
			//Cadastro -> Profissional
			this.cadastro.add(this.getProfissional());	
			this.cadastro.add(new JSeparator());
			//Cadastro -> Eventop
			this.cadastro.add(this.getEvento());
		}
		return this.cadastro;
	}

	public void setCadastro(JMenu cadastro) {
		this.cadastro = cadastro;
	}
	
	public JMenuItem getSair(){
		if(this.sair == null){
			this.sair = new JMenuItem("Sair",KeyEvent.VK_S);
			this.sair.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);					
				}
			});
			this.setVisible(true);
		}
		
		return this.sair;
	}
	
	public JMenuItem getDicas(){
		if(this.dicas == null){
			this.dicas = new JMenuItem("Dicas",KeyEvent.VK_D);
			this.dicas.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "Sistema de Alocação de Recursos\n***************************************\n\n" +
				"1 - Em cadastros de entidades, onde as colunas apresentarem como valores três pontos (\"...\"),\ndê duplo clique e pressione a tecla ENTER em seguida.\n\n"
				+ "Essa ação resultará na edição dos detalhes do cadastro (entidade associada).\n\n"
				+ SEPARADOR_UNDERSCORE + "\n\n"
				+ "2 - Para salvar alterações em registros de tabelas que listam as entidades de um determinado tipo,\n"
				+ "é necessário posicionar o mouse (clicar) em outra coluna diferente da que foi editada por último,\n"
				+ "para refletir as alterações no salvamento (botão \"Alterar\").\n\n"
				+ "Caso contrário, a alteração não será efetivada.\n\n"
				+ SEPARADOR_UNDERSCORE + "\n\n"
				+ "3 - Para excluir registros de tabelas que listam as entidades de um determinado tipo,\n"
				+ "é necessário selecionar pelo menos uma linha.\n\n"
				+ "Caso contrário, a exclusão não será realizada.\n\n"
				, "Dicas de uso", JOptionPane.PLAIN_MESSAGE);				
				}
			});
			this.setVisible(true);
		}
		
		return this.dicas;
	}

	
	public JMenuItem getSobre(){
		if(this.sobre == null){
			this.sobre = new JMenuItem("Sobre",KeyEvent.VK_B);
			this.sobre.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "SAR - Sistema de Alocação de Recursos\n=================================\n\n" +
				"Desenvolvido na disciplina de Desenvolvimento Ágil de Sistemas - 2015/2\n\n"
				+ "Grupo 1:\n-----------\n"				
				+ "Ernani\n"
				+ "Jefferson\n"
				+ "João\n"
				+ "Lucas", "SAR", JOptionPane.PLAIN_MESSAGE);				
				}
			});
			this.setVisible(true);
		}
		
		return this.sobre;
	}
	public static AppGUI getAplicacao() {
		return aplicacao;
	}	
}