/**
 * 
 */
package com.ipartek.formacion.service;

import java.util.List;

import com.ipartek.formacion.pojo.Alumno;
import com.ipartek.formacion.pojo.exception.AlumnoException;

/**
 * @author Adrian York
 *
 */
public interface AlumnoService  {

	
	public Alumno createAlumno(Alumno alumno);

	public Alumno getById(int codigo);
	
	public void deleteAlumno(int codigo);
	
	public List<Alumno> getAll();
	
	public Alumno updateAlumno(Alumno alumno);
	

	
	
	
	
	
}
