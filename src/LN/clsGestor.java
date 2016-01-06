package LN;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import LD.*;
import comun.ComparatorSalario;
import comun.SinPresupuestoException;
import comun.VacioException;
import comun.clsConstantes.enFicDatos;
import comun.DuplicadoException;
/**
 * clase gestor que hará todas las operaciones, haciendo de intermediario entre la LD y la LP
 * @author jon.orte
 *
 */
public class clsGestor {
	clsDatos dat=new clsDatos();

	/**
	 * Recibe por parámetro los atributos de nombre, país y presupuesto necesarios para crear un nuevo objeto clsClub. Luego, llamando
	 * a los métodos del objeto dat de tipo clsDatos los guarda en su fichero correspondiente
	 * @param nombre: String con el nombre del club
	 * @param pais: String con el país de origen del club
	 * @param presup: double con el presupuesto para fichajes del club en millones de €
	 * @return el nuevo objeto clsClub creado
	 * @throws IOException
	 * @throws DuplicadoException: Lanzada en caso de que el club creado esté repetido
	 */
	public clsClub NuevoClub(String nombre, String pais, double presup) throws IOException, DuplicadoException{
	
		clsClub nuevo=new clsClub();
		nuevo.setNombre(nombre);
		nuevo.setPais(pais);
		nuevo.setPresupuesto(presup);
		LinkedList<clsClub> temp=ListaClubes();
		int id=0;
		for(clsClub aux:temp){
			if(aux.getId_club()>=id){
				id=aux.getId_club()+1;
			}
		}
		nuevo.setId_club(id);
		HashSet<clsClub> set=new HashSet<clsClub>();
		set.addAll(temp);
		if (set.add(nuevo)==false){
			DuplicadoException e=new DuplicadoException();
			throw e;
		}
		else{
			enFicDatos f=enFicDatos.DAT_CLUBES;
			dat.ComenzarSave(f);
			dat.Save(nuevo);
			dat.TerminarSave();
			return nuevo;
		}
	}
	/**
	 * Recibe por parámetro los atributos de nombre, apellido, nacionalidad, edad y número de ID necesarios para crear un nuevo objeto
	 * clsJugador. Luego, llamando a los métodos del objeto dat de tipo clsDatos los guarda en su fichero correspondiente
	 * @param nombre: String con el nombre del nuevo jugador
	 * @param apellido: String con el apellido del nuevo jugador
	 * @param nac: String con la nacionalidad del nuevo jugador
	 * @param edad: int que indica la edad del nuevo jugador
	 * @return el nuevo objeto clsJugador creado
	 * @throws IOException
	 * @throws DuplicadoException: Lanzada en caso de que el jugador creado esté repetido
	 */
	public clsJugador NuevoJugador(String nombre, String apellido, String nac, int edad) throws IOException, DuplicadoException{
	
		clsJugador nuevo=new clsJugador();
		nuevo.setNombre(nombre);
		nuevo.setApellido(apellido);
		nuevo.setNacionalidad(nac);
		nuevo.setEdad(edad);
		LinkedList<clsJugador> temp=ListaJugadores();
		int id=0;
		for(clsJugador aux:temp){
			if(aux.getId_jugador()>=id){
				id=aux.getId_jugador()+1;
			}
		}
		nuevo.setId_jugador(id);
		HashSet<clsJugador> set=new HashSet<clsJugador>();
		set.addAll(temp);
		if (set.add(nuevo)==false){
			DuplicadoException e=new DuplicadoException();
			throw e;
		}
		else{
			enFicDatos f=enFicDatos.DAT_JUGADORES;
			dat.ComenzarSave(f);
			dat.Save(nuevo);
			dat.TerminarSave();
			return nuevo;
		}
	}
	/**
	 * Recibe por parámetro los atributos de nombre, apellido, nacionalidad y número de licencia necesarios para crear un nuevo objeto
	 * clsAgente. Luego, llamando a los métodos del objeto dat de tipo clsDatos los guarda en su fichero correspondiente
	 * @param nombre: String con el nombre del nuevo agente
	 * @param apellido: String con el apellido del nuevo agente
	 * @param nac: String con la nacionalidad del nuevo agente
	 * @return el nuevo objeto clsAgente creado
	 * @throws IOException
	 * @throws DuplicadoException: Lanzada en caso de que el agente creado esté repetido
	 */
	public clsAgente NuevoAgente(String nombre, String apellido, String nac) throws IOException, DuplicadoException{
	
		clsAgente nuevo=new clsAgente();
		nuevo.setNombre(nombre);
		nuevo.setApellido(apellido);
		nuevo.setNacionalidad(nac);
		LinkedList<clsAgente> temp=ListaAgentes();
		int num_lic=0;
		for(clsAgente aux:temp){
			if(aux.getNum_licencia()>=num_lic){
				num_lic=aux.getNum_licencia()+1;
			}
		}
		nuevo.setNum_licencia(num_lic);
		HashSet<clsAgente> set=new HashSet<clsAgente>();
		set.addAll(temp);
		if (set.add(nuevo)==false){
			DuplicadoException e=new DuplicadoException();
			throw e;
		}
		else{
			enFicDatos f=enFicDatos.DAT_AGENTES;
			dat.ComenzarSave(f);
			dat.Save(nuevo);
			dat.TerminarSave();
			return nuevo;
		}
	}
	/**
	 * Recibe por parámetro los atributos de ID de club y jugador, coste y comisión del agente necesarios para crear un nuevo objeto 
	 * clsFichaje. Luego, llamando a los métodos del objeto dat de tipo clsDatos los guarda en 
	 * su fichero correspondiente
	 * @param id_club: int con el ID del club que va a realizar el fichaje
	 * @param id_jug: int con el ID del jugador que va a ser fichado
	 * @param cos: double con el coste del fichaje en millones de €
	 * @param com: int con el porcentaje de comisión que se llevará el agente
	 * @return el nuevo objeto clsFichaje creado
	 * @throws IOException
	 * @throws DuplicadoException: Lanzada en caso de que el fichaje esté repetido
	 */
	public clsFichaje NuevoFichaje(int id_club, int id_jug, double cos, int com) throws IOException, DuplicadoException{
		/*
		 * Recibe por parámetro los atributos de club, jugador, coste, duración del contrato, comisión del agente y salario del jugador
		 * necesarios para crear un nuevo objeto clsFichaje. Luego, llamando a los métodos del objeto dat de tipo clsDatos los guarda en 
		 * su fichero correspondiente
		 */
		clsFichaje nuevo=new clsFichaje();
		nuevo.setId_club(id_club);
		nuevo.setId_jugador(id_jug);
		nuevo.setCoste(cos);
		nuevo.setComision(com);
		LinkedList<clsFichaje> temp=ListaFichajes();
		HashSet<clsFichaje> set=new HashSet<clsFichaje>();
		set.addAll(temp);
		if (set.add(nuevo)==false){
			DuplicadoException e=new DuplicadoException();
			throw e;
		}
		else{
			enFicDatos f=enFicDatos.DAT_FICHAJES;
			dat.ComenzarSave(f);
			dat.Save(nuevo);
			dat.TerminarSave();
			return nuevo;
		}
	}
	/**
	 * Recibe los IDs del jugador y del agente y crea con ellos un nuevo objeto de tipo clsContrato. Luego, llamando a los métodos 
	 * del objeto dat de tipo clsDatos lo guarda en su fichero correspondiente
	 * @param id_jug: int con el ID del jugador
	 * @param id_agent: int con el ID del agente
	 * @return: el nuevo objeto clsContrato creado
	 */
	public clsContrato NuevoContrato(int id_jug, int id_agent){
		clsContrato nuevo=new clsContrato();
		nuevo.setId_agente(id_agent);
		nuevo.setId_jugador(id_jug);
		enFicDatos f=enFicDatos.DAT_CONTRATOS;
		dat.ComenzarSave(f);
		dat.Save(nuevo);
		dat.TerminarSave();
		return nuevo;
	}
	/**
	 * Método que llama a la lógica de datos para recibir una lista de clubes guardados en fichero
	 * @return LinkedList con los clubes guardados
	 * @throws IOException
	 */
	public LinkedList<clsClub> ListaClubes() throws IOException{
		LinkedList<clsClub> lista=new LinkedList<clsClub>();
		enFicDatos f=enFicDatos.DAT_CLUBES;
		dat.ComenzarRead(f);
		LinkedList<Serializable> l=dat.Read();
		dat.TerminarRead();
		for (Serializable aux:l){
			lista.add((clsClub)aux);
		}
		return lista;
	}
	/**
	 * Método que llama a la lógica de datos para recibir una lista de jugadores guardados en fichero
	 * @return LinkedList con los jugadores guardados
	 * @throws IOException
	 */
	public LinkedList<clsJugador> ListaJugadores() throws IOException{
		LinkedList<clsJugador> lista=new LinkedList<clsJugador>();
		enFicDatos f=enFicDatos.DAT_JUGADORES;
		dat.ComenzarRead(f);
		LinkedList<Serializable> l=dat.Read();
		dat.TerminarRead();
		for (Serializable aux:l){
			lista.add((clsJugador)aux);
		}
		return lista;
	}
	/**
	 * Método que llama a la lógica de datos para recibir una lista de agentes guardados en fichero
	 * @return LinkedList con los agentes guardados
	 * @throws IOException
	 */
	public LinkedList<clsAgente> ListaAgentes() throws IOException{
		LinkedList<clsAgente> lista=new LinkedList<clsAgente>();
		enFicDatos f=enFicDatos.DAT_AGENTES;
		dat.ComenzarRead(f);
		LinkedList<Serializable> l=dat.Read();
		dat.TerminarRead();
		for (Serializable aux:l){
			lista.add((clsAgente)aux);
		}
		return lista;
	}
	/**
	 * Método que llama a la lógica de datos para recibir una lista de fichajes guardados en fichero
	 * @return LinkedList con los fichajes guardados
	 * @throws IOException
	 */
	public LinkedList<clsFichaje> ListaFichajes() throws IOException{
		LinkedList<clsFichaje> lista=new LinkedList<clsFichaje>();
		enFicDatos f=enFicDatos.DAT_FICHAJES;
		dat.ComenzarRead(f);
		LinkedList<Serializable> l=dat.Read();
		dat.TerminarRead();
		for (Serializable aux:l){
			lista.add((clsFichaje)aux);
		}
		return lista;
	}
	/**
	 * Método que llama a la lógica de datos para recibir una lista de contratos guardados en fichero
	 * @return LinkedList con los contratos guardados
	 * @throws IOException
	 */
	public LinkedList<clsContrato> ListaContratos() throws IOException{
		LinkedList<clsContrato> lista=new LinkedList<clsContrato>();
		enFicDatos f=enFicDatos.DAT_CONTRATOS;
		dat.ComenzarRead(f);
		LinkedList<Serializable> l=dat.Read();
		dat.TerminarRead();
		for (Serializable aux:l){
			lista.add((clsContrato)aux);
		}
		return lista;
	}
	/**
	 * Método que recibe un ID de un club, lo busca entre los que están guardados en el fichero de clubes
	 * y devuelve un int indicando el índice de la lista de clubes donde se encuentra el club con ese ID
	 * @author jon.orte
	 * @param ID: id del club buscado
	 * @return int con el índice de la lista donde está el club
	 * @throws IOException
	 */
	public int BuscarClub(int id) throws IOException {
		int i=-1;
		LinkedList<clsClub> lista=ListaClubes();
		for (clsClub aux:lista){
			if(aux.getId_club()==id){
				i=lista.indexOf(aux);
				break;
			}
		}
		return i;
	}
	/**
	 * Método que recibe un ID de un agente, lo busca entre los que están guardados en el fichero de agentes
	 * y devuelve un int indicando el índice de la lista de agentes donde se encuentra el agente con ese ID
	 * @author jon.orte
	 * @param ID: id del agente buscado
	 * @return int con el índice de la lista donde está el agente
	 * @throws IOException
	 */
	public int BuscarAgente(int licen) throws IOException{
		int i=-1;
		LinkedList<clsAgente> lista=ListaAgentes();
		for(clsAgente aux:lista){
			if (aux.getNum_licencia()==licen){
				i=lista.indexOf(aux);
				break;
			}
		}
		return i;
	}
	/**
	 * Método que recibe un ID de un jugador, lo busca entre los que están guardados en el fichero de jugadores
	 * y devuelve un int indicando el índice de la lista de jugadores donde se encuentra el jugador con ese ID
	 * @author jon.orte
	 * @param ID: id del jugador buscado
	 * @return int con el índice de la lista donde está el jugador
	 * @throws IOException
	 */
	public int BuscarJugador(int id) throws IOException{
		int i=-1;
		LinkedList<clsJugador> lista=ListaJugadores();
		for(clsJugador aux:lista){
			if (aux.getId_jugador()==id){
				i=lista.indexOf(aux);
				break;
			}
		}
		return i;
	}
	/**
	 * Método que recibe un ID de un fichaje, lo busca entre los que están guardados en el fichero de fichajes
	 * y devuelve un int indicando el índice de la lista de fichajes donde se encuentra el fichaje con ese ID
	 * @author jon.orte
	 * @param ID: id del fichaje buscado
	 * @return int con el índice de la lista donde está el fichaje
	 * @throws IOException
	 */
	public int BuscarFichaje(int id_jug) throws IOException{
		int i=-1;
		LinkedList<clsFichaje> lista=ListaFichajes();
		for(clsFichaje aux:lista){
			if (aux.getId_jugador()==id_jug){
				i=lista.indexOf(aux);
				break;
			}
		}
		return i;
	}
	/**
	 * Método que recibe un ID de un contrato, lo busca entre los que están guardados en el fichero de contratos
	 * y devuelve un int indicando el índice de la lista de contratos donde se encuentra el contrato con ese ID
	 * @author jon.orte
	 * @param ID: id del contrato buscado
	 * @return int con el índice de la lista donde está el contrato
	 * @throws IOException
	 */
	public int BuscarContrato(int id_jug) throws IOException{
		int i=-1;
		LinkedList<clsContrato> lista=ListaContratos();
		for(clsContrato aux:lista){
			if (aux.getId_jugador()==id_jug){
				i=lista.indexOf(aux);
				break;
			}
		}
		return i;
	}
	/**
	 * Método que recibe un club que va a ser modificado y una serie de atributos, que son 
	 * asignados a ese club y guardados en el fichero correspondiente.
	 * @author jon.orte
	 * @param club: objeto de la clase alumno que se desea modificar
	 * @param nombre: nombre modificado
	 * @param pais: String con el país modificado
	 * @param pres: double con el presupuesto modificado
	 * @throws IOException
	 * @throws DuplicadoException: excepción lanzada si el club modificado coincide con alguno guardado anteriormente
	 */
	public void ModificarClub(clsClub club, String nombre, String pais, double pres) throws IOException, DuplicadoException{
		LinkedList<clsClub> lista=ListaClubes();
		HashSet<clsClub> set=new HashSet<clsClub>();
		set.addAll(lista);
		int indx=lista.indexOf(club);
		set.remove(club);
		System.out.println(club.toString());
		club.setNombre(nombre);
		club.setPais(pais);
		club.setPresupuesto(pres);
		set.addAll(lista);
		if(set.add(club)==false){
			DuplicadoException e=new DuplicadoException();
			throw e;
		}
		else{
			lista.set(indx, club);
			enFicDatos f=enFicDatos.DAT_CLUBES;
			dat.ResetFile(f);
			dat.ComenzarSave(f);
			for(clsClub aux:lista){
				dat.Save(aux);
			}
			dat.TerminarSave();
		}
	}
	/**
	 * Método que sirve para modificar un objeto clsClub una vez se ha realizado el fichaje, restando el coste del fichaje al presupuesto
	 * y sumándolo al total de dinero gastado.
	 * @param club: club que ha realizado el fichaje
	 * @param indx: índice de la lista de clubes donde se encuentra el club
	 * @param cos: coste del fichjae
	 * @throws IOException
	 * @throws SinPresupuestoException: Será lanzada en el caso de que el coste del fichaje exceda lo que queda en el presupuesto
	 */
	public void AñadirFichaje(clsClub club, int indx, double cos) throws IOException, SinPresupuestoException{
		
		if (club.getPresupuesto()<cos){
			throw new SinPresupuestoException();
		} else{
			club.setPresupuesto(club.getPresupuesto()-cos);
			club.setDinero_gastado(club.getDinero_gastado()+cos);
			LinkedList<clsClub> lista=ListaClubes();
			lista.set(indx, club);
			enFicDatos f=enFicDatos.DAT_CLUBES;
			dat.ResetFile(f);
			dat.ComenzarSave(f);
			for(clsClub aux:lista){
				dat.Save(aux);
			}
			dat.TerminarSave();
		}
	}
	/**
	 * Método que sirve para establecer a un jugador el salario que va a apercibir con motivo de un fichaje
	 * @param jug: jugador fichado
	 * @param salario: salario para el jugador
	 * @throws IOException
	 */
	public void EstablecerSalario(clsJugador jug, double salario) throws IOException{
		LinkedList<clsJugador> lista=ListaJugadores();
		int indx=BuscarJugador(jug.getId_jugador());
		jug.setSalario(salario);
		lista.set(indx, jug);
		enFicDatos f=enFicDatos.DAT_JUGADORES;
		dat.ResetFile(f);
		dat.ComenzarSave(f);
		for(clsJugador aux:lista){
			dat.Save(aux);
		}
		dat.TerminarSave();
	}
	/**
	 * Método que sirve para que un agente, una vez se cierre un fichaje, reciba el dinero que le corresponde como comisión
	 * @param ag: agente que va a cerrar el traspaso
	 * @param indx: índice de la lista de agentes donde se encuentra el agente
	 * @param com: porcentaje de comisión sobre el coste total del fichaje que va a recibir el agente
	 * @param cos: coste total del fichaje en millones de €
	 * @throws IOException
	 */
	public void ModificarAgente(clsAgente ag, int indx, int com, double cos) throws IOException{
		ag.setDinero_recibido(ag.getDinero_recibido()+((com*cos))/100);
		ag.setTraspasos(ag.getTraspasos()+1);
		LinkedList<clsAgente> lista=ListaAgentes();
		lista.set(indx, ag);
		enFicDatos f=enFicDatos.DAT_AGENTES;
		dat.ResetFile(f);
		dat.ComenzarSave(f);
		for(clsAgente aux:lista){
			dat.Save(aux);
		}
		dat.TerminarSave();
	}
	/**
	 * Método que recibe un fichaje por parámetro y lo borra del fichero de fichajes
	 * @param fic: fichaje que va a ser borrado
	 * @throws IOException
	 */
	public void BorrarFichaje(clsFichaje fic) throws IOException{
		LinkedList<clsFichaje> lista=ListaFichajes();
		lista.remove(fic);
		enFicDatos f=enFicDatos.DAT_FICHAJES;
		dat.ResetFile(f);
		dat.ComenzarSave(f);
		for(clsFichaje aux:lista){
			dat.Save(aux);
		}
		dat.TerminarSave();
	}
	/**
	 * Método que recibe por parámetro el ínidce de la lista de jugadores donde se encuentra un jugador concreto y lo borra de su fichero
	 * @param indx: índice de la lista de jugadores donde se encuentra el jugador
	 * @throws IOException
	 */
	public void BorrarJugador(int indx) throws IOException{
		LinkedList<clsJugador> lista=ListaJugadores();
		lista.remove(indx);
		enFicDatos f=enFicDatos.DAT_JUGADORES;
		dat.ResetFile(f);
		dat.ComenzarSave(f);
		for(clsJugador aux:lista){
			dat.Save(aux);
		}
		dat.TerminarSave();
	}
	/**
	 * Método que recibe por parámetro el ínidce de la lista de contratos donde se encuentra un contrato concreto y lo borra de su fichero
	 * @param indx: índice de la lista de contratos donde se encuentra el contrato
	 * @throws IOException
	 */
	public void BorrarContrato(int indx) throws IOException{
		LinkedList<clsContrato> lista=ListaContratos();
		lista.remove(indx);
		enFicDatos f=enFicDatos.DAT_CONTRATOS	;
		dat.ResetFile(f);
		dat.ComenzarSave(f);
		for(clsContrato aux:lista){
			dat.Save(aux);
		}
		dat.TerminarSave();
	}
	/**
	 * Método que coge los fichajes guardados y los ordena en una LinkedList en función de su coste
	 * @return LinkedList de fichajes ordenada
	 * @throws IOException
	 */
	public LinkedList <clsFichaje> OrdenarFichajes() throws IOException{
		LinkedList <clsFichaje> lista=ListaFichajes();
		clsFichaje temp=null;
		for(int i=0; i<(lista.size()-1); i++){
			for(int j=i+1; j<lista.size(); j++){
				if(lista.get(j).getCoste()>lista.get(i).getCoste()){
					temp=lista.get(i);
					lista.set(i, lista.get(j));
					lista.set(j, temp);
				}
			}
		}
		return lista;
	}
	/**
	 * Método que coge los clubes guardados y los ordena en una LinkedList en función del dinero que han gastado
	 * @return LinkedList de clubes ordenada
	 * @throws IOException
	 */
	public LinkedList<clsClub> OrdenarClubes() throws IOException{
		LinkedList<clsClub> lista=ListaClubes();
		clsClub temp=null;
		for(int i=0; i<(lista.size()-1); i++){
			for(int j=i+1; j<lista.size(); j++){
				if(lista.get(j).getDinero_gastado()>lista.get(i).getDinero_gastado()){
					temp=lista.get(i);
					lista.set(i, lista.get(j));
					lista.set(j, temp);
				}
			}
		}
		return lista;
	}
	/**
	 * Método que coge los agentes guardados y los ordena en una LinkedList en función del dinero que han recibido en comisiones
	 * @return LinkedList de agentes ordenada
	 * @throws IOException
	 */
	public LinkedList<clsAgente> OrdenarAgentes() throws IOException{
		LinkedList<clsAgente> lista=ListaAgentes();
		clsAgente temp=null;
		for (int i=0; i<(lista.size()-1); i++){
			for (int j=i+1; j<lista.size(); j++){
				if (lista.get(j).getDinero_recibido()>lista.get(i).getDinero_recibido()){
					temp=lista.get(i);
					lista.set(i, lista.get(j));
					lista.set(j, temp);
				}
			}
		}
		return lista;
	}
	/**
	 * Método que coge los Jugadores guardados y los ordena en una LinkedList alfabéticamente por apellido
	 * @return LinkedList de jugadores ordenada alfabéticamente
	 * @throws IOException
	 */
	public LinkedList<clsJugador> OrdenarAlf() throws IOException{
		LinkedList<clsJugador> temp=ListaJugadores();
		TreeSet<clsJugador> tree=new TreeSet<clsJugador>();
		tree.addAll(temp);
		LinkedList<clsJugador> ordenado=new LinkedList<clsJugador>();
		for(clsJugador aux:tree){
			ordenado.add(aux);
		}
		return ordenado;
	}
	/**
	 * Método que coge los Jugadores guardados y los ordena en una LinkedList por su salario, utilizando un objeto tipo Comparator
	 * @return LinkedList de jugadores ordenada por salario
	 * @throws IOException
	 */
	public LinkedList<clsJugador> OrdenarSalario() throws IOException{
		LinkedList<clsJugador> ordenado=ListaJugadores();
		Collections.sort(ordenado, new ComparatorSalario());
		return ordenado;
	}
	/**
	 * Método que coge una LinkedList cualquiera y lanza una excepción en el caso de que esté vacía
	 * @param lista: LinkedList que se va a comprobar si está vacía
	 * @throws VacioException: Excepción que se lanzará en el caso de que la lista esté vacía
	 */
	public void ComprobarVacio(LinkedList<?> lista) throws VacioException{
		if (lista.isEmpty()){
			throw new VacioException();
		}
	}
	/**
	 * Devuelve una LinkedList de Strings que contendrá en cada posición un club y los ficajes que ha realizado
	 * @return LinkedList con esos elementos
	 * @throws IOException
	 */
	public LinkedList<String> FichajesPorClubes() throws IOException{
		LinkedList<String> lista=new LinkedList<String>();
		LinkedList<clsFichaje> fichajes=ListaFichajes();
		LinkedList<clsClub> clubes=ListaClubes();
		for(clsClub aux:clubes){
			String elem=aux.getNombre();
			for(clsFichaje aux1:fichajes){
				if(aux1.getId_club()==aux.getId_club()){
					elem=elem+"\n: "+aux1.toString();
				}
			}
			lista.add(elem);
		}
		return lista;
	}
	/**
	 * Devuelve una LinkedList de Strings que contendrá en cada posición un país y el dinero que han gastado sus clubes en fichajes
	 * @return LinkedList con esos atributos
	 * @throws IOException
	 */
	public LinkedList<String> DineroPorPaises() throws IOException{
		LinkedList<String> lista=new LinkedList<String>();
		LinkedList<String> listaPaises=new LinkedList<String>();
		LinkedList<clsClub> clubes=ListaClubes();
		for(clsClub aux:clubes){
			if(listaPaises.contains(aux.getPais())==false){
				listaPaises.add(aux.getPais());
			}
		}
		double dinero=0;
		for(String aux:listaPaises){
			for(clsClub aux1:clubes){
				if(aux.equals(aux1.getPais())){
					dinero+=aux1.getDinero_gastado();
				}
			}
			lista.add(aux+ ". Dinero gastado: "+dinero+" millones de €");
			dinero=0;
		}
		return lista;
	}
	/**
	 * Método que utiliza la lista de fichajes ordenados por coste, los mete en una lista de Strings añadiendo el dato del coste
	 * @return LinkedList de Strings
	 * @throws IOException
	 */
	public LinkedList<String> TopFichajes() throws IOException{
		LinkedList<String> lista=new LinkedList<String>();
		LinkedList<clsFichaje> orden=OrdenarFichajes();
		for(clsFichaje aux:orden){
			lista.add(aux.toString()+". Coste: "+aux.getCoste()+" millones de €\n");
		}
		return lista;
	}
	/**
	 * Método que utiliza la lista de agentes, los ordena por dinero recibido y los mete en una lista de Strings añadiendo el dato del
	 * dinero recibido
	 * @return LinkedList de Strings
	 * @throws IOException
	 */
	public LinkedList<String> TopAgentes() throws IOException{
		LinkedList<String> orden=new LinkedList<String>();
		LinkedList<clsAgente> lista=ListaAgentes();
		Collections.sort(lista);
		for(clsAgente aux:lista){
			String str=aux.toString()+". Dinero ganado: "+aux.getDinero_recibido()+" millones de €";
			orden.add(str);
		}
		return orden;
	}
	/**
	 * Método que crea un Mapa utilizado para la JTable, utiliza como clave un int, recibe la LinkedList ordenada alfabéticamente
	 * e inroduce cada elemento de ella en una posición del Mapa
	 * @return Map de jugadores ordenado alfabéticamente
	 * @throws IOException
	 */
	public Map <Integer, clsJugador> MapaJugEdad() throws IOException{
		LinkedList<clsJugador> l;
		l=OrdenarAlf();
		Map<Integer, clsJugador> mapa=new TreeMap<Integer, clsJugador>();
		for (int i=0; i<l.size(); i++){
			clsJugador c=l.get(i);
			mapa.put(i, c);
		}
		return mapa;
	}
	/**
	 * Método que crea un Mapa utilizado para la JTable, utiliza como clave un int, recibe la LinkedList ordenada por salario
	 * e inroduce cada elemento de ella en una posición del Mapa
	 * @return Map de jugadores ordenado por salario
	 * @throws IOException
	 */
	public Map <Integer, clsJugador> MapaJugSalario() throws IOException{
		LinkedList<clsJugador> l;
		l=OrdenarSalario();
		Map<Integer, clsJugador> mapa=new TreeMap<Integer, clsJugador>();
		for (int i=0; i<l.size(); i++){
			clsJugador c=l.get(i);
			mapa.put(i, c);
		}
		return mapa;
	}
}
