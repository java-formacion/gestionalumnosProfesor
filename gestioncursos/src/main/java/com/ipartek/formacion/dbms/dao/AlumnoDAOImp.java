package com.ipartek.formacion.dbms.dao;

import java.sql.Connection;
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
import com.mysql.jdbc.CallableStatement;

public class AlumnoDAOImp implements AlumnoDAO {

	// singleton porque va a tener un atributo de conexion a bbdd
	private static final Logger LOG = Logger.getLogger(AlumnoDAOImp.class);

	@Override
	public Alumno getByID(int codigo) {
		String sql = "SELECT codAlumno, a.nombre as 'nAlumno', apellidos, email, dni, fNacimiento, telefono, codGenero, g.nombre as 'nGenero'"
				+ "FROM alumno a " + "INNER JOIN genero g ON g.codGenero=a.codGenero" + "WHERE codAlumno =" + codigo;

		ConexionDB dbConnection = ConexionDBImp.getInstance();
		dbConnection.conectar();
		Connection conexion = dbConnection.getConexion();
		Alumno alumno = null;
		try {
			PreparedStatement pSmt = conexion.prepareStatement(sql);
			ResultSet rs = pSmt.executeQuery();
			while (rs.next()) {
				alumno = (Alumno) parseAlumno(rs);
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage());
		}

		return alumno;
	}

	private Object parseAlumno(ResultSet rs) {
		Alumno alumno = null;
		alumno = new Alumno();
		try {
			alumno.setCodigo(rs.getInt("codAlumno"));
			alumno.setNombre(rs.getString("nAlumno"));
			alumno.setApellidos(rs.getString("apellidos"));
			alumno.setfNacimiento(rs.getDate("fNacimiento"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOG.error(e.getMessage());
		} catch (CandidatoException e) {
			LOG.error(e.getMessage() + "Error en la fecha");
		}

		return null;
	}

	@Override
	public Alumno update(Alumno alumno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Alumno insert(Alumno alumno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Alumno delete(int codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Alumno> getAll() {
		List<Alumno> alumnos = null;
		String sql = "{CALL getAllAlumno()}";

		ConexionDB myconexion = ConexionDBImp.getInstance();
		Connection conection = myconexion.getConexion();
		Alumno alumno = null;
		try {
			CallableStatement cSmt = (CallableStatement) conection.prepareStatement(sql);
			ResultSet rs = cSmt.executeQuery();
			alumnos = new ArrayList<Alumno>();
			while (rs.next()) {
				alumno = (Alumno) parseAlumno(rs);
				alumnos.add(alumno);
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage());
		}

		return null;
	}

}
