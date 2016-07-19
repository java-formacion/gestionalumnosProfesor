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

public class AlumnoDAOImp implements AlumnoDAO{
	private static final Logger LOG = Logger.getLogger(AlumnoDAOImp.class);
	private ConexionDB myConexion;
	private static AlumnoDAOImp INSTANCE;
	//ES SINGLETON PORQUE VA A TENER EL ATRIBUTO QUE ES LA CONEXION A LA BBDD
	
	private AlumnoDAOImp(){
		myConexion = ConexionDBImp.getInstance(); //al crear una instancia, se realiza la conexion
		
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
	
	@Override
	public Alumno getById(int codigo) {
		Alumno alumno = null;
		String sql = "SELECT codAlumno, a.nombre as 'nAlumno', apellidos, email, telefono, dni_nie, fNacimiento, codGenero, g.nombre as 'nGenero'"
				+ "FROM alumno a INNER JOIN genero g ON g.codGenero=a.codGenero"
				+ "WHERE codAlumno =" + codigo;
		
		myConexion.conectar();
		Connection conexion = myConexion.getConexion();
		try {
			PreparedStatement pSmt =  conexion.prepareStatement(sql);
			ResultSet rs = pSmt.executeQuery();
			while(rs.next()){
				alumno = parseAlumno(rs);
			}
			
		} catch (SQLException e) {
			LOG.fatal(e.getMessage());
		}finally{
			myConexion.desconectar();
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
			alumno.setDni(rs.getString("dni_nie"));
			//alumno.setEmail(rs.getString("email"));
			//alumno.setTelefono(rs.getString("telefono"));
			
		} catch (SQLException e) {
			LOG.fatal(e.getMessage());
		}
		
		
		return alumno;
	}

	@Override
	public Alumno update(Alumno alumno) {
		Alumno alum = null;
		String sql = "{call updateAlumno(?,?,?,?,?,?,?,?)}"; 
		ConexionDB myConexion = ConexionDBImp.getInstance();
		myConexion.conectar();
		Connection connection = myConexion.getConexion();
		
		try {
			CallableStatement cSmt = connection.prepareCall(sql);
			cSmt.setInt("codigo", alumno.getCodigo());
			cSmt.setString("nombre", alumno.getNombre());
			cSmt.setString("apellidos", alumno.getApellidos());
			cSmt.setString("dni", alumno.getDni());
			cSmt.setDate("fNacimiento", new java.sql.Date(alumno.getfNacimiento().getTime()));
			cSmt.setString("telefono", alumno.getTelefono());
			cSmt.setString("email", alumno.getEmail());
			cSmt.setInt("codGenero", alumno.getGenero().getCodigo());
			cSmt.executeUpdate();
			alum = alumno; //si todo va bien meto los datos de alumno en alum que es el objeto que voy a devolver
		} catch (SQLException e) {
			alum = getById(alumno.getCodigo());
			LOG.fatal(e.getMessage());
		}finally{
			myConexion.desconectar();
		}
		
		return alum;
	}

	@Override
	public Alumno create(Alumno alumno) {
		Alumno alum = null;
		String sql = "{call insertAlumno(?,?,?,?,?,?,?,?)}"; 
		myConexion.conectar();
		Connection connection = myConexion.getConexion();
		
		try {
			CallableStatement cSmt = connection.prepareCall(sql);
			cSmt.setString("nombre", alumno.getNombre());
			cSmt.setString("apellidos", alumno.getApellidos());
			cSmt.setString("dni", alumno.getDni());
			cSmt.setDate("fNacimiento", new java.sql.Date(alumno.getfNacimiento().getTime()));
			cSmt.setString("telefono", alumno.getTelefono());
			cSmt.setString("email", alumno.getEmail());
			cSmt.setInt("codGenero", alumno.getGenero().getCodigo());
			cSmt.executeUpdate();
			alum = alumno;
			alum.setCodigo(cSmt.getInt("codigo")); 
			
		} catch (SQLException e) {
			alum = getById(alumno.getCodigo());
			LOG.fatal(e.getMessage());
		}finally{
			myConexion.desconectar();
		}
		
		return alum;
	}

	@Override
	public void delete(int codigo) {
		String sql = "{call deleteAlumno(?)}"; //llamamos al procedimiento almacenado en bbdd, cada parametro se pone con una ?
		myConexion.conectar();
		Connection connection = myConexion.getConexion();
		
		try {
			CallableStatement cSmt = connection.prepareCall(sql);
			cSmt.setInt("codigo", codigo); //"codigo" pq en el procedimiento le hemos llamado codigo, y codigo xq le hemos llamado así en el método delete
			cSmt.executeUpdate();
			
		} catch (SQLException e) {
			LOG.fatal(e.getMessage());
		}finally{
			myConexion.desconectar();
		}
	}

	@Override
	public List<Alumno> getAll() {
		List<Alumno> alumnos = null;
		String sql = "{call getAllAlumno()}"; //llamamos al procedimiento almacenado en bbdd
		myConexion.conectar();
		Connection connection = myConexion.getConexion();
		try {
			Alumno alumno = null;
			CallableStatement cSmt = connection.prepareCall(sql);
			ResultSet rs = cSmt.executeQuery();
			alumnos = new ArrayList<Alumno>();
			while(rs.next()){
				alumno = parseAlumno(rs);
				alumnos.add(alumno);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			myConexion.desconectar();
		}
		
		
		return alumnos;
	}
	
	

}
