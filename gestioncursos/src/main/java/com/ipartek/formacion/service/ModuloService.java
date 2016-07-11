package com.ipartek.formacion.service;

import java.util.List;

import com.ipartek.formacion.pojo.Modulo;

public interface ModuloService {
	
	/*
	 * CRUD(Create, Read, Update, Delete)
	 */
	
	public Modulo createModulo(Modulo modulo);
	
	public Modulo getById(int codigo);
	
	public void deleteModulo(int codigo);
	
	public List<Modulo> getAll();
	
	public Modulo update(Modulo modulo);

}
