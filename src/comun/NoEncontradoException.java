package comun;

public class NoEncontradoException extends Exception{
	
	public String getMessage(){
		return "No se ha encontrado el elemento buscado, intÚntelo de nuevo";
	}

}
