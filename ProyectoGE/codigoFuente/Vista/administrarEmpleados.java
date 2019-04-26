package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class administrarEmpleados extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodEmpleado;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtPuesto;
	private JTextField txtSueldo;
	private JTextField txtHoras;

	private final String base = "empleados";
	private final String user = "root";
	private final String password = "manolo";
	private final String timeZone = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private final String url = "jdbc:mysql://localhost:3308/" + base + timeZone;
	private Connection con = null;

	PreparedStatement ps;
	ResultSet rs;

	/**
	 * Función que genera la conexión con la base de datos
	 */
	public Connection getConexion() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

	private void limpiarCajas() {
		txtCodEmpleado.setText(null);
		txtNombre.setText(null);
		txtApellido.setText(null);
		txtPuesto.setText(null);
		txtSueldo.setText(null);
		txtHoras.setText(null);
	}

	/**
	 * Create the frame.
	 */
	public administrarEmpleados() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 570, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("C\u00F3digo Empleado:");
		lblNewLabel.setBounds(73, 37, 111, 23);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(112, 71, 46, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(112, 105, 46, 14);
		contentPane.add(lblApellido);

		JLabel lblPuesto = new JLabel("Puesto:");
		lblPuesto.setBounds(112, 130, 46, 14);
		contentPane.add(lblPuesto);

		JLabel lblNewLabel_2 = new JLabel("Sueldo:");
		lblNewLabel_2.setBounds(112, 167, 46, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblHoras = new JLabel("Horas:");
		lblHoras.setBounds(112, 198, 46, 14);
		contentPane.add(lblHoras);

		txtCodEmpleado = new JTextField();
		txtCodEmpleado.setBounds(178, 38, 86, 20);
		contentPane.add(txtCodEmpleado);
		txtCodEmpleado.setColumns(10);

		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(178, 68, 195, 20);
		contentPane.add(txtNombre);

		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(178, 102, 195, 20);
		contentPane.add(txtApellido);

		txtPuesto = new JTextField();
		txtPuesto.setColumns(10);
		txtPuesto.setBounds(178, 133, 195, 20);
		contentPane.add(txtPuesto);

		txtSueldo = new JTextField();
		txtSueldo.setColumns(10);
		txtSueldo.setBounds(178, 164, 195, 20);
		contentPane.add(txtSueldo);

		txtHoras = new JTextField();
		txtHoras.setColumns(10);
		txtHoras.setBounds(178, 195, 195, 20);
		contentPane.add(txtHoras);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = null;

				try {
					con = getConexion();
					ps = con.prepareStatement("INSERT INTO empleado(nombre, apellido, puesto, sueldo, horas) VALUES (?,?,?,?,?)");
					ps.setString(1, txtNombre.getText());
					ps.setString(2, txtApellido.getText());
					ps.setString(3, txtPuesto.getText());
					ps.setInt(4, Integer.parseInt(txtSueldo.getText()));
					ps.setInt(5, Integer.parseInt(txtHoras.getText()));

					int res = ps.executeUpdate();

					if (res > 0) {
						JOptionPane.showMessageDialog(null, "Empleado Guardado");
						limpiarCajas();
					} else {
						JOptionPane.showMessageDialog(null, "Error al Guardar Empleado");
						limpiarCajas();
					}
					con.close();
				} catch (Exception err) {
					System.err.println(err);
				}
			}
		});
		btnGuardar.setBounds(73, 287, 89, 23);
		contentPane.add(btnGuardar);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = null;

				try {
					con = getConexion();
					ps = con.prepareStatement("UPDATE empleado SET nombre=?, apellido=?, puesto=?, sueldo=?, horas=? WHERE cod_empleado=?");
					ps.setString(1, txtNombre.getText());
					ps.setString(2, txtApellido.getText());
					ps.setString(3, txtPuesto.getText());
					ps.setInt(4, Integer.parseInt(txtSueldo.getText()));
					ps.setInt(5, Integer.parseInt(txtHoras.getText()));
					ps.setInt(6, Integer.parseInt(txtCodEmpleado.getText()));

					int res = ps.executeUpdate();

					if (res > 0) {
						JOptionPane.showMessageDialog(null, "Empleado Modificado");
						limpiarCajas();
					} else {
						JOptionPane.showMessageDialog(null, "Error al Modificar Empleado");
						limpiarCajas();
					}
					con.close();
				} catch (Exception err) {
					System.err.println(err);
				}
			}
		});
		btnModificar.setBounds(175, 287, 89, 23);
		contentPane.add(btnModificar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = null;

				try {
					con = getConexion();
					ps = con.prepareStatement("DELETE FROM empleado WHERE cod_empleado=?");
					ps.setInt(1, Integer.parseInt(txtCodEmpleado.getText()));

					int res = ps.executeUpdate();

					if (res > 0) {
						JOptionPane.showMessageDialog(null, "Empleado Eliminado");
						limpiarCajas();
					} else {
						JOptionPane.showMessageDialog(null, "Error al Eliminar Empleado");
						limpiarCajas();
					}
					con.close();
				} catch (Exception err) {
					System.err.println(err);
				}
			}
		});
		btnEliminar.setBounds(274, 287, 89, 23);
		contentPane.add(btnEliminar);

		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCajas();
			}
		});
		btnLimpiar.setBounds(373, 287, 89, 23);
		contentPane.add(btnLimpiar);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = null;

				try {
					con = getConexion();
					ps = con.prepareStatement("SELECT nombre,apellido,puesto,sueldo,horas FROM empleado WHERE cod_empleado=?");
					ps.setString(1, txtCodEmpleado.getText());
					
					rs = ps.executeQuery();
					
					if (rs.next()) {
						txtNombre.setText(rs.getString("nombre"));
						txtApellido.setText(rs.getString("apellido"));
						txtPuesto.setText(rs.getString("puesto"));
						txtSueldo.setText(rs.getString("sueldo"));
						txtHoras.setText(rs.getString("horas"));
					}else {
						JOptionPane.showMessageDialog(null, "No existe un empleado con ese código, porfavor introduce uno válido");
					}
				} catch (Exception err) {
					System.err.println(err);
				}
			}
		});
		btnBuscar.setBounds(284, 37, 89, 23);
		contentPane.add(btnBuscar);
	}
}
