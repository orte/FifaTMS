package LP;

import java.util.LinkedList;

import javax.swing.DefaultListModel;

import LN.clsFichaje;

/**
 * Modelo para las JList de fichajes, que extiende de DefaultListModel
 * @author jon.orte
 *
 */
public class ListaFichajesMdl extends DefaultListModel<clsFichaje>{
	
	private static final long serialVersionUID = 1L;
	protected LinkedList<clsFichaje> fichajes;
	
	public ListaFichajesMdl(LinkedList<clsFichaje> lista){
		fichajes=lista;
	}
	public clsFichaje getElementAt(int index)
	  {
	    return fichajes.get(index);
	  }
	
	  public int getSize()
	  {
	    return fichajes.size();
	  }

	  @Override
	  public void addElement(clsFichaje element)
	  {
		  fichajes.add(element);
		  this.fireContentsChanged(this, fichajes.size(), fichajes.size());
	  }
}
