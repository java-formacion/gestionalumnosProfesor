package com.ipartek.formacion.dbms.dao;

import java.sql.CallableStatement;
import java.sql.Date;
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
import com.ipartek.formacion.pojo.Genero;

public class AlumnoDAOImp implements AlumnoDAO {
	
	private static final Logger LOG=Logger.getLogger(AlumnoDAOImp.class);
	private ConexionDB dbConnection;
	private static AlumnoDAOImp INSTANCE;
	
	private AlumnoDAOImp(){
		dbConnection=ConexionDBImp.getInstance();
	}
	private synchronized static void createInstance(){
		if(INSTANCE==null){
			INSTANCE=new AlumnoDAOImp();
		}
	}
	
	public static AlumnoDAOImp getInstance(){
		if(INSTANCE==null){
			createInstance();
		}
		
		
		return INSTANCE;
	}
	

	@Override
	public Alumno getById(int codigo) {
		
		String sql="SELECT a.codAlumno, a.nombre as 'nAlumno' , a.apellidos, a.dni_nie, a.fNacimiento, a.codGenero, g.nombreGenero "+
					"FROM alumno a INNER JOIN genero g ON a.codGenero=g.codGenero "+
					"WHERE a.codAlumno="+codigo;
		Alumno alumno=null;
		
		//dbConnection.conectar();
		Connection conexion=dbConnection.getConexion();
		try {
			
			PreparedStatement pStat= conexion.prepareStatement(sql);
			ResultSet rS=pStat.executeQuery();
			while (rS.next()) {
				alumno=parseAlumno(rS);
				
			}
		} catch (SQLException e) {
			LOG.fatal(e.getMessage());
		}finally {
			dbConnection.desconectar();
		}
	
		return null;
	}

	@Override
	public Alumno updateAlumno(Alumno alumno) {
		Alumno a=null;
		String sql="{call updateAlumno(?,?,?,?,?,?,?,?)}";
		
		//dbConnection.conectar();
		Connection conexion=dbConnection.getConexion();
		try {
			
			CallableStatement cStat= conexion.prepareCall(sql);
			cStat.setInt("codigo", alumno.getCodigo());
			cStat.setString("nombre", alumno.getNombre());
			cStat.setString("apellido", alumno.getApellidos());
			cStat.setString("dni_nie", alumno.getDni());
			cStat.setDate("fNacimiento", new java.sql.Date(alumno.getfNacimiento().getTime()));
			cStat.setInt("codGenero", alumno.getGenero().getCodigo());
			cStat.setString("email", alumno.getEmail());
			cStat.setString("telefono", alumno.getTelefono());
			
			cStat.executeUpdate();
			a=alumno;
			
		} catch (SQLException e) {
			a=getById(alumno.getCodigo());
			LOG.fatal(e.getMessage());
			
		}finally {
			dbConnection.desconectar();
		}
		
		
		return a;
	}

	@Override
	public void deleteAlumno(int codigo) {
		String sql="{call deleteAlumno(?)}";
		
		//dbConnection.conectar();
		Connection conexion=dbConnection.getConexion();
		try {
			
			CallableStatement cStat= conexion.prepareCall(sql);
			cStat.setInt("codigo", codigo);
			cStat.executeUpdate();
			
		} catch (SQLException e) {
			LOG.fatal(e.getMessage());
		}finally {
			dbConnection.desconectar();
		}
		
	}

	@Override
	public Alumno createAlumno(Alumno alumno) {
		Alumno a=null;
		String sql="{call insertAlumno(?,?,?,?,?,?,?,?)}";
		
		//dbConnection.conectar();
		Connection conexion=dbConnection.getConexion();
		try {
			
			CallableStatement cStat= conexion.prepareCall(sql);
			cStat.setString("nombre", alumno.getNombre());
			cStat.setString("apellido", alumno.getApellidos());
			cStat.setString("dni_nie", alumno.getDni());
			cStat.setDate("fNacimiento", new java.sql.Date(alumno.getfNacimiento().getTime()));
			cStat.setInt("codGenero", alumno.getGenero().getCodigo());
			cStat.setString("email", alumno.getEmail());
			cStat.setString("telefono", alumno.getTelefono());
			
			cStat.executeUpdate();
			
			a=alumno;
			a.setCodigo(cStat.getInt("codigo"));
			
		} catch (SQLException e) {
			a=getById(alumno.getCodigo());
			LOG.fatal(e.getMessage());
			
		}finally {
			dbConnection.desconectar();
		}
		
		
		return a;
	}
	public Alumno parseAlumno(ResultSet rS) {
		Alumno aux=null;
		aux=new Alumno();
		try {
			aux.setCodigo(rS.getInt("codAlumno"));
			aux.setNombre(rS.getString("nAlumno"));
			aux.setApellidos(rS.getString("apellidos"));
			aux.setDni(rS.getString("dni_nie"));
			for (Genero g : Genero.values()) {
				if (g.getCodigo()==rS.getInt("codGenero")) {
					aux.setGenero(g);
				}
			}
			
		} catch (SQLException e) {
			LOG.fatal(e.getMessage());
		}
		
		
		return aux;
		
		
	}

	@Override
	public List<Alumno> getAll() {
		List <Alumno> lAlumnos=new ArrayList<Alumno>();
		String sql="{call getAllAlumno()}";
		
		//dbConnection.conectar();
		Connection conexion=dbConnection.getConexion();
		try {
			
			CallableStatement cStat= conexion.prepareCall(sql);
			ResultSet rS=cStat.executeQuery();
			while (rS.next()) {
				lAlumnos.add(parseAlumno(rS));
				
			}
		} catch (SQLException e) {
			LOG.fatal(e.getMessage());
		}finally {
			dbConnection.desconectar();
		}
		
		
		return lAlumnos;
	}
}
