package com.ipartek.formacion.service;

import java.util.List;

import com.ipartek.formacion.pojo.Alumno;
import com.ipartek.formacion.pojo.Curso;

public interface CursoService {

	public Curso createCurso(Curso curso);

	public Curso getById(int codigo);

	public void delete(int codigo);

	public List<Curso> getAll();

	public Curso update(Curso curso);

	public void darDeAlta(Alumno alumno, int codigo);

	public void darDeBaja(String dni, int codigo);
	
	public void darDeAlta(Alumno alumno);

	public void darDeBaja(Alumno alumno);
}
