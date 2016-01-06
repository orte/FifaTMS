package LP;
import javax.swing.JFrame;


import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Map;

import javax.swing.JButton;

import LN.clsGestor;
import LN.clsJugador;

import java.awt.Font;

/**
 * Clase ventana que hereda de JFrame y que muestra una JTable con los jugadore que han sido fichados. Tiene un botón
 * para configurar el orden en el que se quieren ver los elementos de la tabla, si ordenados alfabéticamente o por salario
 * @author jon.orte
 *
 */
public class TablasJugadoresFrm extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private Map<Integer, clsJugador> jugadores;
	private JTable table;
	private JButton btnOrden;
	private boolean modo=false;
	
	public TablasJugadoresFrm() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Lista de jugadores");
		getContentPane().setLayout(null);
		
		clsGestor ges=new clsGestor();
		try {
			jugadores=ges.MapaJugEdad();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TablaJugadoresMdl model=new TablaJugadoresMdl(jugadores);
		table = new JTable(model.data, model.columnNames);
		table.setFillsViewportHeight(true);
		table.setEnabled(true);
		table.setRowSelectionAllowed(true);
		model.fireTableDataChanged();
		JScrollPane scrl=new JScrollPane(table);
		scrl.setBounds(20, 11, 624, 351);
		table.setVisible(true);
		getContentPane().add(scrl);
		
		btnOrden = new JButton("Ordenar por salario");
		btnOrden.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnOrden.setBounds(214, 373, 200, 23);
		btnOrden.addActionListener(this);
		getContentPane().add(btnOrden);
		this.setSize(667, 433);
	}
	/**
	 * Clase interna usada de modelo de tabla para la tabla de jugadores que extiende de AbstractTableModel. Tiene dos arrays 
	 * como atributos, uno de Strings con los nombres de las columnas y otro bidimensional de la clase Object para los datos. 
	 * Para rellenar ese array se usan los datos de un Map.
	 * @author jon.orte
	 */
	class TablaJugadoresMdl extends AbstractTableModel{

		private static final long serialVersionUID = 1L;
		private String[] columnNames={"Nombre", "Apellido", "Nacionalidad", "Edad", "Salario"};
		Object[][] data;
		
		public TablaJugadoresMdl(Map<Integer, clsJugador> m){
			
			
			int filas = m.size();
    		int cont;
    		data=new Object[filas][];
    		cont=0;
			
			for (Map.Entry<Integer, clsJugador> entry : m.entrySet()){
    			Object[]a={
    					   new String(entry.getValue().getNombre()),
    					   new String(entry.getValue().getApellido()),
    					   new String(entry.getValue().getNacionalidad()),
    					   new Integer(entry.getValue().getEdad()),
    					   new Double(entry.getValue().getSalario())
    			};		
    			data[cont]=a;
    			cont++;
    		}
		}
		public void setData(Map<Integer, clsJugador> m){
			int filas = m.size();
    		int cont;
    		data=new Object[filas][];
    		cont=0;
			
			for (Map.Entry<Integer, clsJugador> entry : m.entrySet()){
    			Object[]a={
    					new String(entry.getValue().getNombre()),
 					   	new String(entry.getValue().getApellido()),
 					   	new String(entry.getValue().getNacionalidad()),
 					   	new Integer(entry.getValue().getEdad()),
 					   	new Double(entry.getValue().getSalario())
    			};		
    			data[cont]=a;
    			cont++;
    		}
		}
		
		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Object getValueAt(int arg0, int arg1) {
			// TODO Auto-generated method stub
			return null;
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }
		
	}
	/**
	 * Al pulsar el botón que determina el orden de los elementos de la tabla, se cargará el mapa de alumnos con el órden
	 * que corresponda y se actualizará la tabla, además de cambiar el título del botón
	 * @author jon.orte
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		clsGestor ges=new clsGestor();
		try {
			if(modo==false){
				jugadores=ges.MapaJugSalario();
				modo=true;
				btnOrden.setText("Ordenar alfabéticamente");
				btnOrden.repaint();
				this.getContentPane().revalidate();
			}
			else{
				jugadores=ges.MapaJugEdad();
				modo=false;
				btnOrden.setText("Ordenar por salario");
				btnOrden.repaint();
				this.getContentPane().revalidate();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TablaJugadoresMdl model=new TablaJugadoresMdl(jugadores);
		table=new JTable(model.data,model.columnNames);
		table.setFillsViewportHeight(true);
		table.setEnabled(true);
		table.setRowSelectionAllowed(true);
		model.fireTableDataChanged();
		JScrollPane scrl=new JScrollPane(table);
		scrl.setBounds(20, 11, 624, 351);
		table.setVisible(true);
		getContentPane().add(scrl);
	}
}
