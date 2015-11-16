package LN;

import java.io.Serializable;

public class clsFichaje implements Serializable, Comparable<clsFichaje>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8266056360133085552L;
	private clsClub club;
	private clsJugador jugador;
	private double coste;
	private int duracion;
	private int comision;
	private double salario;
	
	public clsClub getClub() {
		return club;
	}
	public void setClub(clsClub club) {
		this.club = club;
	}
	public clsJugador getJugador() {
		return jugador;
	}
	public void setJugador(clsJugador jugador) {
		this.jugador = jugador;
	}
	public double getCoste() {
		return coste;
	}
	public void setCoste(double coste) {
		this.coste = coste;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public int getComision() {
		return comision;
	}
	public void setComision(int comision) {
		this.comision = comision;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public String toString(){
		String string;
		string=jugador.toString()+". Fichado por "+club.getNombre()+". Coste del fichaje: "+coste;
		return string;
	}
	@Override
	public int compareTo(clsFichaje arg0) {
		// TODO Auto-generated method stub
		int comp=0;
		if(arg0.getCoste()>this.coste){
			comp=1;
		}
		if(arg0.getCoste()<this.coste){
			comp=-1;
		}
		return comp;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((club == null) ? 0 : club.hashCode());
		result = prime * result + ((jugador == null) ? 0 : jugador.hashCode());
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
		clsFichaje other = (clsFichaje) obj;
		if (club == null) {
			if (other.club != null)
				return false;
		} else if (!club.equals(other.club))
			return false;
		if (jugador == null) {
			if (other.jugador != null)
				return false;
		} else if (!jugador.equals(other.jugador))
			return false;
		return true;
	}
	
}
