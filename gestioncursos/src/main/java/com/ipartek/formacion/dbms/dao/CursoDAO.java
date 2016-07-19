package com.ipartek.formacion.dbms.dao;

import java.util.List;

import com.ipartek.formacion.pojo.Alumno;
import com.ipartek.formacion.pojo.Curso;

public interface CursoDAO
	{
		/*
		 * CRUD: Create, Read, Update, Delete.
		 */
		
		// Create
		public Curso create(Curso curso);
		// Read
		public Curso getById(int codigo);
		// Update
		public Curso update(Curso curso);
		// Delete
		public void delete(int codigo);
		
		public List<Curso> getAll();
	}
