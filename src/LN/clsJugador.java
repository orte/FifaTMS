package LN;

import java.io.Serializable;

/**
 * Clase jugador, heredera de clsPersona y que implementa las interfaces Serializable y Comparable. Además de los atributos heredados, tiene
 * otros propios como el salario (en mill. de €), la edad y un ID.
 * @author jon.orte
 *
 */
public class clsJugador extends clsPersona implements Serializable, Comparable<clsJugador>{

	private static final long serialVersionUID = -4388794772002112086L;
	private double salario;
	private int edad;
	private int id_jugador;
	
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
	/**
	 * Método compareTo de la interfaz comparable utilizado para comparar dos jugadores en base a su apellido y, en caso de ser iguales, 
	 * a su nombre.
	 * @author jon.orte
	 */
	@Override
	public int compareTo(clsJugador o) {
		// TODO Auto-generated method stub
		int comp=this.getApellido().compareTo(o.getApellido());
		if (comp==0){
			comp=this.getNombre().compareTo(o.getNombre());
		}
		return comp;
	}
	
	
	
}
