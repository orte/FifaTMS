package LN;

import java.io.Serializable;

/**
 * Clase persona, padre de clsAgente y clsJugador y que implementa la interfaz Serializable. Tiene atributos de nombre, apellido y nacionalidad
 * @author jon.orte
 *
 */
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
	/**
	 * Método hashCode que genera un código único en base a los tres atributos de la clase: nombre, apellido y nacionalidad
	 * @author jon.orte
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((apellido == null) ? 0 : apellido.hashCode());
		result = prime * result
				+ ((nacionalidad == null) ? 0 : nacionalidad.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}
	/**
	 * Método equals que compara dos personas en base a si sus nombres, apellidos y nacionalidades son iguales.
	 * @author jon.orte
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		clsPersona other = (clsPersona) obj;
		if (apellido == null) {
			if (other.apellido != null)
				return false;
		} else if (!apellido.equals(other.apellido))
			return false;
		if (nacionalidad == null) {
			if (other.nacionalidad != null)
				return false;
		} else if (!nacionalidad.equals(other.nacionalidad))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	
}
