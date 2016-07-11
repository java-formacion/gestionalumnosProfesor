package com.ipartek.formacion.service;

import java.util.List;

import com.ipartek.formacion.pojo.Alumno;

public interface AlumnoService {

	public Alumno createAlumno(Alumno alum);

	public Alumno getById(int codigo);
	
	public void delete(int codigo);
	
	public List<Alumno> getAll();
	
	public Alumno update(Alumno alumno);
	
	
}
