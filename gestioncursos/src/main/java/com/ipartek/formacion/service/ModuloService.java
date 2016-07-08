/**
 * 
 */
package com.ipartek.formacion.service;

import java.util.List;

import com.ipartek.formacion.pojo.Alumno;
import com.ipartek.formacion.pojo.Modulo;
import com.ipartek.formacion.pojo.exception.ModuloException;

/**
 * @author Curso
 *
 */
public interface ModuloService {
	
	public Modulo createModulo (Modulo modulo);

	
	public Modulo getById(int codigo) throws ModuloException;
	
	
	public void deleteModulo(int codigo);
	
	
	public List<Modulo> getAll();
	
	
	public Modulo updateModulo(Modulo modulo);
	
	
	

}
