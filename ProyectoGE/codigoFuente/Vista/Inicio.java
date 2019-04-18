package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Inicio extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creamos el frame de inicio
	 */
	public Inicio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/**
		 * Creamos el boton de registrar el cual inicia el formulario de registro
		 */
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Registro Registro = new Registro();
				Registro.setVisible(true);
				
			}
		});
		btnRegistrar.setBounds(86, 109, 89, 23);
		contentPane.add(btnRegistrar);
		
		/**
		 * Creamos el boton de iniciar sesion el cual inicia el formulario de inicio de sesion
		 */
		JButton btnIngresar = new JButton("Iniciar Sesi\u00F3n");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login Login = new Login();
				Login.setVisible(true);
			}
		});
		btnIngresar.setBounds(254, 109, 112, 23);
		contentPane.add(btnIngresar);
		setLocationRelativeTo(null);
	}

}
