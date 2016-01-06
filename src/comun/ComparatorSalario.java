package comun;

import java.util.Comparator;

import LN.clsJugador;


/**
 * Clase comparator utilizada para comparar dos objetos de la clase Jugador en base a su salario
 * @author jon.orte
 *
 */
public class ComparatorSalario implements Comparator<clsJugador>{

	/*
	 * (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(clsJugador o1, clsJugador o2) {
		// TODO Auto-generated method stub
		if(o1.getSalario()>o2.getSalario()){
			return 1;
		} else if(o1.getSalario()<o2.getSalario()){
			return -1;
		} else{
			return 0;
		}
	}

}
