package br.ufsc.sar.gui.componentes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import br.ufsc.sar.entity.Caracteristica;
import br.ufsc.sar.entity.Profissional;
import br.ufsc.sar.service.ProfissionalService;
import br.ufsc.sar.serviceimpl.ProfissionalServiceImpl;

@SuppressWarnings("unchecked")
public class ProfissionalTableModel extends EntityRowTableModel<Profissional>
{		
	/**
	 * 
	 */
	private static final long serialVersionUID = 318144832582474285L;
	
	// id, nome, profissão, data de nascimento, telefone e cpf
	private static String[] COLUMN_NAMES =
	{
		"ID",
		"Nome",
		"Profissão", 
		"Data de Nascimento",
		"Telefone",
		"CPF"		
	};

	public ProfissionalTableModel()
	{
		super( Arrays.asList(COLUMN_NAMES) );
		setRowClass( Profissional.class );

		setColumnClass(0, Long.class);
		setColumnClass(1, String.class);
		setColumnClass(2, String.class);
		setColumnClass(3, Date.class);
		setColumnClass(4, String.class);
		setColumnClass(5, String.class);
		
		initializeData();
	}

	public Object getValueAt(int row, int column)
	{
		Profissional profissional = getRow(row);

		switch (column)
        {
            case 0: return profissional.getId();
            case 1: return profissional.getNome();
            case 2: return profissional.getProfissao();
            case 3: return profissional.getDtnascimento();
            case 4: return profissional.getTelefone();
            case 5: return profissional.getCpf();
            default: return null;
        }
	}

	@Override
	public void setValueAt(Object value, int row, int column)
	{
		Profissional profissional = getRow(row);

		switch (column)
        {
            case 0: profissional.setId((Long)value); break;
            case 1: profissional.setNome((String)value); break;
            case 2: profissional.setProfissao((String)value); break;
            case 3: profissional.setDtnascimento((Date)value); break;
            case 4: profissional.setTelefone((String)value); break;
            case 5: profissional.setCpf((String)value); break;
        }

		fireTableCellUpdated(row, column);
	}
	
	@Override
	public boolean hasEmptyRow() {
		if(!super.hasEmptyRow()) {        
			Profissional profissional = (Profissional)this.modelData.get(this.modelData.size() - 1);	        
	        if (profissional.getId().toString().trim().equals("") &&
	        	profissional.getNome().trim().equals("") &&
	        	profissional.getProfissao().toString().trim().equals("") &&
	        	profissional.getDtnascimento().toString().trim().equals("") &&
	        	profissional.getTelefone().trim().equals("") &&
	        	profissional.getCpf().trim().equals("")) 
	        {
	        	return true;
	        }
		}
       
       	return false;		
    }
	
    @Override
    public void addEmptyRow() {
        this.addRow(new Profissional());
    }
    
    private void initializeData(){
    	ProfissionalService ps = new ProfissionalServiceImpl();
    	List<Profissional> profissionais = null;
    	try {
    		profissionais = (List<Profissional>)ps.getList();
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
    	
    	if(profissionais == null){   
    		Profissional p = new Profissional();
    		p.setId(new Long(0));
    		p.setNome("Dados não encontrados");
    		p.setDtnascimento(new Date());
    		profissionais = new ArrayList<Profissional>();
    		profissionais.add(p);
    	}
    	
    	super.addRows(profissionais);
    }
}
