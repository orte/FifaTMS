package LP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;

import javax.swing.SwingConstants;

import comun.DuplicadoException;
import LN.clsClub;
import LN.clsGestor;

/**
 * Ventana interna que hereda de JInternalFrame y muestra un formulario para modificar un club en concreto. En su constructor
 * se reciben los atributos del club en cuestión para ponerlos por defecto en los campos de texto
 * @author jon.orte
 *
 */
public class ModificarClubInternFrm extends JInternalFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JComboBox<String> comboPais;
	private JLabel lblNombre;
	private JLabel lblPas;
	private JLabel lblIntroduzcaLosNuevos;
	private JLabel lblPresupuestoParaFichajes;
	private JButton aceptar;
	private JComboBox<String> comboPres;
	private int id_club;
	private JTextField textField;
	
	public ModificarClubInternFrm(String nombre, String pais, double pres, int id_club){
		this.id_club=id_club;
		setClosable(true);
		setTitle("Modificar club");
		getContentPane().setLayout(null);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombre.setBounds(10, 54, 73, 14);
		getContentPane().add(lblNombre);
		
		textField = new JTextField();
		textField.setBounds(10, 79, 86, 20);
		textField.setText(nombre);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		lblPas = new JLabel("Pa\u00EDs");
		lblPas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPas.setBounds(10, 123, 46, 14);
		getContentPane().add(lblPas);
		
		int indx=-1;
		String[] paises={"España", "Inglaterra", "Italia", "Alemania", "Francia", "Holanda", "Portugal", "Otro"};
		comboPais = new JComboBox<String>(paises);
		for (int i=0; i<paises.length; i++){
			if(paises[i].equals(pais)){
				indx=i;
			}
		}
		comboPais.setSelectedIndex(indx);
		comboPais.setBounds(10, 149, 86, 20);
		getContentPane().add(comboPais);
		
		lblPresupuestoParaFichajes = new JLabel("Presupuesto para fichajes");
		lblPresupuestoParaFichajes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPresupuestoParaFichajes.setBounds(10, 191, 167, 14);
		getContentPane().add(lblPresupuestoParaFichajes);
		
		String [] presupuestos=new String [20];
		int inicial=5;
		for (int i=0; i<20; i++){
			presupuestos[i]=Integer.toString(inicial);
			inicial=inicial+5;
		}
		comboPres = new JComboBox<String>(presupuestos);
		int indx1=-1;
		for(int i=0; i<presupuestos.length; i++){
			if(Double.parseDouble(presupuestos[i])==pres){
				indx1=i;
			}
		}
		comboPres.setSelectedIndex(indx1);
		comboPres.setBounds(10, 216, 86, 20);
		getContentPane().add(comboPres);
		
		lblIntroduzcaLosNuevos = new JLabel("Introduzca los nuevos datos del club");
		lblIntroduzcaLosNuevos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIntroduzcaLosNuevos.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntroduzcaLosNuevos.setBounds(10, 11, 215, 14);
		getContentPane().add(lblIntroduzcaLosNuevos);
		aceptar=new JButton("Aceptar");
		aceptar.setBounds(76, 260, 91, 23);
		getContentPane().add(aceptar);
		aceptar.setEnabled(true);
		aceptar.addActionListener(this);
		this.setSize(new Dimension(252, 323));
		
		this.setSize(new Dimension(252, 323));
		
	}
	/**
	 * Al pulsar el botón aceptar, se llama al método ModificarClub de la clase gestor para, con los nuevos atributos
	 * modificar el club. Si este alumno modificado coincide con alguno ya existente, se abre un cuadro de diálogo avisando de ello
	 * @author jon.orte
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		clsGestor ges=new clsGestor();
		try {
			clsClub mod=ges.ListaClubes().get(ges.BuscarClub(id_club));
			ges.ModificarClub(mod, textField.getText(),(String) comboPais.getSelectedItem(),Double.parseDouble((String) comboPres.getSelectedItem()));
			JOptionPane.showMessageDialog(this, "Enhorabuena, has modificado los datos de "+mod.toString());;
			this.dispose();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (DuplicadoException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, "El club que ha introducido ya existe en nuestro sistema, inténtelo de nuevo");
		}
	}
}
