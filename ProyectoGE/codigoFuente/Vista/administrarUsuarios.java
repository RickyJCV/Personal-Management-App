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
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("C\u00F3digo Usuario:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(38, 22, 111, 23);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nombre usuario:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(58, 85, 100, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblApellido = new JLabel("Contrase\u00F1a:");
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblApellido.setBounds(90, 116, 85, 14);
		contentPane.add(lblApellido);

		JLabel lblPuesto = new JLabel("Nombre:");
		lblPuesto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPuesto.setBounds(100, 147, 68, 14);
		contentPane.add(lblPuesto);

		JLabel lblNewLabel_2 = new JLabel("Correo:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(110, 178, 46, 14);
		contentPane.add(lblNewLabel_2);

		txtCodUser = new JTextField();
		txtCodUser.setBounds(142, 23, 86, 20);
		contentPane.add(txtCodUser);
		txtCodUser.setColumns(10);

		txtNombreUser = new JTextField();
		txtNombreUser.setColumns(10);
		txtNombreUser.setBounds(178, 82, 195, 20);
		contentPane.add(txtNombreUser);

		txtPass = new JTextField();
		txtPass.setColumns(10);
		txtPass.setBounds(178, 113, 195, 20);
		contentPane.add(txtPass);

		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(178, 144, 195, 20);
		contentPane.add(txtNombre);

		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(178, 175, 195, 20);
		contentPane.add(txtCorreo);

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
						JOptionPane.showMessageDialog(null, "Error al Modificar Usuario");
						limpiarCajas();
					}
					con.close();
				} catch (Exception err) {
					System.err.println(err);
				}
			}
		});
		btnModificar.setBounds(112, 248, 89, 23);
		contentPane.add(btnModificar);

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
						JOptionPane.showMessageDialog(null, "Error al Eliminar Usuario");
						limpiarCajas();
					}
					con.close();
				} catch (Exception err) {
					System.err.println(err);
				}
			}
		});
		btnEliminar.setBounds(221, 248, 89, 23);
		contentPane.add(btnEliminar);

		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCajas();
			}
		});
		btnLimpiar.setBounds(327, 248, 89, 23);
		contentPane.add(btnLimpiar);

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
						JOptionPane.showMessageDialog(null, "No existe un usuario con ese código, porfavor introduce uno válido");
					}
				} catch (Exception err) {
					System.err.println(err);
				}
			}
		});
		btnBuscar.setBounds(238, 22, 89, 23);
		contentPane.add(btnBuscar);
		
		txtTipoUser = new JTextField();
		txtTipoUser.setBounds(178, 206, 195, 20);
		contentPane.add(txtTipoUser);
		txtTipoUser.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Tipo Usuario:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(73, 209, 85, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Introduzca un c\u00F3digo");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblNewLabel_4.setBounds(337, 22, 146, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("para administrar ese usuario.");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblNewLabel_5.setBounds(337, 31, 136, 14);
		contentPane.add(lblNewLabel_5);
	}
}

