package br.ufsc.sar.gui.componentes;

import java.util.ArrayList;
import java.util.List;

import br.ufsc.gui.componentes.InteractiveTableModel;
import br.ufsc.sar.entity.Caracteristica;
import br.ufsc.util.type.Coluna;

public class InteractiveTableCaracteristica extends InteractiveTableModel<Caracteristica> {

	private static List<Coluna> listIndex = null;
    
	public static List<Coluna> getListIndex(){
		if(listIndex == null){
			listIndex = new ArrayList<Coluna>();
			listIndex.add(new Coluna(0, "id","Código",false,Long.class));
			listIndex.add(new Coluna(1, "nome","Nome",true,String.class));
			listIndex.add(new Coluna(2, "forauso","Fora uso",true,Boolean.class));
		}
		return listIndex;
	}
      
    public InteractiveTableCaracteristica(List<Caracteristica> lista) {
    	super(lista, getListIndex());    
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
    	  for(Coluna coluna : getListIndex()){
    		  if(coluna.getId() == column)
    			  return coluna.isEditable();
    	  }
    	  return true;
       }
      
    @Override
    public Class getColumnClass(int column) {
    	for(Coluna coluna : getListIndex()){
  		  	if(coluna.getId() == column){
  		  		return coluna.getClasse();
  		  	}
    	}
    	return String.class;
    }

      
}
