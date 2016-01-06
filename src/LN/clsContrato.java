package LN;

import java.io.Serializable;

/**
 * Clase que solo sirve para relacionar de manera interna un jugador con el agente que cierra su fichaje
 * @author jon.orte
 *
 */
public class clsContrato implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id_jugador;
	private int id_agente;
	
	public int getId_jugador() {
		return id_jugador;
	}
	public void setId_jugador(int id_jugador) {
		this.id_jugador = id_jugador;
	}
	public int getId_agente() {
		return id_agente;
	}
	public void setId_agente(int id_agente) {
		this.id_agente = id_agente;
	}
	
}
