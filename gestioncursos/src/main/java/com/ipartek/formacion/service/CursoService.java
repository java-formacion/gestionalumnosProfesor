/**
 * 
 */
package com.ipartek.formacion.service;

import java.util.List;

import com.ipartek.formacion.pojo.Alumno;
import com.ipartek.formacion.pojo.Curso;
import com.ipartek.formacion.pojo.exception.CursoException;

/**
 * @author Curso
 *
 */
public interface CursoService  {

	public Curso createCurso(Curso curso);
	public void deleteCurso(int codigo);
	public Curso updateCurso(Curso curso);
	public Curso getById(int codigo) throws CursoException;
	public List<Curso> getAll();
	public void darDeAlta(Alumno alumno,int codCurso) throws CursoException;
	
	public void darDeBaja(String dni, int codCurso) throws CursoException;
	
	
}
