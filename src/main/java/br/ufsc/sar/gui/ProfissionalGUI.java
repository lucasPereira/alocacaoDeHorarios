package br.ufsc.sar.gui;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import br.ufsc.sar.gui.componentes.ProfissionalTableModel;
import br.ufsc.sar.listner.EntityTableListener;

public class ProfissionalGUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6514408055127106463L;
	private JTable tabelaProfissionais = null;
	private JScrollPane scrollerTabela = null;
	private JLabel lblCadastroDeProfissionais = null;
	
	private ProfissionalTableModel modeloTabelaProfissionais = null;
	/**
	 * Create the panel.
	 */
	public ProfissionalGUI() {
		super();
		this.initialize();
	}
	
	private void initialize() {
		this.setLayout(null);
		this.add(this.getLblCadastroDeProfissionais());
		this.add(this.getScrollerTabela());
		this.setVisible(true);	
		this.setOpaque(true);
	}

	public JTable getTabelaProfissionais() {
		if(this.tabelaProfissionais == null){
			this.tabelaProfissionais = new JTable();
			this.tabelaProfissionais.setModel(this.getModeloTabelaProfissionais());
			this.tabelaProfissionais.setSurrendersFocusOnKeystroke(true);
			if(this.modeloTabelaProfissionais.hasEmptyRow()){
				modeloTabelaProfissionais.addEmptyRow();
			}
		}
		return this.tabelaProfissionais;
	}
	public void setTabelaProfissionais(JTable tabelaProfissionais) {
		this.tabelaProfissionais = tabelaProfissionais;
	}
	public ProfissionalTableModel getModeloTabelaProfissionais() {
		if(this.modeloTabelaProfissionais == null){
			this.modeloTabelaProfissionais = new ProfissionalTableModel();
			this.modeloTabelaProfissionais.addTableModelListener(new EntityTableListener());
		}
		return this.modeloTabelaProfissionais;
	}
	public void setModeloTabelaProfissionais(
			ProfissionalTableModel modeloTabelaProfissionais) {
		this.modeloTabelaProfissionais = modeloTabelaProfissionais;
	}
	public JScrollPane getScrollerTabela() {
		if(this.scrollerTabela == null){
			this.scrollerTabela = new javax.swing.JScrollPane(this.getTabelaProfissionais());
			this.scrollerTabela.setBounds(10,35,600, 600);
			this.scrollerTabela.setVisible(true);
		}
		return scrollerTabela;
	}
	public void setScrollerTabela(JScrollPane scrollerTabela) {
		this.scrollerTabela = scrollerTabela;
	}
	public JLabel getLblCadastroDeProfissionais() {
		if(this.lblCadastroDeProfissionais == null){
			this.lblCadastroDeProfissionais = new JLabel("Cadastro de Profissionais");
			this.lblCadastroDeProfissionais.setHorizontalAlignment(SwingConstants.LEFT);
			this.lblCadastroDeProfissionais.setFont(new Font("Tahoma", Font.BOLD, 14));
			this.lblCadastroDeProfissionais.setBounds(10, 11, 282, 14);
		}
		return this.lblCadastroDeProfissionais;
	}
	public void setLblCadastroDeProfissionais(JLabel lblCadastroDeProfissionais) {
		this.lblCadastroDeProfissionais = lblCadastroDeProfissionais;
	}
	
	
}
