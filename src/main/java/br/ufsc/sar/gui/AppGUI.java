package br.ufsc.sar.gui;

import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JLabel;

import br.ufsc.sar.gui.componentes.AppMenuBar;

public class AppGUI extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8980822524029614029L;
	private AppMenuBar menu = null;
	private JLabel lbBemVindo = null;

	public AppGUI() {
		super("SAR - Sistema de Alocação de Recursos");
		initialize();
	}
	
	public void initialize(){
		this.getContentPane().add(this.getLbBemVindo());
		super.setSize(800, 600);
		this.setJMenuBar(this.getMenu());
		this.setVisible(true);
	}


	public AppMenuBar getMenu() {
		if(this.menu == null){
			this.menu = new AppMenuBar(this);
		}
		return menu;
	}

	public void setMenu(AppMenuBar menu) {
		this.menu = menu;
	}

	public JLabel getLbBemVindo() {
		if(this.lbBemVindo == null){
			this.lbBemVindo = new JLabel("Sistema de Alocação de Recursos");
			this.setPreferredSize(getMaximumSize());
		}
		return lbBemVindo;
	}

	public void setLbBemVindo(JLabel lbBemVindo) {
		this.lbBemVindo = lbBemVindo;
	}
	
	
}
