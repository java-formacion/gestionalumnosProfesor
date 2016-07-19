package com.ipartek.formacion.dbms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.ipartek.formacion.dbms.ConexionDB;
import com.ipartek.formacion.dbms.ConexionDBImp;
import com.ipartek.formacion.pojo.Alumno;
import com.ipartek.formacion.pojo.Curso;

public class CursoDAOImp implements CursoDAO
	{

		private static final Logger LOG = Logger.getLogger(CursoDAOImp.class);
		private ConexionDB myConexion;
		
		// SINGLETON
		private static CursoDAOImp INSTANCE = null;
		
		private CursoDAOImp()
			{
				myConexion = ConexionDBImp.getInstance();
			}
		
		private synchronized static void createInstance()
			{
				if (INSTANCE == null)
					{
						INSTANCE = new CursoDAOImp();
					}
			}
		
		public static CursoDAOImp getInstance()
			{
				if (INSTANCE == null)
					{
						createInstance();
					}
				
				return INSTANCE;
			}
		
		// FIN SINGLETON
		
		
	@Override
	public Curso create(Curso curso)
		{
			String sql = "{insertCurso(?,?,?,?)}";			
			return null;
		}
	
	
	
	
	
	
	
	public Alumno create(Alumno alumno)
		{
			String sql = "{insertAlumno(?,?,?,?,?,?,?,?)}";
			Alumno alum = null;
//			ConexionDB myConexion = ConexionDBImp.getInstance();
//			myConexion.conectar();
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
	public Curso getById(int codigo)
		{
			// TODO Auto-generated method stub
			return null;
		}

	@Override
	public Curso update(Curso curso)
		{
			// TODO Auto-generated method stub
			return null;
		}

	@Override
	public void delete(int codigo)
		{
			// TODO Auto-generated method stub
			
		}

	@Override
	public List<Curso> getAll()
		{
			// TODO Auto-generated method stub
			return null;
		}
		
	}
