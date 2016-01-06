package LP;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import LN.clsGestor;
import LN.clsClub;

public class ModificarClubFrm extends JFrame implements ActionListener, InternalFrameListener{

	private static final long serialVersionUID = 1L;
	private JLabel lblSeleccionaElClub;
	private JList<clsClub>listClub;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private clsGestor ges=new clsGestor();
	
	public ModificarClubFrm(){
		setTitle("Modificar club");
		getContentPane().setLayout(null);
		
		lblSeleccionaElClub = new JLabel("Selecciona el club cuyos datos deseas modificar");
		lblSeleccionaElClub.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSeleccionaElClub.setBounds(10, 0, 275, 34);
		getContentPane().add(lblSeleccionaElClub);
		
		listClub = new JList<clsClub>();
		LinkedList<clsClub> clubes=new LinkedList<clsClub>();
		try {
			clubes=ges.ListaClubes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ListaClubesMdl modelo=new ListaClubesMdl(clubes);
		listClub.setModel(modelo);
		listClub.setBounds(20, 34, 200, 283);
		getContentPane().add(listClub);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(30, 328, 89, 23);
		btnAceptar.setActionCommand("aceptar");
		btnAceptar.addActionListener(this);
		getContentPane().add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(131, 328, 89, 23);
		btnCancelar.setActionCommand("cancelar");
		btnCancelar.addActionListener(this);
		getContentPane().add(btnCancelar);
		
		this.setSize(580, 400);
	}

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		LinkedList<clsClub> clubes=new LinkedList<clsClub>();
		try {
			clubes=ges.ListaClubes();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ListaClubesMdl model=new ListaClubesMdl(clubes);
		listClub.setModel(model);
		listClub.repaint();
		this.getContentPane().revalidate();
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		LinkedList<clsClub> clubes=new LinkedList<clsClub>();
		try {
			clubes=ges.ListaClubes();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ListaClubesMdl model=new ListaClubesMdl(clubes);
		listClub.setModel(model);
		listClub.repaint();
		this.getContentPane().revalidate();
	}

	@Override
	public void internalFrameIconified(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameActivated(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("aceptar")){
			clsClub club=listClub.getSelectedValue();
			ModificarClubInternFrm internal=new ModificarClubInternFrm(club.getNombre(), club.getPais(), club.getPresupuesto(), club.getId_club());
			internal.setBounds(288, 11, 238, 292);
			getContentPane().add(internal);
			internal.setSize(new Dimension(252, 323));
			internal.addInternalFrameListener(this);
			internal.setVisible(true);
		} else if(e.getActionCommand().equals("cancelar")){
			this.dispose();
		}
	}
}
