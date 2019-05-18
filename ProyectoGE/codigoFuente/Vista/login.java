package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.sqlUsuarios;
import Modelo.hash;
import Modelo.usuarios;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

/**
 * Esta vista es el login para iniciar sesión
 * 
 * @author Ricardo Jesús Cabrera Valero
 *
 */

public class login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtPassword;

	/**
	 * Frame del login
	 */
	public login() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 529, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setIconImage(new ImageIcon(getClass().getResource("/imagenes/usuarionormal.png")).getImage());
		contentPane.setLayout(null);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsuario.setBounds(126, 99, 103, 17);
		contentPane.add(lblUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(218, 99, 129, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblContrasea.setBounds(105, 147, 103, 17);
		contentPane.add(lblContrasea);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(218, 147, 129, 20);
		contentPane.add(txtPassword);
		/**
		 * Botón para iniciar sesión
		 */
		JButton btnIngresar = new JButton("Iniciar Sesi\u00F3n");
		btnIngresar.setIcon(new ImageIcon(login.class.getResource("/imagenes/login.png")));
		btnIngresar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sqlUsuarios modSql = new sqlUsuarios();
				usuarios mod = new usuarios();

				Date date = new Date();
				DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				String pass = new String(txtPassword.getPassword());

				// Comprobamos la clave y el usuario
				if (!txtUsuario.getText().equals("") && !pass.equals("")) {
					String nuevoPass = hash.sha1(pass);

					mod.setUsuario(txtUsuario.getText());
					mod.setPassword(nuevoPass);
					mod.setLast_session(fechaHora.format(date).toString());

					/**
					 * Hasta aquí ya hemos comprobado el Login y decimos que si es correcto que haga
					 * esto
					 */
					if (modSql.login(mod)) {
						home Home = new home(mod);
						Home.setVisible(true);
						dispose();

					} else {
						JOptionPane.showMessageDialog(null, "Datos incorrectos");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Debe ingresar sus datos");
				}
			}
		});
		btnIngresar.setBounds(198, 193, 149, 25);
		contentPane.add(btnIngresar);

		JLabel lblNewLabel = new JLabel("Bienvenido al Sistema de Gesti\u00F3n de Empleados y Usuarios");
		lblNewLabel.setFont(new Font("Gill Sans MT", Font.BOLD, 15));
		lblNewLabel.setBounds(44, 11, 432, 75);
		contentPane.add(lblNewLabel);
		/**
		 * Botón para resgitrarse en la BBDD
		 */
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setIcon(new ImageIcon(login.class.getResource("/imagenes/registro.png")));
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registro frame = new registro();
				frame.setVisible(true);
			}
		});
		btnRegistrarse.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRegistrarse.setBounds(53, 287, 143, 23);
		contentPane.add(btnRegistrarse);

		JLabel lblParaRegistrarsePulse = new JLabel("Para registrarse pulse el siguiente bot\u00F3n:");
		lblParaRegistrarsePulse.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblParaRegistrarsePulse.setBounds(10, 256, 233, 20);
		contentPane.add(lblParaRegistrarsePulse);
		/**
		 * Botón para restaurar la clave
		 */
		JButton btnNewButton = new JButton("Restaurar Clave");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contraseñaOlvidada frame = new contraseñaOlvidada();
				frame.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setIcon(new ImageIcon(login.class.getResource("/imagenes/olvidarPass.png")));
		btnNewButton.setBounds(298, 287, 178, 23);
		contentPane.add(btnNewButton);

		JLabel lblSiHaOlvidado = new JLabel("Si ha olvidado su contrase\u00F1a");
		lblSiHaOlvidado.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblSiHaOlvidado.setBounds(311, 256, 165, 14);
		contentPane.add(lblSiHaOlvidado);

		JLabel lblPulseElSiguiente = new JLabel("pulse el siguiente bot\u00F3n:");
		lblPulseElSiguiente.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblPulseElSiguiente.setBounds(321, 270, 167, 14);
		contentPane.add(lblPulseElSiguiente);
		setLocationRelativeTo(null);
	}
}
