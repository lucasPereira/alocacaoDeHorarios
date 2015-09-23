package br.ufsc.sar.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

import br.ufsc.sar.listener.FormularioEspacoListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.List;

/**
 * 
 * @author ErnaniCésar
 *
 */
public class FormularioEspacoGUI extends JPanel {
	private JTextField nome;
	private JTextPane descricao;
	private JTextField capacidade;
	private static AppGUI aplicacaoGUI;
	
	public static AppGUI getAplicacaoGUI() {
		return aplicacaoGUI;
	}

	public static void setAplicacaoGUI(AppGUI aplicacaoGUI) {
		FormularioEspacoGUI.aplicacaoGUI = aplicacaoGUI;
	}

	/**
	 * Create the panel.
	 */
	public FormularioEspacoGUI(AppGUI app) {
		this.aplicacaoGUI = app;
		this.setLayout(null);
		this.setSize(600, 400);
		
		
		JTabbedPane panelEspaco = new JTabbedPane(JTabbedPane.TOP);
		panelEspaco.setBounds(10, 10, 570, 340);
		this.add(panelEspaco);
		
		JPanel panelDadosBasicos = new JPanel();
		panelDadosBasicos.setToolTipText("Dados Básicos");
		panelDadosBasicos.setSize(570, 340);
		panelEspaco.addTab("Dados Básicos", null, panelDadosBasicos, "Dados Básicos");
		
		JPanel panelDadosBasicosConteudo = new JPanel();
		panelDadosBasicosConteudo.setLayout(null);
		panelDadosBasicos.setBounds(10, 11, 200, 200);
		panelDadosBasicos.setLayout(new GridLayout(0, 1, 0, 0));
		panelDadosBasicos.add(panelDadosBasicosConteudo);
		
		
		JLabel lblNome = new JLabel("Nome: ");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setBounds(10, 25, 80, 20);
		lblNome.setToolTipText("Nome do espaço. Deve ser um nome único.");
		panelDadosBasicosConteudo.add(lblNome);
		
		nome = new JTextField();
		nome.setBounds(95, 25, 460, 20);
		panelDadosBasicosConteudo.add(nome);
		nome.setColumns(10);
		
		JLabel lblDescricao = new JLabel("Descrição: ");
		lblDescricao.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescricao.setBounds(10, 50, 80, 20);
		lblDescricao.setToolTipText("Descrição do espaço.");
		panelDadosBasicosConteudo.add(lblDescricao);
		
		descricao = new JTextPane();
		descricao.setBounds(95, 50, 460, 100);
		panelDadosBasicosConteudo.add(descricao);
		
		JLabel lblCapacidade = new JLabel("Capacidade: ");
		lblCapacidade.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCapacidade.setBounds(10, 155, 80, 20);
		lblCapacidade.setToolTipText("Lotação máxima do espaço.");
		panelDadosBasicosConteudo.add(lblCapacidade);
		
		capacidade = new JTextField();
		capacidade.setBounds(95, 155, 150, 20);
		panelDadosBasicosConteudo.add(capacidade);
		capacidade.setColumns(10);
		
		JCheckBox forauso = new JCheckBox("Fora uso?");
		forauso.setBounds(95, 182, 150, 23);
		panelDadosBasicosConteudo.add(forauso);
		
		JPanel panelCaracteristicas = new JPanel();
		panelEspaco.addTab("Características", null, panelCaracteristicas, "Características");
		
		JPanel panelCaracteristicasConteudo = new JPanel();
		panelCaracteristicas.add(panelCaracteristicasConteudo);
		panelCaracteristicasConteudo.setLayout(null);
		panelCaracteristicas.setBounds(10, 11, 200, 200);
		panelCaracteristicas.setLayout(new GridLayout(0, 1, 0, 0));
		panelCaracteristicas.add(panelCaracteristicasConteudo);
		
		List caracteristicas = new List();
		caracteristicas.setBounds(10, 50, 545, 252);
		panelCaracteristicasConteudo.add(caracteristicas);
		
		JLabel lblSelecioneAsCaractersticas = new JLabel("Selecione as características do espaço");
		lblSelecioneAsCaractersticas.setBounds(10, 31, 545, 14);
		panelCaracteristicasConteudo.add(lblSelecioneAsCaractersticas);
		
		JPanel panelAgenda = new JPanel();
		panelEspaco.addTab("Agenda", null, panelAgenda, "Agenda");
		
		JPanel panelDisponibilidade = new JPanel();
		panelEspaco.addTab("Disponibiliade", null, panelDisponibilidade, "Disponibilidade");
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(296, 361, 89, 23);
		btnExcluir.addActionListener(new FormularioEspacoListener(this));
		add(btnExcluir);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(395, 361, 89, 23);
		btnSalvar.addActionListener(new FormularioEspacoListener(this));
		add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(491, 361, 89, 23);
		btnCancelar.addActionListener(new FormularioEspacoListener(this));
		add(btnCancelar);

	}
}
