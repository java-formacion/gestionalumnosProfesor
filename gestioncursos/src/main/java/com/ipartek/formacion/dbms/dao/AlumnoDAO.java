package com.ipartek.formacion.dbms.dao;

import java.util.List;

import com.ipartek.formacion.pojo.Alumno;

public interface AlumnoDAO {

	public Alumno getById(int codigo);
	public Alumno updateAlumno(Alumno alumno);
	public void deleteAlumno(int codigo);
	public Alumno createAlumno(Alumno alumno);
	public List<Alumno> getAll();
	
	
}
