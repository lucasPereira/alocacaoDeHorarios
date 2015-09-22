package br.ufsc.sar.gui.componentes;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import br.ufsc.sar.gui.AppGUI;
import br.ufsc.sar.listener.AppListener;

public class AppMenuBar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7489722476297260548L;
	// **************** Opções do Menu ************************************
	private JMenu cadastro = null;
	
	
	// ***************  Itens de Menu *************************************
	private JMenuItem caracteristica = null;
	private JMenuItem profissional = null;
	private JMenuItem espaco = null;
	
	private static AppGUI aplicacao = null;
	
	public AppMenuBar(AppGUI app){
		super();
		aplicacao = app;
		this.add(this.getCadastro());
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
		}
		return this.cadastro;
	}

	public void setCadastro(JMenu cadastro) {
		this.cadastro = cadastro;
	}

	public static AppGUI getAplicacao() {
		return aplicacao;
	}	
}