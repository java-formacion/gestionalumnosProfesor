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

public class CursoDAOImp implements CursoDAO {
	private static final Logger LOG = Logger.getLogger(CursoDAOImp.class);
	private ConexionDB myConexion;
	private static CursoDAOImp INSTANCE = null;

	private CursoDAOImp() {
		myConexion = ConexionDBImp.getInstance();
	}

	public static CursoDAOImp getInstance() {
		if (INSTANCE == null) {
			createInstance();
		}
		return INSTANCE;
	}

	private synchronized static void createInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CursoDAOImp();
		}

	}

	@Override
	public Curso getById(int codigo) {
		String sql = "SELECT codCurso, c.nombre as 'nombreCurso', codPatrocinador, codTipoCurso, t.nombre as 'nombreTipoCurso' "
				+ "FROM curso c INNER JOIN tipocurso t ON t.codTipoCurso=c.codTipoCurso " + "WHERE codCurso=codigo";
		Connection conexion = myConexion.getConexion();
		Curso curso = null;
		try {
			PreparedStatement pStm = conexion.prepareStatement(sql);
			ResultSet rs = pStm.executeQuery();
			while (rs.next()) {
				curso = parseCurso(rs);
			}
		} catch (SQLException e) {
			LOG.fatal(e.getMessage());
		} finally {
			myConexion.desconectar();
		}
		return null;
	}

	private Curso parseCurso(ResultSet rs) {
		Curso curso = null;
		curso = new Curso();
		try {
			curso.setCodigo(rs.getInt("codCurso"));
			curso.setNombre(rs.getString("nCurso"));
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
		// ConexionDB myConexion = ConexionDBImp.getInstance();
		// myConexion.conectar();
		try {
			CallableStatement cSmt = myConexion.getConexion().prepareCall(sql);
			cSmt.setInt("codCurso", curso.getCodigo());
			cSmt.setString("nombre", curso.getNombre());
			cSmt.setString("codPatrocinador", curso.getReferencia());
			cSmt.setInt("codTipoCurso", curso.getTipo().getCodigo());

			cSmt.executeUpdate();
			cur = curso;
		} catch (SQLException e) {
			cur = getById(curso.getCodigo());
			LOG.fatal(e.getMessage());
		} finally {
			myConexion.desconectar();
		}

		return cur;
	}

	@Override
	public Curso create(Curso curso) {
		Curso cur = null;
		String sql = "{call insertCurso(?,?,?,?)}";
		// ConexionDB myConexion = ConexionDBImp.getInstance();
		// myConexion.conectar();
		Connection conexion = myConexion.getConexion();
		try {
			CallableStatement cSmt = conexion.prepareCall(sql);
			// cSmt.setInt("codigo", alumno.getCodigo());//el codigo NO se
			// pasa!!!
			cSmt.setString("nombre", curso.getNombre());
			cSmt.setString("codReferencia", curso.getReferencia());
			cSmt.setInt("codTipoCurso", curso.getTipo().getCodigo());
			cSmt.executeUpdate();
			cur = curso;
			cur.setCodigo(cSmt.getInt("codCurso"));
			cur = curso;
		} catch (SQLException e) {
			LOG.fatal(e.getMessage());
		} finally {
			myConexion.desconectar();
		}

		return cur;
	}

	@Override
	public void delete(int codigo) {
		String sql = "{call deleteAlumno(?)}";
		// ConexionDB myConexion = ConexionDBImp.getInstance();
		// myConexion.conectar();
		Connection conexion = myConexion.getConexion();
		try {
			CallableStatement cSmt = conexion.prepareCall(sql);
			cSmt.setInt("codigo", codigo);// el nombre del parametro del
											// procedimiento en xampp entre ""
			cSmt.executeUpdate();
		} catch (SQLException e) {
			LOG.fatal(e.getMessage());
		} finally {
			myConexion.desconectar();
		}

	}

	@Override
	public List<Curso> getAll() {
		List<Curso> cursos = null;
		String sql = "{call getAllCurso()}";
		// ConexionDB myConexion = ConexionDBImp.getInstance();
		Connection conection = myConexion.getConexion();
		try {
			Curso curso = null;
			CallableStatement cSmt = conection.prepareCall(sql);
			ResultSet rs = cSmt.executeQuery();
			cursos = new ArrayList<Curso>();
			while (rs.next()) {
				curso = parseCurso(rs);
				cursos.add(curso);
			}
		} catch (SQLException e) {
			LOG.fatal(e.getMessage());
		} finally {
			myConexion.desconectar();
		}

		return cursos;
	}

}
