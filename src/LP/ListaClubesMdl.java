package LP;

import java.util.LinkedList;

import javax.swing.DefaultListModel;

import LN.clsClub;

/**
 * Modelo para las JList de clubes, que extiende de DefaultListModel
 * @author jon.orte
 *
 */
public class ListaClubesMdl  extends DefaultListModel<clsClub>{
	
	private static final long serialVersionUID = 1L;
	protected LinkedList<clsClub> clubes;
	
	public ListaClubesMdl(LinkedList<clsClub> lista){
		clubes=lista;
	}
	public clsClub getElementAt(int index)
	  {
	    return clubes.get(index);
	  }
	
	  public int getSize()
	  {
	    return clubes.size();
	  }

	  @Override
	  public void addElement(clsClub element)
	  {
		  clubes.add(element);
		  this.fireContentsChanged(this, clubes.size(), clubes.size());
	  }
}
