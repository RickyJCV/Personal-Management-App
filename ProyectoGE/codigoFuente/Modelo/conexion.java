package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * Esta clase es la encargada de realizar la conexión con la Base de Datos
 * 
 * @author: Ricardo Jesús Cabrera Valero
 * 
 */

public class conexion {

	// Campos de la clase
	private final static String base = "empleados";
	private final static String user = "root";
	private final static String password = "manolo";
	private final static String timeZone = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private final static String url = "jdbc:mysql://localhost:3306/" + base + timeZone;
	private static Connection con = null;
	static Statement consulta;

	/**
	 * 
	 * Método que establece la conexión con la Base de Datos
	 * 
	 * @return La conexión establecida
	 * 
	 */
	public static Connection getConexion() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

}
