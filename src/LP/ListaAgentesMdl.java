package LP;

import java.util.LinkedList;

import javax.swing.DefaultListModel;

import LN.clsAgente;

/**
 * Modelo para las JList de agentes, que extiende de DefaultListModel
 * @author jon.orte
 *
 */
public class ListaAgentesMdl extends DefaultListModel<clsAgente>{
	
	private static final long serialVersionUID = 1L;
	protected LinkedList<clsAgente> agentes;
	
	public ListaAgentesMdl(LinkedList<clsAgente> lista){
		agentes=lista;
	}
	public clsAgente getElementAt(int index)
	  {
	    return agentes.get(index);
	  }
	
	  public int getSize()
	  {
	    return agentes.size();
	  }

	  @Override
	  public void addElement(clsAgente element)
	  {
		  agentes.add(element);
		  this.fireContentsChanged(this, agentes.size(), agentes.size());
	  }
}
