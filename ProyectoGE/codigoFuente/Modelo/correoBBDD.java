package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class correoBBDD {
	public static String[] Correo() throws SQLException {
		int i=0;
		int a=0;
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
             while(rs.next()) {
            	 
             solucion[i]=rs.getString("correo");
             i++;
             }
            return solucion;
          
	}
}
