package com.ipartek.formacion.dbms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.ipartek.formacion.dbms.ConexionDB;
import com.ipartek.formacion.dbms.ConexionDBImp;
import com.ipartek.formacion.pojo.Modulo;

public class ModuloDAOImp implements ModuloDAO {
	private static final Logger LOG = Logger.getLogger(ModuloDAOImp.class);
	private ConexionDB myConexion;
	private static ModuloDAOImp INSTANCE = null;

	private ModuloDAOImp() {
		myConexion = ConexionDBImp.getInstance();
	}

	public static ModuloDAOImp getInstance() {
		if (INSTANCE == null) {
			createInstance();
		}
		return INSTANCE;
	}

	private synchronized static void createInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ModuloDAOImp();
		}

	}

	@Override
	public Modulo getById(int codigo) {
		String sql = "SELECT codModulo, nombre, uFormativa, duracion, g.nombre as 'nGenero'"
				+ " FROM modulo  WHERE codModulo=" + codigo;
		// ConexionDB dbConnection = ConexionDBImp.getInstance();
		// myConexion.conectar();
		Connection conexion = myConexion.getConexion();
		Modulo modulo = null;
		try {
			PreparedStatement pStm = conexion.prepareStatement(sql);
			ResultSet rs = pStm.executeQuery();
			while (rs.next()) {
				modulo = parseModulo(rs);
			}
		} catch (SQLException e) {
			LOG.fatal(e.getMessage());
		} finally {
			myConexion.desconectar();
		}
		return modulo;
	}

	private Modulo parseModulo(ResultSet rs) {
		Modulo modulo = null;
		modulo = new Modulo();
		try {
			modulo.setCodigo(rs.getInt("codModulo"));
			modulo.setNombre(rs.getString("nombre"));
			modulo.setReferencia(rs.getString("uFormativa"));
			// modulo.setDuracion(rs.getInt("duracion"));
		} catch (SQLException e) {
			LOG.fatal(e.getMessage());
		}

		return modulo;
	}

	@Override
	public Modulo update(Modulo modulo) {
		// TODO terminar!!! y hacer los procedures en xampp
		Modulo modu = null;
		String sql = "{call updateModulo(?,?,?,?,?,?,?,?)}";
		// ConexionDB myConexion = ConexionDBImp.getInstance();
		// myConexion.conectar();

		/*
		 * try { CallableStatement cSmt =
		 * myConexion.getConexion().prepareCall(sql); cSmt.setInt("codigo",
		 * alumno.getCodigo()); cSmt.setString("nombre", alumno.getNombre());
		 * cSmt.setString("apellidos", alumno.getApellidos());
		 * cSmt.setString("dni_nie", alumno.getDni());
		 * cSmt.setDate("fNacimiento", new
		 * java.sql.Date(alumno.getfNacimiento().getTime()));
		 * cSmt.setString("email", alumno.getEmail());
		 * cSmt.setString("telefono", alumno.getTfno());
		 * cSmt.setInt("codGenero", alumno.getGenero().getCodigo());
		 * cSmt.executeUpdate(); alum = alumno; } catch (SQLException e) { alum
		 * = getById(alumno.getCodigo()); LOG.fatal(e.getMessage()); } finally {
		 * myConexion.desconectar(); }
		 */

		return modu;
	}

	@Override
	public Modulo create(Modulo modulo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int codigo) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Modulo> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
