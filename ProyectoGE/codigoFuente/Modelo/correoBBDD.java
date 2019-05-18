package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Esta clase realiza una select a la BBDD para obtener los correos
 * 
 * @author Ricardo Jesús Cabrera Valero
 *
 */

public class correoBBDD {
	/**
	 * Usamos esta clase para obtener los correos de la BBDD
	 * 
	 * @return Los correos uno a uno
	 * @throws SQLException
	 */
	public static String[] Correo() throws SQLException {
		// Campos de la clase
		int i = 0;
		int a = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conexion conn = new conexion();
		Connection con = conn.getConexion();
		/**
		 * Ejecutamos la sql para recibir los datos
		 */
		String sql = "SELECT correo FROM usuarios";
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();

		while (rs.next()) {
			a++;
		}
		String[] solucion = new String[a];
		rs.beforeFirst();
		while (rs.next()) {

			solucion[i] = rs.getString("correo");
			i++;
		}
		return solucion;

	}
}
