package br.ufsc.sar.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTable;
import javax.swing.SwingConstants;

import br.ufsc.sar.gui.componentes.InteractiveTableCaracteristica;
import br.ufsc.sar.listner.InteractiveTableCaracteristicaListener;

public class CaracteristicaGUI extends JPanel {

	private JTable tabelaCaracteristicas = null;
	private JScrollPane scrollerTabela = null;
	private JLabel lblCadastroDeCaractersticas = null;
	
	private InteractiveTableCaracteristica modeloTabelaCaracteristicas = null;
	/**
	 * Create the panel.
	 */
	public CaracteristicaGUI() {
		super();
		this.initialize();
	}
	
	private void initialize() {
		this.setLayout(null);
		this.add(this.getLblCadastroDeCaractersticas());
		this.add(this.getScrollerTabela());
		this.setVisible(true);	
	}

	public JTable getTabelaCaracteristicas() {
		if(this.tabelaCaracteristicas == null){
			this.tabelaCaracteristicas = new JTable();
			this.tabelaCaracteristicas.setModel(this.getModeloTabelaCaracteristicas());
			this.tabelaCaracteristicas.setSurrendersFocusOnKeystroke(true);
			if(this.modeloTabelaCaracteristicas.hasEmptyRow()){
				modeloTabelaCaracteristicas.addEmptyRow();
			}
		}
		return this.tabelaCaracteristicas;
	}
	public void setTabelaCaracteristicas(JTable tabelaCaracteristicas) {
		this.tabelaCaracteristicas = tabelaCaracteristicas;
	}
	public InteractiveTableCaracteristica getModeloTabelaCaracteristicas() {
		if(this.modeloTabelaCaracteristicas == null){
			this.modeloTabelaCaracteristicas = new InteractiveTableCaracteristica();
			this.modeloTabelaCaracteristicas.addTableModelListener(new InteractiveTableCaracteristicaListener());
		}
		return this.modeloTabelaCaracteristicas;
	}
	public void setModeloTabelaCaracteristicas(
			InteractiveTableCaracteristica modeloTabelaCaracteristicas) {
		this.modeloTabelaCaracteristicas = modeloTabelaCaracteristicas;
	}
	public JScrollPane getScrollerTabela() {
		if(this.scrollerTabela == null){
			this.scrollerTabela = new javax.swing.JScrollPane(this.getTabelaCaracteristicas());
			this.scrollerTabela.setBounds(10,35,600, 600);
			this.scrollerTabela.setVisible(true);
		}
		return scrollerTabela;
	}
	public void setScrollerTabela(JScrollPane scrollerTabela) {
		this.scrollerTabela = scrollerTabela;
	}
	public JLabel getLblCadastroDeCaractersticas() {
		if(this.lblCadastroDeCaractersticas == null){
			this.lblCadastroDeCaractersticas = new JLabel("Cadastro de Características");
			this.lblCadastroDeCaractersticas.setHorizontalAlignment(SwingConstants.LEFT);
			this.lblCadastroDeCaractersticas.setFont(new Font("Tahoma", Font.BOLD, 14));
			this.lblCadastroDeCaractersticas.setBounds(10, 11, 282, 14);
		}
		return this.lblCadastroDeCaractersticas;
	}
	public void setLblCadastroDeCaractersticas(JLabel lblCadastroDeCaractersticas) {
		this.lblCadastroDeCaractersticas = lblCadastroDeCaractersticas;
	}
	
	
}
