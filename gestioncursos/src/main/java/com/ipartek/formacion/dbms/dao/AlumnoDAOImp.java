package com.ipartek.formacion.dbms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ipartek.formacion.dbms.ConexionDBImp;
import com.ipartek.formacion.pojo.Alumno;
import com.ipartek.formacion.pojo.Genero;
import com.ipartek.formacion.pojo.exception.CandidatoException;

public class AlumnoDAOImp implements AlumnoDAO {
	
	private static final Logger LOG = Logger.getLogger(AlumnoDAOImp.class);
	private ConexionDBImp myConexion;
	private static AlumnoDAOImp INSTANCE = null;
	
	private AlumnoDAOImp(){
		myConexion = ConexionDBImp.getInstance();
	}
	
	private synchronized static void createInstance() {
        if (INSTANCE == null) { 
            INSTANCE = new AlumnoDAOImp();
        }
    }

    public static AlumnoDAOImp getInstance() {
        if (INSTANCE == null) createInstance();
        return INSTANCE;
    }

	@Override
	public Alumno getById(int codigo) {
		
		Alumno alumno = null;

		String sql = "SELECT codAlumno, a.nombre as 'nAlumno', apellidos, dni_nie, fNacimiento, email, telefono, g.codGenero as 'codGenero', g.nombre as 'nGenero'"
				+ "		FROM alumno a "
				+ "		INNER JOIN genero g ON g.codGenero=a.codGenero"
				+ "		WHERE codAlumno = " + codigo;
						
		Connection conexion = myConexion.getConexion();
		
		
		try {
			PreparedStatement pSmt = conexion.prepareStatement(sql);
			ResultSet rs = pSmt.executeQuery();
			
			while(rs.next()){
				alumno = parseAlumno(rs);
			}
			
		} catch (SQLException e) {
			LOG.error("Error en AlumnoDAOImp.java - SQLException - getById(): "+ sql + e.getMessage());
		} finally{
			myConexion.desconectar();
		}
		
		return alumno;
	}
	
	@Override
	public List<Alumno> getAll() {
		List<Alumno> alumnos = null;
		String sql = "{call getAllAlumno()}";
		Connection conection = myConexion.getConexion();
		
		try {
			Alumno alumno = null;
			CallableStatement cSmt = conection.prepareCall(sql);
			ResultSet rs = cSmt.executeQuery();
			alumnos = new ArrayList<Alumno>();
			
			while(rs.next()){
				alumno = parseAlumno(rs);
				alumnos.add(alumno);
			}
		} catch (NullPointerException e) {
			LOG.fatal("Error - NullPointerException: " + e.getMessage());
		} catch (SQLException e) {
			LOG.fatal("Error - SQLException: "+sql+" " + e.getMessage());
		} finally{
			myConexion.desconectar();
		}

		return alumnos;
	}

	private Alumno parseAlumno(ResultSet rs) {
		Alumno alumno = null;
		alumno = new Alumno();
		
		try {
			alumno.setCodigo(rs.getInt("codAlumno"));
			alumno.setNombre(rs.getString("nAlumno"));
			alumno.setApellidos(rs.getString("apellidos"));
			try {
				alumno.setDni(rs.getString("dni_nie"));
				alumno.setfNacimiento(rs.getDate("fNacimiento"));
			} catch (CandidatoException e) {
				LOG.fatal("Error " + e.getMessage());
			}
			alumno.setEmail(rs.getString("email"));
			alumno.setTelefono(rs.getString("telefono"));
			
			int codGene = rs.getInt("codGenero");
			
			if(codGene == Genero.MASCULINO.getCodigo()){
				alumno.setGenero(Genero.MASCULINO);
			} else{
				if(codGene == Genero.FEMENINO.getCodigo()){
					alumno.setGenero(Genero.FEMENINO);
				} else{
					if(codGene == Genero.OTRO.getCodigo()){
						alumno.setGenero(Genero.OTRO);
					}
				}
			}
		} catch (SQLException e) {
			LOG.fatal("Error: " + e.getMessage());
		} 
		
		return alumno;
	}
	
	@Override
	public Alumno insert(Alumno alumno) {
		Alumno alum = null;
		
		String sql = "{insertAlumno(?, ?, ?, ?, ?, ?, ?, ?)}";
		Connection conection = myConexion.getConexion();
		
		try {
			CallableStatement cSmt = conection.prepareCall(sql);
			
			LOG.trace(alumno.toString());
			
			cSmt.setString("nombre", alumno.getNombre());
			cSmt.setString("apellidos", alumno.getApellidos());
			cSmt.setDate("fNacimiento", new java.sql.Date(alumno.getfNacimiento().getTime()));
			cSmt.setString("email", alumno.getEmail());
			cSmt.setString("telefono", alumno.getTelefono());
			cSmt.setString("dni_nie", alumno.getDni());
			cSmt.setInt("codGenero", alumno.getGenero().getCodigo());
			
			cSmt.executeUpdate();
			alum = alumno;
			alum.setCodigo(cSmt.getInt("codigo"));
			
			LOG.trace(alumno.toString());
			
		} catch (SQLException e) {
			LOG.fatal("Error - SQLException: " + e.getMessage());
		} catch (NullPointerException e){
			LOG.fatal(e.getMessage());
		}
		finally{
			myConexion.desconectar();
		}
		
		return null;
	}

	@Override
	public Alumno update(Alumno alumno) {
		Alumno alum = null;
		
		String sql = "{updateAlumno(?, ?, ?, ?, ?, ?, ?, ?)}";
		Connection conection = myConexion.getConexion();
		
		try {
			CallableStatement cSmt = conection.prepareCall(sql);
			LOG.trace(alumno.toString());
			cSmt.setInt("codigo", alumno.getCodigo());
			cSmt.setString("nombre", alumno.getNombre());
			cSmt.setString("apellidos", alumno.getApellidos());
			cSmt.setDate("fNacimiento", new java.sql.Date(alumno.getfNacimiento().getTime()));
			cSmt.setString("email", alumno.getEmail());
			cSmt.setString("telefono", alumno.getTelefono());
			cSmt.setString("dni_nie", alumno.getDni());
			cSmt.setInt("codGenero", alumno.getGenero().getCodigo());
			
			cSmt.executeUpdate();
			alum = alumno;
		} catch (SQLException e) {
			LOG.fatal("Error - SQLException: " + e.getMessage());
		} finally{
			myConexion.desconectar();
		}
		
		return alum;
	}

	@Override
	public void delete(int codigo) {
		String sql = "{call deleteAlumno(?)}";
		Connection conection = myConexion.getConexion();
		
		try {
			CallableStatement cSmt = conection.prepareCall(sql);
			cSmt.setInt("codigo", codigo);
			cSmt.executeUpdate();
		} catch (SQLException e) {
			LOG.fatal("Error - SQLException: " + e.getMessage());
		} finally{
			myConexion.desconectar();
		}
	}
}
