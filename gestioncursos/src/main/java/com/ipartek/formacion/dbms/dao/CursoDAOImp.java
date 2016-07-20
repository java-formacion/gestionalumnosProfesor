package com.ipartek.formacion.dbms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ipartek.formacion.dbms.ConexionDB;
import com.ipartek.formacion.dbms.ConexionDBImp;
import com.ipartek.formacion.pojo.Alumno;
import com.ipartek.formacion.pojo.Curso;

public class CursoDAOImp implements CursoDAO {
	private final static Logger LOG = Logger.getLogger(CursoDAOImp.class);
	private static AlumnoDAOImp INSTANCE = null;
	private static ConexionDB myConexion;
	private Connection conexion;
	
	public CursoDAOImp() {
		super();
		myConexion = ConexionDBImp.getInstance();
	}

	@Override
	public Curso getById(int codigo) {
		conexion = myConexion.getConexion();
		String sql = "{call getCursoById(?)}";
		Curso curso=null;
		try {
			
			CallableStatement cSmt = conexion.prepareCall(sql);
			cSmt.setInt("codigo", codigo);
			ResultSet rs = cSmt.executeQuery();
			
			while (rs.next()) {
				curso=parseCurso(rs);
							
			}

		} catch (SQLException e) {
			LOG.error(e.getMessage());
		} finally {
			myConexion.desconectar();
		}
		
		return curso;
		
	}
	@Override
	public Curso update(Curso curso) {
		conexion = myConexion.getConexion();
		String sql = "{call updateCurso(?,?,?,?)}";
		try {
			CallableStatement cSmt = conexion.prepareCall(sql);
			cSmt.setInt("codigo", curso.getCodigo());
			cSmt.setString("nCurso", curso.getNombre());
			cSmt.setInt("codPatrocinador", 1);
			cSmt.setInt("codTipoCurso", 1);
			cSmt.executeUpdate();
			
		} catch (SQLException e) {
			LOG.error(e.getMessage()+"casca aqui");
		}
		
		return curso;
	}

	@Override
	public Curso create(Curso curso) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	
	public void delete(int codigo) {
		conexion = myConexion.getConexion();
		String sql = "{call deleteCurso(?)}";
		try {
			CallableStatement cSmt = conexion.prepareCall(sql);
			cSmt.setInt("codigo", codigo);
			cSmt.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.getMessage());
		}
	}

	@Override
	public List<Curso> getAll() {
		List<Curso> cursos = null;
		String sql = "{call getAllCurso()}";
		
		conexion = myConexion.getConexion();
		try {
			Curso curso=null;
			CallableStatement cSmt = conexion.prepareCall(sql);
			ResultSet rs = cSmt.executeQuery();
			cursos = new ArrayList<Curso>();
			while (rs.next()) {
				curso=parseCurso(rs);
				
				cursos.add(curso);
			}

		} catch (SQLException e) {
			LOG.error(e.getMessage());
		} finally {
			myConexion.desconectar();
		}
		return cursos;
	}

	private Curso parseCurso(ResultSet rs) throws SQLException {
		Curso curso= null;
		curso =new Curso();
		curso.setCodigo(rs.getInt("codCurso"));
		curso.setNombre(rs.getString("nCurso"));
		return curso;
	}

}
