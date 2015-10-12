package br.ufsc.sar.gui.componentes;

import java.util.Arrays;
import java.util.Date;

import br.ufsc.sar.entity.Evento;
import br.ufsc.sar.entity.HorarioEvento;

/**
 * 
 * @author João
 *
 */
@SuppressWarnings("unchecked")
public class HorarioEventoTableModel extends EntityRowTableModel<HorarioEvento>
{		
	/**
	 * 
	 */
	private static final long serialVersionUID = 318144832582474285L;
	
	// id, nome, profissão, data de nascimento, telefone e cpf
	private static String[] COLUMN_NAMES =
	{
		"Data de Início", 
		"Data de Término",
		"Dia da Semana",
		"Hora de Início", 
		"Hora de Término"
	};
	
	private Evento evento;

	public HorarioEventoTableModel(Evento evento)
	{
		super( Arrays.asList(COLUMN_NAMES) );
		setRowClass( HorarioEvento.class );

		setColumnClass(0, Date.class);
		setColumnClass(1, Date.class);
		setColumnClass(2, String.class);
		setColumnClass(3, String.class);
		setColumnClass(4, String.class);
		
		setColumnEditable(0, false);
		setColumnEditable(1, false);
		setColumnEditable(2, false);
		setColumnEditable(3, false);
		setColumnEditable(4, false);
		
		this.evento = evento;
	}

	public Object getValueAt(int row, int column)
	{
		HorarioEvento hrevento = getRow(row);

		switch (column)
        {
            case 0: return hrevento.getDatainicio();
            case 1: return hrevento.getDatatermino();
            case 2: return hrevento.getDiadasemana();
            case 3: return hrevento.getHorainicio();
            case 4: return hrevento.getHoratermino();
            default: return null;
        }
	}

	@Override
	public void setValueAt(Object value, int row, int column)
	{
		HorarioEvento hrevento = getRow(row);

		switch (column)
        {
            case 0: hrevento.setDatainicio((Date)value); break;
            case 1: hrevento.setDatatermino((Date)value); break;
            case 2: hrevento.setDiadasemana((String)value); break;
            case 3: hrevento.setHorainicio((String)value); break;
            case 4: hrevento.setHoratermino((String)value); break;
        }

		fireTableCellUpdated(row, column);
	}
	
	@Override
	public boolean hasEmptyRow() {
		if(!super.hasEmptyRow()) {        
			HorarioEvento hrespaco = (HorarioEvento)this.modelData.get(this.modelData.size() - 1);	        
	        if (hrespaco != null && 
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
    	HorarioEvento horarioEvento = new HorarioEvento();
    	horarioEvento.setEvento(this.evento);
        this.addRow(horarioEvento);
    }
}