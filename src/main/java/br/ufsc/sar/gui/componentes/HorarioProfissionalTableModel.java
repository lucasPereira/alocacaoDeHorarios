package br.ufsc.sar.gui.componentes;

import java.util.Arrays;
import java.util.Date;

import br.ufsc.sar.entity.HorarioProfissional;
import br.ufsc.sar.entity.Profissional;

/**
 * 
 * @author João
 *
 */
@SuppressWarnings("unchecked")
public class HorarioProfissionalTableModel extends EntityRowTableModel<HorarioProfissional>
{		
	/**
	 * 
	 */
	private static final long serialVersionUID = 318144832582474285L;
	
	// id, nome, profissão, data de nascimento, telefone e cpf
	private static String[] COLUMN_NAMES =
	{
		"ID",
		"ID Profissional",
		"Data de Início", 
		"Data de Término",
		"Dia da Semana",
		"Hora de Início", 
		"Hora de Término"
	};
	
	private Profissional profissional;

	public HorarioProfissionalTableModel(Profissional profissional)
	{
		super( Arrays.asList(COLUMN_NAMES) );
		setRowClass( HorarioProfissional.class );

		setColumnClass(0, Long.class);
		setColumnClass(1, Long.class);
		setColumnClass(2, Date.class);
		setColumnClass(3, Date.class);
		setColumnClass(4, String.class);
		setColumnClass(5, String.class);
		setColumnClass(6, String.class);
		
		setColumnEditable(0, false);
		setColumnEditable(1, false);
		
		this.profissional = profissional;
	}

	public Object getValueAt(int row, int column)
	{
		HorarioProfissional hrprofissional = getRow(row);

		switch (column)
        {
            case 0: return hrprofissional.getId();
            case 1: return hrprofissional.getProfissional() != null ? hrprofissional.getProfissional().getId() : null;
            case 2: return hrprofissional.getDatainicio();
            case 3: return hrprofissional.getDatatermino();
            case 4: return hrprofissional.getDiadasemana();
            case 5: return hrprofissional.getHorainicio();
            case 6: return hrprofissional.getHoratermino();
            default: return null;
        }
	}

	@Override
	public void setValueAt(Object value, int row, int column)
	{
		HorarioProfissional hrprofissional = getRow(row);

		switch (column)
        {
            case 0: hrprofissional.setId((Long)value); break;
            case 1: hrprofissional.setProfissional(this.profissional); break;
            case 2: hrprofissional.setDatainicio((Date)value); break;
            case 3: hrprofissional.setDatatermino((Date)value); break;
            case 4: hrprofissional.setDiadasemana((String)value); break;
            case 5: hrprofissional.setHorainicio((String)value); break;
            case 6: hrprofissional.setHoratermino((String)value); break;
        }

		fireTableCellUpdated(row, column);
	}
	
	@Override
	public boolean hasEmptyRow() {
		if(!super.hasEmptyRow()) {        
			HorarioProfissional hrprofissional = (HorarioProfissional)this.modelData.get(this.modelData.size() - 1);	        
	        if (hrprofissional != null && 
	        	hrprofissional.getId() != null && hrprofissional.getId().toString().trim().equals("") &&
	        	hrprofissional.getProfissional() != null && hrprofissional.getProfissional().getId() != null && hrprofissional.getProfissional().getId().toString().trim().equals("") &&
	        	hrprofissional.getDatainicio() != null && hrprofissional.getDatainicio().toString().trim().equals("") &&
	        	hrprofissional.getDatatermino() != null && hrprofissional.getDatatermino().toString().trim().equals("") &&
	        	hrprofissional.getDiadasemana() != null && hrprofissional.getDiadasemana().trim().equals("") &&
	        	hrprofissional.getHorainicio() != null && hrprofissional.getHorainicio().trim().equals("") &&
	        	hrprofissional.getHoratermino() != null && hrprofissional.getHoratermino().trim().equals("")) 
	        {
	        	return true;
	        }
		}
       
       	return false;		
    }
	
    @Override
    public void addEmptyRow() {
    	HorarioProfissional horarioProfissional = new HorarioProfissional();
    	horarioProfissional.setProfissional(this.profissional);
        this.addRow(horarioProfissional);
    }
}