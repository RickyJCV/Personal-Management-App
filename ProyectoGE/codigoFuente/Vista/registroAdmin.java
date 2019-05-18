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
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Esta vista se usa para el registro que ve el usuario administrador
 * 
 * @author Ricardo Jesús Cabrera Valero
 *
 */

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(registroAdmin.class.getResource("/imagenes/admin.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(86, 24, 65, 14);
		contentPane.add(lblNewLabel);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(161, 22, 138, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblContrasea.setBounds(67, 55, 84, 14);
		contentPane.add(lblContrasea);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(161, 53, 138, 20);
		contentPane.add(txtPassword);

		JLabel lblConfirmarContrasea = new JLabel("Confirmar  Contrase\u00F1a:");
		lblConfirmarContrasea.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblConfirmarContrasea.setBounds(10, 86, 140, 14);
		contentPane.add(lblConfirmarContrasea);

		txtConfirmaPassword = new JPasswordField();
		txtConfirmaPassword.setBounds(161, 84, 138, 20);
		contentPane.add(txtConfirmaPassword);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNombre.setBounds(86, 117, 76, 14);
		contentPane.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(161, 115, 138, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCorreo.setBounds(86, 148, 65, 14);
		contentPane.add(lblCorreo);

		txtCorreo = new JTextField();
		txtCorreo.setBounds(161, 146, 138, 20);
		contentPane.add(txtCorreo);
		txtCorreo.setColumns(10);

		/**
		 * Creamos el boton de dar de alta a los usuarios
		 */
		JButton btnRegistrar = new JButton("Dar de alta");
		btnRegistrar.setIcon(new ImageIcon(registroAdmin.class.getResource("/imagenes/guardar.png")));
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
										JOptionPane.showMessageDialog(null, "Error al guardar el registro");
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
		btnRegistrar.setBounds(161, 218, 138, 33);
		contentPane.add(btnRegistrar);

		txtIdTipo = new JTextField();
		txtIdTipo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (c < '0' || c > '9')
					e.consume();
			}
		});
		txtIdTipo.setBounds(161, 179, 138, 20);
		contentPane.add(txtIdTipo);
		txtIdTipo.setColumns(10);

		JLabel lblTipoDeUsuario = new JLabel("Tipo de Usuario:");
		lblTipoDeUsuario.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTipoDeUsuario.setBounds(33, 181, 118, 14);
		contentPane.add(lblTipoDeUsuario);

		JLabel lbladministrador = new JLabel("1) Administrador");
		lbladministrador.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lbladministrador.setBounds(309, 179, 84, 14);
		contentPane.add(lbladministrador);

		JLabel lblNewLabel_1 = new JLabel("2) Usuario Normal");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblNewLabel_1.setBounds(309, 193, 97, 14);
		contentPane.add(lblNewLabel_1);
		setLocationRelativeTo(null);
	}
}