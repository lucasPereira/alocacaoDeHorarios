package br.ufsc.sar.service;

import br.ufsc.sar.entity.Espaco;
import br.ufsc.service.BaseService;

public interface EspacoService extends BaseService<Espaco>{
	
	public boolean excluirCaracteristicaEspaco(Espaco espaco);
}
