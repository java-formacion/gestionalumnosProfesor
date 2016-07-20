package com.ipartek.formacion.dbms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ipartek.formacion.dbms.ConexionDB;
import com.ipartek.formacion.dbms.ConexionDBImp;
import com.ipartek.formacion.pojo.Alumno;

public class AlumnoDAOImp implements AlumnoDAO {

	private static final Logger LOG = Logger.getLogger(AlumnoDAOImp.class);
	private ConexionDB myConexion;
	private static AlumnoDAOImp INSTANCE;

	private AlumnoDAOImp() {
		myConexion = ConexionDBImp.getInstance();
	}

	private synchronized static void createInstance() {
		if (INSTANCE == null) {
			INSTANCE = new AlumnoDAOImp();
		}
	}

	public static AlumnoDAOImp getInstance() {
		if (INSTANCE == null) {
			createInstance();
		}
		return INSTANCE;
	}

	// pq va a tener el atributo que es la conexion
	@Override
	public Alumno getById(int codigo) {
		Alumno alumno = null;
		String sql = "SELECT codAlumno, a.nombre as 'nAlumno', apellidos, email,telefono,dni_nie,fNacimiento, codGenero, g.nombre as 'nGenero'"
				+ " FROM alumno a "
				+ "	INNER JOIN genero g ON g.codGenero = a.codGenero"
				+ " WHERE codAlumno =" + codigo;
		// ConexionDB dbConnection = ConexionDBImp.getInstance();
		// myConexion.conectar();
		Connection conexion = myConexion.getConexion();
		try {

			PreparedStatement pSmt = conexion.prepareStatement(sql);
			ResultSet rs = pSmt.executeQuery();
			while (rs.next()) {
				alumno = parseAlumno(rs);
			}

		} catch (SQLException e) {
			LOG.fatal(e.getMessage());
		} finally {
			myConexion.desconectar();
		}

		return alumno;
	}

	private Alumno parseAlumno(ResultSet rs) {
		Alumno alumno = null;
		alumno = new Alumno();
		try {
			alumno.setCodigo(rs.getInt("codAlumno"));
			alumno.setNombre(rs.getString("nAlumnno"));
		} catch (SQLException e) {
			LOG.fatal(e.getMessage());
		}

		return alumno;
	}

	@Override
	public Alumno update(Alumno alumno) {
		Alumno alum = null;
		String sql = "{call updateAlumno(?,?,?,?,?,?,?,?)}";
		// ConexionDB myConexion = ConexionDBImp.getInstance();
		// myConexion.conectar();
		Connection conexion = myConexion.getConexion();
		try {
			CallableStatement cSmt = conexion.prepareCall(sql);
			cSmt.setInt("codigo", alumno.getCodigo());
			cSmt.setString("nombre", alumno.getNombre());
			cSmt.setString("apellidos", alumno.getApellidos());
			cSmt.setString("dni", alumno.getDni());
			cSmt.setDate("fecha", new java.sql.Date(alumno.getfNacimiento()
					.getTime()));
			cSmt.setString("email", alumno.getEmail());
			cSmt.setString("telefono", alumno.getTelefono());
			cSmt.setInt("codigoGenero", alumno.getGenero().getCodigo());
			cSmt.executeUpdate();
			alum = alumno;
		} catch (SQLException e) {
			alum = getById(alumno.getCodigo());
			LOG.fatal(e.getMessage());
		} finally {
			myConexion.desconectar();
		}

		return alum;
	}

	@Override
	public Alumno create(Alumno alumno) {
		String sql = "{insertAlumno(?,?,?,?,?,?,?,?)}";
		Alumno alum = null;
		// ConexionDB myConexion = ConexionDBImp.getInstance();
		// myConexion.conectar();
		Connection conexion = myConexion.getConexion();

		try {
			CallableStatement cSmt = conexion.prepareCall(sql);
			cSmt.registerOutParameter("codigo", Types.INTEGER);
			cSmt.setString("", alumno.getNombre());

			cSmt.executeUpdate();
			alum = alumno;
			alum.setCodigo(cSmt.getInt("codigo"));

		} catch (SQLException e) {
			LOG.fatal(e.getMessage());
		} finally {
			myConexion.desconectar();
		}

		return alum;
	}

	@Override
	public void delete(int codigo) {
		String sql = "{call deleteAlumno(?)}";
		// ConexionDB myConexion = ConexionDBImp.getInstance();
		// myConexion.conectar();
		Connection conexion = myConexion.getConexion();
		try {
			CallableStatement cSmt = conexion.prepareCall(sql);
			cSmt.setInt("codigo", codigo);
			cSmt.executeUpdate();

		} catch (SQLException e) {
			LOG.fatal(e.getMessage());
		} finally {
			myConexion.desconectar();
		}

	}

	@Override
	public List<Alumno> getAll() {
		List<Alumno> alumnos = null;
		String sql = "{call getAllAlumno()}";
		// ConexionDB myConexion = ConexionDBImp.getInstance();
		Connection conection = myConexion.getConexion();
		try {
			Alumno alumno = null;
			CallableStatement cSmt = conection.prepareCall(sql);
			ResultSet rs = cSmt.executeQuery();
			alumnos = new ArrayList<Alumno>();
			while (rs.next()) {
				alumno = parseAlumno(rs);
				alumnos.add(alumno);
			}
		} catch (SQLException e) {
			LOG.fatal(e.getMessage());
		} finally {
			myConexion.desconectar();
		}
		return alumnos;
	}

}
