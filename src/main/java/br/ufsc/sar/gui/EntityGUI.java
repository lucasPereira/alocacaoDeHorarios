package br.ufsc.sar.gui;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import br.ufsc.entity.BaseEntity;
import br.ufsc.sar.controller.EntityController;
import br.ufsc.sar.entity.Profissional;
import br.ufsc.sar.gui.componentes.EntityRowTableModel;
import br.ufsc.sar.listner.EntityListener;
import br.ufsc.util.type.EntidadeDetalheInfo;

public abstract class EntityGUI<T extends BaseEntity> extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1509959024718839004L;
	
	private JTable tabelaEntity = null;
	private JScrollPane scrollerTabela = null;
	private JLabel lblCadastroEntity = null;
	private JButton btNovo = null;
	private JButton btSalvar = null;
	private JButton btExcluir = null;
	
	private EntityRowTableModel<T> modeloTabelaEntity = null;
	
	//private static final CaracteristicaService caracteristicaService = new CaracteristicaServiceImpl();
	private static AppGUI aplicacaoGUI = null;
	
	private EntityController<T> controller;
			
	/**
	 * Create the panel.
	 */
	protected EntityGUI(AppGUI app) {
		super();
		EntityGUI.aplicacaoGUI = app;
		this.initialize();
	}
	
	protected EntityGUI(AppGUI app, BaseEntity entidade) {
		super();
		EntityGUI.aplicacaoGUI = app;
		setEntidadeDetalhe(entidade);
		this.initialize();
	}
	
	private void initialize() {
		this.setLayout(null);
		this.add(this.getLblCadastroEntity());
		this.add(this.getBtNovo());
		this.add(this.getBtExcluir());
		this.add(this.getBtSalvar());
		this.add(this.getScrollerTabela());
		this.setVisible(true);	
	}
	
	public JTable getTabelaEntity() {
		if(this.tabelaEntity == null){
			this.tabelaEntity = new JTable();
			this.tabelaEntity.setModel(this.getModeloTabelaEntity());
			this.tabelaEntity.setSurrendersFocusOnKeystroke(true);			
			this.tabelaEntity.addMouseListener(new MouseAdapter() {
				@SuppressWarnings("unused")
				public void mouseClicked(MouseEvent e) {
				      if (e.getClickCount() == 2) {
				         JTable target = (JTable)e.getSource();
				         int row = target.getSelectedRow();
				         int column = target.getSelectedColumn();
				         //JFrame newFrame = new JFrame();
				         //newFrame.setTitle("Detail Screen");
				         //newFrame.setVisible(true);
				         JPanel panelDetalhe = null;
				         String textoLabelEntity = null;
				         switch (getTextoLabelEntity()) {
				         	case ProfissionalGUI.GUI_LABEL:
				         		Profissional profissional = (Profissional) getEntityController().buscarEntity(row);
				         		if(profissional != null) {
				         			panelDetalhe = new HorarioProfissionalGUI(aplicacaoGUI, profissional);
				         			textoLabelEntity = HorarioProfissionalGUI.GUI_LABEL;
				         		}
								break;
							default:
								break;
						 }
				         
				         if(panelDetalhe != null) {
					         final JDialog frame = new JDialog(aplicacaoGUI, textoLabelEntity, true);
					         panelDetalhe.setOpaque(true);
					         frame.getContentPane().add(panelDetalhe);
					         frame.setSize(800, 600);
					         //frame.pack();
					         frame.setVisible(true);
				         }
				      }
				   }
				});
			if(this.modeloTabelaEntity.hasEmptyRow()){
				modeloTabelaEntity.addEmptyRow();
			}
		}
		return this.tabelaEntity;
	}
	public void setTabelaEntity(JTable tabelaEntity) {
		this.tabelaEntity = tabelaEntity;
	}
	
	public abstract EntityRowTableModel<T> getNewEntityTableModel();
	
	//public abstract EntityTableListener<T> getEntityTableListener();
	
	public EntityRowTableModel<T> getModeloTabelaEntity() {
		if(this.modeloTabelaEntity == null){
			this.modeloTabelaEntity = getNewEntityTableModel();//new EntityRowTableModel(caracteristicaService.getList());
			this.modeloTabelaEntity.addTableModelListener(getEntityListener().getEntityTableListener());
			EntidadeDetalheInfo<? extends BaseEntity, T> entidadeDetalhe = getEntidadeDetalheInfo();
			if(entidadeDetalhe != null) {
				this.getEntityController().buscarTodos(entidadeDetalhe);
			}
			else {
				this.getEntityController().buscarTodos();
			}
		}
		return this.modeloTabelaEntity;
	}
	
	/**
	 * 
	 * @return
	 */
	public abstract EntidadeDetalheInfo<? extends BaseEntity, T> getEntidadeDetalheInfo();
	
	/**
	 * 
	 * @return
	 */
	public abstract void setEntidadeDetalhe(BaseEntity entidade);

	public void setModeloTabelaEntity(EntityRowTableModel<T> modeloTabelaEntity) {
		this.modeloTabelaEntity = modeloTabelaEntity;
	}
	
	public JScrollPane getScrollerTabela() {
		if(this.scrollerTabela == null){
			this.scrollerTabela = new JScrollPane(this.getTabelaEntity());
			this.scrollerTabela.setBounds(10,35,600,400);
			this.scrollerTabela.setVisible(true);
		}
		return scrollerTabela;
	}
	public void setScrollerTabela(JScrollPane scrollerTabela) {
		this.scrollerTabela = scrollerTabela;
	}
	
	public abstract String getTextoLabelEntity();
	
	public JLabel getLblCadastroEntity() {
		if(this.lblCadastroEntity == null){
			this.lblCadastroEntity = new JLabel(getTextoLabelEntity());
			this.lblCadastroEntity.setHorizontalAlignment(SwingConstants.LEFT);
			this.lblCadastroEntity.setFont(new Font("Tahoma", Font.BOLD, 14));
			this.lblCadastroEntity.setBounds(10, 11, 282, 14);
		}
		return this.lblCadastroEntity;
	}
	public void setLblCadastroEntity(JLabel lblCadastroEntity) {
		this.lblCadastroEntity = lblCadastroEntity;
	}

	public JButton getBtNovo() {
		if(this.btNovo == null){
			
			this.btNovo = new JButton();
			this.btNovo.setText("Novo");
			this.btNovo.addActionListener(getEntityListener());
			this.btNovo.setBounds(390,11,100,22);
		}
		return this.btNovo ;
	}

	public abstract EntityListener<T> getEntityListener();

	public void setBtNovo(JButton btNovo) {
		this.btNovo = btNovo;
	}

	public static AppGUI getAplicacaoGUI() {
		return aplicacaoGUI;
	}
	
	public JButton getBtSalvar() {
		if(this.btSalvar == null){
			this.btSalvar = new JButton();
			this.btSalvar.setText("Salvar");
			this.btSalvar.addActionListener(getEntityListener());
			this.btSalvar.setBounds(500,11,100,22);
		}
		return this.btSalvar;
	}
	
	public JButton getBtExcluir() {
		if(this.btExcluir == null){
			this.btExcluir = new JButton();
			this.btExcluir.setText("Excluir");
			this.btExcluir.addActionListener(getEntityListener());
			this.btExcluir.setBounds(280,11,100,22);
		}
		return this.btExcluir;
	}

	public void setBtSalvar(JButton btSalvar) {
		this.btSalvar = btSalvar;
	}
	
	public void setBtExcluir(JButton btExcluir) {
		this.btExcluir = btExcluir;
	}

	public abstract EntityController<T> getNewEntityController();
	
	public EntityController<T> getEntityController() {
		if(this.controller == null) {
			this.controller = getNewEntityController();
		}
		
		return this.controller;
	}	
}