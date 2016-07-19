package com.ipartek.formacion.dbms.dao;

import java.util.List;

import com.ipartek.formacion.pojo.Alumno;

public interface AlumnoDAO
	{
		/*
		 * CRUD: Create, Read, Update, Delete.
		 */
		
		// Create
		public Alumno create(Alumno alumno);
		// Read
		public Alumno getById(int codigo);
		// Update
		public Alumno update(Alumno alumno);
		// Delete
		public void delete(int codigo);
		
		public List<Alumno> getAll();
	}
