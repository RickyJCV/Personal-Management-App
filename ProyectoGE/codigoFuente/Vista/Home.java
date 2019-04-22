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

public class Home extends JFrame {

	private JPanel contentPane;
	usuarios mod;

	/**
	 * Constructor que recibe el modelo de usuarios para el HOME
	 */
	public Home(usuarios mod) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnDarAltaUsuarios = new JButton("Dar Alta Usuarios");
		btnDarAltaUsuarios.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDarAltaUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registro Registro = new Registro();
				Registro.setVisible(true);
			}
		});
		btnDarAltaUsuarios.setBounds(35, 86, 137, 23);
		contentPane.add(btnDarAltaUsuarios);
		
		JButton btnVerUsuarios = new JButton("Ver usuarios");
		btnVerUsuarios.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnVerUsuarios.setBounds(35, 120, 137, 23);
		contentPane.add(btnVerUsuarios);
		
		JButton btnDarBajaUsuarios = new JButton("Dar Baja Usuarios");
		btnDarBajaUsuarios.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDarBajaUsuarios.setBounds(35, 151, 137, 23);
		contentPane.add(btnDarBajaUsuarios);
		
		JButton btnDarAltaEmpleados = new JButton("Dar Alta Empleados");
		btnDarAltaEmpleados.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDarAltaEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDarAltaEmpleados.setBounds(262, 86, 127, 23);
		contentPane.add(btnDarAltaEmpleados);
		
		JButton btnVerEmpleados = new JButton("Ver Empleados");
		btnVerEmpleados.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnVerEmpleados.setBounds(262, 120, 127, 23);
		contentPane.add(btnVerEmpleados);
		
		JButton btnDarBajaEmpleados = new JButton("Dar Baja Empleados");
		btnDarBajaEmpleados.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDarBajaEmpleados.setBounds(262, 151, 127, 23);
		contentPane.add(btnDarBajaEmpleados);
		
		JButton btnVerTurnos = new JButton("VER TURNOS Y SUELDO");
		btnVerTurnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tablaEmpleados tabla = new tablaEmpleados();
				tabla.setVisible(true);
			}
		});
		btnVerTurnos.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnVerTurnos.setBounds(92, 60, 237, 140);
		contentPane.add(btnVerTurnos);

		setLocationRelativeTo(null);
		this.mod = mod;

		if (mod.getId_tipo() == 1) {
			btnVerTurnos.setVisible(false);
		} else if (mod.getId_tipo() == 2) {
			btnDarAltaUsuarios.setVisible(false);
			btnVerUsuarios.setVisible(false);
			btnDarBajaUsuarios.setVisible(false);
			btnDarAltaEmpleados.setVisible(false);
			btnVerEmpleados.setVisible(false);
			btnDarBajaEmpleados.setVisible(false);
		}
	}
}
