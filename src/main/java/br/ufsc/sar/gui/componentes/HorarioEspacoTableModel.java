package br.ufsc.sar.gui.componentes;

import java.util.Arrays;
import java.util.Date;

import br.ufsc.sar.entity.Espaco;
import br.ufsc.sar.entity.HorarioEspaco;

/**
 * 
 * @author João
 *
 */
@SuppressWarnings("unchecked")
public class HorarioEspacoTableModel extends EntityRowTableModel<HorarioEspaco>
{		
	/**
	 * 
	 */
	private static final long serialVersionUID = 318144832582474285L;
	
	// id, nome, profissão, data de nascimento, telefone e cpf
	private static String[] COLUMN_NAMES =
	{
		"ID",
		"ID Espaço",
		"Data de Início", 
		"Data de Término",
		"Dia da Semana",
		"Hora de Início", 
		"Hora de Término"
	};
	
	private Espaco espaco;

	public HorarioEspacoTableModel(Espaco espaco)
	{
		super( Arrays.asList(COLUMN_NAMES) );
		setRowClass( HorarioEspaco.class );

		setColumnClass(0, Long.class);
		setColumnClass(1, Long.class);
		setColumnClass(2, Date.class);
		setColumnClass(3, Date.class);
		setColumnClass(4, String.class);
		setColumnClass(5, String.class);
		setColumnClass(6, String.class);
		
		setColumnEditable(0, false);
		setColumnEditable(1, false);
		
		this.espaco = espaco;
	}

	public Object getValueAt(int row, int column)
	{
		HorarioEspaco hrespaco = getRow(row);

		switch (column)
        {
            case 0: return hrespaco.getId();
            case 1: return hrespaco.getEspaco() != null ? hrespaco.getEspaco().getId() : null;
            case 2: return hrespaco.getDatainicio();
            case 3: return hrespaco.getDatatermino();
            case 4: return hrespaco.getDiadasemana();
            case 5: return hrespaco.getHorainicio();
            case 6: return hrespaco.getHoratermino();
            default: return null;
        }
	}

	@Override
	public void setValueAt(Object value, int row, int column)
	{
		HorarioEspaco hrespaco = getRow(row);

		switch (column)
        {
            case 0: hrespaco.setId((Long)value); break;
            case 1: hrespaco.setEspaco(this.espaco); break;
            case 2: hrespaco.setDatainicio((Date)value); break;
            case 3: hrespaco.setDatatermino((Date)value); break;
            case 4: hrespaco.setDiadasemana((String)value); break;
            case 5: hrespaco.setHorainicio((String)value); break;
            case 6: hrespaco.setHoratermino((String)value); break;
        }

		fireTableCellUpdated(row, column);
	}
	
	@Override
	public boolean hasEmptyRow() {
		if(!super.hasEmptyRow()) {        
			HorarioEspaco hrespaco = (HorarioEspaco)this.modelData.get(this.modelData.size() - 1);	        
	        if (hrespaco != null && 
	        	hrespaco.getId() != null && hrespaco.getId().toString().trim().equals("") &&
	        	hrespaco.getEspaco() != null && hrespaco.getEspaco().getId() != null && hrespaco.getEspaco().getId().toString().trim().equals("") &&
	        	hrespaco.getDatainicio() != null && hrespaco.getDatainicio().toString().trim().equals("") &&
	        	hrespaco.getDatatermino() != null && hrespaco.getDatatermino().toString().trim().equals("") &&
	        	hrespaco.getDiadasemana() != null && hrespaco.getDiadasemana().trim().equals("") &&
	        	hrespaco.getHorainicio() != null && hrespaco.getHorainicio().trim().equals("") &&
	        	hrespaco.getHoratermino() != null && hrespaco.getHoratermino().trim().equals("")) 
	        {
	        	return true;
	        }
		}
       
       	return false;		
    }
	
    @Override
    public void addEmptyRow() {
    	HorarioEspaco horarioEspaco = new HorarioEspaco();
    	horarioEspaco.setEspaco(this.espaco);
        this.addRow(horarioEspaco);
    }
}