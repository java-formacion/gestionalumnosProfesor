package com.ipartek.formacion.dbms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import org.apache.log4j.Logger;

import com.ipartek.formacion.dbms.ConexionDB;
import com.ipartek.formacion.dbms.ConexionDBImp;
import com.ipartek.formacion.pojo.Alumno;

public class AlumnoDAOImp implements AlumnoDAO
	{
		private static final Logger LOG = Logger.getLogger(AlumnoDAOImp.class);
		private ConexionDB myConexion;
		
		// SINGLETON
		private static AlumnoDAOImp INSTANCE = null;
		
		private AlumnoDAOImp()
			{
				myConexion = ConexionDBImp.getInstance();
			}
		
		private synchronized static void createInstance()
			{
				if (INSTANCE == null)
					{
						INSTANCE = new AlumnoDAOImp();
					}
			}
		
		public static AlumnoDAOImp getInstance()
			{
				if (INSTANCE == null)
					{
						createInstance();
					}
				
				return INSTANCE;
			}
		
		// FIN SINGLETON
		
		// TODO es singleton porque va a tener el atributo de conexion a base de
		// datos
		
		@Override
		public Alumno create(Alumno alumno)
			{
				String sql = "{insertAlumno(?,?,?,?,?,?,?,?)}";
				Alumno alum = null;
//				ConexionDB myConexion = ConexionDBImp.getInstance();
//				myConexion.conectar();
				Connection conexion = myConexion.getConexion();
				try
					{
						CallableStatement cSmt = conexion.prepareCall(sql);
						
						cSmt.setInt("codigo", alumno.getCodigo());
						cSmt.setString("", alumno.getNombre());
						cSmt.setString("apellidos", alumno.getApellidos());
						cSmt.setString("dni_nie", alumno.getDni());
						cSmt.setDate("fNacimiento",
								new java.sql.Date(alumno.getfNacimiento().getTime()));
						cSmt.setString("email", alumno.getEmail());
						cSmt.setString("telefono", alumno.getTelefono());
						cSmt.setInt("codGenero", alumno.getGenero().getCodigo());
						
						cSmt.executeUpdate();
						alum = alumno;
						alum.setCodigo(cSmt.getInt(""));
						
					}
				catch (SQLException e)
					{
						LOG.fatal(e.getMessage());
						e.printStackTrace();
					}
				finally
					{
						myConexion.desconectar();
					}
				
				return null;
			}
		
		@Override
		public Alumno getById(int codigo)
			{
				Alumno alumno = null;
				String sql = "SELECT codAlumno, a.nombre as 'nomAlumno', a.apellidos, a.email, a.telefono, a.dni-nie, a.fNacimiento, a.codGenero, g.nombre as 'nomGenero'"
						+ "	FROM alumnos a"
						+ " INNER JOIN genero g ON g.codGenero = a.codGenero"
						+ "	WHERE codAlumno = " + codigo;
				
				ConexionDB dbConnection = ConexionDBImp.getInstance();
//				dbConnection.conectar();
				Connection conexion = dbConnection.getConexion();
				
				try
					{
						PreparedStatement pSmt = conexion.prepareStatement(sql);
						ResultSet rs = pSmt.executeQuery();
						while (rs.next())
							{
								alumno = parseAlumno(rs);
							}
					}
				catch (SQLException e)
					{
						e.printStackTrace();
					}
				finally
					{
						dbConnection.desconectar();
					}
				
				return alumno;
			}
		
		private Alumno parseAlumno(ResultSet rs)
			{
				Alumno alumno = null;
				alumno = new Alumno();
				try
					{
						alumno.setCodigo(rs.getInt("codAlumno"));
						alumno.setNombre(rs.getString("nomAlumno"));
						
					}
				catch (SQLException e)
					{
						e.printStackTrace();
					}
				
				return alumno;
			}
		
		@Override
		public Alumno update(Alumno alumno)
			{
				Alumno alum = null;
				String sql = "{call updateAlumno(?,?,?,?,?,?,?,?)}";
//				ConexionDB myConexion = ConexionDBImp.getInstance();
//				myConexion.conectar();
				Connection conexion = myConexion.getConexion();
				try
					{
						
						CallableStatement cSmt = conexion.prepareCall(sql);
						cSmt.setInt("codigo", alumno.getCodigo());
						cSmt.setString("nombre", alumno.getNombre());
						cSmt.setString("apellidos", alumno.getApellidos());
						cSmt.setString("dni_nie", alumno.getDni());
						cSmt.setDate("fNacimiento",
								(Date) alumno.getfNacimiento());
						cSmt.setString("email", alumno.getEmail());
						cSmt.setString("telefono", alumno.getTelefono());
						cSmt.setInt("codGenero", alumno.getGenero().getCodigo());
						
						cSmt.executeUpdate();
						alum = alumno;
					}
				catch (SQLException e)
					{
						alum = getById(alumno.getCodigo());
						LOG.fatal(e.getMessage());
					}
				finally
					{
						myConexion.desconectar();
					}
				return alum;
			}
		
		@Override
		public void delete(int codigo)
			{
				String sql = "{call deleteAlumno(?)}";
//				ConexionDB myConexion = ConexionDBImp.getInstance();
//				myConexion.conectar();
				Connection conexion = myConexion.getConexion();
				try
					{
						CallableStatement cSmt = conexion.prepareCall(sql);
						cSmt.setInt("codigo", codigo);
						cSmt.executeUpdate();
					}
				catch (SQLException e)
					{
						LOG.fatal(e.getMessage());
						
					}
				finally
					{
						myConexion.desconectar();
					}
			}
		
		@Override
		public List<Alumno> getAll()
			{
				List<Alumno> alumnos = null;
				String sql = "{call getAllAlumno()}";
//				ConexionDB myConexion = ConexionDBImp.getInstance();
				Connection conection = myConexion.getConexion();
				try
					{
						Alumno alumno = null;
						CallableStatement cSmt = conection.prepareCall(sql);
						ResultSet rs = cSmt.executeQuery();
						alumnos = new ArrayList<Alumno>();
						while (rs.next())
							{
								alumno = parseAlumno(rs);
								alumnos.add(alumno);
							}
						
					}
				catch (SQLException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				finally
					{
						myConexion.desconectar();
					}
				return alumnos;
			}
		
	}
