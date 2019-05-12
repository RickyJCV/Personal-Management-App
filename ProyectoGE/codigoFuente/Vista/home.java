package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.usuarios;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;

public class home extends JFrame {

	private JPanel contentPane;
	usuarios mod;

	/**
	 * Constructor que recibe el modelo de usuarios para el HOME
	 */
	public home(usuarios mod) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 593, 426);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnDarAltaUsuarios = new JButton("Dar Alta Usuarios");
		btnDarAltaUsuarios.setBounds(40, 188, 219, 35);
		btnDarAltaUsuarios.setFont(new Font("Consolas", Font.BOLD, 14));
		btnDarAltaUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registroAdmin Registro = new registroAdmin();
				Registro.setVisible(true);
			}
		});
		
		JButton btnVerUsuarios = new JButton("Ver Usuarios");
		btnVerUsuarios.setBounds(40, 140, 219, 35);
		btnVerUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablaUsuarios verUsuarios = new tablaUsuarios();
				verUsuarios.setVisible(true);
			}
		});
		btnVerUsuarios.setFont(new Font("Consolas", Font.BOLD, 14));
		
		JButton btnAdministrarUsuarios = new JButton("Administrar Usuarios");
		btnAdministrarUsuarios.setBounds(40, 234, 219, 37);
		btnAdministrarUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				administrarUsuarios verUsuarios = new administrarUsuarios();
				verUsuarios.setVisible(true);
			}
		});
		btnAdministrarUsuarios.setFont(new Font("Consolas", Font.BOLD, 14));
		
		JButton btnVerEmpleados = new JButton("Ver Empleados");
		btnVerEmpleados.setBounds(309, 140, 219, 33);
		btnVerEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablaEmpleados verEmpleados = new tablaEmpleados();
				verEmpleados.setVisible(true);
			}
		});
		btnVerEmpleados.setFont(new Font("Consolas", Font.BOLD, 14));
		
		JButton btnAdministrarEmpleados = new JButton("Administrar Empleados");
		btnAdministrarEmpleados.setBounds(309, 188, 219, 35);
		btnAdministrarEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				administrarEmpleados frame = new administrarEmpleados();
				frame.setVisible(true);
			}
		});
		btnAdministrarEmpleados.setFont(new Font("Consolas", Font.BOLD, 14));
		
		JButton btnVerTurnos = new JButton("VER TURNOS Y SUELDO");
		btnVerTurnos.setBounds(77, 113, 421, 203);
		btnVerTurnos.setIcon(new ImageIcon(home.class.getResource("/imagenes/verTurnos.png")));
		btnVerTurnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tablaEmpleados tabla = new tablaEmpleados();
				tabla.setVisible(true);
			}
		});
		btnVerTurnos.setFont(new Font("Monospaced", Font.BOLD, 16));
		
		JLabel lblUser = new JLabel("\u00A1Bienvenido, para ver su turno y sueldo pulse el bot\u00F3n y \r\nintroduzca su C\u00F3digo!");
		lblUser.setBounds(-82, 56, 746, 27);
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser.setFont(new Font("Consolas", Font.BOLD, 12));
		
		JLabel lblAdmin = new JLabel("\u00A1Bienvenido al Sistema de Gesti\u00F3n de Empleados y Usuarios!");
		lblAdmin.setBounds(-82, 29, 746, 21);
		lblAdmin.setFont(new Font("Consolas", Font.BOLD, 16));
		lblAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblUsuariosAdmin = new JLabel("Gesti\u00F3n de Usuarios");
		lblUsuariosAdmin.setBounds(77, 113, 276, 16);
		lblUsuariosAdmin.setFont(new Font("Consolas", Font.BOLD, 15));
		
		JLabel lblEmpleadosAdmin = new JLabel("Gesti\u00F3n de Empleados");
		lblEmpleadosAdmin.setBounds(352, 114, 279, 14);
		lblEmpleadosAdmin.setFont(new Font("Consolas", Font.BOLD, 15));
		contentPane.setLayout(null);
		contentPane.add(lblUser);
		contentPane.add(lblAdmin);
		contentPane.add(lblUsuariosAdmin);
		contentPane.add(lblEmpleadosAdmin);
		contentPane.add(btnVerUsuarios);
		contentPane.add(btnAdministrarEmpleados);
		contentPane.add(btnDarAltaUsuarios);
		contentPane.add(btnAdministrarUsuarios);
		contentPane.add(btnVerEmpleados);
		contentPane.add(btnVerTurnos);

		setLocationRelativeTo(null);
		this.mod = mod;

		if (mod.getId_tipo() == 1) {
			btnVerTurnos.setVisible(false);
			lblUser.setVisible(false);
		} else if (mod.getId_tipo() == 2) {
			lblEmpleadosAdmin.setVisible(false);
			lblUsuariosAdmin.setVisible(false);
			lblAdmin.setVisible(false);
			btnDarAltaUsuarios.setVisible(false);
			btnVerUsuarios.setVisible(false);
			btnAdministrarUsuarios.setVisible(false);
			btnVerEmpleados.setVisible(false);
			btnAdministrarEmpleados.setVisible(false);
		}
	}
}
