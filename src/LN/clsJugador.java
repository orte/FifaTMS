package LN;

import java.io.Serializable;

public class clsJugador extends clsPersona implements Serializable{

	private static final long serialVersionUID = -4388794772002112086L;
	private double salario;
	private int edad;
	private int id_jugador;
	private clsClub club;
	private clsAgente agente;
	
	
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public clsClub getClub() {
		return club;
	}
	public void setClub(clsClub club) {
		this.club = club;
	}
	public clsAgente getAgente() {
		return agente;
	}
	public void setAgente(clsAgente agente) {
		this.agente = agente;
	}
	public int getId_jugador() {
		return id_jugador;
	}
	public void setId_jugador(int id_jugador) {
		this.id_jugador = id_jugador;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String string;
		string=nombre+" "+apellido+". ID: "+id_jugador+". Nacionalidad: "+nacionalidad+" Edad: "+edad;
		return string;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_jugador;
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
		clsJugador other = (clsJugador) obj;
		if (id_jugador != other.id_jugador)
			return false;
		return true;
	}
	
}
