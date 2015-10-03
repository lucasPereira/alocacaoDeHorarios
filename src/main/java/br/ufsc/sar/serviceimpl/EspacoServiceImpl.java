package br.ufsc.sar.serviceimpl;

import java.util.List;

import javax.persistence.Query;

import br.ufsc.sar.entity.Caracteristica;
import br.ufsc.sar.entity.Espaco;
import br.ufsc.sar.service.EspacoService;
import br.ufsc.serviceimpl.BaseServiceImpl;

public class EspacoServiceImpl extends BaseServiceImpl<Espaco> implements EspacoService{

	@Override
	public boolean excluirCaracteristicaEspaco(Espaco espaco) {		
		int deleted = 0;
		if(espaco != null && espaco.getCaracteristicas() != null && !espaco.getCaracteristicas().isEmpty()) {			
			List<Caracteristica> caracteristicas = espaco.getCaracteristicas();
			String idsCaracteristicas = "";
			for (Caracteristica caracteristica : caracteristicas) {
				idsCaracteristicas = idsCaracteristicas + "," + caracteristica.getId();
			}
			
			idsCaracteristicas = idsCaracteristicas.substring(1);
			
			String sqlDelete = "DELETE FROM local_caracteristica " +
					"      WHERE local_id = " + espaco.getId() +
					"        AND caracteristica_id in (" + idsCaracteristicas + ")";
			
			System.out.println("Deletar: " + sqlDelete);
			
			Query query = getEntityManager().createNativeQuery(sqlDelete);
			
			//
			this.getEntityManager().getTransaction().begin();
			deleted = query.executeUpdate();
			this.getEntityManager().getTransaction().commit();			
		}
		
		return deleted > 0;
	}
	
}
