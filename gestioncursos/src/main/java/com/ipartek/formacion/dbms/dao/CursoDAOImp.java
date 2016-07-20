package com.ipartek.formacion.dbms.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ipartek.formacion.dbms.ConexionDB;
import com.ipartek.formacion.dbms.ConexionDBImp;
import com.ipartek.formacion.pojo.Curso;
import com.ipartek.formacion.service.Util;

/**
 * 
 * @author Curso
 *
 */
public class CursoDAOImp implements CursoDAO {
  private final static Logger LOG = Logger.getLogger(CursoDAOImp.class);
  private static CursoDAOImp INSTANCE = null;
  private static ConexionDB myConexion;

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
    try {
      CallableStatement cSmt = myConexion.getConexion().prepareCall(sql);
      cSmt.setInt("codigo", codigo);

      ResultSet rs = cSmt.executeQuery();
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
      curso.setCodigoPatrocinador(rs.getString("codPatrocinador"));
      curso.setTipo(Util.parseTipo(rs.getInt("codTipoCurso")));
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
    String sql = "{call updateCurso(?,?,?,?)}";

    try {
      CallableStatement cSmt = myConexion.getConexion().prepareCall(sql);
      cSmt.setInt("codigo", curso.getCodigo());
      cSmt.setString("nombre", curso.getNombre());
      cSmt.setString("codPatrocinador", curso.getCodigoPatrocinador());
      cSmt.setInt("codTipo", curso.getTipo().getCodigo());
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
    Curso cur = null;
    String sql = "{call insertCurso(?,?,?,?)}";

    try {
      CallableStatement cSmt = myConexion.getConexion().prepareCall(sql);
      cSmt.setString("nombre", curso.getNombre());
      cSmt.setString("codPatrocinador", curso.getCodigoPatrocinador());
      cSmt.setInt("codTipoCurso", curso.getTipo().getCodigo());
      cSmt.executeUpdate();
      cur = curso;
      cur.setCodigo(cSmt.getInt("codCurso"));
    } catch (SQLException e) {
      LOG.fatal(e.getMessage() + " -- Error al insertar curso");
    } finally {
      myConexion.desconectar();
    }
    return cur;
  }

  /**
   * @Override
   * @param codigo
   *          int
   */
  public void delete(int codigo) {
    String sql = "{call deleteCurso(?)}";
    try {
      CallableStatement cSmt = myConexion.getConexion().prepareCall(sql);
      cSmt.setInt("codigo", codigo);
      cSmt.executeUpdate();
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
    try {
      Curso curso = null;
      CallableStatement cSmt = myConexion.getConexion().prepareCall(sql);
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
