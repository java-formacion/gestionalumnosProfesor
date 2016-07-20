package com.ipartek.formacion.dbms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.ipartek.formacion.dbms.ConexionDB;
import com.ipartek.formacion.dbms.ConexionDBImp;
import com.ipartek.formacion.pojo.Alumno;
import com.ipartek.formacion.pojo.Curso;
import com.ipartek.formacion.pojo.TipoCurso;
import com.ipartek.formacion.service.Util;

public class CursoDAOImp implements CursoDAO{
  private static final Logger LOG=Logger.getLogger(CursoDAOImp.class);
  private ConexionDB myConexion;
  private static CursoDAOImp INSTANCE;
  
  private CursoDAOImp(){
    myConexion=ConexionDBImp.getInstance();
  }
  
  private synchronized static void createInstance() {
    if (INSTANCE == null) {
      INSTANCE = new CursoDAOImp();
    }
  }
  public static CursoDAOImp getInstance(){
    if (INSTANCE==null) {
        createInstance();
   } 
    return INSTANCE;
   }
  
  @Override
  public Curso getById(int codigo) {
    Curso  curso=null;
    String sql="select a.codCurso,a.nombre as nombreCurso,a.codPatrocinador,a.codTipoCurso, b.referencia,b.finicio,b.fFin,c.nombre as nombreCurso,c.codTipoCurso"
        + "from curso a inner join curso_alumno b on a.codCurso=b.codCurso "
        + "inner join tipocurso c on b.codTipoCurso=c.codTipoCurso"
        + "where a.codCurso="+codigo;

    Connection conexion=myConexion.getConexion();
    try {
     
      PreparedStatement pSmt=conexion.prepareStatement(sql);
      ResultSet rs= pSmt.executeQuery();
      while (rs.next()) {
       curso=parseCurso(rs);
      }
      
    } catch (SQLException e) {
      LOG.fatal(e.getMessage());
      
    }finally {
      myConexion.desconectar();
    }
    
    return curso;
  }

  private Curso parseCurso(ResultSet rs) {
    Curso curso=null;
    curso=new Curso();
    try {
      curso.setCodigo(rs.getInt("a.codCurso"));
      curso.setNombre(rs.getString("nombreCurso"));
      curso.setCodPatrocinador(rs.getString("a.codPatrocinador"));
      curso.setCodTipoCurso(rs.getString("a.codTipoCurso"));
      curso.setReferencia(rs.getString("b.referencia"));
      curso.setFinicio(rs.getDate("b.finicio"));
      curso.setfFin(rs.getDate("b.fFin"));
      curso.setNombreTipoCurso(rs.getString("nombreCurso"));
      curso.setTipo(Util.parseTipo(rs.getInt("c.codTipoCurso")));
      
    } catch (SQLException e) {
      
      LOG.fatal(e.getMessage());
    }

    return curso;
  }


  @Override
  public Curso update(Curso alumno) {
    Curso curso=null;
    String sql="{call updateCurso(?,?,?,?,?,?,?,?,?)}";

    Connection conexion = myConexion.getConexion();
    try {

     // codigo; nombre; codPatrocinador;  codTipoCurso; referencia;
      //finicio; fFin; nombreTipoCurso; tipo;
      CallableStatement cSmt=myConexion.getConexion().prepareCall(sql);
      
      cSmt.setInt("codigo", curso.getCodigo());
      cSmt.setString("nombre", curso.getNombre());


      //cSmt.setDate("fecha", new java.sql.Date(alumno.getfNacimiento().getTime())); 


      cSmt.executeUpdate();
    } catch (SQLException e) {
      curso=getById(curso.getCodigo());
      LOG.fatal(e.getMessage());
      
    }finally {
      myConexion.desconectar();
    }

    
    return curso;
  }

  @Override
  public Curso create(Curso alumno) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void delete(int codigo) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public List<Curso> getAll() {
    List<Curso>cursos=null;
    Curso curso=null;
    String sql="{call getAllCurso()}";//llamada al procedimiento
    //ConexionDB myConexion=ConexionDBImp.getInstance();
    Connection conection=myConexion.getConexion();
    
    CallableStatement cSmt;
    try {
      cSmt =  conection.prepareCall(sql);
      
      ResultSet rs=cSmt.executeQuery();
      cursos=new ArrayList<Curso>();
      while(rs.next()){
        curso=parseCurso(rs);
        cursos.add(curso);
      }
    } catch (SQLException e) {
      LOG.error(e.getMessage());
    }finally {
      myConexion.desconectar();
    }
    

    return cursos;
  }



}
