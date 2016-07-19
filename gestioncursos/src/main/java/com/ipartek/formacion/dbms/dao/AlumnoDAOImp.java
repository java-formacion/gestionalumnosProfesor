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
import com.ipartek.formacion.pojo.exception.CandidatoException;
import com.ipartek.formacion.service.Util;

public class AlumnoDAOImp implements AlumnoDAO{
  private static final Logger LOG=Logger.getLogger(AlumnoDAOImp.class);
private ConexionDB myConexion;
private static AlumnoDAOImp INSTANCE;

  private AlumnoDAOImp(){
    myConexion=ConexionDBImp.getInstance();
  }
  
  private synchronized static void createInstance() {
    if (INSTANCE == null) {
      INSTANCE = new AlumnoDAOImp();
    }
  }
  
  public static AlumnoDAOImp getInstance(){
   if (INSTANCE==null) {
       createInstance();
  } 
   return INSTANCE;
  }
  
  
  
  //TODO es singleton
  //pq tendra atributo de conexion
  @Override
  public Alumno getById(int codigo) {
    Alumno alumno=null;
    String sql="SELECT codAlumno, a.nombre as 'nAlumno', apellidos, email, telefono,dni_nie,fNacimiento, codGenero, g.nombre as 'nGenero'"
            + "FROM alumno a "
            + "INNER JOIN genero g ON g.codGenero=a.codGenero "
             + "WHERE codAlumno="+codigo;
   // myConexion=ConexionDBImp.getInstance();
  //  myConexion.conectar();
    Connection conexion=myConexion.getConexion();
    try {
     
      PreparedStatement pSmt=conexion.prepareStatement(sql);
      ResultSet rs= pSmt.executeQuery();
      while (rs.next()) {
        alumno=parseAlumno(rs);
      }
      
    } catch (SQLException e) {
      LOG.fatal(e.getMessage());
      
    }finally {
      myConexion.desconectar();
    }
    
    return alumno;
  }

  private Alumno parseAlumno(ResultSet rs) {
    Alumno alumno=null;
    alumno=new Alumno();
    try {
      //,'nAlumno',codAlumno, apellidos, email, telefono,dni_nie,fNacimiento, , 'nGenero'"codGenero
      alumno.setCodigo(rs.getInt("codAlumno"));
      alumno.setNombre(rs.getString("nAlumno"));
      alumno.setApellidos(rs.getString("apellidos"));
      alumno.setEmail(rs.getString("email"));
      alumno.setTelefono(rs.getString("telefono"));
      alumno.setDni(rs.getString("dni_nie"));
      alumno.setfNacimiento(rs.getDate("fNacimiento"));
      alumno.setGenero(Util.parseGenero(rs.getString("nGenero")));
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      LOG.fatal(e.getMessage());
    } catch (CandidatoException e) {
      // TODO Auto-generated catch block
      LOG.fatal(e.getMessage());
    }
    return alumno;
  }

  @Override
  public Alumno update(Alumno alumno) {
    Alumno alum=null;
    String sql="{call updateAlumno(?,?,?,?,?,?,?,?)}";
   // ConexionDB myConexion=ConexionDBImp.getInstance();
   // myConexion.conectar();
    Connection conexion = myConexion.getConexion();
    try {
      CallableStatement cSmt=myConexion.getConexion().prepareCall(sql);
      cSmt.setInt("codigo", alumno.getCodigo());
      cSmt.setString("nombre", alumno.getNombre());
      cSmt.setString("apellidos", alumno.getApellidos());
      cSmt.setString("dni", alumno.getDni()); 
      cSmt.setDate("fecha", new java.sql.Date(alumno.getfNacimiento().getTime())); 
      cSmt.setString("email", alumno.getEmail());
      cSmt.setString("telefono", alumno.getTelefono());
      cSmt.setInt("codGenero", alumno.getGenero().getCodigo());
      cSmt.executeUpdate();
    } catch (SQLException e) {
      alum=getById(alumno.getCodigo());
      LOG.fatal(e.getMessage());
      
    }finally {
      myConexion.desconectar();
    }

    
    return alum;
  }

  @Override
  public Alumno create(Alumno alumno) {
    String sql="{insertAlumno(?,?,?,?,?,?,?,?)}";
    Alumno alum=null;
    //ConexionDB myConexion=ConexionDBImp.getInstance();
    Connection conexion=myConexion.getConexion();
    try {
      CallableStatement cSmt=conexion.prepareCall(sql);
      cSmt.setString("", alumno.getNombre());

      cSmt.executeUpdate();
      
      alum=alumno;
      alum.setCodigo(cSmt.getInt("codigo"));
      alum.setApellidos(cSmt.getString("apellidos"));
      alum.setDni(cSmt.getString("dni_nie"));
      alum.setEmail(cSmt.getString("email"));
      alum.setfNacimiento(cSmt.getDate("fecha"));
      alum.setGenero(Util.parseGenero(cSmt.getString("codGenero")));
      alum.setTelefono(cSmt.getString("telefono"));
      //nombre, apellidos,
      //dni_nie,
      //email,
      //fecha,
      //codGenero,
      //telefono
    } catch (SQLException e) {
      LOG.fatal(e.getMessage());
    } catch (CandidatoException e) {
      // TODO Auto-generated catch block
      LOG.fatal(e.getMessage());
    }finally {
      myConexion.desconectar();
    }
    
    return alum;
  }

  @Override
  public void delete(int codigo) {
    String sql="";
    sql="{call deleteAlumno(?)}";
    ConexionDB myConexion = ConexionDBImp.getInstance();
    myConexion.conectar();
    
    Connection conexion=myConexion.getConexion();
        try {
          CallableStatement cSmt=conexion.prepareCall(sql);
          cSmt.setInt("codigo", codigo);
          //int nFilas=cSmt.executeUpdate(); en caso de querer lanzar una excepcion
          cSmt.executeUpdate();
        } catch (SQLException e) {
          LOG.fatal(e.getMessage());
        }finally {
          myConexion.desconectar();
        }

    
    
    
  }

  @Override
  public List<Alumno> getAll() {
    List<Alumno>alumnos=null;
    Alumno alumno=null;
    String sql="{call getAllAlumno()}";//llamada al procedimiento
    //ConexionDB myConexion=ConexionDBImp.getInstance();
    Connection conection=myConexion.getConexion();
    
    CallableStatement cSmt;
    try {
      cSmt =  conection.prepareCall(sql);
      
      ResultSet rs=cSmt.executeQuery();
      alumnos=new ArrayList<Alumno>();
      while(rs.next()){
        alumno=parseAlumno(rs);
        alumnos.add(alumno);
      }
    } catch (SQLException e) {
      LOG.error(e.getMessage());
    }finally {
      myConexion.desconectar();
    }
    

    return alumnos;
  }



}
