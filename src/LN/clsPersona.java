package LN;

import java.io.Serializable;

public abstract class clsPersona implements Serializable{

	private static final long serialVersionUID = -4540543585010491128L;
	protected String nombre;
	protected String apellido;
	protected String nacionalidad;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
	
}
