package LP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;

import comun.DuplicadoException;
import comun.SinPresupuestoException;
import LN.clsAgente;
import LN.clsClub;
import LN.clsFichaje;
import LN.clsGestor;
import LN.clsJugador;

/**
 * De tipo ventana, hereda de JFrame e implementa ActionListener. Realiza el paso final  en un fichaje, pidiendo al usuario que introduzca
 * el coste del mismo, la comisión para el agente y el salario del jugador para poder cerrar el traspaso.
 * @author jon.orte
 *
 */
public class FichajeFrm extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JLabel lblTitulo;
	private JLabel lblCosteenMill;
	private JLabel lblComisinDelAgente;
	private JComboBox<String> comboCom;
	private JButton btnAceptar;
	private JTextField txtCoste;
	private int id_club;
	private int id_jug;
	private int id_agen;
	private JLabel lblSalarioAnualDel;
	private JTextField txtSal;
	
	public FichajeFrm(int id_club, int id_jug, int id_agen) {
		this.id_club=id_club;
		this.id_jug=id_jug;
		this.id_agen=id_agen;
		setSize(260,280);
		setTitle("Nuevo Fichaje");
		getContentPane().setLayout(null);
		
		lblTitulo = new JLabel("Introduzca los detalles del traspaso");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTitulo.setBounds(10, 11, 222, 14);
		getContentPane().add(lblTitulo);
		
		lblCosteenMill = new JLabel("Coste (en mill. de \u20AC)");
		lblCosteenMill.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCosteenMill.setBounds(10, 36, 119, 14);
		getContentPane().add(lblCosteenMill);
		
		lblComisinDelAgente = new JLabel("Comisi\u00F3n del agente (%)");
		lblComisinDelAgente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblComisinDelAgente.setBounds(10, 92, 136, 14);
		getContentPane().add(lblComisinDelAgente);
		
		String [] comisiones= new String [11];
		int com_inic=0;
		for (int i=0; i<comisiones.length; i++){
			comisiones[i]=Integer.toString(com_inic);
			com_inic=com_inic+5;
		}
		comboCom = new JComboBox<String>(comisiones);
		comboCom.setBounds(10, 117, 64, 20);
		getContentPane().add(comboCom);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(73, 202, 89, 23);
		btnAceptar.addActionListener(this);
		getContentPane().add(btnAceptar);
		
		txtCoste = new JTextField();
		txtCoste.setBounds(10, 61, 64, 20);
		getContentPane().add(txtCoste);
		txtCoste.setColumns(10);
		
		lblSalarioAnualDel = new JLabel("Salario anual del jugador (en mill. de \u20AC)");
		lblSalarioAnualDel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSalarioAnualDel.setBounds(10, 148, 222, 14);
		getContentPane().add(lblSalarioAnualDel);
		
		txtSal = new JTextField();
		txtSal.setBounds(10, 171, 64, 20);
		getContentPane().add(txtSal);
		txtSal.setColumns(10);
	}

	/**
	 * Una vez introducidos los datos y pulsado el botón aceptar, se hacen diferentes gestiones. Por un lado, se modifica el club que ha
	 * realizado el fichaje, restando el coste del mismo del presupuesto y añadiéndolo al total de dinero gastado.  Luego se crea el objeto
	 * de la clase fichaje con los IDs de club y jugador, el coste y el porcentaje de comisión. Después se le establece el salario al jugador
	 * para, por último, añadir lo ganado con la comisión al total de ingresos del agente.
	 * @author jon.orte
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		clsGestor ges=new clsGestor();
		try {
			clsJugador jug=ges.ListaJugadores().get(ges.BuscarJugador(id_jug));
			clsClub club=ges.ListaClubes().get(ges.BuscarClub(id_club));
			clsAgente agen=ges.ListaAgentes().get(ges.BuscarAgente(id_agen));
			ges.AñadirFichaje(club, ges.BuscarClub(id_club), Double.parseDouble(txtCoste.getText()));
			clsFichaje nuevo=ges.NuevoFichaje(id_club, id_jug, Double.parseDouble(txtCoste.getText()), Integer.parseInt((String) comboCom.getSelectedItem()));
			ges.EstablecerSalario(jug, Double.parseDouble(txtSal.getText()));
			ges.ModificarAgente(agen, ges.BuscarAgente(id_agen), Integer.parseInt((String)comboCom.getSelectedItem()), Double.parseDouble(txtCoste.getText()));
			JOptionPane.showMessageDialog(this, "Ha sido realizado el siguiente fichaje: "+nuevo.toString());
			this.dispose();
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (DuplicadoException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, "Este fichaje ya ha sido realizado antes");
		} catch (SinPresupuestoException e1) {
			JOptionPane.showMessageDialog(this, e1.getMessage());
		}
	}

}
