package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.conexion;

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
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class administrarEmpleados extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodEmpleado;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtPuesto;
	private JTextField txtSueldo;
	private JTextField txtHoras;	

	PreparedStatement ps;
	ResultSet rs;

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

		JLabel lblNewLabel = new JLabel("C\u00F3digo Empleado:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel lblPuesto = new JLabel("Puesto:");
		lblPuesto.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel lblNewLabel_2 = new JLabel("Sueldo:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel lblHoras = new JLabel("Horas:");
		lblHoras.setFont(new Font("Tahoma", Font.BOLD, 11));

		txtCodEmpleado = new JTextField();
		txtCodEmpleado.setColumns(10);

		txtNombre = new JTextField();
		txtNombre.setColumns(10);

		txtApellido = new JTextField();
		txtApellido.setColumns(10);

		txtPuesto = new JTextField();
		txtPuesto.setColumns(10);

		txtSueldo = new JTextField();
		txtSueldo.setColumns(10);

		txtHoras = new JTextField();
		txtHoras.setColumns(10);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = null;

				try {
					con = conexion.getConexion();
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
						JOptionPane.showMessageDialog(null, "Error al Guardar Empleado", "Error", JOptionPane.ERROR_MESSAGE);
						limpiarCajas();
					}
					con.close();
				} catch (Exception err) {
					System.err.println(err);
				}
			}
		});

		JButton btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = null;

				try {
					con = conexion.getConexion();
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
						JOptionPane.showMessageDialog(null, "Error al Modificar Empleado", "Error", JOptionPane.ERROR_MESSAGE);
						limpiarCajas();
					}
					con.close();
				} catch (Exception err) {
					System.err.println(err);
				}
			}
		});

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = null;

				try {
					con = conexion.getConexion();
					ps = con.prepareStatement("DELETE FROM empleado WHERE cod_empleado=?");
					ps.setInt(1, Integer.parseInt(txtCodEmpleado.getText()));

					int res = ps.executeUpdate();

					if (res > 0) {
						JOptionPane.showMessageDialog(null, "Empleado Eliminado");
						limpiarCajas();
					} else {
						JOptionPane.showMessageDialog(null, "Error al Eliminar Empleado", "Error", JOptionPane.ERROR_MESSAGE);
						limpiarCajas();
					}
					con.close();
				} catch (Exception err) {
					System.err.println(err);
				}
			}
		});

		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCajas();
			}
		});

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = null;

				try {
					con = conexion.getConexion();
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
						JOptionPane.showMessageDialog(null, "No existe un empleado con ese código, porfavor introduce uno válido", "Error", JOptionPane.WARNING_MESSAGE);
						
					}
				} catch (Exception err) {
					System.err.println(err);
				}
			}
		});
		
		JLabel label = new JLabel("Introduzca un c\u00F3digo");
		label.setFont(new Font("Tahoma", Font.ITALIC, 10));
		
		JLabel lblParaAdministrarEse = new JLabel("para administrar ese empleado.");
		lblParaAdministrarEse.setFont(new Font("Tahoma", Font.ITALIC, 10));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(36)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(txtCodEmpleado, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(btnBuscar, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(label, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
						.addComponent(lblParaAdministrarEse, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
					.addGap(46))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(96)
					.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(txtNombre, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
					.addGap(176))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(87)
					.addComponent(lblApellido, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
					.addGap(26)
					.addComponent(txtApellido, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
					.addGap(176))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(96)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblPuesto, GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
							.addGap(189))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(77)
							.addComponent(txtPuesto, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)))
					.addGap(176))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(97)
					.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
					.addGap(17)
					.addComponent(txtSueldo, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
					.addGap(176))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(96)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(77)
							.addComponent(txtHoras, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblHoras, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
							.addGap(179)))
					.addGap(176))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(68)
					.addComponent(btnGuardar, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
					.addGap(13)
					.addComponent(btnModificar, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(btnEliminar, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(btnLimpiar, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
					.addGap(87))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addComponent(txtCodEmpleado)
							.addGap(2))
						.addComponent(btnBuscar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label, GroupLayout.DEFAULT_SIZE, 14, Short.MAX_VALUE)
							.addGap(9))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(9)
							.addComponent(lblParaAdministrarEse, GroupLayout.DEFAULT_SIZE, 14, Short.MAX_VALUE)))
					.addGap(47)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(3))
						.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblApellido, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(3))
						.addComponent(txtApellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblPuesto, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(3))
						.addComponent(txtPuesto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(3))
						.addComponent(txtSueldo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(14)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(txtHoras)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblHoras, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(3)))
					.addGap(46)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnGuardar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnModificar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnEliminar)
						.addComponent(btnLimpiar))
					.addGap(59))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
