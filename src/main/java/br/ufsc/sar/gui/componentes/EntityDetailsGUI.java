package br.ufsc.sar.gui.componentes;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

import br.ufsc.entity.BaseEntity;
import br.ufsc.sar.controller.EntityController;
import br.ufsc.sar.serviceimpl.HorarioProfissionalServiceImpl;
import br.ufsc.service.BaseService;

public class EntityDetailsGUI<T extends BaseEntity> extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9180140596987232955L;
	
	private EntityRowTableModel<T> modeloTabelaHorarios = null;
	
	@SuppressWarnings("unused")
	private String entityGUITitle;
	
	@SuppressWarnings("unused")
	private EntityController<T> entityController;
	
	public EntityDetailsGUI(String entityGUITitle){
		super(entityGUITitle);
		this.entityGUITitle = entityGUITitle;
		//this.entityController = entityControler;
	}
	
	/**
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public void generateEntityGUI( String[] labels, Class[] classes, Boolean[] editables, Object [] values){
		
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		
		this.setBounds(100, 100, 680, 658);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.getContentPane().setLayout(new BorderLayout());
				      
		//String[] labels = {"Name: ", "Fax: ", "Email: ", "Address: "};
		int numPairs = labels.length + 1;
		
		JPanel panel = new JPanel(new SpringLayout());

		//Create and populate the panel.		
		Component elementGUI = null;
		for (int i = 0; i < numPairs; i++) {
			
			if(i == (numPairs - 1)) {
				panel.add(new JLabel(" ", JLabel.TRAILING));
				JButton btnSalvar = new JButton("Salvar");		
				panel.add(btnSalvar);
				panel.add(new JLabel(" ", JLabel.TRAILING));
		    }
			else {				
			    JLabel lbl = new JLabel(labels[i], JLabel.TRAILING);
			    panel.add(lbl);
			    
			    String value = values[i] == null ? null : values[i].toString();
			    
			    Class classe = classes[i];
			    if(classe == java.util.Date.class){		    	
			    	elementGUI = new JFormattedTextField(df);
			    	((JFormattedTextField)elementGUI).setColumns(20);
			    	((JFormattedTextField)elementGUI).setText(value);
			    	panel.add(elementGUI);
			        try {
			            MaskFormatter dateMask = new MaskFormatter("##/##/####");
			            dateMask.install((JFormattedTextField)elementGUI);
			        } catch (ParseException ex) {
			            System.out.println("Erro: " + ex.getMessage());
			        }
			        ((JFormattedTextField)elementGUI).setEditable(editables[i]);
			    }
			    else {
			    	if(classe == Boolean.class) {
			    		elementGUI = new JCheckBox();	
			    		((JCheckBox)elementGUI).setEnabled(editables[i]);
			    		((JCheckBox)elementGUI).setSelected(Boolean.getBoolean(value));
			    		 panel.add(elementGUI);
			    	}
			    	else {
			    		elementGUI = new JTextField(10);
			    		((JTextField)elementGUI).setEditable(editables[i]);
			    		((JTextField)elementGUI).setText(value);
			    		 panel.add(elementGUI);
				    }		
			    }	
			    
			    lbl.setLabelFor(elementGUI);
			    panel.add(new JLabel(" ", JLabel.TRAILING));
			}
		}		
//		
		
		//Lay out the panel.
		//SpringUtilities.makeCompactGrid(p,
		SpringUtilities.makeGrid(panel,
		                                numPairs, 3, //rows, cols
		                                6, 6,        //initX, initY
		                                6, 6);       //xPad, yPad		
		
		panel.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.AFTER_LAST_LINE);
		//
		
		JPanel panelHorario = new JPanel();		
		panelHorario.setLayout(new BorderLayout());
		
		JLabel lblHorarios = new JLabel("Horários");
		panelHorario.add(lblHorarios, BorderLayout.NORTH);		
				
		JTable tabelaHorarios = new JTable();
		tabelaHorarios.setModel(getModeloTabelaEntity());
		tabelaHorarios.setSurrendersFocusOnKeystroke(true);
		tabelaHorarios.addMouseListener(new MouseAdapter() {
			   @SuppressWarnings("unused")
			public void mouseClicked(MouseEvent e) {
			      if (e.getClickCount() == 2) {
			         JTable target = (JTable)e.getSource();
			         int row = target.getSelectedRow();
			         int column = target.getSelectedColumn();
			         JFrame newFrame = new JFrame();
			         newFrame.setTitle("Detail Screen");
			         newFrame.setVisible(true);
			      }
			   }
			});
		
		JScrollPane scrollerTabela = new JScrollPane(tabelaHorarios);
		scrollerTabela.setBounds(10,35,200,100);
		scrollerTabela.setVisible(true);
		
		panelHorario.add(scrollerTabela, BorderLayout.CENTER);
		JButton salvarHorario = new JButton("Salvar Horário");
		panelHorario.add(salvarHorario, BorderLayout.SOUTH);		
		
		JPanel panelExterno = new JPanel();
		panelExterno.setLayout(new BorderLayout());
		panelExterno.add(panel, BorderLayout.NORTH);
		panelExterno.add(panelHorario, BorderLayout.CENTER);		
		
		this.getContentPane().add(panelExterno);
		
	    //this.setSize(800, 600);
	    this.setResizable(false);
		//this.pack();
		this.setVisible(true);		
	}
	
	@SuppressWarnings("unchecked")
	public EntityRowTableModel<T> getModeloTabelaEntity() {
		if(this.modeloTabelaHorarios == null){
			this.modeloTabelaHorarios = (EntityRowTableModel<T>)new HorarioProfissionalTableModel(null);
			//this.modeloTabelaHorarios.addTableModelListener(modeloTabelaHorarios().getEntityTableListener());
			buscarTodos();
		}
		
		if(this.modeloTabelaHorarios.hasEmptyRow()){
			modeloTabelaHorarios.addEmptyRow();
		}
		
		return this.modeloTabelaHorarios;
	}
	
	@SuppressWarnings("unchecked")
	public void buscarTodos() {
		BaseService<T> service = (BaseService<T>)new HorarioProfissionalServiceImpl();
		List<T> storedEntities = null;
		try {
			System.out.println("Service: " + service);
			storedEntities = (List<T>)service.getList();
    		if(storedEntities != null){
    			this.getModeloTabelaEntity().addRows(storedEntities);
    			System.out.println(storedEntities.size() + " entidades encontradas");  
    		}
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		if(storedEntities == null){   
			adicionarLinha();
			System.out.println("Não há entidades armazenadas"); 
    	}   	  		
	}	
	
	public void adicionarLinha() {
		this.getModeloTabelaEntity().addEmptyRow();	
	}

}
