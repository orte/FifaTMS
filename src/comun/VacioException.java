<<<<<<< HEAD
package comun;

/**
 * Excepci�n lanzada cuando se quiera comprobar si un archivo est� vac�o o no
 * @author jon.orte
 *
 */
public class VacioException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
=======
package comun;

public class VacioException extends Exception{

	String clase;
	public VacioException(String cls){
		clase=cls;
	}
	public String getMessage(){
		return "No hay ning�n "+clase+" dado de alta a�n";
	}
}
>>>>>>> 7ec2ec74481d9fd4f244615e66beee7ba8277ac2
