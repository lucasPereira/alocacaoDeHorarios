package br.ufsc.entity;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseEntity implements Serializable {

	private static Logger logger = Logger.getLogger(BaseEntity.class.getName());

	private static final long serialVersionUID = 7701410610053053551L;

	public Number getIdValue() {
		List<Field> fields = new ArrayList<Field>();
		fields.addAll(Arrays.asList(this.getClass().getDeclaredFields()));
		if (this.getClass().getSuperclass() != null) {
			fields.addAll(Arrays.asList(this.getClass().getSuperclass().getDeclaredFields()));
		}

		for (Field field : fields) {
			if (field.isAnnotationPresent(javax.persistence.Id.class)) {
				String name = field.getName();
				try {
					name = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
					// //System.out.println("NOME: " + name);
					Method m = this.getClass().getMethod(name);
					return (Number) m.invoke(this);
				} catch (Exception e) {
					logger.log(Level.SEVERE, "Erro ao obter ID." + name, e);
					return null;
				}
			}
		}
		return null;
	}
}
