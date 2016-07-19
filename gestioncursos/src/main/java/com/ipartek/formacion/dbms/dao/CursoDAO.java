package com.ipartek.formacion.dbms.dao;

import java.util.List;

import com.ipartek.formacion.pojo.Curso;

public interface CursoDAO {
	public Curso getById(int codigo);

	public Curso update(Curso curso);

	public Curso create(Curso curso);

	public void delete(int codigo);

	public List<Curso> getAll();
}
