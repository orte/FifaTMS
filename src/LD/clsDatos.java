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

public class clsDatos implements itfDatos{
	
	private final String RUTA_JUGADORES="src\\dat\\jugadores.dat";
	private final String RUTA_AGENTES="src\\dat\\agentes.dat";
	private final String RUTA_CLUBES="src\\dat\\clubes.dat";
	private final String RUTA_FICHAJES="src\\dat\\fichajes.dat";
	
	ObjectOutputStream oos;
	AppendableObjectOutputStream aoos;
	ObjectInputStream ois;
	FileOutputStream fos;
	FileInputStream fis;

	public String setFichero(enFicDatos fic){
		String ruta=null;
		switch (fic){
		case DAT_JUGADORES: ruta = RUTA_JUGADORES; break;
		case DAT_AGENTES: ruta = RUTA_AGENTES; break;
		case DAT_CLUBES: ruta = RUTA_CLUBES; break;
		case DAT_FICHAJES: ruta = RUTA_FICHAJES; break;
		}
		return ruta;
	}
	
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

	@Override
	public void ResetFile(enFicDatos fichero) {
		String ruta=setFichero(fichero);
		File fich=new File(ruta);
		fich.delete();
		
	}

}
