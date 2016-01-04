package LP;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import comun.DuplicadoException;
import LN.clsClub;
import LN.clsGestor;

public class NuevoClubFrm extends JFrame implements ActionListener, DocumentListener{

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNombre;
	private JLabel lblPas;
	private JComboBox<String> comboPais;
	private JLabel lblPresupuestoParaFichajes;
	private JComboBox<String> comboPres;
	private JLabel lblNewLabel;
	private JButton btnCancelar;
	private JButton btnAceptar;

	
	public NuevoClubFrm() {
		setTitle("Nuevo Club");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 313, 356);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombre.setBounds(10, 36, 86, 14);
		contentPane.add(lblNombre);
		
		textField = new JTextField();
		textField.setBounds(10, 61, 86, 20);
		textField.getDocument().addDocumentListener(this);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblPas = new JLabel("Pa\u00EDs");
		lblPas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPas.setBounds(10, 107, 46, 14);
		contentPane.add(lblPas);
		
		String [] paises={"España", "Inglaterra", "Italia", "Alemania", "Francia", "Holanda", "Portugal", "Otro"};
		comboPais = new JComboBox<String>(paises);
		comboPais.setBounds(10, 132, 86, 20);
		contentPane.add(comboPais);
		
		lblPresupuestoParaFichajes = new JLabel("Presupuesto para fichajes");
		lblPresupuestoParaFichajes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPresupuestoParaFichajes.setBounds(10, 196, 144, 14);
		contentPane.add(lblPresupuestoParaFichajes);
		
		String [] presupuestos=new String [20];
		int inicial=5;
		for (int i=0; i<20; i++){
			presupuestos[i]=Integer.toString(inicial);
			inicial=inicial+5;
		}
		comboPres = new JComboBox<String>(presupuestos);
		comboPres.setBounds(10, 221, 86, 20);
		contentPane.add(comboPres);
		
		lblNewLabel = new JLabel("Introduzca los datos del nuevo club");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(30, 11, 228, 14);
		contentPane.add(lblNewLabel);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(this);
		btnAceptar.setActionCommand("aceptar");
		btnAceptar.setBounds(55, 284, 89, 23);
		btnAceptar.setEnabled(false);
		contentPane.add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setActionCommand("cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(154, 284, 89, 23);
		contentPane.add(btnCancelar);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		clsGestor ges=new clsGestor();
		if (e.getActionCommand().equals("aceptar")){
			try {
				clsClub nuevo=ges.NuevoClub((String)textField.getText(), (String)comboPais.getSelectedItem(), Double.parseDouble((String) comboPres.getSelectedItem()));
				JOptionPane.showMessageDialog(this, "Enhorabuena, el "+nuevo.getNombre()+" ha sido dado de alta en el sistema");
				this.dispose();
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (DuplicadoException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, "El club que ha introducido ya existe en nuestro sistema, inténtelo de nuevo");
			}
		}
		else if(e.getActionCommand().equals("cancelar")){
			this.dispose();
		}
	}


	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		if(textField.getText().equals(null)==false){
			btnAceptar.setEnabled(true);
		}
	}


	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		if(textField.getText().equals(null)==false){
			btnAceptar.setEnabled(false);
		}
	}


	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		if(textField.getText().equals(null)==false){
			btnAceptar.setEnabled(false);
		}
	}
}
