package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Modelo.Conexion;

public class tablaEmpleados extends JFrame {

	private JPanel contentPane;
	private JTable jtEmpleados;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tablaEmpleados frame = new tablaEmpleados();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public tablaEmpleados() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 754, 575);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		jtEmpleados = new JTable();
		jtEmpleados.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo Empleado", "Nombre", "Apellido", "Sueldo", "Horas"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, Double.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		jtEmpleados.getColumnModel().getColumn(0).setPreferredWidth(105);
		jtEmpleados.setBounds(562, 397, -439, -345);
		contentPane.add(jtEmpleados);
		
		try {
			DefaultTableModel modelo = new DefaultTableModel();
			jtEmpleados.setModel(modelo);
			
			PreparedStatement ps = null;
			ResultSet rs = null;
			Conexion conn = new Conexion();
			Connection con = conn.getConexion();
			
			String sql = "SELECT cod_empleado,nombre,apellido,sueldo,horas FROM empleado";
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			
			ResultSetMetaData rsMd=rs.getMetaData();
			int cantidadColumnas = rsMd.getColumnCount();
			
			while(rs.next()) {
				
			}
		}catch(){
			
		}
	}

}
