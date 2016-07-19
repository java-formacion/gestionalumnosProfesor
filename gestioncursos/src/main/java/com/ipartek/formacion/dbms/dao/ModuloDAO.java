package com.ipartek.formacion.dbms.dao;

import java.util.List;

import com.ipartek.formacion.pojo.Modulo;

public interface ModuloDAO {

	public Modulo getById(int codigo);
	public Modulo updateModulo(Modulo modulo);
	public void deleteModulo(int codigo);
	public Modulo createModulo(Modulo modulo);
	public List<Modulo> getAll();
	
}
