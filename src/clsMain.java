import java.io.IOException;

import LP.Utilidades;
import LP.clsMenu;


public class clsMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion;
		clsMenu menus=new clsMenu();
		do{
			menus.MenuPrincipal();
			opcion=Utilidades.leerEntero();
			switch(opcion){
			case 1: menus.AltaClub();break;
			
			case 2: try {
					menus.ModificarClub();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}break;
				
			case 3: try {
					menus.NuevoFichaje();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}break;
				
			case 4: try{
				menus.CancelarTraspaso();
			} catch (IOException e){
				e.printStackTrace();
			} break;
			
			case 5: try{
				menus.ListaFichajes();
			} catch (IOException e){
				e.printStackTrace();
			} break;
			
			case 6: try{
				menus.RankingFichajes();
			} catch(IOException e){
				e.printStackTrace();
			} break;
			
			case 7: try{
				menus.TopClubes();
			} catch(IOException e){
				e.printStackTrace();
			} break;
			
			case 8: try{
				menus.RankingAgentes();
			} catch(IOException e){
				e.printStackTrace();
			} break;
			
			case 9: try{
				menus.MediaDeEdad();
			} catch(IOException e){
				e.printStackTrace();
			} break;
			
			case 10: System.out.println("Ha elegido salir. Hasta pronto!");
			
			default: System.out.println("Escoja una opción válida"); break;
			}
		}
		while(opcion!=10);
	}

}
