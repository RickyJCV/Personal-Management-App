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
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class tablaEmpleados extends JFrame {

	private JPanel contentPane;
	private JTable jtEmpleados;
	private JTextField txtCampo;

	/**
	 * Creamos la tabla que pueden ver los empleados
	 */
	public tablaEmpleados() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(tablaEmpleados.class.getResource("/imagenes/home.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
			 * Creamos el objeto con las columnas y le pasamos el modelo por defecto junto a
			 * un scroll
			 */
			Object[][] data = new Object[0][0];
			String[] datos = { "Código Empleado", "Nombre", "Apellido", "Puesto", "Horas" };
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
		btnCargar.setIcon(new ImageIcon(tablaEmpleados.class.getResource("/imagenes/Buscar.png")));
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

		btnCargar.setBounds(386, 11, 109, 23);
		panel.add(btnCargar);

		txtCampo = new JTextField();
		txtCampo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				if(c<'0' || c>'9')
					arg0.consume();
			}
		});
		txtCampo.setBounds(217, 12, 159, 20);
		panel.add(txtCampo);
		txtCampo.setColumns(10);

		JLabel lblNewLabel = new JLabel("Introduce tu c\u00F3digo de empleado:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 15, 221, 14);
		panel.add(lblNewLabel);
		
		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.setIcon(new ImageIcon(tablaEmpleados.class.getResource("/imagenes/imprimir.png")));
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/**
				 * FUNCION QUE IMPRIME UNA TABLA PARA UNA IMPRESORA O PDF
				 */
				MessageFormat header =new MessageFormat("Lista de Empleados");
				MessageFormat pie =new MessageFormat("Página 1");
				try {
					jtEmpleados.print(JTable.PrintMode.FIT_WIDTH, header, pie);

				}catch(java.awt.print.PrinterException f) {
					System.err.format("Error de impresion", f.getMessage());

				}
			}
		});
		btnImprimir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnImprimir.setBounds(505, 11, 111, 23);
		panel.add(btnImprimir);

	}
}