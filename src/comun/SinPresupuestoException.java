package comun;

/**
 * Excepción que se lanzará cuando un club intente realizar un fichaje que exceda su presupuesto para fichajes
 * @author jon.orte
 *
 */
public class SinPresupuestoException extends Exception{

	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	public String getMessage(){
		return "El fichaje sobrepasa lo presupuestado";
	}
}
