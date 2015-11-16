package LN;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;

import LD.*;
import comun.clsConstantes.enFicDatos;
import comun.DuplicadoException;
public class clsGestor {
	clsDatos dat=new clsDatos();

	public clsClub NuevoClub(String nombre, String pais, double presup) throws IOException, DuplicadoException{
		/*
		 * Recibe por parámetro los atributos de nombre, país y presupuesto necesarios para crear un nuevo objeto clsClub. Luego, llamando
		 * a los métodos del objeto dat de tipo clsDatos los guarda en su fichero correspondiente
		 */
		clsClub nuevo=new clsClub();
		nuevo.setNombre(nombre);
		nuevo.setPais(pais);
		nuevo.setPresupuesto(presup);
		LinkedList<clsClub> temp=ListaClubes();
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
	public clsJugador NuevoJugador(String nombre, String apellido, String nac, int edad, int id) throws IOException, DuplicadoException{
		/*
		 * Recibe por parámetro los atributos de nombre, apellido, nacionalidad, edad y número de ID necesarios para crear un nuevo objeto
		 * clsJugador. Luego, llamando a los métodos del objeto dat de tipo clsDatos los guarda en su fichero correspondiente
		 */
		clsJugador nuevo=new clsJugador();
		nuevo.setNombre(nombre);
		nuevo.setApellido(apellido);
		nuevo.setNacionalidad(nac);
		nuevo.setEdad(edad);
		nuevo.setId_jugador(id);
		LinkedList<clsJugador> temp=ListaJugadores();
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
	public clsAgente NuevoAgente(String nombre, String apellido, String nac, int num_lic) throws IOException, DuplicadoException{
		/*
		 * Recibe por parámetro los atributos de nombre, apellido, nacionalidad y número de licencia necesarios para crear un nuevo objeto
		 * clsAgente. Luego, llamando a los métodos del objeto dat de tipo clsDatos los guarda en su fichero correspondiente
		 */
		clsAgente nuevo=new clsAgente();
		nuevo.setNombre(nombre);
		nuevo.setApellido(apellido);
		nuevo.setNacionalidad(nac);
		nuevo.setNum_licencia(num_lic);
		LinkedList<clsAgente> temp=ListaAgentes();
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
	public clsFichaje NuevoFichaje(clsClub club, clsJugador jug, double cos, int dur, int com, double sal) throws IOException, DuplicadoException{
		/*
		 * Recibe por parámetro los atributos de club, jugador, coste, duración del contrato, comisión del agente y salario del jugador
		 * necesarios para crear un nuevo objeto clsFichaje. Luego, llamando a los métodos del objeto dat de tipo clsDatos los guarda en 
		 * su fichero correspondiente
		 */
		clsFichaje nuevo=new clsFichaje();
		nuevo.setClub(club);
		nuevo.setJugador(jug);
		nuevo.setCoste(cos);
		nuevo.setDuracion(dur);
		nuevo.setComision(com);
		nuevo.setSalario(sal);
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
	public int BuscarClub(String nom) throws IOException {
		int i=-1;
		LinkedList<clsClub> lista=ListaClubes();
		for (clsClub aux:lista){
			if(aux.getNombre().equals(nom)){
				i=lista.indexOf(aux);
				break;
			}
		}
		return i;
	}
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
	public int BuscarFichaje(int id_jug) throws IOException{
		int i=-1;
		LinkedList<clsFichaje> lista=ListaFichajes();
		for(clsFichaje aux:lista){
			if (aux.getJugador().getId_jugador()==id_jug){
				i=lista.indexOf(aux);
				break;
			}
		}
		return i;
	}
	public void ModificarClub(clsClub club, int indx, String nombre, String pais, double pres) throws IOException, DuplicadoException{
		club.setNombre(nombre);
		club.setPais(pais);
		club.setPresupuesto(pres);
		LinkedList<clsClub> lista=ListaClubes();
		HashSet<clsClub> set=new HashSet<clsClub>();
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
	public void AñadirFichaje(clsClub club, int indx, clsJugador jug, double cos) throws IOException{
		LinkedList<clsJugador> l=club.getFichajes();
		l.add(jug);
		club.setFichajes(l);
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
	public void ModificarJugador(clsJugador jug, clsClub club, clsAgente agente, double salario) throws IOException{
		LinkedList<clsJugador> lista=ListaJugadores();
		int indx=-1;
		for (clsJugador aux:lista){
			if (aux.getId_jugador()==jug.getId_jugador()){
				indx=lista.indexOf(aux);
				break;
			}
		}
		jug.setClub(club);
		jug.setAgente(agente);
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
	public void ModificarAgente(clsAgente ag, int indx, clsJugador rep, int com, double cos) throws IOException{
		ag.setDinero_recibido(ag.getDinero_recibido()+((com*cos))/100);
		LinkedList<clsJugador> repr=ag.getRepresentados();
		repr.add(rep);
		ag.setRepresentados(repr);
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
	public void BorrarFichaje(clsFichaje fic, int indx) throws IOException{
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
	public double PromedioEdad() throws IOException{
		double res;
		LinkedList <clsJugador> lista=ListaJugadores();
		double temp=0;
		for(clsJugador aux:lista){
			temp=temp+aux.getEdad();
		}
		res=temp/lista.size();
		return res;
	}
}
