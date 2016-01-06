package LP;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import comun.DuplicadoException;
import LN.clsAgente;
import LN.clsGestor;

/**
 * Ventana que será creada durante el proceso de fichaje, si se quiere o es necesario dar de alta un nuevo agente en la base de datos. Pedirá
 * al usuario que introduzca los atributos de nombre, apelido y nacionalidad del mismo para poder crearlo. Implementa las interfaces 
 * DocumentListener y ActionListener
 * @author jon.orte
 *
 */
public class NuevoAgenteFrm extends JFrame implements ActionListener, DocumentListener{
	
	private static final long serialVersionUID = 1L;
	private JLabel lblIntroduzcaLosDatos;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblApellido;
	private JTextField txtAp;
	private JTextField txtNacion;
	private JLabel lblNacionalidad;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private int id_jug;
	private int id_club;
	
	public NuevoAgenteFrm(int id_jug, int id_club) {
		this.id_jug=id_jug;
		this.id_club=id_club;
		setSize(288,334);
		setTitle("Nuevo Fichaje");
		getContentPane().setLayout(null);
		
		lblIntroduzcaLosDatos = new JLabel("Introduzca los datos del agente");
		lblIntroduzcaLosDatos.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIntroduzcaLosDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntroduzcaLosDatos.setBounds(10, 11, 261, 14);
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
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(33, 262, 89, 23);
		btnAceptar.setEnabled(false);
		btnAceptar.addActionListener(this);
		btnAceptar.setActionCommand("aceptar");
		getContentPane().add(btnAceptar);
		btnCancelar.setActionCommand("cancelar");
		btnCancelar.setBounds(146, 262, 89, 23);
		getContentPane().add(btnCancelar);
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

	/**
	 * Una vez introducidos los atributos y pulsado el botón aceptar, se crearán un nuevo agente y un nuevo contrato, involucrando al jugador
	 * fichado y al agente recién creado. Después se creará una ventana FichajeFrm para seguir con el proceso del fichaje
	 * @author jon.orte
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		clsGestor ges=new clsGestor();
		try {
			clsAgente nuevo=ges.NuevoAgente(txtNombre.getText(), txtAp.getText(), txtNacion.getText());
			ges.NuevoContrato(id_jug, nuevo.getNum_licencia());
			JOptionPane.showMessageDialog(this, "El agente dado de alta para el fichaje ha sido "+nuevo.toString());
			this.dispose();
			FichajeFrm a=new FichajeFrm(id_club, id_jug, nuevo.getNum_licencia());
			a.setVisible(true);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (DuplicadoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
