package LP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;

import java.awt.Font;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JButton;

import comun.SinPresupuestoException;
import LN.clsClub;
import LN.clsFichaje;
import LN.clsGestor;

/**
 * Objeto de tipo ventana, heredero de JFrame y que implementa las interfaces ListSelectionListener y ActionListener. Muestra una lista de 
 * fichajes realizados para que el usuario escoja uno y lo pueda cancelar
 * @author jon.orte
 *
 */
public class CancelarFichajeFrm extends JFrame implements ListSelectionListener, ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JList<clsFichaje> list;
	private JLabel lblSeleccionaElFichaje;
	private clsGestor ges=new clsGestor();
	
	public CancelarFichajeFrm() {
		setSize(523,365);
		setTitle("Cancelar Fichaje");
		getContentPane().setLayout(null);
		
		lblSeleccionaElFichaje = new JLabel("Selecciona el fichaje que deseas cancelar");
		lblSeleccionaElFichaje.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccionaElFichaje.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSeleccionaElFichaje.setBounds(10, 11, 487, 14);
		getContentPane().add(lblSeleccionaElFichaje);
		
		list = new JList<clsFichaje>();
		LinkedList<clsFichaje> lista=new LinkedList<clsFichaje>();
		try {
			lista=ges.ListaFichajes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ListaFichajesMdl modelo=new ListaFichajesMdl(lista);
		list.setModel(modelo);
		list.setBounds(10, 31, 487, 247);
		list.addListSelectionListener(this);
		getContentPane().add(list);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(this);
		btnAceptar.setActionCommand("aceptar");
		btnAceptar.setBounds(147, 289, 89, 23);
		btnAceptar.setEnabled(false);
		getContentPane().add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setActionCommand("cancelar");
		btnCancelar.setBounds(278, 289, 89, 23);
		getContentPane().add(btnCancelar);
	}

	

	/**
	 * Una vez pulsado el botón aceptar, se le preguntará al usuario si desea cancelar el fichaje seleccionado. En caso afirmativo, se 
	 * restará el montante del fichaje al presupuesto del club, y se borrarán el fichaje, el jugador y el contrato de la base de datos.
	 * Luego, se actualizará la tabla para que ya no aparezca el fichaje borrado.
	 * @author jon.orte
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("aceptar")){
			clsFichaje borr=list.getSelectedValue();
			int opcion=JOptionPane.showConfirmDialog(this, "Estás seguro de que quieres cancelar este fichaje? Se eliminará también \nal jugador y su contrato de la base de datos y se restituirá el importe del fichaje en \n el presupuesto del club, pero no se devolverá la comisión del agente");
			if(opcion==0){
				try {
					LinkedList<clsClub> clubes=ges.ListaClubes();
					clsClub temp=clubes.get(ges.BuscarClub(borr.getId_club()));
					ges.AñadirFichaje(temp, ges.BuscarClub(borr.getId_club()), (-borr.getCoste()));
					ges.BorrarFichaje(borr);
					ges.BorrarContrato(ges.BuscarContrato(borr.getId_jugador()));
					ges.BorrarJugador(ges.BuscarJugador(borr.getId_jugador()));
					LinkedList<clsFichaje> fichajes=new LinkedList<clsFichaje>();
					try {
						fichajes=ges.ListaFichajes();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					ListaFichajesMdl model=new ListaFichajesMdl(fichajes);
					list.setModel(model);
					list.repaint();
					this.getContentPane().revalidate();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SinPresupuestoException e1) {
					// TODO Auto-generated catch block
				} catch (IndexOutOfBoundsException e1){
					
				}
			} 
		} else if(e.getActionCommand().equals("cancelar")){
			this.dispose();
		}
			
	}

	/**
	 * Sirve para que el botón aceptar, desactivado por defecto, se active una vez se seleccione un elemento de la lista
	 * @author jon.orte
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		btnAceptar.setEnabled(true);
	}

}
