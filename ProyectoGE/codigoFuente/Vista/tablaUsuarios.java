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
import Modelo.sqlUsuarios;

import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileWriter;

/**
 * Esta vista se usa para mostrar la tabla de usuarios
 * 
 * @author Ricardo Jesús Cabrera Valero
 *
 */

public class tablaUsuarios extends JFrame {

	private JPanel contentPane;
	private JTable jtUsuarios;
	private JTextField txtCampo;

	/**
	 * Creamos la tabla para visualizar los usuarios
	 */
	public tablaUsuarios() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(tablaUsuarios.class.getResource("/imagenes/home.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 661, 519);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		jtUsuarios = new JTable();
		jtUsuarios.setBounds(22, 22, 561, 338);
		panel.add(jtUsuarios);

		try {
			/**
			 * Creamos el objeto con las columnas y le pasamos el modelo por defecto junto a
			 * un scroll
			 */
			Object[][] data = new Object[0][0];
			String[] datos = { "ID Usuario", "Usuario", "Contraseña", "Nombre", "Correo", "Última sesión",
					"Tipo de Usuario" };
			DefaultTableModel modelo = new DefaultTableModel(data, datos);
			jtUsuarios.setModel(modelo);
			JScrollPane scroll = new JScrollPane(jtUsuarios);
			scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scroll.setVisible(true);
			getContentPane().add(scroll, BorderLayout.NORTH);

			/**
			 * Establecemos la conexion
			 */
			PreparedStatement ps = null;
			ResultSet rs = null;
			conexion conn = new conexion();
			Connection con = conn.getConexion();
			/**
			 * Ejecutamos la sql para recibir los datos de los usuarios
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
		btnCargar.setIcon(new ImageIcon(tablaUsuarios.class.getResource("/imagenes/Buscar.png")));
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
						String[] datos = { "ID Usuario", "Usuario", "Contraseña", "Nombre", "Correo", "Última sesión",
								"Tipo de Usuario" };
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

		btnCargar.setBounds(354, 11, 108, 23);
		panel.add(btnCargar);

		txtCampo = new JTextField();
		txtCampo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (c < '0' || c > '9')
					e.consume();
			}
		});
		txtCampo.setBounds(185, 12, 159, 20);
		panel.add(txtCampo);
		txtCampo.setColumns(10);

		JLabel lblNewLabel = new JLabel("Introduce el ID de usuario:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 15, 194, 14);
		panel.add(lblNewLabel);

		/**
		 * Botón para generar un archivo csv con la tabla de la base de datos
		 */
		JButton btnCsv = new JButton("Generar CSV");
		btnCsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				conexion conn = new conexion();
				Connection con = conn.getConexion();
				String sql2 = "SELECT * FROM usuarios";

				try {
					PreparedStatement ps = con.prepareStatement(sql2);
					;
					ResultSet rs2 = ps.executeQuery();

					String extension = ".csv";
					String ruta = "codigoFuente/ficheros/bbddUsuarios" + extension;
					FileWriter writer = new FileWriter(ruta);
					// Establecemos las columnas que tendrá
					writer.write("Id;Usuario;Password;Nombre;Correo;Ultima Sesion;Tipo de ID\n");
					/**
					 * Siguiente linea escribe bbdd en fichero
					 */
					while (rs2.next()) {

						writer.write(rs2.getInt("id") + ";" + rs2.getString("usuario") + ";" + rs2.getString("password")
								+ ";" + rs2.getString("nombre") + ";" + rs2.getString("correo") + ";"
								+ rs2.getString("last_session") + ";" + rs2.getString("id_tipo") + "\n");
					}
					writer.close();
					JOptionPane.showMessageDialog(null, "Fichero creado con éxito");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Error");
				}

			}
		});
		btnCsv.setIcon(new ImageIcon(tablaUsuarios.class.getResource("/imagenes/csv.png")));
		btnCsv.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCsv.setBounds(483, 11, 142, 23);
		panel.add(btnCsv);

	}
}