package br.ufsc.sar.listner;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class InteractiveTableCaracteristicaListener implements
		TableModelListener {

	public void tableChanged(TableModelEvent e) {
		if (e.getType() == TableModelEvent.UPDATE) {
            int column = e.getColumn();
            int row = e.getFirstRow();
            System.out.println("row: " + row + " column: " + column);
        }
	}

}
