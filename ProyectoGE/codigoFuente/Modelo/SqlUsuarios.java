package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlUsuarios extends Conexion{
	
	public boolean registrar(usuarios usr) {
		
		PreparedStatement ps = null;
		Connection con = getConexion();
		
		String sql = "INSERT INTO usuarios (usuario,password,nombre,correo,id_tipo) VALUES(?,?,?,?,?)";
		
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, usr.getUsuario());
			ps.setString(2, usr.getPassword());
			ps.setString(3, usr.getNombre());
			ps.setString(4, usr.getCorreo());
			ps.setInt(5, usr.getId_tipo());
			ps.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
