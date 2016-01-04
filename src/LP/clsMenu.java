package LP;
import java.io.IOException;
import java.util.LinkedList;

import comun.DuplicadoException;
import LN.*;

public class clsMenu {

	clsGestor ges=new clsGestor();
	public void MenuPrincipal(){
		System.out.println("Bienvenido al FIFA Transfer Matching System");
		System.out.println("Introduzca una opción");
		System.out.println("1.- Dar de alta nuevo club");
		System.out.println("2.- Modificar datos de club");
		System.out.println("3.- Nuevo traspaso");
		System.out.println("4.- Cancelar trapaso");
		System.out.println("5.- Lista de clubes dados de alta y los fichajes realizados por cada uno");
		System.out.println("6.- Lista de los traspasos más caros");
		System.out.println("7.- Top 5 de clubes que más han gastado");
		System.out.println("8.- Lista de agentes que más han ganado en comisiones");
		System.out.println("9.- Media de edad de los jugadores traspasados");
		System.out.println("10.- Salir");
	}
	public void AltaClub(){
		NuevoClubFrm a=new NuevoClubFrm();
		a.setVisible(true);
		
	}
	public void ModificarClub(){
		
	}
	public void NuevoFichaje(){
		
	}
	public void CancelarTraspaso(){
		
	}
	public void  ListaFichajes(){
		
	}
	public void RankingFichajes(){
		
	}
	public void TopClubes(){
		
	}
	public void RankingAgentes(){
		
	}
	public void MediaDeEdad(){
		
	}
}
