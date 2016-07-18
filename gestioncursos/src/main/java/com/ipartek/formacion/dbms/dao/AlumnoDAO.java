package com.ipartek.formacion.dbms.dao;

import com.ipartek.formacion.pojo.Alumno;

public interface AlumnoDAO {
	
	public Alumno getById(int codigo);
	
	public Alumno update(Alumno alumno);
	
	public Alumno insert(Alumno alumno);
	
	public void delete(int codigo);
}
