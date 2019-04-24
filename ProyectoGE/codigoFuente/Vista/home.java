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
		contentPane.setLayout(null);
		
		JButton btnDarAltaUsuarios = new JButton("Dar Alta Usuarios");
		btnDarAltaUsuarios.setFont(new Font("Consolas", Font.BOLD, 14));
		btnDarAltaUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registro Registro = new registro();
				Registro.setVisible(true);
			}
		});
		btnDarAltaUsuarios.setBounds(50, 206, 214, 35);
		contentPane.add(btnDarAltaUsuarios);
		
		JButton btnVerUsuarios = new JButton("Ver Usuarios");
		btnVerUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablaUsuarios verUsuarios = new tablaUsuarios();
				verUsuarios.setVisible(true);
			}
		});
		btnVerUsuarios.setFont(new Font("Consolas", Font.BOLD, 14));
		btnVerUsuarios.setBounds(50, 160, 214, 35);
		contentPane.add(btnVerUsuarios);
		
		JButton btnAdministrarUsuarios = new JButton("Administrar Usuarios");
		btnAdministrarUsuarios.setFont(new Font("Consolas", Font.BOLD, 14));
		btnAdministrarUsuarios.setBounds(50, 252, 214, 37);
		contentPane.add(btnAdministrarUsuarios);
		
		JButton btnVerEmpleados = new JButton("Ver Empleados");
		btnVerEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablaEmpleados verEmpleados = new tablaEmpleados();
				verEmpleados.setVisible(true);
			}
		});
		btnVerEmpleados.setFont(new Font("Consolas", Font.BOLD, 14));
		btnVerEmpleados.setBounds(327, 161, 214, 33);
		contentPane.add(btnVerEmpleados);
		
		JButton btnAdministrarEmpleados = new JButton("Administrar Empleados");
		btnAdministrarEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				administrarEmpleados frame = new administrarEmpleados();
				frame.setVisible(true);
			}
		});
		btnAdministrarEmpleados.setFont(new Font("Consolas", Font.BOLD, 14));
		btnAdministrarEmpleados.setBounds(327, 206, 214, 35);
		contentPane.add(btnAdministrarEmpleados);
		
		JButton btnVerTurnos = new JButton("VER TURNOS Y SUELDO");
		btnVerTurnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tablaEmpleados tabla = new tablaEmpleados();
				tabla.setVisible(true);
			}
		});
		btnVerTurnos.setFont(new Font("Monospaced", Font.BOLD, 16));
		btnVerTurnos.setBounds(165, 133, 237, 140);
		contentPane.add(btnVerTurnos);
		
		JLabel lblUser = new JLabel("\u00A1Bienvenido, para ver su turno y sueldo pulse el bot\u00F3n y \r\nintroduzca su C\u00F3digo!");
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser.setFont(new Font("Consolas", Font.BOLD, 12));
		lblUser.setBounds(20, 58, 557, 29);
		contentPane.add(lblUser);
		
		JLabel lblAdmin = new JLabel("\u00A1Bienvenido al Sistema de Gesti\u00F3n de Empleados y Usuarios!");
		lblAdmin.setFont(new Font("Consolas", Font.BOLD, 16));
		lblAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdmin.setBounds(10, 40, 557, 23);
		contentPane.add(lblAdmin);
		
		JLabel lblUsuariosAdmin = new JLabel("Gesti\u00F3n de Usuarios");
		lblUsuariosAdmin.setFont(new Font("Consolas", Font.BOLD, 15));
		lblUsuariosAdmin.setBounds(69, 114, 178, 16);
		contentPane.add(lblUsuariosAdmin);
		
		JLabel lblEmpleadosAdmin = new JLabel("Gesti\u00F3n de Empleados");
		lblEmpleadosAdmin.setFont(new Font("Consolas", Font.BOLD, 15));
		lblEmpleadosAdmin.setBounds(346, 115, 183, 14);
		contentPane.add(lblEmpleadosAdmin);

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
