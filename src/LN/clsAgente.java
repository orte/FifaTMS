package LN;

import java.io.Serializable;
import java.util.LinkedList;
public class clsAgente extends clsPersona implements Serializable, Comparable<clsAgente>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3486390554402644510L;
	private int num_licencia;
	private LinkedList<clsJugador> representados=new LinkedList<clsJugador>();
	private double dinero_recibido;
	private int traspasos;
	
	public int getNum_licencia() {
		return num_licencia;
	}
	public void setNum_licencia(int num_licencia) {
		this.num_licencia = num_licencia;
	}
	public LinkedList<clsJugador> getRepresentados() {
		return representados;
	}
	public void setRepresentados(LinkedList<clsJugador> representados) {
		this.representados = representados;
	}
	public double getDinero_recibido() {
		return dinero_recibido;
	}
	public void setDinero_recibido(double dinero_recibido) {
		this.dinero_recibido = dinero_recibido;
	}
	public int getTraspasos() {
		return traspasos;
	}
	public void setTraspasos(int traspasos) {
		this.traspasos = traspasos;
	}
	public String toString(){
		String string;
		string=nombre+" "+apellido+". Número de licencia: "+num_licencia;
		return string;
	}
	@Override
	public int compareTo(clsAgente arg0) {
		// TODO Auto-generated method stub
		int comp=0;
		if(arg0.getDinero_recibido()>this.dinero_recibido){
			comp=1;
		}
		if(arg0.getDinero_recibido()<this.dinero_recibido){
			comp=-1;
		}
		return comp;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + num_licencia;
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
		clsAgente other = (clsAgente) obj;
		if (num_licencia != other.num_licencia)
			return false;
		return true;
	}
	

}
