package LN;

import java.io.Serializable;

/**
 * Objeto de la clase Club. Implementa las interfaces Serializable y Comparable, adem�s de contar con atributos tales como nombre, pa�s,
 * presupuesto para fichajes (en mill. de �), dinero gastado (en mill. de �) y un ID.
 * @author jon.orte
 *
 */
public class clsClub implements Serializable, Comparable<clsClub>{

	private static final long serialVersionUID = -3881740560470115469L;
	private String nombre;
	private String pais;
	private double presupuesto;
	private double dinero_gastado;
	private int id_club;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public double getPresupuesto() {
		return presupuesto;
	}
	public void setPresupuesto(double presupuesto) {
		this.presupuesto = presupuesto;
	}
	public double getDinero_gastado() {
		return dinero_gastado;
	}
	public void setDinero_gastado(double dinero_gastado) {
		this.dinero_gastado = dinero_gastado;
	}
	
	public int getId_club() {
		return id_club;
	}
	public void setId_club(int id_club) {
		this.id_club = id_club;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String string;
		string=nombre+" ("+pais+")"+id_club;
		return string;
	}
	/**
	 * M�todo compareTo de la interfaz Comparable que compara dos clubes en base al dinero que han gastado en fichajes.
	 * @author jon.orte
	 */
	@Override
	public int compareTo(clsClub arg0) {
		// TODO Auto-generated method stub
		int comp=0;
		if(arg0.getDinero_gastado()>this.dinero_gastado){
			comp=1;
		}
		if(arg0.getDinero_gastado()<this.dinero_gastado){
			comp=-1;
		}
		return comp;
	}
	/**
	 * M�todo HashCode que genera un c�digo en base al nombre y pa�s de origen del club
	 * @author jon.orte
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((pais == null) ? 0 : pais.hashCode());
		return result;
	}
	/**
	 * M�todo equals utilizado para comprobar si dos clubes son iguales en base a su nombre y su pa�s de origen
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
		clsClub other = (clsClub) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (pais == null) {
			if (other.pais != null)
				return false;
		} else if (!pais.equals(other.pais))
			return false;
		return true;
	}
	
	
}
