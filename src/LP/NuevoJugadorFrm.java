package LP;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import comun.DuplicadoException;
import comun.VacioException;
import LN.clsGestor;
import LN.clsJugador;

/**
 * Ventana que será creada durante el proceso de fichaje, se va a fichar a un jugador y es necesario darlo de alta en el sistema. Pedirá
 * al usuario que introduzca los atributos de nombre, apelido, nacionalidad y edad del mismo para poder crearlo. Implementa las interfaces 
 * DocumentListener y ActionListener
 * @author jon.orte
 *
 */
public class NuevoJugadorFrm extends JFrame implements ActionListener, DocumentListener{
	
	private static final long serialVersionUID = 1L;
	private JLabel lblIntroduzcaLosDatos;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblApellido;
	private JTextField txtAp;
	private JTextField txtNacion;
	private JLabel lblNacionalidad;
	private JLabel lblEdad;
	private JComboBox<String> comboBox;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private int id_club;
	private int id_jug;
	
	public NuevoJugadorFrm(int id_club) {
		
		this.id_club=id_club;
		setSize(350,380);
		setTitle("Nuevo Fichaje");
		getContentPane().setLayout(null);
		
		lblIntroduzcaLosDatos = new JLabel("Introduzca los datos del jugador que se va a fichar");
		lblIntroduzcaLosDatos.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIntroduzcaLosDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntroduzcaLosDatos.setBounds(10, 11, 318, 14);
		getContentPane().add(lblIntroduzcaLosDatos);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombre.setBounds(10, 42, 98, 25);
		getContentPane().add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(10, 67, 162, 20);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		txtNombre.getDocument().addDocumentListener(this);
		
		lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblApellido.setBounds(10, 109, 65, 14);
		getContentPane().add(lblApellido);
		
		txtAp = new JTextField();
		txtAp.setBounds(10, 130, 162, 20);
		getContentPane().add(txtAp);
		txtAp.setColumns(10);
		txtAp.getDocument().addDocumentListener(this);
		
		lblNacionalidad = new JLabel("Nacionalidad");
		lblNacionalidad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNacionalidad.setBounds(10, 176, 86, 14);
		getContentPane().add(lblNacionalidad);
		
		txtNacion = new JTextField();
		txtNacion.setBounds(10, 201, 162, 20);
		getContentPane().add(txtNacion);
		txtNacion.setColumns(10);
		txtNacion.getDocument().addDocumentListener(this);
		
		lblEdad = new JLabel("Edad");
		lblEdad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEdad.setBounds(10, 245, 46, 14);
		getContentPane().add(lblEdad);
		
		String [] edades=new String[30];
		int edad_inic=15;
		for(int i=0; i<edades.length; i++){
			edades[i]=Integer.toString(edad_inic);
			edad_inic++;
		}
		comboBox = new JComboBox<String>(edades);
		comboBox.setBounds(10, 270, 65, 20);
		getContentPane().add(comboBox);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(68, 307, 89, 23);
		btnAceptar.setEnabled(false);
		btnAceptar.addActionListener(this);
		btnAceptar.setActionCommand("aceptar");
		getContentPane().add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setActionCommand("cancelar");
		btnCancelar.setBounds(182, 307, 89, 23);
		getContentPane().add(btnCancelar);
	}

	/**
	 * Una vez introducidos los datos, se llamará a clsGestor para que cree el nuevo jugador. Después, siguiendo con el proceso del fichaje, 
	 * se le preguntará al usuario si desea dar de alta a un nuevo agente para cerrar el fichaje o utilizará uno ya dado de alta.  En el primer
	 * caso, se creará una ventana NuevoAgenteFrm y en el segundo una ventana ListaAgentesFrm. En caso de que no haya ninguno dado de alta, se
	 * creará una ventana del segundo tipo.
	 * directamente 
	 * @author jon.orte
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("aceptar")){
			clsGestor ges=new clsGestor();
			try {
				clsJugador nuevo=ges.NuevoJugador(txtNombre.getText(), txtAp.getText(), txtNacion.getText(), Integer.parseInt((String) comboBox.getSelectedItem()));
				JOptionPane.showMessageDialog(this, "Has decidido fichar a +"+nuevo.toString());
				id_jug=nuevo.getId_jugador();
				ges.ComprobarVacio(ges.ListaAgentes());
				int seleccion=JOptionPane.showConfirmDialog(this, "Quiere dar de alta un nuevo agente para el traspaso? En caso contrario tendrá que seleccionar uno ya dado de alta");
				if(seleccion==0){
					NuevoAgenteFrm a=new NuevoAgenteFrm(id_jug, id_club);
					a.setVisible(true);
					this.dispose();
				} else if (seleccion==1){
					ListaAgentesFrm a=new ListaAgentesFrm(id_jug, id_club);
					a.setVisible(true);
					this.dispose();
				}
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (DuplicadoException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, "Este jugador ya existe en nuestra base de datos, inténtalo de nuevo");
			} catch (VacioException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, "No hay ningún agente dado de alta en el sistema, introduce los datos de uno nuevo");
				NuevoAgenteFrm a=new NuevoAgenteFrm(id_jug, id_club);
				a.setVisible(true);
				this.dispose();
			}
		} else if(e.getActionCommand().equals("cancelar")){
			this.dispose();
		}
	}

	/**
	 * Sirve para activar el botón aceptar, desactivado por defecto, una vez haya algo escrito en los tres campos de texto
	 * @author jon.orte
	 */
	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		if (txtNombre.getText().equals("")==false&txtAp.getText().equals("")==false&txtNacion.getText().equals("")==false){
			btnAceptar.setEnabled(true);
		}
	}

	/**
	 * Sirve para desactivar el botón aceptar si hay algún campo de texto vacío
	 */
	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		if (txtNombre.getText().equals("")|txtAp.getText().equals("")|txtNacion.getText().equals("")){
			btnAceptar.setEnabled(false);
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}
}
