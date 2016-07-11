package com.ipartek.formacion.service;

import java.util.List;

import com.ipartek.formacion.pojo.Alumno;
import com.ipartek.formacion.pojo.Curso;

public interface CursoService {
	
	/*
	 * CRUD(Create, Read, Update, Delete)
	 */
	
	public Curso createCurso(Curso curso);
	
	public Curso getById(int codigo);
	
	public void deleteCurso(int codigo);
	
	public List<Curso> getAll();
	
	public Curso update(Curso curso);
	
	public void darDeAlta(int codigo,Alumno alumno); 
	
	public void darDeBaja(int codigo,Alumno alumno); 
	

}
