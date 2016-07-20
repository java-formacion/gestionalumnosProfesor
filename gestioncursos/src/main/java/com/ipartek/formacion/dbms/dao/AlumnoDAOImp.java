package com.ipartek.formacion.dbms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ipartek.formacion.dbms.ConexionDB;
import com.ipartek.formacion.dbms.ConexionDBImp;
import com.ipartek.formacion.pojo.Alumno;
import com.ipartek.formacion.pojo.exception.CandidatoException;
import com.ipartek.formacion.service.Util;

/**
 *
 * @author Curso
 *
 */
public class AlumnoDAOImp implements AlumnoDAO {
	private final static Logger LOG = Logger.getLogger(AlumnoDAOImp.class);
	private static AlumnoDAOImp INSTANCE = null;
	private static ConexionDB myConexion;
	private Connection conexion;

	/**
	 *
	 */
	private AlumnoDAOImp() {
		myConexion = ConexionDBImp.getInstance();
	}

	/**
	 *
	 * @return INSTANCE
	 */
	public static AlumnoDAOImp getInstance() {
		if (INSTANCE == null) {
			createInstance();
		}
		return INSTANCE;
	}

	/**
	 *
	 */
	private synchronized static void createInstance() {
		if (INSTANCE == null) {
			INSTANCE = new AlumnoDAOImp();
		}
	}

	/**
	 * @Override
	 * @return nada
	 * @throws CloneNotSupportedException
	 *             no se puede cñlonar
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	/**
	 * @Override
	 * @param codigo
	 *            codigo alumno
	 * @return alumno
	 */
	@Override
	public Alumno getById(int codigo) {
		Alumno alumno = null;
		String sql = "{call getAlumnoById(?)}";
		try {
			CallableStatement cSmt = myConexion.getConexion().prepareCall(sql);
			cSmt.setInt("codigo", codigo);
			ResultSet rs = cSmt.executeQuery();
			while (rs.next()) {
				alumno = parseAlumno(rs);
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage());
		} finally {
			myConexion.desconectar();
		}

		return alumno;
	}

	/**
	 * @param rs
	 *            ResultSet
	 * @return alumno
	 * @throws SQLException
	 *             excepcion sql
	 */
	private Alumno parseAlumno(ResultSet rs) {
		Alumno alumno = null;
		alumno = new Alumno();
		try {
			alumno.setCodigo(rs.getInt("codAlumno"));
			alumno.setNombre(rs.getString("nAlumno"));
			alumno.setApellidos(rs.getString("apellidos"));
			alumno.setDni(rs.getString("dni_nie"));
			alumno.setEmail(rs.getString("email"));
			alumno.setTelefono(rs.getString("telefono"));
			alumno.setfNacimiento(new java.util.Date(rs.getDate("fNacimiento")
					.getTime()));
			alumno.setGenero(Util.parseGenero(rs.getString("a.codGenero")));
		} catch (SQLException e) {
			LOG.error(e.getMessage());
		} 
		return alumno;
	}

	/**
	 * @Override
	 * @param alumno
	 *            Alumno
	 * @return alumno
	 */
	@Override
	public Alumno update(Alumno alumno) {
		Alumno alum = null;
		String sql = "{call updateAlumno(?,?,?,?,?,?,?,?)}";
		LOG.trace(alumno.toString());
		try {
			CallableStatement cSmt = myConexion.getConexion().prepareCall(sql);
			cSmt.setInt("codigo", alumno.getCodigo());
			cSmt.setString("nombre", alumno.getNombre());
			cSmt.setString("apellidos", alumno.getApellidos());
			cSmt.setString("dni", alumno.getDni());
			cSmt.setDate("fecha", new Date(alumno.getfNacimiento().getTime()));
			cSmt.setString("email", alumno.getEmail());
			cSmt.setString("telefono", alumno.getTelefono());
			cSmt.setInt("codGenero", alumno.getGenero().getCodigo());
			cSmt.executeUpdate();
			alum = alumno;
		} catch (SQLException e) {
			alum = getById(alumno.getCodigo());
			LOG.fatal(e.getMessage() + " -- Error al actualizar");
		} finally {
			myConexion.desconectar();
		}
		return alum;
	}

	/**
	 * @Override
	 * @param alumno
	 *            Alumno
	 * @return alumno
	 */
	@Override
	public Alumno create(Alumno alumno) {
		Alumno alum = null;
		String sql = "{call insertAlumno(?,?,?,?,?,?,?,?)}";

		try {
			CallableStatement cSmt = myConexion.getConexion().prepareCall(sql);
			cSmt.setString("nombre", alumno.getNombre());
			cSmt.setString("apellidos", alumno.getApellidos());
			cSmt.setString("dni", alumno.getDni());
			cSmt.setDate("fecha", new Date(alumno.getfNacimiento().getTime()));
			cSmt.setString("email", alumno.getEmail());
			cSmt.setString("telefono", alumno.getTelefono());
			cSmt.setInt("codGenero", alumno.getGenero().getCodigo());
			cSmt.executeUpdate();
			alum = alumno;
			alum.setCodigo(cSmt.getInt("codAlumno"));
		} catch (SQLException e) {
			LOG.fatal(e.getMessage() + " -- Error al insertar alumno");
		} finally {
			myConexion.desconectar();
		}
		return alum;
	}

	/**
	 * @Override
	 * @param codigo
	 *            int
	 */
	@Override
	public void delete(int codigo) {
		String sql = "{call deleteAlumno(?)}";
		conexion = myConexion.getConexion();
		try {
			CallableStatement cSmt = conexion.prepareCall(sql);
			cSmt.setInt("codigo", codigo);

			cSmt.executeUpdate();

		} catch (SQLException e) {
			LOG.fatal(e.getMessage() + " -- Error al borrar");
		} finally {
			myConexion.desconectar();
		}

	}

	/**
	 * @Override
	 * @return lista de alumnos
	 */
	@Override
	public List<Alumno> getAll() {
		List<Alumno> alumnos = null;
		String sql = "{call getAllAlumno()}";
		conexion = myConexion.getConexion();
		try {
			Alumno alumno = null;
			CallableStatement cSmt = conexion.prepareCall(sql);
			ResultSet rs = cSmt.executeQuery();
			alumnos = new ArrayList<Alumno>();
			while (rs.next()) {
				alumno = parseAlumno(rs);
				alumnos.add(alumno);
			}

		} catch (SQLException e) {
			LOG.error(e.getMessage());
		} finally {
			myConexion.desconectar();
		}
		return alumnos;
	}
}