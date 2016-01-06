package LP;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import LN.clsGestor;

/**
 * Ventana que muestra en un textArea los traspasos realizados ordenados según su coste. Hereda de JFrame e implementa ActionListener
 * @author jon.orte
 *
 */
public class RankingTraspasosFrm extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JLabel lblListaDeFichajes;
	private JButton btnActualizar;
	private JTextArea textArea;
	private JPanel panel;
	private JScrollPane scrollPane;
	
	public RankingTraspasosFrm() {
		setSize(405,375);
		getContentPane().setLayout(null);
		
		clsGestor ges=new clsGestor();
		
		
		panel = new JPanel();
		panel.setBounds(10, 38, 354, 256);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 354, 256);
		panel.add(scrollPane);
		textArea = new JTextArea();
		LinkedList<String> lista=new LinkedList<String>();
		try {
			lista=ges.TopFichajes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(String aux:lista){
			textArea.setText(textArea.getText()+aux+"\n");
		}
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(131, 305, 110, 23);
		btnActualizar.addActionListener(this);
		getContentPane().add(btnActualizar);
		
		lblListaDeFichajes = new JLabel("Lista de los fichajes ordenados de más caro a más barato");
		lblListaDeFichajes.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaDeFichajes.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblListaDeFichajes.setBounds(0, 16, 379, 14);
		getContentPane().add(lblListaDeFichajes);
		
		
	}

	/**
	 * Cuando se pulsa el botón actualizar, se carga de nuevo la textArea, por si se ha realizado algún fichaje mientras estaba abierta 
	 * la ventana.
	 * @author jon.orte
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		clsGestor ges=new clsGestor();
		LinkedList<String> lista=new LinkedList<String>();
		try {
			lista=ges.TopFichajes();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		textArea.setText(null);
		for(String aux:lista){
			textArea.setText(textArea.getText()+aux+"\n");
		}
		textArea.repaint();
		this.revalidate();
	}
}
