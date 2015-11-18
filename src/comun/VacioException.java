package comun;

public class VacioException extends Exception{

	String clase;
	public VacioException(String cls){
		clase=cls;
	}
	public String getMessage(){
		return "No hay ningún "+clase+" dado de alta aún";
	}
}
