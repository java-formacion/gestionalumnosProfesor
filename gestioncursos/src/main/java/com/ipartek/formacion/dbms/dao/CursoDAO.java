package com.ipartek.formacion.dbms.dao;

import java.util.List;

import com.ipartek.formacion.pojo.Curso;

public interface CursoDAO {

	public Curso getByID(int codigo);

	public Curso update(Curso curso);

	public Curso insert(Curso curso);

	public void delete(int codigo);

	public List<Curso> getAll();

}
