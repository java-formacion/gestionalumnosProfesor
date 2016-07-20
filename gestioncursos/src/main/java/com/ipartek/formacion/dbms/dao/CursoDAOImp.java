package com.ipartek.formacion.dbms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ipartek.formacion.dbms.ConexionDB;
import com.ipartek.formacion.dbms.ConexionDBImp;
import com.ipartek.formacion.pojo.Curso;
import com.ipartek.formacion.service.Util;

public class CursoDAOImp implements CursoDAO{
	private static final Logger LOG = Logger.getLogger(CursoDAOImp.class);
	private ConexionDB myConexion;
	private static CursoDAOImp INSTANCE;
	
	private CursoDAOImp(){
		myConexion = ConexionDBImp.getInstance(); //al crear una instancia, se realiza la conexion
		
	}
	
	private synchronized static void createInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CursoDAOImp();
		}
	}

	public static CursoDAOImp getInstance() {
		if (INSTANCE == null) {
			createInstance();
		}
		return INSTANCE;
	}
	

	@Override
	public Curso getById(int codigo) {
		Curso curso = null;
		String sql = "SELECT codCurso, nombre, codTipoCurso, codPatrocinador"
				+ "FROM curso"
				+ "WHERE codigo =" + codigo;
		
		myConexion.conectar();
		Connection conexion = myConexion.getConexion();
		try {
			PreparedStatement pSmt =  conexion.prepareStatement(sql);
			ResultSet rs = pSmt.executeQuery();
			while(rs.next()){
				curso = parseCurso(rs);
			}
			
		} catch (SQLException e) {
			LOG.fatal(e.getMessage());
		}finally{
			myConexion.desconectar();
		}
		
		return curso;
	}

	private Curso parseCurso(ResultSet rs) {
		Curso curso = null;
		curso = new Curso();
		try {
			curso.setCodigo(rs.getInt("codCurso"));
			curso.setNombre(rs.getString("nombre"));
			curso.setTipo(Util.parseTipoCurso(rs.getInt("codTipoCurso")));
			curso.setReferencia(rs.getString("codPatrocinador"));
			
		} catch (SQLException e) {
			LOG.fatal(e.getMessage());
		}
		return curso;
	}

	@Override
	public Curso update(Curso curso) {
		Curso cur = null;
		String sql = "{call updateCurso(?,?,?,?)}"; 
		ConexionDB myConexion = ConexionDBImp.getInstance();
		myConexion.conectar();
		Connection connection = myConexion.getConexion();
		
		try {
			CallableStatement cSmt = connection.prepareCall(sql);
			cSmt.setInt("codigo", curso.getCodigo());
			cSmt.setString("nombre", curso.getNombre());
			cSmt.setString("referencia", curso.getReferencia());
			cSmt.setInt("tipo", curso.getTipo().getCodigo());
			cSmt.executeUpdate();
			cur = curso; //si todo va bien meto los datos de alumno en alum que es el objeto que voy a devolver
		} catch (SQLException e) {
			cur = getById(curso.getCodigo());
			LOG.fatal(e.getMessage());
		}finally{
			myConexion.desconectar();
		}
		
		return cur;
	}

	@Override
	public Curso create(Curso curso) {
		Curso cur = null;
		String sql = "{call insertCurso(?,?,?,?)}"; 
		myConexion.conectar();
		Connection connection = myConexion.getConexion();
		
		try {
			CallableStatement cSmt = connection.prepareCall(sql);
			cSmt.setString("nombre", curso.getNombre());
			cSmt.setString("referencia", curso.getReferencia());
			cSmt.setInt("tipo", curso.getTipo().getCodigo());
			cSmt.executeUpdate();
			cur = curso;
			curso.setCodigo(cSmt.getInt("codigo")); 
			
		} catch (SQLException e) {
			cur = getById(curso.getCodigo());
			LOG.fatal(e.getMessage());
		}finally{
			myConexion.desconectar();
		}
		
		return cur;
	}
	

	@Override
	public void delete(int codigo) {
		String sql = "{call deleteCurso(?)}"; //llamamos al procedimiento almacenado en bbdd, cada parametro se pone con una ?
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
	public List<Curso> getAll() {
		List<Curso> cursos = null;
		String sql = "{call getAllCurso()}"; //llamamos al procedimiento almacenado en bbdd
		myConexion.conectar();
		Connection connection = myConexion.getConexion();
		try {
			Curso curso = null;
			CallableStatement cSmt = connection.prepareCall(sql);
			ResultSet rs = cSmt.executeQuery();
			cursos = new ArrayList<Curso>();
			while(rs.next()){
				curso = parseCurso(rs);
				cursos.add(curso);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			myConexion.desconectar();
		}
		
		
		return cursos;
	}

}
