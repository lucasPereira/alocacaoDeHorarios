package br.ufsc.sar.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import br.ufsc.sar.controller.FormularioEventoController;
import br.ufsc.sar.entity.Evento;
import br.ufsc.sar.entity.Profissional;
import br.ufsc.sar.gui.componentes.CaracteristicaTableModel;
import br.ufsc.sar.gui.componentes.EspacoTableModel;
import br.ufsc.sar.gui.componentes.EventoCaracteristicaTableModel;
import br.ufsc.sar.gui.componentes.EventoEspacoTableModel;
import br.ufsc.sar.gui.componentes.EventoProfissionalTableModel;
import br.ufsc.sar.gui.componentes.HorarioEventoTableModel;
import br.ufsc.sar.gui.componentes.ProfissionalTableModel;
import br.ufsc.sar.listener.FormularioEventoListener;

/**
 * 
 * @author ErnaniCésar
 *
 */
public class FormularioEventoGUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8235049353933462299L;
	
	public static final String GUI_LABEL = "Detalhes do Evento";
	
	public static final String SUFIXO_ID_PROFISSIONAL = "*|) - ";

	public static final String PREFIXO_ID_PROFISSIONAL = "(|*";
	
	public static final String LABEL_BOTAO_ADICIONAR_AO_EVENTO = "Adicionar ao Evento";
	
	public static final String LABEL_BOTAO_EXCLUIR_DO_EVENTO = "Excluir do Evento";
	
	private JTextField nome;
	
	private JTextPane descricao;	
	
	private JTextField capacidade;
	
	private JTable tabelaProfissionais;
	
	private JTable tabelaCaracteristicas;
	
	private JTable tabelaEspacos;
	
	private JTable tabelaProfissionaisEvento;
	
	private JTable tabelaCaracteristicasEvento;
	
	private JTable tabelaEspacosEvento;
	
	private JTable tabelaHorariosEvento;
	
	private static AppGUI aplicacaoGUI;
	
	private JList<String> profissionais;
	
	private JList<String> caracteristicas;
	
	private JList<String> espacos;
	
	private FormularioEventoController formularioEventoController;
	
	private EventoProfissionalTableModel modeloTabelaProfissionaisEvento;
	
	private ProfissionalTableModel modeloTabelaProfissionais;
	
	private EventoCaracteristicaTableModel modeloTabelaCaracteristicasEvento;
	
	private CaracteristicaTableModel modeloTabelaCaracteristicas;
	
	private EventoEspacoTableModel modeloTabelaEspacosEvento;
	
	private EspacoTableModel modeloTabelaEspacos;
	
	private HorarioEventoTableModel modeloTabelaHorariosEvento;
	
	private java.util.List<String> listaProfissionaisSelecionados;
	
	private java.util.List<String> listaCaracteristicasSelecionadas;
	
	private java.util.List<String> listaEspacosSelecionados;
	
	private DefaultListModel<String> listModel;
	
	public static AppGUI getAplicacaoGUI() {
		return aplicacaoGUI;
	}

	public static void setAplicacaoGUI(AppGUI aplicacaoGUI) {
		FormularioEventoGUI.aplicacaoGUI = aplicacaoGUI;
	}

	/**
	 * Create the panel.
	 */
	@SuppressWarnings("static-access")
	public FormularioEventoGUI(AppGUI app, Evento evento) {
		this.aplicacaoGUI = app;
		this.formularioEventoController = new FormularioEventoController(this, evento);
		this.setLayout(null);
		this.setSize(600, 450);		
		
		JLabel lblTitulo = new JLabel(GUI_LABEL);
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setBounds(10, 5, 180, 20);
		this.add(lblTitulo);
		
		JTabbedPane panelEvento = new JTabbedPane(JTabbedPane.TOP);
		panelEvento.setBounds(10, 30, 570, 340);
		this.add(panelEvento);
		
		JPanel panelDadosBasicos = new JPanel();
		panelDadosBasicos.setToolTipText("Dados Básicos");
		panelDadosBasicos.setSize(570, 340);
		panelEvento.addTab("Dados Básicos", null, panelDadosBasicos, "Dados Básicos");
		
		JPanel panelDadosBasicosConteudo = new JPanel();
		panelDadosBasicosConteudo.setLayout(null);
		panelDadosBasicos.setBounds(10, 11, 200, 200);
		panelDadosBasicos.setLayout(new GridLayout(0, 1, 0, 0));
		panelDadosBasicos.add(panelDadosBasicosConteudo);		
		
		JLabel lblNome = new JLabel("Nome: ");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setBounds(10, 25, 80, 20);
		lblNome.setToolTipText("Nome do evento. Deve ser um nome único.");
		panelDadosBasicosConteudo.add(lblNome);
		
		nome = new JTextField();
		nome.setBounds(95, 25, 460, 20);
		panelDadosBasicosConteudo.add(nome);
		nome.setColumns(10);
		
		JLabel lblDescricao = new JLabel("Descrição: ");
		lblDescricao.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescricao.setBounds(10, 50, 80, 20);
		lblDescricao.setToolTipText("Descrição do evento.");
		panelDadosBasicosConteudo.add(lblDescricao);
		
		descricao = new JTextPane();
		descricao.setBounds(95, 50, 460, 100);
		panelDadosBasicosConteudo.add(descricao);
		
		JPanel panelCaracteristicasEvento = new JPanel();
		panelEvento.addTab("Características do Evento", null, panelCaracteristicasEvento, "Características do Evento");	
		panelCaracteristicasEvento.setBounds(10, 50, 545, 252);
		createAbaCaracteristicasEvento(panelCaracteristicasEvento);
		
		JPanel panelCaracteristicas = new JPanel();
		panelEvento.addTab("Características", null, panelCaracteristicas, "Características");		
		panelCaracteristicas.setBounds(10, 50, 545, 252);
		createAbaCaracteristicas(panelCaracteristicas);
		
		JPanel panelProfissionaisEvento = new JPanel();
		panelEvento.addTab("Profissionais do Evento", null, panelProfissionaisEvento, "Profissionais do Evento");	
		panelProfissionaisEvento.setBounds(10, 50, 545, 252);
		createAbaProfissionaisEvento(panelProfissionaisEvento);
		
		JPanel panelProfissionais = new JPanel();
		panelEvento.addTab("Profissionais", null, panelProfissionais, "Profissionais");		
		panelProfissionais.setBounds(10, 50, 545, 252);
		//createAbaProfissionais(panelProfissionais);
		createAbaProfissionaisTabela(panelProfissionais);
		
		JPanel panelEspacosEvento = new JPanel();
		panelEvento.addTab("Espaços do Evento", null, panelEspacosEvento, "Espaços do Evento");	
		panelEspacosEvento.setBounds(10, 50, 545, 252);
		createAbaEspacosEvento(panelEspacosEvento);
		
		JPanel panelEspacos = new JPanel();
		panelEvento.addTab("Espaços", null, panelEspacos, "Espaços");		
		panelEspacos.setBounds(10, 50, 545, 252);
		createAbaEspacos(panelEspacos);
		
		JPanel panelAgenda = new JPanel();
		panelEvento.addTab("Agenda", null, panelAgenda, "Agenda");
		panelAgenda.setBounds(10, 50, 545, 252);
		createAbaAgenda(panelAgenda);
		
		JPanel panelDisponibilidade = new JPanel();
		panelEvento.addTab("Disponibiliade", null, panelDisponibilidade, "Disponibilidade");
		
//		JButton btnExcluir = new JButton("Excluir");
//		btnExcluir.setBounds(296, 381, 89, 23);
//		btnExcluir.addActionListener(new FormularioEventoListener(this));
//		add(btnExcluir);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(395, 381, 89, 23);
		btnSalvar.addActionListener(new FormularioEventoListener(this));
		add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(491, 381, 89, 23);
		btnCancelar.addActionListener(new FormularioEventoListener(this));
		add(btnCancelar);
		
		preencherValores();
	}

	private void preencherValores(){
		this.nome.setText(this.formularioEventoController.getEvento().getNome());
		this.descricao.setText(this.formularioEventoController.getEvento().getDescricao());
	}
	
	public FormularioEventoController getController() {
		return this.formularioEventoController;
	}	
	
	public JTextField getCampoNome() {
		return nome;
	}

	public JTextPane getCampoDescricao() {
		return descricao;
	}

	public JTextField getCampoCapacidade() {
		return capacidade;
	}
	
	
	// Profissionais	
	
	public void setProfissionaisDisponiveis(java.util.List<String> lista){		
		profissionais.setListData((String[])lista.toArray());
	}
	
	public void setProfissionaisEvento(java.util.List<String> lista){		
		profissionais.setListData((String[])lista.toArray());
	}
	
	public JList<String> getJListProfissionais() {
		return profissionais;
	}
	
	public java.util.List<String> getListaProfissionaisSelecionados() {
		return listaProfissionaisSelecionados;
	}
	
	public int[] getTabelaProfissionaisSelecionados() {
		return this.tabelaProfissionais.getSelectedRows();
	}
	
	public JTable getTabelaProfissionais() {
		return this.tabelaProfissionais;
	}

	public JList<String> getProfissionais() {
		return profissionais;
	}

	public DefaultListModel<String> getListModel() {
		return listModel;
	}

	public JTable getTabelaProfissionaisEvento() {
		return tabelaProfissionaisEvento;
	}
	
	public EventoProfissionalTableModel getModeloTabelaProfissionaisEvento() {
		if(this.modeloTabelaProfissionaisEvento == null){
			this.modeloTabelaProfissionaisEvento = (EventoProfissionalTableModel)new EventoProfissionalTableModel(null);
			//this.modeloTabelaHorarios.addTableModelListener(modeloTabelaHorarios().getEntityTableListener());
			this.getController().buscarProfissionaisDoEvento();
		}
		
		if(this.modeloTabelaProfissionaisEvento.hasEmptyRow()){
			modeloTabelaProfissionaisEvento.addEmptyRow();
		}
		
		return this.modeloTabelaProfissionaisEvento;
	}
	
	public ProfissionalTableModel getModeloTabelaProfissionais() {
		if(this.modeloTabelaProfissionais == null){
			this.modeloTabelaProfissionais = (ProfissionalTableModel)new ProfissionalTableModel(false);
			this.getController().buscarProfissionais();
		}
		
		if(this.modeloTabelaProfissionais.hasEmptyRow()){
			modeloTabelaProfissionais.addEmptyRow();
		}
		
		return this.modeloTabelaProfissionais;
	}	
	
	private void createAbaProfissionaisEvento(JPanel panelProfissionaisEvento) {	
		
		JLabel lblProfissionaisEvento = new JLabel("Profissionais do Evento");
		panelProfissionaisEvento.add(lblProfissionaisEvento);		
				
		tabelaProfissionaisEvento = new JTable();
		tabelaProfissionaisEvento.setModel(getModeloTabelaProfissionaisEvento());
		tabelaProfissionaisEvento.setSurrendersFocusOnKeystroke(true);
		tabelaProfissionaisEvento.addMouseListener(new MouseAdapter() {
			//   @SuppressWarnings("unused")
			public void mouseClicked(MouseEvent e) {
//			      if (e.getClickCount() == 2) {
//			         JTable target = (JTable)e.getSource();
//			         int row = target.getSelectedRow();
//			         int column = target.getSelectedColumn();
//			         JFrame newFrame = new JFrame();
//			         newFrame.setTitle("Detail Screen");
//			         newFrame.setVisible(true);
//			      }
			   }
			});
		
		JScrollPane scrollerTabela = new JScrollPane(tabelaProfissionaisEvento);
		scrollerTabela.setPreferredSize(new Dimension(450, 200));
		scrollerTabela.setVisible(true);
		panelProfissionaisEvento.add(scrollerTabela);
		
		JButton excluir = new JButton(LABEL_BOTAO_EXCLUIR_DO_EVENTO);
		excluir.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				getController().excluirProfissionalEvento();
				
			}
		});
		panelProfissionaisEvento.add(excluir);
		
		
//		JPanel panelProfissionaisConteudo = new JPanel();
//		panelProfissionais.add(panelProfissionaisConteudo);
//		panelProfissionaisConteudo.setLayout(null);
//		panelProfissionais.setBounds(10, 11, 200, 200);
//		panelProfissionais.setLayout(new GridLayout(0, 1, 0, 0));
//		panelProfissionais.add(panelProfissionaisConteudo);
		
		
		
//		JLabel lblSelecioneProfissionais = new JLabel("Selecione os Profissionais do Evento");
//		lblSelecioneProfissionais.setBounds(10, 31, 545, 14);
//		panelProfissionaisConteudo.add(lblSelecioneProfissionais);
		
		
 
		
//		JScrollPane teste = new JScrollPane(profissionais);
//		panelProfissionais.add(teste);
		
//		List profissionaisList = new List();
//		profissionaisList.setBounds(10, 50, 545, 252);
//		profissionaisList.add("Teste 1");
//		profissionaisList.add("Teste 2");
//		panelProfissionaisConteudo.add(profissionaisList);
		
		
		
		
       // panelProfissionaisConteudo.add(new JScrollPane(profissionais));
 
	}
	
	private void createAbaProfissionaisTabela(JPanel panelProfissionais) {			
		JLabel lblProfissionaisEvento = new JLabel("Profissionais");
		panelProfissionais.add(lblProfissionaisEvento);		
				
		tabelaProfissionais = new JTable();
		tabelaProfissionais.setModel(getModeloTabelaProfissionais());
		tabelaProfissionais.setSurrendersFocusOnKeystroke(true);
		tabelaProfissionais.addMouseListener(new MouseAdapter() {
			//   @SuppressWarnings("unused")
			public void mouseClicked(MouseEvent e) {
//			      if (e.getClickCount() == 2) {
//			         JTable target = (JTable)e.getSource();
//			         int row = target.getSelectedRow();
//			         int column = target.getSelectedColumn();
//			         JFrame newFrame = new JFrame();
//			         newFrame.setTitle("Detail Screen");
//			         newFrame.setVisible(true);
//			      }
			   }
			});
		
		JScrollPane scrollerTabela = new JScrollPane(tabelaProfissionais);
		scrollerTabela.setPreferredSize(new Dimension(450, 200));
		scrollerTabela.setVisible(true);
		panelProfissionais.add(scrollerTabela); 
		
		JButton adicionar = new JButton(LABEL_BOTAO_ADICIONAR_AO_EVENTO);
        adicionar.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				getController().salvarProfissionalEvento();
				
			}
		});
        panelProfissionais.add(adicionar);
	}
		
	/**
	 * 
	 * @param panelProfissionais
	 */
	@Deprecated
	public void createAbaProfissionais(JPanel panelProfissionais){		
		JLabel lblProfissionais = new JLabel("Profissionais");
		panelProfissionais.add(lblProfissionais);		
		
		 //create the model and add elements
        listModel = new DefaultListModel<>();
        java.util.List<Profissional> listaProfissionais = getController().getListaProfissionaisDisponiveis();
        for (Profissional profissional : listaProfissionais) {
        	 listModel.addElement(PREFIXO_ID_PROFISSIONAL + profissional.getId() + SUFIXO_ID_PROFISSIONAL + profissional.getNome() + " - " + profissional.getProfissao());
		}
        
        //create the list
        profissionais = new JList<>(listModel);
        profissionais.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                	listaProfissionaisSelecionados = profissionais.getSelectedValuesList();
//                    //System.out.println(profissionais.getSelectedValuesList());
                }
            }
        });
        
        profissionais.setPreferredSize(new Dimension(250, listModel.getSize()*19));
        JScrollPane scrollerTabela = new JScrollPane(profissionais);
		scrollerTabela.setPreferredSize(new Dimension(350, 200));
		scrollerTabela.setVisible(true);
        panelProfissionais.add(scrollerTabela);
        
        JButton adicionar = new JButton(LABEL_BOTAO_ADICIONAR_AO_EVENTO);
        adicionar.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				getController().salvarProfissionalEvento();
				
			}
		});
        panelProfissionais.add(adicionar);
	}
		
	
	// Caractarísticas
	public JTable getTabelaCaracteristicasEvento() {
		return tabelaCaracteristicasEvento;
	}
	
	public JList<String> getJListCaracteristicas() {
		return caracteristicas;
	}

	public java.util.List<String> getListaCaracteristicasSelecionados() {
		return listaCaracteristicasSelecionadas;
	}
	
	public int[] getTabelaCaracteristicasSelecionados() {
		return this.tabelaCaracteristicas.getSelectedRows();
	}
	
	public JTable getTabelaCaracteristicas() {
		return this.tabelaCaracteristicas;
	}

	public JList<String> getCaracteristicas() {
		return caracteristicas;
	}
	
	public CaracteristicaTableModel getModeloTabelaCaracteristicas() {
		if(this.modeloTabelaCaracteristicas == null){
			this.modeloTabelaCaracteristicas = (CaracteristicaTableModel)new CaracteristicaTableModel(false);
			this.getController().buscarCaracteristicas();		
		}
		
		if(this.modeloTabelaCaracteristicas.hasEmptyRow()){
			modeloTabelaCaracteristicas.addEmptyRow();
		}
		
		return this.modeloTabelaCaracteristicas;
	}
	
	public EventoCaracteristicaTableModel getModeloTabelaCaracteristicasEvento() {
		if(this.modeloTabelaCaracteristicasEvento == null){
			this.modeloTabelaCaracteristicasEvento = (EventoCaracteristicaTableModel)new EventoCaracteristicaTableModel(null);
			this.modeloTabelaCaracteristicasEvento.addTableModelListener(new TableModelListener() {				
				@Override
				public void tableChanged(TableModelEvent e) {
					//Object source = e.getSource();
					int column = e.getColumn();
					int row = e.getFirstRow();
					//System.out.println("row: " + row + " column: " + column);	
					if(column >= 0) {
						getController().marcarLinhaEventoCaracteristicaAtualizada(row);							
					}
				}
			});
			this.getController().buscarCaracteristicasDoEvento();
		}
		
		if(this.modeloTabelaCaracteristicasEvento.hasEmptyRow()){
			modeloTabelaCaracteristicasEvento.addEmptyRow();
		}
		
		return this.modeloTabelaCaracteristicasEvento;
	}
	
	private void createAbaCaracteristicasEvento(JPanel panelCaracteristicasEvento) {	
		
		JLabel lblCaracteristicasEvento = new JLabel("Características do Evento");
		panelCaracteristicasEvento.add(lblCaracteristicasEvento);		
				
		tabelaCaracteristicasEvento = new JTable();
		tabelaCaracteristicasEvento.setModel(getModeloTabelaCaracteristicasEvento());
		tabelaCaracteristicasEvento.setSurrendersFocusOnKeystroke(true);
		tabelaCaracteristicasEvento.addMouseListener(new MouseAdapter() {
			//   @SuppressWarnings("unused")
			public void mouseClicked(MouseEvent e) {
//			      if (e.getClickCount() == 2) {
//			         JTable target = (JTable)e.getSource();
//			         int row = target.getSelectedRow();
//			         int column = target.getSelectedColumn();
//			         JFrame newFrame = new JFrame();
//			         newFrame.setTitle("Detail Screen");
//			         newFrame.setVisible(true);
//			      }
			   }
			});
		
		JScrollPane scrollerTabela = new JScrollPane(tabelaCaracteristicasEvento);
		scrollerTabela.setPreferredSize(new Dimension(450, 200));
		scrollerTabela.setVisible(true);
		panelCaracteristicasEvento.add(scrollerTabela);
		
		JButton alterar = new JButton("Alterar");
		alterar.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				getController().alterarCaracteristicaEvento();				
			}
		});
		panelCaracteristicasEvento.add(alterar);
		
		JButton excluir = new JButton(LABEL_BOTAO_EXCLUIR_DO_EVENTO);
		excluir.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				getController().excluirCaracteristicaEvento();				
			}
		});
		panelCaracteristicasEvento.add(excluir);
	}
	
	private void createAbaCaracteristicas(JPanel panelCaracteristicas) {			
		JLabel lblCaracteristicasEvento = new JLabel("Caracteristicas");
		panelCaracteristicas.add(lblCaracteristicasEvento);		
				
		tabelaCaracteristicas = new JTable();
		tabelaCaracteristicas.setModel(getModeloTabelaCaracteristicas());
		tabelaCaracteristicas.setSurrendersFocusOnKeystroke(true);
		tabelaCaracteristicas.addMouseListener(new MouseAdapter() {
			//   @SuppressWarnings("unused")
			public void mouseClicked(MouseEvent e) {
//			      if (e.getClickCount() == 2) {
//			         JTable target = (JTable)e.getSource();
//			         int row = target.getSelectedRow();
//			         int column = target.getSelectedColumn();
//			         JFrame newFrame = new JFrame();
//			         newFrame.setTitle("Detail Screen");
//			         newFrame.setVisible(true);
//			      }
			   }
			});
		
		JScrollPane scrollerTabela = new JScrollPane(tabelaCaracteristicas);
		scrollerTabela.setPreferredSize(new Dimension(450, 200));
		scrollerTabela.setVisible(true);
		panelCaracteristicas.add(scrollerTabela); 
		
		JButton adicionar = new JButton(LABEL_BOTAO_ADICIONAR_AO_EVENTO);
        adicionar.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				getController().incluirCaracteristicaEvento();				
			}
		});
        panelCaracteristicas.add(adicionar);
	}
	
	@SuppressWarnings("unused")
	@Deprecated
	private void createCaracteristica(JTabbedPane panelEspaco) {
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
	}
	
	
	// Espaços
	
	public JTable getTabelaEspacosEvento() {
		return tabelaEspacosEvento;
	}
	
	public JList<String> getJListEspacos() {
		return espacos;
	}

	public java.util.List<String> getListaEspacosSelecionados() {
		return listaEspacosSelecionados;
	}
	
	public int[] getTabelaEspacosSelecionados() {
		return this.tabelaEspacos.getSelectedRows();
	}
	
	public JTable getTabelaEspacos() {
		return this.tabelaEspacos;
	}

	public JList<String> getEspacos() {
		return espacos;
	}
	
	public EspacoTableModel getModeloTabelaEspacos() {
		if(this.modeloTabelaEspacos == null){
			this.modeloTabelaEspacos = (EspacoTableModel)new EspacoTableModel(false);
			this.getController().buscarEspacos();		
		}
		
		if(this.modeloTabelaEspacos.hasEmptyRow()){
			modeloTabelaEspacos.addEmptyRow();
		}
		
		return this.modeloTabelaEspacos;
	}
	
	public EventoEspacoTableModel getModeloTabelaEspacosEvento() {
		if(this.modeloTabelaEspacosEvento == null){
			this.modeloTabelaEspacosEvento = (EventoEspacoTableModel)new EventoEspacoTableModel(null);			
			this.getController().buscarEspacosDoEvento();
		}
		
		if(this.modeloTabelaEspacosEvento.hasEmptyRow()){
			modeloTabelaEspacosEvento.addEmptyRow();
		}
		
		return this.modeloTabelaEspacosEvento;
	}
	
	/**
	 * 
	 * @param panelEspacosEvento
	 */
	private void createAbaEspacosEvento(JPanel panelEspacosEvento) {	
		
		JLabel lblEspacosEvento = new JLabel("Espaços do Evento");
		panelEspacosEvento.add(lblEspacosEvento);		
				
		tabelaEspacosEvento = new JTable();
		tabelaEspacosEvento.setModel(getModeloTabelaEspacosEvento());
		tabelaEspacosEvento.setSurrendersFocusOnKeystroke(true);
		tabelaEspacosEvento.addMouseListener(new MouseAdapter() {
			//   @SuppressWarnings("unused")
			public void mouseClicked(MouseEvent e) {
//			      if (e.getClickCount() == 2) {
//			         JTable target = (JTable)e.getSource();
//			         int row = target.getSelectedRow();
//			         int column = target.getSelectedColumn();
//			         JFrame newFrame = new JFrame();
//			         newFrame.setTitle("Detail Screen");
//			         newFrame.setVisible(true);
//			      }
			   }
			});
		
		JScrollPane scrollerTabela = new JScrollPane(tabelaEspacosEvento);
		scrollerTabela.setPreferredSize(new Dimension(450, 200));
		scrollerTabela.setVisible(true);
		panelEspacosEvento.add(scrollerTabela);
		
		JButton excluir = new JButton(LABEL_BOTAO_EXCLUIR_DO_EVENTO);
		excluir.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				getController().excluirEspacoEvento();				
			}
		});
		panelEspacosEvento.add(excluir);
	}
	
	private void createAbaEspacos(JPanel panelEspacos) {			
		JLabel lblEspacosEvento = new JLabel("Espaços");
		panelEspacos.add(lblEspacosEvento);		
				
		tabelaEspacos = new JTable();
		tabelaEspacos.setModel(getModeloTabelaEspacos());
		tabelaEspacos.setSurrendersFocusOnKeystroke(true);
		tabelaEspacos.addMouseListener(new MouseAdapter() {
			//   @SuppressWarnings("unused")
			public void mouseClicked(MouseEvent e) {
//			      if (e.getClickCount() == 2) {
//			         JTable target = (JTable)e.getSource();
//			         int row = target.getSelectedRow();
//			         int column = target.getSelectedColumn();
//			         JFrame newFrame = new JFrame();
//			         newFrame.setTitle("Detail Screen");
//			         newFrame.setVisible(true);
//			      }
			   }
			});
		
		JScrollPane scrollerTabela = new JScrollPane(tabelaEspacos);
		scrollerTabela.setPreferredSize(new Dimension(450, 200));
		scrollerTabela.setVisible(true);
		panelEspacos.add(scrollerTabela); 
		
		JButton adicionar = new JButton(LABEL_BOTAO_ADICIONAR_AO_EVENTO);
        adicionar.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				getController().incluirEspacoEvento();				
			}
		});
        panelEspacos.add(adicionar);
	}
	
	
	// Agenda de Horários do Evento
	
	public HorarioEventoTableModel getModeloTabelaHorariosEvento() {
		if(this.modeloTabelaHorariosEvento == null){
			this.modeloTabelaHorariosEvento = (HorarioEventoTableModel)new HorarioEventoTableModel(null);			
			this.getController().buscarHorariosDoEvento();
		}
		
		if(this.modeloTabelaHorariosEvento.hasEmptyRow()){
			modeloTabelaHorariosEvento.addEmptyRow();
		}
		
		return this.modeloTabelaHorariosEvento;
	}
	
	private void createAbaAgenda(JPanel panelAgenda) {			
		JLabel lblAgendaEvento = new JLabel("Agenda de Horários do Evento");
		panelAgenda.add(lblAgendaEvento);		
								
		tabelaHorariosEvento = new JTable();
		tabelaHorariosEvento.setModel(getModeloTabelaHorariosEvento());
		tabelaHorariosEvento.setSurrendersFocusOnKeystroke(true);
		tabelaHorariosEvento.addMouseListener(new MouseAdapter() {
			//   @SuppressWarnings("unused")
			public void mouseClicked(MouseEvent e) {
//			      if (e.getClickCount() == 2) {
//			         JTable target = (JTable)e.getSource();
//			         int row = target.getSelectedRow();
//			         int column = target.getSelectedColumn();
//			         JFrame newFrame = new JFrame();
//			         newFrame.setTitle("Detail Screen");
//			         newFrame.setVisible(true);
//			      }
			   }
			});
		
		JScrollPane scrollerTabela = new JScrollPane(tabelaHorariosEvento);
		scrollerTabela.setPreferredSize(new Dimension(450, 200));
		scrollerTabela.setVisible(true);
		panelAgenda.add(scrollerTabela);		
	}
}