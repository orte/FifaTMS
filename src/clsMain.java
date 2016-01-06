
import java.awt.EventQueue;

import LP.MenuPrincipalFrm;


public class clsMain {
	/**
	 * github.com/orte/FifaTMS
	 * M�todo main, crea y lanza el hilo swing y empieza la aplicaci�n
	 * @author jon.orte
	 * 
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipalFrm frame = new MenuPrincipalFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
