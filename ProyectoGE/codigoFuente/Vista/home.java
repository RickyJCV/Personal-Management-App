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
import javax.swing.ImageIcon;
import java.awt.Toolkit;

/**
 * Esta vista se usa para mostrar el Home
 * 
 * @author Ricardo Jesús Cabrera Valero
 *
 */

public class home extends JFrame {

	private JPanel contentPane;
	usuarios mod;

	/**
	 * Constructor que recibe el modelo de usuarios para el HOME
	 * 
	 * @param mod
	 */

	public home(usuarios mod) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(home.class.getResource("/imagenes/home.png")));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 593, 426);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		/**
		 * Botón para dar de alta a los usuarios
		 */
		JButton btnDarAltaUsuarios = new JButton("Dar Alta Usuarios");
		btnDarAltaUsuarios.setIcon(new ImageIcon(home.class.getResource("/imagenes/altausuario.png")));
		btnDarAltaUsuarios.setFont(new Font("Consolas", Font.BOLD, 14));
		btnDarAltaUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registroAdmin Registro = new registroAdmin();
				Registro.setVisible(true);
			}
		});
		btnDarAltaUsuarios.setBounds(50, 206, 235, 35);
		contentPane.add(btnDarAltaUsuarios);
		/**
		 * Botón para ver a los usuarios en una tabla
		 */
		JButton btnVerUsuarios = new JButton("Ver Usuarios");
		btnVerUsuarios.setIcon(new ImageIcon(home.class.getResource("/imagenes/ver.png")));
		btnVerUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablaUsuarios verUsuarios = new tablaUsuarios();
				verUsuarios.setVisible(true);
			}
		});
		btnVerUsuarios.setFont(new Font("Consolas", Font.BOLD, 14));
		btnVerUsuarios.setBounds(50, 160, 235, 35);
		contentPane.add(btnVerUsuarios);
		/**
		 * Botón para administrar a los usuarios
		 */
		JButton btnAdministrarUsuarios = new JButton("Administrar Usuarios");
		btnAdministrarUsuarios.setIcon(new ImageIcon(home.class.getResource("/imagenes/administrar.png")));
		btnAdministrarUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				administrarUsuarios verUsuarios = new administrarUsuarios();
				verUsuarios.setVisible(true);
			}
		});
		btnAdministrarUsuarios.setFont(new Font("Consolas", Font.BOLD, 14));
		btnAdministrarUsuarios.setBounds(50, 252, 235, 37);
		contentPane.add(btnAdministrarUsuarios);
		/**
		 * Botón para ver a los empleados
		 */
		JButton btnVerEmpleados = new JButton("Ver Empleados");
		btnVerEmpleados.setIcon(new ImageIcon(home.class.getResource("/imagenes/ver.png")));
		btnVerEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablaEmpleados verEmpleados = new tablaEmpleados();
				verEmpleados.setVisible(true);
			}
		});
		btnVerEmpleados.setFont(new Font("Consolas", Font.BOLD, 14));
		btnVerEmpleados.setBounds(306, 161, 235, 33);
		contentPane.add(btnVerEmpleados);
		/**
		 * Botón para administrar a los empleados
		 */
		JButton btnAdministrarEmpleados = new JButton("Administrar Empleados");
		btnAdministrarEmpleados.setIcon(new ImageIcon(home.class.getResource("/imagenes/administrar.png")));
		btnAdministrarEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				administrarEmpleados frame = new administrarEmpleados();
				frame.setVisible(true);
			}
		});
		btnAdministrarEmpleados.setFont(new Font("Consolas", Font.BOLD, 14));
		btnAdministrarEmpleados.setBounds(306, 206, 235, 35);
		contentPane.add(btnAdministrarEmpleados);
		/**
		 * Botón para ver los turnos y sueldo
		 */
		JButton btnVerTurnos = new JButton("VER TURNOS Y SUELDO");
		btnVerTurnos.setIcon(new ImageIcon(home.class.getResource("/imagenes/verTurnosySueldo.png")));
		btnVerTurnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tablaEmpleados tabla = new tablaEmpleados();
				tabla.setVisible(true);
			}
		});
		btnVerTurnos.setFont(new Font("Monospaced", Font.BOLD, 16));
		btnVerTurnos.setBounds(109, 136, 373, 168);
		contentPane.add(btnVerTurnos);

		JLabel lblUser = new JLabel(
				"\u00A1Bienvenido, para ver su turno y sueldo pulse el bot\u00F3n y \r\nintroduzca su C\u00F3digo!");
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser.setFont(new Font("Consolas", Font.BOLD, 12));
		lblUser.setBounds(10, 58, 557, 29);
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
		// Esto lo usamos para mostrar una ventana u otra dependiendo de quien inicie
		// sesión
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