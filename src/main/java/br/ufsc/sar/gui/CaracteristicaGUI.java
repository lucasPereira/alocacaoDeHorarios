package br.ufsc.sar.gui;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.MenuItem;
import java.awt.PopupMenu;

import javax.swing.JTable;
import javax.swing.SwingConstants;

import br.ufsc.sar.entity.Caracteristica;
import br.ufsc.sar.gui.componentes.InteractiveTableCaracteristica;
import br.ufsc.sar.listner.CaracteristicaListner;
import br.ufsc.sar.listner.InteractiveTableCaracteristicaListener;
import br.ufsc.sar.service.CaracteristicaService;
import br.ufsc.sar.serviceimpl.CaracteristicaServiceImpl;

public class CaracteristicaGUI extends JPanel {

	private JTable tabelaCaracteristicas = null;
	private JScrollPane scrollerTabela = null;
	private JLabel lblCadastroDeCaractersticas = null;
	private JButton btNovo = null;
	
	private InteractiveTableCaracteristica modeloTabelaCaracteristicas = null;
	
	private static final CaracteristicaService caracteristicaService = new CaracteristicaServiceImpl();
	private static AppGUI aplicacaoGUI = null;
	
	/**
	 * Create the panel.
	 */
	public CaracteristicaGUI(AppGUI app) {
		super();
		CaracteristicaGUI.aplicacaoGUI = app;
		this.initialize();
	}
	
	private void initialize() {
		this.setLayout(null);
		this.add(this.getLblCadastroDeCaractersticas());
		this.add(this.getBtNovo());
		this.add(this.getScrollerTabela());
		this.setVisible(true);	
	}

	public JTable getTabelaCaracteristicas() {
		if(this.tabelaCaracteristicas == null){
			this.tabelaCaracteristicas = new JTable();
			this.tabelaCaracteristicas.setModel(this.getModeloTabelaCaracteristicas());
			this.tabelaCaracteristicas.setSurrendersFocusOnKeystroke(true);
			if(this.modeloTabelaCaracteristicas.hasEmptyRow()){
				modeloTabelaCaracteristicas.addEmptyRow(new Caracteristica());
			}
		}
		return this.tabelaCaracteristicas;
	}
	public void setTabelaCaracteristicas(JTable tabelaCaracteristicas) {
		this.tabelaCaracteristicas = tabelaCaracteristicas;
	}
	public InteractiveTableCaracteristica getModeloTabelaCaracteristicas() {
		if(this.modeloTabelaCaracteristicas == null){
			this.modeloTabelaCaracteristicas = new InteractiveTableCaracteristica(caracteristicaService.getList());
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
			this.scrollerTabela = new JScrollPane(this.getTabelaCaracteristicas());
			this.scrollerTabela.setBounds(10,35,600,400);
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

	public JButton getBtNovo() {
		if(this.btNovo == null){
			
			this.btNovo = new JButton();
			this.btNovo.setText("Novo");
			this.btNovo.addActionListener(new CaracteristicaListner(this));
			this.btNovo.setBounds(500,11,100,22);
		}
		return this.btNovo ;
	}

	public void setBtNovo(JButton btNovo) {
		this.btNovo = btNovo;
	}

	public static AppGUI getAplicacaoGUI() {
		return aplicacaoGUI;
	}
	
	
}
