package br.ufsc.util.type;

public class Coluna {
	private int id;
	private String field;
	private String label;
	private boolean isEditable;
	private Class classe;
	
	public Coluna(int id, String field, String label, boolean isEditable, Class classe){
		this.setId(id);
		this.setField(field);
		this.setLabel(label);
		this.setEditable(isEditable);
		this.setClasse(classe);
	}
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getField() {
		return this.field;
	}
	public void setField(String field) {
		this.field = field;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public boolean isEditable() {
		return this.isEditable;
	}

	public void setEditable(boolean isEditable) {
		this.isEditable = isEditable;
	}

	public Class getClasse() {
		return this.classe;
	}

	public void setClasse(Class classe) {
		this.classe = classe;
	}
	
	
}
