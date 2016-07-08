package com.ipartek.formacion.services;

import java.util.List;

import com.ipartek.formacion.pojo.Modulo;
import com.ipartek.formacion.pojo.excepciones.ModuloServiceException;

public interface ModuloService {
	
	public Modulo getByID(int codigo);
	
	public void delete (int codigo) throws ModuloServiceException;
	
	public List<Modulo> getAll();
		
	public Modulo update (Modulo Modulo);

	public Modulo createModulo(Modulo modulo);

}
