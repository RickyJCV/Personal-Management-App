package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class sqlUsuarios extends conexion {

	public boolean registrar(usuarios usr) {

		/**
		 * Creamos el insert sql para poder registrar a los usuarios
		 */
		PreparedStatement ps = null;
		Connection con = getConexion();

		String sql = "INSERT INTO usuarios (usuario,password,nombre,correo,id_tipo) VALUES(?,?,?,?,?)";

		try {
			ps = con.prepareStatement(sql);
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

	public boolean login(usuarios usr) {

		/**
		 * Creamos el select sql para poder comprobar si existe el usuario Devuelve 1 si
		 * el usuario existe Devuelve 0 si el usuario no existe
		 */
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = getConexion();

		String sql = "SELECT u.id,u.usuario, u.password, u.nombre, u.id_tipo, t.nombre FROM usuarios AS u INNER JOIN tipo_usuario AS t ON u.id_tipo=t.id WHERE usuario = ?";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, usr.getUsuario());
			rs = ps.executeQuery();

			if (rs.next()) {
				if (usr.getPassword().equals(rs.getString(3))) {
					
					String sqlUpdate = "UPDATE usuarios SET last_session = ? WHERE id=?";
					ps=con.prepareStatement(sqlUpdate);
					ps.setString(1, usr.getLast_session());
					ps.setInt(2, rs.getInt(1));
					ps.execute();
					
					usr.setId(rs.getInt(1));
					usr.setNombre(rs.getString(4));
					usr.setId_tipo(rs.getInt(5));
					usr.setNombre_tipo(rs.getString(6));
					
					return true;
				} else {
					return false;
				}
			}

			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public int existeUsuario(String usuario) {

		/**
		 * Creamos el select sql para poder comprobar si existe el usuario Devuelve 1 si
		 * el usuario existe Devuelve 0 si el usuario no existe
		 */
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = getConexion();

		String sql = "SELECT count(id) FROM usuarios WHERE usuario = ?";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, usuario);
			rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			}

			return 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 1;
		}
	}

	public boolean esEmail(String correo) {
		// Patrón para validar el email
		Pattern pattern = Pattern.compile(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

		Matcher mather = pattern.matcher(correo);

		return mather.find();
	}
}