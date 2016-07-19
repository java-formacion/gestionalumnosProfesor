package com.ipartek.formacion.dbms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.dbms.ConexionDB;
import com.ipartek.formacion.dbms.ConexionDBImp;
import com.ipartek.formacion.pojo.Alumno;

public class AlumnoDAOImp implements AlumnoDAO {

	// TODO es sigleton
	// pq va a tener el atributo que es la conexion
	@Override
	public Alumno getById(int codigo) {
		Alumno alumno = null;
		String sql = "SELECT codAlumno, a.nombre as 'nAlumno', apellidos, email,telefono,dni_nie,fNacimiento, codGenero, g.nombre as 'nGenero'"
				+ " FROM alumno a "
				+ "	INNER JOIN genero g ON g.codGenero = a.codGenero"
				+ " WHERE codAlumno =" + codigo;
		ConexionDB dbConnection = ConexionDBImp.getInstance();
		dbConnection.conectar();
		Connection conexion = dbConnection.getConexion();
		try {

			PreparedStatement pSmt = conexion.prepareStatement(sql);
			ResultSet rs = pSmt.executeQuery();
			while (rs.next()) {
				alumno = parseAlumno(rs);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return alumno;
	}

	@Override
	public Alumno update(Alumno alumno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Alumno create(Alumno alumno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int codigo) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Alumno> getAll() {
		List<Alumno> alumnos = null;
		String sql = "{call getAllAlumno()}";
		ConexionDB myConexion = ConexionDBImp.getInstance();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alumnos;
	}

}
