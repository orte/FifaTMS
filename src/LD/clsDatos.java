package LD;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;

import comun.clsConstantes.enFicDatos;

/**
 * Clase que se usará para leer y escribir de ficheros
 * @author jon.orte
 *
 */

public class clsDatos implements itfDatos{
	
	private final String RUTA_JUGADORES="src\\dat\\jugadores.dat";
	private final String RUTA_AGENTES="src\\dat\\agentes.dat";
	private final String RUTA_CLUBES="src\\dat\\clubes.dat";
	private final String RUTA_FICHAJES="src\\dat\\fichajes.dat";
	private final String RUTA_CONTRATOS="src\\dat\\contratos.dat";
	
	ObjectOutputStream oos;
	AppendableObjectOutputStream aoos;
	ObjectInputStream ois;
	FileOutputStream fos;
	FileInputStream fis;

	/**
	 * Método que debe crear un objectOutputStream o un AppendableObjectOutputStream para proceder
	 * a la escritura del fichero. Si el fichero existe,AppendableObjectOutputStream; de lo 
	 * contrario objectOutputStream
	 * @author jon.orte
	 * @param fic enumerado de la clase clsConstantes que indica el fichero del que se va a escribir
	 * @return ruta del fichero 
	 */
	public String setFichero(enFicDatos fic){
		String ruta=null;
		switch (fic){
		case DAT_JUGADORES: ruta = RUTA_JUGADORES; break;
		case DAT_AGENTES: ruta = RUTA_AGENTES; break;
		case DAT_CLUBES: ruta = RUTA_CLUBES; break;
		case DAT_FICHAJES: ruta = RUTA_FICHAJES; break;
		case DAT_CONTRATOS: ruta= RUTA_CONTRATOS; break;
		}
		return ruta;
	}
	
	/**
	 * 
	 * Método que debe crear un objectOutputStream o un AppendableObjectOutputStream para proceder
	 * a la escritura del fichero. Si el fichero existe,AppendableObjectOutputStream; de lo 
	 * contrario objectOutputStream
	 * @author jon.orte
	 * @param fic enumerado de la clase clsConstantes que indica el fichero del que se va a escribir
	 * @return void
	 */
	@Override
	public void ComenzarSave(enFicDatos fichero) {
		// TODO Auto-generated method stub
		String ruta=setFichero(fichero);
		File archivo=new File(ruta);
		
		if(archivo.exists()){
			try {
				fos = new FileOutputStream(ruta, true);
				aoos=new AppendableObjectOutputStream(fos);	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try{
				fos=new FileOutputStream(ruta);	
				oos=new ObjectOutputStream(fos);
			} catch (IOException e){
				e.printStackTrace();
			}			
		}
	}
	/**
	 * @author jon.orte
	 * @return void
	 * Método que debe cerrar el fichero en el que se ha escrito.
	 */
	@Override
	public void TerminarSave() {
		// TODO Auto-generated method stub
		try {
			if (oos==null){
				aoos.close();
				fos.close();
				aoos=null;
			}
			else if(aoos==null){
				oos.close();
				fos.close();
				oos=null;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Método que guarda en el fichero indicado previamente el objeto recibido.
	 * @author jon.orte
	 * @param o Objeto a guardar, que debe implementar la interfaz serializable.
	 * @return void
	 */
	@Override
	public void Save(Serializable o) {
		// TODO Auto-generated method stub
		try{
			if (aoos!=null){
				aoos.writeObject(o);
			}
			else if(oos!=null){
				oos.writeObject(o);
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
	}
	/**
	 * Método que crea un objectInputStream para la lectura del fichero indicado previamente.
	 * @author jon.orte
	 * @param fichero: enumerado de la clase clsConstantes que indica el fichero del que se va a leer. 
	 * @return void
	 * @throws IOException: excepción lanzada en caso de que se dé un error de lectura/escritura en fichero.
	 */
	@Override
	public void ComenzarRead(enFicDatos fichero) throws IOException {
		String ruta=setFichero(fichero);
		try{
			fis=new FileInputStream(ruta);
			ois=new ObjectInputStream(fis);
		} catch (FileNotFoundException e){
			ComenzarSave(fichero);
			TerminarSave();
			try {
				fis=new FileInputStream(ruta);
				ois=new ObjectInputStream(fis);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} catch (IOException e){
			e.printStackTrace();
			
		}			
	}

	/**
	 * Método que cierra el fichero del que se ha leído.
	 * @author jon.orte
	 * @return void
	 */
	@Override
	public void TerminarRead() {
		try {
			ois.close();
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @author jon.orte
	 * @return ArrayList<Serializable>: Devuelve un arraylist con los datos leídos, en el tipo Serializable.
	 */
	@Override
	public LinkedList<Serializable> Read() {
		LinkedList<Serializable> lista=new LinkedList<Serializable>();
		try {
			while(true){
				try{
					Serializable obj=(Serializable) ois.readObject();
					lista.add(obj);
				} catch (EOFException e){
					break;
				}
			} 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return lista;
	}

	/**
	 * Se borra el fichero (porque vamos a escribir datos modificados).
	 * @author jon.orte
	 * @param fichero enumerado de la clase clsConstantes que indica el fichero que se va a borrar.
	 * @return void
	 */
	@Override
	public void ResetFile(enFicDatos fichero) {
		String ruta=setFichero(fichero);
		File fich=new File(ruta);
		fich.delete();
		
	}

}
