package LN;

import java.io.Serializable;
import java.util.LinkedList;

public class clsClub implements Serializable, Comparable<clsClub>{

	private static final long serialVersionUID = -3881740560470115469L;
	private String nombre;
	private String pais;
	private double presupuesto;
	private double dinero_gastado;
	private LinkedList<clsJugador> fichajes=new LinkedList<clsJugador>();
	
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
	public LinkedList<clsJugador> getFichajes() {
		return fichajes;
	}
	public void setFichajes(LinkedList<clsJugador> fichajes) {
		this.fichajes = fichajes;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String string;
		string=nombre+". País: "+pais+". Presupuesto para fichajes: "+presupuesto;
		return string;
	}
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((pais == null) ? 0 : pais.hashCode());
		return result;
	}
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
