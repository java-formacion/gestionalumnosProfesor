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

/**
 * 
 * @author Curso
 *
 */
public class CursoDAOImp implements CursoDAO {
  private final static Logger LOG = Logger.getLogger(CursoDAOImp.class);
  private static CursoDAOImp INSTANCE = null;
  private static ConexionDB myConexion;
  private Connection conexion;

  /**
 * 
 */
  private CursoDAOImp() {
    myConexion = ConexionDBImp.getInstance();
  }

  /**
   * 
   * @return INSTANCE
   */
  public static CursoDAOImp getInstance() {
    if (INSTANCE == null) {
      createInstance();
    }
    return INSTANCE;
  }

  /**
 * 
 */
  private synchronized static void createInstance() {
    if (INSTANCE == null) {
      INSTANCE = new CursoDAOImp();
    }
  }

  /**
   * @Override
   * @return nada
   * @throws CloneNotSupportedException
   *           no se puede cñlonar
   */
  protected Object clone() throws CloneNotSupportedException {
    throw new CloneNotSupportedException();
  }

  /**
   * @Override
   * @param codigo
   *          codigo curso
   * @return curso
   */
  public Curso getById(int codigo) {
    Curso curso = null;
    String sql = "{call getCursoById(?)}";
    conexion = myConexion.getConexion();
    try {
      PreparedStatement pSmt = conexion.prepareStatement(sql);
      ResultSet rs = pSmt.executeQuery();
      while (rs.next()) {
        curso = parseCurso(rs);

      }
    } catch (SQLException e) {
      LOG.error(e.getMessage());
    } finally {
      myConexion.desconectar();
    }

    return curso;
  }

  /**
   * @param rs
   *          ResultSet
   * @return curso
   * @throws SQLException
   *           excepcion sql
   */
  private Curso parseCurso(ResultSet rs) {
    Curso curso = null;
    curso = new Curso();
    try {
      curso.setCodigo(rs.getInt("codCurso"));
      curso.setNombre(rs.getString("nCurso"));
    } catch (SQLException e) {
      LOG.error(e.getMessage());
    }
    return curso;
  }

  /**
   * @Override
   * @param curso
   *          Curso
   * @return curso
   */
  public Curso update(Curso curso) {
    Curso alum = null;
    String sql = "{call updateCurso(?,?,?,?,?,?,?,?)}";

    try {
      CallableStatement cSmt = myConexion.getConexion().prepareCall(sql);
      // TODO Update curso
      cSmt.executeUpdate();
      alum = curso;
    } catch (SQLException e) {
      alum = getById(curso.getCodigo());
      LOG.fatal(e.getMessage() + " -- Error al actualizar");
    } finally {
      myConexion.desconectar();
    }
    return alum;
  }

  /**
   * @Override
   * @param curso
   *          Curso
   * @return curso
   */
  public Curso create(Curso curso) {
    Curso alum = null;
    String sql = "{call insertCurso(?,?,?,?,?,?,?,?)}";

    try {
      CallableStatement cSmt = myConexion.getConexion().prepareCall(sql);
      // TODO create curso
      cSmt.executeUpdate();
      alum = curso;
      alum.setCodigo(cSmt.getInt("codCurso"));
    } catch (SQLException e) {
      LOG.fatal(e.getMessage() + " -- Error al insertar curso");
    } finally {
      myConexion.desconectar();
    }
    return alum;
  }

  /**
   * @Override
   * @param codigo
   *          int
   */
  public void delete(int codigo) {
    String sql = "{call deleteCurso(?)}";
    conexion = myConexion.getConexion();
    try {
      CallableStatement cSmt = conexion.prepareCall(sql);
      cSmt.setInt("codigo", codigo);
      // int nFilas =
      cSmt.executeUpdate();
      // if (nFilas < 1) {
      //
      // }
    } catch (SQLException e) {
      LOG.fatal(e.getMessage() + " -- Error al borrar");
    } finally {
      myConexion.desconectar();
    }

  }

  /**
   * @Override
   * @return lista de cursos
   */
  public List<Curso> getAll() {
    List<Curso> cursos = null;
    String sql = "{call getAllCurso()}";
    conexion = myConexion.getConexion();
    try {
      Curso curso = null;
      CallableStatement cSmt = conexion.prepareCall(sql);
      ResultSet rs = cSmt.executeQuery();
      cursos = new ArrayList<Curso>();
      while (rs.next()) {
        curso = parseCurso(rs);
        cursos.add(curso);
      }

    } catch (SQLException e) {
      LOG.error(e.getMessage());
    } finally {
      myConexion.desconectar();
    }
    return cursos;
  }
}
