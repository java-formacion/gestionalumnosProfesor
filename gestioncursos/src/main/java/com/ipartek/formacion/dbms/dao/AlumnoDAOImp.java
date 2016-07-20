package com.ipartek.formacion.dbms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ipartek.formacion.dbms.ConexionDB;
import com.ipartek.formacion.dbms.ConexionDBImp;
import com.ipartek.formacion.pojo.Alumno;

/**
 * es singleton porque sólo va a tener una conexion
 */

public class AlumnoDAOImp implements AlumnoDAO {
  private static final Logger LOG = Logger.getLogger(AlumnoDAOImp.class);
  private ConexionDB myConexion;
  private static AlumnoDAOImp INSTANCE;

  private AlumnoDAOImp() {

    myConexion = ConexionDBImp.getInstance();
  }

  public static AlumnoDAOImp getInstance() {
    if (INSTANCE == null) {
      createInstance();
    }
    return INSTANCE;
  }

  private synchronized static void createInstance() {
    if (INSTANCE == null) {
      INSTANCE = new AlumnoDAOImp();
    }
  }

  @Override
  public Alumno getById(int codigo) {

    String sql = "SELECT codAlumno, a.nombre as 'nAlumno', apellidos, email, telefono, dni-nie, fNacimiento, codGenero, g.nombre as 'nGenero"
        + "FROM alumno a" + "INNER JOIN genero g ON g.codGenero = a.codGenero"
        + "WHERE codAlumno = " + codigo;

    // myConexion.conectar();
    Alumno alumno = null;

    try {

      PreparedStatement pSmt = myConexion.getConexion().prepareStatement(sql);
      ResultSet rs = pSmt.executeQuery();
      while (rs.next()) {
        alumno = parseAlumno(rs);

      }

    } catch (SQLException e) {

      LOG.fatal(e.getMessage());
    } finally {
      myConexion.desconectar();
    }
    return alumno;
  }

  private Alumno parseAlumno(ResultSet rs) {
    Alumno alumno = new Alumno();
    try {
      alumno.setCodigo(rs.getInt("codAlumno"));
      alumno.setNombre(rs.getString("nAlumno"));
      alumno.setApellidos(rs.getString("apellidos"));
      alumno.setDni(rs.getString("dni_nie"));
      alumno.setfNacimiento(rs.getDate("fNacimiento"));

    } catch (SQLException e) {
      LOG.fatal(e.getMessage());
    }

    return alumno;
  }

  @Override
  public Alumno update(Alumno alumno) {
    Alumno alum = null;
    String sql = "{call updateAlumno(?,?,?,?,?,?,?,?)}";

    // myConexion.conectar();

    try {
      CallableStatement cSmt = myConexion.getConexion().prepareCall(sql);
      cSmt.setInt("codigo", alumno.getCodigo());
      cSmt.setString("nombre", alumno.getNombre());
      cSmt.setString("apellidos", alumno.getApellidos());
      cSmt.setString("dni", alumno.getDni());
      cSmt.setDate("fNacimiento", new java.sql.Date(alumno.getfNacimiento().getTime()));
      cSmt.setString("email", alumno.getEmail());
      cSmt.setString("telefono", alumno.getTelefono());
      cSmt.setInt("codGenero", alumno.getGenero().getCodigo());

      cSmt.executeUpdate();
      alum = alumno;
    } catch (SQLException e) {
      alum = getById(alum.getCodigo());
      LOG.fatal(e.getMessage());

    } finally {
      myConexion.desconectar();
    }

    return alum;
  }

  @Override
  public Alumno create(Alumno alumno) {
    Alumno alum = null;
    String sql = "{call insertAlumno((?,?,?,?,?,?,?,?)}";

    // myConexion.conectar();
    try {
      CallableStatement cSmt = myConexion.getConexion().prepareCall(sql);

      // cSmt.setInt("codigo", alumno.getCodigo());

      cSmt.setString("nombre", alumno.getNombre());
      cSmt.setString("apellidos", alumno.getApellidos());
      cSmt.setString("dni", alumno.getDni());
      cSmt.setDate("fNacimiento", (Date) alumno.getfNacimiento());
      cSmt.setString("email", alumno.getEmail());
      cSmt.setString("telefono", alumno.getTelefono());
      cSmt.setInt("codGenero", alumno.getGenero().getCodigo());

      cSmt.executeUpdate();

      alum = alumno;
      alum.setCodigo(cSmt.getInt(""));

    } catch (SQLException e) {
      alum = getById(alum.getCodigo());
      LOG.fatal(e.getMessage());

    } finally {
      myConexion.desconectar();
    }

    return alum;

  }

  @Override
  public void delete(int codigo) {
    String sql = "{call deleteAlumno(?)}";

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
  public List<Alumno> getAll() {
    List<Alumno> alumnos = null;
    String sql = "{call getAllAlumno()}";

    // myConexion.conectar();
    try {
      Alumno alumno = null;
      myConexion.conectar();
      Connection conexion = myConexion.getConexion();

      if (conexion == null) {
        LOG.trace("es nula");
      }
      CallableStatement cSmt = conexion.prepareCall(sql);
      ResultSet rs = cSmt.executeQuery();
      alumnos = new ArrayList<Alumno>();
      while (rs.next()) {
        alumno = parseAlumno(rs);
        alumnos.add(alumno);
      }
    } catch (SQLException e) {

      LOG.fatal(e.getMessage());
    } finally {
      myConexion.desconectar();
    }

    return alumnos;
  }

}
