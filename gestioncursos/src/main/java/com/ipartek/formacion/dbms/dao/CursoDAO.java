package com.ipartek.formacion.dbms.dao;

import java.util.List;

import com.ipartek.formacion.pojo.Curso;

public interface CursoDAO {

	public Curso getById(int codigo);
	public Curso updateCurso(Curso curso);
	public void deleteCurso(int codigo);
	public Curso createCurso(Curso curso);
	public List<Curso>getAll();
}
