package LP;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipalFrm extends JFrame implements ActionListener{
	private JMenuItem mntmAgentesQueMs;
	private JMenuItem mntmClubesQueMs;
	private JMenuItem mntmLosTraspasosMs;
	private JMenuItem mntmClubesYSus;
	private JMenu mnListas;
	private JMenuItem mntmCancelarTraspaso;
	private JMenuItem mntmNuevoTraspaso;
	private JMenuItem mntmModificarClub;
	private JMenuItem mntmNuevoClub;
	private JMenu mnEditar;
	private JMenuBar menuBar;
	private clsMenu menu=new clsMenu();

	public MenuPrincipalFrm() {
		setTitle("FIFA Transfer Matching System");
		setBounds(100, 100, 466, 313);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuBar = new JMenuBar();
		getContentPane().add(menuBar, BorderLayout.NORTH);
		
		mnEditar = new JMenu("Editar");
		menuBar.add(mnEditar);
		
		mntmNuevoClub = new JMenuItem("Nuevo club");
		mntmNuevoClub.addActionListener(this);
		mntmNuevoClub.setActionCommand("nuevo club");
		mnEditar.add(mntmNuevoClub);
		
		mntmModificarClub = new JMenuItem("Modificar club");
		mntmModificarClub.addActionListener(this);
		mntmModificarClub.setActionCommand("modificar club");
		mnEditar.add(mntmModificarClub);
		
		mntmNuevoTraspaso = new JMenuItem("Nuevo traspaso");
		mntmNuevoTraspaso.addActionListener(this);
		mntmNuevoTraspaso.setActionCommand("nuevo traspaso");
		mnEditar.add(mntmNuevoTraspaso);
		
		mntmCancelarTraspaso = new JMenuItem("Cancelar traspaso");
		mntmCancelarTraspaso.addActionListener(this);
		mntmCancelarTraspaso.setActionCommand("cancelar traspaso");
		mnEditar.add(mntmCancelarTraspaso);
		
		mnListas = new JMenu("Listas");
		menuBar.add(mnListas);
		
		mntmClubesYSus = new JMenuItem("Clubes y sus fichajes");
		mntmClubesYSus.addActionListener(this);
		mntmClubesYSus.setActionCommand("clubes y fchajes");
		mnListas.add(mntmClubesYSus);
		
		mntmLosTraspasosMs = new JMenuItem("Los traspasos m\u00E1s caros");
		mntmLosTraspasosMs.addActionListener(this);
		mntmLosTraspasosMs.setActionCommand("top traspasos");
		mnListas.add(mntmLosTraspasosMs);
		
		mntmClubesQueMs = new JMenuItem("Clubes que m\u00E1s han gastado");
		mntmClubesQueMs.addActionListener(this);
		mntmClubesQueMs.setActionCommand("top clubes");
		mnListas.add(mntmClubesQueMs);
		
		mntmAgentesQueMs = new JMenuItem("Agentes que m\u00E1s han ganado");
		mntmAgentesQueMs.addActionListener(this);
		mntmAgentesQueMs.setActionCommand("top agentes");
		mnListas.add(mntmAgentesQueMs);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command=e.getActionCommand();
		switch(command){
		case "nuevo club":menu.AltaClub();
		case "modificar club":menu.ModificarClub();
		case "nuevo traspaso":menu.NuevoFichaje();
		case "cancelar traspaso":menu.CancelarTraspaso();
		case "clubes y fichajes":menu.ListaFichajes();
		case "top traspasos": menu.RankingFichajes();
		case "top clubes":menu.TopClubes();
		case "top agentes":menu.RankingAgentes();
		}
	}

}
