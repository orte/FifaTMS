package LN;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;

/**
 * Clase que relaciona un jugador con el club que ha realizado su fichaje. También incluye detalles como el coste del fichaje (en mill. de €) y
 * el porcentaje de comisión que se lleva el agente. Implementa las interfaces Comparable y Serializable
 * @author jon.orte
 *
 */
public class clsFichaje implements Serializable, Comparable<clsFichaje>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8266056360133085552L;
	private int id_jugador;
	private int id_club;
	private double coste;
	private int comision;
	
	public int getId_jugador() {
		return id_jugador;
	}
	public void setId_jugador(int id_jugador) {
		this.id_jugador = id_jugador;
	}
	public int getId_club() {
		return id_club;
	}
	public void setId_club(int id_club) {
		this.id_club = id_club;
	}
	public double getCoste() {
		return coste;
	}
	public void setCoste(double coste) {
		this.coste = coste;
	}
	public int getComision() {
		return comision;
	}
	public void setComision(int comision) {
		this.comision = comision;
	}
	/**
	 * Método toString que hace varias llamadas a métodos de clsGestor para, con los ID de club y jugador que tiene, conseguir los atributos
	 * de nombre, nacionalidad, etc. de los mismos y con ellos generar un String.
	 * @author jon.orte
	 */
	public String toString(){
		String str=null;
		clsGestor ges=new clsGestor();
		LinkedList<clsJugador> listaJ;
		clsJugador jug=new clsJugador();
		LinkedList<clsClub> listaC;
		clsClub club=new clsClub();
		try {
			listaJ = ges.ListaJugadores();
			jug=listaJ.get(ges.BuscarJugador(id_jugador));
			listaC = ges.ListaClubes();
			club=listaC.get(ges.BuscarClub(id_club));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		str=jug.getNombre()+" "+jug.getApellido()+". , fichado por "+club.getNombre();
		return str;
	}
	/**
	 * Método compareTo de la interfaz Comparable que compara dos fichajes en base a su coste
	 * @author jon.orte
	 */
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
	/**
	 * Método HashCode que genera un código único en base a los ID del jugador y el club involucrados en el fichaje
	 * @author jon.orte
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_club;
		result = prime * result + id_jugador;
		return result;
	}
	/**
	 * Método equals que comprueba si dos fichajes son iguales en base a si los IDs de club y jugador de ambos son iguales
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		clsFichaje other = (clsFichaje) obj;
		if (id_club != other.id_club)
			return false;
		if (id_jugador != other.id_jugador)
			return false;
		return true;
	}
	
	
	
}
