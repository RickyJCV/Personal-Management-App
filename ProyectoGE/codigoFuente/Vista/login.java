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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;

public class login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtPassword;

	/**
	 * Creamos la ventana de login
	 */
	public login() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 529, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconoVentanas.png")).getImage());

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));

		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 14));

		txtPassword = new JPasswordField();

		JButton btnIngresar = new JButton("Iniciar Sesi\u00F3n");
		btnIngresar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sqlUsuarios modSql = new sqlUsuarios();
				usuarios mod = new usuarios();

				Date date = new Date();
				DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				String pass = new String(txtPassword.getPassword());

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
		
		JLabel lblNewLabel = new JLabel("Bienvenido al Sistema de Gesti\u00F3n de Empleados y Usuarios");
		lblNewLabel.setFont(new Font("DejaVu Sans", Font.BOLD, 15));
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registro frame = new registro();
				frame.setVisible(true);
			}
		});
		btnRegistrarse.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblParaRegistrarsePulse = new JLabel("Para registrarse pulse el siguiente bot\u00F3n:");
		lblParaRegistrarsePulse.setFont(new Font("Tahoma", Font.ITALIC, 12));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(121)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(92)
							.addComponent(txtUsuario, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblUsuario, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
							.addGap(118)))
					.addGap(161))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(100)
					.addComponent(lblContrasea, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(txtPassword, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
					.addGap(161))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(193)
					.addComponent(btnIngresar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(191))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addComponent(lblParaRegistrarsePulse, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
					.addGap(265))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(48)
					.addComponent(btnRegistrarse, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(346))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(26)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
					.addGap(26))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
					.addGap(17)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(txtUsuario)
						.addComponent(lblUsuario))
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblContrasea)
						.addComponent(txtPassword))
					.addGap(26)
					.addComponent(btnIngresar, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
					.addGap(38)
					.addComponent(lblParaRegistrarsePulse, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
					.addGap(11)
					.addComponent(btnRegistrarse, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(18))
		);
		contentPane.setLayout(gl_contentPane);
		setLocationRelativeTo(null);
	}
}
