package br.ufsc.sar.gui;

import java.awt.GridLayout;
import java.awt.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import br.ufsc.sar.gui.componentes.EventoTableModel;
import br.ufsc.sar.listener.EventoListener;
import br.ufsc.sar.listener.FormularioEspacoListener;

/**
 * 
 * @author ErnaniCésar
 * 
 */
public class FormularioEspacoGUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2988815653120904467L;

	private static AppGUI aplicacaoGUI = null;
	

	public static AppGUI getAplicacaoGUI() {
		return aplicacaoGUI;
	}

	public static void setAplicacaoGUI(AppGUI aplicacaoGUI) {
		FormularioEspacoGUI.aplicacaoGUI = aplicacaoGUI;
	}

	private JTable agendaEventos = null;

	private EventoTableModel agendaEventoTableModel = null;

	private JButton btnCancelar = null;

	private JButton btnExcluir = null;

	private JButton btnSalvar = null;

	private JTextField capacidade = null;

	private List caracteristicas = null;

	private JTextPane descricao = null;

	private JCheckBox forauso = null;

	private JTextField id = null;

	private JLabel lblCapacidade = null;

	private JLabel lblDescricao = null;

	private JLabel lblId = null;

	private JLabel lblNome = null;

	private JLabel lblSelecioneAsCaractersticas = null;

	private JTextField nome = null;

	private JPanel panelAgenda = null;

	private JPanel panelAgendaConteudo = null;

	private JPanel panelCaracteristicas = null;

	private JPanel panelCaracteristicasConteudo = null;
	
	private JPanel panelDadosBasicos = null;
	
	private JPanel panelDadosBasicosConteudo = null;
	
	private JTabbedPane panelEspaco = null;
	
	public FormularioEspacoGUI(AppGUI app) {
		FormularioEspacoGUI.aplicacaoGUI = app;
		this.setLayout(null);
		this.setSize(600, 400);
		this.add(this.getPanelEspaco());
		this.add(this.getBtnExcluir());
		this.add(this.getBtnSalvar());
		this.add(this.getBtnCancelar());
	}

	public JTable getAgendaEventos(){
		if(this.agendaEventos == null){
			this.agendaEventos = new JTable(this.getAgendaEventoTableModel());
			this.agendaEventos.doLayout();
		}
		return this.agendaEventos;
	}

	public EventoTableModel getAgendaEventoTableModel() {
		if(this.agendaEventoTableModel == null){
			this.agendaEventoTableModel = new EventoTableModel();
			this.agendaEventoTableModel.addTableModelListener(new EventoListener(new EventoGUI(FormularioEspacoGUI.aplicacaoGUI)).getEntityTableListener());
		}
		return agendaEventoTableModel;
	}

	public JButton getBtnCancelar() {
		if (this.btnCancelar == null) {
			this.btnCancelar = new JButton("Cancelar");
			this.btnCancelar.setBounds(491, 361, 89, 23);
			this.btnCancelar.addActionListener(new FormularioEspacoListener(
					this));
		}
		return this.btnCancelar;
	}

	public JButton getBtnExcluir() {
		if (this.btnExcluir == null) {
			this.btnExcluir = new JButton("Excluir");
			this.btnExcluir.setBounds(296, 361, 89, 23);
			this.btnExcluir
			.addActionListener(new FormularioEspacoListener(this));
		}
		return this.btnExcluir;
	}

	public JButton getBtnSalvar() {
		if (this.btnSalvar == null) {
			this.btnSalvar = new JButton("Salvar");
			this.btnSalvar.setBounds(395, 361, 89, 23);
			this.btnSalvar
			.addActionListener(new FormularioEspacoListener(this));
		}
		return this.btnSalvar;
	}

	public JTextField getCapacidade() {
		if (this.capacidade == null) {
			this.capacidade = new JTextField();
			this.capacidade.setBounds(95, 205, 150, 20);
		}
		return this.capacidade;
	}

	public List getCaracteristicas() {
		if (this.caracteristicas == null) {
			caracteristicas = new List();
			caracteristicas.setBounds(10, 50, 545, 252);
			caracteristicas.setMultipleMode(true);
		}
		return this.caracteristicas;
	}

	public JTextPane getDescricao() {
		if (this.descricao == null) {
			this.descricao = new JTextPane();
			this.descricao.setBounds(95, 94, 460, 100);
		}
		return this.descricao;
	}

	public JCheckBox getForauso() {
		if (this.forauso == null) {
			this.forauso = new JCheckBox("Fora uso?");
			this.forauso.setBounds(95, 232, 150, 23);
		}
		return this.forauso;
	}

	public JTextField getId() {
		if (this.id == null) {
			this.id = new JTextField();
			this.id.setBounds(93, 37, 86, 20);
			this.id.setEditable(true);
		}
		return this.id;
	}

	public JLabel getLblCapacidade() {
		if (this.lblCapacidade == null) {
			this.lblCapacidade = new JLabel("Capacidade: ");
			this.lblCapacidade.setHorizontalAlignment(SwingConstants.RIGHT);
			this.lblCapacidade.setBounds(10, 205, 80, 20);
			this.lblCapacidade.setToolTipText("Lotação máxima do espaço.");
		}
		return this.lblCapacidade;
	}

	public JLabel getLblDescricao() {
		if (this.lblDescricao == null) {
			this.lblDescricao = new JLabel("Descrição: ");
			this.lblDescricao.setHorizontalAlignment(SwingConstants.RIGHT);
			this.lblDescricao.setBounds(10, 94, 80, 20);
			this.lblDescricao.setToolTipText("Descrição do espaço.");
		}
		return this.lblDescricao;
	}

	public JLabel getLblId() {
		if (this.lblId == null) {
			this.lblId = new JLabel("Id:");
			this.lblId.setBounds(44, 40, 46, 14);
			this.lblId.setToolTipText("Chave de identificação do espaço.");
		}
		return this.lblId;
	}

	public JLabel getLblNome() {
		if (this.lblNome == null) {
			this.lblNome = new JLabel("Nome: ");
			this.lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
			this.lblNome.setBounds(10, 63, 80, 20);
			this.lblNome.setToolTipText("Nome do espaço. Deve ser um nome único.");
		}
		return lblNome;
	}

	public JLabel getLblSelecioneAsCaractersticas() {
		if (this.lblSelecioneAsCaractersticas == null) {
			this.lblSelecioneAsCaractersticas = new JLabel(
					"Selecione as características do espaço");
			this.lblSelecioneAsCaractersticas.setBounds(10, 31, 545, 14);
		}
		return this.lblSelecioneAsCaractersticas;
	}

	public JTextField getNome() {
		if (this.nome == null) {
			this.nome = new JTextField();
			this.nome.setBounds(95, 63, 460, 20);
		}
		return this.nome;
	}

	public JPanel getPanelAgenda() {
		if (this.panelAgenda == null) {
			this.panelAgenda = new JPanel();
			this.panelAgenda.setToolTipText("Agenda");
			this.panelAgenda.setSize(570, 340);
			this.panelAgenda.setBounds(10, 11, 200, 200);
			this.panelAgenda.setLayout(new GridLayout(0, 1, 0, 0));
			this.panelAgenda.add(this.getPanelAgendaConteudo());
		}
		return this.panelAgenda;
	}

	public JPanel getPanelAgendaConteudo(){
		if (this.panelAgendaConteudo == null) {
			this.panelAgendaConteudo = new JPanel();
			this.panelAgendaConteudo.setLayout(null);
			this.panelAgenda.add(this.getAgendaEventos());
		}
		return this.panelAgendaConteudo;
	}

	public JPanel getPanelCaracteristicas() {
		if (this.panelCaracteristicas == null) {
			this.panelCaracteristicas = new JPanel();
			this.panelCaracteristicas.setBounds(10, 11, 200, 200);
			this.panelCaracteristicas.setLayout(new GridLayout(0, 1, 0, 0));
			this.panelCaracteristicas.add(this.getPanelCaracteristicasConteudo());
		}
		return this.panelCaracteristicas;
	}

	public JPanel getPanelCaracteristicasConteudo() {
		if (this.panelCaracteristicasConteudo == null) {
			this.panelCaracteristicasConteudo = new JPanel();
			this.panelCaracteristicasConteudo.setLayout(null);
			
			this.panelCaracteristicasConteudo.add(this.getLblSelecioneAsCaractersticas());
			this.panelCaracteristicasConteudo.add(this.getCaracteristicas());
			
		}
		return this.panelCaracteristicasConteudo;
	}
	
	public JPanel getPanelDadosBasicos() {
		if (this.panelDadosBasicos == null) {
			this.panelDadosBasicos = new JPanel();
			this.panelDadosBasicos.setToolTipText("Dados Básicos");
			this.panelDadosBasicos.setSize(570, 340);
			this.panelDadosBasicos.setBounds(10, 11, 200, 200);
			this.panelDadosBasicos.setLayout(new GridLayout(0, 1, 0, 0));
			this.panelDadosBasicos.add(this.getPanelDadosBasicosConteudo());
		}
		return this.panelDadosBasicos;
	}
	
	public JPanel getPanelDadosBasicosConteudo() {
		if (this.panelDadosBasicosConteudo == null) {
			this.panelDadosBasicosConteudo = new JPanel();
			this.panelDadosBasicosConteudo.setLayout(null);
			
			this.panelDadosBasicosConteudo.add(this.getLblCapacidade());
			this.panelDadosBasicosConteudo.add(this.getCapacidade());
			
			this.panelDadosBasicosConteudo.add(this.getLblDescricao());
			this.panelDadosBasicosConteudo.add(this.getDescricao());
			
			this.panelDadosBasicosConteudo.add(this.getLblNome());
			this.panelDadosBasicosConteudo.add(this.getNome());
			
			this.panelDadosBasicosConteudo.add(this.getForauso());
			
			this.panelDadosBasicosConteudo.add(this.getLblId());
			this.panelDadosBasicosConteudo.add(this.getId());
			
		}
		return this.panelDadosBasicosConteudo;
	}

	public JTabbedPane getPanelEspaco() {
		if (this.panelEspaco == null) {
			this.panelEspaco = new JTabbedPane(JTabbedPane.TOP);
			this.panelEspaco.setBounds(10, 10, 570, 340);
			
			this.panelEspaco.addTab("Dados Básicos", null,	this.getPanelDadosBasicos(), "Dados Básicos");
			this.panelEspaco.addTab("Características", null, this.getPanelCaracteristicas(), "Características");
			this.panelEspaco.addTab("Agenda", null, this.getPanelAgenda(),"Agenda");
		}
		return this.panelEspaco;
	}

	public void setAgendaEventos(JTable agendaEventos) {
		this.agendaEventos = agendaEventos;
	}

	public void setAgendaEventoTableModel(EventoTableModel agendaEventoTableModel) {
		this.agendaEventoTableModel = agendaEventoTableModel;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public void setBtnExcluir(JButton btnExcluir) {
		this.btnExcluir = btnExcluir;
	}

	public void setBtnSalvar(JButton btnSalvar) {
		this.btnSalvar = btnSalvar;
	}

	public void setCapacidade(JTextField capacidade) {
		this.capacidade = capacidade;
	}

	public void setCaracteristicas(List list) {
		this.caracteristicas = list;
	}

	public void setDescricao(JTextPane descricao) {
		this.descricao = descricao;
	}

	public void setForauso(JCheckBox forauso) {
		this.forauso = forauso;
	}

	public void setId(JTextField id) {
		this.id = id;
	}

	public void setLblCapacidade(JLabel lblCapacidade) {
		this.lblCapacidade = lblCapacidade;
	}

	public void setLblDescricao(JLabel lblDescricao) {
		this.lblDescricao = lblDescricao;
	}

	public void setLblId(JLabel lblId) {
		this.lblId = lblId;
	}

	public void setLblNome(JLabel lblNome) {
		this.lblNome = lblNome;
	}

	public void setLblSelecioneAsCaractersticas(
			JLabel lblSelecioneAsCaractersticas) {
		this.lblSelecioneAsCaractersticas = lblSelecioneAsCaractersticas;
	}

	public void setNome(JTextField nome) {
		this.nome = nome;
	}

	public void setPanelAgenda(JPanel panelAgenda) {
		this.panelAgenda = panelAgenda;
	}

	public void setPanelAgendaConteudo(JPanel panelAgendaConteudo) {
		this.panelAgendaConteudo = panelAgendaConteudo;
	}

	public void setPanelCaracteristicas(JPanel panelCaracteristicas) {
		this.panelCaracteristicas = panelCaracteristicas;
	}

	public void setPanelCaracteristicasConteudo(
			JPanel panelCaracteristicasConteudo) {
		this.panelCaracteristicasConteudo = panelCaracteristicasConteudo;
	}

	public void setPanelDadosBasicos(JPanel panelDadosBasicos) {
		this.panelDadosBasicos = panelDadosBasicos;
	}

	public void setPanelDadosBasicosConteudo(JPanel panelDadosBasicosConteudo) {
		this.panelDadosBasicosConteudo = panelDadosBasicosConteudo;
	}

	public void setPanelEspaco(JTabbedPane panelEspaco) {
		this.panelEspaco = panelEspaco;
	}
}
