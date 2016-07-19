package com.ipartek.formacion.dbms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ipartek.formacion.dbms.ConexionDB;
import com.ipartek.formacion.dbms.ConexionDBImp;
import com.ipartek.formacion.pojo.Alumno;
import com.ipartek.formacion.pojo.excepciones.CandidatoException;

public class AlumnoDAOImp implements AlumnoDAO {

	// singleton porque va a tener un atributo de conexion a bbdd
	private static final Logger LOG = Logger.getLogger(AlumnoDAOImp.class);
	private ConexionDB myconexion;
	private static AlumnoDAOImp INSTANCE;

	private AlumnoDAOImp() {
		myconexion = ConexionDBImp.getInstance();
	}

	public static AlumnoDAOImp getInstance() {
		if (INSTANCE == null) {
			createInstance();
		}
		return INSTANCE;
	}

	private synchronized static void createInstance() {
		if (INSTANCE == null) {
			INSTANCE = new AlumnoDAOImp();
		}
	}

	@Override
	public Alumno getByID(int codigo) {
		String sql = "SELECT codAlumno, a.nombre as 'nAlumno', apellidos, email, dni, fNacimiento, telefono, codGenero, g.nombre as 'nGenero'"
				+ "FROM alumno a " + "INNER JOIN genero g ON g.codGenero=a.codGenero" + "WHERE codAlumno =" + codigo;

		myconexion.conectar();
		Connection conexion = myconexion.getConexion();
		Alumno alumno = null;
		try {
			PreparedStatement pSmt = conexion.prepareStatement(sql);
			ResultSet rs = pSmt.executeQuery();
			while (rs.next()) {
				alumno = parseAlumno(rs);
			}
		} catch (SQLException e) {
			LOG.fatal(e.getMessage());
		} finally {
			myconexion.desconectar();
		}

		return alumno;
	}

	private Alumno parseAlumno(ResultSet rs) {
		Alumno alumno = null;
		alumno = new Alumno();
		try {
			alumno.setCodigo(rs.getInt("codAlumno"));
			alumno.setNombre(rs.getString("nAlumno"));
			alumno.setApellidos(rs.getString("apellidos"));
			alumno.setfNacimiento(rs.getDate("fNacimiento"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOG.fatal(e.getMessage());
		} catch (CandidatoException e) {
			LOG.error(e.getMessage() + "Error en la fecha");
		}

		return null;
	}

	@Override
	public Alumno update(Alumno alumno) {
		String sql = "{CALL updateAlumno(?,?,?,?,?,?,?,?)}";
		Alumno alum = null;

		Connection conection = myconexion.getConexion();

		try {
			CallableStatement cSmt = conection.prepareCall(sql);
			cSmt.setInt("codigo", alumno.getCodigo());
			cSmt.setString("nombre", alumno.getNombre());
			cSmt.setString("apellidos", alumno.getApellidos());
			cSmt.setString("dni", alumno.getDni());
			cSmt.setDate("fNacimiento", (Date) alumno.getfNacimiento());
			cSmt.setString("email", alumno.getEmail());
			cSmt.setString("telefono", alumno.getTelefono());
			cSmt.setInt("codGenero", alumno.getGenero().getCodigo());

			cSmt.executeUpdate();
			alum = alumno;
			// int nFilas = cSmt.executeUpdate();

		} catch (SQLException e) {
			alum = getByID(alumno.getCodigo());
			LOG.fatal(e.getMessage());
		} finally {
			myconexion.desconectar();
		}
		return alum;
	}

	@Override
	public Alumno insert(Alumno alumno) {
		String sql = "{CALL insertAlumno(?,?,?,?,?,?,?,?)}";
		Alumno alum = null;

		Connection conection = myconexion.getConexion();

		try {
			CallableStatement cSmt = conection.prepareCall(sql);
			// cSmt.setInt("codigo", alumno.getCodigo());
			cSmt.setString("nombre", alumno.getNombre());
			cSmt.setString("apellidos", alumno.getApellidos());
			cSmt.setString("dni", alumno.getDni());
			cSmt.setDate("fNacimiento", (Date) alumno.getfNacimiento());
			cSmt.setString("email", alumno.getEmail());
			cSmt.setString("telefono", alumno.getTelefono());
			cSmt.setInt("codGenero", alumno.getGenero().getCodigo());

			cSmt.executeUpdate();
			alumno.setCodigo(cSmt.getInt("codigo"));
			alum = alumno;

		} catch (SQLException e) {
			LOG.fatal(e.getMessage());
		} finally {
			myconexion.desconectar();
		}
		return alum;
	}

	@Override
	public void delete(int codigo) {
		String sql = "{CALL deleteAlumno(?)}";

		Connection conection = myconexion.getConexion();

		try {
			CallableStatement cSmt = conection.prepareCall(sql);
			cSmt.setInt("codigo", codigo);
			// int nFilas = cSmt.executeUpdate();

		} catch (SQLException e) {
			LOG.fatal(e.getMessage());
		} finally {
			myconexion.desconectar();
		}

	}

	@Override
	public List<Alumno> getAll() {
		List<Alumno> alumnos = null;
		String sql = "{CALL getAllAlumno()}";

		Connection conection = myconexion.getConexion();
		Alumno alumno = null;
		try {
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
			myconexion.desconectar();
		}

		return alumnos;
	}

}
