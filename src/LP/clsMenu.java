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
		/*
		 * Le pide al usuario que meta los atributos de nombre, país, y presupuesto para fichajes de un club, para luego llamar
		 * al método de clsGestor NuevoClub para que cree un nuevo objeto clsClub.
		 */
		int t=0;
		do{
			System.out.println("Introduzca los datos del nuevo club");
			System.out.println("Nombre: ");
			String nombre=Utilidades.leerCadena();
			System.out.println("País: ");
			String pais=Utilidades.leerCadena();
			System.out.println("Presupuesto para fichajes: ");
			double pres=Utilidades.leerReal();
			clsClub nuevo;
			try {
				nuevo = ges.NuevoClub(nombre, pais, pres);
				System.out.println(nuevo.getNombre()+" ha sido dado de alta en el sistema");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DuplicadoException e) {
				// TODO Auto-generated catch block
				e.getMessage();
				t=-1;
			}
		}
		while(t==-1);
		
	}
	public void ModificarClub() throws IOException{
		/*
		 * Es la segunda opción del programa, saca por pantalla una lista de todos los clubes dados de alta para luego pedirle al
		 * usuario que introduzca el nombre del club que quiere modificar. Se le pedirá que lo introduzca tantas veces como el 
		 * nombre introducido no coincida con ninguno de la lista. Una vez elegido el club que se quiere modificar, se le pedirá al
		 * usuario que vuelva a meter los datos del club, para luego llamar al método ModificarClub del objeto clsGestor. 
		 */
		System.out.println("Introduzca el nombre del club que desea modificar");
		LinkedList<clsClub> lista=ges.ListaClubes();
		for(clsClub aux:lista){
			System.out.println(aux.getNombre());
		}
		int i=-1;
		String nombre;
		do{
			nombre=Utilidades.leerCadena();
			i=ges.BuscarClub(nombre);
			if(i==-1){
				System.out.println("El nombre que ha introducido no es correcto, inténtelo de nuevo");
			}
		}
		while(i==-1);
		int t=0;
		do{
			System.out.println("Introduzca ahora los nuevos datos del club seleccionado");
			System.out.println("Nombre: ");
			String nom=Utilidades.leerCadena();
			System.out.println("País: ");
			String pais=Utilidades.leerCadena();
			System.out.println("Presupuesto para fichajes: ");
			double pres=Utilidades.leerReal();
			try {
				ges.ModificarClub(lista.get(i), i, nom, pais, pres);
				System.out.println("Se han modificado los datos de "+nom);
			} catch (DuplicadoException e) {
				// TODO Auto-generated catch block
				e.getMessage();
				t=-1;
			}
		}
		while(t==-1);
	}
	public void NuevoFichaje() throws IOException{
		/*
		 * Primero, se le mostrará al usuario una lista de los clubes dados de alta para que introduzca el nombre del que va a realizar
		 * el traspaso. Luego se le pedirá que de de alta un nuevo jugador, para que luego meta sus atributos y llamar al método de la 
		 * clase clsGestor NuevoJugador. Luego se le pedirá que introduzca el número de licencia del agente, sacando una lista de los
		 * agentes dados de alta por pantalla. En el caso de que no haya ninguno, se le pedirá que introduzca los datos de un agente 
		 * nuevo, pasando esos atributos luego al método NuevoAgente de la clase clsGestor. Luego se introducirán los datos del propio
		 * traspaso, para luego llamar al método de gestor NuevoFichaje. Por último, se modificarán el club, jugador y agente involucrados en
		 * el traspaso, llamando para ello a ModificarJugador, ModificarClub y AñadirFichaje
		 */
		System.out.println("Introduzca el nombre del club que va a realizar el traspaso: ");
		LinkedList<clsClub> listaC=ges.ListaClubes();
		for(clsClub aux:listaC){
			System.out.println(aux.getNombre());
		}
		int indxC=-1;
		String nombre;
		do{
			nombre=Utilidades.leerCadena();
			indxC=ges.BuscarClub(nombre);
			if(indxC==-1){
				System.out.println("El nombre que ha introducido no es correcto, inténtelo de nuevo");
			}
		}
		while(indxC==-1);
		int t=0;
		clsJugador jug=null;
		do{
			System.out.println("Introduzca ahora los datos del jugador que se va a fichar");
			System.out.println("Nombre: ");
			String nomJug=Utilidades.leerCadena();
			System.out.println("Apellido: ");
			String apJug=Utilidades.leerCadena();
			System.out.println("Nacionalidad: ");
			String nacJug=Utilidades.leerCadena();
			System.out.println("Edad: ");
			int edadJug=Utilidades.leerEntero();
			System.out.println("ID del jugador: ");
			int idJug=Utilidades.leerEntero();
			try {
				jug = ges.NuevoJugador(nomJug, apJug, nacJug, edadJug, idJug);
				System.out.println("Ha decidido fichar a "+jug.getNombre());
			} catch (DuplicadoException e) {
				// TODO Auto-generated catch block
				e.getMessage();
				t=-1;
			}
		}
		while(t==-1);
		System.out.println("Introduzca ahora el número de licencia del agente que va a cerrar el traspaso");
		LinkedList<clsAgente> listaAg=ges.ListaAgentes();
		int indxAg=-1;
		if (listaAg.size()==0){
			System.out.println("Dado que no hay agentes dados de alta en el sistema, introduzca manualmente los datos del agente");
			System.out.println("Nombre: ");
			String nomAg=Utilidades.leerCadena();
			System.out.println("Apellido: ");
			String apAg=Utilidades.leerCadena();
			System.out.println("Nacionalidad: ");
			String nacAg=Utilidades.leerCadena();
			System.out.println("Número de licencia");
			int idAg=Utilidades.leerEntero();
			clsAgente ag;
			try {
				ag = ges.NuevoAgente(nomAg, apAg, nacAg, idAg);
				listaAg=ges.ListaAgentes();
				indxAg=0;
				System.out.println(ag.getNombre()+" "+ag.getApellido()+" será el agente que cierre el traspaso");
			} catch (DuplicadoException e) {
				// TODO Auto-generated catch block
				e.getMessage();
			}
			
		}
		else{
			for(clsAgente aux:listaAg){
				System.out.println(aux.getNombre()+" "+aux.getApellido());
			}
			System.out.println("Pulse 1 si desea introducir los datos de un nuevo agente. Si no, pulse ENTER");
			int x=Utilidades.leerEntero();
			if(x==1){
				do{
					System.out.println("Nombre: ");
					String nomAg=Utilidades.leerCadena();
					System.out.println("Apellido: ");
					String apAg=Utilidades.leerCadena();
					System.out.println("Nacionalidad: ");
					String nacAg=Utilidades.leerCadena();
					System.out.println("Número de licencia");
					int idAg=Utilidades.leerEntero();
					clsAgente ag;
					try{
					ag=ges.NuevoAgente(nomAg, apAg, nacAg, idAg);
					listaAg=ges.ListaAgentes();
					indxAg=listaAg.indexOf(ag);
					System.out.println(ag.getNombre()+" "+ag.getApellido()+" será el agente que cierre el traspaso");
					} catch(DuplicadoException e){
						e.getMessage();
						t=-1;
					}
				}
				while(t==-1);
			}
			int lic;
			do{
				lic=Utilidades.leerEntero();
				indxAg=ges.BuscarAgente(lic);
				if(indxAg==-1){
					System.out.println("El nombre que ha introducido no es correcto, inténtelo de nuevo");
				}
			}
			while(indxAg==-1);
		}
		System.out.println("Introduzca ahora los detalles del traspaso: ");
		System.out.println("Coste del jugador: ");
		double cos=Utilidades.leerReal();
		System.out.println("Duración del contrato: ");
		int dur=Utilidades.leerEntero();
		System.out.println("Salario del jugador: ");
		double sal=Utilidades.leerReal();
		System.out.println("Comisión para el agente (%): ");
		int com=Utilidades.leerEntero();
		clsFichaje fich;
		try {
			fich = ges.NuevoFichaje(listaC.get(indxC), jug, cos, dur, com, sal);
			ges.ModificarAgente(listaAg.get(indxAg), indxAg, jug, com, cos);
			ges.ModificarJugador(jug, listaC.get(indxC), listaAg.get(indxAg), sal);
			ges.AñadirFichaje(listaC.get(indxC), indxC, jug, cos);
			System.out.println("Se ha realizado con éxito el fichaje de "+fich.getJugador().getNombre()+" "+ fich.getJugador().getApellido()+" por parte de "+fich.getClub().getNombre());
		} catch (DuplicadoException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		
		
	}
	public void CancelarTraspaso() throws IOException{
		/*
		 * Saca por pantalla una lista de jugadores traspasados hasta la fecha. Se le pedirá al usuario que introduzca el ID del jugador
		 * cuyo traspaso se quiere anular, y se le pedirá eso mientras no coincida el introducido con ninguno de la lista. Una vez 
		 * conseguido el ID del jugador, se buscará el fichaje que tiene ese ID llamando al método BuscarFichaje, que nos devolverá el
		 * índice del fichaje dentro de la lista, para luego pasárselo al método BorrarFichaje para que elimine el mismo.
		 */
		System.out.println("Introduzca el ID del jugador cuyo traspaso desea anular: ");
		LinkedList<clsFichaje> lista=ges.ListaFichajes();
		for(clsFichaje aux:lista){
			System.out.println(aux.toString());
		}
		int indxJ=-1;
		int id;
		do{
			id=Utilidades.leerEntero();
			indxJ=ges.BuscarFichaje(id);
			if(indxJ==-1){
				System.out.println("El ID que ha introducido no es correcto, inténtelo de nuevo");
			}
		}
		while(indxJ==-1);
		clsFichaje fich=lista.get(indxJ);
		System.out.println("Si desea cancelar el fichaje de "+fich.getJugador().getNombre()+" "+fich.getJugador().getApellido()+" por el "+fich.getClub().getNombre()+" pulse ENTER");
		String k=Utilidades.leerCadena();
		ges.BorrarFichaje(fich, indxJ);
		System.out.println("El fichaje ha sido cancelado con éxito");
		
	}
	public void  ListaFichajes() throws IOException{
		/*
		 * Llama al métdodo de gestor ListaClubes para recibir la lista de todos los clubes, para luego sacarlos por pantalla, cada uno
		 * seguido de los fichajes que ha realizado el club.
		 */
		LinkedList<clsClub> listC=ges.ListaClubes();
		for(clsClub aux:listC){
			System.out.println(aux.getNombre()+". Fichajes realizados: ");
			for (clsJugador au:aux.getFichajes()){
				System.out.println(au.getNombre()+" "+au.getApellido());
			}
		}
	}
	public void RankingFichajes() throws IOException{
		/*
		 * Llama al método de gestor OrdenarFichajes, que devuelve todos los fichajes ordenados de más caro a más barato y luego los saca
		 * por pantalla.
		 */
		LinkedList<clsFichaje> ranking=ges.OrdenarFichajes();
		for(clsFichaje aux:ranking){
			System.out.println(aux.toString());
		}
	}
	public void TopClubes() throws IOException{
		/*
		 * Llama al  método del gestor OrdenarClubes, que devuelve una lista de todos los clubes ordenados del que más ha gastado al que
		 * menos. Luego, saca por pantalla solo los 5 que más han gastado.
		 */
		LinkedList<clsClub> top=ges.OrdenarClubes();
		for(int i=0; i<5; i++){
			System.out.println(top.get(i).getNombre()+". Dinero gastado: "+top.get(i).getDinero_gastado()+" millones de €");
			if (top.size()<i+1){
				break;
			}
		}
	}
	public void RankingAgentes() throws IOException{
		/*
		 * Llama al método del gestor OrdenarAgentes, que devuelve una lista de todos los agentes ordenados del que más dinero ha recibido
		 * en comisiones al que menos y los saca por pantalla.
		 */
		LinkedList<clsAgente> ranking=ges.OrdenarAgentes();
		for(clsAgente aux:ranking){
			System.out.println(aux.toString()+". Dinero recibido en comisiones: "+aux.getDinero_recibido()+" millones de €");
		}
	}
	public void MediaDeEdad() throws IOException{
		/*
		 * Llama al método del gestor PromedioEdad, que devuelve una cifra con decimales con la media de edad de todos los jugadores que 
		 * se han fichado.
		 */
		double prm=ges.PromedioEdad();
		System.out.println("El promedio de edad de los jugadores fichados es de "+prm+" años.");
	}
}
