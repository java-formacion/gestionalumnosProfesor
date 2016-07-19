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

public class AlumnoDAOImp implements AlumnoDAO
	{
		
		@Override
		public Alumno create(Alumno alumno)
			{
				// TODO Auto-generated method stub
				return null;
			}
		
		// TODO es singleton porque va a tener el atributo de conexion a base de
		// datos
		@Override
		public Alumno getById(int codigo)
			{
				Alumno alumno = null;
				String sql = "SELECT codAlumno, a.nombre as 'nomAlumno', a.apellidos, a.email, a.telefono, a.dni-nie, a.fNacimiento, a.codGenero, g.nombre as 'nomGenero'"
						+ "	FROM alumnos a"
						+ " INNER JOIN genero g ON g.codGenero = a.codGenero"
						+ "	WHERE codAlumno = " + codigo;
				
				ConexionDB dbConnection = ConexionDBImp.getInstance();
				dbConnection.conectar();
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
						// TODO Auto-generated catch block
						e.printStackTrace();
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
				// TODO Auto-generated method stub
				return null;
			}
		
		@Override
		public void delete(int codigo)
			{
String sql = "{call deleteAlumno(?);}";				
			}

		@Override
		public List<Alumno> getAll()
			{
				List<Alumno> alumnos = null;
				String sql = "{call getAllAlumno()}";
				ConexionDB myConexion = ConexionDBImp.getInstance();
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
				return alumnos;
			}
		
	}
