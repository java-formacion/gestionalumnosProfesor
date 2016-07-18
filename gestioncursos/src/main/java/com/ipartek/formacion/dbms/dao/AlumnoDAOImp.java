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
import com.ipartek.formacion.pojo.Alumno;
import com.ipartek.formacion.pojo.excepciones.CandidatoException;
import com.ipartek.formacion.service.Util;

/**
 * 
 * @author Curso
 *
 */
public class AlumnoDAOImp implements AlumnoDAO {
  private final static Logger LOG = Logger.getLogger(AlumnoDAOImp.class);

  /**
   * @Override
   * @param codigo
   *          codigo alumno
   * @return alumno
   */
  public Alumno getById(int codigo) {
    Alumno alumno = null;
    String sql = "SELECT codAlumno, a.nombre as 'nAlumno', apellidos, email, telefono, dni_nie, fNacimiento, a.codGenero, g.nombre as 'nGenero' "
        + "FROM alumno a INNERJOIN genero g ON g.codGenero = a.codGenero"
        + "WHERE codAlumno = "
        + codigo;
    ConexionDB dbConnection = ConexionDBImp.getInstance();
    dbConnection.conectar();
    Connection conexion = dbConnection.getConexion();
    try {
      PreparedStatement pSmt = conexion.prepareStatement(sql);
      ResultSet rs = pSmt.executeQuery();
      while (rs.next()) {
        alumno = parseAlumno(rs);

      }
    } catch (SQLException e) {
      LOG.error(e.getMessage());
    } finally {
      dbConnection.desconectar();
    }

    return alumno;
  }

  /**
   * @param rs
   *          ResultSet
   * @return alumno
   * @throws SQLException
   *           excepcion sql
   */
  private Alumno parseAlumno(ResultSet rs) {
    Alumno alumno = null;
    alumno = new Alumno();
    try {
      alumno.setCodigo(rs.getInt("codAlumno"));
      alumno.setNombre(rs.getString("nAlumno"));
      alumno.setApellidos(rs.getString("apellidos"));
      alumno.setDni(rs.getString("dni_nie"));
      alumno.setGenero(Util.parseGenero(rs.getString("a.codGenero")));
    } catch (SQLException e) {
      LOG.error(e.getMessage());
    } catch (CandidatoException e) {
      LOG.error(e.getMessage());
    }
    return alumno;
  }

  /**
   * @Override
   * @param alumno
   *          Alumno
   * @return alumno
   */
  public Alumno update(Alumno alumno) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * @Override
   * @param alumno
   *          Alumno
   * @return alumno
   */
  public Alumno create(Alumno alumno) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * @Override
   * @param codigo
   *          int
   */
  public void delete(int codigo) {
    // TODO Auto-generated method stub

  }

  /**
   * @Override
   * @return lista de alumnos
   */
  public List<Alumno> getAll() {
    List<Alumno> alumnos = null;
    String sql = "{call getAllAlumno()}";
    ConexionDB dbConnection = ConexionDBImp.getInstance();
    dbConnection.conectar();
    Connection conexion = dbConnection.getConexion();
    try {
      Alumno alumno = null;
      CallableStatement cSmt = conexion.prepareCall(sql);
      ResultSet rs = cSmt.executeQuery();
      alumnos = new ArrayList<Alumno>();
      while (rs.next()) {
        alumno = parseAlumno(rs);
      }
    } catch (SQLException e) {
      LOG.error(e.getMessage());
    }
    return alumnos;
  }
}
