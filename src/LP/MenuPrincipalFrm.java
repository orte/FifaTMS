package LP;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Ventana del menú principal. Cuenta con una barra de menú superior y un fondo de pantalla. Pulsando sobre los elementos de la barra de 
 * menú se cargan las diferentes funciones de la aplicación, que se finalizará cuando esta ventana se cierre.
 * @author jon.orte
 *
 */
public class MenuPrincipalFrm extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JMenuItem mntmAgentesQueMs;
	private JMenuItem mntmClubesQueMs;
	private JMenuItem mntmLosTraspasosMs;
	private JMenuItem mntmClubesYSus;
	private JMenuItem mntmPaisesDinero;
	private JMenu mnListas;
	private JMenuItem mntmCancelarTraspaso;
	private JMenuItem mntmNuevoTraspaso;
	private JMenuItem mntmModificarClub;
	private JMenuItem mntmNuevoClub;
	private JMenu mnEditar;
	private JMenuBar menuBar;
	private JPanel fondo;
	private clsMenu menu=new clsMenu();

	public MenuPrincipalFrm() {
		setResizable(false);
		setTitle("FIFA Transfer Matching System");
		setBounds(100, 100, 466, 313);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pane=(JPanel)getContentPane();
		pane.setLayout(new BorderLayout());
		try {
			fondo=new JPanelWithBackground("fifa1.jpg");
			fondo.setPreferredSize(new Dimension(630,380));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setSize(new Dimension(604, 334));
		pane.add(fondo, BorderLayout.CENTER);
		fondo.setLayout(null);
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
		mntmClubesYSus.setActionCommand("clubes y fichajes");
		mnListas.add(mntmClubesYSus);
		
		mntmLosTraspasosMs = new JMenuItem("Agentes que m\u00E1s han ganado");
		mntmLosTraspasosMs.addActionListener(this);
		mntmLosTraspasosMs.setActionCommand("top traspasos");
		mnListas.add(mntmLosTraspasosMs);
		
		mntmClubesQueMs = new JMenuItem("Lista de jugadores");
		mntmClubesQueMs.addActionListener(this);
		mntmClubesQueMs.setActionCommand("jugadores");
		mnListas.add(mntmClubesQueMs);
		
		mntmAgentesQueMs = new JMenuItem("Los traspasos m\u00E1s caros");
		mntmAgentesQueMs.addActionListener(this);
		mntmAgentesQueMs.setActionCommand("top agentes");
		mnListas.add(mntmAgentesQueMs);
		
		mntmPaisesDinero = new JMenuItem("Lista de países y dinero gastado por cada uno");
		mntmPaisesDinero.addActionListener(this);
		mntmPaisesDinero.setActionCommand("paises");
		mnListas.add(mntmPaisesDinero);
	}

	/**
	 * Dependiendo del botón que se pulse, ya que cada uno tiene un actionCommand distinto, se llamará al método de clsMenú que cree la ventana
	 * que vaya a realizar la función especificada.
	 * @author jon.orte
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command=e.getActionCommand();
		switch(command){
		case "nuevo club":menu.AltaClub(); break;
		case "modificar club":menu.ModificarClub(); break;
		case "nuevo traspaso":menu.NuevoFichaje(); break;
		case "cancelar traspaso":menu.CancelarTraspaso(); break;
		case "clubes y fichajes":menu.ListaFichajes(); break;
		case "top traspasos": menu.RankingFichajes(); break;
		case "jugadores":menu.ListaJugadores(); break;
		case "top agentes":menu.RankingAgentes(); break;
		case "paises":menu.RankingPaises(); break;
		}
	}

}
