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
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class registroAdmin extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtPassword;
	private JPasswordField txtConfirmaPassword;
	private JTextField txtNombre;
	private JTextField txtCorreo;
	private JTextField txtIdTipo;

	/**
	 * Se crea el frame de registro de usuarios
	 */
	public registroAdmin() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));

		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 12));

		txtPassword = new JPasswordField();

		JLabel lblConfirmarContrasea = new JLabel("Confirmar  Contrase\u00F1a:");
		lblConfirmarContrasea.setFont(new Font("Tahoma", Font.BOLD, 11));

		txtConfirmaPassword = new JPasswordField();

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 12));

		txtNombre = new JTextField();
		txtNombre.setColumns(10);

		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setFont(new Font("Tahoma", Font.BOLD, 12));

		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);

		/**
		 * Creamos el boton de dar de alta a los usuarios
		 */
		JButton btnRegistrar = new JButton("Dar de alta");
		btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sqlUsuarios modSql = new sqlUsuarios();
				usuarios mod = new usuarios();

				String pass = new String(txtPassword.getPassword());
				String passCon = new String(txtConfirmaPassword.getPassword());

				/**
				 * Validamos que ninguno de los campos esten vacios
				 */
				if (txtUsuario.getText().equals("") || pass.equals("") || passCon.equals("")
						|| txtNombre.getText().equals("") || txtCorreo.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Hay campos vacíos, debe rellenar todos los campos");
				} else {
					/**
					 * Comprobamos que las claves sean iguales
					 */
					if (pass.equals(passCon)) {
						/**
						 * Comprobamos si el usuario existe o no
						 */
						if (modSql.existeUsuario(txtUsuario.getText()) == 0) {
							/**
							 * Comprobamos si el correo es valido
							 */

							if (modSql.esEmail(txtCorreo.getText())) {

								if (Integer.parseInt(txtIdTipo.getText()) == 1
										|| Integer.parseInt(txtIdTipo.getText()) == 2) {

									String nuevoPass = hash.sha1(pass);

									mod.setUsuario(txtUsuario.getText());
									mod.setPassword(nuevoPass);
									mod.setNombre(txtNombre.getText());
									mod.setCorreo(txtCorreo.getText());
									/**
									 * Establecemos que siempre que se registre sea de tipo usuario y no de tipo
									 * administrador por defecto
									 */
									mod.setId_tipo(Integer.parseInt(txtIdTipo.getText()));
									/**
									 * Si todo se cumple guardamos los registros
									 */
									if (modSql.registrar(mod)) {
										JOptionPane.showMessageDialog(null, "Registro Guardado");
										limpiar();
									} else {
										JOptionPane.showMessageDialog(null, "Error al guardar el registro","Error",JOptionPane.ERROR_MESSAGE);
									}
								} else {
									JOptionPane.showMessageDialog(null,
											"El tipo de usuario debe ser 1)Administrador o 2)Usuario Normal. ¡ Porfavor introduce un tipo de usuario correcto !");
								}
							} else {
								JOptionPane.showMessageDialog(null, "El correo electrónico no es válido");
							}
						} else {
							JOptionPane.showMessageDialog(null, "El usuario ya existe");
						}

					} else {
						JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
					}
				}
			}

			/**
			 * Metodo que vacía los campos de texto
			 */
			public void limpiar() {
				txtUsuario.setText("");
				txtPassword.setText("");
				txtConfirmaPassword.setText("");
				txtNombre.setText("");
				txtCorreo.setText("");
				txtIdTipo.setText("");
			}
		});

		txtIdTipo = new JTextField();
		txtIdTipo.setColumns(10);

		JLabel lblTipoDeUsuario = new JLabel("Tipo de Usuario:");
		lblTipoDeUsuario.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel lbladministrador = new JLabel("1) Administrador");
		lbladministrador.setFont(new Font("Tahoma", Font.ITALIC, 10));

		JLabel lblNewLabel_1 = new JLabel("2) Usuario Normal");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 10));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(81)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(txtUsuario, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
					.addGap(130))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(62)
					.addComponent(lblContrasea, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(txtPassword, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
					.addGap(130))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addComponent(lblConfirmarContrasea, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
					.addGap(11)
					.addComponent(txtConfirmaPassword, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
					.addGap(130))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(81)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(75)
							.addComponent(txtNombre, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNombre, GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
							.addGap(137)))
					.addGap(130))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(81)
					.addComponent(lblCorreo, GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(txtCorreo, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
					.addGap(130))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(28)
					.addComponent(lblTipoDeUsuario, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(txtIdTipo, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lbladministrador, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
							.addGap(13))
						.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
					.addGap(23))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(156)
					.addComponent(btnRegistrar, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
					.addGap(150))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(17)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 14, Short.MAX_VALUE)
							.addGap(4))
						.addComponent(txtUsuario))
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(lblContrasea, GroupLayout.PREFERRED_SIZE, 14, Short.MAX_VALUE)
							.addGap(4))
						.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(lblConfirmarContrasea, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(4))
						.addComponent(txtConfirmaPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 14, Short.MAX_VALUE)
							.addGap(4)))
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(lblCorreo, GroupLayout.PREFERRED_SIZE, 14, Short.MAX_VALUE)
							.addGap(4))
						.addComponent(txtCorreo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(lblTipoDeUsuario, GroupLayout.PREFERRED_SIZE, 14, Short.MAX_VALUE)
							.addGap(12))
						.addComponent(txtIdTipo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lbladministrador, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)))
					.addGap(21)
					.addComponent(btnRegistrar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(6))
		);
		contentPane.setLayout(gl_contentPane);
		setLocationRelativeTo(null);
	}
}
