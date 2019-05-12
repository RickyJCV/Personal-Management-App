package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Modelo.conexion;

import javax.swing.JTable;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.awt.Button;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class tablaEmpleados extends JFrame {

	private JPanel contentPane;
	private JTable jtEmpleados;
	private JTextField txtCampo;
	private JScrollPane scroll_1;
	private JButton btnPdf;

	/**
	 * Creamos la tabla que pueden ver los empleados
	 */
	public tablaEmpleados() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 661, 519);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();

		jtEmpleados = new JTable();
		panel.add(jtEmpleados);

		try {
			/**
			 * Creamos el objeto con las columnas y le pasamos el modelo por defecto junto a
			 * un scroll
			 */
			Object[][] data = new Object[0][0];
			String[] datos = { "Código Empleado", "Nombre", "Apellido", "Puesto", "Horas" };
			DefaultTableModel modelo = new DefaultTableModel(data, datos);
			jtEmpleados.setModel(modelo);
			scroll_1 = new JScrollPane(jtEmpleados);
			scroll_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scroll_1.setVisible(true);

			/**
			 * Establecemos la conexion
			 */
			PreparedStatement ps = null;
			ResultSet rs = null;
			conexion conn = new conexion();
			Connection con = conn.getConexion();
			/**
			 * Ejecutamos la sql para recibir los datos
			 */
			String sql = "SELECT cod_empleado,nombre,apellido,puesto,horas FROM empleado";
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
			JOptionPane.showMessageDialog(null, "No se puede mostrar la tabla empleados");
		}

		/**
		 * Añadimos un boton de busqueda para encontrar nuestros datos
		 */
		JButton btnCargar = new JButton("Buscar");
		btnCargar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jtEmpleados = new JTable();
				jtEmpleados.setBounds(22, 22, 561, 338);
				panel.add(jtEmpleados);
				String campo = txtCampo.getText();
				String where = "";

				if (!"".equals(campo)) {
					where = "WHERE cod_empleado = '" + campo + "'";

					try {
						/**
						 * Creamos el objeto con las columnas y le pasamos el modelo por defecto junto a
						 * un scroll
						 */
						Object[][] data = new Object[0][0];
						String[] datos = { "Código Empleado", "Nombre", "Apellido", "Puesto", "Sueldo", "Horas" };
						DefaultTableModel modelo = new DefaultTableModel(data, datos);
						jtEmpleados.setModel(modelo);
						JScrollPane scroll = new JScrollPane(jtEmpleados);
						getContentPane().add(scroll, BorderLayout.NORTH);

						/**
						 * Establecemos la conexion
						 */
						PreparedStatement ps = null;
						ResultSet rs = null;
						conexion conn = new conexion();
						Connection con = conn.getConexion();
						/**
						 * Ejecutamos la sql para recibir los datos, pero aquí ponemos el where para
						 * filtrar por el cod_empleado
						 */
						String sql = "SELECT cod_empleado,nombre,apellido,puesto,sueldo,horas FROM empleado " + where;
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
						JOptionPane.showMessageDialog(null, "No se puede mostrar la tabla empleados");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Debes introducir un código de empleado");
				}
			}
		});

		txtCampo = new JTextField();
		txtCampo.setColumns(10);

		JLabel lblNewLabel = new JLabel("Introduce tu c\u00F3digo de empleado:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(scroll_1, GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scroll_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
		);
		
		btnPdf = new JButton("Imprimir");
		btnPdf.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
						/**
						 * FUNCION QUE IMPRIME UNA TABLA PARA UNA IMPRESORA O PDF
						 */
						MessageFormat header =new MessageFormat("Lista de Empleados");
						MessageFormat pie =new MessageFormat("Página 1");
						try {
							jtEmpleados.print(JTable.PrintMode.NORMAL, header, pie);
							
						}catch(java.awt.print.PrinterException f) {
							System.err.format("Error de impresion", f.getMessage());
							
						}
						
									
			
					}
				});
				btnPdf.setBounds(473, 4, 180, 56);
				panel.add(btnPdf);
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(10)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(txtCampo, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(btnCargar, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
					.addGap(28)
					.addComponent(btnPdf, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(19))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(15)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(15))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(12)
					.addComponent(txtCampo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(12))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCargar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnPdf, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(10))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);

	}
}
