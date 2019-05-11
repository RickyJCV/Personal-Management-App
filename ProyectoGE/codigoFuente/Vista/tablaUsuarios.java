package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Modelo.conexion;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class tablaUsuarios extends JFrame {

	private JPanel contentPane;
	private JTable jtUsuarios;
	private JTextField txtCampo;
	private JScrollPane scroll_1;

	/**
	 * Creamos la tabla que pueden ver los empleados
	 */
	public tablaUsuarios() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 661, 519);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();

		jtUsuarios = new JTable();
		panel.add(jtUsuarios);

		try {
			/**
			 * Creamos el objeto con las columnas y le pasamos el modelo por defecto junto a
			 * un scroll
			 */
			Object[][] data = new Object[0][0];
			String[] datos = { "ID Usuario","Usuario", "Contraseña", "Nombre", "Correo", "Última sesión", "Tipo de Usuario" };
			DefaultTableModel modelo = new DefaultTableModel(data, datos);
			jtUsuarios.setModel(modelo);
			scroll_1 = new JScrollPane(jtUsuarios);
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
			String sql = "SELECT * FROM usuarios";
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
			JOptionPane.showMessageDialog(null, "No se puede mostrar la tabla de usuarios");
		}

		/**
		 * Añadimos un boton de busqueda para encontrar nuestros datos
		 */
		JButton btnCargar = new JButton("Buscar");
		btnCargar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jtUsuarios = new JTable();
				jtUsuarios.setBounds(22, 22, 561, 338);
				panel.add(jtUsuarios);
				String campo = txtCampo.getText();
				String where = "";

				if (!"".equals(campo)) {
					where = "WHERE id = '" + campo + "'";

					try {
						/**
						 * Creamos el objeto con las columnas y le pasamos el modelo por defecto junto a
						 * un scroll
						 */
						Object[][] data = new Object[0][0];
						String[] datos = { "ID Usuario","Usuario", "Contraseña", "Nombre", "Correo", "Última sesión", "Tipo de Usuario" };
						DefaultTableModel modelo = new DefaultTableModel(data, datos);
						jtUsuarios.setModel(modelo);
						JScrollPane scroll = new JScrollPane(jtUsuarios);
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
						String sql = "SELECT * FROM usuarios " + where;
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
						JOptionPane.showMessageDialog(null, "No se puede mostrar la tabla de usuarios");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Debes introducir un ID de usuario");
				}
			}
		});

		txtCampo = new JTextField();
		txtCampo.setColumns(10);

		JLabel lblNewLabel = new JLabel("Introduce el ID de usuario:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(scroll_1, GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scroll_1, GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
		);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(29)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
							.addGap(150))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(212)
							.addComponent(txtCampo, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)))
					.addGap(10)
					.addComponent(btnCargar, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
					.addGap(136))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(12)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(3))
						.addComponent(txtCampo))
					.addGap(12))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(11)
					.addComponent(btnCargar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(10))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);

	}
}
