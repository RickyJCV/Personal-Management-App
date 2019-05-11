package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.conexion;
import Modelo.hash;

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

public class administrarUsuarios extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodUser;
	private JTextField txtNombreUser;
	private JTextField txtPass;
	private JTextField txtNombre;
	private JTextField txtCorreo;
	private JTextField txtTipoUser;

	PreparedStatement ps;
	ResultSet rs;

	private void limpiarCajas() {
		txtCodUser.setText(null);
		txtNombreUser.setText(null);
		txtPass.setText(null);
		txtNombre.setText(null);
		txtCorreo.setText(null);
		txtTipoUser.setText(null);
	}

	/**
	 * Create the frame.
	 */
	public administrarUsuarios() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 546, 348);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("C\u00F3digo Usuario:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel lblNewLabel_1 = new JLabel("Nombre usuario:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel lblApellido = new JLabel("Contrase\u00F1a:");
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel lblPuesto = new JLabel("Nombre:");
		lblPuesto.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel lblNewLabel_2 = new JLabel("Correo:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));

		txtCodUser = new JTextField();
		txtCodUser.setColumns(10);

		txtNombreUser = new JTextField();
		txtNombreUser.setColumns(10);

		txtPass = new JTextField();
		txtPass.setColumns(10);

		txtNombre = new JTextField();
		txtNombre.setColumns(10);

		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = null;

				try {				
					con = conexion.getConexion();
					ps = con.prepareStatement("UPDATE usuarios SET usuario=?, password=?, nombre=?, correo=?, id_tipo=? WHERE id=?");
					String nuevoPass = hash.sha1(txtPass.getText());
					ps.setString(1, txtNombreUser.getText());
					ps.setString(2, nuevoPass);
					ps.setString(3, txtNombre.getText());
					ps.setString(4, txtCorreo.getText());
					ps.setInt(5, Integer.parseInt(txtTipoUser.getText()));
					ps.setInt(6, Integer.parseInt(txtCodUser.getText()));

					int res = ps.executeUpdate();

					if (res > 0) {
						JOptionPane.showMessageDialog(null, "Usuario Modificado");
						limpiarCajas();
					} else {
						JOptionPane.showMessageDialog(null, "Error al Modificar Usuario","Error",JOptionPane.ERROR_MESSAGE);
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
					ps = con.prepareStatement("DELETE FROM usuarios WHERE id=?");
					ps.setInt(1, Integer.parseInt(txtCodUser.getText()));

					int res = ps.executeUpdate();

					if (res > 0) {
						JOptionPane.showMessageDialog(null, "Usuario Eliminado");
						limpiarCajas();
					} else {
						JOptionPane.showMessageDialog(null, "Error al Eliminar Usuario","Error",JOptionPane.ERROR_MESSAGE);
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
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = null;

				try {
					con = conexion.getConexion();
					ps = con.prepareStatement("SELECT usuario,password,nombre,correo,id_tipo FROM usuarios WHERE id=?");
					ps.setString(1, txtCodUser.getText());
					
					rs = ps.executeQuery();
					
					if (rs.next()) {
						txtNombreUser.setText(rs.getString("usuario"));
						txtPass.setText(rs.getString("password"));
						txtNombre.setText(rs.getString("nombre"));
						txtCorreo.setText(rs.getString("correo"));
						txtTipoUser.setText(rs.getString("id_tipo"));
					}else {
						JOptionPane.showMessageDialog(null, "No existe un empleado con ese código, porfavor introduce uno válido", "Error", JOptionPane.WARNING_MESSAGE);
					}
				} catch (Exception err) {
					System.err.println(err);
				}
			}
		});
		
		txtTipoUser = new JTextField();
		txtTipoUser.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Tipo Usuario:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblNewLabel_4 = new JLabel("Introduzca un c\u00F3digo");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.ITALIC, 10));
		
		JLabel lblNewLabel_5 = new JLabel("para administrar ese usuario.");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.ITALIC, 10));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
							.addGap(79))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(104)
							.addComponent(txtCodUser)))
					.addGap(10)
					.addComponent(btnBuscar, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_5, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
							.addGap(10))
						.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
					.addGap(42))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(53)
					.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
					.addGap(20)
					.addComponent(txtNombreUser, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
					.addGap(152))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(85)
					.addComponent(lblApellido, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
					.addGap(3)
					.addComponent(txtPass, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
					.addGap(152))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(95)
					.addComponent(lblPuesto, GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(txtNombre, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
					.addGap(152))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(105)
					.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
					.addGap(22)
					.addComponent(txtCorreo, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
					.addGap(152))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(68)
					.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
					.addGap(20)
					.addComponent(txtTipoUser, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
					.addGap(152))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(107)
					.addComponent(btnModificar, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
					.addGap(20)
					.addComponent(btnEliminar, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
					.addGap(17)
					.addComponent(btnLimpiar, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
					.addGap(109))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(17)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addComponent(txtCodUser)
							.addGap(2))
						.addComponent(btnBuscar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(9)
							.addComponent(lblNewLabel_5, GroupLayout.DEFAULT_SIZE, 14, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, 14, Short.MAX_VALUE)
							.addGap(9)))
					.addGap(37)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(3))
						.addComponent(txtNombreUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblApellido))
						.addComponent(txtPass))
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblPuesto, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(3))
						.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel_2))
						.addComponent(txtCorreo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(3))
						.addComponent(txtTipoUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(22)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnModificar)
						.addComponent(btnEliminar)
						.addComponent(btnLimpiar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(34))
		);
		contentPane.setLayout(gl_contentPane);
	}
}

