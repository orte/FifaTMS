package comun;

/**
 * Excepción que se generará cuando dos elementos introducidos en el sistema estén duplicados
 * @author jon.orte
 *
 */
public class DuplicadoException extends Exception {

	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Ha introducido un elemento duplicado, inténtelo de nuevo.";
	}

	
}
