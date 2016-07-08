package com.ipartek.formacion.service;

import java.util.List;

import com.ipartek.formacion.pojo.Alumno;

public interface AlumnoService {

	/*
	 * CRUD
	 * Create
	 * Read
	 * Update
	 * Delete
	 * 
	 */
	public Alumno createAlumno(Alumno alumno);
	
	public Alumno getById(int codigo);
	
	public void delete(int codigo);
	
	public List<Alumno> getAll();
	
	public Alumno update(Alumno alumno);
	
	
	
	
	
	
	
	
	
	
}
