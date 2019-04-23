package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Modelo.Conexion;

import javax.swing.JTable;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Button;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.ScrollPaneConstants;

public class tablaEmpleados extends JFrame {

	private JPanel contentPane;
	private JTable jtEmpleados;
	private JTextField txtCampo;

	/**
	 * Creamos la tabla que pueden ver los empleados
	 */
	public tablaEmpleados() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 661, 519);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		jtEmpleados = new JTable();
		jtEmpleados.setBounds(22, 22, 561, 338);
		panel.add(jtEmpleados);

		try {
			/**
			 * Creamos el objeto con las columnas y le pasamos el modelo por defecto
			 * junto a un scroll
			 */
			Object[][] data = new Object[0][0];
			String[] datos = { "Código Empleado", "Nombre", "Apellido","Puesto", "Sueldo", "Horas" };
			DefaultTableModel modelo = new DefaultTableModel(data, datos);
			jtEmpleados.setModel(modelo);
			JScrollPane scroll = new JScrollPane(jtEmpleados);
			scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scroll.setVisible(true);
			getContentPane().add(scroll, BorderLayout.NORTH);
			
			/**
			 * Establecemos la conexion
			 */
			PreparedStatement ps = null;
			ResultSet rs = null;
			Conexion conn = new Conexion();
			Connection con = conn.getConexion();
			/**
			 * Ejecutamos la sql para recibir los datos
			 */
			String sql = "SELECT cod_empleado,nombre,apellido,puesto,sueldo,horas FROM empleado";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			ResultSetMetaData rsMd = rs.getMetaData();
			int cantidadColumnas = rsMd.getColumnCount();
			/**
			 * Rellenamos la tabla con los datos de la consulta sql
			 */
			while (rs.next()) {

				Object[] filas = new Object[cantidadColumnas];

				for (int i = 0; i < cantidadColumnas; i++) {
					filas[i] = rs.getObject(i + 1);

				}

				modelo.addRow(filas);
			}
		} catch (SQLException ex) {
			System.out.println("No se puede mostrar la tabla empleados");
		}
		
		/**
		 * Añadimos un boton de busqueda para encontrar nuestros datos
		 */
		JButton btnCargar = new JButton("Buscar");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jtEmpleados = new JTable();
				jtEmpleados.setBounds(22, 22, 561, 338);
				panel.add(jtEmpleados);
				String campo = txtCampo.getText();
				String where = "";
				
				if(!"".equals(campo)) {
					where = "WHERE cod_empleado = '"+campo+"'";
				}
				
				try {
					/**
					 * Creamos el objeto con las columnas y le pasamos el modelo por defecto
					 * junto a un scroll
					 */
					Object[][] data = new Object[0][0];
					String[] datos = { "Código Empleado", "Nombre", "Apellido","Puesto", "Sueldo", "Horas" };
					DefaultTableModel modelo = new DefaultTableModel(data, datos);
					jtEmpleados.setModel(modelo);
					JScrollPane scroll = new JScrollPane(jtEmpleados);
					getContentPane().add(scroll, BorderLayout.NORTH);
					
					/**
					 * Establecemos la conexion
					 */
					PreparedStatement ps = null;
					ResultSet rs = null;
					Conexion conn = new Conexion();
					Connection con = conn.getConexion();
					/**
					 * Ejecutamos la sql para recibir los datos, pero aquí ponemos el where para filtrar por el cod_empleado
					 */
					String sql = "SELECT cod_empleado,nombre,apellido,puesto,sueldo,horas FROM empleado "+ where;
					ps = con.prepareStatement(sql);
					rs = ps.executeQuery();

					ResultSetMetaData rsMd = rs.getMetaData();
					int cantidadColumnas = rsMd.getColumnCount();
					/**
					 * Rellenamos la tabla con los datos de la consulta sql
					 */
					while (rs.next()) {

						Object[] filas = new Object[cantidadColumnas];

						for (int i = 0; i < cantidadColumnas; i++) {
							filas[i] = rs.getObject(i + 1);

						}

						modelo.addRow(filas);
					}
				} catch (SQLException ex) {
					System.out.println("No se puede mostrar la tabla empleados");
				}
			}
		});
		btnCargar.setBounds(410, 11, 89, 23);
		panel.add(btnCargar);
		
		txtCampo = new JTextField();
		txtCampo.setBounds(241, 12, 159, 20);
		panel.add(txtCampo);
		txtCampo.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Introduce tu c\u00F3digo de empleado:");
		lblNewLabel.setBounds(10, 15, 221, 14);
		panel.add(lblNewLabel);

	}
}
