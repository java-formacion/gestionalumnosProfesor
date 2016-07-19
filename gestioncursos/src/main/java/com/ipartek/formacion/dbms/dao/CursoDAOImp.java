package com.ipartek.formacion.dbms.dao;

import java.sql.CallableStatement;
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
  private static final Logger LOG = Logger.getLogger(AlumnoDAOImp.class);
  private ConexionDB myConexion;
  private static CursoDAOImp INSTANCE;

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
  public Curso create(Curso curso) {
    Curso curs = null;
    String sql = "{call insertCurso(?,?,?,?)}";

    try {
      CallableStatement cSmt = myConexion.getConexion().prepareCall(sql);
      cSmt.setString("nombre", curso.getNombre());
      cSmt.setString("codPatrocinador", curso.getReferencia());
      cSmt.setInt("codTipoCurso", curso.getTipo().getCodigo());

      cSmt.executeUpdate();

      curs = curso;
      curs.setCodigo(cSmt.getInt(""));

    } catch (SQLException e) {
      curs = getById(curs.getCodigo());
      LOG.fatal(e.getMessage());

    } finally {
      myConexion.desconectar();
    }

    return curs;

  }

  @Override
  public Curso getById(int codigo) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void delete(int codigo) {
    String sql = "{call deleteCurso(?)}";

    try {
      CallableStatement cSmt = myConexion.getConexion().prepareCall(sql);
      cSmt.setInt("", codigo);
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

    // myConexion.conectar();
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

      LOG.fatal(e.getMessage());
    } finally {
      myConexion.desconectar();
    }

    return cursos;
  }

  private Curso parseCurso(ResultSet rs) {
    Curso curso = new Curso();
    try {
      curso.setCodigo(rs.getInt("codCurso"));
      curso.setNombre(rs.getString("nCurso"));
      curso.setReferencia(rs.getString("referencia"));
      // curso.setTipo(rs.getInt("codTipoCurso"));

    } catch (SQLException e) {
      LOG.fatal(e.getMessage());
    }

    return curso;
  }

  @Override
  public Curso update(Curso curso) {
    Curso cur = null;
    String sql = "{call updateCurso(?,?,?,?,?,?,?,?)}";

    // myConexion.conectar();

    try {
      CallableStatement cSmt = myConexion.getConexion().prepareCall(sql);
      cSmt.setInt("codigo", curso.getCodigo());
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
  public void darDeAlta(Alumno alumno) {
    // TODO Auto-generated method stub

  }

  @Override
  public void darDeBaja(Alumno alumno) {
    // TODO Auto-generated method stub

  }

}
