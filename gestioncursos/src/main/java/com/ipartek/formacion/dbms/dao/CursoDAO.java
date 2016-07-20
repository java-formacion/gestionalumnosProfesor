package com.ipartek.formacion.dbms.dao;

import java.util.List;

import com.ipartek.formacion.pojo.Alumno;
import com.ipartek.formacion.pojo.Curso;

public interface CursoDAO {
  
  /* C
   * R
   * U
   * D
   * 
   * 
   * */
  public Curso getById(int codigo);//read
  
  public List<Curso> getAll();
  
  public Curso update(Curso alumno);//update
  
  public Curso create(Curso alumno);//create
  
  public void delete(int codigo);//delete
  
  
}
