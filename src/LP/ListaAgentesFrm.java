package LP;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JList;
import javax.swing.JButton;

import LN.clsAgente;
import LN.clsGestor;

/**
 * Clase ventana, que hereda de JFrame e implementa la interfaz ActionListener y que muestra una lista de agentes dados de alta previamente
 * para poder elegir uno y que cierre el fichaje en curso
 * @author jon.orte
 *
 */
public class ListaAgentesFrm extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JLabel lblSeleccioneElAgente;
	private JButton btnAceptar;
	private JList<clsAgente> list;
	private clsGestor ges=new clsGestor();
	private int id_jug;
	private int id_club;
	
	public ListaAgentesFrm(int id_jug, int id_club) {
		this.id_jug=id_jug;
		this.id_club=id_club;
		setTitle("Nuevo Fichaje");
		setSize(315,345);
		getContentPane().setLayout(null);
		LinkedList<clsAgente> listaA=new LinkedList<clsAgente>();
		try{
			listaA=ges.ListaAgentes();
		} catch(IOException e){
			e.printStackTrace();
		}
		ListaAgentesMdl model=new ListaAgentesMdl(listaA);
		list = new JList<clsAgente>();
		list.setModel(model);
		list.setBounds(10, 31, 271, 240);
		getContentPane().add(list);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(107, 282, 89, 23);
		btnAceptar.addActionListener(this);
		getContentPane().add(btnAceptar);
		
		lblSeleccioneElAgente = new JLabel("Seleccione el agente que va a cerrar el traspaso");
		lblSeleccioneElAgente.setBounds(10, 11, 271, 14);
		getContentPane().add(lblSeleccioneElAgente);
		lblSeleccioneElAgente.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSeleccioneElAgente.setHorizontalAlignment(SwingConstants.CENTER);
		
	}

	/**
	 * Una vez seleccionado el agente que queremos que cierre el fichaje, se creará un nuevo contrato vinculando al mismo y al jugador.
	 * Luego, se creará una ventana FichajeFrm para seguir con el proceso del fichaje.
	 * @author jon.orte
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ges.NuevoContrato(id_jug, list.getSelectedValue().getNum_licencia());
		JOptionPane.showMessageDialog(this, "El agente seleccionado para el fichaje ha sido "+list.getSelectedValue().toString());
		this.dispose();
		FichajeFrm a=new FichajeFrm(id_club, id_jug, list.getSelectedValue().getNum_licencia());
		a.setVisible(true);
	}
}
