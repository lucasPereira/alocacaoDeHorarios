package br.ufsc.sar.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class EspacoGUI extends JPanel {
	private JTextField nome;

	/**
	 * Create the panel.
	 */
	public EspacoGUI() {
		setLayout(null);
		
		JTabbedPane panelEspaco = new JTabbedPane(JTabbedPane.TOP);
		panelEspaco.setBounds(10, 11, 400, 600);
		add(panelEspaco);
		
		JPanel panelDadosBasicos = new JPanel();
		panelDadosBasicos.setToolTipText("Dados Básicos");
		panelEspaco.addTab("Dados Básicos", null, panelDadosBasicos, "Dados Básicos");
		panelDadosBasicos.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panelDadosBasicosConteudo = new JPanel();
		panelDadosBasicos.add(panelDadosBasicosConteudo);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setToolTipText("Nome");
		panelDadosBasicosConteudo.add(lblNome);
		
		nome = new JTextField();
		panelDadosBasicosConteudo.add(nome);
		nome.setColumns(10);
		
		JLabel lblDescricao = new JLabel("Descrição");
		panelDadosBasicosConteudo.add(lblDescricao);
		
		JTextPane descricao = new JTextPane();
		panelDadosBasicosConteudo.add(descricao);
		
		JLabel lblCapacidade = new JLabel("Capacidade");
		panelDadosBasicosConteudo.add(lblCapacidade);
		
		JPanel panelCaracteristicas = new JPanel();
		panelEspaco.addTab("Características", null, panelCaracteristicas, "Características");
		
		JPanel panelAgenda = new JPanel();
		panelEspaco.addTab("Agenda", null, panelAgenda, "Agenda");
		
		JPanel panelDisponibilidade = new JPanel();
		panelEspaco.addTab("Disponibiliade", null, panelDisponibilidade, "Disponibilidade");

	}

}
