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
		btnDarAltaUsuarios.setFont(new Font("Consolas", Font.BOLD, 14));
		btnDarAltaUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registroAdmin Registro = new registroAdmin();
				Registro.setVisible(true);
			}
		});
		
		JButton btnVerUsuarios = new JButton("Ver Usuarios");
		btnVerUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablaUsuarios verUsuarios = new tablaUsuarios();
				verUsuarios.setVisible(true);
			}
		});
		btnVerUsuarios.setFont(new Font("Consolas", Font.BOLD, 14));
		
		JButton btnAdministrarUsuarios = new JButton("Administrar Usuarios");
		btnAdministrarUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				administrarUsuarios verUsuarios = new administrarUsuarios();
				verUsuarios.setVisible(true);
			}
		});
		btnAdministrarUsuarios.setFont(new Font("Consolas", Font.BOLD, 14));
		
		JButton btnVerEmpleados = new JButton("Ver Empleados");
		btnVerEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablaEmpleados verEmpleados = new tablaEmpleados();
				verEmpleados.setVisible(true);
			}
		});
		btnVerEmpleados.setFont(new Font("Consolas", Font.BOLD, 14));
		
		JButton btnAdministrarEmpleados = new JButton("Administrar Empleados");
		btnAdministrarEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				administrarEmpleados frame = new administrarEmpleados();
				frame.setVisible(true);
			}
		});
		btnAdministrarEmpleados.setFont(new Font("Consolas", Font.BOLD, 14));
		
		JButton btnVerTurnos = new JButton("VER TURNOS Y SUELDO");
		btnVerTurnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tablaEmpleados tabla = new tablaEmpleados();
				tabla.setVisible(true);
			}
		});
		btnVerTurnos.setFont(new Font("Monospaced", Font.BOLD, 16));
		
		JLabel lblUser = new JLabel("\u00A1Bienvenido, para ver su turno y sueldo pulse el bot\u00F3n y \r\nintroduzca su C\u00F3digo!");
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser.setFont(new Font("Consolas", Font.BOLD, 12));
		
		JLabel lblAdmin = new JLabel("\u00A1Bienvenido al Sistema de Gesti\u00F3n de Empleados y Usuarios!");
		lblAdmin.setFont(new Font("Consolas", Font.BOLD, 16));
		lblAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblUsuariosAdmin = new JLabel("Gesti\u00F3n de Usuarios");
		lblUsuariosAdmin.setFont(new Font("Consolas", Font.BOLD, 15));
		
		JLabel lblEmpleadosAdmin = new JLabel("Gesti\u00F3n de Empleados");
		lblEmpleadosAdmin.setFont(new Font("Consolas", Font.BOLD, 15));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(lblUser, GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblAdmin, GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
							.addGap(10))))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(64)
					.addComponent(lblUsuariosAdmin, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
					.addGap(99)
					.addComponent(lblEmpleadosAdmin, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
					.addGap(43))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(45)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnVerUsuarios, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
							.addGap(63)
							.addComponent(btnAdministrarEmpleados, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnDarAltaUsuarios, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
							.addGap(277))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnAdministrarUsuarios, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
							.addGap(277))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(277)
							.addComponent(btnVerEmpleados, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(115)
							.addComponent(btnVerTurnos, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
							.addGap(139)))
					.addGap(31))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(35)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(lblUser, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblAdmin, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
							.addGap(24)))
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblUsuariosAdmin, GroupLayout.PREFERRED_SIZE, 16, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addComponent(lblEmpleadosAdmin, GroupLayout.PREFERRED_SIZE, 14, Short.MAX_VALUE)
							.addGap(1)))
					.addGap(3)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(27)
							.addComponent(btnVerUsuarios, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(73)
							.addComponent(btnAdministrarEmpleados, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
							.addGap(48))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(73)
							.addComponent(btnDarAltaUsuarios, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(119)
							.addComponent(btnAdministrarUsuarios, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(28)
							.addComponent(btnVerEmpleados, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
							.addGap(95))
						.addComponent(btnVerTurnos, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
					.addGap(94))
		);
		contentPane.setLayout(gl_contentPane);

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
