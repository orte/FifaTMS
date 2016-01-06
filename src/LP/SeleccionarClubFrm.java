package LP;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import LN.clsClub;
import LN.clsGestor;

/**
 * Ventana que se carga cuando se inicia el proceso de un fichaje, para escoger de una lista de los clubes dados de alta aquél que va a realizar
 * el traspaso. Implementa ActionListener y ListSelectionListener
 * @author jon.orte
 *
 */
public class SeleccionarClubFrm extends JFrame implements ActionListener, ListSelectionListener{

	private static final long serialVersionUID = 1L;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JList<clsClub> list;
	private JLabel lblSeleccioneElClub;
	private clsGestor ges=new clsGestor();
	
	public SeleccionarClubFrm() {
		setTitle("Nuevo fichaje");
		getContentPane().setLayout(null);
		this.setSize(400, 400);
		
		lblSeleccioneElClub = new JLabel("Seleccione el club que va a realizar el fichaje");
		lblSeleccioneElClub.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSeleccioneElClub.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccioneElClub.setBounds(10, 42, 364, 14);
		getContentPane().add(lblSeleccioneElClub);
		list = new JList<clsClub>();
		LinkedList<clsClub> clubes=new LinkedList<clsClub>();
		try {
			clubes=ges.ListaClubes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ListaClubesMdl modelo=new ListaClubesMdl(clubes);
		list.setModel(modelo);
		list.setBounds(50, 84, 280, 211);
		list.addListSelectionListener(this);
		getContentPane().add(list);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(60, 306, 89, 23);
		btnAceptar.setEnabled(false);
		btnAceptar.addActionListener(this);
		btnAceptar.setActionCommand("aceptar");
		getContentPane().add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setActionCommand("cancelar");
		btnCancelar.setBounds(232, 306, 89, 23);
		getContentPane().add(btnCancelar);
	}

	/**
	 * Simplemente activa el botón aceptar, desactivado por defecto, cuando se selecciona un elemento de la lista
	 * @author jon.orte
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		if((list.getSelectedValue()==null)==false){
			btnAceptar.setEnabled(true);
		}
	}

	/**
	 * Cuando se selecciona un club de la lista, se pasa su ID por parámetro y se crea una nueva ventana de tipo NuevoJugadorFrm para continuar
	 * con el fichaje
	 * @author jon.orte
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("aceptar")){
			int id_club=list.getSelectedValue().getId_club();
			NuevoJugadorFrm a=new NuevoJugadorFrm(id_club);
			a.setVisible(true);
			this.dispose();
		} else if(e.getActionCommand().equals("cancelar")){
			this.dispose();
		}
	}

}
